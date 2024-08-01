package client;

import java.io.IOException;
import java.util.ArrayList;

import EnumsAndConstants.BranchLocation;
import EnumsAndConstants.CommandConstants;
import EnumsAndConstants.UserType;
import common.ChatIF;
import gui.LoginPageController;
import logic.CommMessage;
import logic.Users.Customer;
import logic.Users.Supplier;
import logic.Users.User;
import ocsf.client.AbstractClient;

public class ChatClient extends AbstractClient {
	// Instance variables **********************************************

	ChatIF clientUI;
	public static boolean awaitResponse = false;
	private static ClientUI clientui = null;
	public static LoginPageController LoginPageController;

	// Constructors ****************************************************

	public ChatClient(String host, int port, ChatIF clientUI) throws IOException {
		super(host, port);
		this.clientUI = clientUI;
	}

	// Instance methods ************************************************

	public void handleMessageFromServer(Object msg) {
		System.out.println(msg.toString());
		System.out.println(" ChatClientD.java handleMessageFromServer(). start");

		System.out.println("STUB ChatClientD.java handleMessageFromServer(). CREATE SERVER RESPONSE");

		
        User customerBob = new Customer("3" , "bwilliams", "password123" , "Bob", "Williams" , "bobwilliams@example.com" ,"555-8765", UserType.Customer, BranchLocation.South );

		CommMessage clientPackage = new CommMessage(CommandConstants.Login, null );
		CommMessage serverPackage = new CommMessage(true, "kniga",  customerBob);
		serverPackage.setCommandForServer(clientPackage.getCommandForServer());
		
		
		//CommMessage messageFromSrv = new CommMessage(true, "pidor", Object dataFromServer) {

		
		CommMessage messageFromSrv;
		messageFromSrv = serverPackage;
		System.out.println(" ChatClientD.java handleMessageFromServer(). 1");
		System.out.println("the command is " + messageFromSrv.getCommandForServer());

		switch (messageFromSrv.getCommandForServer()) {

		case Login:
			System.out.println(" ChatClientD.java handleMessageFromServer(). 2");

			if (messageFromSrv.isSucceeded()) {
				User user = (User) messageFromSrv.getDataFromServer();
				
				//STUBBBB!!!
				

				clientui.openUserGUI(user);
			} else {
				System.out.println(messageFromSrv.getMsg());
			}
			break;
		case LogOut:
			if (messageFromSrv.isSucceeded()) {
				logic.Users.User user = (User) messageFromSrv.getDataFromServer();
				user.setIsLoggedIn(0);
				clientui.closeUserGUI(user);
			} else {
				System.out.println(messageFromSrv.getMsg());
			}
		case UpdatePersonalData:
			if (messageFromSrv.isSucceeded()) {
				User user = (User) messageFromSrv.getDataFromServer();
				clientui.user = user;
				clientui.reciveMsgToGui("Update Successfull");
			} else {
				clientui.reciveMsgToGui("error in updating user");
			}
		case GetRestaurantData:
            if (messageFromSrv.isSucceeded()) {
                ArrayList<Supplier> restaurantData = (ArrayList<Supplier>) messageFromSrv.getDataFromServer();
               // clientui.updateRestaurantData(restaurantData);
            } else {
                System.out.println("Failed to retrieve restaurant data");
            }
			break;

		}
		
	}

	

	public void handleMessageFromClientUI(Object msg) {
		try {

			awaitResponse = true;
			sendToServer(msg);
			while (awaitResponse) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			System.out.println("handleMessageFromClientUI FAILED");
			e.printStackTrace();
			clientUI.display("Could not send message to server: Terminating client." + e);
			quit();
		}
	}

	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}
}
