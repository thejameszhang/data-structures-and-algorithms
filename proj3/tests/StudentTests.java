/**
 * @author: James Zhang
 * @directoryID: jzhang72
 * @uid: 118843940
 * @discussionNumber: 0107
 * I pledge on my honor that I have not given or received any unauthorized 
 * assistance on this assignment.
 */

package tests;

import org.junit.*;

import listVersions.NormalLinkedList;

import static org.junit.Assert.*;

public class StudentTests {

	/**
	 * Tests calling the rest of the methods with null parameters
	 */
	@Test
	public void testStudent1() {
		NormalLinkedList<Integer> intList = TestData.list1();

		try {
			intList.addNewEltToList(null);
			fail();
		} catch (IllegalArgumentException iae) {
			// if we get here the expected exception was thrown, so continue to
			// the next thing we want to test
		}

		try {
			intList.removeValuesBetween(null, 5);
			fail();
		} catch (IllegalArgumentException iae) {
			// if we get here the test should pass
		}

		try {
			intList.removeValuesBetween(2, null);
			fail();
		} catch (IllegalArgumentException iae) {
			// if we get here the test should pass
		}

		try {
			intList.compareTo(null);
			fail();
		} catch (IllegalArgumentException iae) {
			// if we get here the test should pass
		}
	}

	/**
	 * Tests that if you add a node to an empty list, then the length becomes 1
	 */
	@Test
	public void testStudent2() {
		NormalLinkedList<Integer> intList = TestData.list1();

		intList.clear();
		assertEquals(0, intList.length());

		intList.addNewEltToList(2);

		assertEquals(1, intList.length());
		assertEquals("2", intList.toString());
	}

	/**
	 * Tests that the length of a linked list doesn't change after an exception
	 * is thrown in the addNewEltToLIst() method
	 */
	@Test
	public void testStudent3() {
		NormalLinkedList<Integer> intList = TestData.list1();

		assertEquals(18, intList.length());
		try {
			intList.addNewEltToList(null);
			fail();
		} catch (IllegalArgumentException iae) {
			// if we get here the expected exception was thrown, so continue to
			// the next thing we want to test
		}
		assertEquals(18, intList.length());
	}

	/**
	 * Tests that the occurrencesOfValue() method returns 0 when called on an
	 * empty list
	 */
	@Test
	public void testStudent4() {
		NormalLinkedList<Character> charList = TestData.list2();
		NormalLinkedList<String> strList = TestData.list3();

		charList.clear();
		strList.clear();

		assertEquals(0, charList.occurrencesOfValue('s'));
		assertEquals(0, strList.occurrencesOfValue("s"));
	}

	/**
	 * Tests that occurrencesOfValue() checks the first and last nodes of the
	 * linked list
	 */
	@Test
	public void testStudent5() {
		NormalLinkedList<Character> charList = TestData.list2();

		// 'i' is at the beginning of the linked list
		assertEquals(1, charList.occurrencesOfValue('i'));

		// 's' is at the end of the linked list
		assertEquals(1, charList.occurrencesOfValue('s'));
	}

	/**
	 * Tests that indexOfValue() checks the first and last nodes of the linked
	 * list
	 */
	@Test
	public void testStudent6() {
		NormalLinkedList<Character> charList = TestData.list2();

		// 'i' is at the beginning of the linked list
		assertEquals(0, charList.indexOfValue('i'));

		// 's' is at the end of the linked list
		assertEquals(13, charList.indexOfValue('s'));
	}

	/**
	 * Tests that valueLocatedAtIndex() checks the first and last nodes of the
	 * linked list
	 */
	@Test
	public void testStudent7() {
		NormalLinkedList<String> strList = TestData.list3();

		// "pig" is at index 0
		assertEquals("pig", strList.valueLocatedAtIndex(0));

		// "cow" is at index 9
		assertEquals("cow", strList.valueLocatedAtIndex(9));
	}

	/**
	 * Tests that removeValuesBetween() works as correctly
	 */
	@Test
	public void testStudent8() {
		NormalLinkedList<Integer> intList = TestData.list1();
		intList.clear();

		intList.addNewEltToList(12);
		intList.addNewEltToList(11);
		intList.addNewEltToList(15);
		intList.addNewEltToList(13);
		intList.addNewEltToList(14);
		intList.addNewEltToList(10);

		intList.removeValuesBetween(11, 13);

		assertEquals(3, intList.length());
		assertEquals("12 14 10", intList.toString());
	}

	/**
	 * Tests that removeValuesBetween() works when a single node is removed from
	 * the middle of a linked list
	 */
	@Test
	public void testStudent9() {
		NormalLinkedList<Integer> intList = TestData.list1();
		intList.clear();

		intList.addNewEltToList(12);
		intList.addNewEltToList(11);
		intList.addNewEltToList(15);
		intList.addNewEltToList(13);
		intList.addNewEltToList(14);
		intList.addNewEltToList(10);

		// remove 13, an element in the middle of the list
		intList.removeValuesBetween(13, 13);

		// check that this info is still correct
		assertEquals(5, intList.length());
		assertEquals("12 11 15 14 10", intList.toString());
	}

