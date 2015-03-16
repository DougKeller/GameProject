package game;

import javax.swing.JFrame;


public class GameWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private int score;
	
	private long startTimeMillis;
	private long timePassed;

	private boolean paused;
	
	public static final String TITLE = "Douglas Keller";
	
	Environment environment;
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
		
		environment = new Environment
				(this, "C:\\Users\\Douglas\\Documents\\eclipse\\workspace\\Game\\src\\level.svw");
		entityController = environment.getEntityController();
		paintController = new PaintController(this);
		
		setLocationRelativeTo(null);
		
		timePassed = 0;
		score = 0;
		paused = false;
	}
	
	public void run() {
		setVisible(true);
		startTimeMillis = System.currentTimeMillis();
		
		paintController.initializeValues();
		
		long timeBegin, timeElapsed = 0;
		
		while(isVisible()) {
				timeBegin = runTimeMillis();
				
				if(!paused)
					entityController.update(timeElapsed);
				
				paintController.update();
				
				timeElapsed = runTimeMillis() - timeBegin;
		}
	}
	
	public long runTimeMillis() {
		return System.currentTimeMillis() - startTimeMillis;
	}
	
	public void pause(boolean b){
		paused = b;
	}
	
	public EntityController getEntityController() {
		return entityController;
	}
	
	public void incrementScore() {
		score++;
	}
	
	public int getScore() {
		return score;
	}
	
	public long deltaTime() {
		return timePassed;
	}

}
