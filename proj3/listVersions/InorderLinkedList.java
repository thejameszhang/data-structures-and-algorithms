/**
 * @author: James Zhang
 * @directoryID: jzhang72
 * @uid: 118843940
 * @discussionNumber: 0107
 * I pledge on my honor that I have not given or received any unauthorized 
 * assistance on this assignment.
 */

/*
 * The InorderLinkedList class is a subclass of the NormalLinkedList class
 * because an InorderLinkedList is a type of linked list. It inherits much of
 * the functionality from its superclass except for three methods: 
 * addNewEltToList(), occurrencesOfValue(), and indexOfValue(). These methods
 * are overrode in this class because due to the ordered nature of the linked
 * list, these functions can be written more efficiently. 
 */

package listVersions;

import listVersions.NormalLinkedList.Node;

public class InorderLinkedList<T extends Comparable<T>>
		extends NormalLinkedList<T> {

	/**
	 * This method must be overrode due to the ordered nature of the ordered
	 * linked lists. Nodes must be added in the correct order, not just at the
	 * end of a list.
	 */
	@Override
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
		}

		// if the list isn't empty, we must find the correct place to insert the
		// new node
		else {

			// define a current node, prev node, and a boolean inserted
			Node current = head.next;
			Node prev = head;
			boolean inserted = false;

			// traverse the array until we've reached the end or we've found the
			// correct position to insert the new node
			while (current != null && !inserted) {

				// if the current node is less than or equal to newValue and the
				// next node is greater than newValue, then we can insert this
				// node here
				if (current.data.compareTo(newValue) >= 0) {
					inserted = true;
				} else {
					prev = prev.next;
					current = current.next;
				}
			}

			// in the case where the node is smaller than all of the other nodes
			// in the list and head needs to be set to that node
			if (prev == head && prev.data.compareTo(newValue) >= 0) {
				node.next = prev;
				head = node;
			}

			// insert the node into its correct position
			else {
				prev.next = node;
				node.next = current;
			}
		}

		// increment length, which equals the number nodes in the linked list
		length++;
	}

	/**
	 * This method needs to be overrode because in an InorderLinkedList, once we
	 * traverse past the point where the current node data value is greater than
	 * our desired value, we don't need to continue traversing the list
	 */
	@Override
	public int occurrencesOfValue(T value) {
		// edge case
		if (value == null) {
			throw new IllegalArgumentException("value cannot be null.");
		}

		// the returned num variable and initializing a current node
		int num = 0;
		Node current = head;

		// traverse the linked list until the current node data is greater than
		// value -> since the list is ordered, we don't need to search anymore
		// however, we still need this condition current != null because of the
		// edge case where we're the last element is an occurrence of this
		// value; in this situation, we will add it to our returned num, then
		// current = current.next will make current equal to null, and null has
		// no data field, so this will result in an error. if we have this
		// condition before it, when current is null so the condition is false,
		// this will short circuit and there won't be an error
		while (current != null && current.data.compareTo(value) <= 0) {

			// use compareTo() to evaluate if the two objects are the same
			if ((current.data).compareTo(value) == 0) {
				num++;
			}
			current = current.next;
		}
		return num;
	}

	/**
	 * Similar to occurrencesOfValue(), continuing to search after a node that
	 * has a greater data value than our desired value is pointless
	 */
	@Override
	public int indexOfValue(T value) {
		// edge case
		if (value == null) {
			throw new IllegalArgumentException("value cannot be null.");
		}

		// the returned index variable and the current Node
		int index = 0;
		Node current = head;

		// traverse the linked list until the current node data is greater than
		// value -> since the list is ordered, we don't need to search anymore
		// the current != null condition is here for the same reason as the
		// occurrencesOfValue method above
		while (current != null && current.data.compareTo(value) <= 0) {

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
}