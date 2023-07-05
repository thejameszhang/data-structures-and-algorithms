package tests;

// (c) Larry Herman, 2022.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import bag.UMBag;
import courseList.UMCourseList;
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

  // Tests calling contains() on elements that are in a bag and on one that
  // is not.
  @Test public void testPublic1() {
    UMBag bag= new UMBag();

    bag.add(131);
    bag.add(132);
    bag.add(250);
    
    assertTrue(bag.contains(131));
    assertTrue(bag.contains(132));
    assertFalse(bag.contains(130));
  }

  // Tests calling size() on a small bag with a few elements, having only
  // one occurrence of each element.
  @Test public void testPublic2() {
    UMBag bag= new UMBag();

    bag.add(131);
    bag.add(132);

    assertEquals(2, bag.size());
  }

  // Tests calling size() on a small bag with a few elements, having more
  // than one occurrence of one element.
  @Test public void testPublic3() {
    UMBag bag= new UMBag();

    bag.add(131);
    bag.add(132);
    bag.add(132);

    assertTrue(bag.contains(131));
    assertTrue(bag.contains(132));
    assertFalse(bag.contains(130));

    assertEquals(3, bag.size());
  }

  // Tests calling getCount() on a small bag with a few elements, where one
  // element has one occurrence and one has than one occurrence.
  @Test public void testPublic4() {
    UMBag bag= new UMBag();

    bag.add(131);
    bag.add(132);
    bag.add(132);

    assertEquals(1, bag.getCount(131));
    assertEquals(2, bag.getCount(132));
  }

  // Tests calling getCount() on an element that is not present in a bag.
  @Test public void testPublic5() {
    UMBag bag= exampleUMBag();

    assertEquals(0, bag.getCount(10));
  }

  // Tests calling numUniqueElements() on a small bag with a few elements,
  // where each just has one occurrence.
  @Test public void testPublic6() {
    UMBag bag= new UMBag();
    Set<Integer> elements;
    int[] expectedElements= {131, 132, 216, 250, 320, 330, 351};

    bag.add(250);
    bag.add(131);
    bag.add(132);
    bag.add(351);
    bag.add(216);
    bag.add(330);
    bag.add(320);

    elements= bag.uniqueElements();

    assertEquals(7, elements.size());

    for (int num : expectedElements)
      assertTrue(elements.contains(num));
  }

  // Tests calling numUniqueElements() on a small bag with a few elements,
  // where each has more than one occurrence.
  @Test public void testPublic7() {
    UMBag bag= new UMBag();
    Set<Integer> elements;

    bag.add(131);
    bag.add(131);
    bag.add(132);
    bag.add(132);
    bag.add(132);

    elements= bag.uniqueElements();

    assertEquals(2, elements.size());
    assertTrue(elements.contains(131));
    assertTrue(elements.contains(132));
  }

  // Tests that uniqueElements() returns a set of elements that is
  // independent of its current object bag.
  @Test public void testPublic8() {
    UMBag bag= exampleUMBag();
    Set<Integer> elements= bag.uniqueElements();

    elements.remove(2);
    // ensure that removing 2 from elements didn't affect the bag
    assertTrue(bag.contains(2));
    assertEquals(1, bag.getCount(2));

    bag.remove(6);
    // ensure that removing 6 from the bag didn't affect elements
    assertTrue(elements.contains(6));
  }

  // Tests calling remove() on a bag that has some elements with one
  // occurrence and some with multiple occurrences, to remove one but not
  // all occurrences of some elements.
  @Test public void testPublic9() {
    UMBag bag= exampleUMBag();
    // array element 0 is the number of occurrences of 1 in the example bag,
    // element 1 is the numebr of occurrences of 2 in the example bag, etc.
    int[] numOcurrences= {1, 1, 2, 1, 3, 1, 1, 1, 3};
    int i;

    bag.remove(1);
    bag.remove(3);

    for (i= 1; i <= 9; i++)
      assertEquals(numOcurrences[i - 1], bag.getCount(i));
  }

  // Tests calling remove() on a bag that has some elements with one
  // occurrence and some with multiple occurrences, to remove all
  // occurrences of one element.
  @Test public void testPublic10() {
    UMBag bag= exampleUMBag();

    bag.remove(5);
    bag.remove(5);
    bag.remove(5);

    assertFalse(bag.contains(5));
  }

  // Tests calling remove() on a bag that has some elements with one
  // occurrence and some with multiple occurrences, to remove all
  // occurrences of all elements.
  @Test public void testPublic11() {
    UMBag bag= exampleUMBag();
    int[] elements= {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9, 3};

    for (int j: elements)
      bag.remove(j);

    for (int k: elements) {
      assertEquals(0, bag.getCount(k));
      assertFalse(bag.contains(k));
    }

    assertEquals(0, bag.size());
  }

  // Tests calling remove() to remove more occurrences of an element than
  // are present in a bag.
  @Test public void testPublic12() {
    UMBag bag= exampleUMBag();
    int[] numOcurrences= {2, 1, 0, 1, 3, 1, 1, 1, 3};
    int i;

    for (i= 1; i <= 5; i++)
      bag.remove(3);

    for (i= 1; i <= 9; i++)
      assertEquals(numOcurrences[i - 1], bag.getCount(i));
  }

  // Tests creating two bags, to ensure that their data does not conflict.
  @Test public void testPublic13() {
    UMBag bag1= new UMBag();
    UMBag bag2= new UMBag();
    int[] bag1NumOccurrences= {1, 0, 2, 0, 3, 1, 0, 0};
    int[] bag2NumOccurrences= {0, 0, 1, 4, 0, 1, 1, 3};
    int i;

    for (int j : new int[]{1, 3, 3, 5, 5, 5, 6})
      bag1.add(j);

    for (int k : new int[]{3, 4, 4, 4, 4, 6, 7, 8, 8, 8})
      bag2.add(k);

    for (i= 0; i < bag1NumOccurrences.length; i++)
      assertEquals(bag1NumOccurrences[i], bag1.getCount(i + 1));

    for (i= 0; i < bag2NumOccurrences.length; i++)
      assertEquals(bag2NumOccurrences[i], bag2.getCount(i + 1));
  }

  // Tests calling isTakingCourse() on an empty UMCourseList that contains no
  // students.
  @Test public void testPublic14() {
    UMCourseList courseList= new UMCourseList();

    assertFalse(courseList.isTakingCourse("Peggy Penguin", "CMSC 321"));
  }

  // Tests calling isTakingCourse() on every student who is taking a course
  // in a UMCourseList.
  @Test public void testPublic15() {
    UMCourseList courseList= exampleUMCourseList();

    assertTrue(courseList.isTakingCourse("Armand Armadillo", "CMSC 567"));
    assertTrue(courseList.isTakingCourse("Armand Armadillo", "CMSC 700"));
    assertTrue(courseList.isTakingCourse("Armand Armadillo", "CMSC 899"));
    assertTrue(courseList.isTakingCourse("Armand Armadillo", "CMSC 800"));

    assertTrue(courseList.isTakingCourse("Ginny Giraffe", "CMSC 456"));
    assertTrue(courseList.isTakingCourse("Ginny Giraffe", "CMSC 567"));
    assertTrue(courseList.isTakingCourse("Ginny Giraffe", "CMSC 799"));

    assertTrue(courseList.isTakingCourse("Wally Walrus", "CMSC 567"));
    assertTrue(courseList.isTakingCourse("Wally Walrus", "CMSC 799"));
    assertTrue(courseList.isTakingCourse("Wally Walrus", "MATH 345"));

    assertTrue(courseList.isTakingCourse("Peggy Penguin", "CMSC 456"));
    assertTrue(courseList.isTakingCourse("Peggy Penguin", "CMSC 567"));
    assertTrue(courseList.isTakingCourse("Peggy Penguin", "CMSC 799"));
    assertTrue(courseList.isTakingCourse("Peggy Penguin", "CMSC 899"));
  }

  // Tests calling isTakingCourse() to ensure that studnets are only taking
  // the courses that they actually signed up for.
  @Test public void testPublic16() {
    UMCourseList courseList= exampleUMCourseList();

    assertFalse(courseList.isTakingCourse("Armand Armadillo", "CMSC 456"));
    assertFalse(courseList.isTakingCourse("Armand Armadillo", "CMSC 799"));
    assertFalse(courseList.isTakingCourse("Armand Armadillo", "MATH 345"));

    assertFalse(courseList.isTakingCourse("Ginny Giraffe", "CMSC 700"));
    assertFalse(courseList.isTakingCourse("Ginny Giraffe", "CMSC 800"));
    assertFalse(courseList.isTakingCourse("Ginny Giraffe", "CMSC 899"));
    assertFalse(courseList.isTakingCourse("Ginny Giraffe", "MATH 345"));

    assertFalse(courseList.isTakingCourse("Wally Walrus", "CMSC 456"));
    assertFalse(courseList.isTakingCourse("Wally Walrus", "CMSC 700"));
    assertFalse(courseList.isTakingCourse("Wally Walrus", "CMSC 800"));
    assertFalse(courseList.isTakingCourse("Wally Walrus", "CMSC 899"));

    assertFalse(courseList.isTakingCourse("Peggy Penguin", "CMSC 700"));
    assertFalse(courseList.isTakingCourse("Peggy Penguin", "CMSC 800"));
    assertFalse(courseList.isTakingCourse("Peggy Penguin", "MATH 899"));
    assertFalse(courseList.isTakingCourse("Peggy Penguin", "MATH 345"));
  }

  // Tests calling numEnrolled() on an empty UMCourseList that contains no
  // students.
  @Test public void testPublic17() {
    UMCourseList courseList= new UMCourseList();

    assertEquals(0, courseList.numEnrolled("CMSC 123"));
  }

  // Tests calling numCoursesTaking() on students taking courses in a
  // UMCourseList that contains several students.
  @Test public void testPublic18() {
    UMCourseList courseList= exampleUMCourseList();

    assertEquals(4, courseList.numCoursesTaking("Armand Armadillo"));
    assertEquals(3, courseList.numCoursesTaking("Ginny Giraffe"));
    assertEquals(3, courseList.numCoursesTaking("Wally Walrus"));
    assertEquals(4, courseList.numCoursesTaking("Peggy Penguin"));
  }

  // Tests calling numCoursesTaking() on a student who is not taking any
  // courses in a UMCourseList.
  @Test public void testPublic19() {
    UMCourseList courseList= exampleUMCourseList();

    assertEquals(0, courseList.numCoursesTaking("Otto Otter"));
  }

  // Tests calling numEnrolled() on an unpopular course that no students are
  // taking (obviously not one that is taught by Larry Herman....).
  @Test public void testPublic20() {
    UMCourseList courseList= exampleUMCourseList();

    assertEquals(0, courseList.numEnrolled("CMSC 123"));
  }

  // Tests calling numEnrolled() on all courses that are being taken by at
  // least one student.
  @Test public void testPublic21() {
    UMCourseList courseList= exampleUMCourseList();

    assertEquals(2, courseList.numEnrolled("CMSC 456"));
    assertEquals(4, courseList.numEnrolled("CMSC 567"));
    assertEquals(1, courseList.numEnrolled("CMSC 700"));
    assertEquals(3, courseList.numEnrolled("CMSC 799"));
    assertEquals(1, courseList.numEnrolled("CMSC 800"));
    assertEquals(2, courseList.numEnrolled("CMSC 899"));
    assertEquals(1, courseList.numEnrolled("MATH 345"));
  }

  // Tests that takeCourse() does not add a student to a course more than
  // once.
  @Test public void testPublic22() {
    UMCourseList courseList= new UMCourseList();

    courseList.takeCourse("Ellie Elephant", "CMSC 799");
    courseList.takeCourse("Armand Armadillo", "CMSC 799");
    courseList.takeCourse("Ellie Elephant", "CMSC 799");

    assertEquals(1, courseList.numCoursesTaking("Ellie Elephant"));
    assertEquals(2, courseList.numEnrolled("CMSC 799"));
  }

  // Tests calling mostPopularCourse() on a UMCourseList that contains several
  // students.
  @Test public void testPublic23() {
    assertEquals("CMSC 567", exampleUMCourseList().mostPopularCourse());
  }

  // Tests calling dropCourse() to remove a student from a course.
  @Test public void testPublic24() {
    UMCourseList courseList= exampleUMCourseList();

    assertTrue(courseList.dropCourse("Ginny Giraffe", "CMSC 567"));
    assertEquals(2, courseList.numCoursesTaking("Ginny Giraffe"));
    assertEquals(3, courseList.numEnrolled("CMSC 567"));
  }

  // Tests calling dropCourse() on a student who is not even taking a
  // course, on a nonexistent student, and on a nonexistent course.
  @Test public void testPublic25() {
    UMCourseList courseList= exampleUMCourseList();

    assertFalse(courseList.dropCourse("Ginny Giraffe", "CMSC 800"));
    assertFalse(courseList.dropCourse("Aaron Aardvark", "CMSC 800"));
    assertFalse(courseList.dropCourse("Ginny Giraffe", "CMSC 666"));
    
    assertEquals(3, courseList.numCoursesTaking("Ginny Giraffe"));
    assertEquals(1, courseList.numEnrolled("CMSC 800"));
  }

  // Tests calling dropCourse() to drop all of a student's courses, after
  // which the student signs up for a new course.  This also tests dropping
  // the last course for a student.
  @Test public void testPublic26() {
    UMCourseList courseList= exampleUMCourseList();

    assertTrue(courseList.dropCourse("Ginny Giraffe", "CMSC 456"));
    assertTrue(courseList.dropCourse("Ginny Giraffe", "CMSC 567"));
    assertTrue(courseList.dropCourse("Ginny Giraffe", "CMSC 799"));

    assertEquals(0, courseList.numCoursesTaking("Ginny Giraffe"));

    courseList.takeCourse("Ginny Giraffe", "CMSC 732");
    
    assertEquals(1, courseList.numCoursesTaking("Ginny Giraffe"));
    assertEquals(1, courseList.numEnrolled("CMSC 732"));
  }

  // Tests that calling dropCourse() on several students changes the most
  // popular course.
  @Test public void testPublic27() {
    UMCourseList courseList= exampleUMCourseList();

    courseList.dropCourse("Armand Armadillo", "CMSC 567");
    courseList.dropCourse("Ginny Giraffe", "CMSC 567");
    courseList.dropCourse("Peggy Penguin", "CMSC 567");

    assertEquals("CMSC 799", courseList.mostPopularCourse());
  }

  // Tests calling dropCourse() to remove the only student from a course.
  @Test public void testPublic28() {
    UMCourseList courseList= exampleUMCourseList();

    // no one wants to take CMSC 700 because it's being taught by Professor
    // Badteacher
    courseList.dropCourse("Armand Armadillo", "CMSC 700");

    assertEquals(0, courseList.numEnrolled("CMSC 700"));
    assertEquals(3, courseList.numCoursesTaking("Armand Armadillo"));
  }

  // Tests calling mostPopularCourse() on an empty UMCourseList that contains
  // no students.
  @Test public void testPublic29() {
    assertNull(new UMCourseList().mostPopularCourse());
  }

  // Tests creating two UMCourseList objects, to ensure that their data does
  // not conflict.
  @Test public void testPublic30() {
    UMCourseList courseList= exampleUMCourseList();
    UMCourseList courseList2= new UMCourseList();

    courseList2.takeCourse("Zelda Zebra", "CMSC 777");
    courseList2.takeCourse("Zelda Zebra", "CMSC 345");

    assertEquals(4, courseList.numCoursesTaking("Armand Armadillo"));
    assertEquals(3, courseList.numCoursesTaking("Ginny Giraffe"));
    assertEquals(3, courseList.numCoursesTaking("Wally Walrus"));
    assertEquals(4, courseList.numCoursesTaking("Peggy Penguin"));

    assertEquals(2, courseList.numEnrolled("CMSC 456"));
    assertEquals(4, courseList.numEnrolled("CMSC 567"));
    assertEquals(1, courseList.numEnrolled("CMSC 700"));
    assertEquals(3, courseList.numEnrolled("CMSC 799"));
    assertEquals(1, courseList.numEnrolled("CMSC 800"));
    assertEquals(2, courseList.numEnrolled("CMSC 899"));
    assertEquals(1, courseList.numEnrolled("MATH 345"));

    assertEquals(2, courseList2.numCoursesTaking("Zelda Zebra"));
    assertEquals(1, courseList2.numEnrolled("CMSC 777"));
    assertEquals(1, courseList2.numEnrolled("CMSC 345"));
  }

  // Tests calling all of the methods on null parameters, to ensure that
  // they have no effect in that case.
  @Test public void testPublic31() {
    UMCourseList courseList= exampleUMCourseList();

    try {
      courseList.takeCourse(null, null);
      // if we reach here- meaning if the expected exception is not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException iae) {
      // if we get here the expected exception was thrown, so continue to the
      // next thing we want to test
    }

    try {
      courseList.isTakingCourse(null, null);
      // if we reach here- meaning if the expected exception is not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException iae) {
      // if we get here the expected exception was thrown, so continue to the
      // next thing we want to test
    }

    try {
      courseList.numCoursesTaking(null);
      // if we reach here- meaning if the expected exception is not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException iae) {
      // if we get here the expected exception was thrown, so continue to the
      // next thing we want to test
    }

    try {
      courseList.numEnrolled(null);
      // if we reach here- meaning if the expected exception is not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException iae) {
      // if we get here the expected exception was thrown, so continue to the
      // next thing we want to test
    }

    try {
      courseList.dropCourse(null, null);
      // if we reach here- meaning if the expected exception is not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException iae) {
      // if we get here the expected exception was thrown, so continue to the
      // next thing we want to test
    }

    // now check that the data is still as it should be, namely unaffected
    // by all the bogus calls above
    assertEquals(2, courseList.numEnrolled("CMSC 456"));
    assertEquals(4, courseList.numEnrolled("CMSC 567"));
    assertEquals(1, courseList.numEnrolled("CMSC 700"));
    assertEquals(3, courseList.numEnrolled("CMSC 799"));
    assertEquals(1, courseList.numEnrolled("CMSC 800"));
    assertEquals(2, courseList.numEnrolled("CMSC 899"));
    assertEquals(1, courseList.numEnrolled("MATH 345"));

    assertEquals(4, courseList.numCoursesTaking("Armand Armadillo"));
    assertEquals(3, courseList.numCoursesTaking("Ginny Giraffe"));
    assertEquals(3, courseList.numCoursesTaking("Wally Walrus"));
    assertEquals(4, courseList.numCoursesTaking("Peggy Penguin"));
  }

  // private utility methods ////////////////////////////////////////////

  public UMBag exampleUMBag() {
    UMBag b= new UMBag();

    for (int j: new int[] {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9, 3})
      b.add(j);

    return b;
  }

  public UMCourseList exampleUMCourseList() {
    UMCourseList courseList= new UMCourseList();

    courseList.takeCourse("Ginny Giraffe", "CMSC 799");
    courseList.takeCourse("Armand Armadillo", "CMSC 899");
    courseList.takeCourse("Peggy Penguin", "CMSC 456");
    courseList.takeCourse("Wally Walrus", "MATH 345");
    courseList.takeCourse("Wally Walrus", "CMSC 567");
    courseList.takeCourse("Ginny Giraffe", "CMSC 456");
    courseList.takeCourse("Armand Armadillo", "CMSC 567");
    courseList.takeCourse("Peggy Penguin", "CMSC 567");
    courseList.takeCourse("Armand Armadillo", "CMSC 700");
    courseList.takeCourse("Armand Armadillo", "CMSC 800");
    courseList.takeCourse("Ginny Giraffe", "CMSC 567");
    courseList.takeCourse("Peggy Penguin", "CMSC 799");
    courseList.takeCourse("Wally Walrus", "CMSC 799");
    courseList.takeCourse("Peggy Penguin", "CMSC 899");

    return courseList;
  }

}
