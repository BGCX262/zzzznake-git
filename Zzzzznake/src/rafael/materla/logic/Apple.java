/*
 * Apple
 * 
 * Version 1.0.0
 * 
 * 5/18/13
 * 
 * Author: Rafael Materla
 */

package rafael.materla.logic;

import java.awt.Color;

public final class Apple extends Figure {

	Apple(int x, int y) {
		super(x, y, Color.RED);
	}
	Apple() {
		super((int) (Math.random() * Board.HORIZONTAL_CELLS), (int) (Math
				.random() * Board.VERTICAL_CELLS), Color.RED);
	}
}
