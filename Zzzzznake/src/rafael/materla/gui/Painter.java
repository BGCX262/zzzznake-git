/*
 * Painter
 * 
 * Version 1.0.0
 * 
 * 5/18/13
 * 
 * Author: Rafael Materla
 */

package rafael.materla.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import rafael.materla.logic.Board;
import rafael.materla.logic.Figure;

/*
 * The Painter class gets the informations about the visual informations about 
 * the board and paints them to the users display.
 */
public class Painter extends JPanel {

	// ---INSTANCE-VARIABLES---------------------------------------------------/
	private static final long serialVersionUID = 3597547278483158168L;

	// ---CONSTRUCTOR----------------------------------------------------------/
	public Painter() {
		addKeyListener(Board.getSnake());
		setPreferredSize(Board.getBoardSize());
		setBackground(Color.BLACK);
		setFocusable(true);
		setRequestFocusEnabled(true);
		setDoubleBuffered(true);
		setIgnoreRepaint(true);
		setFont(new Font("GAME OVER", Font.BOLD, 30));
	}

	// ---METHODS--------------------------------------------------------------/
	// TODO REFACTOR GAME OVER SCREEN
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!Board.isGameOver()) {
			paintTiles(g);
		} else {
			g.setColor(Color.RED);
			g.drawString("GAME OVER",
					(int) Board.getBoardSize().getWidth() / 3, (int) Board
							.getBoardSize().getHeight() / 2);
		}
	}

	private void paintTiles(Graphics g) {
		for (Figure figure : Board.getTiles()) {
			g.setColor(figure.getColor());
			g.fillRect(figure.getX() * Board.CELL_LENGTH, figure.getY()
					* Board.CELL_LENGTH, Board.CELL_LENGTH, Board.CELL_LENGTH);
		}
	}
}
