/**
 * @author: James Zhang
 * @directoryID: jzhang72
 * @uid: 118843940
 * @discussionNumber: 0107
 * I pledge on my honor that I have not given or received any unauthorized 
 * assistance on this assignment.
 */

/**
 * 	This class contains some methods to perform basic functionality for a 
 * 	Nonempty object in a Polymorphic Binary Search Tree. The class only contains 
 * 	4 fields: the key, the value, the left, and the right. Some methods include
 *  adding and removing key value pairs, and these are the only two methods 
 *  that can modify the current object tree. Other methods include returning
 *  the number of pairs in the tree, returning the value associated with a
 *  specific key, and returning the largest and smallest keys. Many of these 
 *  methods are done recursively. 
 */

package pbst;

import java.util.Collection;
import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class NonemptyPBST<K extends Comparable<K>, V> extends PBST<K, V> {

	// the only four instance fields of the class
	private K key;
	private V value;
	private PBST<K, V> left;
	private PBST<K, V> right;

	/**
	 * constructor for the NonemptyPBST class
	 * 
	 * @param key
	 * @param value
	 */
	public NonemptyPBST(K key, V value) {
		this.key = key;
		this.value = value;
		left = EmptyPBST.getInstance();
		right = EmptyPBST.getInstance();
	}

	/**
	 * 3.1) This method should add the specified key value pair. This pair will
	 * always be added as a leaf, unless it is the only object in the PBST, then
	 * it will be the root.
	 * 
	 * @param K key
	 * @param V value
	 * @NonemptyPBST<K, V>
	 */
	public NonemptyPBST<K, V> addKeyValuePair(K key, V value) {

		// check if argument is null
		if (key == null || value == null)
			throw new IllegalArgumentException("Argument cannot be null.");

		// for simplicity, store this compareTo value in an int num
		int num = key.compareTo(this.key);

		// if the key is less than the current key, make a recursive call on the
		// left subtree
		if (num < 0)
			left = left.addKeyValuePair(key, value);

		// or recursive call on the right subtree if the key is greater
		else if (num > 0)
			right = right.addKeyValuePair(key, value);

		// in the case where there is a duplicate key, we want to replace the
		// value of this object with the parameter specified value
		else
			this.value = value;

		return this;
	}

	/**
	 * 3.2) This method recursively calculates the total number of objects in
	 * the PBST
	 * 
	 * @return total number of pairs
	 */
	public int numPairs() {
		return 1 + left.numPairs() + right.numPairs();
	}

	/**
	 * 3.3) This method recursively, effectively traverses the PBST until it
	 * reaches an object with a key identical to the specified key. In this
	 * case, the value of this object is returned.
	 * 
	 * @param K keyToLookUp
	 * @return V value associated with the parameter K keyToLookUp
	 */
	public V getValueByKey(K keyToLookUp) {

		// check if argument is null
		if (keyToLookUp == null)
			throw new IllegalArgumentException("Argument cannot be null.");
		int num = keyToLookUp.compareTo(key);

		// base case where keys are the same
		if (num == 0)
			return value;

		// if the key is less than the current key, search left
		else if (num < 0)
			return left.getValueByKey(keyToLookUp);

		// otherwise search right
		else
			return right.getValueByKey(keyToLookUp);
	}

	/**
	 * 3.4) This method recursively returns the largest key in a binary tree
	 * 
	 * @return the largest key
	 */
	public K largestKey() throws EmptyPBSTException {
		try {
			return right.largestKey();
		} catch (EmptyPBSTException e) {
			return key;
		}
	}

	/**
	 * 3.5) This method recursively returns the smallest key in a binary tree
	 * 
	 * @return the smallest key
	 */
	public K smallestKey() throws EmptyPBSTException {
		try {
			return left.smallestKey();
		} catch (EmptyPBSTException e) {
			return key;
		}
	}

	/**
	 * 3.6) This method returns an integer representing how many left branches
	 * and right branches must be taken from the root to find the keyToFind. A
	 * left branch taken means 1 will be subtracted from the result and a right
	 * branch taken means 1 added to the result.
	 * 
	 * @param K keyToFind
	 * @return int result
	 */
	public int pathBalance(K keyToFind) {

		// check if argument is null
		if (keyToFind == null)
			throw new IllegalArgumentException("Argument cannot be null.");

		int num = keyToFind.compareTo(key);

		// base case is when you've found the object with the specified key
		if (num == 0)
			return 0;

		// if a right branch is taken, add 1 and then make the recursive call
		else if (num > 0)
			return 1 + right.pathBalance(keyToFind);

		// if a left branch is taken, subtract 1 and then make the recursive
		// call
		else
			return -1 + left.pathBalance(keyToFind);
	}

	/**
	 * 3.7) This method returns an ArrayList containing all of the keys within a
	 * PBST in an Inorder Traversal order. This means that the keys are stored
	 * in the ArrayList in increasing order.
	 * 
	 * @return ArrayList<K> list
	 */
	public Collection<K> collectionOfKeys() {

		// declare and initialize a new array list
		Collection<K> list = new ArrayList<>();

		// take the left path to find the smallest key in the tree
		list = left.collectionOfKeys();

		// add this key
		list.add(key);

		// add all of the keys in the right of this parent. note that if there
		// are no keys to the right, nothing will be added, and the method will
		// go back up the PBST
		for (K k : right.collectionOfKeys()) {
			list.add(k);
		}

		return list;
	}

	/**
	 * 3.8) This method removes a key value pair specified by the key parameter
	 * keyToRemove using the algorithm discussed in class. If the parameter
	 * keyToRemove is null, then an exception is thrown.
	 * 
	 * @param K keyToRemove
	 * @return the current object PBST<K, V>
	 */
	public PBST<K, V> removeKeyValuePair(K keyToRemove) {

		// check if argument is null
		if (keyToRemove == null)
			throw new IllegalArgumentException("Argument cannot be null.");

		// compare key and keyToRemove and store this for simplicity
		int num = keyToRemove.compareTo(key);

		// based on the compareTo value, search left or right
		if (num < 0)
			left = left.removeKeyValuePair(keyToRemove);
		else if (num > 0)
			right = right.removeKeyValuePair(keyToRemove);

		// if neither above conditions are true, then we have found the
		// keyToRemove
		else {

			// replace the key and value of this object with the key and value
			// of the largest object in the left subtree
			try {
				key = left.largestKey();
				value = left.getValueByKey(key);
				left = left.removeKeyValuePair(key);
			}

			// in the case where there is no left subtree
			catch (EmptyPBSTException e) {

				// replace the key and value of this object with the key and
				// value of the smallest object in the right subtree
				try {
					key = right.smallestKey();
					value = right.getValueByKey(key);
					right = right.removeKeyValuePair(key);
				}

				// if this object is a leaf, then simply return the EmptyPBST
				// object
				catch (EmptyPBSTException p) {
					return EmptyPBST.getInstance();
				}
			}
		}
		return this;
	}

	/**
	 * 5.1) This method returns a string representation of Key/Value pairs in
	 * increasing order.
	 * 
	 * @String res
	 */
	public String toString() {
		String res = "";

		// go all the way down the left of the tree
		res += left.toString();

		// now cover the parent object
		res += key + "/" + value + " ";

		// go all the way down the right side of the tree
		res += right.toString();

		return res;
	}
}