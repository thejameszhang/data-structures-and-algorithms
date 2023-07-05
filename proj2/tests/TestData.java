package tests;

// (c) Larry Herman, 2022.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

/* Although the project grading policies say not to use this form of import
 * (to use explicit imports instead), we have to use it here, because we
 * don't know what classes you will write in the businessOffice package.
 */
import businessOffice.*;

/* This class contains utility methods that create and return example
 * Account objects that the public (and secret) tests can use, to reduce the
 * amount of code needed in different tests to create objects to test the
 * methods with.
 *
 * Your student tests themselves must be your own individual work- you can
 * use ideas from the public the public tests, but you cannot just copy the
 * public tests to create your student tests.  However, you CAN use the
 * methods in THIS class in writing your own student tests, without any
 * restrictions, meaning your student tests can call any of the methods
 * below to create Account objects for testing purposes.  However, don't
 * modify this TestData class, because our version is going to be used on
 * the submit server.  (If you want to have a modified version of any of the
 * methods here note that you can write your own helper methods in your
 * StudentTests class, and you can also add your own classes to the tests
 * package, so instead of changing the methods here, you can make a copy of
 * them in one of these two places and modify that copy.)
 */

public class TestData {

	// Returns a Account with a free account that only has salaried employees,
	// who haven't (yet) worked any hours or made any sales.
	public static Account exampleAccount1() {
		Account company = BusinessOffice.createAccount("Gooble", 5);

		company.hireSalariedWorker("Dolly Dolphin", 51012.0);
		company.hireSalariedWorker("Freddy Frog", 68900.0);
		company.hireSalariedWorker("Geri Giraffe", 49946.0);
		company.hireSalariedWorker("Kourtney Koala", 59410.0);

		return company;
	}

	// Returns a Account with a paid account that only has commissioned
	// employees, who haven't (yet) worked any hours or made any sales.
	public static Account exampleAccount2() {
		Account company = BusinessOffice.createAccount("Microsloth");

		company.hireCommissionedWorker("Paul Platypus", 15.0);
		company.hireCommissionedWorker("Steve Starfish", 15.25);
		company.hireCommissionedWorker("Timmy Termite", 16.0);
		company.hireCommissionedWorker("Jackie Jaguar", 15.75);
		company.hireCommissionedWorker("Sally Salamander", 16.5);
		company.hireCommissionedWorker("Chippy Chipmunk", 10.0);

		return company;
	}

	// Returns a Account with a free account that has a mix of salaried and
	// commissioned employees, who haven't (yet) worked any hours or made any
	// sales.
	public static Account exampleAccount3() {
		Account company = BusinessOffice.createAccount("Nvidiot", 50);

		company.hireSalariedWorker("Lizzie Lizard", 48900.0);
		company.hireCommissionedWorker("Paul Platypus", 5.0);
		company.hireCommissionedWorker("Chippy Chipmunk", 10.0);
		company.hireSalariedWorker("Kourtney Koala", 59425.0);
		company.hireCommissionedWorker("Jackie Jaguar", 7.5);
		company.hireCommissionedWorker("Steve Starfish", 8.0);
		company.hireSalariedWorker("Ginny Giraffe", 79950.0);
		company.hireCommissionedWorker("Sally Salamander", 6.5);
		company.hireCommissionedWorker("Timmy Termite", 4.0);
		company.hireSalariedWorker("Wally Walrus", 61000.0);

		return company;
	}

	// Returns a Account with a paid account that has a mix of salaried and
	// commissioned employees, who haven't (yet) worked any hours or made any
	// sales.
	public static Account exampleAccount4() {
		Account company = BusinessOffice.createAccount("Auricle");

		company.hireSalariedWorker("Holly Dolphin", 81016.0);
		company.hireCommissionedWorker("Freddy Frog", 5.0);
		company.hireSalariedWorker("Geri Giraffe", 49959.0);
		company.hireCommissionedWorker("Kourtney Koala", 6.0);
		company.hireSalariedWorker("Ryan Lion", 79950.0);
		company.hireCommissionedWorker("Bruce Moose", 7.5);

		return company;
	}

}
