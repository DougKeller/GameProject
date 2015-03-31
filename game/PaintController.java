package game;
import game.entities.ConcreteEntity;
import game.entities.Entity;
import game.utilities.Time;
import game.utilities.Keyboard;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


public class PaintController extends Canvas {
	private static final long serialVersionUID = 1L;
	
	private BufferStrategy buffer;
	private BufferedImage bufferedImage;
	private Graphics2D g2d;
	
	
	public static int score;
	
	private int frames;
	private int fps;
	private long totalTime;
	
	public PaintController() {
		setIgnoreRepaint(true);
		setSize(300, 400);
		setPreferredSize(new Dimension(300, 400));
	}
	
	public void initializeValues() {
		g2d = null;
		
		createBufferStrategy(2);
		buffer = getBufferStrategy();
		
		setFocusable(true);

	    GraphicsEnvironment ge =  GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice gd = ge.getDefaultScreenDevice();
	    GraphicsConfiguration gc = gd.getDefaultConfiguration();
	    
	    bufferedImage = gc.createCompatibleImage(300, 400);
		
		frames = 0;
	}

	public void update() {
		totalTime += Time.LOOP_DURATION();
		
		if(totalTime > 1000) {
			totalTime -= 1000;
			fps = frames;
			frames = 0;
		}
		
		try {
			Game.camera.update();
			g2d = bufferedImage.createGraphics();

			g2d.translate(-Game.camera.x, -Game.camera.y);
			
		    RenderingHints rh = new RenderingHints(
		             RenderingHints.KEY_ANTIALIASING,
		             RenderingHints.VALUE_ANTIALIAS_ON);
		    g2d.setRenderingHints(rh);
		    
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0, 0, Environment.WIDTH, Environment.HEIGHT);
			
			for(Entity e : Entity.entities) {
				if(e.visible() && e instanceof ConcreteEntity)
				((ConcreteEntity)e).repaint(g2d);
			}
			
			g2d.setColor(Color.WHITE);
			g2d.drawString("" + Time.RUNTIME()/1000, Game.camera.x + 20, Game.camera.y + 20);
			
			g2d.setColor(Color.GREEN);
			g2d.drawString(String.format("fps %s", fps), Game.camera.x + 20, Game.camera.y + 50);
			g2d.drawString(String.format("score: %s", score), Game.camera.x + 20, Game.camera.y + 80);
			g2d.drawString(String.format("ticks: %s", Time.TICKS()), Game.camera.x + 20, Game.camera.y + 110);
			
			buffer.getDrawGraphics().drawImage(bufferedImage, 0, 0, null);
			
			if(!buffer.contentsLost())
				buffer.show();
			
		} finally {
			if(g2d != null)
				g2d.dispose();
		}
		
		frames++;
	}
}
