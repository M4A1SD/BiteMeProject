package gui;

import java.util.ArrayList;

import EnumsAndConstants.CommandConstants;
import client.ClientUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.CommMessage;
import logic.Users.User;

public class LoginPageController{

    @FXML
    private Button btnExit = null;
    
    @FXML
    private Button btnLogin = null;
    
    @FXML
    private TextField userName;
    
    @FXML
    private TextField password;
    
    @FXML
    private Text error;
    
    private ClientUI client= new ClientUI();
    
    private Helper guiK = new Helper();
    

    
    // Submit button to read user input and send to server
    public void sumbitCredentialsToServer(ActionEvent event) throws Exception {
        String userInput = userName.getText();
        String passInput = password.getText();
        userInput = "cjones";
        passInput = "password123";
        ArrayList<String> sendToServer = new ArrayList<String>();
        sendToServer.add(userInput);
        sendToServer.add(passInput);
        //stage.close();
       // Platform.exit();
        client.RequestData(new  CommMessage(CommandConstants.Login,sendToServer));
        while (Helper.login==null) {
        	Thread.sleep(50);
        }
        ((Node)event.getSource()).getScene().getWindow().hide();
        System.out.println("LoginPageController.java sumbitCredentialsToServer(); sent to server and hid. sending " + Helper.login);
        
        guiK.openUserGUI(Helper.login);
    }
    

    public void showErrorInLoginPageController(String errorMsg){
    		error.setVisible(true);
    		error.setText(errorMsg);
    }
    
    public void exitApp(ActionEvent event) throws Exception {
    	System.exit(0);
    }
    
    @FXML
    public void exitApplication(ActionEvent event) {
        try {
			this.exitApp(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    

}
