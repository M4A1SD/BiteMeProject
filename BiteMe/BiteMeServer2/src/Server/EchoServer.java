package Server;

import java.io.IOException;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Client;

import ServerGui.ServerPortFrameController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.Order;
import logic.ClientUser;
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
        if (msg instanceof String) {
            String[] parts = ((String) msg).split("/");
            String commandType = parts[0];

            if ("GET".equals(commandType)) {
                // Get order data from SQL DB
                String orderNumber = parts[1];
                Order targetOrder = database.retrieveData(orderNumber);
                try {
                    client.sendToClient(targetOrder);
                    System.out.println("EchoServer.java handleMessageFromClient() sent targetOrder to client");
                } catch (IOException e) {
                    System.out.println("EchoServer.java handleMessageFromClient() couldn't send targetOrder to client");
                    e.printStackTrace();
                }
            } else if ("POST".equals(commandType)) {
                // POST, orderNum-PK, price, Address
                int orderNumber = Integer.parseInt(parts[1]);
                double price = Double.parseDouble(parts[2]);
                String address = parts[3];
                String restaurant = parts[4];
                int ordList  = Integer.parseInt(parts[5]);
                Order updatedOrder = new Order("", orderNumber, price, 0, address); // Assuming restaurant and list number are not required here
                boolean success = database.updateOrder(updatedOrder);
                try {
                    client.sendToClient(success ? "Update Successful/" + price + "/" + address : "Update Failed");
                } catch (IOException e) {
                    System.out.println("EchoServer.java handleMessageFromClient() couldn't send update result to client");
                    e.printStackTrace();
                }
            } else if("disconnect".equals(commandType)) {
            		
            	try {
            			clientDisconnected(client);	
            			client.sendToClient("can disconnect");
            		}catch(IOException e){
            			System.out.println("EchoServer.java handleMessageFromClient() couldn't send update result to client");
                        e.printStackTrace();
            		}
            		           
            }
            	
        }
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
    

    @Override
    protected void serverStarted() {
        System.out.println("Server listening for connections on port " + getPort());
    }

    @Override
    protected void serverStopped() {
        System.out.println("Server has stopped listening for connections.");
    }
}
