package tests;

// (c) Larry Herman, 2022.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

/* This class contains utility methods that create and return example
 * NonEmptyPolyPBST objects that the public (and secret) tests
 * can use, to reduce the amount of code needed in different tests to create
 * objects to test the methods with.  You can use these methods in your
 * student tests as well, but don't modify this file, because our version is
 * going to be used on the submit server.  (You can either write your own
 * helper methods in your StudentTests class, and you can add your own
 * classes to the tests package, if you want to use modified versions of
 * these methods.)
 */

import pbst.PBST;
import pbst.EmptyPBST;
import java.util.List;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class TestData {

  // utility methods ////////////////////////////////////////////////////

  @SuppressWarnings("unchecked")
  public static <T extends Comparable<T>, U> PBST<T, U>
                createPBST(T[] keyArr, U[] valueArr) {
    PBST<T, U> tree= EmptyPBST.getInstance();
    int smallestKey, i;

    if (keyArr != null && valueArr != null) {
      // if the arrays are for some reason of different sizes just iterate
      // over the number of elements in the smaller one
      smallestKey= keyArr.length < valueArr.length ? keyArr.length :
                                                     valueArr.length;
      for (i= 0; i < smallestKey; i++)
        tree= tree.addKeyValuePair(keyArr[i], valueArr[i]);
    }

    return tree;
  }

  // Adds all elements of a list of keys (of any type) and a list of values
  // (of any type) to a tree, and returns it.  The lists should be the same
  // size, but we handle things gracefully if they're not.
  @SuppressWarnings("unchecked")
  public static <T extends Comparable<T>, U> PBST<T, U>
                createPBST(List<T> keyList, List<U> valueList) {
    PBST<T, U> tree= EmptyPBST.getInstance();
    Iterator<T> keyIter;
    Iterator<U> valueIter;

    if (keyList != null && valueList != null) {
      keyIter= keyList.iterator();
      valueIter= valueList.iterator();

      while (keyIter.hasNext() && valueIter.hasNext())
        tree= tree.addKeyValuePair(keyIter.next(), valueIter.next());
    }

    return tree;
  }

  // Some tests must check the contents of a Collection returned by a
  // method, but we can't create a Collection that has the expected values
  // and use the equals() method to compare against the Collection, because
  // we don't even know what kind of Collection the methods will return.
  // This method takes a Collection to check and another Collection with the
  // expected values (this Collection will actually be some type of List).
  // Then it uses the Collection containsAll() method to compare the two
  // parameter Collections.  If we have two collections A and B, and A
  // contains all of the elements of B, and B contains all of the elements
  // of A, then it must be the case that they must have all the same
  // elements, and only the same elements.  Of course this is not
  // particularly efficient, but our goal is just to make it easy to check
  // the results of tests.
  public static <T> boolean checkCollection(Collection<T> collection,
                                            Collection<T> expectedResults) {
    return collection.containsAll(expectedResults) &&
           expectedResults.containsAll(collection);
  }

  // Returns a small tree with several elements, with Integer keys and
  // Character values.
  public static PBST<Integer, Character> sampleTree1() {
    return createPBST(Arrays.asList(7, 5, 13, 1, 6, 11, 17),
                      Arrays.asList('p', 'o', 'i', 'd', 'l', 'h', 'n'));
  }

  // Returns a small tree with several elements, with Integer keys and
  // Character values.
  public static PBST<Integer, Character> sampleTree2() {
    return createPBST(Arrays.asList(7, 13, 11, 5, 1, 17, 3, 2),
                      Arrays.asList('h', 'n', 'a', 'p', 'e', 't',
                                    'e', 'l'));
  }

  // Returns a slightly larger tree with Character keys and Character
  // values.
  public static PBST<Character, Character> sampleTree3() {
    return createPBST(Arrays.asList('e', 'b', 'l', 'c', 'g', 'd', 'k',
                                    'f', 'j', 'n', 'i', 'a', 'm', 'h'),
                      Arrays.asList('l', 'a', 'a', 'v', 'v', 'a', 's',
                                    'o', 'u', 'l', 's', 'j', 'l', 'e'));
  }

}
