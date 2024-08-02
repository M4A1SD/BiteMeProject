package gui;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
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
		((Node) event.getSource()).getScene().getWindow().hide();
		

		Object controller = new StartOrderController(user);
		System.out.println("CustomerHomePage.java StartOrder(); now gui converter");
//		client.guiConverter("Start Order", "/gui/StartOrderPage.fxml", controller);

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/StartOrderPage.fxml"));
		loader.setController(controller);
		Parent root = loader.load();
		Stage stage = new Stage();
		stage.setTitle("Start order");
		stage.setScene(new Scene(root));
		stage.show();
		
		
	}
//	public void PersonalData(ActionEvent event) throws Exception {
//		((Node) event.getSource()).getScene().getWindow().hide();
//		client.guiConverter("PersonalData Page", "/gui/PersonalDataPage.fxml");
//ss
//	}
}







