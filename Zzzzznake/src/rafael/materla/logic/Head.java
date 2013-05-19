/*
 * Head
 * 
 * Version 1.0.0
 * 
 * 5/18/13
 * 
 * Author: Rafael Materla
 */

package rafael.materla.logic;

import java.awt.Color;

final class Head extends SnakePart {

	Directions direction;
	Directions oldDirection;

	// ---CONSTRUCTORS---------------------------------------------------------/
	Head(int x, int y) {
		super(x, y, Color.GREEN);
		direction = Directions.NONE;
	}

	public Head() {
		this(Board.HORIZONTAL_CELLS / 2, Board.VERTICAL_CELLS / 2);
	}

	// ---METHODS--------------------------------------------------------------/
	@Override
	void move() {
		switch (direction) {
		case NORTH:
			moveUp();
			break;
		case EAST:
			moveRight();
			break;
		case SOUTH:
			moveDown();
			break;
		case WEST:
			moveLeft();
			break;
		default:
			break;
		}
		oldDirection = direction;
	}

	private void moveUp() {
		setPosition(position.x, position.y - 1);
	}

	private void moveRight() {
		setPosition(position.x + 1, position.y);
	}

	private void moveDown() {
		setPosition(position.x, position.y + 1);
	}

	private void moveLeft() {
		setPosition(position.x - 1, position.y);
	}

	void setDirection(Directions direction) {
		this.direction = direction;
	}

	Directions getDirections() {
		return oldDirection;
	}
}