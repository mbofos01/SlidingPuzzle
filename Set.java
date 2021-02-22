package epl341.SlidingPuzzle;

public class Set {
	private int from;
	private int to;

	public Set(int from, int to) {
		this.from = from;
		this.to = to;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public String toString() {
		return " Swap tile number " + from + " with tile number " + to;

	}

}
