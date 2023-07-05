/**
 * @author: James Zhang
 * @directoryID: jzhang72
 * @uid: 118843940
 * @discussionNumber: 0107
 * I pledge on my honor that I have not given or received any unauthorized 
 * assistance on this assignment.
 */

/*
 * The purpose of this Account class is make modifications based on the
 * businesses desired. A business may hire both commissioned and salaried
 * workers, log hours for their workers, add sales that a worker may have
 * made, calculate a worker's wages as well as the total payroll, and other
 * functions that a business would like it have.
 */

package businessOffice;

public class Account {

	// declaring instance fields
	private String accName;
	private int maxEmployees;
	private DatarrayList list;

	// constructor for an account with a paid plan
	public Account(String name) {
		
		//initializing instance fields
		accName = name;
		maxEmployees = Integer.MAX_VALUE;
		list = new DatarrayList();
	}

	// constructor for an account with a free plan
	public Account(String name, int maxEmployees) {
		
		//initializing instance fields
		accName = name;
		this.maxEmployees = maxEmployees;
		list = new DatarrayList();
	}

	/**
	 * 1) This method returns the name of the account
	 * 
	 * @return accName of the account as defined in the BusinessOffice class
	 */
	public String getAccountName() {
		return accName;
	}

	/**
	 * 2) This method adds a new commissioned employee to the company's Account.
	 * There are 5 cases where this method doesn't add the Commissioned Worker 
	 * 1. If the name is null
	 * 2. If the name is an empty string 
	 * 3. If the commission rate is not strictly greater than 0.0 and less than
	 * or equal to 100.0
	 * 4. If an employee in the company's account with the same name 
	 * already exists 
	 * 5. If the number of employees in the company's account is greater than
	 * or equal to the number of maxEmployees
	 * If all these base cases are passed, then the company can add the 
	 * Commissioned Worker, and the method returns true
	 * 
	 * @param name of the employee
	 * @param commissionRate of the commissioned worker
	 * @return boolean if the worker was hired or not
	 */
	public boolean hireCommissionedWorker(String name, double commissionRate) {

		// cases 1-5
		if (name == null || name.equals("") || commissionRate <= 0.0 
			|| commissionRate > 100.0 || list.getSize() >= maxEmployees 
			|| isEmployee(name)) {
			return false;
		}

		// otherwise, add the commissioned employee and return true
		list.add(new CommissionedEmployee(name, commissionRate));
		return true;
	}

	/**
	 * 3) This method is very similar to the hireCommissionedWorker method. 
	 * However, instead of checking if the commission rate is strictly greater 
	 * than 0.0, it checks if the yearlySalary is. Other than that, the methods 
	 * are essentially the same.
	 * 
	 * @param name of the employee
	 * @param yearlySalary for the salaried employee
	 * @return boolean if the worker was hired or not
	 */
	public boolean hireSalariedWorker(String name, double yearlySalary) {

		// cases 1-5
		if (name == null || name.equals("") || yearlySalary <= 0.0 || 
			list.getSize() >= maxEmployees || isEmployee(name)) {
			return false;
		}

		// otherwise, add the commissioned employee and return true
		list.add(new SalariedEmployee(name, yearlySalary));
		return true;
	}

