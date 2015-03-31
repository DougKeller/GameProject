package game.entities;

import game.Game;
import game.utilities.C;
import game.utilities.Keyboard;
import game.utilities.Location;
import game.utilities.Timer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;



public class Player extends PlayerEntity {
	
	protected double rpm = C.TAU;
	//protected double velocityPerSec = 10 * Time.TICKS_PER_SECOND;
	protected double running;
	
	protected long tickUsed;
	protected double missileTheta = -100;
	int ballsdestroyed;
	
	protected ArrayList<Tail> tail;
	
	protected Timer cooldown1;
	protected Timer cooldown2;

	// *** Constructors *** //
	public Player(double x, double y) {
		this(new Location(x, y));
	}	
	public Player(Location l) {
		super(l);
		cooldown1 = new Timer(100);
		cooldown2 = new Timer(1000);
		tail = new ArrayList<Tail>();
		ballsdestroyed = 0;
		Game.camera.setSource(loc);
	}
	
	// *** Inherited functions *** // 
	@Override
	public void repaint(Graphics2D g) {
		g.setColor(Color.YELLOW);
		g.fillRect(left(), top(), width(), height());
		g.setColor(Color.red);
		g.drawLine(loc.x(), loc.y(), loc.x() + (int)(Math.cos(theta) * 30), loc.y() + (int)(Math.sin(theta) * 30));
	}
	@Override
	public void handleCollision(Entity e) {
		if(e instanceof Ball) 
			ballsdestroyed+=8;
		if(e instanceof Tail) {
			Game.exit();
		}
	}
	@Override
	public void tick() {
		
		tail.add(new Tail(location()));
		while(tail.size() > ballsdestroyed) {
			tail.get(0).destroy();
			tail.remove(0);
		}

		boolean w = Keyboard.isPressed(KeyEvent.VK_W) || Keyboard.isPressed(KeyEvent.VK_UP);
		boolean a = Keyboard.isPressed(KeyEvent.VK_A) || Keyboard.isPressed(KeyEvent.VK_LEFT);
		boolean s = Keyboard.isPressed(KeyEvent.VK_S) || Keyboard.isPressed(KeyEvent.VK_DOWN);
		boolean d = Keyboard.isPressed(KeyEvent.VK_D) || Keyboard.isPressed(KeyEvent.VK_RIGHT);
		
		
		if(w && !s && theta != C.DOWN)
			setTheta(C.UP);
		else if(s && !w && theta != C.UP)
			setTheta(C.DOWN);
		else if(a && !d && theta != C.RIGHT)
			setTheta(C.LEFT);
		else if(d && !a && theta != C.LEFT)
			setTheta(C.RIGHT);
			
		move(10);
		
		if(cooldown1.complete() && Keyboard.isPressed(KeyEvent.VK_SPACE)) {
			cooldown1.restart();
			new Bullet(this);
		}
	}
	
}
