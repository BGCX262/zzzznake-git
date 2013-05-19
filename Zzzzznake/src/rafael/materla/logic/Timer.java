/*
 * Timer
 * 
 * Version 1.0.0
 * 
 * 5/18/13
 * 
 * Author: Rafael Materla
 */

package rafael.materla.logic;

import rafael.materla.gui.Painter;

/*
 * The Timer class times the events that have to happen to make the game run
 * (update board, paint it to the screen, wait, repeat)
 * Mate. Feed. Kill. Repeat. $tay ($ic)
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
		while (true) {
			if (lastFrame + 66000000 <= System.nanoTime()
					&& !Board.isGameOver()) {
				boardReference.updateTiles();
				painterReference.repaint();
				lastFrame = System.nanoTime();
			} else if (Board.isGameOver()) {
				painterReference.repaint();
			}
		}
	}
}