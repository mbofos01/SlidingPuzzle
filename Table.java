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
    static char[] solution = {'W','W','W','W','W','B','B','B','B','B','E'};
	

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
		for (int i = 1; i <= table.puzzle.getSize(); i++)
			for (int j = 1; j <= table.puzzle.getSize(); j++) {
				temp = table.puzzle.duplicate();
				if (temp.validMove(i, j)) {
					int c = temp.move(i, j);
					c+= aStar.heuristic2(temp,solution);
					//c+= aStar.heuristic(temp);
					// temp.print();
					Puzzle s = temp.duplicate();
					// fringe.add(new Table(s, 0));
					// System.out.println(exists(fringe, s));
					if (exists(fringe, s)) {
						// System.out.println("exists(fringe, s)");
					} else
						fringe.add(new Table(s, c));
				}
			}

	}

	private static boolean exists(ArrayList<Table> fringe, Puzzle puzzle2) {
		for (Table a : fringe)
			if (puzzle2.equals(a.puzzle)) {
				// puzzle2.print();
				return true;
			}
		return false;
	}

	private static Table find(ArrayList<Table> fringe, Puzzle puzzle2) {
		for (Table a : fringe)
			if (a.puzzle.equals(puzzle2))
				return a;
		return null;
	}

	public static void main(String[] args) {
		ArrayList<Table> fringe = new ArrayList<Table>();
		calculatePossibleMoves(fringe, new Table(new Puzzle(5), 1));
		for (Table s : fringe)
			s.debbug();

	}

}
