/**
 * @author: James Zhang
 * @directoryID: jzhang72
 * @uid: 118843940
 * @discussionNumber: 0107
 * I pledge on my honor that I have not given or received any unauthorized 
 * assistance on this assignment.
 */

/*
 * The Salaried Employee subclass differentiates from the Employee superclass
 * and the CommissionedEmployee subclass predominantly since a salaried
 * employee has a yearlySalary which is independent of how many sales they
 * make. Furthermore, their pay amount is calculated differently.
 */

package businessOffice;

public class SalariedEmployee extends Employee {

	// instance field
	private double yearlySalary;

	/**
	 * Salaried Employee constructor which first calls the superclass 
	 * constructor followed by initializing the yearlySalary superclass
	 * 
	 * @param name
	 * @param yearlySalary
	 */
	public SalariedEmployee(String name, double yearlySalary) {
		super(name);
		this.yearlySalary = yearlySalary;
	}

	/**
	 * Instead of adding the saleAmt to the instance field sales, a salaried
	 * employee shouldn't even keep track of the sales, so this overrode
	 * method should just be empty
	 */
	@Override
	public void addSales(double saleAmt) {

	}

	/**
	 * This method checks to see if a salaried employee were to work the
	 * numHours, if they'd be over the maximum of 80 hours.
	 */
	@Override
	public boolean validAddHoursOperation(int numHours) {
		return getHoursWorked() + numHours < 80;
	}

	/**
	 * This method returns the pay amount for a salaried employee, which is
	 * calculated by simply doing yearlySalry divided by the number of 
	 * pay periods, which is 26
	 */
	@Override
	public double calculatePayAmount() {
		return yearlySalary / 26;
	}

}
