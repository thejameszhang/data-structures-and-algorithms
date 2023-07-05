/**
 * @author: James Zhang
 * @directoryID: jzhang72
 * @uid: 118843940
 * @discussionNumber: 0107
 * I pledge on my honor that I have not given or received any unauthorized 
 * assistance on this assignment.
 */

/**
 * The Course class provides a blueprint for every course object that needs
 * to be instantiated in the university registrar. The instance fields
 * that define a course are a String department, int course number, and int
 * numSeats. The helper methods are used to compare if a course meets some requirements with respect to the values of its instance fields.
 */

package registrar;

public class Course {
	private String department;
	private int number;
	private int numSeats;

	/*
	 * constructor for the course class initializes the department, number,
	 * numSeats, and empty students arraylist
	 */
	public Course(String department, int number, int numSeats) {
		this.department = department;
		this.number = number;
		this.numSeats = numSeats;
	}

	/**
	 * This is a helper method in the searchCourseInRegistrar method. It checks
	 * if the department and number of a course match the specified department
	 * and number
	 * 
	 * @param department
	 * @param number
	 * @return boolean if the course is found
	 */
	public boolean checkCourse(String department, int number) {
		return this.department.equals(department) && this.number == number;
	}

	/**
	 * This is a helper method in the addToCourse method. This method determines
	 * if there are available seats left in the course
	 * 
	 * @param none
	 * @return if the number of students in the course is less than the number
	 *         of seats in the class
	 */
	public boolean checkNumStudents(int num) {
		return num < numSeats;
	}

}