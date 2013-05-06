/*
 * Board
 * 
 * Version 0.8
 * 
 * 5/1/13
 * 
 * Author: Rafael Materla
 */

package rafael.materla.logic;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * The Board class is the logical part of the game itself. It contains 
 * informations about the positions of every tile including the snake and also 
 * controles the movement and collision of same.
 */
public class Board {

	// ---INSTANCE-VARIABLES---------------------------------------------------/
	public static final int CELL_LENGTH = 15;
	public static final int HORIZONTAL_CELLS = 35;
	public static final int VERTICAL_CELLS = 25;

	private Snake snake;
	private LinkedList<Figure> tiles; // Had to be a LinkedList
	private static boolean gameIsOver = false;

	// TODO INSTEAD OF SAVING THE POSITION INFORMATION IN THE ARRAY INDEX
	// POSITION (X / Y)
	// WHY NOT HAVE AN ARRAYLIST WHICH ONLY CONTAINS ACTUAL OBJECT (SNAKEHEAD,
	// BODY AND APPLE)
	// --------------------------------------------------------------------------
	// PRO: NO MORE AIR TILE,
	// CON: HAVE TO CREATE OBJECT FOR EACH TILE (WHICH EXTEND TILE) TO MAKE THEM
	// SAVE THEIR POSITOIN
	// PRO: WHICH MIGHT MAKE IT EASIER TO MOVE THE SNAKE + BODY;
	// CON: HAVE TO USE SLOW (ARRAY)LIST
	// PRO: DONT HAVE TO LOOP THROUGH EACH X AND Y COORDINATE
	// CONCLUSION: LET'S GIVE IT A TRY

	// private final Tiles[][] tiles;

	// ---CONSTRUCTOR----------------------------------------------------------/
	public Board() {
		snake = new Snake();
		tiles = new LinkedList<Figure>();

		initBoard();
	}

	// ---GAME-METHODS--------------------------------------------------------------/
	private void initBoard() {
		addTiles(snake.getSnake());
		addTile(new Apple());
	}

	void updateTiles() {
		snake.move();
		collide();
	}

	private void collide() {
		for (Figure figure : tiles) {
			if (!figure.getName().equals("SnakeHead")) {
				if (figure.getPosition().equals(snake.getPosition())) {
					if (figure.getName().equals("Apple")) {
						// As mentioned, tiles had to be a LinkedList. I've got
						// no
						// fucking clue why but this remove part seems to fuck
						// up if
						// I use the ArrayList
						// It had complained about an concurrency error so I
						// have
						// tried to use a Stack, a Vector or any other
						// Thread-Safe
						// list but that fucked up too...
						// FOR FUCKS SAKE I DON'T KNOW WHY THIS WON'T WORK WITH
						// A
						// STACK BUT AT LEAST THE LINKEDLIST DOES IT'S JOB RIGHT
						tiles.remove(figure);
						snake.grow();
					} else if(figure.getName().equals("SnakeBody")){
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

	private void addTiles(List<Figure> tileList) {
		tiles.addAll(tileList);
	}

	public int getHorizontalCells() {
		return HORIZONTAL_CELLS;
	}

	public int getVerticalCells() {
		return VERTICAL_CELLS;
	}

	public ArrayList<Figure> getTiles() {
		ArrayList<Figure> test = new ArrayList<Figure>();
		test.addAll(tiles);
		return test;
	}

	public int getCellLength() {
		return CELL_LENGTH;
	}

	public Dimension getBoardSize() {
		Dimension boardSize = new Dimension(HORIZONTAL_CELLS * CELL_LENGTH,
				VERTICAL_CELLS * CELL_LENGTH);
		return boardSize;
	}

	public Snake getSnake() {
		return snake;
	}

	public static void setGameOver() {
		gameIsOver = true;
	}

	public static boolean isGameOver() {
		return gameIsOver;
	}

	// TODO FINISH THIS LOGICAL CLASS AND REFACTOR UPDATETILES

}
