package epl341;

/**
 * 
 * This object represents a typical block in the puzzle
 *
 * 
 * @author mbofos01
 *
 */
public class Tile {
	/**
	 * A block is defined by its place and its color. For our convenience we assume
	 * empty is a type of color.
	 */
	private int place;
	private char type;

	/**
	 * The constructor needs the place of the Tile and its color (given by char E
	 * Empty W White B Black)
	 * 
	 * IMPORTANT NOTE: Places start at 1
	 * 
	 * @param place An integer that shows the place of the Tile
	 * @param type  A character that shows the color of the Tile
	 */
	public Tile(int place, char type) {
		this.place = place;
		this.type = type;
	}

	/**
	 * A method that returns the place of the Tile
	 * 
	 * @return The place of the Tile
	 */
	public int getPlace() {
		return place;
	}

	/**
	 * Setter method for place
	 * 
	 * @param place new place for the tile
	 */
	public void setPlace(int place) {
		this.place = place;
	}

	/**
	 * A method that returns the color of the Tile
	 * 
	 * @return E Empty W White B Black
	 */
	public char getType() {
		return type;
	}

	/**
	 * Setter method for color
	 * 
	 * @param type E Empty W White B Black
	 */
	public void setType(char type) {
		this.type = type;
	}

	/**
	 * A method that returns true if the tile is empty
	 * 
	 * @return true if tile is Empty
	 */
	public boolean isEmpty() {
		return type == 'E';
	}

	/**
	 * A method that returns true if the tile is white
	 * 
	 * @return true if tile is White
	 */
	public boolean isWhite() {
		return type == 'W';
	}

	/**
	 * A method that returns true if the tile is black
	 * 
	 * @return true if tile is Black
	 */
	public boolean isBlack() {
		return type == 'B';
	}

	/**
	 * A method that returns true if the tile is black or white
	 * 
	 * @return true if tile is Black or White
	 */
	public boolean isMovable() {
		return isBlack() || isWhite();
	}

	/**
	 * A toString method for the tile
	 */
	public String toString() {
		return " " + type + " ";
	}

}
