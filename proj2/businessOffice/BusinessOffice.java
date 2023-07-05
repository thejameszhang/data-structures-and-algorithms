/**
 * @author: James Zhang
 * @directoryID: jzhang72
 * @uid: 118843940
 * @discussionNumber: 0107
 * I pledge on my honor that I have not given or received any unauthorized 
 * assistance on this assignment.
 */

/*
 * The purpose of this class is to create new accounts for businesses. A
 * business can either have a free plan or a paid plan, which are
 * differentiated by the maximum number of employees they can have
 * on the account.
 */

package businessOffice;

public class BusinessOffice {

	/**
	 * 1) This method creates a new account for a business on a paid plan, as
	 * businesses with a paid plan have no limit on the number of maxEmployees
	 * 
	 * @param name
	 * @return the newly created account
	 */
	public static Account createAccount(String name) {
		
		//if either of these edge cases are true, then return null
		if (name == null || name.equals("")) {
			return null;
		}
		
		//otherwise, create a new account and return it
		Account account = new Account(name);
		return account;
	}

	/**
	 * 2) This method creates a new account for a business on a free plan, 
	 * since businesses with a free plan have a set number of maxEmployees
	 * 
	 * @param name
	 * @param maxEmployees
	 * @return the newly created account
	 */
	public static Account createAccount(String name, int maxEmployees) {
		
		//if any of these edge cases are true, return null
		if (name == null || name.equals("") || maxEmployees <= 0) {
			return null;
		}
		
		//if it passes all of the above cases, then create and return a new
		//account
		Account account = new Account(name, maxEmployees);
		return account;
	}

}
