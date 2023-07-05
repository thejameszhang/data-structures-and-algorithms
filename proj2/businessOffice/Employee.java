/**
 * @author: James Zhang
 * @directoryID: jzhang72
 * @uid: 118843940
 * @discussionNumber: 0107
 * I pledge on my honor that I have not given or received any unauthorized 
 * assistance on this assignment.
 */

/*
 * The purpose of this Employee superclass is to be a blueprint for the 
 * CommissionedEmployee and SalariedEmployee subclasses to inherit from.
 * Some methods that both subclasses will need are the checkEmployeeName(), 
 * getHoursWorked(), and addHours(). The two methods that are overrided 
 * in this class are the calculatePayAmount() and reset() methods.
 */

package businessOffice;

public class Employee {

	// declare instance fields
	private String name;
	private int hoursWorked;
	private double sales;

	/**
	 * constructor called in the Account class
	 * 
	 * @param name
	 */
	public Employee(String name) {
		this.name = name;
		hoursWorked = 0;
		sales = 0.0;
	}

	/**
	 * This method returns if the specified name is the same as the name of the
	 * employee in the list
	 * 
	 * @param name
	 * @return if the names are equal
	 */
	public boolean checkEmployeeName(String name) {
		return this.name == name;
	}

	/**
	 * This getter method returns the number of hours worked by an employee
	 * 
	 * @return hoursWorked
	 */
	public int getHoursWorked() {
		return hoursWorked;
	}

	/**
	 * This method adds addHours to the instance field numHours
	 * 
	 * @param numHours
	 */
	public void addHours(int numHours) {
		hoursWorked += numHours;
	}

	/**
	 * This method gets overrode by both CommissionedEmployee and
	 * SalariedEmployee subclasses, so it just returns true, but that line
	 * of code is never executed since it's always overrode
	 * 
	 * @param numHours
	 * @return
	 */
	public boolean validAddHoursOperation(int numHours) {
		return true;
	}

	/**
	 * This method adds the saleAmt parameter to an employee's total sales
	 * 
	 * @param saleAmt
	 */
	public void addSales(double saleAmt) {
		sales += saleAmt;
	}

	/**
	 * This getter method returns an employee's total number of sales
	 * 
	 * @return sales
	 */
	public double getTotalSales() {
		return sales;
	}

	/**
	 * This method is overrode by both subclasses, as calculating the pay 
	 * amount is different depending on what type of employee someone is. It
	 * returns 0.0, but that line will never be executed.
	 * 
	 * @return 0.0
	 */
	public double calculatePayAmount() {
		return 0.0;
	}

	/**
	 * This method resets an employee's hoursWorked and sales after a 
	 * newPayPeriod.
	 */
	public void reset() {
		hoursWorked = 0;
		sales = 0.0;
	}

}
