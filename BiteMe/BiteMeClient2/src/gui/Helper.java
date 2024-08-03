package gui;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.Users.User;

public  class  Helper {
	
	public static Object login=null;
	
	
	private User user;
	
	
	
		public void openUserGUI(User user) {
			
			this.user = user;
			String fxmlStringPath = "";
			String title = "";
			Object controller = null;
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
			fxmlStringPath = "/gui/ManagementHomePage.fxml";
			title = "Management home page";
			controller= new ManagementHomePageController(user);
			break;
		case CEO:
			fxmlStringPath = "/gui/ManagementHomePage.fxml";
			title = "Management home page";
			controller= new ManagementHomePageController(user);
			break;
//		case Supplier:
//			this.restaurant.setSupplierId(user.getId());
//			fxmlStringPath = "/gui/SupplierHomePage.fxml";
//			title = "Supplier home page";
//			// add constructor !!!!!
//			break;
		default:
			System.out.println("User type not found");
		}
		System.out.println("TheGuiKing.java openUserGUI(); doing brand new gui [by servers ask]");

		
		newGui(title , fxmlStringPath , controller );

		
	}
	
	
	public void newGui(String title, String fxmlStringPath, Object controller) {
	   // Platform.runLater(() -> {
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlStringPath));
	            loader.setController(controller);

	            Parent root = loader.load();

	            Stage stage = new Stage();
	            stage.setTitle(title);
	            stage.setScene(new Scene(root));
	            stage.show();
	        } catch (IOException e) {
	            System.err.println("Error loading FXML: " + e.getMessage());
	            e.printStackTrace();
	        } catch (IllegalStateException e) {
	            System.err.println("JavaFX toolkit not initialized: " + e.getMessage());
	            e.printStackTrace();
	        } catch (Exception e) {
	            System.err.println("Unexpected error in newGui: " + e.getMessage());
	            e.printStackTrace();
	        }
	  //  });
	} 
	
	
	
//	public void guiConverter(String title, String source, Object controller, ActionEvent event) {
//		try
//		 {
//	            FXMLLoader loader = new FXMLLoader();
//	            loader.setController(controller);
//	            ((Node) event.getSource()).getScene().getWindow().hide(); // Hiding the primary window
//	            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//	            Pane root = loader.load(getClass().getResource(source).openStream());
//	            Scene scene = new Scene(root);
//	            primaryStage.setTitle("Start Frame");
//	            primaryStage.setScene(scene);
//	            primaryStage.show();
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//
//	}
	
	
	public void guiConverter(String title, String fxmlStringPath, Object controller, ActionEvent event) {
		System.out.println("TheGuiKing.java guiConvcerted(); trying to open "+ title);

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlStringPath));
			loader.setController(controller);
            ((Node) event.getSource()).getScene().getWindow().hide(); // Hiding the primary window

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
