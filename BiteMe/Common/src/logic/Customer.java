package logic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import EnumsAndConstants.*;

public class Customer extends User {

	private HashMap<String, Float> refunds;  //to check
	private int customerID;
	private boolean isBusiness;
	private boolean isPrivate;
	private boolean isApproved;

	/**
	 * @param userName
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param id
	 * @param email
	 * @param phoneNumber
	 * @param userType
	 * @param organization
	 * @param mainBranch
	 * @param role
	 * @param status
	 * @param avatar
	 * @param w4c
	 * @param refundedBalance
	 * @param customerID
	 */
	public Customer(String userName, String password, String firstName, String lastName, String id, String email,
			String phoneNumber, UserType userType, String organization, BranchLocation mainBranch, String role,
			UserStatus status,  HashMap<String, Float> refunds, int customerID, boolean isPrivate,
			boolean isBusiness, boolean isApproved) {
		super(userName, password, firstName, lastName, id, email, phoneNumber, userType, organization, mainBranch, role,
				status);
		this.refunds = refunds;
		this.customerID = customerID;
		this.isPrivate = isPrivate;
		this.isBusiness = isBusiness;
		this.isApproved = isApproved;
	}

	public Customer() {
	}


	/**
	 * @return the refunds
	 */
	public HashMap<String, Float> getRefunds() {
		return refunds;
	}

	/**
	 * @param refunds the refunds to set
	 */
	public void setRefunds(HashMap<String, Float> refunds) {
		this.refunds = refunds;
	}

	/**
	 * @return the customerID
	 */
	public int getCustomerID() {
		return customerID;
	}

	/**
	 * @return the isBusiness
	 */
	public boolean isBusiness() {
		return isBusiness;
	}

	/**
	 * @return the isPrivate
	 */
	public boolean isPrivate() {
		return isPrivate;
	}

	/**
	 * @param isBusiness the isBusiness to set
	 */
	public void setBusiness(boolean isBusiness) {
		this.isBusiness = isBusiness;
	}

	/**
	 * @param isPrivate the isPrivate to set
	 */
	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	/**
	 * @return the isApproved
	 */
	public boolean isApproved() {
		return isApproved;
	}

	/**
	 * @param isApproved the isApproved to set
	 */
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	
	

}
