/*
 * SchemeComparisons.java
 * This object runs one cache situation on all schemes implemented
 * to compare their performances.
 */
import java.util.HashMap;

import javax.swing.JOptionPane;

public class SchemeComparisons {
	private LFU leastFreqUsed = new LFU();
	private FIFO firstIn = new FIFO();
	private LRU leastRecently = new LRU();
	private FIF inFuture = new FIF();

	/*
	 * Run this method to compare hard-coded strings and sizes
	 */
	public void comparePresetValues(){
		HashMap<CacheScheme, String> codes = new HashMap<CacheScheme, String>();
		codes.put(firstIn, "fifo");
		codes.put(inFuture, "fif");
		codes.put(leastFreqUsed, "lfu");
		codes.put(leastRecently, "lru");
		String[] dataSets = {
				"ABACDEBDA",
				"abcdefecbab", 
				"723a1w31w21q", 
				"837y873681927368", 
				"abababab", 
				"abcdcefdbcda"};
		int[] sizes = {2,3,4,5};
		for(CacheScheme s: codes.keySet()){
			for(int size:sizes){
				for(String data:dataSets){
					compare(data,size);
				}
				
			}
		}
	}

	
	/*
	 * Compares the performances of the scheme algorithms and 
	 * prints the results in a JOptionPane.
	 */
	public void compare(String data, int size){
		int lfu = leastFreqUsed.numCollisions(size, data);
		int lifo = firstIn.numCollisions(size, data);
		int lru = leastRecently.numCollisions(size, data);
		int future = inFuture.numCollisions(size, data);
		JOptionPane.showMessageDialog(null, "FIF: \t"+future+"\n"+
				"LFU: \t"+lfu+"\n"+
				"LIFO: \t"+lifo+"\n"+
				"LRU: \t"+lru);

	}

	/*
	 * Asks the user for the string and cache size to run, 
	 * then calls compare.
	 */
	public void userCompare(){
		String data = JOptionPane.showInputDialog("Enter the string of data requests.");
		int size = Integer.parseInt(JOptionPane.showInputDialog("Enter the cache size (integer)"));
		compare(data,size);
	}
}
