package client;

import java.io.IOException;

import EnumsAndConstants.CommandConstants;
import common.ChatIF;
import gui.ConnectionPageController;
import gui.Helper;
import javafx.application.Platform;
import logic.CommMessage;
import logic.Users.User;
import ocsf.client.AbstractClient;

public class ChatClient extends AbstractClient {
	// Instance variables **********************************************

	ChatIF clientUI;
	public static boolean awaitResponse = false;
	private static ClientUI clientui;
	
	private Helper guiK = new Helper();
	
	

	// Constructors ****************************************************

	public ChatClient(String host, int port, ChatIF clientUI, ClientUI clientUInstance) throws IOException {
		super(host, port);
		this.clientUI = clientUI;
		clientui = clientUInstance;
	}

	// Instance methods ************************************************
	
	@Override
	public void handleMessageFromServer(Object message) {
		System.out.println("ChatClient.java handleMessageFromServer(); "+ message.toString());
		CommMessage msg = (CommMessage) message;
		switch (msg.getCommandForServer()) {
		
		case Login:
			if (msg.isSucceeded()) {
				User user = (User)msg.getDataFromServer();
				guiK.login = (User)msg.getDataFromServer();
				System.out.println("ChatClient,java handleMessageFromServer() Login Success");
				//Platform.runLater(() -> guiK.openUserGUI(user));
			} else {
				clientui.showMsgInLoginPage(msg.getMsg());
			}
			break;
		
	
//		case LogOut:
//			if (msg.isSucceeded()) {
//				User user2 = (User) msg.getDataFromServer();
//				clientui.closeUserGUI(user2);
//			} else {
//				clientui.showMsgInLoginPage(msg.getMsg());
//			}
//			break;
			
//		case "PersonalData":
//			if (messageFromSrv.isSucceeded()) {
//				User user = (User) messageFromSrv.getDataFromServer();
//				clientui.user = user;
//				clientui.reciveMsgToGui("Update Successfull");
//			} else {
//				clientui.reciveMsgToGui("error in updating user");
//			}
//		case "GetRestaurantData":
//            if (messageFromSrv.isSucceeded()) {
//                ArrayList<Supplier> restaurantData = (ArrayList<Supplier>) messageFromSrv.getDataFromServer();
//                clientui.updateRestaurantData(restaurantData);
//            } else {
//                System.out.println("Failed to retrieve restaurant data");
//            }
//			break;
//
		default:
			break;
		}
		
	}

	

	public void handleMessageFromClientUI(CommMessage msg) {
		try {
			awaitResponse = true;
			sendToServer(msg);
			if(msg.getCommandForServer().equals(CommandConstants.Login)) {
				awaitResponse= false;
			}
			while (awaitResponse) {
				System.out.println("ChatClient.java handleMessageFromClientUI() Going to sleep");

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
