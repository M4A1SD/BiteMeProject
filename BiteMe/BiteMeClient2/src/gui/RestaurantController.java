package gui;

import java.util.ArrayList;

import client.ClientUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RestaurantController {
	public ArrayList<Restaurant> getResList() {
		return reslist;
	}
	public void setResList(ArrayList<Restaurant> reslist) {
		this.reslist = reslist;
	}
	private ClientUI client;
	private ObservableList<Restaurant> data;
	@FXML
	private Button btnBack;
	@FXML
	private TableView<Restaurant> resTable;
	@FXML
	private TableColumn<Restaurant,String> resName;
	@FXML
	private TableColunm<Restaurant,Button> btnChoose;
	
	@FXML
	public void Intialize()
	{
		ArrayList<String> resdata=new ArrayList<String>();
		resdata.add("GET");
		//add the time and date
		resdata.add("");
		client.requestDetails(resdata);

	}
	public void setTable()
	{
        // Initialize the columns.
		resName.setCellValueFactory(new PropertyValueFactory<>("name"));
		btnChoose.setCellValueFactory(new PropertyValueFactory<>("chooseMenuButton"));

        // Initialize the data list and set it to the TableView.
        data = FXCollections.observableArrayList();
        resTable.setItems(data);
        Restaurant newRestaurant = new Restaurant("New Restaurant"); // Replace with actual data.
        data.add(newRestaurant);
	}
	public void Back(ActionEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide();
		client.guiConverter("Start Order", "/gui/UserHomePage.fxml");

	}
	

}
