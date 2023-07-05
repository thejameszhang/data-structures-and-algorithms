/**
 * @author: James Zhang
 * @directoryID: jzhang72
 * @uid: 118843940\
 * @discussionNumber: 0107
 * I pledge on my honor that I have not given or received any unauthorized 
 * assistance on this assignment.
 */

/**
 * The student class provides the blueprint for all student objects that are
 * instantiated from the class. The instance fields that define a student
 * are the student's firstName, and lastName. The helper methods in this class are mainly used to compare the equality of some parameters with a course's instance fields firstName and lastName.
 */

package registrar;

public class Student {
	private String firstName;
	private String lastName;

	/**
	 * constructor that initializes firstName, lastName, and the student's
	 * schedule
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * This is a helper method in the countStudentsInCourseWithLastName method.
	 * It checks to see if the student's last name is the same as the specified
	 * last name
	 * 
	 * @param lastName
	 * @return boolean if the last names match
	 */
	public boolean checkLastName(String lastName) {
		return this.lastName.equals(lastName);
	}

	/**
	 * This is a helper method found in the checkStudentInCourse method. It
	 * checks to see if a student's first and last name match the specified
	 * first and last name
	 * 
	 * @param firstName
	 * @param lastName
	 * @return if the first and last names match
	 */
	public boolean checkFirstAndLastName(String firstName, String lastName) {
		return this.firstName.equals(firstName)
				&& this.lastName.equals(lastName);
	}
}