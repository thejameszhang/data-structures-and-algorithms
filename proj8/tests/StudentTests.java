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

import dwGraph.DWGraph;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashSet;

public class StudentTests {

	/**
	 * Tests the basic operation of predecessorsOfVertex
	 */
	@Test
	public void testStudent1() {
		DWGraph<Character> graph = TestData.exampleGraph5();
		Collection<Character> result = graph.predecessorsOfVertex('e');
		Collection<Character> expected = new HashSet<>();
		expected.add('c');
		expected.add('a');
		expected.add('l');
		assertTrue(TestData.compareCollections(result, expected));
	}

	/**
	 * Tests the that predecessorOfVertex returns null when destVertex is not in
	 * the graph
	 */
	@Test
	public void testStudent2() {
		DWGraph<Character> graph = TestData.exampleGraph5();
		assertNull(graph.predecessorsOfVertex('z'));
	}

	/**
	 * Tests that the rest of the methods throw exceptions when arguments are
	 * null
	 */
	@Test
	public void testStudent3() {
		DWGraph<Character> graph = new DWGraph<>();

		try {
			graph.createEdge('s', null, 5);
			// if we reach here- meaning if the expected exception was not
			// thrown-
			// the test should fail
			fail();
		} catch (IllegalArgumentException e) {
			// if we get here the expected exception was thrown, so continue to
			// the next thing we want to test
		}

		try {
			graph.createEdge(null, 'z', 5);
			// if we reach here- meaning if the expected exception was not
			// thrown-
			// the test should fail
			fail();
		} catch (IllegalArgumentException e) {
			// if we get here the expected exception was thrown, so continue to
			// the next thing we want to test
		}

		try {
			graph.edgeCost('s', null);
			// if we reach here- meaning if the expected exception was not
			// thrown-
			// the test should fail
			fail();
		} catch (IllegalArgumentException e) {
			// if we get here the expected exception was thrown, so continue to
			// the next thing we want to test
		}

		try {
			graph.edgeCost(null, 'z');
			// if we reach here- meaning if the expected exception was not
			// thrown-
			// the test should fail
			fail();
		} catch (IllegalArgumentException e) {
			// if we get here the expected exception was thrown, so continue to
			// the next thing we want to test
		}

		try {
			graph.removeEdge('s', null);
			// if we reach here- meaning if the expected exception was not
			// thrown-
			// the test should fail
			fail();
		} catch (IllegalArgumentException e) {
			// if we get here the expected exception was thrown, so continue to
			// the next thing we want to test
		}

		try {
			graph.removeEdge(null, 'z');
			// if we reach here- meaning if the expected exception was not
			// thrown-
			// the test should fail
			fail();
		} catch (IllegalArgumentException e) {
			// if we get here the expected exception was thrown, so continue to
			// the next thing we want to test
		}

		try {
			graph.removeVertex(null);
			// if we reach here- meaning if the expected exception was not
			// thrown-
			// the test should fail
			fail();
		} catch (IllegalArgumentException e) {
			// if we get here the expected exception was thrown, so continue to
			// the next thing we want to test
		}

		try {
			graph.adjacentVertices(null);
			// if we reach here- meaning if the expected exception was not
			// thrown-
			// the test should fail
			fail();
		} catch (IllegalArgumentException e) {
			// if we get here the expected exception was thrown, so continue to
			// the next thing we want to test
		}

		try {
			graph.predecessorsOfVertex(null);
			// if we reach here- meaning if the expected exception was not
			// thrown-
			// the test should fail
			fail();
		} catch (IllegalArgumentException e) {
			// if we get here the expected exception was thrown, so continue to
			// the next thing we want to test
		}

		try {
			graph.divideGraph(null);
			// if we reach here- meaning if the expected exception was not
			// thrown-
			// the test should fail
			fail();
		} catch (IllegalArgumentException e) {
			// if we get here the expected exception was thrown, so continue to
			// the next thing we want to test
		}
	}

	/**
	 * Test that divideGraph works under the general case
	 */
	@Test
	public void testStudent4() {
		DWGraph<Character> graph = TestData.exampleGraph5();

		Collection<Character> set = new HashSet<>();
		set.add('f');
		set.add('r');
		set.add('o');
		set.add('g');

		Collection<Character> set2 = new HashSet<>();
		set2.add('c');
		set2.add('a');
		set2.add('m');
		set2.add('e');
		set2.add('l');

		DWGraph<Character> newGraph = graph.divideGraph(set);
		assertTrue(TestData.compareCollections(set, newGraph.getVertices()));
		assertTrue(TestData.compareCollections(set2, graph.getVertices()));
		assertFalse(graph.isVertex('f'));
		assertFalse(newGraph.isVertex('c'));
	}

