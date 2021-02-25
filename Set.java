package epl341.SlidingPuzzle;

/**
 * Set object is used to store the swaps in our puzzle (basically we represent
 * our path to a number of sets)
 * 
 * @author mbofos01
 * @author enicol09
 * 
 */
public class Set {
	/**
	 * A set is defined by two integers the places of two tiles
	 * 
	 */
	private int from;
	private int to;

	/**
	 * Typical constructor
	 * 
	 * @param from The place of one of the tiles we want to move
	 * @param to   The place of the other tile we want to move
	 */
	public Set(int from, int to) {
		this.from = from;
		this.to = to;
	}

	/**
	 * Getter for from
	 * 
	 * @return from
	 */
	public int getFrom() {
		return from;
	}

	/**
	 * Setter for from
	 * 
	 * @param from
	 */
	public void setFrom(int from) {
		this.from = from;
	}

	/**
	 * Getter for to
	 * 
	 * @return
	 */
	public int getTo() {
		return to;
	}

	/**
	 * Setter for to
	 * 
	 * @param to
	 */
	public void setTo(int to) {
		this.to = to;
	}

	/**
	 * A typical toString method, that shows the logic of Set object
	 */
	public String toString() {
		return " Swap tile number " + from + " with tile number " + to;

	}

}
