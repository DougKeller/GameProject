package game.utilities;

import java.awt.event.KeyEvent;
import java.util.HashMap;

public class Keyboard {
	
	private static HashMap<Integer, Boolean> inputs = new HashMap<Integer, Boolean>();
	
	public static void pressKey(KeyEvent e) {
		inputs.put(e.getKeyCode(), true);
	}
	public static void releaseKey(KeyEvent e) {
		inputs.put(e.getKeyCode(), false);
	}
	
	public static boolean isPressed() {
		return inputs.containsValue(true);
	}
	
	public static boolean isPressed(int... vals) {
		for(int i : vals) {
			if(inputs.containsKey(i))
				return inputs.get(i);
		}
		return false;
	}
}
