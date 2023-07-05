package tests;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;
import org.junit.*;

import dwGraph.DWGraph;

import static org.junit.Assert.*;

public class PublicTests {

	/*
	 * A few tests use a method compareCollections() defined in the TestData
	 * class that checks a Collection to see if it contains expected contents.
	 * See the comments before it in the TestData class for more info. One test
	 * uses a method compareGraph(), also in the TestData class. It is passed a
	 * graph and some other parameters representing the expected components of
	 * the graph, and it compares to see if the graph's components match the
	 * parameter values. Again, see the comments before it in the TestData class
	 * for explanation.
	 *
	 * You can use both of these methods in your own student tests as needed, if
	 * you take a few moments first to understand what they are doing. (What
	 * this means is to understand what these methods are doing and how to call
	 * them- it's not necessary to understand their code to use these helper
	 * methods in your own tests, as long as you understand what they're doing
	 * and what to pass into them.)
	 */

	// Tests calling isVertex() on the vertices of a simple graph that has
	// vertices but no edges, and tests calling createVertex() on an
	// already-existing vertex.
	@Test
	public void testPublic1() {
		DWGraph<String> graph = TestData.exampleGraph1();

		for (String vertex : Arrays.asList("sheep", "beagle", "cat", "donkey",
				"elephant", "flamingo", "giraffe", "hyena", "iguana", "jaguar",
				"koala", "lemur", "manatee", "numbat", "ocelot"))
			assertTrue(graph.isVertex(vertex));

		// test the return value of createVertex() in the normal case
		assertTrue(graph.createVertex("parrot"));
		assertTrue(graph.isVertex("parrot"));

		assertFalse(graph.createVertex("parrot"));
	}

	// Tests calling getVertices() on an empty graph.
	@Test
	public void testPublic2() {
		DWGraph<Boolean> graph = new DWGraph<>();

		assertEquals(0, graph.getVertices().size());
	}

	// Tests calling isVertex() on an empty graph and on a nonexistent vertex,
	// and tests calling getVertices() on a graph that contains vertices but
	// no edges.
	@Test
	public void testPublic3() {
		DWGraph<String> graph = new DWGraph<>();

		assertFalse(graph.isVertex("zebra"));
		graph = TestData.exampleGraph1();
		assertFalse(graph.isVertex("zebra"));

		// tests that these two collections have the same elements, although not
		// necessarily in the same order
		assertTrue(TestData.compareCollections(
				Arrays.asList("sheep", "beagle", "cat", "donkey", "elephant",
						"flamingo", "giraffe", "hyena", "iguana", "jaguar",
						"koala", "lemur", "manatee", "numbat", "ocelot"),
				graph.getVertices()));
	}

	// Tests calling removeVertex() to remove some vertices from a graph that
	// contains vertices but no edges.
	@Test
	public void testPublic4() {
		DWGraph<String> graph = TestData.exampleGraph1();
		int pos = 1;

		// remove half of the current vertices
		for (String vertex : Arrays.asList("sheep", "cat", "elephant",
				"giraffe", "iguana", "koala", "manatee", "ocelot"))
			assertTrue(graph.removeVertex(vertex));

		for (String vertex : Arrays.asList("sheep", "beagle", "cat", "donkey",
				"elephant", "flamingo", "giraffe", "hyena", "iguana", "jaguar",
				"koala", "lemur", "manatee", "numbat", "ocelot")) {
			// every other vertex was removed from this list of original vertex
			// names, so test alternating vertices for presence and absence
			if (pos % 2 == 1)
				assertFalse(graph.isVertex(vertex));
			else
				assertTrue(graph.isVertex(vertex));

			pos++;
		}
	}

	// Tests calling createEdge() and edgeCost() in normal cases.
	@Test
	public void testPublic5() {
		DWGraph<Character> graph = TestData.exampleGraph2(); // calls
																// createEdge()
		char[] vertices = { 'e', 'd', 'u', 'c', 'a', 't', 'i', 'o', 'n' };
		int i;

		for (i = 0; i < vertices.length - 1; i++)
			assertEquals(1, graph.edgeCost(vertices[i], vertices[i + 1]));
	}

	// Tests calling createEdge() to try to create an edge with negative
	// weight, which should fail, and to try to add an already-existing edge,
	// which should succeed and change the edge's weight.
	@Test
	public void testPublic6() {
		// this is going to be a very small graph with only two vertices, "true"
		// and "false"
		DWGraph<Boolean> graph = new DWGraph<>();

		graph.createVertex(true);
		graph.createVertex(false);

		// a negative-weight edge cannot be created
		assertFalse(graph.createEdge(false, true, -1));

		// create a valid edge
		assertTrue(graph.createEdge(true, false, 132));
		// change its weight by calling createEdge() again with the same
		// vertices
		assertTrue(graph.createEdge(true, false, 216));
		// check that the weight was changed
		assertEquals(216, graph.edgeCost(true, false));
	}

