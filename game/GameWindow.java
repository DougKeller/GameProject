package game;

import game.utilities.Keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


public class GameWindow extends JFrame implements KeyListener {
	private int score;
	
	public static final String TITLE = "Douglas Keller";
	
	public void initUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(TITLE);
		setIgnoreRepaint(true);
		
		setLocationRelativeTo(null);
		

		
		addKeyListener(this);
		
		score = 0;
	}
	
	public void setVisible(boolean b) {
		super.setVisible(b);
		initUI();
		setResizable(false);
	}
	
	public void incrementScore() {
		score++;
	}
	
	public int getScore() {
		return score;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		Keyboard.pressKey(arg0);
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		Keyboard.releaseKey(arg0);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
