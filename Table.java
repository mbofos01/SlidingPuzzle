package epl341.SlidingPuzzle;

/**
 * Table objects should be used as the solutions placed in fringe. Fringe will
 * be an arraylist of tables
 * 
 * @author mbofos01
 *
 */
public class Table {
	public Puzzle puzzle;
	public int cost;

	public Table(Puzzle puzzle, int cost) {
		this.puzzle = puzzle;
		this.cost = cost;
	}

}
