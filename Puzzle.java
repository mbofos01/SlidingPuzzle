package epl341.SlidingPuzzle;

/**
 * The Puzzle object represents the actual sliding block puzzle according to the
 * rules given
 * 
 * @author mbofos01
 * @author enicol09
 */
public class Puzzle {
	/**
	 * A puzzle is defined by the size of the puzzle and the tiles inside of it
	 */
	private int size;
	private Tile[] puzzle;

	/**
	 * The constructor needs only the size of the puzzle, the tiles are always
	 * placed by the same principle
	 * 
	 * @param size An integer bigger than three
	 */
	public Puzzle(int size) {
		this.size = size;
		puzzle = new Tile[size];
		int blacks = (size - 1) / 2;
		int i = 0;
		for (i = 1; i <= blacks; i++)
			puzzle[i - 1] = new Tile(i, 'B');
		puzzle[i - 1] = new Tile(i, 'E');
		i++;
		for (; i <= size; i++)
			puzzle[i - 1] = new Tile(i, 'W');
	}

	/**
	 * The constructor needs only the size of the puzzle, the tiles are always
	 * placed according to the demo array
	 * 
	 * @param size An integer bigger than three
	 */
	public Puzzle(int size, char[] demo) {
		this.size = size;
		puzzle = new Tile[size];
		int i = 0;
		for (i = 1; i <= size; i++) {
			puzzle[i - 1] = new Tile(i, demo[i - 1]);
		}

	}

	/**
	 * The default setup we will use is a 7-placed puzzle.
	 */
	public Puzzle() {
		this(7);
	}

	/**
	 * Getter method for size
	 * 
	 * @return size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Setter method for size
	 * 
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Getter method for puzzle
	 * 
	 * @return puzzle
	 */
	public Tile[] getPuzzle() {
		return puzzle;
	}

	/**
	 * Setter method for puzzle
	 * 
	 * @param puzzle
	 */
	public void setPuzzle(Tile[] puzzle) {
		this.puzzle = puzzle;
	}

	/**
	 * This method checks if a move is actually valid, according to the rules that
	 * were given to us.A move can be valid if:
	 * 
	 * A black or white tile wants to move to an empty space next to it. (cost 1) A
	 * black or white tile wants to move to an empty space 2 or 3 tiles next to it.
	 * (penalized according to the distance)
	 * 
	 * @param from The place of one of the tiles we want to move
	 * @param to   The place of the other tile we want to move
	 * @return true if the move is valid, otherwise false
	 */
	boolean validMove(int from, int to) {

		if (from < 1 || from > size || to < 1 || to > size)
			return false;
		if (Math.abs(from - to) == 1) {
			if (puzzle[from - 1].isMovable() && puzzle[to - 1].isEmpty()) {
				// System.out.println("1");
				return true;
			} else if (puzzle[to - 1].isMovable() && puzzle[from - 1].isEmpty()) {
				// System.out.println("2");
				return true;
			}
		} else if (Math.abs(from - to) == 2) {
			if (puzzle[from - 1].isMovable() && puzzle[to - 1].isEmpty()) {
				// System.out.println("3");
				return true;
			} else if (puzzle[to - 1].isMovable() && puzzle[from - 1].isEmpty()) {
				// System.out.println("4");
				return true;
			}

		} else if (Math.abs(from - to) == 3) {
			if (puzzle[from - 1].isMovable() && puzzle[to - 1].isEmpty()) {
				// System.out.println("5");
				return true;
			} else if (puzzle[to - 1].isMovable() && puzzle[from - 1].isEmpty()) {
				// System.out.println("6");
				return true;
			}
		}

		return false;

	}

	/**
	 * This method calculates the cost of each move
	 * 
	 * @param from The place of one of the tiles we want to move
	 * @param to   The place of the other tile we want to move
	 * @return The cost of the move
	 */
	private int costOfMove(int from, int to) {
		return Math.abs(from - to);

	}

	/**
	 * This method moves two tiles only if the move is valid and returns the cost of
	 * this move
	 * 
	 * @param from The place of one of the tiles we want to move
	 * @param to   The place of the other tile we want to move
	 * @return The cost of the move
	 */
	public int move(int from, int to) {
		if (validMove(from, to))
			swap(from, to);
		else
			return 0;
		return costOfMove(from, to);
	}

	/**
	 * This method calculates the possible cost of a move, if a move is invalid it
	 * returns 0
	 * 
	 * @param from The place of one of the tiles we want to move
	 * @param to   The place of the other tile we want to move
	 * @return The cost of the move
	 */
	public int possibleCost(int from, int to) {
		if (validMove(from, to))
			return costOfMove(from, to);
		return 0;
	}

	/**
	 * This method actually swaps two tiles. We do not actually move the tiles but
	 * we rename each place
	 * 
	 * @param from The place of one of the tiles we want to move
	 * @param to   The place of the other tile we want to move
	 */
	private void swap(int from, int to) {
		from--;
		to--;
		int place_temp = puzzle[from].getPlace();
		char type_temp = puzzle[from].getType();
		puzzle[from].setPlace(puzzle[to].getPlace());
		puzzle[from].setType(puzzle[to].getType());
		puzzle[to].setPlace(place_temp);
		puzzle[to].setType(type_temp);
	}

	/**
	 * This method prints the puzzle, mostly used for debugging reasons
	 */
	public void print() {
		for (int i = 0; i < size; i++)
			System.out.print("|" + " " + (i + 1) + " ");
		System.out.print("|\n");
		for (int i = 0; i < size; i++)
			System.out.print("|" + puzzle[i].toString());
		System.out.print("|\n");
	}

	/**
	 * This method checks if a state is acceptable For a state to be accepted it has
	 * to have all white tiles in places left by black tiles
	 * 
	 * @return true if the puzzle is solved, otherwise false
	 */
	public boolean solved() {
		int cnt = 0;
		for (Tile a : puzzle)
			if (a.isWhite() || a.isEmpty()) {
				if (a.isWhite())
					cnt++;
			} else
				break;

		if (cnt != (size - 1) / 2)
			return false;
		return true;

	}

	/**
	 * This method duplicates a puzzle
	 * 
	 * @return a functional copy of a puzzle
	 */
	public Puzzle duplicate() {
		Puzzle copy = new Puzzle(size);
		for (int i = 0; i < size; i++) {
			copy.puzzle[i].setType(this.puzzle[i].getType());
			copy.puzzle[i].setPlace(this.puzzle[i].getPlace());
		}
		return copy;

	}

	/**
	 * This method checks if the state of the puzzle is the same with another one
	 * 
	 * @param solution A representation of a solution
	 * @return true if the array is the same with the puzzle
	 */

	public boolean same(char[] solution) {
		for (int i = 0; i < size; i++)
			if (solution[i] != this.puzzle[i].getType())
				return false;
		return true;
	}

	/**
	 * Typical equals method that checks the type of each tile
	 */
	@Override
	public boolean equals(Object obj) {
		Puzzle other = (Puzzle) obj;
		for (int i = 0; i < size; i++)
			if (puzzle[i].getType() != other.puzzle[i].getType())
				return false;
		return true;
	}

}
