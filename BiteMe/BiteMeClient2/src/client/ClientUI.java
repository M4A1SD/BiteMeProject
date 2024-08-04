package client;

import java.io.IOException;
import gui.BusinessCustomerHomePageController;
import gui.ConnectionPageController;
import gui.CustomerHomeController;
import gui.LoginPageController;
import gui.BranchManagerHomePageController;
import gui.CEOHomePageController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.CommMessage;
import logic.Users.User;

public class ClientUI extends Application {

	public static ClientController chat; 
	public static ChatClient client;
	public static ClientUI instance;
	private User user;
	
	
	public static void main(String args[]) throws Exception {
		// launch start method
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// new GUI - start page
		ConnectionPageController aFrame = new ConnectionPageController();
		aFrame.start(primaryStage);
	}
	
	
	public static void RequestData(CommMessage comm){
		chat.accept(comm);
	}
	
	
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
	
	
	public User getUser() {
		return user;
	}
	
	
	
	
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



//	//////// ---> PickRestaurantpage controller methods <--- ////////
//	public void SendRestaurantData(CommMessage commMessage) {
//		chat.accept(commMessage.toString());
//	}


	
//	//////// ---> Cart page controller methods <--- ////////
//	public void GetOrderData(CommMessage commMessage)
//	{
//		chat.accept(commMessage.toString());
//	}


	

//	public void guiConverter(String title, String fxmlStringPath, Object controller) {
//		Platform.runLater(()->{
//			try {
//				FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlStringPath));
//				loader.setController(controller);
//				Parent root = loader.load();
//				Stage stage = new Stage();
//				stage.setTitle(title);
//				stage.setScene(new Scene(root));
//				stage.show();
//			}catch (IOException e) {
//				System.out.println(title + ": failed to open");
//				e.printStackTrace();
//			}
//		});
//	}
	
	


//	public void reciveMsgToGui(String msg) {
//		this.msg = msg;
//	}
//
//	public String ReturnMsgToGui() {
//		return msg;
//	}

}
