package tests;

// (c) Larry Herman, 2022.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import objectList.ObjectList;
import java.util.Iterator;
import java.util.Arrays;
import java.util.NoSuchElementException;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

	// Checks the value of calling hasNext() on a new iterator over a nonempty
	// ObjectList, which should be true.
	@Test
	public void testPublic1() {
		ObjectList list = TestData.exampleObjectList();
		Iterator<Object> iter = list.iterator();

		assertTrue(iter.hasNext());
	}

	// Checks that when hasNext() is called on an iterator for an ObjectList
	// with several elements, it still returns true after the first call to
	// next().
	@Test
	public void testPublic2() {
		ObjectList list = TestData.exampleObjectList();
		Iterator<Object> iter = list.iterator();

		iter.next(); // we just ignore the object returned

		assertTrue(iter.hasNext());
	}

	// Checks the values returned by multiple iterations (meaning calling
	// next() multiple times) on an iterator. Note that there is no
	// requirement that an iterator return the elements of an object in any
	// particular order, so we are checking that the iterator next() method
	// returns the elements in the list, but we don't know what order they
	// will be returned in.
	@Test
	public void testPublic3() {
		ObjectList list = TestData.exampleObjectList();
		Iterator<Object> iter = list.iterator();
		Object[] expectedObjects = Arrays.copyOf(TestData.objArr,
				TestData.objArr.length);

		int i;

		for (i = 0; i < expectedObjects.length; i++)
			// findObject() is calling the Object equals() method
			assertTrue(TestData.findObject(expectedObjects, iter.next()));
	}

	// Checks that hasNext() can be called multiple times and returns the
	// right value each time.
	@Test
	public void testPublic4() {
		ObjectList list = TestData.exampleObjectList();
		Iterator<Object> iter = list.iterator();

		assertTrue(iter.hasNext());
		assertTrue(iter.hasNext());
		assertTrue(iter.hasNext());

		while (iter.hasNext())
			iter.next();

		assertFalse(iter.hasNext());
		assertFalse(iter.hasNext());
		assertFalse(iter.hasNext());
	}

	// Checks the value of hasNext() on a new iterator over an empty ObjectList,
	// which should be false.
	@Test
	public void testPublic5() {
		ObjectList list = new ObjectList();
		Iterator<Object> iter = list.iterator();

		assertFalse(iter.hasNext());
	}

	// Checks the values returned by multiple calls to next() on an ObjectList
	// iterator, until hasNext() returns false, which is also testing that
	// hasNext() properly returns false when it should, and the iterator
	// doesn't have any errors in this case.
	@Test
	public void testPublic6() {
		ObjectList list = TestData.exampleObjectList();
		Iterator<Object> iter = list.iterator();
		Object[] expectedObjects = Arrays.copyOf(TestData.objArr,
				TestData.objArr.length);

		// findObject() is calling the Object equals() method
		while (iter.hasNext())
			assertTrue(TestData.findObject(expectedObjects, iter.next()));
	}

	// Verifies that an ObjectList iterator's next() method properly throws a
	// NoSuchElementException when next() is called more times than the number
	// of Objects in an ObjectList.
	@Test(expected = NoSuchElementException.class)
	public void testPublic7() {
		ObjectList list = TestData.exampleObjectList();
		Iterator<Object> iter = list.iterator();

		while (true)
			iter.next();
	}

	// Tests that a new iterator can iterate over an ObjectList object after
	// one iterator has already iterated over it.
	@Test
	public void testPublic8() {
		ObjectList list = TestData.exampleObjectList();
		Iterator<Object> iter = list.iterator();
		Object[] expectedObjects = Arrays.copyOf(TestData.objArr,
				TestData.objArr.length);
		int count = 0;

		// findObject() is calling the Object equals() method
		while (iter.hasNext()) {
			assertTrue(TestData.findObject(expectedObjects, iter.next()));
			count++;
		}
		assertEquals(TestData.objArr.length, count);

		// now do the same thing with a new iterator
		count = 0;
		iter = list.iterator();

		// reinitialize the array
		expectedObjects = Arrays.copyOf(TestData.objArr,
				TestData.objArr.length);
		// findObject() is calling the Object equals() method
		while (iter.hasNext()) {
			assertTrue(TestData.findObject(expectedObjects, iter.next()));
			count++;
		}
		assertEquals(TestData.objArr.length, count);
	}

	// Tests that two iterators can simultaneously iterate over an ObjectList
	// object.
	@Test
	public void testPublic9() {
		ObjectList list = TestData.exampleObjectList();
		Iterator<Object> iter1 = list.iterator();
		Iterator<Object> iter2 = null;
		Object[] expectedObjects = Arrays.copyOf(TestData.objArr,
				TestData.objArr.length);
		// make a second copy of the array
		Object[] expectedObjects2 = Arrays.copyOf(TestData.objArr,
				TestData.objArr.length);
		// will count every element returned by both iterators
		int count = 0;

		// continue until the second iterator has returned all of the elements,
		// as well as before it has started
		while (iter2 == null || iter2.hasNext()) {
			if (iter1.hasNext()) {
				assertTrue(TestData.findObject(expectedObjects, iter1.next()));
				count++;
			}

			// start the second iterator when the first iterator has processed a
			// few elements
			if (count == 4)
				iter2 = list.iterator();
			else if (count > 4) {
				assertTrue(TestData.findObject(expectedObjects2, iter2.next()));
				count++;
			}
		}

		assertEquals(2 * TestData.objArr.length, count);
	}

	// Tests that an iterator for an ObjectList works correctly after the
	// ObjectList has been modified by adding new elements. The list is
	// iterated over, elements are added, and the list is iterated over again,
	// to ensure that the iterator is iterating over the list's correct
	// current contents.
	@Test
	public void testPublic10() {
		ObjectList list = TestData.exampleObjectList();
		Iterator<Object> iter = list.iterator();
		Object[] expectedObjects = Arrays.copyOf(TestData.objArr,
				TestData.objArr.length);
		int i;

		for (i = 0; i < expectedObjects.length; i++)
			// findObject() is calling the Object equals() method
			assertTrue(TestData.findObject(expectedObjects, iter.next()));

		// add new elements to the list
		list.addObject(new String("elephant"));
		list.addObject(new String("sheep"));
		list.addObject(new String("penguin"));

		// reinitialize the array used for checking the iterator, and add the
		// new elements to it also
		expectedObjects = Arrays.copyOf(TestData.objArr,
				TestData.objArr.length + 3);
		expectedObjects[expectedObjects.length - 3] = new String("elephant");
		expectedObjects[expectedObjects.length - 2] = new String("sheep");
		expectedObjects[expectedObjects.length - 1] = new String("penguin");

		// now create a new iterator and check the list's contents again
		iter = list.iterator();
		for (i = 0; i < expectedObjects.length; i++)
			// findObject() is calling the Object equals() method
			assertTrue(TestData.findObject(expectedObjects, iter.next()));
	}

	// Tests the basic operation of the ObjectList iterator remove() method to
	// remove just one Object from an ObjectList.
	@Test
	public void testPublic11() {
		ObjectList list = TestData.exampleObjectList();
		Iterator<Object> iter = list.iterator();
		Object[] expectedObjects = Arrays.copyOf(TestData.objArr,
				TestData.objArr.length);
		int i;

		iter.next();
		iter.remove();

		// check the remaining four elements (findObject() is calling the Object
		// equals() method)
		for (i = 1; i <= 4; i++)
			assertTrue(TestData.findObject(expectedObjects, iter.next()));
	}

	// Tests that the right element is removed by calling the iterator
	// remove() method, meaning the one returned by the most recent call to
	// next().
	@Test
	public void testPublic12() {
		ObjectList list = TestData.exampleObjectList();
		Iterator<Object> iter = list.iterator();
		Object[] expectedObjects = Arrays.copyOf(TestData.objArr,
				TestData.objArr.length);
		Object elementToRemove;

		iter.next();
		// save the element that's going to be removed from the list
		elementToRemove = iter.next();
		iter.remove();

		// now remove the element that was removed from the list from
		// expectedObjects, by calling findObject(), then iterate over the list
		// again, making sure that all of the remaining elements are present in
		// it (meaning that the right one was removed)
		TestData.findObject(expectedObjects, elementToRemove);

		// check the elements remaining, using the iterator
		iter = list.iterator();
		while (iter.hasNext())
			assertTrue(TestData.findObject(expectedObjects, iter.next()));
	}

	// Tests that the ObjectList size() method returns the right value after
	// removing elements using the iterator.
	@Test
	public void testPublic13() {
		ObjectList list = TestData.exampleObjectList();
		Iterator<Object> iter = list.iterator();
		int i;

		i = 1;
		while (iter.hasNext()) {
			iter.next();

			if (i % 2 != 0)
				iter.remove();

			i++;
		}

		iter = list.iterator(); // a new iterator that starts at the beginning
		i = 1;
		while (iter.hasNext()) {
			iter.next();

			if (i % 2 == 0)
				iter.remove();

			i++;
		}

		// this test is the same as the previous one up to this point
		assertEquals(3, list.size());
	}

	// Test calling the ObjectList iterator remove() method to remove only
	// some Objects from an ObjectList object (several in the middle).
	@Test
	public void testPublic14() {
		ObjectList list = TestData.exampleObjectList();
		Iterator<Object> iter = list.iterator();
		int i, count = 0, firstToRemove = 4, numToRemove = 6;

		for (i = 1; i <= TestData.objArr.length; i++) {
			iter.next();
			// remove six elements starting with the fourth one
			if (i >= firstToRemove && i - firstToRemove + 1 <= numToRemove)
				iter.remove();
		}

		// now count the elements remaining and ensure that there are the right
		// number; the list should still be valid for a new iterator to work
		iter = list.iterator();
		while (iter.hasNext()) {
			iter.next();
			count++;
		}
		assertEquals(TestData.objArr.length - numToRemove, count);
	}

	// Tests that the ObjectList iterator remove() method throws the expected
	// IllegalStateException if remove() is called before next().
	@Test(expected = IllegalStateException.class)
	public void testPublic15() {
		ObjectList list = TestData.exampleObjectList();
		Iterator<Object> iter = list.iterator();

		iter.remove();
	}

	// Tests that the ObjectList iterator remove() method throws the expected
	// IllegalStateException if remove() has already been called after the
	// last call to next(), so the element has already been removed.
	@Test(expected = IllegalStateException.class)
	public void testPublic16() {
		ObjectList list = TestData.exampleObjectList();
		Iterator<Object> iter = list.iterator();

		iter.next();
		iter.next();

		iter.remove();
		iter.remove();
	}

	// Test calling the ObjectList iterator remove() method to remove only
	// some Objects from an ObjectList object (the first several).
	@Test
	public void testPublic17() {
		ObjectList list = TestData.exampleObjectList();
		Iterator<Object> iter = list.iterator();
		int i, count = 0, numToRemove = 5;

		for (i = 1; i <= TestData.objArr.length; i++) {
			iter.next();
			if (i <= numToRemove)
				iter.remove();
		}

		// now count the elements remaining and ensure that there are the right
		// number; the list should still be valid for a new iterator to work
		iter = list.iterator();
		while (iter.hasNext()) {
			iter.next();
			count++;
		}
		assertEquals(TestData.objArr.length - numToRemove, count);
	}

	// Test calling the ObjectList iterator remove() method to remove only
	// some Objects from an ObjectList object (the last several).
	@Test
	public void testPublic18() {
		ObjectList list = TestData.exampleObjectList();
		Iterator<Object> iter = list.iterator();
		int i, count = 0, numToRemove = 5;

		for (i = 1; i <= TestData.objArr.length; i++) {
			iter.next();
			if (i > TestData.objArr.length - numToRemove)
				iter.remove();
		}

		// now count the elements remaining and ensure that there are the right
		// number; the list should still be valid for a new iterator to work
		iter = list.iterator();
		while (iter.hasNext()) {
			iter.next();
			count++;
		}
		assertEquals(TestData.objArr.length - numToRemove, count);
	}

	// Tests using the ObjectList iterator remove() method to remove all of
	// the Objects from an ObjectList.
	@Test
	public void testPublic19() {
		ObjectList list = TestData.exampleObjectList();
		Iterator<Object> iter = list.iterator();
		Object[] expectedObjects = Arrays.copyOf(TestData.objArr,
				TestData.objArr.length);
		int i;

		for (i = 1; i <= TestData.objArr.length; i++) {
			// findObject() is calling the Object equals() method
			assertTrue(TestData.findObject(expectedObjects, iter.next()));
			iter.remove();
		}

		// ensure everything has been removed
		iter = list.iterator();
		assertFalse(iter.hasNext());
	}

	// Tests that its hasNext() method returns false after all of the Objects
	// have been removed from an ObjectList by calling the remove() iterator
	// method.
	@Test
	public void testPublic20() {
		ObjectList list = TestData.exampleObjectList();
		Iterator<Object> iter = list.iterator();
		Object[] expectedObjects = Arrays.copyOf(TestData.objArr,
				TestData.objArr.length);

		while (iter.hasNext()) {
			assertTrue(TestData.findObject(expectedObjects, iter.next()));
			iter.remove();
		}

		// ensure everything has been removed
		iter = list.iterator();
		assertFalse(iter.hasNext());
	}

	// Tests that the ObjectList iterator next() method throws the expected
	// NoSuchElementException when called after all of the elements have been
	// removed by the iterator remove() method.
	@Test(expected = NoSuchElementException.class)
	public void testPublic21() {
		ObjectList list = TestData.exampleObjectList();
		Iterator<Object> iter = list.iterator();

		while (iter.hasNext()) {
			iter.next();
			iter.remove();
		}

		assertFalse(list.iterator().hasNext());
		// the ObjectList should be empty now, so this should throw the
		// exception
		iter.next();
	}

	// Tests using the ObjectList iterator remove() method to remove every
	// other Object from an ObjectList. First the odd-position elements are
	// removed (meaning the first one returned by the iterator, the third,
	// etc.); then the even-position ones are removed from the remaining ones.
	// Then the ObjectList's contents are checked, and the remaining elements
	// are removed, to ensure that the list of elements remains valid.
	//
	// Since there is no guarantee of the order that elements will be returned
	// by an iterator, we can't be sure exactly which elements will be
	// returned and removed each time. So we start with an array of all of
	// the objects in an example ObjectList and each time an element is
	// returned and from the ObjectList by calling next(), and removed by
	// calling remove(), we remove it from the array. So the array's contents
	// should be the same as the elements in the ObjectList.
	@Test
	public void testPublic22() {
		ObjectList list = TestData.exampleObjectList();
		Iterator<Object> iter = list.iterator();
		Object[] expectedObjects = { "pachyderm", // the objects are listed one
													// per line for clarity
				Integer.valueOf(132), Integer.valueOf(1), Integer.valueOf(3),
				Integer.valueOf(2), Double.valueOf(13.2), Boolean.valueOf(true),
				Character.valueOf('1'), Character.valueOf('3'),
				Character.valueOf('2'), "I love CMSC 132!",
				"I love elephants too!!" };
		Object obj;
		int i;

		i = 1;
		while (iter.hasNext()) {
			obj = iter.next();

			if (i % 2 != 0) {
				iter.remove();
				// findObject() is calling the Object equals() method
				assertTrue(TestData.findObject(expectedObjects, obj));
			}

			i++;
		}

		iter = list.iterator(); // a new iterator that starts at the beginning
		i = 1;
		while (iter.hasNext()) {
			obj = iter.next();

			if (i % 2 == 0) {
				iter.remove();
				// findObject() is calling the Object equals() method
				assertTrue(TestData.findObject(expectedObjects, obj));
			}

			i++;
		}

		// check current contents and remove them in the process
		iter = list.iterator();
		while (iter.hasNext()) {
			// findObject() is calling the Object equals() method
			assertTrue(TestData.findObject(expectedObjects, iter.next()));
			iter.remove();
		}

		// ensure everything has been removed
		iter = list.iterator();
		assertFalse(iter.hasNext());
	}

	// Uses the ObjectList reverseToString() method to verify that the prev
	// references are being properly set when elements are removed while
	// iterating over a list.
	@Test
	public void testPublic23() {
		ObjectList list = new ObjectList();
		Iterator<Object> iter;
		int i = 0;

		// add some objects (Characters) to the list
		for (Character ch : Arrays.asList('p', 'a', 'c', 'h', 'y', 'd', 'e',
				'r', 'm'))
			list.addObject(ch);

		iter = list.iterator();

		// remove only some objects
		while (iter.hasNext()) {
			iter.next();
			if (i >= 2 && i <= 6)
				iter.remove();

			i++;
		}

		// now get the string representation of the list. The reverseToString()
		// method returns a string representation of the elements of an
		// ObjectList from the first one to the last one, but it builds the
		// result by iterating over the list from back to front. So the strings
		// returned by toString() and by reverseToString() should be identical,
		// if the prev references are being properly set.
		assertEquals(list.toString(), list.reverseToString());
	}

}
