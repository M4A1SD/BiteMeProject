package logic;

import java.io.File;

import EnumsAndConstants.*;

public class CEO extends User {


	public CEO(String userName, String password, String firstName, String lastName, String id,
			String email, String phoneNumber, UserType userType, String organization,
			BranchLocation mainBranch, String role, UserStatus status)
	{
		super(userName, password, firstName, lastName, id, email, phoneNumber, userType,
				organization,mainBranch, role, status);
		
		
	}

}
