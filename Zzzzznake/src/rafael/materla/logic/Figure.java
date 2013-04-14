package rafael.materla.logic;

import java.awt.Point;

public abstract class Figure {

	// ---INSTANCE-VARIABLES---------------------------------------------------/
	Point position;

	// ---CONSTRUCTORS----------------------------------------------------------/
	public Figure(int x, int y) {
		position = new Point(x, y);
	}

	public Figure() {
		this(0, 0);
	}
	
	void setPosition(int x, int y){
		position = new Point(x, y);
	}
	
	Point getPosition(){
		return position;
	}
	
	abstract void onCollision();
	
}
