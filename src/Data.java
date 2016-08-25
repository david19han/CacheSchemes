/**
 * @author David Han & Keith Davis
 */
/*
 * Data representing information about a data request.
 * The instance variables are public to make accessing information
 * efficient.
 */
public class Data implements Comparable<Data>{
	
	// For making a linked list
	Data before = null;
	Data after = null;
	Character letter;
	
	// You may or may not need these, and more.  
	// Add to them however you'd like.
	//Character letter = null;
	boolean cached = false;
	int frequency = 0;
	
	public Data(Character theLetter){
		letter = theLetter;
	}

	// Lets me compare based on frequency.  If more
	// comparisons are needed, use a comparator.
	public int compareTo(Data other){
		return this.frequency-other.frequency;
	}
}
