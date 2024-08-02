package logic.Users;

import java.io.File;
import java.io.Serializable;

import EnumsAndConstants.*;

public class BuisnessCustomer extends User  {


	
	public BuisnessCustomer(String id, String userName, String password, String firstName, String lastName, String email,
			String phoneNumber, UserType userType, BranchLocation mainBranch) {
		super( id,  userName,  password,  firstName,  lastName,  email,
				 phoneNumber,  userType,  mainBranch);

	}
	

	
	
	
}