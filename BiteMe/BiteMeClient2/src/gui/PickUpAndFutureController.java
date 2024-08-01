package gui;

import java.time.LocalTime;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PickUpAndFutureController {
	private ClientUI client;
	@FXML
	private Button btnChooseRestuarant = null;

	@FXML
	private Button btnBack = null;
	@FXML
	private TextField Name;
	@FXML
	private Text error;

	@FXML
	private TextField Phone;
	@FXML
	private MenuButton timeMenuButton;
	@FXML
	private DatePicker date;

	public void Choose(ActionEvent event) throws Exception {
		if (Name.getText() != null || Phone.getText() != null||timeMenuButton.getText()=="select time"||date.getValue()!=null) {
			error.setVisible(true);
			error.setText("please fill all fields");
		} else {
			((Node) event.getSource()).getScene().getWindow().hide();
			client.guiConverter("restaurant", "/gui/restaurantPage.fxml");
		}
	}
	public void back(ActionEvent event) throws Exception{
		((Node) event.getSource()).getScene().getWindow().hide();
		client.guiConverter("Start order", "/gui/StartOrder.fxml");
	}
	
	private void initializeTimeMenuItems() {
		int currentHour = LocalTime.now().getHour();
		int currentMinute = LocalTime.now().getMinute();
		int roundedMinute = (currentMinute / 10) * 10 + 10;
		if (roundedMinute == 60) {
			roundedMinute = 0;
			currentHour += 1;
		}
		
		for (int hour = currentHour; hour < 24; hour++) {
			for (int minute = (hour == currentHour) ? roundedMinute : 0; minute < 60; minute += 10) {
				String timeString = String.format("%02d:%02d", hour, minute);
				MenuItem timeItem = new MenuItem(timeString);
				timeItem.setOnAction(e -> timeMenuButton.setText(timeString));
				timeMenuButton.getItems().add(timeItem);
			}
		}
	}
}
