/**
 * @author: James Zhang
 * @directoryID: jzhang72
 * @uid: 118843940
 * @discussionNumber: 0107
 * I pledge on my honor that I have not given or received any unauthorized 
 * assistance on this assignment.
 */

/*
 * This class defines a type of list that can hold any Object type, meaning it
 * can store multiple data items. It uses a regular array. If a there's not
 * enough space in the array to add a new element, the items are stored in an 
 * temporary array, and the reference of the list will change to a new array
 * which is increment spaces bigger. Once an element is added in this class,
 * it cannot be removed or replaced. 
 */

package businessOffice;

public class DatarrayList {

	// declares instance fields
	private Object[] list;
	private int increment;
	private int size;

	/**
	 * 1) This constructor initializes the instance field list of type Object 
	 * based on the increment parameter.
	 * 
	 * @param increment
	 */
	public DatarrayList(int increment) {

		// if the value increment is less than or equal to 0, then just create
		// a lsit of size 1
		if (increment <= 0) {
			list = new Object[1];
		}

		// otherwise, create the list with the size increment
		else {
			list = new Object[increment];
		}

		// initializes instance fields
		this.increment = increment;
		size = 0;
	}

	/**
	 * 2) No arg constructor, initializes the instance field list to size 1
	 */
	public DatarrayList() {
		list = new Object[1];
		increment = 1;
		size = 0;
	}

	/**
	 * 3) This method stores newElement in the first null element of the array. 
	 * If newElement is Null, the method returns false, otherwise it will 
	 * return true. If every spot in the array is taken up already, the 
	 * method will increase the size of the array by increment. For example, 
	 * if increment is 5, and the first 5 spaces are taken up, the new size 
	 * of the array will be 10.
	 * 
	 * @param Object newElement
	 * @return boolean if the newElement was added into the list or not
	 */
	public boolean add(Object newElement) {
		// base case; if newElement is null, then we can immediately return false
		if (newElement == null) {
			return false;
		}

		// There are two cases; either there's space or there isn't space. If
		// there's space, the first null element will hold newElement. If there's
		// no space, then new space will be added.
		else {

			// represents the position of the first non-null element
			int pos = 0;

			// case 1) we found the first non-null element
			boolean found = false;

			// case 2) the array has no space left
			boolean arrayFull = false;

			// loop through the list until one of the two cases occurs
			while (!found && !arrayFull) {

				// case 1
				if (list[pos] == null) {
					found = true;
				}

				// case 2
				else {
					pos++;
					if (pos == list.length) {
						arrayFull = true;
					}
				}
			}

			// At this point, if there's space, newElement can be added. 
			// If it's full, we need to make store all list elements in 
			// a temp array, change the list reference to a new, bigger 
			// array, and then repopulate all of the elements from 
			//the temp array back into list
			if (arrayFull) {

				// store the current list in a temp array
				Object[] temp = list;

				// list now references a new, bigger list
				list = new Object[list.length + increment];

				// populate the new list with values from the old list
				for (int i = 0; i < temp.length; i++) {
					list[i] = temp[i];
				}
			}

			// if false wasn't already returned, these two things must occur
			list[pos] = newElement;
		}

		size++; // used in the getSize() method
		return true;
	}

	/**
	 * 4) This method returns the number of elements inside the list (also the
	 * number of times the add function was called), not the defined size of the
	 * list. Thus, instead of looping through the array every time to find the
	 * number of elements, just keep track of every time a new element is added
	 * using the add method, and that will tell you the size.
	 * 
	 * @return int size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 5) This method returns the capacity of the list, otherwise known as the
	 * maximum number of elements the list can hold. The capacity will always 
	 * be greater than or equal to the size.
	 * 
	 * @return capacity of the list
	 */
	public int getCapacity() {
		return list.length;
	}

	/**
	 * 6) This method returns the element in list at position pos. If pos is 
	 * an invalid subscript for the list, then null will be returned instead.
	 * 
	 * @param int pos
	 * @return Object (either the element at the position or null)
	 */
	public Object get(int pos) {
		
		//if pos is an invalid subscript, return null
		if (pos < 0 || pos >= list.length) {
			return null;
		}
		
		//otherwise, return the element at index pos
		return list[pos];
	}
}