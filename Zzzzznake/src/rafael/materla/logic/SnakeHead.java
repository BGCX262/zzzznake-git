/*
 * SnakeHead
 * 
 * Version 0.8
 * 
 * 5/1/13
 * 
 * Author: Rafael Materla
 */

package rafael.materla.logic;

import java.awt.Color;

final class SnakeHead extends Figure {

	// ---CONSTRUCTORS---------------------------------------------------------/
	SnakeHead(int x, int y) {
		super(x, y, Color.GREEN, "SnakeHead");
	}

	public SnakeHead() {
	}

	// ---METHODS--------------------------------------------------------------/
	void move(Directions direction) {
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
	}

	private void moveUp() {
		position.y--;
	}

	private void moveRight() {
		position.x++;
	}

	private void moveDown() {
		position.y++;
	}

	private void moveLeft() {
		position.x--;
	}
}
