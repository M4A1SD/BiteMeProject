package gui;

import java.util.ArrayList;

import EnumsAndConstants.CommandConstants;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import logic.CommMessage;

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
    
    private Helper helper = new Helper();
    
    
    @FXML
    public void sumbitCredentialsToServer(ActionEvent event) throws Exception {
        String userInput = userName.getText();
        String passInput = password.getText();
        ArrayList<String> sendToServer = new ArrayList<String>();
        sendToServer.add(userInput);
        sendToServer.add(passInput);
        ClientUI.RequestData(new CommMessage(CommandConstants.Login,sendToServer));
        while(Helper.login == null) {
        	Thread.sleep(50);
        	if(Helper.errorMsg != null) {
        		error.setVisible(true);
        		error.setText(Helper.errorMsg);
        		break;
        	}
        }
        if(Helper.login != null) {
        	((Node)event.getSource()).getScene().getWindow().hide();
        	helper.openUserGUI(Helper.login);
        }
        else {
        	return;
        }

    }
    
//    public void logoutPressed(ActionEvent event) throws Exception {
//        ArrayList<String> sendToServer = new ArrayList<String>();
//        sendToServer.add(client.getUser().getUserName());
//        sendToServer.add(client.getUser().getPassword());
//    	client.SendLoggoutRequest(new CommMessage(CommandConstants.LogOut,sendToServer));
//    }
    
//    public void showErrorInLoginPageController(String errorMsg){
//    		error.setVisible(true);
//    		error.setText(errorMsg);
//    }
    
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
