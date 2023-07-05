package objectList;

// (c) Larry Herman, 2022.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ObjectList implements Iterable<Object> {

	private static class Node {
		private Object data;
		private Node next;
		private Node prev;
	}

	private Node head;
	private Node tail;
	private int numObjects;

	public ObjectList() {
		head = tail = null;
		numObjects = 0;
	}

	// appends newObject to the doubly-linked list of objects being stored,
	// but just does nothing if newObject is null
	public void addObject(Object newObject) {
		Node newNode;

		if (newObject != null) {
			newNode = new Node();
			newNode.data = newObject;

			newNode.prev = tail;
			if (tail != null)
				tail.next = newNode;
			else
				head = newNode; // if the list is currently empty

			tail = newNode;

			numObjects++;
		}
	}

	// return the number of objects in the current object list
	public int size() {
		return numObjects;
	}

	// for use in debugging
	@Override
	public String toString() {
		String result = "";
		Node current = head;

		while (current != null) {
			result += (current != head ? " " : "") + current.data;
			current = current.next;
		}

		return result;
	}

	// for use in debugging and in one test
	public String reverseToString() {
		String result = "";
		Node current = tail;

		while (current != null) {
			result = current.data + (current != tail ? " " : "") + result;
			current = current.prev;
		}

		return result;
	}

	public Iterator<Object> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Object> {

		private int pos;
		private int size;
		private Node curr;
		private Node remove;
		private boolean removed;

		public ListIterator() {
			pos = 0;
			size = numObjects;
			curr = head;
			remove = head;
			removed = true;
		}

		public boolean hasNext() {
			return pos < size;
		}

		public Object next() throws NoSuchElementException {

			if (!hasNext())
				throw new NoSuchElementException("No elements left.");

			pos++;
			remove = curr;
			curr = curr.next;
			removed = false;

			return remove.data;
		}

		public void remove() throws IllegalStateException {

			if (removed || remove == curr)
				throw new IllegalStateException();

			if (head != null && remove != null) {

				// edge case - first node is the one that needs to be removed
				if (pos == 0) {
					head = head.next;
				}

				// general case
				if (remove.prev != null && remove.next != null) {
					remove.prev.next = remove.next;
					remove.next.prev = remove.prev;
				}

				// edge case - last node is the one that needs to be removed
				if (pos == size() - 1) {
					tail = tail.prev;
				}

				removed = true;
				numObjects--;
			}
		}
	}

}
