/*
 * Snake
 * 
 * Version 0.8
 * 
 * 5/1/13
 * 
 * I will push the shit out of you
 * Author: Rafael Materla
 */

package rafael.materla.logic;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;

public final class Snake implements KeyListener {

	// ---INSTANCE-VARIABLES---------------------------------------------------/
	private SnakeHead head;
	private Directions headDirection;
	private LinkedList<SnakeBody> body;
	private LinkedList<Point> headPositions;
	private String WTF = "DU BLÖDER HURENSOHN JETZT FUNK-FUCKING-KURWA-TIONIER";

	// ---CONSTRUCTORS---------------------------------------------------------/
	public Snake(int x, int y) {
		head = new SnakeHead(x, y);
		headDirection = Directions.NONE;
		body = new LinkedList<SnakeBody>();
		headPositions = new LinkedList<Point>();
	}

	public Snake() {
		this(Board.HORIZONTAL_CELLS / 2, Board.VERTICAL_CELLS / 2);
	}

	// ---METHODS--------------------------------------------------------------/
	void move() {
		head.move(headDirection);
		if (!head.isInsideBoard()) {
			Board.setGameOver();
		}
		pushBody();
	}

	void grow() {
		System.out
				.println("GROW :D! Length " + (headPositions.size() + 1) + "");
		body.add(new SnakeBody(headPositions.getLast()));
	}

	ArrayList<Figure> getSnake() {
		ArrayList<Figure> snake = new ArrayList<Figure>();
		snake.add(head);
		snake.addAll(body);
		return snake;
	}

	// TODO REPAIR THIS FUCKED UP METHOD (MAYBE IF(ONLY ONE HEAD) DO -1 )
	private void pushBody() {
		if (headPositions.size() > SnakeBody.getCount()) {
			headPositions.removeLast();
		}
		headPositions.addFirst(head.getPosition());
		for (SnakeBody snakeBody : body) {
			snakeBody.setPosition(headPositions.get(snakeBody.getID()));
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			headDirection = Directions.NORTH;
			break;
		case KeyEvent.VK_RIGHT:
			headDirection = Directions.EAST;
			break;
		case KeyEvent.VK_DOWN:
			headDirection = Directions.SOUTH;
			break;
		case KeyEvent.VK_LEFT:
			headDirection = Directions.WEST;
			break;
		}
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
