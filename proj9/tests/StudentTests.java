package tests;

import org.junit.*;

import registrar.Registrar;

import static org.junit.Assert.*;

import java.util.Arrays;

public class StudentTests {

	/*
	 * Tests that addNewCourse() only adds methods that pass the following
	 * tests: - the number cannot be negative - the numSeats cannot be negative
	 * - as long as the registrar doesn't already have a course with that
	 * department and number Also tests that numCourses works correctly
	 */
	@Test
	public void testStudent1() {
		Registrar registrar = new Registrar(5);

		assertEquals(0, registrar.numCourses());
		registrar.addNewCourse("ECON", 305, 50);
		registrar.addNewCourse("ECON", 305, 184);

		assertEquals(1, registrar.numCourses());
	}

	/*
	 * Tests that if a student has 5 courses, and then the course is cancelled,
	 * they are unregistered from the course and can thereby add a new one.
	 */
	@Test
	public void testStudent2() {
		Registrar registrar = new Registrar(5);

		registrar.addNewCourse("CMSC", 132, 10);
		registrar.addNewCourse("MATH", 340, 25);
		registrar.addNewCourse("STAT", 400, 20);
		registrar.addNewCourse("ENGL", 101, 14);
		registrar.addNewCourse("COMM", 107, 12);
		registrar.addNewCourse("STAT", 410, 10);

		registrar.addToCourse("CMSC", 132, "Abe", "Apple");
		registrar.addToCourse("MATH", 340, "Abe", "Apple");
		registrar.addToCourse("STAT", 400, "Abe", "Apple");
		registrar.addToCourse("ENGL", 101, "Abe", "Apple");
		registrar.addToCourse("COMM", 107, "Abe", "Apple");

		assertEquals(6, registrar.numCourses());
		assertFalse(registrar.cancelCourse("CMSC", 340));
		assertTrue(registrar.cancelCourse("STAT", 400));

		registrar.addToCourse("STAT", 410, "Abe", "Apple");
		assertEquals(1, registrar.numStudentsInCourse("STAT", 410));
	}

	/*
	 * Tests that addToCourse() returns false when someone tries to add a
	 * student to a course that doesn't exist in the registrar
	 */
	@Test
	public void testStudent3() {
		Registrar registrar = new Registrar(5);

		registrar.addNewCourse("CMSC", 132, 10);
		registrar.addNewCourse("MATH", 340, 25);
		registrar.addNewCourse("STAT", 400, 20);
		registrar.addNewCourse("ENGL", 101, 14);
		registrar.addNewCourse("COMM", 107, 12);
		registrar.addNewCourse("STAT", 410, 10);

		assertFalse(registrar.addToCourse("ECON", 305, "Ben", "Benny"));
	}

	/*
	 * Tests that numStudentsInCourseWithLastName() works correctly
	 */
	@Test
	public void testStudent4() {
		Registrar registrar = new Registrar(5);

		registrar.addNewCourse("CMSC", 132, 10);

		registrar.addToCourse("CMSC", 132, "Abe", "Apple");
		registrar.addToCourse("CMSC", 132, "Alex", "Apple");
		registrar.addToCourse("CMSC", 132, "Andrew", "Apple");
		registrar.addToCourse("CMSC", 132, "Anthony", "Apple");
		registrar.addToCourse("CMSC", 132, "Alice", "Apple");
		registrar.addToCourse("CMSC", 132, "Alex", "Banana");
		registrar.addToCourse("CMSC", 132, "Jack", "Orange");

		assertEquals(5, registrar.numStudentsInCourseWithLastName("CMSC", 132,
				"Apple"));
	}

	/*
	 * Tests isInCourse() and howManyCoursesTaking() work correctly before and
	 * after a course is cancelled
	 */
	@Test
	public void testStudent5() {
		Registrar registrar = new Registrar(5);

		registrar.addNewCourse("CMSC", 132, 10);
		registrar.addToCourse("CMSC", 132, "Abe", "Apple");

		assertTrue(registrar.isInCourse("CMSC", 132, "Abe", "Apple"));
		assertEquals(1, registrar.howManyCoursesTaking("Abe", "Apple"));

		registrar.cancelCourse("CMSC", 132);

		assertFalse(registrar.isInCourse("CSMC", 132, "Abe", "Apple"));
		assertEquals(0, registrar.howManyCoursesTaking("Abe", "Apple"));

	}

	/*
	 * Tests isInCourse() before and after a student drops a course
	 */
	@Test
	public void testStudent6() {
		Registrar registrar = new Registrar(5);

		registrar.addNewCourse("CMSC", 132, 10);
		registrar.addToCourse("CMSC", 132, "Abe", "Apple");

		assertTrue(registrar.isInCourse("CMSC", 132, "Abe", "Apple"));
		assertEquals(1, registrar.howManyCoursesTaking("Abe", "Apple"));

		registrar.dropCourse("CMSC", 132, "Abe", "Apple");

		assertFalse(registrar.isInCourse("CSMC", 132, "Abe", "Apple"));
		assertEquals(0, registrar.howManyCoursesTaking("Abe", "Apple"));
	}

