package tests;

import org.junit.*;

import pbst.EmptyPBST;
import pbst.EmptyPBSTException;
import pbst.PBST;

import static org.junit.Assert.*;

public class StudentTests {

	// Checks that methods with null parameters throw an exception when
	// appropriate
	@Test
	public void testStudent1() {

		try {
			TestData.sampleTree3().addKeyValuePair(null, 'g');
			fail();
		} catch (IllegalArgumentException iae) {
			// if we get here the expected exception was thrown, so continue to
			// the next thing we want to test
		}

		try {
			TestData.sampleTree3().addKeyValuePair('z', null);
			fail();
		} catch (IllegalArgumentException iae) {
			// go to the next thing we want to test
		}

		try {
			TestData.sampleTree1().getValueByKey(null);
			fail();
		} catch (IllegalArgumentException iae) {
			// go to the next thing we want to test
		}

		try {
			TestData.sampleTree1().pathBalance(null);
			fail();
		} catch (IllegalArgumentException iae) {
			// go to the next thing we want to test
		}

		try {
			TestData.sampleTree2().removeKeyValuePair(null);
			fail();
		} catch (IllegalArgumentException iae) {
			// go to the next thing we want to test
		}
	}

	// Tests that addKeyValuePair works when the key to be added will be the
	// smallest key in the tree
	@Test
	public void testStudent2() throws EmptyPBSTException {

		PBST<Integer, Character> tree = TestData.sampleTree1();

		assertEquals(7, tree.numPairs());
		assertEquals(Integer.valueOf(1), tree.smallestKey());

		tree = tree.addKeyValuePair(0, 's');

		assertEquals(8, tree.numPairs());
		assertEquals(Integer.valueOf(0), tree.smallestKey());
		assertEquals(Character.valueOf('s'), tree.getValueByKey(0));
	}

	// Tests that removeKeyValuePair removes the root correctly plus basic
	// functionality
	@Test
	public void testStudent3() {
		PBST<Character, Character> tree = TestData.sampleTree3();
		assertEquals(14, tree.numPairs());

		tree = tree.removeKeyValuePair('e');
		assertEquals(13, tree.numPairs());
		assertNull(tree.getValueByKey('e'));

		tree = tree.removeKeyValuePair('c');
		assertEquals(12, tree.numPairs());
		assertNull(tree.getValueByKey('c'));
	}

	// Tests that addKeyValuePair updates the value when a key already exists
	@Test
	public void testStudent4() {
		PBST<Integer, Character> tree = TestData.sampleTree2();
		assertEquals(Character.valueOf('n'), tree.getValueByKey(13));
		tree = tree.addKeyValuePair(13, 's');
		assertEquals(Character.valueOf('s'), tree.getValueByKey(13));
	}

	// Tests that pathBalance throws an IllegalArgumentException when called on
	// an empty tree and when the key is not in the current object tree
	@Test
	public void testStudent5() {
		PBST<Integer, Integer> emptyTree = EmptyPBST.getInstance();
		PBST<Integer, Character> tree = TestData.sampleTree2();

		try {
			emptyTree.pathBalance(5);
			fail();
		} catch (IllegalArgumentException iae) {
			// go to the next thing we want to test
		}

		try {
			tree.pathBalance(100);
			fail();
		} catch (IllegalArgumentException iae) {
			// go to the next thing we want to test
		}
	}

	// Tests that basic functionality of pathBalance is correct
	@Test
	public void testStudent6() throws EmptyPBSTException {
		PBST<Integer, Character> tree = TestData.sampleTree2();
		assertEquals(0, tree.pathBalance(7));
		assertEquals(-2, tree.pathBalance(1));
		assertEquals(-2, tree.pathBalance(2));
		assertEquals(2, tree.pathBalance(17));

		tree.addKeyValuePair(100, 's');
		assertEquals(9, tree.numPairs());
		assertEquals(3, tree.pathBalance(100));
		assertEquals(Integer.valueOf(100), tree.largestKey());
		assertEquals(Integer.valueOf(1), tree.smallestKey());
	}

	// Tests that addKeyValuePair works for a key that will be the largest key
	@Test
	public void testStudent7() throws EmptyPBSTException {

		PBST<Integer, Character> tree = TestData.sampleTree2();

		assertEquals(8, tree.numPairs());
		assertEquals(Integer.valueOf(1), tree.smallestKey());

		tree = tree.addKeyValuePair(20, 's');

		assertEquals(9, tree.numPairs());
		assertEquals(Integer.valueOf(20), tree.largestKey());
		assertEquals(Character.valueOf('s'), tree.getValueByKey(20));
	}
}
