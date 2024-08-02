package gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.application.Platform;
import client.ChatClient;
import client.ClientUI;

public class ConnectionPageController {
	

	
	@FXML
	private Button btnConnect = null;
	
	@FXML
	private TextField ipField;
	
	@FXML
	private Button submitOrderId = null;
	
	private ClientUI client;
	
	public void connectToServer(ActionEvent event) throws Exception {
		
		((Node)event.getSource()).getScene().getWindow().hide();
		
		String ipDestination = ipField.getText();
		client = new ClientUI();
		client.newConnection(ipDestination);


		
        try {
            // Use the correct relative path for the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LoginPage.fxml"));
            LoginPageController loginCtrl =  new LoginPageController ();
            loader.setController(new LoginPageController());
            root = loader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            //stage.setTitle("Login Page");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println("OpenUp function failed");
            e.printStackTrace();
        }
		
		
	}
	
	private Parent root;
	 public void start(Stage primaryStage) {
		// Platform.runLater(() -> { 
	        try {
	        	root = FXMLLoader.load(getClass().getResource("/gui/ConnectionPage.fxml"));
	            Scene scene = new Scene(root);
	            scene.getStylesheets().add(getClass().getResource("/gui/ConnectionPage.css").toExternalForm());
	            primaryStage.setTitle("Connection Page");
	            primaryStage.setScene(scene);
	            primaryStage.show();  
	        } catch (Exception e) {
	            System.err.println("Error loading FXML or CSS files.");
	            e.printStackTrace();
	        }    
		  //});
	    }
	
	
		public void getExitBtn(ActionEvent event) throws Exception {
			System.exit(0);			
		}
		
		
		public void openUpStart() {
		 //   Platform.runLater(() -> {
	
		 //   });
		}
			
		
			
		public void exitApp(ActionEvent event) throws Exception {
			
			System.exit(0);
		
		}
			
			
			
	
		
		
		

	
}