package epl341.SlidingPuzzle;

import java.util.ArrayList;

public class aStar {
	public ArrayList<Table> fringe = new ArrayList<>();

	public aStar() {
		fringe.add(new Table(new Puzzle(7), 1));
		// fringe.add(new Table(new Puzzle(), 200));
		Table.calculatePossibleMoves(fringe, fringe.get(0));
		// sort();
		// for (Table a : fringe)
		// a.debbug();

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

	public Table getFirst() {
		return fringe.get(0);
	}

	public static int heuristic(Puzzle p) {
		return 0;
	}

	public void solve() {
		while (true) {
			sort();
			Table t = getFirst();
			fringe.remove(t);
			// t.puzzle.print();
			if (t.puzzle.solved()) {
				t.puzzle.print();
				System.exit(0);

			}
			Table.calculatePossibleMoves(fringe, t);
			t.cost = Integer.MAX_VALUE;
			fringe.add(t);
		}
	}

	public static void main(String[] args) {
		System.out.println("Start");
		aStar star = new aStar();
		star.solve();

	}

}
