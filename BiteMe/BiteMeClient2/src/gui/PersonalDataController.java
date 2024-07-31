package gui;

import java.util.ArrayList;

import com.mysql.cj.xdevapi.Client;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PersonalDataController {
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	private ClientUI clientui;
	private Client client;
	@FXML
	private Button btnBack = null;
	@FXML
	private Button btnUpdate = null;
	@FXML
	private TextField Name;
	@FXML
	private Text error;

	@FXML
	private TextField Phone;
	@FXML
	private TextField FamilyName;
	@FXML
	private TextField Email;
	@FXML
	private TextField Id;
	
	@FXML
	public void intialize()
	{
		//add the personaldata
	 clientui.requestDetails(new ArrayList<String>());
	 Name.setText(client.name);
	 Phone.setText(client.phone);
	 FamilyName.setText(client.FamilyName);
	 Email.setText(client.Email);
	 Id.setText(client.id);
	}
	
	public void UpdateDetails(ActionEvent event)
	{
		clientui.UpdateDetails(client);
	}
	
	public void back(ActionEvent event) throws Exception{
		//check what customer it is?
		((Node) event.getSource()).getScene().getWindow().hide();
		clientui.guiConverter("Home Page", "/gui/UserHomePage.fxml");
	}
	
	
}
