/*
 * SnakeBody
 * 
 * Version 0.8
 * 
 * 5/1/13
 * 
 * Author: Rafael Materla
 */

package rafael.materla.logic;

import java.awt.Color;
import java.awt.Point;

final class SnakeBody extends Figure {

	private static int count = 0;
	private int id;

	// ---CONSTRUCTORS---------------------------------------------------------/
	SnakeBody(int x, int y) {
		super(x, y, Color.GREEN, "SnakeBody");
		id = count++;
	}

	SnakeBody(Point point) {
		this(point.x, point.y);
	}

	// ---METHODS--------------------------------------------------------------/
	public static int getCount() {
		return count;
	}

	public int getID() {
		return id;
	}

	public void setPosition(Point point) {
		position.setLocation(point);
	}
}
