package game.interfaces;

import java.awt.event.KeyEvent;

public interface Controllable {
	public void keyPressed(KeyEvent e);
	public void keyReleased(KeyEvent e);
	public void keyTyped(KeyEvent e);
}
