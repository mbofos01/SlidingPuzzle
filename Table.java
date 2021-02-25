package epl341.SlidingPuzzle;

import java.util.ArrayList;

/**
 * Table objects should be used as the solutions placed in fringe. Fringe will
 * be an arraylist of tables
 * 
 * @author mbofos01
 * @author enicol09
 *
 */
public class Table {
	/**
	 * A table is defined by a puzzle state, its cost and the path used to get
	 * there.
	 */
	public Puzzle puzzle;
	public int cost;
	static char[] solution;
	private ArrayList<Set> steps;

	/**
	 * This constructor is used to create a new table, most of the times for the
	 * starting state, so we set the first step to an illegal value, which we don't
	 * use
	 * 
	 * @param puzzle current state
	 * @param cost   current states' cost
	 */
	public Table(Puzzle puzzle, int cost) {
		this.puzzle = puzzle;
		this.cost = cost;
		steps = new ArrayList<>();
		steps.add(new Set(-1, -1));
	}

	/**
	 * This constructor basically updates a state
	 * 
	 * @param puzzle current state
	 * @param cost   current states' cost
	 * @param sets   current states' path
	 */
	public Table(Puzzle puzzle, int cost, ArrayList<Set> sets) {
		this.puzzle = puzzle;
		this.cost = cost;
		steps = new ArrayList<>();
		for (int i = 0; i < sets.size(); i++)
			steps.add(sets.get(i));
	}

	/**
	 * This method was used to debbug some functions. It prints the cost of a state
	 */
	public void debbug() {
		System.out.println("Cost: " + cost);
		puzzle.print();

	}

	/**
	 * This method calculates all the possible moves from a state, if a new state
	 * already exists, we use the one with the lowest cost, and update costs and
	 * paths
	 * 
	 * 
	 * @param fringe an arraylist that holds the possible states
	 * @param table  current state
	 */
	public static void calculatePossibleMoves(ArrayList<Table> fringe, Table table) {
		Puzzle temp;
		for (int i = 1; i <= table.puzzle.getSize(); i++)
			for (int j = 1; j <= table.puzzle.getSize(); j++) {
				temp = table.puzzle.duplicate();
				if (temp.validMove(i, j)) {
					int c = temp.move(i, j);
					c += aStar.heuristic(temp, solution);
					Puzzle s = temp.duplicate();
					if (exists(fringe, s)) {

						Table new_table = find(fringe, s);
						if (new_table.cost > c) {
							new_table.cost = c;
							new_table.steps = new ArrayList<>();
							new_table.addSteps(table.steps);
							new_table.steps.add(new Set(i, j));

						}

					} else {
						Table f = new Table(s, c, table.steps);
						f.steps.add(new Set(i, j));
						fringe.add(f);
					}
				}
			}

	}

	/**
	 * This method adds steps to a path of a state
	 * 
	 * @param sets new steps we need to add to a states path
	 */
	private void addSteps(ArrayList<Set> sets) {
		for (int i = 0; i < sets.size(); i++)
			steps.add(sets.get(i));
	}

	/**
	 * This method prints the path of a state
	 */
	public void printSetps() {
		for (int i = 1; i < steps.size(); i++)
			System.out.println("Step " + (i + 0) + ")" + steps.get(i));

	}

	/**
	 * This function checks if a puzzle state exists in a table arraylist
	 * 
	 * @param fringe  a list of states
	 * @param puzzle2 a puzzle state
	 * @return true if this state exists
	 */
	private static boolean exists(ArrayList<Table> fringe, Puzzle puzzle2) {
		for (Table a : fringe)
			if (puzzle2.equals(a.puzzle))
				return true;

		return false;
	}

	/**
	 * This function finds a specific puzzle state in a table arraylist
	 * 
	 * @param fringe  a list of states
	 * @param puzzle2 a puzzle state
	 * @return the same puzzle state (same according to the placement of the tiles)
	 */
	private static Table find(ArrayList<Table> fringe, Puzzle puzzle2) {
		for (Table a : fringe)
			if (a.puzzle.equals(puzzle2))
				return a;
		return null;
	}

}