	// Tests calling edgeCost() on existing vertices that don't have an edge
	// between them.
	@Test
	public void testPublic7() {
		DWGraph<String> graph = TestData.exampleGraph3();
		String[] vertices = { "sheep", "beagle", "cat", "donkey", "elephant",
				"flamingo", "giraffe", "hyena", "iguana", "jaguar", "koala",
				"lemur", "manatee", "numbat", "ocelot" };
		int i, j;

		for (i = vertices.length - 1; i >= 0; i--)
			for (j = i; j >= 0; j--)
				assertEquals(-1, graph.edgeCost(vertices[i], vertices[j]));
	}

	// Tests that createEdge() will create an edge with zero weight, and that
	// it only creates an edge in the correct direction, not one in the
	// opposite direction as well.
	@Test
	public void testPublic8() {
		DWGraph<Character> graph = TestData.exampleGraph5();

		// a zero-weight edge is fine
		assertTrue(graph.createEdge('r', 'a', 0));

		// creating this edge
		assertEquals(0, graph.edgeCost('r', 'a'));
		// does not cause the reverse one to also be created
		assertEquals(-1, graph.edgeCost('a', 'r'));
	}

	// Tests calling createEdge() to create an edge from a vertex to itself,
	// which is fine.
	@Test
	public void testPublic9() {
		DWGraph<Character> graph = new DWGraph<>();

		// create vertex
		assertTrue(graph.createVertex('a'));
		// create an edge from it to itself
		assertTrue(graph.createEdge('a', 'a', 1));
		// check that the edge exists
		assertEquals(1, graph.edgeCost('a', 'a'));
	}

	// Tests calling removeVertex() on an empty graph and on a nonexistent
	// vertex in a nonempty graph.
	@Test
	public void testPublic10() {
		DWGraph<Character> emptyGraph = new DWGraph<>();
		DWGraph<String> nonemptyGraph = TestData.exampleGraph3();

		// the graph should definitely be empty
		assertEquals(0, emptyGraph.getVertices().size());
		// removing a vertex should fail
		assertFalse(emptyGraph.removeVertex('x'));
		// the graph should still be empty
		assertEquals(0, emptyGraph.getVertices().size());

		// check the number of vertices of the nonempty graph
		assertEquals(15, nonemptyGraph.getVertices().size());
		// removing a nonexistent vertex should fail
		assertFalse(nonemptyGraph.removeVertex("dolphin"));
		// the number of vertices should not have changed
		assertEquals(15, nonemptyGraph.getVertices().size());
	}

	// Tests creating a larger graph with a lot of intermixed insertions and
	// deletions of vertices and insertions of edges.
	@Test
	public void testPublic11() {
		DWGraph<Character> graph = new DWGraph<>();
		char[] vertices = { 'e', 'd', 'u', 'c', 'a', 't', 'i', 'o', 'n' };
		int i;

		// add vertices to the graph, which have the names of the characters in
		// the array above
		for (char vertex : vertices)
			graph.createVertex(vertex);

		// create edges between 'e' and 'd', 'd' and 'u', 'u' and 'c', etc.; all
		// with weight 1
		for (i = 0; i < vertices.length - 1; i++)
			graph.createEdge(vertices[i], vertices[i + 1], 1);

		// add a number of other vertices and edges
		assertTrue(graph.createEdge('e', 'c', 1));
		assertTrue(graph.createEdge('a', 't', 1));
		assertTrue(graph.removeVertex('a'));
		assertFalse(graph.isVertex('a'));
		assertTrue(graph.createVertex('a'));
		assertTrue(graph.isVertex('a'));
		assertTrue(graph.createEdge('a', 't', 1));
		assertTrue(graph.isVertex('a'));
		assertTrue(graph.createEdge('d', 'u', 3));
		assertTrue(graph.removeVertex('c'));
		assertTrue(graph.createEdge('d', 'u', 3));
		assertTrue(graph.createEdge('o', 'e', 3));
		assertFalse(graph.createEdge('i', 'd', -3));

		assertEquals(8, graph.getVertices().size());
	}

	// Tests calling removeVertex() on a vertex that has outgoing edges.
	@Test
	public void testPublic12() {
		DWGraph<Integer> graph = TestData.exampleGraph4();

		assertTrue(graph.removeVertex(8));
		assertFalse(graph.isVertex(8));
	}

