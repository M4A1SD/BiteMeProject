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
import logic.Users.User;


public class PersonalDataController {
	private User user;
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
	
	public PersonalDataController(User user) {
		this.user=user;
	}

	@FXML
	public void intialize()
	{
		//add the personaldata
		ArrayList<String> data=new ArrayList<String>();
		data.add(user.getUserName().toString());
		data.add(user.getPassword().toString());
		ClientUI.RequestData(new CommMessage(CommandConstants.getPersonalData,data));
		Name.setText(user.getFirstName());
		Phone.setText(user.getPhoneNumber());
		FamilyName.setText(user.getLastName());
		Email.setText(user.getEmail());
		Id.setText(user.getId());
	}
	
	public void UpdateDetails(ActionEvent event)
	{
		user.setFirstName(Name.getText());
		user.setLastName(FamilyName.getText());
		user.setPhoneNumber(Phone.getText());
		user.setEmail(Email.getText());
		user.setId(Id.getText());
		ArrayList<String> data=new ArrayList<String>();
		data.add(user.toString());
		ClientUI.RequestData(new CommMessage(CommandConstants.setPersonalData,data));
		
		// correct this section ---->
		
//		error.setText(clientui.ReturnMsgToGui());
//		error.setVisible(true);
		
		// <------------
	}
	
	public void back(ActionEvent event) throws Exception{

	}
	
	
}
