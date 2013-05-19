/*
 * Figure
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

public abstract class Figure {

	// ---INSTANCE-VARIABLES---------------------------------------------------/
	protected Point position;
	protected final Color figureColor;
	protected String name;

	// ---CONSTRUCTORS---------------------------------------------------------/
	Figure(int x, int y, Color color) {
		position = new Point(x, y);
		figureColor = color;
	}

	Figure() {
		this(0, 0, Color.WHITE);
	}

	// ---METHODS--------------------------------------------------------------/
	protected void setPosition(int x, int y) {
		position.setLocation(x, y);
	}

	protected void setPosition(Point position) {
		position.setLocation(position);
	}

	protected Point getPosition() {
		return position;
	}

	public int getX() {
		return (int) position.getX();
	}

	public int getY() {
		return (int) position.getY();
	}

	public boolean isInsideBoard() {
		return (getX() >= 0 && getX() < Board.HORIZONTAL_CELLS)
				&& (getY() >= 0 && getY() < Board.VERTICAL_CELLS);
	}

	public Color getColor() {
		return figureColor;
	}

	public String getName() {
		return name;
	}
}