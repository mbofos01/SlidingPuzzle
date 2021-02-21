package epl341.SlidingPuzzle;

import java.util.ArrayList;

public class aStar {
	public ArrayList<Table> fringe = new ArrayList<>();

	public aStar() {
		Puzzle a = new Puzzle();
		a.print();
		fringe.add(new Table(a, 1));

		Table.calculatePossibleMoves(fringe, fringe.get(0));

	}

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

	public static int heuristic(Puzzle p) {
		int cost = 0;
		for (int i = 0; i < p.getSize() / 2; i++)
			if (p.getPuzzle()[i].isBlack())
				cost += ((p.getSize() - 1) / 2) - (i + 1);
		return cost;
	}

	public void test() {
		while (true) {
			Table A = fringe.get(0);

			Table t = new Table(A.puzzle.duplicate(), A.cost);

			if (A.puzzle.solved()) {
				System.out.println("Solution:");
				A.puzzle.print();
				return;
			}
			Table.calculatePossibleMoves(fringe, t);
			fringe.remove(0);
			t.cost = 100;
			fringe.add(t);
			sort();
		}
	}

	public static void main(String[] args) {
		System.out.println("Start:");
		aStar star = new aStar();
		star.test();

	}

}
