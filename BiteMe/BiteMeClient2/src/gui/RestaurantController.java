package gui;

import java.util.ArrayList;


import EnumsAndConstants.CommandConstants;
import client.ClientUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import logic.CommMessage;
import logic.Restaurant;
import logic.Orders.Order;
import logic.Users.User;

/**
 * Controller class for the Restaurant view.
 */
public class RestaurantController {
    private ObservableList<Restaurant> observableList;
    private Helper helper=new Helper();
    @FXML
    private Button btnBack;

    @FXML
    private Button btnDone;

    @FXML
    private ListView<Restaurant> lstview;
	@FXML
	private Text error;
    private Order order;
    private User user;
    public RestaurantController(Order order,User user) {
	this.order=order;
	this.user=user;
	}

    @FXML
    public void Initialize() {
        // Create a message to request the list of restaurants
        ArrayList<String> msg = new ArrayList<>();
        msg.add(order.getAskedByCustomerTime().toString());
        msg.add(order.getAskedByCustomerDate().toString());
        CommMessage cmsg = new CommMessage(CommandConstants.GetRestaurants, msg);
        
    }
    public void SetTable(ArrayList<Restaurant> reslist) {
        // Convert ArrayList to ObservableList
    	observableList = FXCollections.observableArrayList(reslist);

        // Set the items of the ListView
        lstview.setItems(observableList);
    }

    @FXML
    public void handleBtnDone(ActionEvent event) throws Exception {
        // Add a listener to track the selected item
        lstview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // newValue is the selected item
            if (newValue != null) {
                Restaurant res = newValue;
             // Navigate to the MenuPage
                Object controller=new MenuPageController(order,res);
                helper.guiConverter("MenuPage", "/gui/MenuPage.fxml",controller,event);
            }
            else
            {
            	error.setVisible(true);
            	error.setText("please choose restaurant");
            }
        });

    }
	@FXML
	public void back(ActionEvent event) throws Exception {
		Object controller = new StartOrderController(user);
		helper.guiConverter("Start order", "/gui/StartOrderPage.fxml", controller, event);
	}
}

