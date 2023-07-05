/**
 * @author: James Zhang
 * @directoryID: jzhang72
 * @uid: 118843940
 * @discussionNumber: 0107
 * I pledge on my honor that I have not given or received any unauthorized 
 * assistance on this assignment.
 */

/**
 * This class just contains some basic recursive methods.
 */

package pbst;

// (c) Larry Herman, 2022.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import java.util.Collection;

abstract public class PBST<K extends Comparable<K>, V> {

  abstract public NonemptyPBST<K, V> addKeyValuePair(K newKey, V newValue);
  abstract public int numPairs();
  abstract public V getValueByKey(K keyToLookUp);
  abstract public K largestKey() throws EmptyPBSTException;
  abstract public K smallestKey() throws EmptyPBSTException;
  abstract public int pathBalance(K keyToFind);
  abstract public Collection<K> collectionOfKeys();
  abstract public PBST<K, V> removeKeyValuePair(K keyToRemove);

}
