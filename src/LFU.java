/**
 * @author David Han & Keith Davis
 */
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

/*
 * Least frequently used Cache algorithm.
 * This algorithm replaces the piece of data that has the least 
 * number of requests.
 */

public class LFU extends CacheScheme {

	public int numCollisions(int cacheSize, String word) {
		int collisionNum = 0;
		LinkedHashMap<Character, Data> cKeys = new LinkedHashMap<Character, Data>();
		char[] requests = word.toCharArray();
		for (int i = 0; i < word.length(); i++) {
			char currentKey = requests[i];
			if (cKeys.containsKey(requests[i])) {
				// character is already there
				cKeys.get(requests[i]).frequency++;
			} else {
				// character is not there
				if (cKeys.size() < cacheSize) {
					// cache is not full
					cKeys.put(requests[i], new Data(requests[i]));
				} else {
					// cache is full and character is not there

					Iterator iter = cKeys.keySet().iterator();
					char smallest = (char) iter.next();
					while (iter.hasNext()) {
						char current = (char) iter.next();
						int thefreq = cKeys.get(current).frequency;
						if (cKeys.get(current).frequency<cKeys.get(smallest).frequency) {
							smallest = current;
						}
					}
					cKeys.remove(smallest);
					cKeys.put(requests[i],new Data(requests[i]));
					collisionNum++;
				}
			}
		}
		//System.out.println("LFU cache is " + cKeys.keySet());
		//System.out.println(collisionNum);
		return collisionNum;
	}
}
