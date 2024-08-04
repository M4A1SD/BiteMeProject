package gui;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import logic.Users.Supplier;
import logic.Users.User;

public class SupplierHomePageController {
	
	@FXML
	private Button btnUpdateMenu;
	@FXML
	private Button btnActiveOrder;
	@FXML
	private Button btnPreviusOrders;
	@FXML
	private Button btnContactUs;
	@FXML
	private Button btnLogout;
	@FXML
	private ImageView personalData;

	private User user;
	private Helper helper = new Helper();
	
	public SupplierHomePageController(User user) {
		this.user = user;
	}

	public User getUser(){
		return user;
	}
	
	
//	public void Logout(ActionEvent event) throws Exception {
//		((Node) event.getSource()).getScene().getWindow().hide();
//		user.setIsLoggedIn(0);
//		client.guiConverter("Login", "/gui/LoginPage.fxml", new LoginPageController());
//	}
//	
//	public void PreviusOrder(ActionEvent event) throws Exception {
//		((Node) event.getSource()).getScene().getWindow().hide();
//		client.guiConverter("Previus Order", "/gui/PreviusOrderPage.fxml",new PreviusOrderController(user));
//	}
//	public void ActiveOrder(ActionEvent event) throws Exception {
//		((Node) event.getSource()).getScene().getWindow().hide();
//		client.guiConverter("Active Orders ", "/gui/ActiveOrderPage.fxml",new ActiveOrderController(user));
//	}
//	public void PersonalData(ActionEvent event) throws Exception {
//		((Node) event.getSource()).getScene().getWindow().hide();
//		client.guiConverter("Personal Data ", "/gui/PersonalDataPage.fxml",new PersonalDataController(user));
//	}


}