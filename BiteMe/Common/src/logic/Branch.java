package logic;

import java.io.Serializable;
import EnumsAndConstants.BranchLocation;

public class Branch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7545747177565237611L;
	private BranchLocation branchLocation;
	private BranchManager branchManager;
	
	public Branch(BranchLocation branchLocation, BranchManager branchManager) {
		super();
		this.branchLocation = branchLocation;
		this.branchManager = branchManager;
	}
	
	
	/**
	 * @return the BranchLocation
	 */
	public BranchLocation getBranchLocation() {
		return branchLocation;
	}
	
	/**
	 * @return the branchManager
	 */
	public BranchManager getBranchManager() {
		return branchManager;
	}
	
	/**
	 * @param BranchLocation the branchName to set
	 */
	public void setBranchLocation(BranchLocation branchLocation) {
		this.branchLocation = branchLocation;
	}
	
	/**
	 * @param branchManager the branchManager to set
	 */
	public void setBranchManager(BranchManager branchManager) {
		this.branchManager = branchManager;
	}
		
}
