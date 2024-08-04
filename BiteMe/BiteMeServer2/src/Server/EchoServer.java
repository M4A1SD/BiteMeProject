package Server;

import java.io.IOException;
import java.util.ArrayList;

import EnumsAndConstants.CommandConstants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.CommMessage;
import logic.Orders.Order;
import logic.Users.ClientUser;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class EchoServer extends AbstractServer {
    final public static int DEFAULT_PORT = 5555;
    private serverDB database;
    private ServerGui.ServerPortFrameController controller;
    private ArrayList<ClientUser> clients = new ArrayList<>();

    public EchoServer(int port, ServerGui.ServerPortFrameController controller) {
        super(port);
        this.controller = controller;
        this.database = new serverDB();
    }
    
    @Override
    public void handleMessageFromClient(Object msg, ConnectionToClient client) {
    	if (msg instanceof CommMessage) {
    		
    		CommMessage commMsg = (CommMessage)msg;
    		switch (commMsg.getCommandForServer()) {
			case Login:
				commMsg = serverDB.Login(commMsg.messageForServer.get(0), commMsg.messageForServer.get(1), commMsg);
				this.sendToClient(commMsg,client);
				break;
				
			case LogOut:
				commMsg = serverDB.Logout(commMsg.messageForServer.get(0), commMsg.messageForServer.get(1), commMsg);
				this.sendToClient(commMsg,client);
				break;
				
//			case GetRestaurant:
//	
//				break;
				
			case UpdateOrderStatus:
				
				break;
		
			default:
				break;
			}
    	}
    }
    
	public void sendToClient(Object msg, ConnectionToClient client) {
		try {
			client.sendToClient(msg);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(" Error sending msg to client !");
		}
	}
    
    

    @Override
    protected void clientConnected(ConnectionToClient client) {
    	System.out.println(client + " is connected");
    	ClientUser newClient = new ClientUser(client.getInetAddress().getHostAddress(), client.getInetAddress().getHostName(),
    			"Connected");
    	//checking if client already connected 
		for (ClientUser c : clients) {
			if (newClient.getIpAddress().equals(c.getIpAddress())
					&& newClient.getHostName().equals(c.getHostName())) {
				return;
			}
		}
		clients.add(newClient);
//		System.out.println(clients);
		controller.updateUI(newClient,true);
    }

    @Override
    protected void clientDisconnected(ConnectionToClient client) {
    	System.out.println(client + " is disconnected");
    	ClientUser newClient = new ClientUser(client.getInetAddress().getHostAddress(), client.getInetAddress().getHostName(),
    			"Connected");
		for (ClientUser c : clients) {
			if (newClient.getIpAddress().equals(c.getIpAddress())
					&& newClient.getHostName().equals(c.getHostName())) {
				System.out.println("HERE");
				c.setStatus("Disconnected");
				controller.updateUI(c,false);
				break;
			}
		}
		System.out.println(clients);
    }
    
    public ObservableList<ClientUser> getClients() {
		return FXCollections.observableArrayList(clients);
	}
    
    public boolean areAllDisconnected() {
		for (ClientUser c : clients) {
			if (c.getStatus() == "Connected") {
				return false;
			}
		}
		return true;
	}
    

    @Override
    protected void serverStarted() {
        System.out.println("Server listening for connections on port " + getPort());
    }

    @Override
    protected void serverStopped() {
        System.out.println("Server has stopped listening for connections.");
    }
}
