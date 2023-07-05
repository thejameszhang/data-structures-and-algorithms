/**
 * @author: James Zhang
 * @directoryID: jzhang72
 * @uid: 118843940
 * @discussionNumber: 0107
 * I pledge on my honor that I have not given or received any unauthorized 
 * assistance on this assignment.
 */

/**
 * This class includes basic functionality for a School Registrar. Data in the 
 * class is stored in two hashmap instance fields. One hashmap named students 
 * maps from a student to a hashset of their current courses. The other hashmap 
 * named courses maps from a course to a hashset of students enrolled in that 
 * particular course. Some basic functions include adding/dropping courses,
 * registering/unregistering students, if a course contains a student, if a 
 * student is part of a course, and the number of students in a course. Adding 
 * students and courses can be done concurrently using Threads in the 
 * doRegistration method. 
 */

package registrar;

// import statements
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Registrar {

	// maps from a student to their enrolled courses
	private HashMap<Student, HashSet<Course>> students;

	// maps a course to all the students in that course
	private HashMap<Course, HashSet<Student>> courses;

	// max Courses that each student can register for
	private int maxCourses;


	// constructor for the Registrar class
	public Registrar(int maxCoursesPerStudent) {
		students = new HashMap<>();
		courses = new HashMap<>();

		// maxCourses is 1 if maxCoursesPerStudent <= 1 and the specified value
		// if otherwises
		maxCourses = maxCoursesPerStudent <= 0 ? 1 : maxCoursesPerStudent;
	}

	/**
	 * 1) This method adds a new course to the courses hashmap for the current
	 * object registrar as long as department is not an empty string and number
	 * and numSeats nonpositive.
	 * 
	 * @param department
	 * @param number
	 * @param numSeats
	 * @return the current object registrar
	 */
	public Registrar addNewCourse(String department, int number, int numSeats) {

		// check for illegal arguments
		if (department == null || department.equals("") || number <= 0)
			throw new IllegalArgumentException();

		Course c = containsCourse(department, number);

		// add a new course as the key and a new hashset as the value
		// also checks if the course is already in the registrar
		if (c == null && numSeats > 0)
			courses.put(new Course(department, number, numSeats),
				new HashSet<>());
		return this;
	}

	/**
	 * This is a helper method used in cancelCourse() that finds a specific
	 * course by looping through a hashmap
	 * 
	 * @param department
	 * @param number
	 * @return a course in the courses hashmap as specified by the parameters
	 */
	private Course containsCourse(String department, int number) {
		for (Course c : courses.keySet()) {
			if (c.checkCourse(department, number))
				return c;
		}
		return null;
	}

	/**
	 * This is a helper method in the addToCourse() method by looping through
	 * the students hashmap
	 * 
	 * @param firstName
	 * @param lastName
	 * @return a student in the students hashmap as specified by the parameters
	 */
	private Student containsStudent(String firstName, String lastName) {
		for (Student s : students.keySet()) {
			if (s.checkFirstAndLastName(firstName, lastName))
				return s;
		}
		return null;
	}

	/**
	 * 2) This method cancels a course in the courses hashmap of the given
	 * registrar if a course with that department and number exist within the
	 * courses hashmap. It also unregisters every student that was enrolled in
	 * that course.
	 * 
	 * @param department
	 * @param number
	 * @return a boolean whether or not a course was canceled or not
	 */
	public boolean cancelCourse(String department, int number) {

		// check for illegal arguments
		if (department == null || department.equals("") || number <= 0)
			throw new IllegalArgumentException();

		HashSet<Course> schedule;
		boolean result = false;

		Course c = containsCourse(department, number);

		if (c != null) {

			// first we need to remove all students enrolled in this course
			for (Student s : students.keySet()) {
				schedule = students.get(s);
				if (schedule.contains(c))
					schedule.remove(c);
			}

			// now you can remove the course from the courses hashmap
			courses.remove(c);
			result = true;
		}
		return result;
	}

	/**
	 * 3) This method returns the number of courses that currently exist in the
	 * given Registrar
	 * 
	 * @return courses.size()
	 */
	public int numCourses() {
		return courses.size();
	}

	/**
	 * 4) This method adds a student to the course defined by its department and
	 * number if it passes all of the following cases: a) the course does not
	 * exist in the registrar's course list b) the student cannot be already
	 * enrolled in the course c) the student cannot already be enrolled in
	 * maxNumber or more other courses d) there cannot be more students enrolled
	 * than the number of seats available in the course
	 * 
	 * @param department
	 * @param number
	 * @param firstName
	 * @param lastName
	 * @return if a student was added to a course succesfully or not
	 */
	public boolean addToCourse(String department, int number, String firstName,
			String lastName) {

		// check illegal arguments
		if (department == null || department.equals("") || number <= 0
				|| firstName.equals("") || firstName == null || lastName == null
				|| lastName.equals(""))
			throw new IllegalArgumentException();

		Student s = containsStudent(firstName, lastName);
		Course c = containsCourse(department, number);
		boolean result = false;

		if (c == null) {
			return false;
		}

		// in the case where the student doesn't exist
		if (s == null) {

			// if the course still has space, then we can create the student and
			// register them
			if (c.checkNumStudents(courses.get(c).size())) {

				// make a new student and their schedule hashset
				Student newStudent = new Student(firstName, lastName);
				HashSet<Course> schedule = new HashSet<>();

				// add the student to this course's hashset
				courses.get(c).add(newStudent);

				// add the course to this student's schedule
				schedule.add(c);
				students.put(newStudent, schedule);
				
				result = true;
			}
		}

		// in the other case where the student already exists
		else {

			// if the course and the student's schedule has space
			if (students.get(s).size() < maxCourses
					&& c.checkNumStudents(courses.get(c).size())) {

				// add student to this course's hashset
				courses.get(c).add(s);

				// add the course to the student's schedule
				students.get(s).add(c);
				result = true;
			}
		}
		return result;
	}

	/**
	 * 5) This method returns the number of students enrolled in a course
	 * defined by its department and number
	 * 
	 * @param department
	 * @param number
	 * @return int number of students in a specified course
	 */
	public int numStudentsInCourse(String department, int number) {

		// check illegal arguments
		if (department == null || department.equals("") || number <= 0)
			throw new IllegalArgumentException();

		// find the course
		Course c = containsCourse(department, number);

		// if the course is found, return the size of the hashset, else return
		// -1
		int result = -1;
		if (c != null)
			result = (courses.get(c)).size();
		return result;
	}

	/**
	 * 6) This method returns the number of students with the same last name
	 * enrolled in a course defined by its department and number. However, if
	 * there is no course in the Registrar with that specific department and
	 * number, then the method will return -1
	 * 
	 * @param department
	 * @param number
	 * @param lastName
	 * @return int number of students in a course with the specified last name
	 */
	public int numStudentsInCourseWithLastName(String department, int number,
			String lastName) {

		// check illegal arguments
		if (department == null || department.equals("") || number <= 0
				|| lastName == null || lastName.equals(""))
			throw new IllegalArgumentException();

		// find the course
		Course c = containsCourse(department, number);

		int result = 0;
		if (c != null) {

			// if the course exists, loop through the students and check last
			// name
			for (Student s : courses.get(c)) {
				if (s.checkLastName(lastName))
					result++;
			}
		}

		// if the course exists return result if not return -1
		return c != null ? result : -1;
	}

	/**
	 * 7) This method returns true if there is a student with the specified
	 * first and last name enrolled in the course with the specified department
	 * and number. it returns false if no such course with that combination of
	 * department and number exists, or if no student with both the matching
	 * first and last name is found in the course
	 *
	 * @param department
	 * @param number
	 * @param firstName
	 * @param lastName
	 * @return boolean if a student is in a course or not
	 */
	public boolean isInCourse(String department, int number, String firstName,
			String lastName) {

		// check illegal arguments
		if (department == null || department.equals("") || number <= 0
				|| firstName == null || firstName.equals("") || lastName == null
				|| lastName.equals(""))
			throw new IllegalArgumentException();

		// get the course and student if they exist
		Course c = containsCourse(department, number);
		Student s = containsStudent(firstName, lastName);
		boolean result = false;

		// if both exist, and both s and c are in the others hashset, return
		// true
		if (c != null && s != null) {
			if (students.get(s).contains(c) && courses.get(c).contains(s))
				result = true;
		}
		return result;
	}

	/**
	 * 8) This method returns the number of courses a student is taking. This
	 * number should always be between 0 and 5. If no student with the given
	 * first and last name exists, return 0. Note: I have two return statements
	 * in this method because if the correct student is found in the for loop,
	 * there is no need to keep checking the loop. I could break, but a TA said
	 * I should just have two return statements.
	 * 
	 * @param firstName
	 * @param lastName
	 * @return int the number of courses that a student is taking
	 */
	public int howManyCoursesTaking(String firstName, String lastName) {

		// check illegal arguments
		if (firstName == null || firstName.equals("") || lastName == null
				|| lastName.equals(""))
			throw new IllegalArgumentException();

		Student s = containsStudent(firstName, lastName);

		int result = 0;
		if (s != null)
			result = students.get(s).size();

		return result;
	}

	/**
	 * 9) This method drops a course from its student schedule as long as
	 * department, firstName, and lastName are not empty strings and number is
	 * positive. In addition, it must also unregister the student from the
	 * course.
	 * 
	 * @param department
	 * @param number
	 * @param firstName
	 * @param lastName
	 * @return if the course was successfully dropped or not
	 */
	public boolean dropCourse(String department, int number, String firstName,
			String lastName) {

		// check illegal arguments
		if (department == null || department.equals("") || number <= 0
				|| firstName == null || firstName.equals("")
				|| lastName.equals("") || lastName == null)
			throw new IllegalArgumentException();

		// get the course and student if they exist
		Course c = containsCourse(department, number);
		Student s = containsStudent(firstName, lastName);
		boolean result = false;

		// as long as both the course and student exist
		if (c != null && s != null) {

			// if student is in course and course contains the student
			if (courses.get(c).contains(s) && students.get(s).contains(c)) {
				courses.get(c).remove(s);
				students.get(s).remove(c);
				result = true;
			}
		}
		return result;
	}

	/**
	 * 10) This method removes a student from all of the courses from their
	 * schedule. Consequently, each course must also remove the student from all
	 * of their courses in the courses hashmap
	 * 
	 * @param firstName
	 * @param lastName
	 * @return if the student registration was successfully cancelled or not
	 */
	public boolean cancelRegistration(String firstName, String lastName) {

		// check illegal arguments
		if (firstName == null || firstName.equals("") || lastName.equals("")
				|| lastName == null)
			throw new IllegalArgumentException();

		// see if student exists
		Student s = containsStudent(firstName, lastName);
		boolean result = false;

		// if the student exists
		if (s != null) {

			// remove the student from each course
			for (Course c : students.get(s)) {
				courses.get(c).remove(s);
			}

			// remove the studnet from the students hashmap
			students.remove(s);
			result = true;
		}
		return result;
	}

	/**
	 * This Inner Class implements the Runnable Interface so that an instance of
	 * this class can be used as a parameter when we need to create a Thread.
	 * The class has a constructor that takes in a string filename as an
	 * argument. It also overrides the run method from the Runnable interface
	 * which essentially tries to read and parse lines from a file and makes a
	 * decision on whether or not it needs to add a new course to the registrar
	 * or add a new student to a course. Exceptions are caught in catch clauses.
	 */
	private class ThreadCreator implements Runnable {

		// instance fields of the class
		private String fileName;

		// constructor that takes in the name of a file as a parameter
		public ThreadCreator(String file) {
			fileName = file;
		}

		// make a lock of an object that all threads will have access to so I
		// used the students hashmap
		Object lock = students;

		@Override
		public void run() {

			try {

				// try to open this file
				FileReader fileReader = new FileReader(fileName);
				BufferedReader reader = new BufferedReader(fileReader);

				// read the data and call the correct method
				try {
					String thisLine;
					while ((thisLine = reader.readLine()) != null) {
						String[] args = thisLine.split("\\s+");

						// add student to a course
						if (args[0].equals("addregistration")) {
							synchronized (lock) {
								addToCourse(args[1], Integer.parseInt(args[2]),
										args[3], args[4]);
							}
						}

						// add course to Registrar
						if (args[0].equals("addcourse")) {
							synchronized (lock) {
								addNewCourse(args[1], Integer.parseInt(args[2]),
										Integer.parseInt(args[3]));
							}
						}
					}
				}

				// catch exception caused by BufferedReader readLine()
				catch (IOException ioe) {
					System.out.println("IO Exception");
				}
			}

			// catch exception potentially causes by FileReader
			catch (FileNotFoundException fnfe) {
				System.out.println("File Not Found Exception");
			}
		}
	}

	/**
	 * 11) This method essentially allows students and courses to be added to
	 * the registrar currently by making a new thread for each line that is read
	 * from the file. All threads for the current object registrar are stored in
	 * a hashset thread. Furthermore, all threads are started first and then
	 * joined to allow for the most amount of concurrency.
	 * 
	 * @param filenames
	 */
	public void doRegistrations(Collection<String> filenames) {

		// check illegal arguments
		if (filenames == null)
			throw new IllegalArgumentException();

		// make a hashset to store the threads
		Set<Thread> threads = new HashSet<>();

		// make threads and store them in the threads array
		for (String file : filenames) {
			if (file != null) {
				ThreadCreator tc = new ThreadCreator(file);
				Thread thread = new Thread(tc);
				threads.add(thread);
			}
		}

		// start each thread in the hashset thread
		for (Thread thread1 : threads)
			thread1.start();

		// join each thread in the array so public tests don't assert before the
		// threads are done running
		for (Thread thread2 : threads) {
			try {
				thread2.join();
			} catch (InterruptedException e) {
				System.out.println("Interrupted Excetpion e");
			}
		}
	}
}