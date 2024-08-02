package client;

import EnumsAndConstants.BranchLocation;
import EnumsAndConstants.UserType;
import gui.BusinessCustomerHomePageController;
import logic.Users.*;

public class UserStubs {

	
	public User customerBob = new Customer("3" , "bwilliams", "password123" , "Bob", "Williams" , "bobwilliams@example.com" ,"555-8765", UserType.Customer, BranchLocation.South );
	public User businessCustomer = new BuisnessCustomer("4" , "cjones", "password123" , "Cathy", "Jones" , "cathyjones@example.com" ,"555-8765", UserType.BusinessCustomer, BranchLocation.South );




}
