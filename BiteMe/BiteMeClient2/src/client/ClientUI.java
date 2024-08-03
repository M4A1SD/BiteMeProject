package client;

import java.io.IOException;

import gui.BusinessCustomerHomePageController;
import gui.ConnectionPageController;
import gui.CustomerHomeController;
import gui.LoginPageController;
import gui.ManagementHomePageController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import logic.CommMessage;
import logic.Users.User;

public class ClientUI extends Application {

	public static ClientController chat; // only one instance
	public static ChatClient client;
	public static ClientUI instance;
	private User user;

	
    
	//////// ---> Login page controller methods <--- ////////

		
	
//	public void closeAppAfterDisconnectClient() {
//		System.exit(0);	
//	}
	
//	public void closeUserGUI(User user) {
//		guiConverter("Login Page", "/gui/LoginPage.fxml");
//	}
	
//	public void SendLoggoutRequest(CommMessage commMessage)
//	{
//		chat.accept(commMessage);
//	}

//	//////// ---> Personal page controller methods <--- ////////
//	public void sendUserPersonalData(CommMessage commMessage) {
//		chat.accept(commMessage.toString());
//	}

	/////////////////////////////////////////

	public static void main(String args[]) throws Exception {
		// launch start method
		launch(args);
	}

//	//////// ---> PickRestaurantpage controller methods <--- ////////
//	public void SendRestaurantData(CommMessage commMessage) {
//		chat.accept(commMessage.toString());
//	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// new GUI - start page
		ConnectionPageController aFrame = new ConnectionPageController();
		aFrame.start(primaryStage);
	}
	
//	//////// ---> Cart page controller methods <--- ////////
//	public void GetOrderData(CommMessage commMessage)
//	{
//		chat.accept(commMessage.toString());
//	}

	// create connection to server.
	public boolean newConnection(String ip, int port){
		try {
			chat = new ClientController(ip, port, this);
			return true;
		}
		catch (IOException ex) {
			return false;
		}
	}
	


	
	public void RequestData(CommMessage comm)
	{
		
		chat.accept(comm);
	}

//	public void reciveMsgToGui(String msg) {
//		this.msg = msg;
//	}
//
//	public String ReturnMsgToGui() {
//		return msg;
//	}
	
	public User getUser() {
		return user;
	}
	
	public void showMsgInLoginPage(String errorMsg){
		LoginPageController login = new LoginPageController();
		login.showErrorInLoginPageController(errorMsg);
	}

}