	/*
	 * Tests that if a student uses cancelRegistration(), the course size goes
	 * down by 1, and another student can now enroll in the course
	 */
	@Test
	public void testStudent7() {
		Registrar registrar = new Registrar(5);

		registrar.addNewCourse("CMSC", 132, 3);

		registrar.addToCourse("CMSC", 132, "Abe", "Apple");
		registrar.addToCourse("CMSC", 132, "Ben", "Benny");
		registrar.addToCourse("CMSC", 132, "Carol", "Canopie");

		assertEquals(3, registrar.numStudentsInCourse("CMSC", 132));
		assertFalse(registrar.addToCourse("CMSC", 132, "Daniel", "Ducky"));

		registrar.cancelRegistration("Abe", "Apple");
		assertFalse(registrar.isInCourse("CMSC", 132, "Abe", "Apple"));
		assertTrue(registrar.addToCourse("CMSC", 132, "Daniel", "Ducky"));
	}

	/*
	 * Tests that numStudentsInCourse() returns appropriate values - -1 when the
	 * course doesn't exist in the registrar max capacity when a class is full
	 */
	@Test
	public void testStudent8() {
		Registrar registrar = new Registrar(5);

		registrar.addNewCourse("CMSC", 132, 3);

		registrar.addToCourse("CMSC", 132, "Abe", "Apple");
		registrar.addToCourse("CMSC", 132, "Ben", "Benny");
		registrar.addToCourse("CMSC", 132, "Carol", "Canopie");
		registrar.addToCourse("CMSC", 132, "Daniel", "Ducky");

		assertEquals(3, registrar.numStudentsInCourse("CMSC", 132));
		assertEquals(-1, registrar.numStudentsInCourse("CMSC", 216));
	}

	// Tests that all methods throw IllegalArgumentExceptions when appropriate
	@Test
	public void testStudent9() {
		Registrar registrar = new Registrar(20);
		try {
			registrar.addNewCourse("", 5, 5);
			// if we reach here- meaning if the expected exception was not
			// thrown-
			// the test should fail
			fail();
		} catch (IllegalArgumentException e) {
			// if we get here the expected exception was thrown, so continue to
			// the next thing we want to test
		}

		try {
			registrar.addNewCourse("CMSC", 0, 5);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	// Tests that doRegistration doesn't do anything when reading an empty file
	@Test
	public void testStudent10() {
		Registrar registrar = new Registrar(5);
		registrar.addNewCourse("CMSC", 132, 3);

		registrar.addToCourse("CMSC", 132, "Abe", "Apple");
		registrar.addToCourse("CMSC", 132, "Ben", "Benny");
		registrar.addToCourse("CMSC", 132, "Carol", "Canopie");
		registrar.addToCourse("CMSC", 132, "Daniel", "Ducky");

		assertEquals(3, registrar.numStudentsInCourse("CMSC", 132));
		assertEquals(-1, registrar.numStudentsInCourse("CMSC", 216));
		registrar.doRegistrations(Arrays.asList("registrationdata-student10"));
		assertEquals(3, registrar.numStudentsInCourse("CMSC", 132));
		assertEquals(-1, registrar.numStudentsInCourse("CMSC", 216));
	}

	// Tests that doRegistration doesn't do anything when the first keyword in
	// the file is not addcourse or addregistration
	@Test
	public void testStudent11() {
		Registrar registrar = new Registrar(5);
		registrar.addNewCourse("CMSC", 132, 3);

		registrar.addToCourse("CMSC", 132, "Abe", "Apple");
		registrar.addToCourse("CMSC", 132, "Ben", "Benny");
		registrar.addToCourse("CMSC", 132, "Carol", "Canopie");
		registrar.addToCourse("CMSC", 132, "Daniel", "Ducky");

		assertEquals(3, registrar.numStudentsInCourse("CMSC", 132));
		assertEquals(-1, registrar.numStudentsInCourse("CMSC", 216));
		registrar.doRegistrations(Arrays.asList("registrationdata-student10"));
		assertEquals(3, registrar.numStudentsInCourse("CMSC", 132));
		assertEquals(-1, registrar.numStudentsInCourse("CMSC", 216));
	}

	// Tests that if there is a null object in the collection then
	// doRegistrations keeps going
	@Test
	public void testStudent12() {
		Registrar registrar = new Registrar(5);
		registrar.addNewCourse("CMSC", 132, 3);

		registrar.addToCourse("CMSC", 132, "Abe", "Apple");
		registrar.addToCourse("CMSC", 132, "Ben", "Benny");
		registrar.addToCourse("CMSC", 132, "Carol", "Canopie");
		registrar.addToCourse("CMSC", 132, "Daniel", "Ducky");

		assertEquals(3, registrar.numStudentsInCourse("CMSC", 132));
		assertEquals(-1, registrar.numStudentsInCourse("CMSC", 216));
		registrar.doRegistrations(
				Arrays.asList("registrationdata-student10", null));
		assertEquals(3, registrar.numStudentsInCourse("CMSC", 132));
		assertEquals(-1, registrar.numStudentsInCourse("CMSC", 216));
	}
}
