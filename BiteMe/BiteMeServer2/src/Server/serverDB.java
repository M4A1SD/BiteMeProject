package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import EnumsAndConstants.BranchLocation;
import EnumsAndConstants.UserType;
import logic.CommMessage;
import logic.ResultSetFromDB;
import logic.Users.User;

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
    
    public static CommMessage Logout(String username, String password,CommMessage msg) {
    	String updateQuery = "UPDATE biteme.users SET isLoggedIn = 0 WHERE Username = ? AND Password = ?";
        PreparedStatement updateStmt;
		try {
			updateStmt = conn.prepareStatement(updateQuery);
			updateStmt.setString(1, username);
			updateStmt.setString(2, password);
			updateStmt.executeUpdate();
			msg.setSucceeded(true);
			msg.setMsg("User logged in successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg.setSucceeded(false);
            msg.setMsg("Database error");
		}

    	return msg;
    }
    
    public static CommMessage Login(String username, String password,CommMessage msg) {
        try {
            String query = "SELECT * FROM biteme.users WHERE Username = ? AND Password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            // Check if result set is empty
            if (!rs.isBeforeFirst()) {
                msg.setSucceeded(false);
                msg.setMsg("User not found");
                return msg;
            }

            // If user exists, process the result
            if (rs.next()) {
                if (rs.getInt("isLoggedIn") == 1) {
                    msg.setSucceeded(false);
                    msg.setMsg("User is already logged in");
                    return msg;
                } else {
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    String id = rs.getString("ID");
                    String email = rs.getString("Email");
                    String phoneNumber = rs.getString("PhoneNumber");
                    UserType role = UserType.valueOf(rs.getString("Role"));
                    BranchLocation branch = BranchLocation.getEnum(rs.getString("HomeBranch"));
                    int isLoggedIn = rs.getInt("isLoggedIn");
                    User user = new User(username,password,firstName,lastName,email,phoneNumber,role,branch,id,1);
                    //User user = new User(username, password, firstName, lastName, email, phoneNumber, role, branch, id, isLoggedIn);

                    // Update the database to set IsLoggedIn to 1
                    String updateQuery = "UPDATE biteme.users SET isLoggedIn = 1 WHERE Username = ? AND Password = ?";
                    PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                    updateStmt.setString(1, username);
                    updateStmt.setString(2, password);
                    updateStmt.executeUpdate();

                    msg.setSucceeded(true);
                    msg.setDataFromServer(user);
                    msg.setMsg("User logged in successfully");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            msg.setSucceeded(false);
            msg.setMsg("Database error");
        }

        return msg;
    }
    //optional
    public static ResultSetFromDB  getAllRecordsFrom(String from) {
    	ResultSet rs = null;
        String query = "SELECT * FROM " + from;
        
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return new ResultSetFromDB(rs);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    //prototype Methods
//    public static Order retrieveData(String orderNumber) {
//        String selectSQL = "SELECT * FROM `order` WHERE Order_number = ?";
//        try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
//            pstmt.setString(1, orderNumber);
//            ResultSet resultSet = pstmt.executeQuery();
//            if (resultSet.next()) {
//                return new Order(resultSet.getString("Restaurant"),
//                        resultSet.getInt("Order_number"),
//                        resultSet.getDouble("Total_price"),
//                        resultSet.getInt("Order_list_number"),
//                        resultSet.getString("Order_address"));
//            }
//        } catch (SQLException e) {
//            System.out.println("Failed to retrieve order: " + e.getMessage());
//        }
//        return null;
//    }

    //prototype Methods
//    public static boolean updateOrder(Order order) {
//        String updateSQL = "UPDATE `order` SET Total_price = ?, Order_address = ? WHERE Order_number = ?";
//        try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
//            pstmt.setDouble(1, order.getTotal_price());
//            pstmt.setString(2, order.getOrder_adress());
//            pstmt.setInt(3, order.getOrder_number());
//            return pstmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            System.out.println("Failed to update order: " + e.getMessage());
//            return false;
//        }
//    }

    /**
    public static void closeDBconnection() {
       try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Failed to close database connection: " + e.getMessage());
        }
    }
    */
}
