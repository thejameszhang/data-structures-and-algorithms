package tests;

import org.junit.*;

import businessOffice.Account;

import static org.junit.Assert.*;

public class StudentTests {

	// Tests calling employeeLimit() for companies with paid plans
	@Test
	public void testPublic1() {
		assertEquals(Integer.MAX_VALUE, TestData.exampleAccount2().
				employeeLimit());
		
		assertEquals(Integer.MAX_VALUE, TestData.exampleAccount4().
				employeeLimit());
	}

	// Tests trying to hire employees with invalid names
	@Test
	public void testPublic2() {
		Account company = TestData.exampleAccount2();
		
		assertEquals(6, company.numEmployees());
		assertEquals(0.0, company.getPayroll(), 0.001);
		assertEquals(60.0, company.billAmount(), 0.001);
		
		assertFalse(company.hireSalariedWorker(null, 432.0));
		assertFalse(company.hireSalariedWorker("", 23454.0));
		assertFalse(company.hireCommissionedWorker(null, 24.0));
		assertFalse(company.hireCommissionedWorker("", 5.0));
		
		assertEquals(6, company.numEmployees());
		assertEquals(0.0, company.getPayroll(), 0.001);
		assertEquals(60.0, company.billAmount(), 0.001);
	}

	// Tests trying to hire employees with invalid commissionRates and
	// yearlySalary values
	@Test
	public void testPublic3() {
		Account company = TestData.exampleAccount1();

		assertFalse(company.hireSalariedWorker("Jackson Jane", -23.0));
		assertFalse(company.hireCommissionedWorker("George Gee", -5.0));
		assertFalse(company.hireCommissionedWorker("George Gee", 127.0));
	}

	// Tests that workHours() returns false when a salaried employee tries
	// add more than 80 hours in a pay period and true when a commissioned
	// employee works more than 82 hours
	@Test
	public void testPublic4() {
		Account company = TestData.exampleAccount4();

		assertTrue(company.workHours("Holly Dolphin", 40));
		assertFalse(company.workHours("Holly Dolphin", 42));
		
		assertTrue(company.workHours("Freddy Frog", 82));
	}

	// Tests that workHours() returns false when name is null, empty string, 
	// numHours is not strictly greater than 0
	@Test
	public void testPublic5() {
		Account company = TestData.exampleAccount3();
		
		assertFalse(company.workHours(null, 23));
		assertFalse(company.workHours("", 23));
		assertFalse(company.workHours("Lizzie Lizard", 0));
		assertFalse(company.workHours("Paul Platypus", 0));
		assertFalse(company.workHours("Lizzie Lizard", -1));
		assertFalse(company.workHours("Paul Platypus", -12));
	
	}

	// Tests that workHours() returns false if the name is null, empty string,
	// no employee exists, or numHours is negative
	@Test
	public void testPublic6() {
		Account company = TestData.exampleAccount1();

		assertFalse(company.workHours(null, 25));
		assertFalse(company.workHours("", 67));
		assertFalse(company.workHours("Random Guy", 72));
		assertFalse(company.workHours("Dolly Dolphin", -7));
	}

	// Tests that numHours() works with invalid name calls
	@Test
	public void testPublic7() {
		Account company = TestData.exampleAccount4();

		assertEquals(-1, company.numHours(""));
		assertEquals(-1, company.numHours(null));
		assertEquals(-1, company.numHours("Random Guy"));

		assertTrue(company.workHours("Freddy Frog", 5));
		assertEquals(5, company.numHours("Freddy Frog"));
	}

	// Tests that makeSales() returns false if name is null, empty string,
	// doesn't exist, or the sale is a negative value
	@Test
	public void testPublic8() {
		Account company = TestData.exampleAccount3();

		assertFalse(company.makeSale(null, 25.0));
		assertFalse(company.makeSale("", 67.2));
		assertFalse(company.makeSale("Random Guy", 72.5));
		assertFalse(company.makeSale("Chippy Chipmunk", -7.1));
		
		assertEquals(0.0, company.getPayAmount("Chippy Chipmunk"), 0.001);
		assertTrue(company.makeSale("Chippy Chipmunk", 200.0));
		assertEquals(200.0, company.amtSalesMade("Chippy Chipmunk"), 0.001);
		assertEquals(20.0, company.getPayAmount("Chippy Chipmunk"), 0.001);
	}

	// Tests that amtSalesMade() returns -1.0 when invalid names are called
	@Test
	public void testPublic9() {
		Account company = TestData.exampleAccount3();

		assertEquals(-1.0, company.amtSalesMade(null), 0.001);
		assertEquals(-1.0, company.amtSalesMade(""), 0.001);
		assertEquals(-1.0, company.amtSalesMade("Random Guy"), 0.001);
	}

	// Tests that amtSalesMade() returns 0.0 when called for a salaried worker
	@Test
	public void testPublic10() {
		Account company = TestData.exampleAccount3();

		assertTrue(company.makeSale("Lizzie Lizard", 12.0));
		assertEquals(0.0, company.amtSalesMade("Lizzie Lizard"), 0.001);
	}

	// Tests that getPayAmount() works, and then after a new pay period
	// starts, their pay amount resets to 0
	@Test
	public void testPublic11() {
		Account company = TestData.exampleAccount3();

		assertEquals(1880.769, company.getPayAmount("Lizzie Lizard"), 0.001);
		assertEquals(0.0, company.billAmount(), 0.001);
		assertTrue(company.makeSale("Chippy Chipmunk", 100.0));
		assertEquals(10.0, company.getPayAmount("Chippy Chipmunk"), 0.001);
		company.newPayPeriod();
		assertEquals(0.0, company.getPayAmount("Chippy Chipmunk"), 0.001);
		assertEquals(0.0, company.billAmount(), 0.001);
	}
	
	//Tests isEmployee() returns false when invalid names are passed in
	@Test
	public void testPublic12() {
		Account company = TestData.exampleAccount1();
		
		assertTrue(company.isEmployee("Dolly Dolphin"));
		assertFalse(company.isEmployee(null));
		assertFalse(company.isEmployee(""));
		assertFalse(company.isEmployee("Jane Jackson"));
	}
	
	//Tests getPayAmount() returns -1.0 when called with invalid names
	@Test
	public void testPublic13() {
		Account company = TestData.exampleAccount1();
		
		assertEquals(-1.0, company.getPayAmount(null), 0.001);
		assertEquals(-1.0, company.getPayAmount(""), 0.001);
		assertEquals(-1.0, company.getPayAmount("Random Robert"), 0.001);
	}
	
	//Tests that my helper function findEmployeeByName() works and returns -1
	//when no such employee is found
	@Test
	public void testPublic14() {
		Account company = TestData.exampleAccount3();
		
		assertEquals(-1, company.findEmployeeByName("James Zhang"));
		assertEquals(1, company.findEmployeeByName("Paul Platypus"));
		assertEquals(9, company.findEmployeeByName("Wally Walrus"));
	}
	
	public void testPublic15() {
		Account company = TestData.exampleAccount2();
		
	
		assertEquals(10, company.numEmployees());
		
		
		
		
	}
	
}
