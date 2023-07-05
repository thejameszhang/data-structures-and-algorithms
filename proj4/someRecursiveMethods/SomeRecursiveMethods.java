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

package someRecursiveMethods;

public class SomeRecursiveMethods {

	/**
	 * 3.1) This method sums up all of the indexes of elements that are
	 * identical to the specified element. The list uses 1-based indexing. It
	 * calls a helper method to maintain track of the position within the list.
	 * 
	 * @param <T>
	 * @param list
	 * @param element
	 * @return sum of all indexes identical to element
	 */
	public static <T> int sumEltLocations(UMCPList<T> list, T element) {

		// check if either parameter is null
		if (list == null || element == null)
			throw new IllegalArgumentException("Parameters cannot be null.");

		return sumEltLocationsHelper(list, element, 0);
	}

	/**
	 * This is a helper method for the sumEltLocations method. It maintains
	 * track of the position within the list and makes one of two recursive
	 * calls based on whether or not the element at the specified pos is
	 * identical to the specified element.
	 * 
	 * @param <T>
	 * @param list
	 * @param element
	 * @param pos
	 * @return a recursive call to this method
	 */
	private static <T> int sumEltLocationsHelper(UMCPList<T> list, T element,
			int pos) {

		// base case
		if (pos >= list.size())
			return 0;

		// recursive case where the element in the list is the same as he
		// desired element, return pos + 1 and then call the helper method again
		if (list.get(pos).equals(element))
			return pos + 1 + sumEltLocationsHelper(list, element, pos + 1);

		// otherwise just call the helper method on the next element
		return sumEltLocationsHelper(list, element, pos + 1);
	}

	/**
	 * 3.2) This method works with the helper method and returns the location of
	 * the nth occurrence of element in the list. It checks some edge cases
	 * where parameters list and element are null, in which case an exception is
	 * thrown, and the edge case where n is invalid, in which -1 is returned. If
	 * these edge cases are passed, it makes a call to the helper method.
	 * 
	 * @param <T>
	 * @param list
	 * @param element
	 * @param n
	 * @return
	 */
	public static <T> int locOfOccurrence(UMCPList<T> list, T element, int n) {

		// check if either parameter is null
		if (list == null || element == null)
			throw new IllegalArgumentException("Parameters cannot be null.");

		// if n is 0 or -1 then return -1
		if (n == 0 || n == -1)
			return -1;

		return locOfOccurrenceHelper(list, element, n, 0);
	}

	/**
	 * This helper method returns the nth occurrence of element in the list. It
	 * checks a base case in which the entire list has been searched and there
	 * are not n occurrences of element in list. It uses the parameter n as a
	 * tracker of the occurrences of n. Suppose we want to find the 3rd
	 * occurrence. When n is 1, if the searched element and desired element are
	 * identical, this element is the nth occurrence. Otherwise, decrement n to
	 * keep track of the number of occurrences and then make a recursive call to
	 * the helper method.
	 * 
	 * @param <T>
	 * @param list
	 * @param element
	 * @param n
	 * @param pos
	 * @return
	 */
	private static <T> int locOfOccurrenceHelper(UMCPList<T> list, T element,
			int n, int pos) {

		// base case. this means that there are not n occurrences of element in
		// the list, so return -1
		if (pos >= list.size())
			return -1;

		// if the element at pos is the same as the element, return pos + 1
		// if it's the n-th occurrence, otherwise decrement n by 1.
		// n keeps track of the number of occurrences of element
		if (list.get(pos).equals(element)) {
			if (n == 1) {
				return pos + 1;
			}
			n--;
		}

		// recursive call
		return locOfOccurrenceHelper(list, element, n, pos + 1);
	}

	/**
	 * 3.3) This method checks the edge case where the list parameter is null.
	 * If it's not, an empty newList will be created, and then this method calls
	 * the helper method, which will populate newList with a forward and
	 * backward image of the list.
	 * 
	 * @param <T>
	 * @param list
	 * @return the newList, which is returned by the helper method
	 */
	public static <T> UMCPList<T> mirrorImage(UMCPList<T> list) {

		// checking edge case
		if (list == null)
			throw new IllegalArgumentException("List cannot be null.");

		// making the new UMCPlist
		UMCPList<T> newList = new UMCPList<T>();
		return mirrorImageHelper(list, newList, 0);
	}

	/**
	 * This helper method adds the forward and backward images of a list into
	 * the newList thanks to a stack.
	 * 
	 * @param <T>
	 * @param list
	 * @param newList
	 * @param pos
	 * @return the newList
	 */
	private static <T> UMCPList<T> mirrorImageHelper(UMCPList<T> list,
			UMCPList<T> newList, int pos) {

		// uses understanding of the runtime stack
		if (pos < list.size()) {
			newList.add(list.get(pos));

			// before the method is finished running, make a call to the helper
			// again
			mirrorImageHelper(list, newList, pos + 1);

			// when the forward image is done, we have to go back down the
			// stack, and this gives us the backward image
			newList.add(list.get(pos));
		}

		return newList;
	}

	/**
	 * 3.4) This method makes a call to the makeList helper method. It also
	 * checks some edge cases. If the list parameter is null, then an exception
	 * is thrown. If startPos or n are less than or equal to 0, an empty
	 * UMCPList is returned.
	 * 
	 * @param <T>
	 * @param list
	 * @param numValues
	 * @param startPos
	 * @param n
	 * @return a call to the helper method, which will return the desired list
	 */
	public static <T> UMCPList<T> makeList(UMCPList<T> list, int numValues,
			int startPos, int n) {

		// edge case
		if (list == null)
			throw new IllegalArgumentException("List cannot be null.");

		UMCPList<T> newList = new UMCPList<T>();

		// another edge case
		if (startPos <= 0 || n <= 0)
			return newList;

		return makeListHelper(list, numValues, startPos, n, newList);
	}

	/**
	 * This helper method adds numValues elements starting from startPos,
	 * incremented by n into the newList. The base case is such that the if our
	 * newList is our desired size or if it's greater than the numValues
	 * parameter, then we should stop and return the newList. This implicitly
	 * covers the edge case where numValues is negative or 0. Another base case
	 * is when there are too few elements in the list to populate our newList as
	 * desired. If both of these conditions are false, then we add the desired
	 * element into our newList, and then another recursive call is made to the
	 * helper method.
	 * 
	 * @param <T>
	 * @param list
	 * @param numValues
	 * @param startPos
	 * @param n
	 * @param newList
	 * @return recursive calls to the helper method
	 */

	private static <T> UMCPList<T> makeListHelper(UMCPList<T> list,
			int numValues, int startPos, int n, UMCPList<T> newList) {

		// base case or if there's no more elements left to add in the list
		if (newList.size() >= numValues || startPos > list.size())
			return newList;

		// we need startPos - 1 because UMCPList is one-indexed
		newList.add(list.get(startPos - 1));

		// recursive call
		return makeListHelper(list, numValues, startPos + n, n, newList);
	}
}
