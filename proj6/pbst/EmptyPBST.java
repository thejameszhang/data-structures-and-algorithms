/**
 * @author: James Zhang
 * @directoryID: jzhang72
 * @uid: 118843940
 * @discussionNumber: 0107
 * I pledge on my honor that I have not given or received any unauthorized 
 * assistance on this assignment.
 */

/**
 * 	This EmptyPBST class has basic methods that serve an important purpose in a 
 * 	Polymorphic Binary Search Tree implementation. All of the methods in the 
 *  NonemptyPBST class are also present here, as both classes extend from the 
 *  abstract PBST class. The class implements a singleton design such that 
 *  there is only one instance of the class, denoted by the variable name 
 *  "empty."
 */

package pbst;

import java.util.Collection;
import java.util.ArrayList;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class EmptyPBST<K extends Comparable<K>, V> extends PBST<K, V> {

	// empty private constructor. this must be empty to prevent other classes
	// from calling this constructor
	private EmptyPBST() {

	}

	// private fields for this class
	private static EmptyPBST empty = new EmptyPBST();

	/**
	 * 4.1) This method simply returns the lone instance of the EmptyPBST class
	 * 
	 * @return EmptyPBST empty
	 */
	public static EmptyPBST getInstance() {
		return empty;
	}

	/**
	 * 3.1) This method simply returns a new instance of the NonemptyPBST class
	 * as long as both parameters K newKey and V newValue are not null.
	 * 
	 * @return NonemptyPBST<K, V>
	 */
	public NonemptyPBST<K, V> addKeyValuePair(K newKey, V newValue) {

		// check if argument is null
		if (newKey == null || newValue == null)
			throw new IllegalArgumentException("Argument cannot be null.");

		return new NonemptyPBST<K, V>(newKey, newValue);
	}

	/**
	 * 3.2) This method returns 0 because it is not a key value pair in the PBST
	 * 
	 * @return 0
	 */
	public int numPairs() {
		return 0;
	}

	/**
	 * 3.3) This method returns null because there is no value associated with
	 * the singleton
	 * 
	 * @param K keyToLookUp
	 * @return null
	 */
	public V getValueByKey(K keyToLookUp) {

		if (keyToLookUp == null)
			throw new IllegalArgumentException("Argument cannot be null.");

		return null;
	}

	/**
	 * 3.4) This method throws an EmptyPBSTException because the singleton has
	 * no right field
	 */
	public K largestKey() throws EmptyPBSTException {
		throw new EmptyPBSTException();
	}

	/**
	 * 3.5) This method throws an EmptyPBSTException because the singleton has
	 * no left field
	 */
	public K smallestKey() throws EmptyPBSTException {
		throw new EmptyPBSTException();
	}

	/**
	 * 3.6) This method returns 0 because no left or right path can be taken
	 * from the singleton
	 */
	public int pathBalance(K keyToFind) {

		// check if argument is null
		if (keyToFind == null)
			throw new IllegalArgumentException("Argument cannot be null.");

		throw new IllegalArgumentException();
	}

	/**
	 * 3.7) This method returns an empty ArrayList of type K
	 * 
	 * @return ArrayList<K>
	 */
	public Collection<K> collectionOfKeys() {
		return new ArrayList<K>();
	}

	/**
	 * 3.8) This method returns the singleton
	 */
	public PBST<K, V> removeKeyValuePair(K keyToRemove) {

		// check if argument is null
		if (keyToRemove == null)
			throw new IllegalArgumentException("Argument cannot be null.");

		return empty;
	}

	/**
	 * 4.2) This method returns an empty string because the singleton has no key
	 * or value
	 */
	public String toString() {
		return "";
	}
}