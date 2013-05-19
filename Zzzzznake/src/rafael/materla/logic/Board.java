/*
 * Board
 * 
 * Version 1.0.0
 * 
 * 5/18/13
 * 
 * Author: Rafael Materla
 */

package rafael.materla.logic;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

/*
 * The Board class is the logical part of the game itself. It contains 
 * informations about the positions of every tile including the snake and also 
 * controles the movement and collision of same.
 */
public class Board {

	// ---INSTANCE-VARIABLES---------------------------------------------------/
	public static final int CELL_LENGTH = 20;
	public static final int HORIZONTAL_CELLS = 35;
	public static final int VERTICAL_CELLS = 25;

	private static Snake snake;
	private static ArrayList<Figure> tiles;
	private static boolean gameIsOver = false;

	// ---CONSTRUCTOR----------------------------------------------------------/
	public Board() {
		snake = new Snake();
		tiles = new ArrayList<Figure>();

		initBoard();
	}

	// ---GAME-METHODS--------------------------------------------------------------/
	private void initBoard() {
		addTile(new Apple());
		addTiles(snake.getSnake());
	}

	void updateTiles() {
		snake.move();
		collide();
		synchronizeTiles();
	}

	private void collide() {
		if (!snake.isInsideBoard()) {
			setGameOver();
		} else {
			for (Figure figure : getTiles()) {
				if (figure.getPosition().equals(snake.getPosition())) {
					if (figure instanceof Apple) {
						removeTile(figure);
						addTile(new Apple());
						snake.grow();
					} else if (figure instanceof Body) {
						setGameOver();
					}
				}
			}
		}
	}

	// ---UTILITY-METHODS------------------------------------------------------/
	private void addTile(Figure tile) {
		tiles.add(tile);
	}

	private void addTiles(List<SnakePart> tileList) {
		tiles.addAll(tileList);
	}

	private void removeTile(Figure tile) {
		tiles.remove(tile);
	}

	private void synchronizeTiles() {

		for (Figure snakePart : snake.getSnake()) {
			boolean isEqual = false;
			for (Figure figure : getTiles()) {
				if (figure.equals(snakePart)) {
					isEqual = true;
				}
			}
			if (!isEqual) {
				addTile(snakePart);
			}
		}
	}

	public int getHorizontalCells() {
		return HORIZONTAL_CELLS;
	}

	public int getVerticalCells() {
		return VERTICAL_CELLS;
	}

	public static ArrayList<Figure> getTiles() {
		ArrayList<Figure> test = new ArrayList<Figure>();
		test.addAll(tiles);
		return test;
	}

	public int getCellLength() {
		return CELL_LENGTH;
	}

	public static Dimension getBoardSize() {
		Dimension boardSize = new Dimension(HORIZONTAL_CELLS * CELL_LENGTH,
				VERTICAL_CELLS * CELL_LENGTH);
		return boardSize;
	}

	public static Snake getSnake() {
		return snake;
	}

	void setGameOver() {
		gameIsOver = true;
	}

	public static boolean isGameOver() {
		return gameIsOver;
	}
}