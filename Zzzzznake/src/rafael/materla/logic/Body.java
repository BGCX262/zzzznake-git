/*
 * Body
 * 
 * Version 1.0.0
 * 
 * 5/18/13
 * 
 * Author: Rafael Materla
 */

package rafael.materla.logic;

import java.awt.Color;
import java.awt.Point;

final class Body extends SnakePart {

	// ---CONSTRUCTORS---------------------------------------------------------/
	Body(int x, int y) {
		super(x, y, Color.GREEN);
	}

	Body(Point point) {
		this(point.x, point.y);
	}

	// ---METHODS--------------------------------------------------------------/
	void move(Point position){
		setPosition(position);
	}
	
	protected void setPosition(Point point) {
		setOldPosition(position);
		position.setLocation(point);
	}
}