	/**
	 * 4) This method returns true if an employee in the company's list 
	 * has the same name as the parameter name
	 * 
	 * @param name of an employee
	 * @return boolean found or not
	 */
	public boolean isEmployee(String name) {

		// loop through all employees
		for (int i = 0; i < list.getSize(); i++) {

			// if the employee has this name
			if (((Employee) (list.get(i))).checkEmployeeName(name)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 5) This method returns the number of employees in a company's list
	 * 
	 * @return number of employees
	 */
	public int numEmployees() {
		return list.getSize();
	}

	/**
	 * 6) This method returns the maximum number of employees a company can 
	 * have based on their free or paid plan. For the free plan, it is the 
	 * value passed into the constructor. For the paid plan, it is 
	 * Integer.MAX_Value.
	 * 
	 * @return maxEmployees
	 */
	public int employeeLimit() {
		return maxEmployees;
	}

	/**
	 * This is a helper method that returns the position of an employee 
	 * specified by the name parameter in the employee list. If ther're 
	 * not found, the method will return -1.
	 * 
	 * @param name of the desired employee
	 * @return pos of the employee or -1
	 */
	public int findEmployeeByName(String name) {

		// loop through all of the employees
		for (int i = 0; i < list.getSize(); i++) {

			// if the employee has this name, note the casting to type Employee
			if (((Employee) (list.get(i))).checkEmployeeName(name)) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * 7) This method adds numHours to an employee's number of hours worked. 
	 * There are 5 cases where numHours are not added: 
	 * 1. If name is null 
	 * 2. If name is an empty string 
	 * 3. If numHours is not strictly greater than 0 
	 * 4. If there's noemployee with the specified name 
	 * 5. For a salaried employee, if numHours + their worked ours > 80, 
	 * then numHours will not be added and the method will return false
	 * 
	 * @param name of the employee
	 * @param numHours to be added
	 * @return boolean if numHours was successfully added to the employee's 
	 * hours
	 */
	public boolean workHours(String name, int numHours) {
		// cases 1 through 4
		if (name == null || name.equals("") || numHours <= 0.0
			|| !isEmployee(name)) {
			return false;
		}

		// case 5
		// int pos and employee e are used here to simplify future lines of
		// code -> it might take up slightly more memory, but it is much
		// more readable
		int pos = findEmployeeByName(name);
		Employee e = (Employee) list.get(pos);
		
		//if this adding these hours is not a valid operation, return false
		if (!e.validAddHoursOperation(numHours)) {
			return false;
		}

		// otherwise, add the hours and return true
		e.addHours(numHours);
		return true;
	}

	/**
	 * 8) This method returns the number of hours an employee has worked in the
	 * current pay period. This method returns -1 if: 
	 * 1. The name is null 
	 * 2. The name is an empty string 
	 * 3. There is no employee with the matching name
	 * 
	 * @param name
	 * @return
	 */
	public int numHours(String name) {

		// cases 1-3
		if (name == null || name.equals("") || !isEmployee(name)) {
			return -1;
		}

		// if it passes all of the cases, the return its hours worked
		int pos = findEmployeeByName(name);
		return ((Employee) (list.get(pos))).getHoursWorked();
	}

	/**
	 * 9) This method returns false if any of these 4 cases are true:
	 * 1. The name is null 
	 * 2. The name is an empty string 
	 * 3. saleAmt is not strictly greater than 0.0 
	 * 4. The employee with the specified name is not found in the list
	 * Otherwise, saleAmt is added should be added to the total amount of 
	 * sales that an employee has made in the current pay period
	 * 
	 * @param name
	 * @param saleAmt
	 * @return boolean if the sale was added or not
	 */
	public boolean makeSale(String name, double saleAmt) {
		// cases 1-4
		if (name == null || name.equals("") || saleAmt <= 0.0 
			|| !isEmployee(name)) {
			return false;
		}

		int pos = findEmployeeByName(name);
		Employee e = (Employee) (list.get(pos));

		// Only commissioned employees need their sales to be tracked
		e.addSales(saleAmt);
		return true;

	}

	/**
	 * 10) This method returns the total amount of sales that an employee 
	 * has made during the current pay period if they are a commissioned 
	 * employee. This method returns -1.0 if any of the following 3 cases
	 * are true: 
	 * 1. Name is null
	 * 2. Name is an empty string
	 * 3. Employee is not found Additionally, if the
	 * employee is salaried, then the method just returns 0.0
	 * 
	 * @param name
	 * @return total amount of sales made
	 */
	public double amtSalesMade(String name) {
		
		//cases 1-3
		if (name == null || name.equals("") || !isEmployee(name)) {
			return -1.0;
		}

		//otherwise, return the employee's total sales
		int pos = findEmployeeByName(name);
		Employee e = (Employee) (list.get(pos));
		return e.getTotalSales();
	}

	/**
	 * 11) This method returns the pay amount for both salaried employees and
	 * commissioned employees if the method call passes the following 3 cases: 
	 * 1. Name is null 
	 * 2. Name is an empty string 
	 * 3. Employee not found
	 * 
	 * @param name
	 * @return
	 */
	public double getPayAmount(String name) {
		
		//cases 1-3
		if (name == null || name.equals("") || !isEmployee(name)) {
			return -1.0;
		}
		
		//otherwise, return the employees' pay amount
		int pos = findEmployeeByName(name);
		Employee e = (Employee) (list.get(pos));
		return e.calculatePayAmount();
	}

	/**
	 * 12) This method returns the total amount of that the current account 
	 * has to pay all if its employees
	 * 
	 * @return total amount that the company must pay
	 */
	public double getPayroll() {

		// initialize and declare the return value sum
		double sum = 0.0;

		// loop through all of the employees
		for (int i = 0; i < list.getSize(); i++) {
			
			//add each employee's pay amount to the cumulative sum
			sum += ((Employee) (list.get(i))).calculatePayAmount();
		}
		return sum;
	}

	/**
	 * 13) This method indicates the start of a new pay period, and it resets 
	 * all of the employees' hours and sales to 0.
	 */
	public void newPayPeriod() {

		// loop through all of the employees
		for (int i = 0; i < list.getSize(); i++) {

			// call the reset method on each employee
			((Employee) (list.get(i))).reset();
		}
	}

	/**
	 * 14) This method returns the amount that a company has to pay you for 
	 * the use of your system. A free company pays nothing, while a paid 
	 * company pays $10 for each hired employee
	 * 
	 * @return
	 */
	public double billAmount() {
		double result = 0.0;

		// this condition determines if this account is a paid plan
		if (maxEmployees == Integer.MAX_VALUE) {

			// if it is, then for every employee, the company pays $10
			result = list.getSize() * 10.0;
		}

		return result;
	}

}
