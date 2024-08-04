package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Callback;
import logic.Orders.Delivery;
import logic.Orders.Order;
import logic.Users.User;
import java.time.*;
import java.util.ArrayList;

public class DeliveryController {
	private Helper helper = new Helper();
	@FXML
	private Button btnChooseRestuarant = null;

	@FXML
	private Button btnBack = null;
	@FXML
	private TextField Name;
	@FXML
	private TextField Phone;
	@FXML
	private TextField Address;
	@FXML
	private Text error;

	@FXML
	private MenuButton timeMenuButton;
	@FXML
	private DatePicker date;
	private Order order;
	private Delivery delivery;
	private User user;

	public DeliveryController(Order order, User user) {
		this.order = order;
		this.user = user;
		delivery = new Delivery(order.getOrderId(), 1, order.getType(), 25);
		order.setDeliveryID(delivery.getDeliveryId());
	}
	@FXML
	public void initialize() {
		initializeTimeMenuItems();
		intializeDate();
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

	private void intializeDate() {
		date.setDayCellFactory(new Callback<DatePicker, DateCell>() {
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);

						// Disable all dates before today
						if (item.isBefore(LocalDate.now())) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;"); // Optional: add a style
						}
					}
				};
			}
		});
	}

	@FXML
	public void Choose(ActionEvent event) throws Exception {
		if (Address.getText() == null || Name.getText() == null || Phone.getText() == null
				|| timeMenuButton.getText() == "select time" || date.getValue() == null) {
			error.setVisible(true);
			error.setText("please fill all fields");

		} else {
			ArrayList<String> usernames = delivery.getUsernamesOfParticipants();
			usernames.add(order.getUsername());
			delivery.setAddress(Address.getText());
			delivery.setUsernamesOfParticipants(usernames);
			delivery.setName(Name.getText());
			delivery.setPhone(Phone.getText());
	        LocalDate localDate = date.getValue();
	        // Convert LocalDate to java.sql.Date
	        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
	        order.setAskedByCustomerDate(sqlDate);

	        String timeString = timeMenuButton.getText() + ":00"; // Convert to hh:mm:ss format
	        java.sql.Time sqlTime = java.sql.Time.valueOf(timeString);
	        order.setAskedByCustomerTime(sqlTime.toString());
			Object controller = new RestaurantController(order,user);
			helper.guiConverter("restaurant", "/gui/RestaurantPage.fxml", controller, event);
		}
	}

	@FXML
	public void back(ActionEvent event) throws Exception {
		Object controller = new StartOrderController(user);
		helper.guiConverter("Start order", "/gui/StartOrderPage.fxml", controller, event);
	}

}