	/**
	 * Tests that removeValuesBetween() works when a single node is removed from
	 * the end of a linked list
	 */
	@Test
	public void testStudent10() {
		NormalLinkedList<Integer> intList = TestData.list1();
		intList.clear();

		intList.addNewEltToList(12);
		intList.addNewEltToList(11);
		intList.addNewEltToList(15);
		intList.addNewEltToList(13);
		intList.addNewEltToList(14);
		intList.addNewEltToList(10);

		// remove 10, an element in the end of the list
		intList.removeValuesBetween(10, 10);

		// check that this info is still correct
		assertEquals(5, intList.length());
		assertEquals("12 11 15 13 14", intList.toString());
	}

	/**
	 * Tests that removeValuesBetween() works when a single node is removed from
	 * the beginning of a linked list
	 */
	@Test
	public void testStudent11() {
		NormalLinkedList<Integer> intList = TestData.list1();
		intList.clear();

		intList.addNewEltToList(12);
		intList.addNewEltToList(11);
		intList.addNewEltToList(15);
		intList.addNewEltToList(13);
		intList.addNewEltToList(14);
		intList.addNewEltToList(10);

		// remove 10, an element in the end of the list
		intList.removeValuesBetween(12, 12);

		// check that this info is still correct
		assertEquals(5, intList.length());
		assertEquals("11 15 13 14 10", intList.toString());
	}

	/**
	 * Tests that removeValuesBetween() works when a some nodes are removed from
	 * a linked list including the first node
	 */
	@Test
	public void testStudent12() {
		NormalLinkedList<Integer> intList = TestData.list1();
		intList.clear();

		intList.addNewEltToList(12);
		intList.addNewEltToList(11);
		intList.addNewEltToList(15);
		intList.addNewEltToList(13);
		intList.addNewEltToList(14);
		intList.addNewEltToList(10);

		// remove 10, an element in the end of the list
		intList.removeValuesBetween(12, 15);

		// check that this info is still correct
		assertEquals(3, intList.length());
		assertEquals("13 14 10", intList.toString());
	}

	/**
	 * Tests that removeValuesBetween() works when a some nodes are removed from
	 * a linked list including the last node
	 */
	@Test
	public void testStudent13() {
		NormalLinkedList<Integer> intList = TestData.list1();
		intList.clear();

		intList.addNewEltToList(12);
		intList.addNewEltToList(11);
		intList.addNewEltToList(15);
		intList.addNewEltToList(13);
		intList.addNewEltToList(14);
		intList.addNewEltToList(10);

		// remove 10, an element in the end of the list
		intList.removeValuesBetween(13, 10);

		// check that this info is still correct
		assertEquals(3, intList.length());
		assertEquals("12 11 15", intList.toString());
	}

	/**
	 * Tests that removeValuesBetween() works when we try to remove all of the
	 * nodes
	 */
	@Test
	public void testStudent14() {
		NormalLinkedList<Integer> intList = TestData.list1();
		intList.clear();

		intList.addNewEltToList(12);
		intList.addNewEltToList(11);
		intList.addNewEltToList(15);
		intList.addNewEltToList(13);
		intList.addNewEltToList(14);
		intList.addNewEltToList(10);

		// remove 10, an element in the end of the list
		intList.removeValuesBetween(12, 10);

		// check that this info is still correct
		assertEquals(0, intList.length());
		assertEquals("", intList.toString());
	}

	/**
	 * Cases 1 and 3 of compareTo() were already tested in the public tests
	 * Tests Case 2 of compareTo() - when the corresponding element of the
	 * current object list is less than the element of the other object list
	 */
	@Test
	public void testStudent15() {
		NormalLinkedList<Integer> intList = TestData.list1();
		intList.clear();

		intList.addNewEltToList(12);
		intList.addNewEltToList(11);
		intList.addNewEltToList(1);
		intList.addNewEltToList(13);
		intList.addNewEltToList(14);
		intList.addNewEltToList(10);

		NormalLinkedList<Integer> other = TestData.list1();
		other.clear();

		other.addNewEltToList(12);
		other.addNewEltToList(11);
		other.addNewEltToList(15);
		other.addNewEltToList(13);
		other.addNewEltToList(14);
		other.addNewEltToList(10);

		assertEquals(-1, intList.compareTo(other));
	}

	/**
	 * Tests Case 4 of compareTo() - when the lists are the same up until a
	 * certain point, but the current object linked list is shorter
	 */
	@Test
	public void testStudent16() {
		NormalLinkedList<Integer> intList = TestData.list1();
		intList.clear();

		intList.addNewEltToList(12);
		intList.addNewEltToList(11);
		intList.addNewEltToList(15);
		intList.addNewEltToList(13);
		intList.addNewEltToList(14);

		NormalLinkedList<Integer> other = TestData.list1();
		other.clear();

		other.addNewEltToList(12);
		other.addNewEltToList(11);
		other.addNewEltToList(15);
		other.addNewEltToList(13);
		other.addNewEltToList(14);
		other.addNewEltToList(10);

		assertEquals(-2, intList.compareTo(other));
	}

