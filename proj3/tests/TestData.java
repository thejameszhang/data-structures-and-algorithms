package tests;

// (c) Larry Herman, 2022.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import listVersions.NormalLinkedList;
import listVersions.InorderLinkedList;
import java.util.List;
import java.util.Arrays;

/* This class contains utility methods that create and return example
 * NormalLinkedList and InorderLinkedList objects that the public
 * (and secret) tests can use, to reduce the amount of code needed in
 * different tests to create objects to test the methods with.  You can use
 * these methods in your student tests as well, but don't modify this file,
 * because our version is going to be used on the submit server.  (You can
 * either write your own helper methods in your StudentTests class, and you
 * can add your own classes to the tests package, if you want to use
 * modified versions of these methods.)
 *
 * Your student tests themselves must be your own individual work- you can
 * use ideas from the public the public tests, but you cannot just copy the
 * public tests to create your student tests.  However, you CAN use the
 * methods in THIS class in writing your own student tests, without any
 * restrictions, meaning your student tests can call any of the methods
 * below to create Account objects for testing purposes.  However, don't
 * modify this TestData class, because our version is going to be used on
 * the submit server.  (If you want to have a modified version of any of the
 * methods here note that you can write your own helper methods in your
 * StudentTests class, and you can also add your own classes to the tests
 * package, so instead of changing the methods here, you can make a copy of
 * them in one of these two places and modify that copy.)
 *
 * Although this wasn't covered in lecture, this class contains generic
 * methods, even though it's not a generic class.
 */

public class TestData {

  // Adds all values of a list of any type (any type that implements
  // Comparable) to a NormalLinkedList of the same type and returns it, for
  // use in creating lists for testing the methods.
  public static <T extends Comparable<T>>
                NormalLinkedList<T> makeNormalLinkedList(List<T> eltList) {
    NormalLinkedList<T> list= new NormalLinkedList<>();

    if (eltList != null)
      for (T elt : eltList)
        list.addNewEltToList(elt);

    return list;
  }

  // Adds all values of a list of any type (any type that implements
  // Comparable) to an InorderLinkedList of the same type and returns it,
  // for use in creating lists for testing the methods.
  public static <T extends Comparable<T>>
                InorderLinkedList<T> makeInorderList(List<T> eltList) {
    InorderLinkedList<T> list= new InorderLinkedList<>();

    if (eltList != null)
      for (T elt : eltList)
        list.addNewEltToList(elt);

    return list;
  }

  // returns a NormalLinkedList with Integer elements
  public static NormalLinkedList<Integer> list1() {
    return makeNormalLinkedList(Arrays.asList(52, 66, 10, 92, 81, 89,
                                              64, 33, 34, 92,  9, 58,
                                              96, 92, 92, 35, 24, 96));
  }

  // returns a NormalLinkedList with Character elements
  public static NormalLinkedList<Character> list2() {
    return makeNormalLinkedList(Arrays.asList('i', 'l', 'o', 'v', 'e',
                                              'e', 'l', 'e', 'p', 'h',
                                              'a', 'n', 't', 's'));
  }

  // returns a NormalLinkedList with String elements
  public static NormalLinkedList<String> list3() {
    return makeNormalLinkedList(Arrays.asList("pig", "eel", "emu","yak",
                                              "cat", "rat", "dog",
                                              "owl", "fox", "cow"));
  }

  // returns an InorderList with Integer elements; note that it returns a
  // superclass reference referring to a subclass object
  public static NormalLinkedList<Integer> list4() {
    return makeInorderList(Arrays.asList(52, 66, 10, 92, 81, 89, 
                                         64, 33, 34, 92, 9, 58,
                                         96, 92, 92, 35, 24, 96));
  }

}
