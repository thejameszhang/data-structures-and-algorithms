package tests;

// (c) Larry Herman, 2022.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import listVersions.NormalLinkedList;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

	// Tests checking the length of a NormalLinkedList object that contains
	// some values.
	@Test
	public void testPublic1() {
		assertEquals(18, TestData.list1().length());
	}

	// Tests calling toString() on a NormalLinkedList object, after adding
	// some values.
	@Test
	public void testPublic2() {
		assertEquals("52 66 10 92 81 89 64 33 34 92 9 58 96 92 92 35 24 96",
				TestData.list1().toString());
	}

	// Tests whether clear() properly removes all values from a
	// NormalLinkedList object.
	@Test
	public void testPublic3() {
		NormalLinkedList<Integer> intList = TestData.list1();

		// clear the list
		intList.clear();
		assertEquals(0, intList.length());

		// now add some new values
		intList.addNewEltToList(1);
		intList.addNewEltToList(3);
		intList.addNewEltToList(2);

		// now check the current contents
		assertEquals(3, intList.length());
		assertEquals("1 3 2", intList.toString());
	}

	// Tests calling occurrencesOfValue() on different values that are
	// present in a NormalLinkedList object.
	@Test
	public void testPublic4() {
		NormalLinkedList<Character> charList = TestData.list2();

		assertEquals(1, charList.occurrencesOfValue('v'));
		assertEquals(3, charList.occurrencesOfValue('e'));
	}

	// Tests calling occurrencesOfValue() on an value that is not
	// present in a NormalLinkedList object.
	@Test
	public void testPublic5() {
		assertEquals(0, TestData.list2().occurrencesOfValue('z'));
	}

	// Tests calling indexOfValue() on different values that are
	// present in a NormalLinkedList object.
	@Test
	public void testPublic6() {
		NormalLinkedList<String> stringList = TestData.list3();

		assertEquals(1, stringList.indexOfValue("eel"));
		assertEquals(5, stringList.indexOfValue("rat"));
		assertEquals(8, stringList.indexOfValue("fox"));
	}

	// Tests calling indexOfValue() on an value that is not present in
	// a NormalLinkedList object.
	@Test
	public void testPublic7() {
		assertEquals(-1, TestData.list3().indexOfValue("bat"));
	}

	// Tests calling valueLocatedAtIndex() on a NormalLinkedList object in
	// typical
	// cases.
	@Test
	public void testPublic8() {
		NormalLinkedList<Integer> intList = TestData.list1();

		assertEquals(66, (int) intList.valueLocatedAtIndex(1));
		assertEquals(34, (int) intList.valueLocatedAtIndex(8));
		assertEquals(24, (int) intList.valueLocatedAtIndex(16));
	}

	// Tests calling valueLocatedAtIndex() on a NormalLinkedList object when
	// position is negative, which should throw the expected
	// IndexOutOfBoundsException.
	@Test(expected = IndexOutOfBoundsException.class)
	public void testPublic9() {
		TestData.list1().valueLocatedAtIndex(-2);
	}

	// Tests calling valueLocatedAtIndex() on a NormalLinkedList object when
	// position is greater than the position of the last value in the current
	// object list, which should throw the expected IndexOutOfBoundsException.
	@Test(expected = IndexOutOfBoundsException.class)
	public void testPublic10() {
		TestData.list1().valueLocatedAtIndex(20);
	}

	// Tests calling compareTo() on two NormalLinkedList objects that have
	// all the same values in the same order.
	@Test
	public void testPublic11() {
		NormalLinkedList<Integer> intList = TestData.list1();

		assertEquals(0, intList.compareTo(intList));
	}

	// Tests calling compareTo() on two NormalLinkedList objects that have
	// different values.
	@Test
	public void testPublic12() {
		NormalLinkedList<Character> otherList = new NormalLinkedList<Character>();

		for (Character ch : new Character[] { 'a', 'r', 'm', 'a', 'd', 'i', 'l',
				'l', 'o' })
			otherList.addNewEltToList(ch);

		assertTrue(TestData.list2().compareTo(otherList) > 0);
	}

	// Tests calling toString() on a InorderLinkedList object, after adding
	// some values.
	@Test
	public void testPublic13() {
		assertEquals("9 10 24 33 34 35 52 58 64 66 81 89 92 92 92 92 96 96",
				TestData.list4().toString());
	}

	// Tests calling a few of the methods on empty lists.
	@Test
	public void testPublic14() {
		NormalLinkedList<Boolean> emptyList = new NormalLinkedList<Boolean>();

		assertEquals(0, emptyList.length());

		// should have no effect; list should still be empty
		emptyList.clear();
		assertEquals(0, emptyList.length());
		assertEquals("", emptyList.toString());

		assertEquals(0, emptyList.compareTo(emptyList));
	}

	// Tests calling some of the methods with null parameters. This test
	// illustrates another way of testing whether expected exceptions are
	// thrown, which would be needed when multiple exceptions will be thrown
	// in a single test method. (This avoids having to write two separate
	// tests, both using "@Test(expected=IllegalArgumentException.class)").
	@Test
	public void testPublic15() {
		NormalLinkedList<Integer> intList = TestData.list1();

		try {
			intList.occurrencesOfValue(null);
			// if we reach here- meaning if the expected exception is not
			// thrown-
			// the test should fail
			fail();
		} catch (IllegalArgumentException iae) {
			// if we get here the expected exception was thrown, so continue to
			// the
			// next thing we want to test
		}

		try {
			intList.indexOfValue(null);
			// if we reach here- meaning if the expected exception is not
			// thrown-
			// the test should fail
			fail();
		} catch (IllegalArgumentException iae) {
			// if we get here the test should pass
		}
	}

}
