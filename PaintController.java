package game;
import game.entities.Entity;
import game.interfaces.Updateable;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class PaintController extends Canvas implements KeyListener {
	private static final long serialVersionUID = 1L;
	
	private GameWindow window;
	
	private BufferStrategy buffer;
	private BufferedImage bufferedImage;
	private Graphics2D g2d;
	private ArrayList<Entity> entities;
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private int frames;
	private int fps;
	private long currentTime;
	private long lastTime;
	private long totalTime;
	
	public PaintController(GameWindow w) {
		window = w;
		
		setIgnoreRepaint(true);
		setSize(WIDTH, HEIGHT);
		
		window.add(this);
		window.pack();
	}
	
	public void initializeValues() {
		g2d = null;
		
		addKeyListener(this);
		
		createBufferStrategy(2);
		buffer = getBufferStrategy();
		
		setFocusable(true);

	    GraphicsEnvironment ge =  GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice gd = ge.getDefaultScreenDevice();
	    GraphicsConfiguration gc = gd.getDefaultConfiguration();
	    
	    bufferedImage = gc.createCompatibleImage(WIDTH, HEIGHT);
		
		loadEntities();
		
		frames = 0;
		currentTime = lastTime = window.runTimeMillis();
	}

	public void update() {
		lastTime = currentTime;
		currentTime = window.runTimeMillis();
		totalTime += currentTime - lastTime;
		
		if(totalTime > 1000) {
			totalTime -= 1000;
			fps = frames;
			frames = 0;
		}
		
		loadEntities();
		
		try {
			
			g2d = bufferedImage.createGraphics();
			
		    RenderingHints rh = new RenderingHints(
		             RenderingHints.KEY_ANTIALIASING,
		             RenderingHints.VALUE_ANTIALIAS_ON);
		    g2d.setRenderingHints(rh);
		    
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0, 0, WIDTH, HEIGHT);
			
			for(Entity e : entities) {
				e.repaint(g2d);
			}
			
			g2d.setColor(Color.GREEN);
			g2d.drawString(String.format("fps %s", fps), 20, 20);
			
			buffer.getDrawGraphics().drawImage(bufferedImage, 0, 0, null);
			
			if(!buffer.contentsLost())
				buffer.show();
			
		} finally {
			if(g2d != null)
				g2d.dispose();
		}
		
		frames++;
	}
	
	public void loadEntities() {
		entities = window.getEntityController().getEntities();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		window.getEntityController().addEvent(KeyEvent.KEY_PRESSED, e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		window.getEntityController().addEvent(KeyEvent.KEY_RELEASED, e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		window.getEntityController().addEvent(KeyEvent.KEY_TYPED, e);
	}
}
