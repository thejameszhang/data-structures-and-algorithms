package tests;

// (c) Larry Herman, 2022.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import objectList.ObjectList;

/* This class contains utility methods that create and return example
 * ObjectList objects that the public (and secret) tests can use, to reduce
 * the amount of code needed in different tests to create objects to test
 * the methods with.  You can use these methods in your student tests as
 * well, but don't modify this file, because our version is going to be used
 * on the submit server.  (You can write your own helper methods in your
 * StudentTests class, and you can add your own classes to the tests
 * package, if you want to use modified versions of these methods.)
 */

public class TestData {

  // This is public so the public tests can use it, which some of them do.
  // They make copies of it though (using Arrays.copyOf()), because some
  // tests remove elements, and we need the array to be unmodified in later
  // tests.
  public final static Object[] objArr= {
    "pachyderm",  // the objects are listed one per line for clarity
    Integer.valueOf(132),
    Integer.valueOf(1),
    Integer.valueOf(3),
    Integer.valueOf(2),
    Double.valueOf(13.2),
    Boolean.valueOf(true),
    Character.valueOf('1'),
    Character.valueOf('3'),
    Character.valueOf('2'),
    "I love CMSC 132!",
    "I love elephants too!!"
  };

  // Returns a sample ObjectList that is storing eleven Objects (the ones in the
  // array above).  Since the ObjectList addObject() method adds each object to
  // the beginning of a linked list of objects, the objects will be stored in
  // the list in reverse order of how they appear in the array above.
  public static ObjectList exampleObjectList() {
    ObjectList list= new ObjectList();

    for (Object object : objArr)
      list.addObject(object);

    return list;
  }

  // Searches for an object in an unordered array of objects, returning true
  // if and only if it is found; also removes the object from the array (so
  // we can test that an iterator is returning each value only once).
  public static boolean findObject(Object[] objArray, Object anObject) {
    int i= 0;
    boolean found= false;

    while (i < objArray.length && !found) {
      if (objArray[i] != null)
        found= objArray[i].equals(anObject);
      i++;
    }

    if (found) {
      while (i <= objArray.length - 1) {
        // note: i was already incremented one past the object, if found
        objArray[i - 1]= objArray[i];
        i++;
      }

      objArray[i - 1]= null;  // really remove the last element
    }

    return found;
  }

}
