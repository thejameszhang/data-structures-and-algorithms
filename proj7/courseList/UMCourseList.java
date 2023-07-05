/**
 * @author: James Zhang
 * @directoryID: jzhang72
 * @uid: 118843940
 * @discussionNumber: 0107
 * I pledge on my honor that I have not given or received any unauthorized 
 * assistance on this assignment.
 */

package courseList;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.HashSet;

public class UMCourseList {

	private Map<String, Collection<String>> courses;

	public UMCourseList() {
		courses = new HashMap<>();
	}

	public void takeCourse(String student, String course)
			throws IllegalArgumentException {

		if (student == null || course == null)
			throw new IllegalArgumentException("Parameters cannot be null.");

		Collection<String> studentCourses;

		if (courses.get(student) == null)
			studentCourses = new HashSet<>();
		else
			studentCourses = courses.get(student);

		studentCourses.add(course);
		courses.put(student, studentCourses);
	}

	public boolean isTakingCourse(String student, String course)
			throws IllegalArgumentException {

		if (student == null || course == null)
			throw new IllegalArgumentException("Parameters cannot be null.");

		boolean res = false;
		if (courses.containsKey(student)) {
			if (courses.get(student).contains(course))
				res = true;
		}
		return res;
	}

	public int numCoursesTaking(String student)
			throws IllegalArgumentException {

		if (student == null)
			throw new IllegalArgumentException("Parameters cannot be null.");

		int res;
		if (courses.get(student) == null)
			res = 0;
		else
			res = courses.get(student).size();

		return res;

	}

	public int numEnrolled(String course) throws IllegalArgumentException {

		if (course == null)
			throw new IllegalArgumentException("Parameters cannot be null.");

		Collection<String> students = courses.keySet();
		int res = 0;
		for (String s : students) {
			if (courses.get(s).contains(course))
				res++;
		}
		return res;

	}

	public String mostPopularCourse() {

		Collection<Collection<String>> allCourses = courses.values();
		int maxEnrolled = 0;
		String mostPopular = null;

		for (Collection<String> set : allCourses) {
			for (String course : set) {
				if (numEnrolled(course) > maxEnrolled) {
					mostPopular = course;
					maxEnrolled = numEnrolled(course);
				}
			}
		}
		return mostPopular;
	}

	public boolean dropCourse(String student, String course)
			throws IllegalArgumentException {

		if (student == null || course == null)
			throw new IllegalArgumentException("Parameters cannot be null.");

		boolean res = false;
		if (courses.containsKey(student)) {
			Collection<String> studentCourses = courses.get(student);
			if (studentCourses.contains(course)) {
				studentCourses.remove(course);
				courses.put(student, studentCourses);
				res = true;
			}
		}
		return res;
	}
}
