/*
 * Board
 * 
 * Version 0.7.5
 * 
 * 4/8/13
 * 
 * Author: Rafael Materla
 */

package rafael.materla.logic;

import java.awt.Dimension;
import java.awt.Point;
import java.util.LinkedList;

/*
 * The Board class is the logical part of the game itself. It contains 
 * informations about the positions of every tile including the snake and also 
 * controles the movement and collision of same.
 */
public class Board {

	// ---INSTANCE-VARIABLES---------------------------------------------------/
	private LinkedList<Point> bodyPositions;
	private Directions oldHeadDirection;
	private Directions headDirection;
	private boolean gameIsOver = false;
	private int snakeLength;
	private final int cellLength;
	private final int horizontalCells;
	private final int verticalCells;

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

	private final Tiles[][] tiles;

	// ---CONSTRUCTOR----------------------------------------------------------/
	public Board(int tileLength, int boardWidth, int boardHeight) {
		bodyPositions = new LinkedList<Point>();
		snakeLength = 3;
		cellLength = tileLength;
		horizontalCells = boardWidth;
		verticalCells = boardHeight;
		tiles = new Tiles[horizontalCells][verticalCells];
		headDirection = Directions.NONE;

		initBoard();
	}

	// ---GAME-METHODS--------------------------------------------------------------/
	private void initBoard() {
		for (byte b = 0; b < horizontalCells; b++) {
			for (byte c = 0; c < verticalCells; c++) {
				tiles[b][c] = Tiles.AIR;
			}
		}
		setTile(((int) horizontalCells / 2), ((int) verticalCells / 2),
				Tiles.SNAKEHEAD);
		setTile((int) (Math.random() * horizontalCells),
				(int) (Math.random() * verticalCells), Tiles.APPLE);
	}

	void updateTiles() {
		switch (headDirection) {
		case NORTH:
			moveSnakeUp();
			oldHeadDirection = headDirection;
			break;
		case EAST:
			moveSnakeRight();
			oldHeadDirection = headDirection;
			break;
		case SOUTH:
			moveSnakeDown();
			oldHeadDirection = headDirection;
			break;
		case WEST:
			moveSnakeLeft();
			oldHeadDirection = headDirection;
			break;
		case NONE:
			break;
		default:
			break;
		}
	}

	// ---TODO some refactoring for the moveSnakeXXX() methods to make them more
	// OOP and clean.

	private void moveSnakeUp() {
		Point oldHeadPosition = new Point(getHeadPosition());

		pushBodyPositions(oldHeadPosition);
		collideSnake((new Point((int) getHeadPosition().getX(),
				(int) getHeadPosition().getY() - 1)));
		setTile((int) getHeadPosition().getX(),
				(int) getHeadPosition().getY() - 1, Tiles.SNAKEHEAD);
		setTile((int) oldHeadPosition.getX(), (int) oldHeadPosition.getY(),
				Tiles.AIR);
	}

	private void moveSnakeRight() {
		Point oldHeadPosition = new Point(getHeadPosition());

		pushBodyPositions(oldHeadPosition);
		collideSnake((new Point((int) getHeadPosition().getX() + 1,
				(int) getHeadPosition().getY())));
		setTile((int) getHeadPosition().getX() + 1, (int) getHeadPosition()
				.getY(), Tiles.SNAKEHEAD);
		setTile((int) oldHeadPosition.getX(), (int) oldHeadPosition.getY(),
				Tiles.AIR);

	}

	private void moveSnakeDown() {
		Point oldHeadPosition = new Point(getHeadPosition());

		pushBodyPositions(oldHeadPosition);
		collideSnake((new Point((int) getHeadPosition().getX(),
				(int) getHeadPosition().getY() + 1)));
		setTile((int) getHeadPosition().getX(),
				(int) getHeadPosition().getY() + 1, Tiles.SNAKEHEAD);
		setTile((int) oldHeadPosition.getX(), (int) oldHeadPosition.getY(),
				Tiles.AIR);

	}

	private void moveSnakeLeft() {
		Point oldHeadPosition = new Point(getHeadPosition());

		pushBodyPositions(oldHeadPosition);
		collideSnake((new Point((int) getHeadPosition().getX() - 1,
				(int) getHeadPosition().getY())));
		setTile((int) getHeadPosition().getX() - 1, (int) getHeadPosition()
				.getY(), Tiles.SNAKEHEAD);
		setTile((int) oldHeadPosition.getX(), (int) oldHeadPosition.getY(),
				Tiles.AIR);

	}

	// TODO SOME OOP REFACTORING AND SO ON
	private void collideSnake(Point futurePosition) {
		if (isHeadInsideBoard()) {
			switch (getTile((int) futurePosition.getX(),
					(int) futurePosition.getY())) {
			case APPLE:
				snakeLength++;
				break;
			case SNAKEBODY:
				endGame();
				break;
			case NULL:
				endGame();
				break;
			default:
				break;
			}
		} else {
			endGame();
		}
	}

	private void moveBody() {

	}

	private void endGame() {
		gameIsOver = true;
	}

	// ---UTILITY-METHODS------------------------------------------------------/
	private boolean isHeadInsideBoard() {
		if (((int) getHeadPosition().getX() >= 0 && (int) getHeadPosition()
				.getX() < horizontalCells)
				&& ((int) getHeadPosition().getY() >= 0 && (int) getHeadPosition()
						.getY() < verticalCells)) {
			return true;
		} else {
			return false;
		}
	}

	private void setTile(int x, int y, Tiles tile) {
		if ((x >= 0 && x < horizontalCells) && (y >= 0 && y < verticalCells)) {
			tiles[x][y] = tile;
		} else {
		}
	}

	private Tiles getTile(int x, int y) {
		if ((x >= 0 && x < horizontalCells) && (y >= 0 && y < verticalCells)) {
			return tiles[x][y];
		} else {
			return Tiles.NULL;
		}
	}

	private Point getHeadPosition() {
		for (int y = 0; y < verticalCells; y++) {
			for (int x = 0; x < horizontalCells; x++) {
				if (getTile(x, y) == Tiles.SNAKEHEAD) {
					return new Point(x, y);
				}
			}
		}
		return null;
	}

	public void setHeadDirection(Directions direction) {
		headDirection = direction;
	}

	public Directions getOldHeadDirection() {
		return oldHeadDirection;
	}

	// TODO TEST
	private void pushBodyPositions(Point oldHeadPosition) {
		bodyPositions.push(oldHeadPosition);
		if (bodyPositions.size() > snakeLength) {
			bodyPositions.pollLast();
		}
	}

	public int getHorizontalCells() {
		return horizontalCells;
	}

	public int getVerticalCells() {
		return verticalCells;
	}

	public Tiles[][] getTiles() {
		return tiles;
	}

	public int getCellLength() {
		return cellLength;
	}

	public Dimension getBoardSize() {
		Dimension boardSize = new Dimension(horizontalCells * cellLength,
				verticalCells * cellLength);
		return boardSize;
	}

	public boolean isGameOver() {
		return gameIsOver;
	}

	// TODO FINISH THIS LOGICAL CLASS AND REFACTOR UPDATETILES

}
