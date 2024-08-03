package gui;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConnectionPageController{

	@FXML
	private Button btnConnect = null;

	@FXML
	private TextField ipField;

	@FXML
	private TextField portField;

	@FXML
	private Button submitOrderId = null;

	@FXML
	private Text errorMsg;
	

	@FXML
	public void connectToServer(ActionEvent event) throws Exception {
		String ipDestination = ipField.getText();
		int port = Integer.parseInt(portField.getText());
		ClientUI clinet = new ClientUI();
		Helper guiK = new Helper();
		boolean succeed = clinet.newConnection(ipDestination, port);
		if (succeed) {
			System.out.println("ConnectionPageController.java connectToServer(); ");

			
			((Node) event.getSource()).getScene().getWindow().hide();
			guiK.guiConverter("Login Page", "/gui/LoginPage.fxml", new LoginPageController(), event);
		} else {
			errorMsg.setVisible(true);
			errorMsg.setText("IP incorrect, server unreachable");
		}
	}

	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/gui/ConnectionPage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Connection Page");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			System.err.println("Error loading FXML or CSS files of the Connection Page");
			e.printStackTrace();
		}
	}

}