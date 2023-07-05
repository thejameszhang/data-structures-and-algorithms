/**
 * @author: James Zhang
 * @directoryID: jzhang72
 * @uid: 118843940
 * @discussionNumber: 0107
 * I pledge on my honor that I have not given or received any unauthorized 
 * assistance on this assignment.
 */

/*
 * This class represents the CommissionedEmployee, which has unique methods
 * in comparison to the Employee superclass and SalariedEmployee subclass. 
 * For example, the Commissioned Employee calculates the pay amount and
 * resets after a pay period differently.
 */

package businessOffice;

public class CommissionedEmployee extends Employee {

	// instance fields
	private double commissionRate;

	/**
	 * Constructor that first calls the superclass constructor and then 
	 * initializes commissionRate and sales
	 * 
	 * @param name
	 * @param commissionRate
	 */
	public CommissionedEmployee(String name, double commissionRate) {
		super(name);
		this.commissionRate = commissionRate;
	}

	@Override
	public boolean validAddHoursOperation(int numHours) {
		return getHoursWorked() + numHours < Integer.MAX_VALUE;
	}

	/**
	 * This method returns the pay amount for a commissioned employee, which is
	 * calculated as shown below
	 */
	@Override
	public double calculatePayAmount() {
		return super.getTotalSales() * commissionRate / 100;
	}

}
