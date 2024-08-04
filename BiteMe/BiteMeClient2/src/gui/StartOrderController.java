package gui;

import EnumsAndConstants.TypeOfOrder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import logic.Orders.Order;
import logic.Users.User;
import javafx.scene.Node;

public class StartOrderController {
	@FXML
	private Button deliveryBtn;
	@FXML
	private Button pickUpBtn;
	@FXML
	private Button FutureBtn;
	@FXML
	private Button backBtn;

	private User user;
	private Order order;
	private Helper helper=new Helper();
	
	public StartOrderController(User user) {
		this.user = user;
		order=new Order(user.getUserName());
	}
	@FXML
	public void Back(ActionEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide();
		helper.openUserGUI(user);
	}
	@FXML
	public void Delivery(ActionEvent event) throws Exception{
		order.setType(TypeOfOrder.Regular);
		Object controller=new DeliveryController(order,user);
		helper.guiConverter("Start Delivery", "/gui/DeliveryPage.fxml",controller,event);

	}
	@FXML
	public void PickUp(ActionEvent event) throws Exception{
		order.setType(TypeOfOrder.PickUp);
		Object controller=new PickUpAndFutureController(order,user);
		helper.guiConverter("Start PickUp or Future", "/gui/PickUpPage.fxml",controller,event);
	}
	@FXML
	public void Future(ActionEvent event) throws Exception{
		order.setType(TypeOfOrder.PRE);
		Object controller=new PickUpAndFutureController(order,user);
		helper.guiConverter("Start PickUp or Future", "/gui/PickUpPage.fxml",controller,event);
	}
}