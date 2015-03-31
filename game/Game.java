package game;

import java.awt.event.KeyEvent;

import game.entities.Entity;
import game.utilities.Time;
import game.utilities.Keyboard;


public class Game {
	
	private static long millis;
	private static long fpsmillis;
	
	public static Camera camera;
	
	public static GameWindow window;
	public static PaintController paint;
	
	public static void run() {
		camera = new Camera();
		Environment.load("C:\\Users\\Douglas\\Documents\\eclipse\\workspace\\Game\\src\\level.svw");
		window = new GameWindow();
		paint = new PaintController();
		camera.width = 300;
		camera.height = 400;
		
		window.add(paint);
		window.pack();
		window.setVisible(true);
		
		paint.initializeValues();
		millis = fpsmillis = 0;
		Time.START();
		while(window.isVisible() && !Keyboard.isPressed(KeyEvent.VK_ESCAPE)) {
			if(Keyboard.isPressed(KeyEvent.VK_ENTER))
				Time.PAUSE();
			else
				Time.RESUME();
			Time.START_LOOP();
			loop();
			Time.END_LOOP();
		}
		
		window.setVisible(false);
	}
	
	public static void loop() {
		if(!Time.PAUSED()) {
			millis += Time.LOOP_DURATION();
			fpsmillis += Time.LOOP_DURATION();
			while(millis > Time.MILLIS_PER_TICK) {
				Time.TICK();
				millis -= Time.MILLIS_PER_TICK;
				for(int i = 0; i < Entity.entities.size(); ++i) {
					Entity a = Entity.entities.get(i);
					for(int j = i + 1; j < Entity.entities.size(); ++j) {
						Entity b = Entity.entities.get(j);
						Entity.testCollision(a, b);
					}
					a.tick();
				}
				
				while(Entity.removed.size() > 0) {
					Entity.removed.get(0).remove();
				}
			}
			//while(fpsmillis >= 16) {
			//	fpsmillis -= 16;
				paint.update();
			//a}
		}
	}
	public static void exit() {
		window.setVisible(false);
	}
}
