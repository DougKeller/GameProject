package game;

import javax.swing.JFrame;


public class GameWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static final long TICK_LENGTH = 15;
	
	private long startTimeMillis;
	private long timePassed;
	
	
	public static final String TITLE = "Douglas Keller";
	
	EntityController entityController;
	PaintController paintController;
	
	public GameWindow() {
		initUI();
	}
	
	public void initUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle(TITLE);
		setIgnoreRepaint(true);
		
		entityController = new EntityController(this);
		paintController = new PaintController(this);
		
		setLocationRelativeTo(null);
		
		timePassed = 0;
	}
	
	public void run() {
		setVisible(true);
		startTimeMillis = System.currentTimeMillis();
		
		paintController.initializeValues();
		
		long timeBegin, timeElapsed = 0;
		
		while(isVisible()) {
			timeBegin = runTimeMillis();
			
			entityController.update(timeElapsed);
			
			paintController.update();
			
			timeElapsed = runTimeMillis() - timeBegin;
		}
	}
	
	public long runTimeMillis() {
		return System.currentTimeMillis() - startTimeMillis;
	}
	
	public EntityController getEntityController() {
		return entityController;
	}
	
	public static void main(String[] args) {
		GameWindow window = new GameWindow();
		window.run();
	}
	
	public long deltaTime() {
		return timePassed;
	}

}
