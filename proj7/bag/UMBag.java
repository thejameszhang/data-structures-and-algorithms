/**
 * @author: James Zhang
 * @directoryID: jzhang72
 * @uid: 118843940
 * @discussionNumber: 0107
 * I pledge on my honor that I have not given or received any unauthorized 
 * assistance on this assignment.
 */

package bag;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class UMBag {

	// private instance fields for the class
	private HashMap<Integer, Integer> map;

	// constructor for the class
	public UMBag() {
		map = new HashMap<>();
	}

	public void add(int elt) {

		// the case where the bag contains elt already
		if (map.containsKey(elt))
			map.put(elt, map.get(elt) + 1);

		// the case where the bag doesn't contain elt
		else
			map.put(elt, 1);

	}

	public boolean contains(int elt) {
		return map.containsKey(elt);
	}

	public int getCount(int elt) {
		int res = 0;
		if (map.containsKey(elt))
			res = map.get(elt);
		return res;
	}

	public int size() {

		Collection<Integer> vals = map.values();
		int res = 0;
		for (int v : vals)
			res += v;

		return res;
	}

	public Set<Integer> uniqueElements() {

		Collection<Integer> keySet = map.keySet();
		HashSet<Integer> res = new HashSet<>();

		for (int key : keySet)
			res.add(key);

		return res;
	}

	public void remove(int elt) {
		if (map.containsKey(elt)) {
			map.put(elt, map.get(elt) - 1);

			if (map.get(elt) == 0)
				map.remove(elt);
		}
	}

}
