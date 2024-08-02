package client;

import java.io.IOException;
import EnumsAndConstants.BranchLocation;
import EnumsAndConstants.UserType;
import gui.BusinessCustomerHomePageController;
import gui.ConnectionPageController;
import gui.CustomerHomeController;
import gui.RestaurantController;
import gui.StartOrderController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.CommMessage;
import logic.Restaurant;
import logic.items;
import logic.Orders.Delivery;
import logic.Orders.Order;
import logic.Users.Customer;
import logic.Users.User;

public class ClientUI extends Application {

	public static ClientController chat; // only one instance
	public static ChatClient client;
	public User user;
	public Restaurant restaurant;
	public Delivery delivery;
	public Order order;
	public items currentitem;
	private String msg;

	//////// ---> Login page controller methods <--- ////////

	public void openUserGUI(User user) {

		this.user = user ;
		String fxmlStringPath = "";
		String title = "";
		Object controller = null;
		//switch case
		switch(user.getUserType()) {
		
		case Customer:
			fxmlStringPath = "/gui/CustomerHomePage.fxml";
			title = "Customer home page";
			controller = new CustomerHomeController(user);
	
			break;
		case BusinessCustomer:
			
			fxmlStringPath = "/gui/BusinessCustomerHomePage.fxml";
			title = "Business Customer home page";
			 controller = new BusinessCustomerHomePageController(user);

			break;
		
		case BranchManager:
			
			fxmlStringPath = "/gui/BranchManagerHomePage.fxml";
			title = "Branch manager home page";
			// controller = new BranchManagerHomePageController(user);

			break;
			
			
		case Supplier:
			fxmlStringPath = "/gui/SupplierHomePage.fxml";
			title = "Supplier manager home page";
			// controller = new SupplierHomePageController(user);

			break;
		case CEO:
			break;
		default:
			break;
			
			
		
		}
		
		
		guiConverter(title, fxmlStringPath, controller);	
	}

	public void closeUserGUI(User user) {
		//guiConverter("Login Page", "/gui/LoginPage.fxml");
	}
	public void SendLoggoutRequest(CommMessage commMessage)
	{
		chat.accept(commMessage);
	}

	//////// ---> Personal page controller methods <--- ////////
	public void sendUserPersonalData(CommMessage commMessage) {
		chat.accept(commMessage.toString());
	}

	/////////////////////////////////////////

	public static void main(String args[]) throws Exception {
		// launch start method
		launch(args);
	}

	//////// ---> PickRestaurantpage controller methods <--- ////////
	public void SendRestaurantData(CommMessage commMessage) {
		chat.accept(commMessage.toString());
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// new GUI - start page
		
		ConnectionPageController aFrame = new ConnectionPageController();
	

		aFrame.start(primaryStage);
	}
	//////// ---> Cart page controller methods <--- ////////
	public void GetOrderData(CommMessage commMessage)
	{
		chat.accept(commMessage.toString());
	}

	// create connection to server.
	public void newConnection(String ip) {
		chat = new ClientController(ip, 5555);
		

	}

	public void guiConverter(String title, String fxmlStringPath, Object controller) {
		System.out.println("ClientUI.java guiConvcerted(); trying to open "+ title);

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlStringPath));
			loader.setController(controller);
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setTitle(title);
			stage.setScene(new Scene(root));
			stage.show();
			
		} catch (IOException e) {
			System.out.println(title + ": failed to open");
			e.printStackTrace();
		}
	}
	public void RequestData(CommMessage comm)
	{
		chat.accept(comm);
	}

	public void reciveMsgToGui(String msg) {
		this.msg = msg;
	}

	public String ReturnMsgToGui() {
		return msg;
	}

}
