/**
 * @author David Han & Keith Davis
 */
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

/*
 * First in first out.  This scheme replaces the data
 * that has been in the cache the longest.
 */

public class FIFO extends CacheScheme {
	
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
					cKeys.remove(iter.next());
					cKeys.put(requests[i],new Data(requests[i]));
					collisionNum++;
				}
			}
		}
		//System.out.println("FIFO cache is " + cKeys.keySet());
		//System.out.println(collisionNum);
		return collisionNum;
	}
}
