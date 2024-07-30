package client;

import java.io.IOException;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Client;

import common.ChatIF;
import gui.LoginPageController;
import gui.PersonalDataController;
import gui.RestaurantController;
import javafx.application.Platform;
import logic.CommMessage;
import logic.Order;
import logic.User;
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
		CommMessage messageFromSrv = new CommMessage();
		messageFromSrv = (CommMessage) msg;
		switch (messageFromSrv.getCommandForServer()) {
		case "Login":
			if (messageFromSrv.isSucceeded()) {
				User user = (User) messageFromSrv.getDataFromServer();
				clientui.openUserGUI(user);
			} else {
				System.out.println(messageFromSrv.getMsg());
			}
			break;
		case "Logout":
			if (messageFromSrv.isSucceeded()) {
				User user = (User) messageFromSrv.getDataFromServer();
				clientui.closeUserGUI(user);
			} else {
				System.out.println(messageFromSrv.getMsg());
			}
		case "PersonalData":
			if (messageFromSrv.isSucceeded()) {
				User user = (User) messageFromSrv.getDataFromServer();
				clientui.user = user;
				clientui.reciveMsgToGui("Update Successfull");
			} else {
				clientui.reciveMsgToGui("error in updating user");
			}

		}
		break;
	}

	if(equals("success")) {
				Platform.runLater(() -> LoginPageController.getInstance().successfullLogin(permissions));
			} else if(((ArrayList) msg).contains("GET")) {
				Platform.runLater(() ->RestaurantController.setTable());
			}
			else
			{
				Platform.runLater(() -> LoginPageController.getInstance().UnSuccessfullLogin(wrong));
			}
			if(msg instanceof client)
			{
				Platform.runLater(() ->PersonalDataController.setClient(msg));
			}
		if (msg instanceof Order) {
			targetOrder = (Order) msg;
			Platform.runLater(() -> LoginPageController.getInstance().openUp(targetOrder));
		} else if (msg instanceof String) {
			if(((String) msg).contains("UPDATE"))
			{
				//cuts the update from the string
				((String) msg).substring(5);
				Platform.runLater(() ->PersonalDataController.setClient((Client)msg));
			}

				else {
					LoginPageController.getInstance().displayUpdateFailedMessage();
				}
			};
		awaitResponse = false;
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
