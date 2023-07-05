package tests;

import org.junit.*;

import someRecursiveMethods.SomeRecursiveMethods;
import someRecursiveMethods.UMCPList;

import static org.junit.Assert.*;

import java.util.Arrays;

public class StudentTests {

	/**
	 * Tests that for all the methods, when null parameters are passed in, an
	 * InvalidArgumentException is thrown
	 */
	@Test
	public void testStudent1() {
		UMCPList<Integer> list = new UMCPList<>(
				Arrays.asList(131, 132, 216, 250, 320, 330, 351));

		try {
			SomeRecursiveMethods.sumEltLocations(null, 250);
			fail();
		} catch (IllegalArgumentException iae) {
			// if we get here the expected exception was thrown, so continue to
			// the next thing we want to test
		}

		try {
			SomeRecursiveMethods.sumEltLocations(list, null);
			fail();
		} catch (IllegalArgumentException iae) {
			// continue to the next thing we want to test
		}

		try {
			SomeRecursiveMethods.locOfOccurrence(null, 250, 2);
			fail();
		} catch (IllegalArgumentException iae) {
			// continue to the next thing we want to test
		}

		try {
			SomeRecursiveMethods.locOfOccurrence(list, null, 2);
			fail();
		} catch (IllegalArgumentException iae) {
			// continue to the next thing we want to test
		}

		try {
			SomeRecursiveMethods.mirrorImage(null);
			fail();
		} catch (IllegalArgumentException iae) {
			// continue to the next thing we want to test
		}

		try {
			SomeRecursiveMethods.makeList(null, 4, 1, 1);
			fail();
		} catch (IllegalArgumentException iae) {
			// continue to the next thing we want to test
		}
	}

	/**
	 * Tests that sumEltLocations returns 0 when list is empty
	 */
	@Test
	public void testStudent2() {
		UMCPList<Integer> list = new UMCPList<>();
		assertEquals(0, SomeRecursiveMethods.sumEltLocations(list, 2));
	}

	/**
	 * Tests that sumEltLocations checks elements at the beginning, middle, and
	 * end of the list
	 */
	@Test
	public void testStudent3() {
		UMCPList<Integer> list = new UMCPList<>(
				Arrays.asList(4, 5, 9, 4, 12, 3, 4));
		assertEquals(12, SomeRecursiveMethods.sumEltLocations(list, 4));
	}

	/**
	 * Tests that sumEltLocations returns 0 when there is no occurrence of
	 * element
	 */
	@Test
	public void testStudent4() {
		UMCPList<Integer> list = new UMCPList<>(
				Arrays.asList(4, 5, 9, 4, 12, 3, 4));
		assertEquals(0, SomeRecursiveMethods.sumEltLocations(list, 100));
	}

	/**
	 * Tests that when an invalid n value is passed into locOfOccurrence, -1 is
	 * returned
	 */
	@Test
	public void testStudent5() {
		UMCPList<Integer> list = new UMCPList<>(
				Arrays.asList(4, 5, 9, 4, 12, 3, 4));
		assertEquals(-1, SomeRecursiveMethods.locOfOccurrence(list, 9, 0));
		assertEquals(-1, SomeRecursiveMethods.locOfOccurrence(list, 9, -1));
	}

	/**
	 * Tests that if there aren't n occurrences of element in or if there are no
	 * occurrences, then -1 is returned
	 */
	@Test
	public void testStudent6() {
		UMCPList<Integer> list = new UMCPList<>(
				Arrays.asList(4, 5, 9, 4, 12, 3, 4));
		assertEquals(-1, SomeRecursiveMethods.locOfOccurrence(list, 4, 4));
		assertEquals(-1, SomeRecursiveMethods.locOfOccurrence(list, 9, 2));
		assertEquals(-1, SomeRecursiveMethods.locOfOccurrence(list, 100, 1));
	}

