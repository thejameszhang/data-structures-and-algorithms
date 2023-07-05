package dwGraph;

/**
 * @author: James Zhang
 * @directoryID: jzhang72
 * @uid: 118843940
 * @discussionNumber: 0107
 * I pledge on my honor that I have not given or received any unauthorized 
 * assistance on this assignment.
 */

/**
 * This class implements a graph using a hashmap such that the keys represent 
 * all of the vertices in the graph. The value of each key is another hashmap. 
 * Essentially, each vertex has its own hashmap such that the key of this inner 
 * hashmap is a neighbor meaning the first vertex points to this vertex, and 
 * the value of this inner hashmap is the weight of this edge. This class also 
 * contains some basic graph functions, such as adding/removing vertices/edges,
 * returning weights of edges, finding adjacent/predecessor vertices, and 
 * dividing graphs into subgraphs.
 */

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class DWGraph<V extends Comparable<V>> {

	// instance fields of the class
	private Map<V, Map<V, Integer>> graph;

	// constructor for the class
	public DWGraph() {
		graph = new HashMap<>();
	}

	/**
	 * 3.1) This method creates a new vertex and adds it to the graph as long as
	 * the argument dataForIndex is not null and dataForIndex is not already
	 * present in the graph, in which the method returns false. Otherwise, the
	 * method adds dataForVertex to the graph and returns true.
	 * 
	 * @param dataForVertex
	 * @return whether the vertex was created or not
	 */
	public boolean createVertex(V dataForVertex) {

		// check if any arguments are null
		if (dataForVertex == null)
			throw new IllegalArgumentException();

		// if the graph already contains the vertex key, then return false
		if (graph.containsKey(dataForVertex))
			return false;

		// otherwise add the vertex to the map and return true
		graph.put(dataForVertex, new HashMap<V, Integer>());
		return true;
	}

	/**
	 * 3.2) This method determines if V dataForVertex is a vertex in the graph
	 * or not.
	 * 
	 * @param dataForVertex
	 * @return whether the argument is a vertex or not
	 */
	public boolean isVertex(V dataForVertex) {

		// check if any arguments are null
		if (dataForVertex == null)
			throw new IllegalArgumentException();

		boolean res = false;

		// if the graph already contains the vertex key, then return true
		if (graph.containsKey(dataForVertex))
			res = true;
		return res;
	}

	/**
	 * 3.3) This method returns an unordered HashSet of the vertices of the
	 * graph that is independent of the current object graph.
	 * 
	 * @return a HashSet containing all vertices of the current object graph
	 */
	public Collection<V> getVertices() {

		Collection<V> vertices = graph.keySet();
		HashSet<V> res = new HashSet<>();

		for (V vertex : vertices)
			res.add(vertex);
		return res;
	}

	/**
	 * 3.4) This method establishes a new edge with weight weight from
	 * initialVertex to finalVertex as long as both vertices are not null and
	 * weight is nonnegative. Furthermore, if either or both initialVertex or
	 * finalVertex are not in the current object graph, they will be created and
	 * added to the graph. If an edge already exists from initialVertex to
	 * finalVertex, then its weight will be updated with the specified argument
	 * weight.
	 * 
	 * @param initialVertex
	 * @param finalVertex
	 * @param weight
	 * @return whether the edge was created or not
	 */
	public boolean createEdge(V initialVertex, V finalVertex, int weight) {

		// check if any arguments are null
		if (initialVertex == null || finalVertex == null)
			throw new IllegalArgumentException();

		// check if weight is less than 0, then return false
		if (weight < 0)
			return false;

		// if neither initialVertex or finalVertex are not already present in
		// the graph, then they should be added as new vertices
		if (!graph.containsKey(initialVertex))
			createVertex(initialVertex);
		if (!graph.containsKey(finalVertex))
			createVertex(finalVertex);

		// the neighbors of initialVertex
		Map<V, Integer> neighbors = graph.get(initialVertex);

		// if final vertex already exists as a neighbor of initial vertex, then
		// the put method will simply update the edge weight with the new
		// argument weight
		neighbors.put(finalVertex, weight);
		return true;
	}

	/**
	 * 3.5) This method returns the weight of the edge from initialVertex to
	 * finalVertex as long as both vertices are not null, both vertices exist in
	 * the graph, and there is in fact an edge between both vertices. If any of
	 * these conditions are not true, then the method will return -1.
	 * 
	 * @param initialVertex
	 * @param finalVertex
	 * @return the weight of the edge or -1
	 */
	public int edgeCost(V initialVertex, V finalVertex) {

		// check if any arguments are null
		if (initialVertex == null || finalVertex == null)
			throw new IllegalArgumentException();

		int res = -1;

		// if both vertices exist in the graph, keep going, otherwise return -1
		if (graph.containsKey(initialVertex)
				&& graph.containsKey(finalVertex)) {

			// check if finalVertex is in initialVertex's neighbors
			// if yes, return the weight
			Map<V, Integer> neighbors = graph.get(initialVertex);
			if (neighbors.containsKey(finalVertex))
				res = neighbors.get(finalVertex);
		}
		return res;
	}

	/**
	 * 3.6) This method removes the edge from initialVertex to finalVertex as
	 * long as both vertices are not null, both vertices exist in the graph, and
	 * finalVertex is a neighbor to initlaVertex, in which case, the method
	 * returns true. Otherwise, the method returns false.
	 * 
	 * @param initialVertex
	 * @param finalVertex
	 * @return whether or not the edge is removed
	 */
	public boolean removeEdge(V initialVertex, V finalVertex) {

		// check if any arguments are null
		if (initialVertex == null || finalVertex == null)
			throw new IllegalArgumentException();

		boolean res = false;
		// if both vertices exist in the graph, keep going
		if (graph.containsKey(initialVertex)
				&& graph.containsKey(finalVertex)) {

			// check if finalVertex is in initialVertex's neighbors
			// if yes, remove the edge
			Map<V, Integer> neighbors = graph.get(initialVertex);
			if (neighbors.containsKey(finalVertex)) {

				// removes finalVertex and its associated value (the weight)
				neighbors.remove(finalVertex);
				res = true;
			}
		}
		return res;
	}

	/**
	 * 3.7) This method removes all of the incoming and outgoing edges
	 * associated with datForVertex as long as dataForVertex is not null and the
	 * graph contains it. If everything is removed successfully, the method
	 * returns true, otherwise it returns false.
	 * 
	 * @param dataForVertex
	 * @return if the vertex and all edges are successfully removed or not
	 */
	public boolean removeVertex(V dataForVertex) {

		// check if any arguments are null
		if (dataForVertex == null)
			throw new IllegalArgumentException();

		boolean res = false;

		// if graph contains dataForVertex, then remove it and all
		// incoming/outgoing edges
		if (graph.containsKey(dataForVertex)) {

			// remove all incoming edges
			Collection<V> vertices = graph.keySet();

			// for all vertices, if dataForVertex is in its neighbors, call
			// removeEdge on it
			for (V vertex : vertices) {
				Map<V, Integer> map = graph.get(vertex);
				if (map.containsKey(dataForVertex))
					removeEdge(vertex, dataForVertex);
			}

			// removes all outgoing edges
			graph.remove(dataForVertex);
			res = true;
		}
		return res;
	}

	/**
	 * 3.8) This method returns all vertices that have an outgoing edge from
	 * dataForVertex as long as dataForVertex is not null and the graph contains
	 * it; otherwise null is returned. The hashset that is returned is
	 * independent of the current object graph.
	 * 
	 * @param dataForVertex
	 * @return
	 */
	public Collection<V> adjacentVertices(V dataForVertex) {

		// check if any arguments are null
		if (dataForVertex == null)
			throw new IllegalArgumentException();

		if (graph.containsKey(dataForVertex)) {

			// all neighbors and associated weights of dataForVertex
			Map<V, Integer> neighbors = graph.get(dataForVertex);

			// get only the vertices and copy it to an independent hashset
			Collection<V> vertices = neighbors.keySet();
			HashSet<V> res = new HashSet<>();
			for (V vertex : vertices) {
				res.add(vertex);
			}
			return res;
		}
		return null;
	}

	/**
	 * 3.9) This method returns a hashset of vertices that have an edge that
	 * points towards the destVertex as long as destVertex is not null and the
	 * graph contains it; otherwise null is returned. The method returns a new
	 * collection independent of the current object graph.
	 * 
	 * @param destVertex
	 * @return collection of predecessor vertices
	 */
	public Collection<V> predecessorsOfVertex(V destVertex) {

		// check if any arguments are null
		if (destVertex == null)
			throw new IllegalArgumentException();

		if (graph.containsKey(destVertex)) {

			// make a collection of all keys in the graph
			Collection<V> vertices = graph.keySet();
			HashSet<V> res = new HashSet<>();

			// for each vertex in the graph
			for (V vertex : vertices) {

				// if destVertex is in the vertex's neighbors, add this vertex
				// to the res hashset
				Map<V, Integer> map = graph.get(vertex);
				if (map.containsKey(destVertex))
					res.add(vertex);
			}
			return res;
		}
		return null;
	}

	/**
	 * 3.10) This method creates a new graph that includes all edges between two
	 * vertices that are both already present in the current object graph and in
	 * the newly specified verticesForNewGraph. After all desired vertices are
	 * added to the new graph, all vertices in the new graph must be removed
	 * from the current object graph.
	 * 
	 * @param verticesForNewGraph
	 * @return the new graph
	 */
	public DWGraph<V> divideGraph(Collection<V> verticesForNewGraph) {

		// check if argument is null
		if (verticesForNewGraph == null)
			throw new IllegalArgumentException();

		// instantiate a new graph object
		DWGraph<V> newGraph = new DWGraph<>();

		// loop through all vertices in verticesForNewGraph
		for (V vertex : verticesForNewGraph) {

			// check that this vertex is in the graph
			if (graph.containsKey(vertex)) {
				newGraph.createVertex(vertex);

				// for all of its neighbors
				for (V v : graph.get(vertex).keySet()) {

					// if a neighbor is also in verticesForNewGraph, add it and
					// its edge weight to the new graph object
					if (verticesForNewGraph.contains(v))
						newGraph.createEdge(vertex, v,
								graph.get(vertex).get(v));
				}

				// edge case: if the collection contains a vertex with no edges
				if (graph.get(vertex).keySet().size() == 0)
					newGraph.createVertex(vertex);
			}
		}

		// find all vertices in the newGraph and remove each from the current
		// object graph
		Collection<V> remove = newGraph.getVertices();
		for (V vertex : remove) {
			removeVertex(vertex);
		}
		return newGraph;
	}
}
