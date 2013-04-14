/*
 * Timer
 * 
 * Version 0.7.5
 * 
 * 4/8/13
 * 
 * Author: Rafael Materla
 */

package rafael.materla.logic;

import rafael.materla.gui.Painter;

/*
 * The Timer class times the events that have to happen to make the game run
 * (update board, paint it to the screen, wait, repeat)
 */
public class Timer {

	// ---INSTANCE-VARIABLES---------------------------------------------------/
	private long lastFrame;
	private Board boardReference;
	private Painter painterReference;

	// ---CONSTRUCTOR----------------------------------------------------------/
	public Timer(Board boardObject, Painter painterObject) {
		lastFrame = System.nanoTime();
		boardReference = boardObject;
		painterReference = painterObject;
	}

	// ---METHODS--------------------------------------------------------------/
	public void start() {
		while (!boardReference.isGameOver()) {
			if (lastFrame + 100000000 <= System.nanoTime()) {
				boardReference.updateTiles();
				painterReference.repaint();
				lastFrame = System.nanoTime();
			}
		}
	}

}