	// Tests calling removeVertex() to remove all of the vertices from a graph
	// (that contains vertices but no edges) and then adds some new vertices.
	@Test
	public void testPublic13() {
		DWGraph<String> graph = TestData.exampleGraph1();
		String[] vertices = { "sheep", "beagle", "cat", "donkey", "elephant",
				"flamingo", "giraffe", "hyena", "iguana", "jaguar", "koala",
				"lemur", "manatee", "numbat", "ocelot" };
		String[] newVertices = { "penguin", "quokka", "rhinoceros",
				"salamander", "turtle", "umbrellabird", "vulture", "wallaby",
				"beagle", "koala" };

		// remove all of the vertices
		for (String vertex : vertices)
			graph.removeVertex(vertex);

		// check that they are no longer present
		for (String vertex : vertices)
			assertFalse(graph.isVertex(vertex));

		// add vertices with the same names back again
		for (String vertex : newVertices)
			graph.createVertex(vertex);

		// check that the new vertices were all created successfully
		for (String vertex : newVertices)
			assertTrue(graph.isVertex(vertex));
	}

	// Tests calling adjacentVertices() in the normal case.
	@Test
	public void testPublic14() {
		DWGraph<Integer> graph = TestData.exampleGraph4();
		// a List of Lists of integers
		List<List<Integer>> expectedNeighbors = Arrays.asList(
				Arrays.asList(2, 3, 4, 5), // expected neighbors of 1
				Arrays.asList(4), // expected neighbors of 2
				Arrays.asList(4), // expected neighbors of 3
				Arrays.asList(), // expected neighbors of 4
				Arrays.asList(4), // expected neighbors of 5
				Arrays.asList(3, 4, 5), // expected neighbors of 6
				Arrays.asList(3, 6), // expected neighbors of 7
				Arrays.asList(4, 6, 7) // expected neighbors of 8
		);
		int i;

		// normal cases
		for (i = 0; i < expectedNeighbors.size(); i++)
			assertTrue(TestData.compareCollections(expectedNeighbors.get(i),
					graph.adjacentVertices(i + 1)));
	}

	// Tests calling adjacentVertices() on a nonexistent vertex.
	@Test
	public void testPublic15() {
		DWGraph<Character> graph = TestData.exampleGraph2();

		assertNull(graph.adjacentVertices('x'));
	}

	// Tests calling createVertex() and isVertex() with null arguments. Using
	// exceptions we can test that more than one expected exception is thrown
	// in the same test.
	@Test
	public void testPublic16() {
		DWGraph<Character> graph = new DWGraph<>();

		try {
			graph.createVertex(null);
			// if we reach here- meaning if the expected exception was not
			// thrown-
			// the test should fail
			fail();
		} catch (IllegalArgumentException e) {
			// if we get here the expected exception was thrown, so continue to
			// the next thing we want to test
		}

		try {
			graph.isVertex(null);
			// if we reach here- meaning if the expected exception was not
			// thrown-
			// the test should fail
			fail();
		} catch (IllegalArgumentException e) {
			// if we get here the expected exception was thrown, so continue to
			// the next thing we want to test
		}

		assertEquals(0, graph.getVertices().size());
	}

	// Tests the basic operation of divideGraph().
	@Test
	public void testPublic17() {
		DWGraph<Character> graph = TestData.exampleGraph5();
		DWGraph<Character> newGraph;
		List<Character> newGraphVertices = Arrays.asList('f', 'r', 'o', 'g');
		int i;

		// divide the graph
		newGraph = graph.divideGraph(newGraphVertices);

		// first just check the number of vertices remainng in the original
		// graph and in the new graph
		assertEquals(5, graph.getVertices().size());
		assertEquals(4, newGraph.getVertices().size());

		// now check the sets of vertices in both graphs
		assertTrue(TestData.compareCollections(graph.getVertices(),
				Arrays.asList('c', 'a', 'm', 'e', 'l')));
		assertTrue(TestData.compareCollections(newGraph.getVertices(),
				Arrays.asList('f', 'r', 'o', 'g')));

		// now check all components of the original graph
		assertTrue(TestData.compareGraph(graph,
				Arrays.asList('c', 'a', 'm', 'e', 'l'),
				Arrays.asList(Arrays.asList('a', 'e'), Arrays.asList('e'),
						Arrays.asList('l'), Arrays.asList('c', 'm'),
						Arrays.asList('c', 'e')),
				Arrays.asList(Arrays.asList(2, 2), Arrays.asList(2),
						Arrays.asList(2), Arrays.asList(2, 2),
						Arrays.asList(2, 2))));

		// and check all components of the new graph
		assertTrue(TestData.compareGraph(newGraph,
				Arrays.asList('f', 'r', 'o', 'g'),
				Arrays.asList(Arrays.asList('o'), Arrays.asList('f', 'o'),
						Arrays.asList('g'), Arrays.asList('r')),
				Arrays.asList(Arrays.asList(2), Arrays.asList(2, 2),
						Arrays.asList(2), Arrays.asList(2))));
	}

}
