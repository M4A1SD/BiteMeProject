package client;

import java.io.IOException;

import EnumsAndConstants.UserType;
import gui.ConnectionPageController;
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
		this.user = user;
		String fxmlStringPath = "";
		String title = "";
		if (user.getUserType().equals(UserType.Customer)) {
			fxmlStringPath = "/gui/CustomerHomePage.fxml";
			title = "Customer home page";
		}
		if (user.getUserType().equals(UserType.BusinessCustomer)) {
			fxmlStringPath = "/gui/BusinessCustomerHomePage.fxml";
			title = "Business Customer home page";
		}
		if (user.getUserType().equals(UserType.BranchManager)) {
			fxmlStringPath = "/gui/BranchManagerHomePage.fxml";
			title = "Branch Manager home page";
		}
		if (user.getUserType().equals(UserType.CEO)) {
			fxmlStringPath = "/gui/CEOHomePage.fxml";
			title = "CEO home page";
		}
		if (user.getUserType().equals(UserType.Supplier)) {
			this.restaurant.setSupplierId(user.getId());
			fxmlStringPath = "/gui/SupplierHomePage.fxml";
			title = "Supplier home page";
		} else {
			System.out.println("User type not found");
		}
		guiConverter(title, fxmlStringPath);
	}

	public void closeUserGUI(User user) {
		guiConverter("Login Page", "/gui/LoginPage.fxml");
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

	public void guiConverter(String title, String fxmlStringPath) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlStringPath));
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
