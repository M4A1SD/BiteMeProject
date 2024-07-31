package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.Orders.Order;

public class serverDB {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String TIMEZONE_SETTINGS = "?serverTimezone=IST";
    private static Connection conn;
                  //IP
    //jdbc:mysql://localhost:3306/biteme/?serverTimezone=IST, root, root
    public static boolean connectToDB(String username, String password, String dbName) {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	System.out.println("Driver Definition succeed");
        } catch (Exception e) {
        	System.out.println("Driver Definition failed");
        }
    	
        
        try {    
            conn = DriverManager.getConnection(DB_URL + dbName + TIMEZONE_SETTINGS, username, password);
            System.out.println("DataBase Connection succeed");
            return true;
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError:" + e.getErrorCode());
            return false;
        }
    }

    public static Order retrieveData(String orderNumber) {
        String selectSQL = "SELECT * FROM `order` WHERE Order_number = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            pstmt.setString(1, orderNumber);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return new Order(resultSet.getString("Restaurant"),
                        resultSet.getInt("Order_number"),
                        resultSet.getDouble("Total_price"),
                        resultSet.getInt("Order_list_number"),
                        resultSet.getString("Order_address"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve order: " + e.getMessage());
        }
        return null;
    }

    public static boolean updateOrder(Order order) {
        String updateSQL = "UPDATE `order` SET Total_price = ?, Order_address = ? WHERE Order_number = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setDouble(1, order.getTotal_price());
            pstmt.setString(2, order.getOrder_adress());
            pstmt.setInt(3, order.getOrder_number());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Failed to update order: " + e.getMessage());
            return false;
        }
    }

//    public static void closeDBconnection() {
//        try {
//            if (conn != null) conn.close();
//        } catch (SQLException e) {
//            System.out.println("Failed to close database connection: " + e.getMessage());
//        }
//    }
}
