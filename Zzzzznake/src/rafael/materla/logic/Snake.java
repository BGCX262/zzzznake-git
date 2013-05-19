/*
 * Snake
 * 
 * Version 1.0.0
 * 
 * 5/18/13
 * 
 * Author: Rafael Materla
 */

package rafael.materla.logic;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public final class Snake implements KeyListener {

	// ---INSTANCE-VARIABLES---------------------------------------------------/
	private Head head;
	private LinkedList<SnakePart> snake;

	// ---CONSTRUCTORS---------------------------------------------------------/
	public Snake(int x, int y) {
		head = new Head(x, y);
		snake = new LinkedList<SnakePart>();
		snake.add(head);
		grow();
		grow();
	}

	public Snake() {
		this(Board.HORIZONTAL_CELLS / 2, Board.VERTICAL_CELLS / 2);
	}

	// ---METHODS--------------------------------------------------------------/
	void move() {
		Point oldPartPosition = null;
		for (SnakePart part : snake) {
			if (part.getID() == 0) {
				part.move();
				oldPartPosition = part.getOldPosition();
			} else {
				part.setPosition(oldPartPosition);
				oldPartPosition = part.getOldPosition();
			}
		}
	}

	void grow() {
		snake.add(new Body(snake.get(SnakePart.getCount() - 1).getOldPosition()));
	}

	Head getHead() {
		return head;
	}

	LinkedList<SnakePart> getSnake() {
		return snake;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if (head.getDirections() != Directions.SOUTH) {
				head.setDirection(Directions.NORTH);
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (head.getDirections() != Directions.WEST) {
				head.setDirection(Directions.EAST);
			}
			break;
		case KeyEvent.VK_DOWN:
			if (head.getDirections() != Directions.NORTH) {
				head.setDirection(Directions.SOUTH);
			}
			break;
		case KeyEvent.VK_LEFT:
			if (head.getDirections() != Directions.EAST) {
				head.setDirection(Directions.WEST);
			}
			break;
		}
	}

	boolean isInsideBoard() {
		return head.isInsideBoard();
	}

	Point getPosition() {
		return head.getPosition();
	}

	// ---UNUSED-METHODS-------------------------------------------------------/
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}