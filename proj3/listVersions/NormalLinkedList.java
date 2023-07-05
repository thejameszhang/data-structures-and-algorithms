/**
 * @author: James Zhang
 * @directoryID: jzhang72
 * @uid: 118843940
 * @discussionNumber: 0107
 * I pledge on my honor that I have not given or received any unauthorized 
 * assistance on this assignment.
 */

/*
 * The NormalLinkedList includes important functionality that a normal linked 
 * list should have, such as methods to add a new node to the end of the 
 * linked list, finding the length of the linked list, getting a string
 * representation of the linked list, finding the number of occurrences of a 
 * value in the linked list, and others. Within the class, there is a Node
 * inner class. Furthermore, this class is the superclass of the 
 * InorderLinkedList class, and so all instance fields have protected access.
 */

package listVersions;

import java.lang.IndexOutOfBoundsException;

public class NormalLinkedList<T extends Comparable<T>>
		implements Comparable<NormalLinkedList<T>> {

	// instance fields for the NormalLinkedList class
	// protected access means that other classes in the same package and
	// subclasses of this class will have access to these instance variables
	protected Node head = null;
	protected Node tail = null;
	protected int length = 0;

	/**
	 * This Node inner class is used to help create Nodes within the
	 * NormalLinkedList class
	 * 
	 * @author James Zhang
	 *
	 */
	public class Node {

		// declare instance fields of the Node class
		protected T data;
		protected Node next;

		// constructor that initializes a node's data field
		public Node(T val) {
			data = val;
			next = null;
		}
	}

	/**
	 * 3.1.1) This method should append newValue to the end of its current
	 * object linked list. It should always succeed because there's no limit to
	 * the number of nodes in a linked list except for the number of nodes that
	 * can be stored in memory. Additionally, there's an edge case where if
	 * newValue is null, then an IllegalArgumentException is thrown but not
	 * caught using a catch clause.
	 * 
	 * Also, if the list is empty, then newValue will become its only element.
	 * 
	 * @param newValue
	 */
	public void addNewEltToList(T newValue) {

		// edge case
		if (newValue == null) {
			throw new IllegalArgumentException("newValue cannot be null.");
		}

		Node node = new Node(newValue);

		// if this list is empty, then make newValue its only element
		if (head == null) {

			// since there's only one node, it will be the head and the tail
			head = node;
			tail = node;
		} else {

			// make the tail refer to the new last node, and then make the tail
			// be the new last node
			tail.next = node;
			tail = node;
		}

		// increment length, which equals the number nodes in the linked list
		length++;
	}

	/**
	 * 3.1.2) This method returns the length of the linked list, which is equal
	 * to the number of times that addNewEltToList() has been called
	 * 
	 * @return length of the linked list
	 */
	public int length() {
		return length;
	}

	/**
	 * 3.1.3) This method should return a string representation of the linked
	 * list by calling the Object toString() method on each element. They will
	 * be separated by blank spaces, but there should be no blank spaces before
	 * the first element or after the last element.
	 */
	@Override
	public String toString() {

		// the resulting string that will be returned
		String res = "";
		Node current = head;

		// represents the element in the linked list
		int count = 0;

		// traverse the linked list
		while (current != null) {

			// if it's the last element, then it should not have a space after
			if (count == length - 1) {
				res += current.data.toString();
			}

			// otherwise add this to the resulting string
			else {
				res += current.data.toString() + " ";
			}

			current = current.next;
			count++;
		}
		return res;
	}

	/**
	 * 3.1.4) This method should remove all values from the linked list
	 */
	public void clear() {

		// resetting instance variables to default state, and let the java
		// garbage collector do the work
		head = null;
		tail = null;
		length = 0;
	}

	/**
	 * 3.1.5) This method returns the number of objects stored in the current
	 * object that are the same as the parameter value. compareTo() is used to
	 * determine if two objects are the same.
	 * 
	 * @param value
	 * @return number of objects equal to value in the current object
	 */
	public int occurrencesOfValue(T value) {

		// edge case
		if (value == null) {
			throw new IllegalArgumentException("value cannot be null.");
		}

		// the returned num variable and initializing a current node
		int num = 0;
		Node current = head;

		// traverse the linked list
		while (current != null) {

			// use compareTo() to evaluate if the two objects are the same
			if ((current.data).compareTo(value) == 0) {
				num++;
			}
			current = current.next;
		}
		return num;
	}

	/**
	 * 3.1.6) This method returns the index of the first occurrence of value in
	 * its current objects list. The method returns -1 if it's not present.
	 * 
	 * @param value
	 * @return index of the first occurrence of value
	 */
	public int indexOfValue(T value) {

		// edge case
		if (value == null) {
			throw new IllegalArgumentException("value cannot be null.");
		}

		// the returned index variable and the current Node
		int index = 0;
		Node current = head;

		// traverse the linked list
		while (current != null) {

			// if the two objects are the same, then return the index
			if ((current.data).compareTo(value) == 0) {
				return index;
			}

			// increment index and update current if not found in this iteration
			index++;
			current = current.next;
		}

		// return -1 if value is not found in the current object list
		return -1;
	}

	/**
	 * 3.1.7) This method returns a reference to the value located at the index
	 * position. However, if it is an invalid position, then an
	 * IndexOutOfBoundsException is thrown.
	 * 
	 * @param position
	 * @return
	 */
	public T valueLocatedAtIndex(int position) {

		// edge case where a position is invalid
		if (position < 0 || position > length - 1) {
			throw new IndexOutOfBoundsException("Position is invalid");
		}

		// pos counter and current Node
		int pos = 0;
		Node current = head;

		while (current != null) {
			if (pos == position) {
				return current.data;
			}

			// increment pos and update current if not found in this iteration
			pos++;
			current = current.next;
		}

		// this return statement will never be run, it's just here to make the
		// method happy by returning something of type T
		return head.data;
	}

	/**
	 * 3.1.8) This method should remove all values in its current object list
	 * between the first occurrence of fromValue to the first occurrence of
	 * toValue, inclusive of the endpoints.
	 * 
	 * If there is no occurrence of fromValue, or there's no occurrence of
	 * toValue at or after the first occurrence of fromValue, then the list is
	 * left unchanged.
	 * 
	 * If fromValue and toValue are the same, then the first occurrence of the
	 * value is removed, if there is one
	 * 
	 * @param fromValue
	 * @param toValue
	 */
	public void removeValuesBetween(T fromValue, T toValue) {

		// edge case
		if (fromValue == null || toValue == null) {
			throw new IllegalArgumentException(
					"The parameters cannot be null.");
		}

		// if fromValue and toValue aren't null, then let's make sure that
		// they're actually in the linked list. This will also help us adjust
		// the length of our linked list after elements are removed.
		int fromIndex = indexOfValue(fromValue);
		int toIndex = indexOfValue(toValue);
		length = length - (toIndex - fromIndex + 1);

		// as long as fromIndex and toIndex are not -1, we can remove some
		// elements
		if (fromIndex != -1 && toIndex != -1) {

			// first, let's traverse the linked list and try to find the first
			// occurrence of fromValue, since toValue will have to be at or
			// after fromValue anyway. Note if we want to remove fromValue,
			// by the time we've reached it, we've gone one node too far,
			// thus, we should be checking from.next

			// initialize from node and boolean found
			Node from = head;
			boolean found = false;

			// since we're checking from.next, we have to manually check the
			// head
			if (head.data == fromValue) {
				found = true;
			}

			// traverse the linked list until we've reached the end or we've
			// found
			// fromValue
			while (from != null && !found) {
				if (from.next.data == fromValue) {
					found = true;
				}

				// if we've found fromValue, we don't want to update from
				if (!found) {
					from = from.next;
				}
			}

			// now that we've found the "from" node, we need to find the "to"
			// node
			// but we only want to bother looking for the "to" node if the
			// "from"
			// node was found.

			// the to node and boolean found2
			Node to = from;
			boolean found2 = false;

			// found implies that we found the "from" node
			if (found) {

				// finding the "to" node is different; we want to go one beyond
				// toValue so that we can extend the reference from the "from"
				// node
				// to this node. That way, we remove everything everything in
				// between.
				while (to != null && !found2) {
					if (to.data == toValue) {
						found2 = true;
					}

					// even if we've found the "to" node, we want to go one node
					// beyond it
					to = to.next;
				}
			}

			if (head.data != fromValue) {
				// we already know that we've found fromValue, so this really
				// means if both nodes were found, then we update the reference
				// from the found node to the to node
				if (found2) {
					from.next = to;
				}
			}
			// we have to manually handle the edge case if we must remove the
			// first
			// node because the head of the linked list has to change
			else if (head.data == from.data) {
				head.next = null;
				head = to;
			}
		}
	}

	/**
	 * 3.1.9) This method compares the current object linked list with the
	 * parameter otherList and returns one of the following numbers based on the
	 * comparison. 1) Returns 0 if all of the elements are identical and in the
	 * same order. 2) Returns -1 if for the first nonmatching element, the
	 * element of the current object list is less than the otherList element. 3)
	 * Returns 1 if for the first nonmatching element, the element of the
	 * otherList is greater than the corresponding element of the current object
	 * list. 4) Returns -2 if the lists are the same up until a certain point
	 * such that the current object list is shorter. 5) Returns 2 if the lists
	 * are the same up until a certain point such that the parameter list is
	 * shorter.
	 */
	@Override
	public int compareTo(NormalLinkedList<T> otherList) {

		// edge case
		if (otherList == null) {
			throw new IllegalArgumentException("otherList cannot be null.");
		}

		// initializing the heads of both linked lists
		Node current = head;
		Node other = otherList.head;
		int result = 0;
		boolean conclusionFound = false;

		// traversing and comparing corresponding elements of the linked list
		while (current != null && other != null && !conclusionFound) {

			// situation 2
			if (current.data.compareTo(other.data) < 0) {
				result = -1;
				conclusionFound = true;
			}

			// situation 3
			else if (current.data.compareTo(other.data) > 0) {
				result = 1;
				conclusionFound = true;
			}

			// incrementing the pointers
			current = current.next;
			other = other.next;
		}

		// if it wasn't situation 2 or 3, then check these
		if (!conclusionFound) {

			// if we've traversed all the way through both linked lists, then
			// they are the same -> situation 1
			if (current == null && other == null) {
				result = 0;

				// if current is null, then it must be shorter -> situation 4
			} else if (current == null) {
				result = -2;

				// otherwise, the otherList is shorter -> situation 5
			} else {
				result = 2;
			}
		}
		return result;
	}
}