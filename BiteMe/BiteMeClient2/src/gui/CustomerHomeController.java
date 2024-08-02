package gui;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import logic.Users.User;

public class CustomerHomeController {
	
	private ClientUI client;
	@FXML
	private Button btnBack;
	@FXML
	private Button btnStartOrder;
	@FXML
	private Button btnPreviusOrder;
	@FXML
	private Button btnRecpites;
	@FXML
	private ImageView personalData;
	
	private User user;
	
	
	
	
	
	
	public CustomerHomeController(User user) {
		super();
		
		this.user = user;
		System.out.println("CustomerHomePage.java CustomerHomeController();   testing do i have the right User in me");
		System.out.println(user.toString());
	}
//	public void Back(ActionEvent event) throws Exception {
//		((Node) event.getSource()).getScene().getWindow().hide();
//		client.guiConverter("Login", "/gui/LoginPage.fxml");
//
//	}
//	public void PreviusOrder(ActionEvent event) throws Exception {
//		((Node) event.getSource()).getScene().getWindow().hide();
//		client.guiConverter("Previus Order", "/gui/PreviusOrderPage.fxml");
//
//	}
	
	@FXML
	public void StartOrder(ActionEvent event) throws Exception {
		System.out.println("CustomerHomePage.java StartOrder();   button clicked");
		System.out.println(user.toString());
		((Node) event.getSource()).getScene().getWindow().hide();
		
		Object controller;
		StartOrderController controller1 = new StartOrderController(user);
		controller = controller1;
		client.guiConverter("Start Order", "/gui/StartOrderPage.fxml", controller);
	}
//	public void PersonalData(ActionEvent event) throws Exception {
//		((Node) event.getSource()).getScene().getWindow().hide();
//		client.guiConverter("PersonalData Page", "/gui/PersonalDataPage.fxml");
//
//	}
}







