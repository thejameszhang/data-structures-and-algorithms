package tests;

// (c) Larry Herman, 2022.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import someRecursiveMethods.UMCPList;
import someRecursiveMethods.SomeRecursiveMethods;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

	// Tests calling sumEltLocations() in different cases when there is only
	// one occurrence of the element in the list.
	@Test
	public void testPublic1() {
		UMCPList<Integer> list = new UMCPList<>(
				Arrays.asList(131, 132, 216, 250, 320, 330, 351));

		assertEquals(1, SomeRecursiveMethods.sumEltLocations(list, 131));
		assertEquals(4, SomeRecursiveMethods.sumEltLocations(list, 250));
	}

	// Tests calling sumEltLocations() when there is more than one occurrence
	// of the element in the list.
	@Test
	public void testPublic2() {
		UMCPList<Integer> list = new UMCPList<>(
				Arrays.asList(131, 132, 216, 250, 320, 216, 330, 351));

		assertEquals(9, SomeRecursiveMethods.sumEltLocations(list, 216));
	}

	// Tests calling locOfOccurrence() in different cases when n is 1.
	@Test
	public void testPublic3() {
		UMCPList<Boolean> list = new UMCPList<>(Arrays.asList(true, false,
				false, true, true, true, false, false, true, true));

		assertEquals(1, SomeRecursiveMethods.locOfOccurrence(list, true, 1));
		assertEquals(2, SomeRecursiveMethods.locOfOccurrence(list, false, 1));
	}

	// Tests calling locOfOccurrence() when n is greater than 1.
	@Test
	public void testPublic4() {
		UMCPList<Boolean> list = new UMCPList<>(Arrays.asList(true, false,
				false, true, true, true, false, false, true, true));

		assertEquals(4, SomeRecursiveMethods.locOfOccurrence(list, true, 2));
	}

	// Tests calling mirrorImage() on a one-element list.
	@Test
	public void testPublic5() {
		UMCPList<Character> list = new UMCPList<>(Arrays.asList('x'));

		assertEquals("x x", SomeRecursiveMethods.mirrorImage(list).toString());
	}

	// Tests calling mirrorImage() on a two-element list.
	@Test
	public void testPublic6() {
		UMCPList<Character> list = new UMCPList<>(Arrays.asList('x', 'y'));

		assertEquals("x y y x",
				SomeRecursiveMethods.mirrorImage(list).toString());
	}

	// Tests calling makeList() to create a one-element list starting with
	// (and containing only) the first element of the list.
	@Test
	public void testPublic7() {
		UMCPList<String> list = new UMCPList<>(
				Arrays.asList("Java", "lava", "guava"));

		assertEquals("Java",
				SomeRecursiveMethods.makeList(list, 1, 1, 1).toString());
	}

	// Tests calling makeList() to create a multiple-element list starting
	// with several elements at the beginning or middle of the list.
	@Test
	public void testPublic8() {
		UMCPList<Character> list = new UMCPList<>(Arrays.asList('r', 'e', 'a',
				'l', 'i', 'g', 'n', 'm', 'e', 'n', 't'));

		assertEquals("r e a l",
				SomeRecursiveMethods.makeList(list, 4, 1, 1).toString());
		assertEquals("r a i n",
				SomeRecursiveMethods.makeList(list, 4, 1, 2).toString());
		assertEquals("a l i g n",
				SomeRecursiveMethods.makeList(list, 5, 3, 1).toString());
	}

}