	/**
	 * Test that divideGraph works when the specified collection contains all
	 * vertices of the current object graph
	 */
	@Test
	public void testStudent5() {
		DWGraph<Character> graph = TestData.exampleGraph5();
		Collection<Character> set = new HashSet<>();
		set.add('f');
		set.add('r');
		set.add('o');
		set.add('g');
		set.add('c');
		set.add('a');
		set.add('m');
		set.add('e');
		set.add('l');

		DWGraph<Character> newGraph = graph.divideGraph(set);
		assertTrue(TestData.compareCollections(set, newGraph.getVertices()));
		for (Character c : set)
			assertFalse(graph.isVertex(c));
	}

	/**
	 * Test that divideGraph does not add elements of the collection that are
	 * not in the current object graph to the new graph
	 */
	@Test
	public void testStudent6() {
		DWGraph<Character> graph = TestData.exampleGraph5();
		Collection<Character> set = new HashSet<>();
		set.add('f');
		set.add('r');
		set.add('o');
		set.add('g');
		set.add('z');

		DWGraph<Character> newGraph = graph.divideGraph(set);
		assertFalse(TestData.compareCollections(set, newGraph.getVertices()));
		assertTrue(newGraph.isVertex('f'));
		assertFalse(newGraph.isVertex('z'));
	}

	/**
	 * Test that divideGraph does not do anything when the collection is empty
	 */
	@Test
	public void testStudent7() {
		DWGraph<Character> graph = TestData.exampleGraph5();
		Collection<Character> set = new HashSet<>();
		Collection<Character> set2 = new HashSet<>();
		set2.add('f');
		set2.add('r');
		set2.add('o');
		set2.add('g');
		set2.add('c');
		set2.add('a');
		set2.add('m');
		set2.add('e');
		set2.add('l');

		graph.divideGraph(set);
		assertTrue(TestData.compareCollections(set2, graph.getVertices()));
	}

	/**
	 * Test that divideGraph works when one element is in the collection
	 */
	@Test
	public void testStudent8() {
		DWGraph<Character> graph = TestData.exampleGraph5();
		Collection<Character> set = new HashSet<>();
		set.add('f');
		Collection<Character> set2 = new HashSet<>();
		set2.add('r');
		set2.add('o');
		set2.add('g');
		set2.add('c');
		set2.add('a');
		set2.add('m');
		set2.add('e');
		set2.add('l');

		DWGraph<Character> newGraph = graph.divideGraph(set);
		assertTrue(TestData.compareCollections(set, newGraph.getVertices()));
		assertTrue(TestData.compareCollections(set2, graph.getVertices()));
	}

	/**
	 * Test that divideGraph works when an element in the collection is a vertex
	 * with no edges
	 */
	@Test
	public void testStudent9() {
		DWGraph<Character> graph = TestData.exampleGraph5();
		graph.createVertex('z');
		Collection<Character> set = new HashSet<>();
		set.add('f');
		set.add('r');
		set.add('o');
		set.add('g');
		set.add('z');
		Collection<Character> set2 = new HashSet<>();
		set2.add('c');
		set2.add('a');
		set2.add('m');
		set2.add('e');
		set2.add('l');

		DWGraph<Character> newGraph = graph.divideGraph(set);
		assertTrue(TestData.compareCollections(set, newGraph.getVertices()));
		assertTrue(TestData.compareCollections(set2, graph.getVertices()));
	}

	/**
	 * Test that divideGraph works when an element in the collection is a vertex
	 * with no edges
	 */
	@Test
	public void testStudent10() {
		DWGraph<Character> graph = TestData.exampleGraph5();
		graph.createVertex('z');
		Collection<Character> set = new HashSet<>();
		set.add('f');
		set.add('z');
		Collection<Character> set2 = new HashSet<>();
		set2.add('r');
		set2.add('o');
		set2.add('g');
		set2.add('c');
		set2.add('a');
		set2.add('m');
		set2.add('e');
		set2.add('l');

		DWGraph<Character> newGraph = graph.divideGraph(set);
		assertTrue(TestData.compareCollections(set, newGraph.getVertices()));
		assertTrue(TestData.compareCollections(set2, graph.getVertices()));
	}

}
