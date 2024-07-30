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

	ChatIF chatIf;
	public static boolean awaitResponse = false;
	private static ClientUI clientui;

	// Constructors ****************************************************

	public ChatClient(String host, int port, ChatIF chatIf) throws IOException {
		super(host, port);
		this.chatIf = chatIf;
	}

	// Instance methods ************************************************

	public void handleMessageFromServer(Object msg) {
		CommMessage messageFromSrv = new CommMessage();
		messageFromSrv = (CommMessage)msg;
		switch (messageFromSrv.getCommandForServer()) {
			case "Login":
				if (messageFromSrv.isSucceeded()) {
					User user = (User)messageFromSrv.getDataFromServer();
					clientui.openUserGUI(user);
				}
				else {
					System.out.println(messageFromSrv.getMsg());
				}
				break;
			case "Logout":
				if(messageFromSrv.isSucceeded()) {
					User user = (User)messageFromSrv.getDataFromServer();
					clientui.closeUserGUI(user);
				}
				else {
					System.out.println(messageFromSrv.getMsg());
				}
			default:
				break;
			}
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
			chatIf.display("Could not send message to server: Terminating client." + e);
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
