import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author David Han & Keith Davis
 */
/*
 * Implements the Furthest In Future Cache algorithm.
 * This algorithm's efficiency doesn't really matter
 * as long as it is tractable.
 * 
 * This is an oracle algorithm, giving a collision lower bounds.
 */

public class FIF extends CacheScheme {
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
					Set<Character> counter = new HashSet<Character>();
					counter.addAll(cKeys.keySet());
					int currentIndex = i;
					char farthestLetter = counter.iterator().next();
					int sizeWord = word.length();
					while (counter.size()>1 && currentIndex<word.length()) {
						if (counter.contains(requests[currentIndex])) {
							farthestLetter = requests[currentIndex];
							counter.remove(farthestLetter);
						}
						currentIndex++;
					}
					// farthestLetter is the letter to be replaced
					cKeys.remove(counter.iterator().next());
					cKeys.put(requests[i],new Data(requests[i]));
					collisionNum++;
				}
			}
		}
		//System.out.println("FIF cache is " + cKeys.keySet());
		return collisionNum;
	}
}
