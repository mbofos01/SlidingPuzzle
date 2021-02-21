package epl341.SlidingPuzzle;

import java.util.ArrayList;

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

	public void debbug() {
		System.out.println("Cost: " + cost);
		puzzle.print();

	}

	/**
	 * This method should calculate the possible moves from a state
	 * 
	 * TODO
	 * 
	 * @param fringe
	 */
	public static void calculatePossibleMoves(ArrayList<Table> fringe, Table table) {
		Puzzle temp;
		for (int i = 0; i < table.puzzle.getSize(); i++)
			for (int j = 0; j < table.puzzle.getSize(); j++) {
				temp = table.puzzle.duplicate();
				if (i != j)
					if (temp.validMove(i, j)) {
						int c = temp.move(i, j);
						if (!temp.equals(table.puzzle) && doesntExist(fringe, table.puzzle)) {
							c += aStar.heuristic(table.puzzle);
							c += table.cost;
							Table t = new Table(temp.duplicate(), c);
							fringe.add(t);
						}
					}
			}

	}

	private static boolean doesntExist(ArrayList<Table> fringe, Puzzle puzzle2) {
		for (Table a : fringe)
			if (a.puzzle.equals(puzzle2))
				return false;
		return true;
	}

}
