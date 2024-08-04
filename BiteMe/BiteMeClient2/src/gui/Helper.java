package gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.Users.User;

public class Helper {
			
	public static User login = null;
	public static String errorMsg = null;
	
	public void openUserGUI(User user) {
		String fxmlStringPath = "";
		String title = "";
		Object controller = null;
			try {
				switch(user.getUserType()) {
					case Customer:
						fxmlStringPath = "/gui/CustomerHomePage.fxml";
						title = "Customer home page";
						controller= new CustomerHomeController(user);
						break;
					case BusinessCustomer:
						fxmlStringPath = "/gui/BusinessCustomerHomePage.fxml";
						title = "Business Customer home page";
						controller= new BusinessCustomerHomePageController(user);
						break;
					case BranchManager:
						fxmlStringPath = "/gui/BranchManagerHomePage.fxml";
						title = "Branch Manager home page";
						controller= new BranchManagerHomePageController(user);
						break;
					case CEO:
						fxmlStringPath = "/gui/CEOHomePage.fxml";
						title = "CEO home page";
						controller= new CEOHomePageController(user);
						break;
					case Supplier:
						fxmlStringPath = "/gui/SupplierHomePage.fxml";
						title = "Supplier home page";
						controller= new SupplierHomePageController(user);
						break;
					default:
						System.out.println("User type not found");
						break;
				}
			}
			catch (Exception e) {
				System.err.println("Helper.java openUserGUI(); error in switch case");
				e.printStackTrace();
			}
			newGui(title , fxmlStringPath, controller);	
	}
		
		
	public void newGui(String title, String fxmlStringPath, Object controller) {
//		Platform.runLater(() -> {	
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlStringPath));
			loader.setController(controller);
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setTitle(title);
			stage.setScene(new Scene(root));
			stage.show();		
		} catch (IOException e) {
			System.out.println(title + ": failed to open");
			e.printStackTrace();
		}
//		});
	}
	
	
	public void guiConverter(String title, String fxmlStringPath, Object controller, ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlStringPath));
			loader.setController(controller);
            ((Node) event.getSource()).getScene().getWindow().hide(); 

			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setTitle(title);
			stage.setScene(new Scene(root));
			stage.show();
			
		} catch (IOException e) {
			System.out.println(title + ": failed to open");
			e.printStackTrace();
		}
	}
}
