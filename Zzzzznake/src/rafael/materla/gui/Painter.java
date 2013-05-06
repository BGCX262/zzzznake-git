/*
 * Painter
 * 
 * Version 0.8
 * 
 * 5/1/13
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
	private final Board board;

	// ---CONSTRUCTOR----------------------------------------------------------/
	public Painter(Board boardObject) {
		board = boardObject;

		addKeyListener(board.getSnake());
		setPreferredSize(board.getBoardSize());
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
					(int) board.getBoardSize().getWidth() / 3, (int) board
							.getBoardSize().getHeight() / 2);
		}
	}

	private void paintTiles(Graphics g) {
		for (Figure figure : board.getTiles()) {
			g.setColor(figure.getColor());
			g.fillRect(figure.getX() * Board.CELL_LENGTH, figure.getY()
					* Board.CELL_LENGTH, Board.CELL_LENGTH, Board.CELL_LENGTH);
		}
	}
}