	/**
	 * Tests that locOfOccurrences checks beginning, middle, and end of the list
	 */
	@Test
	public void testStudent7() {
		UMCPList<Integer> list = new UMCPList<>(
				Arrays.asList(4, 5, 9, 4, 12, 3, 4));
		assertEquals(7, SomeRecursiveMethods.locOfOccurrence(list, 4, 3));
	}

	/**
	 * Tests that mirrorImage works as desired for list longer than size 2
	 */
	@Test
	public void testStudent8() {
		UMCPList<Integer> list = new UMCPList<>(
				Arrays.asList(4, 5, 9, 4, 12, 3));
		assertEquals("4 5 9 4 12 3 3 12 4 9 5 4",
				SomeRecursiveMethods.mirrorImage(list).toString());
	}

	/**
	 * Tests that mirrorImage returns an empty newList if list is empty
	 */
	@Test
	public void testStudent9() {
		UMCPList<Integer> list = new UMCPList<>();
		assertEquals("", SomeRecursiveMethods.mirrorImage(list).toString());
	}

	/**
	 * Tests that if numValues is 0 or negative then makeList returns an empty
	 * list
	 */
	@Test
	public void testStudent10() {
		UMCPList<Integer> list = new UMCPList<>(
				Arrays.asList(4, 5, 9, 4, 12, 3));
		assertEquals("",
				SomeRecursiveMethods.makeList(list, 0, 1, 1).toString());
		assertEquals("",
				SomeRecursiveMethods.makeList(list, -12, 1, 1).toString());
	}

	/**
	 * Tests that if startPos or n is 0 or negative then makeList returns an
	 * empty list
	 */
	@Test
	public void testStudent11() {
		UMCPList<Integer> list = new UMCPList<>(
				Arrays.asList(4, 5, 9, 4, 12, 3));
		assertEquals("",
				SomeRecursiveMethods.makeList(list, 0, 1, 1).toString());
		assertEquals("",
				SomeRecursiveMethods.makeList(list, -12, 1, 1).toString());
		assertEquals("",
				SomeRecursiveMethods.makeList(list, 3, 0, 1).toString());
		assertEquals("",
				SomeRecursiveMethods.makeList(list, 2, -1, 1).toString());
		assertEquals("",
				SomeRecursiveMethods.makeList(list, 3, 2, 0).toString());
		assertEquals("",
				SomeRecursiveMethods.makeList(list, 2, 2, -1).toString());
	}

	/**
	 * Tests that makeList returns an empty list if list is empty
	 */
	@Test
	public void testStudent12() {
		UMCPList<Integer> list = new UMCPList<>();
		assertEquals("",
				SomeRecursiveMethods.makeList(list, 1, 1, 1).toString());
	}

	/**
	 * Tests that makeList works as desired when searching beginning, middle,
	 * and end elements
	 */
	@Test
	public void testStudent13() {
		UMCPList<Integer> list = new UMCPList<>(Arrays.asList(1, 2, 4, 5, 6, 3,
				4, 5, 9, 12, 4, 2, 1, 0, 10, 2));
		assertEquals("1 6 9 1",
				SomeRecursiveMethods.makeList(list, 4, 1, 4).toString());
		assertEquals("0 10 2",
				SomeRecursiveMethods.makeList(list, 3, 14, 1).toString());
		assertEquals("5 12 2 0 2",
				SomeRecursiveMethods.makeList(list, 5, 8, 2).toString());
		assertEquals("5 1",
				SomeRecursiveMethods.makeList(list, 2, 8, 5).toString());
		assertEquals("1 2 4 5 6 3 4 5 9 12 4 2 1 0 10 2",
				SomeRecursiveMethods.makeList(list, 16, 1, 1).toString());
	}

	/**
	 * Tests that when there are too few elements, makeList just returns valid
	 * elements up until the end of the list
	 */
	@Test
	public void testStudent14() {
		UMCPList<Integer> list = new UMCPList<>(Arrays.asList(1, 2, 4, 5, 6, 3,
				4, 5, 9, 12, 4, 2, 1, 0, 10, 2));
	assertEquals("5 12 2 0 2",
			SomeRecursiveMethods.makeList(list, 8, 8, 2).toString());
	}
}
