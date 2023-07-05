package tests;

// (c) Larry Herman, 2022.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

/* Some tests use a form of assertEquals() that is used to compare two
 * floating-point (real) numbers, which has three arguments:
 * assertEquals(double1, double2, delta).  It will say that the two doubles
 * are equal if their values are within delta of each other.  For instance,
 * a call like assertEquals(2.5, 2.501, 0.01) will be true, while
 * assertEquals(2.5, 2.55, 0.01) will fail.  Comparing real numbers this way
 * is needed due to the imprecision involved with doing arithmetic with
 * them.
 */

/* Although the project grading policy handout says not to use the form of
 * import using '*' (you should use explicit imports instead), we have to
 * use it in the next line here, because we don't know what classes
 * different students will write in the employeeBusinessOffice package, so we can't
 * explicitly import them.
 */
import businessOffice.*;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

  @Test public void testPublic1() {
    DatarrayList list= new DatarrayList(5);
    String[] words= {"banana", "mango", "grape", "pear", "orange", "apple",
                     "kiwi"};
    int i;

    for (String s : words)
      list.add(s);

    assertEquals(7, list.getSize());
    assertEquals(10, list.getCapacity());

    for (i= 0; i < list.getSize(); i++)
      // note casting the Object reference returned by get() to the actual
      // type of the object being returned, which we know here is String
      assertEquals(words[i], (String) list.get(i));
  }

  // Just tests creating some Account objects, of different types, and
  // calling getAccountName() on them.
  @Test public void testPublic2() {
    Account company1= BusinessOffice.createAccount("Gooble");
    Account company2= BusinessOffice.createAccount("InstaSnap", 10);

    assertEquals("Gooble", company1.getAccountName());
    assertEquals("InstaSnap", company2.getAccountName());
  }

  // Tests hiring some employees for a company and calling numEmployees().
  @Test public void testPublic3() {
    assertEquals(4, TestData.exampleAccount1().numEmployees());
  }

  // Tests calling employeeLimit() for companies with free accounts.
  @Test public void testPublic4() {
    Account company1= TestData.exampleAccount1();
    Account company2= TestData.exampleAccount3();

    assertEquals(5, company1.employeeLimit());
    assertEquals(50, company2.employeeLimit());
  }

  // Tests trying to try to hire employees who have the same names as
  // existing employees, which should fail.
  @Test public void testPublic5() {
    Account company= TestData.exampleAccount3();

    assertFalse(company.hireSalariedWorker("Kourtney Koala", 89530.0));
    assertFalse(company.hireCommissionedWorker("Sally Salamander", 16.0));
    assertEquals(10, company.numEmployees());
  }

  // Tests trying to hire more employees than a free account company's
  // capacity.
  @Test public void testPublic6() {
    Account company= TestData.exampleAccount1();

    assertTrue(company.hireSalariedWorker("Leanne Lemur", 14.65));
    assertFalse(company.hireSalariedWorker("Sheila Sheep", 34500.0));
    assertEquals(5, company.employeeLimit());
    assertEquals(5, company.numEmployees());
  }

  // Tests the basic operation of workHours() and numHours().
  @Test public void testPublic7() {
    Account company= TestData.exampleAccount4();

    assertTrue(company.workHours("Holly Dolphin", 8));
    assertTrue(company.workHours("Holly Dolphin", 9));
    assertTrue(company.workHours("Holly Dolphin", 7));
    assertTrue(company.workHours("Holly Dolphin", 9));
    assertTrue(company.workHours("Geri Giraffe", 11));
    assertTrue(company.workHours("Geri Giraffe", 10));
    assertTrue(company.workHours("Geri Giraffe", 9));
    assertTrue(company.workHours("Ryan Lion", 8));

    assertEquals(33, company.numHours("Holly Dolphin"));
    assertEquals(0, company.numHours("Freddy Frog"));
    assertEquals(30, company.numHours("Geri Giraffe"));
    assertEquals(0, company.numHours("Kourtney Koala"));
    assertEquals(8, company.numHours("Ryan Lion"));
    assertEquals(0, company.numHours("Bruce Moose"));
  }

  // Tests the basic operation of getPayAmount() for a salaried employee.
  @Test public void testPublic8() {
    Account company= TestData.exampleAccount4();

    assertEquals(3116.0, company.getPayAmount("Holly Dolphin"), 0.001);
  }

  // Tests the basic operation of getPayAmount() for commissioned employees.
  @Test public void testPublic9() {
    Account company= TestData.exampleAccount4();

    company.makeSale("Freddy Frog", 15000.0);
    company.makeSale("Freddy Frog", 30000.0);
    company.makeSale("Kourtney Koala", 27000.0);
    company.makeSale("Kourtney Koala", 32000.0);

    assertEquals(2250.0, company.getPayAmount("Freddy Frog"), 0.001);
    assertEquals(3540.0, company.getPayAmount("Kourtney Koala"), 0.001);
    // Bruce Moose did not make any sales
    assertEquals(0.0, company.getPayAmount("Bruce Moose"), 0.001);
  }

  // Tests nonexistent employees trying to work hours and make sales.
  @Test public void testPublic10() {
    Account company= TestData.exampleAccount4();

    assertFalse(company.workHours("Antonio Antelope", 1));
    assertFalse(company.makeSale("Quinn Quokka", 1000.0));
  }

  // Tests trying to call workHours() with a negative number of hours.
  @Test public void testPublic11() {
    Account company= TestData.exampleAccount3();

    assertTrue(company.workHours("Lizzie Lizard", 10));
    assertTrue(company.workHours("Timmy Termite", 11));
    assertFalse(company.workHours("Lizzie Lizard", -2));
    assertFalse(company.workHours("Timmy Termite", -2));
    assertEquals(10, company.numHours("Lizzie Lizard"));
    assertEquals(11, company.numHours("Timmy Termite"));
  }

  // Tests that employees of both types are able to work more than 40 hours
  // in a pay period.
  @Test public void testPublic12() {
    Account company= TestData.exampleAccount3();
    int i;

    for (i= 1; i <= 12; i++) {
		assertTrue(company.workHours("Kourtney Koala", 4));
	      assertTrue(company.workHours("Jackie Jaguar", 4));
    }

    assertEquals(48, company.numHours("Kourtney Koala"));
    assertEquals(48, company.numHours("Jackie Jaguar"));
  }

  // Tests trying to call makeSale() with a negative sale amount.
  @Test public void testPublic13() {
    Account company= TestData.exampleAccount2();

    assertTrue(company.makeSale("Chippy Chipmunk", 1000.0));
    assertFalse(company.makeSale("Chippy Chipmunk", -200.0));
    assertTrue(company.makeSale("Chippy Chipmunk", 2000.0));
    assertEquals(3000.0, company.amtSalesMade("Chippy Chipmunk"), 0.001);
  }

  // Tests calling getPayroll().
  @Test public void testPublic14() {
    Account company= TestData.exampleAccount1();

    assertEquals(8818.0, company.getPayroll(), 0.001);
  }

  // Tests calling billAmount() on companies that have free accounts.
  @Test public void testPublic15() {
    Account company1= BusinessOffice.createAccount("Applesauce", 10);
    Account company2= TestData.exampleAccount1();

    assertEquals(0.0, company1.billAmount(), 0.001);
    assertEquals(0.0, company2.billAmount(), 0.001);
  }

  // Tests calling billAmount() on companies that have paid accounts.
  @Test public void testPublic16() {
    Account company1= BusinessOffice.createAccount("Instaounce");
    Account company2= BusinessOffice.createAccount("Sungsam");

    company2.hireSalariedWorker("Dolly Dolphin", 51012.0);
    company2.hireSalariedWorker("Freddy Frog", 68900.0);
    company2.hireSalariedWorker("Geri Giraffe", 49946.0);
    company2.hireSalariedWorker("Kourtney Koala", 59410.0);

    assertEquals(0.0, company1.billAmount(), 0.001);
    assertEquals(40.0, company2.billAmount(), 0.001);
  }

}
