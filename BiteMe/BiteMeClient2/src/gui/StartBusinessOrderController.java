package gui;

import java.awt.Label;
import java.awt.MenuItem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import logic.Orders.Order;
import logic.Users.User;

public class StartBusinessOrderController {
	@FXML
	private MenuButton menubutton;

	@FXML
	private MenuItem item1, item2, item3, item4, item5;
	@FXML
	private Label email1, email2, email3, email4, email5;

	@FXML
	private TextField text1, text2, text3, text4, text5;

	@FXML
	private Button choosebtn;
	@FXML
	private Button backbtn;
	
	private User user;
	private Order order;
	private Helper helper=new Helper();

	public StartBusinessOrderController(User user) {
		this.user = user;
		order=new Order(user.getUserName());
	}
	
	
	
	
	
	
	
	

}
