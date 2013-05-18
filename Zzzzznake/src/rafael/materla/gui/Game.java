/*
 * Game
 * 
 * Version 1.0.0
 * 
 * 5/18/13
 * 
 * Author: Rafael Materla
 */

package rafael.materla.gui;

/*
 * The Game class creates the game window and HAS-A reference to everything.
 * The main method is also found in this class.
 */
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

import rafael.materla.logic.Board;
import rafael.materla.logic.Timer;

public class Game {

	// ---INSTANCE-VARIABLES---------------------------------------------------/
	private JFrame frame;
	private Painter panel;
	private Point screenMiddle;
	private Point windowLocation;
	private Board board;
	private Timer clock;

	// ---CONSTRUCTOR----------------------------------------------------------/
	public Game(String name) {
		board = new Board();
		frame = new JFrame(name);
		panel = new Painter();
		clock = new Timer(board, panel);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();

		// ---MORE-COMPLEX-INITIALIZER-----------------------------------------/
		{
			// Calculates the middle of the users screen
			screenMiddle = new Point(((int) Toolkit.getDefaultToolkit()
					.getScreenSize().getWidth() / 2), ((int) Toolkit
					.getDefaultToolkit().getScreenSize().getHeight() / 2));

			// Calculates the position where the frame has to be so it is in the
			// middle of the screen
			windowLocation = new Point(
					(int) (screenMiddle.getX() - (int) (frame.getSize()
							.getWidth() / 2)), (int) screenMiddle.getY()
							- (int) (frame.getSize().getHeight() / 2));
		}
		frame.setLocation(windowLocation);
		frame.setVisible(true);
		clock.start();
	}

	// ---MAIN-----------------------------------------------------------------/
	public static void main(String[] args) {
		new Game("Zzzzznake");
	}
}
