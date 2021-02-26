package epl341.SlidingPuzzle;

import java.util.ArrayList;

/**
 * This is the main object we will use to solve the sliding puzzle problem
 * 
 * @author mbofos01
 * @author enicol09
 * 
 */
public class aStar {
	/**
	 * A* object is defined only by a fringe
	 */
	public ArrayList<Table> fringe = new ArrayList<>();

	/**
	 * This constructor is not used in our project, it would be used if we wanted to
	 * find a solution for the sliding puzzle problem
	 */
	public aStar() {

		Puzzle a = new Puzzle(7);
		a.print();
		fringe.add(new Table(a, 1));
		Table.calculatePossibleMoves(fringe, fringe.get(0));

	}

	/**
	 * This constructor is used to find a specific solution with the help of A*
	 * 
	 * @param array the solution we want to find (end state)
	 */
	public aStar(char[] array, char[] demo) {
		Puzzle a = new Puzzle(7, demo);
		a.print();
		Table.solution = array;
		fringe.add(new Table(a, 1));
		Table.calculatePossibleMoves(fringe, fringe.get(0));

	}

	/**
	 * A typical N^2 sorting algorithm (bubblesort) that sorts our fringe
	 */
	public void sort() {
		for (int i = 0; i < fringe.size(); i++) {
			int min = fringe.get(i).cost;
			int minId = i;
			for (int j = i + 1; j < fringe.size(); j++) {
				if (fringe.get(j).cost < min) {
					min = fringe.get(j).cost;
					minId = j;
				}
			}
			// swapping
			int temp = fringe.get(i).cost;
			fringe.get(i).cost = min;
			fringe.get(minId).cost = temp;
		}

	}

	/**
	 * TODO @elia
	 * 
	 * @param p
	 * @param solution
	 * @return
	 */
	public static int heuristic(Puzzle p, char[] solution) {
		int cost = 0;
		for (int i = 0; i < p.getSize(); i++)
			if (p.getPuzzle()[i].getType() != solution[i])
				cost += 1;// Math.abs(findNearest(p.getPuzzle()[i].getType(), solution) - i);
		return cost;
	}

	/**
	 * TODO @elia
	 * 
	 * @param type
	 * @param solution
	 * @return
	 */
	private static int findNearest(char type, char[] solution) {
		for (int i = 0; i < solution.length; i++) {
			if (type == solution[i])
				return i;
		}
		return 0;
	}

	/**
	 * The first heuristic we tried to use, this heuristic would count the black
	 * tiles in the first half of the puzzle and would increase the cost according
	 * to the place of the tile
	 * 
	 * @param p
	 * @return
	 */
	public static int heuristic_old(Puzzle p) {
		int cost = 0;
		for (int i = 0; i < p.getSize() / 2; i++)
			if (p.getPuzzle()[i].isBlack())
				cost += ((p.getSize() - 1) / 2) - (i + 1);
		return cost;
	}

	/**
	 * This method finds a solution, using the A* algorithm
	 */
	public void test() {
		while (true) {
			Table A = fringe.get(0);
			Table t = new Table(A.puzzle.duplicate(), A.cost);

			if (A.puzzle.solved()) {
				System.out.println("Solution:");
				A.puzzle.print();
				A.printSetps();
				System.out.println("Cost: " + A.cost);
				return;

			}
			Table.calculatePossibleMoves(fringe, fringe.get(0));
			fringe.remove(0);
			fringe.add(t);
			sort();

		}
	}

	/**
	 * This method finds a solution defined by the user, using the A* algorithm
	 */
	public void solve() {
		while (true) {
			Table A = fringe.get(0);
			Table t = new Table(A.puzzle.duplicate(), A.cost);

			if (A.puzzle.same(Table.solution)) {
				System.out.println("Solution:");
				A.puzzle.print();
				A.printSetps();
				return;
			}
			Table.calculatePossibleMoves(fringe, fringe.get(0));
			fringe.remove(0);
			fringe.add(t);
			sort();

		}
	}

	/**
	 * Main function, that counts the time needed to solve the sliding puzzle
	 * problem
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start:");
		char[] sol = new char[7];
		char[] demo = new char[7];
		try {
			int size = args[0].length();
			for (int i = 0; i < size; i++)
				sol[i] = args[0].charAt(i);

		} catch (Exception e) {
			sol[0] = 'W';
			sol[1] = 'W';
			sol[2] = 'W';
			sol[3] = 'E';
			sol[4] = 'B';
			sol[5] = 'B';
			sol[6] = 'B';

		}
		try {
			int size = args[1].length();
			for (int i = 0; i < size; i++)
				demo[i] = args[1].charAt(i);
		} catch (Exception e) {
			demo[0] = 'B';
			demo[1] = 'B';
			demo[2] = 'B';
			demo[3] = 'W';
			demo[4] = 'W';
			demo[5] = 'W';
			demo[6] = 'E';

		}
		aStar star = new aStar(sol, demo);
		long millis = System.currentTimeMillis();
		star.solve();
		System.out.println("Time needed: " + (System.currentTimeMillis() - millis) * 1.0 / 1000 + " seconds.");

	}

}
