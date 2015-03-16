package game.utilities;

import java.awt.event.KeyEvent;
import java.util.HashMap;


public class Keyboard {
	
	private HashMap<Integer, Boolean> inputs;
	
	public Keyboard() {
		inputs = new HashMap<Integer, Boolean>();
	}
	
	public void pressKey(KeyEvent e) {
		inputs.put(e.getKeyCode(), true);
	}
	public void releaseKey(KeyEvent e) {
		inputs.put(e.getKeyCode(), false);
	}
	
	public boolean isPressed(int... vals) {
		for(int i : vals) {
			if(inputs.containsKey(i))
				return inputs.get(i);
		}
		return false;
	}
}
