package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import logic.Users.User;

public class CustomerHomeController {

	@FXML
	private Button btnBack;
	
	@FXML
	private Button btnStartOrder;
	
	@FXML
	private Button btnPreviusOrder;
	
	@FXML
	private Button btnContact;
	
	@FXML
	private ImageView personalData;
	
	@FXML
	private Text contact;

	private User user;

	private Helper helper = new Helper();

	public CustomerHomeController(User user) {
		this.user = user;

	}
	
	
	@FXML 
	public void Contact(ActionEvent event) throws Exception
	{
	contact.setVisible(true);
	contact.setText("Thank you for your call we will come back in email");
	}
	
	
	@FXML
	public void Back(ActionEvent event) throws Exception {
		Object controller = new LoginPageController();
		helper.guiConverter("Login", "/gui/LoginPage.fxml",controller, event);

	}
	
	
	@FXML
	public void PreviusOrder(ActionEvent event) throws Exception {
		Object controller = new PreviusOrderController(user);
		helper.guiConverter("Previus Order", "/gui/PreviusOrderPage.fxml", controller, event);

	}

	
	@FXML
	public void StartOrder(ActionEvent event) throws Exception {
		Object controller = new StartOrderController(user);
		helper.guiConverter("Start Order", "/gui/StartOrder.fxml", controller, event);
	}
	
	
	@FXML
	public void PersonalData(ActionEvent event) throws Exception {
		Object controller =new PersonalDataController(user);
		helper.guiConverter("PersonalData Page", "/gui/PersonalDataPage.fxml",controller, event);
	}
}
