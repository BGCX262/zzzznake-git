/*
 * Painter
 * 
 * Version 0.7.5
 * 
 * 4/7/13
 * 
 * Author: Rafael Materla
 */

package rafael.materla.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import rafael.materla.logic.Board;
import rafael.materla.logic.Directions;
import rafael.materla.logic.Tiles;

/*
 * The Painter class gets the informations about the visual informations about 
 * the board and paints them to the users display.
 */
public class Painter extends JPanel implements KeyListener {

	// ---INSTANCE-VARIABLES---------------------------------------------------/
	private static final long serialVersionUID = 3597547278483158168L;
	private final Board board;

	// ---CONSTRUCTOR----------------------------------------------------------/
	public Painter(Board boardObject) {
		board = boardObject;

		addKeyListener(this);
		setPreferredSize(board.getBoardSize());
		setBackground(Color.BLACK);
		setFocusable(true);
		setRequestFocusEnabled(true);
		setDoubleBuffered(true);
		setIgnoreRepaint(true);
		setFont(new Font("GAME OVER", Font.BOLD, 30));
	}

	// ---METHODS--------------------------------------------------------------/
	// TODO CHANGE TO KEY BINDINGS
	@Override
	public void keyPressed(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_UP:
			if(board.getOldHeadDirection() != Directions.SOUTH){
			board.setHeadDirection(Directions.NORTH);
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(board.getOldHeadDirection() != Directions.WEST){
			board.setHeadDirection(Directions.EAST);
			}
			break;
		case KeyEvent.VK_DOWN:
			if(board.getOldHeadDirection() != Directions.NORTH){
			board.setHeadDirection(Directions.SOUTH);
			}
			break;
		case KeyEvent.VK_LEFT:
			if(board.getOldHeadDirection() != Directions.EAST){
			board.setHeadDirection(Directions.WEST);
			}
			break;
		default:
			break;
		}
	}

	// TODO REFACTOR GAME OVER SCREEN
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!board.isGameOver()) {
			paintTiles(g);
		} else {
			g.setColor(Color.RED);
			g.drawString("GAME OVER", (int) board.getBoardSize().getWidth() / 3,
					(int) board.getBoardSize().getHeight() / 2);
		}
	}

	private void paintTiles(Graphics g) {
		Tiles[][] tiles = board.getTiles();
		for (int y = 0; y < board.getVerticalCells(); y++) {
			for (int x = 0; x < board.getHorizontalCells(); x++) {
				switch (tiles[x][y]) {
				case SNAKEHEAD:
					g.setColor(Color.GREEN);
					g.fillRect(x * board.getCellLength(),
							y * board.getCellLength(), board.getCellLength(),
							board.getCellLength());
					break;
				case SNAKEBODY:
					g.setColor(Color.GREEN);
					g.fillRect(x * board.getCellLength(),
							y * board.getCellLength(), board.getCellLength(),
							board.getCellLength());
					break;
				case APPLE:
					g.setColor(Color.RED);
					g.fillOval(x * board.getCellLength(),
							y * board.getCellLength(), board.getCellLength(),
							board.getCellLength());
					break;
				default:
					break;
				}
			}
		}
	}

	// Those had to be implemented because of the KeyListener interface but
	// are not used in the program
	// ---UNUSED---------------------------------------------------------------/
	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}