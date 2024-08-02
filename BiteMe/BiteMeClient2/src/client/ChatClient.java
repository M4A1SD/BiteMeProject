package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
	private static ClientUI clientui ;
	public static LoginPageController LoginPageController;

	// Constructors ****************************************************

	public ChatClient(String host, int port, ChatIF clientUI) throws IOException {
		super(host, port);
		this.clientUI = clientUI;
		
	}

	// Instance methods ************************************************

	public void handleMessageFromServer(Object msg) {
		//msg cant be null
		

        //create login request for, for a bwiliams user
		CommMessage clientPackage = new CommMessage(CommandConstants.Login,  new ArrayList<>(Arrays.asList("bwilliams", "password123")) ); 
		

		UserStubs allCustomersStubs = new UserStubs();
		
		//create success response,
        User customer = allCustomersStubs.customerBob;
		CommMessage serverPackage = new CommMessage(true, "kniga",  customer);
		serverPackage.setCommandForServer(clientPackage.getCommandForServer());
		
		CommMessage messageFromSrv;
		messageFromSrv = serverPackage;

		switch (messageFromSrv.getCommandForServer()) {

		case Login:

			if (messageFromSrv.isSucceeded()) {
				User user = (User) messageFromSrv.getDataFromServer();



				System.out.println("Warning: ChatClient.java, field private static ClientUI clientui ; is STUBBED. might cause issues later");
				clientui = new ClientUI();
				clientui.openUserGUI(user);


			} else {
			}
			break;
		case LogOut:
			if (messageFromSrv.isSucceeded()) {
				logic.Users.User user = (User) messageFromSrv.getDataFromServer();
				user.setIsLoggedIn(0);
				clientui.closeUserGUI(user);
			} else {
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