	/**
	 * Tests Case 5 of compareTo() - when the lists are the same up until a
	 * certain point, but the other linked list is shorter
	 */
	@Test
	public void testStudent17() {
		NormalLinkedList<Integer> intList = TestData.list1();
		intList.clear();

		intList.addNewEltToList(12);
		intList.addNewEltToList(11);
		intList.addNewEltToList(15);
		intList.addNewEltToList(13);
		intList.addNewEltToList(14);

		NormalLinkedList<Integer> other = TestData.list1();
		other.clear();

		other.addNewEltToList(12);
		other.addNewEltToList(11);
		other.addNewEltToList(15);
		other.addNewEltToList(13);

		assertEquals(2, intList.compareTo(other));
	}

	/**
	 * Tests that addNewEltToList works properly for an InorderLinkedList
	 */
	@Test
	public void testStudent18() {
		NormalLinkedList<Integer> intList = TestData.list4();
		intList.clear();
		intList.addNewEltToList(1);
		intList.addNewEltToList(2);
		intList.addNewEltToList(3);
		intList.addNewEltToList(10);
		intList.addNewEltToList(7);

		assertEquals(5, intList.length());
		assertEquals("1 2 3 7 10", intList.toString());

		intList.addNewEltToList(-1);
		assertEquals(6, intList.length());
		assertEquals("-1 1 2 3 7 10", intList.toString());

		intList.addNewEltToList(5);
		assertEquals(7, intList.length());
		assertEquals("-1 1 2 3 5 7 10", intList.toString());

		intList.addNewEltToList(102);
		assertEquals(8, intList.length());
		assertEquals("-1 1 2 3 5 7 10 102", intList.toString());
	}

	/**
	 * Tests calling methods with null parameters for an InorderLinkedList
	 */
	@Test
	public void testStudent19() {
		NormalLinkedList<Integer> intList = TestData.list4();

		try {
			intList.addNewEltToList(null);
			fail();
		} catch (IllegalArgumentException iae) {
			// if we get here the expected exception was thrown, so continue to
			// the next thing we want to test
		}

		try {
			intList.removeValuesBetween(null, 5);
			fail();
		} catch (IllegalArgumentException iae) {
			// if we get here the test should pass
		}

		try {
			intList.removeValuesBetween(2, null);
			fail();
		} catch (IllegalArgumentException iae) {
			// if we get here the test should pass
		}

		try {
			intList.compareTo(null);
			fail();
		} catch (IllegalArgumentException iae) {
			// if we get here the test should pass
		}

		try {
			intList.occurrencesOfValue(null);
			fail();
		} catch (IllegalArgumentException iae) {
			// if we get here the test should pass
		}

		try {
			intList.indexOfValue(null);
			fail();
		} catch (IllegalArgumentException iae) {
			// if we get here the test should pass
		}
	}

	/**
	 * Tests occurrencesOfValue() for an InorderList
	 */
	@Test
	public void testStudent20() {
		NormalLinkedList<Integer> intList = TestData.list4();

		assertEquals(0, intList.occurrencesOfValue(12));
		assertEquals(1, intList.occurrencesOfValue(9));
		assertEquals(2, intList.occurrencesOfValue(96));
		assertEquals(4, intList.occurrencesOfValue(92));

		intList.clear();
		assertEquals(0, intList.occurrencesOfValue(9));
		assertEquals(0, intList.length());
	}

	/**
	 * Tests indexOfValue() for an InorderList
	 */
	@Test
	public void testStudent21() {
		NormalLinkedList<Integer> intList = TestData.list4();

		assertEquals(-1, intList.indexOfValue(12));
		assertEquals(0, intList.indexOfValue(9));
		assertEquals(16, intList.indexOfValue(96));
		assertEquals(12, intList.indexOfValue(92));
	}

	/**
	 * Tests a lot of methods at once for an InorderLinkedList
	 */
	@Test
	public void testStudent22() {
		NormalLinkedList<Integer> intList = TestData.list4();

		intList.removeValuesBetween(33, 89);
		assertEquals("9 10 24 92 92 92 92 96 96", intList.toString());

		// 89 is an invalid toValue, since it's not in the linked list anymore
		intList.removeValuesBetween(9, 89);

		// the toString should stay the same
		assertEquals("9 10 24 92 92 92 92 96 96", intList.toString());

		intList.removeValuesBetween(9, 9);
		intList.removeValuesBetween(96, 96);
		intList.removeValuesBetween(96, 96);

		assertEquals("10 24 92 92 92 92", intList.toString());
		assertEquals(6, intList.length());

		assertEquals(0, intList.indexOfValue(10));
		assertEquals(4, intList.occurrencesOfValue(92));
		assertEquals((Integer) 24, intList.valueLocatedAtIndex(1));

		intList.addNewEltToList(191);
		assertEquals(7, intList.length());
		assertEquals("10 24 92 92 92 92 191", intList.toString());
	}
}