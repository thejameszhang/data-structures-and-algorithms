package tests;

// (c) Larry Herman, 2022.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import pbst.PBST;
import pbst.EmptyPBST;
import pbst.EmptyPBSTException;
import java.util.Collection;
import java.util.Arrays;
import org.junit.*;
import static org.junit.Assert.*;

@SuppressWarnings({ "unchecked" })
public class PublicTests {

	// Tests calling numPairs() on an empty tree.
	@Test
	public void testPublic1() {
		assertEquals(0, EmptyPBST.getInstance().numPairs());
	}

	// Tests calling numPairs() on nonempty trees; this also tests that
	// addKeyValuePair() is working.
	@Test
	public void testPublic2() {
		assertEquals(7, TestData.sampleTree1().numPairs());
		assertEquals(8, TestData.sampleTree2().numPairs());
	}

	// Tests calling getValueByKey() on the key of the root element of a
	// nonempty tree.
	@Test
	public void testPublic3() {
		assertEquals(Character.valueOf('p'),
				TestData.sampleTree1().getValueByKey(7));
	}

	// Tests calling getValueByKey() on the key of an interior element of a
	// nonempty tree.
	@Test
	public void testPublic4() {
		assertEquals(Character.valueOf('i'),
				TestData.sampleTree1().getValueByKey(13));
	}

	// Tests calling getValueByKey() on a key that is not present in a
	// nonempty tree.
	@Test
	public void testPublic5() {
		assertNull(TestData.sampleTree1().getValueByKey(132));
	}

	// Tests calling getValueByKey() on an empty tree.
	@Test
	public void testPublic6() {
		assertNull(EmptyPBST.getInstance().getValueByKey('X'));
	}

	// Tests calling toString() on an empty tree.
	@Test
	public void testPublic7() {
		assertEquals("", EmptyPBST.getInstance().toString());
	}

	// Tests calling toString() on a nonempty tree.
	@Test
	public void testPublic8() {
		assertEquals("1/d 5/o 6/l 7/p 11/h 13/i 17/n ",
				TestData.sampleTree1().toString());
	}

	// Tests the basic functionality of numPairs().
	@Test
	public void testPublic9() {
		PBST<String, String> tree = EmptyPBST.getInstance();

		assertEquals(0, tree.numPairs());

		tree = tree.addKeyValuePair("Ellie", "Elephant");
		assertEquals(1, tree.numPairs());
	}

	// Tests the basic functionality of largestKey(). (The "throws" clause is
	// just to make the compiler happy; the exception should not actually be
	// thrown.)
	@Test
	public void testPublic10() throws EmptyPBSTException {
		assertEquals(Integer.valueOf(17), TestData.sampleTree1().largestKey());
	}

	// Tests calling largestKey() on an empty tree. (The "throws" clause is
	// just to make the compiler happy; the exception should not actually be
	// thrown.)
	@Test(expected = EmptyPBSTException.class)
	public void testPublic11() throws EmptyPBSTException {
		EmptyPBST.getInstance().largestKey();
	}

	// Tests the basic functionality of smallestKey(). (The "throws" clause is
	// just to make the compiler happy; the exception should not actually be
	// thrown.)
	@Test
	public void testPublic12() throws EmptyPBSTException {
		assertEquals(Integer.valueOf(1), TestData.sampleTree2().smallestKey());
	}

	// Tests calling collectionOfKeys() on an empty tree.
	@Test
	public void testPublic13() {
		PBST<Integer, Integer> emptyTree = EmptyPBST.getInstance();

		// Arrays.asList() is an empty collection
		assertTrue(TestData.checkCollection(Arrays.asList(),
				emptyTree.collectionOfKeys()));
	}

	// Tests calling collectionOfKeys() on a nonempty tree.
	@Test
	public void testPublic14() {
		PBST<Integer, Character> tree = TestData.sampleTree1();

		// Arrays.asList() is an empty collection
		assertTrue(
				TestData.checkCollection(Arrays.asList(1, 5, 6, 7, 11, 13, 17),
						tree.collectionOfKeys()));
	}

	// Tests calling removeKeyValuePair() on a leaf element of a nonempty
	// tree.
	@Test
	public void testPublic15() {
		PBST<Integer, Character> tree = TestData.sampleTree2();

		tree = tree.removeKeyValuePair(2);

		assertEquals(7, tree.numPairs());
		assertEquals("1/e 3/e 5/p 7/h 11/a 13/n 17/t ", tree.toString());
	}

	// Tests calling removeKeyValuePair() on an interior element of a nonempty
	// tree.
	@Test
	public void testPublic16() {
		PBST<Integer, Character> tree = TestData.sampleTree2();

		tree = tree.removeKeyValuePair(13);
		assertEquals(7, tree.numPairs());
		assertEquals("1/e 2/l 3/e 5/p 7/h 11/a 17/t ", tree.toString());
	}

	// Tests calling removeKeyValuePair() on a key that is not present in a
	// nonempty tree.
	@Test
	public void testPublic17() {
		PBST<Integer, Character> tree = TestData.sampleTree2();

		tree = tree.removeKeyValuePair(100000);

		assertEquals(8, tree.numPairs());
		assertEquals("1/e 2/l 3/e 5/p 7/h 11/a 13/n 17/t ", tree.toString());
	}

}
