package game.entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import game.calculations.Collision;
import game.calculations.Constants;
import game.interfaces.Controllable;
import game.utilities.Keyboard;

public class Player extends Entity implements Controllable {
	
	private double velocity = 100;
	private double running = 1;
	
	private ArrayList<Point> tail;
	
	private long cooldown;
	private long used;
	private long burstUse;
	private long burstCooldown;
	
	private Keyboard keyboard;
	
	public Player(int a, int b) {
		super(a, b);
		
		used = 0;
		depth = 0;
		cooldown = 50;
		
		burstCooldown = 2000;
		burstUse = 0;
		
		keyboard = new Keyboard();
		tail = new ArrayList<Point>();
	}
	
	public void update(double elapsed) {
		tail.add(new Point(centerXInt(), centerYInt()));
		while(tail.size() > 200)
			tail.remove(0);
		if(x < 0)
			x += 800;
		if(x > 800)
			x -= 800;
		if(y < 0)
			y += 600;
		if(y > 600)
			y -= 600;
		running = keyboard.isPressed(KeyEvent.VK_SHIFT) ? 2.5 : 1.0;
		
		boolean right = keyboard.isPressed(KeyEvent.VK_D, KeyEvent.VK_RIGHT);
		boolean up = keyboard.isPressed(KeyEvent.VK_W, KeyEvent.VK_UP);
		boolean left = keyboard.isPressed(KeyEvent.VK_A, KeyEvent.VK_LEFT);
		boolean down = keyboard.isPressed(KeyEvent.VK_S, KeyEvent.VK_DOWN);

		if(up)
			velocity = 100f;
		else if(down)
			velocity = -100f;
		else
			velocity = 0f;
		
		if(right)
			rotate(2 * Constants.PI * elapsed);
		
		if(left)
			rotate(-2 * Constants.PI * elapsed);
		
		move(theta, running * velocity, elapsed);
		
		if(keyboard.isPressed(KeyEvent.VK_R))
			setCenter(400, 300);
		
		if(keyboard.isPressed(KeyEvent.VK_E)){
			if(used == 0)
				used = System.currentTimeMillis();
		}
		if(keyboard.isPressed(KeyEvent.VK_Q)){
			if(System.currentTimeMillis() - burstUse > burstCooldown) {
				burstUse = System.currentTimeMillis();
				for(int i = 0; i < 360; i += 10) {
					entityController.createEntity(new Bullet(centerXInt(), centerYInt(), Math.toRadians(i)));
				}
			}
		}
		if(keyboard.isPressed(KeyEvent.VK_SPACE)){
			if(System.currentTimeMillis() - used > cooldown) {
				used = System.currentTimeMillis();
				entityController.createEntity(new Bullet(centerXInt(), centerYInt(), theta));
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyboard.pressKey(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyboard.releaseKey(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void repaint(Graphics2D g) {
		Stroke s = g.getStroke();
		for(int i = 0; i < tail.size() - 1; ++i){
			Point a = tail.get(i);
			Point b = tail.get(i+1);
			g.setStroke(new BasicStroke(6));
			g.setColor(new Color(255, 140, 0, i/2));
			g.drawLine(a.x, a.y, b.x, b.y);
		}
		g.setStroke(s);
		
		g.setColor(Color.YELLOW);
		g.fillRect(leftInt(), topInt(), widthInt(), heightInt());
		g.setColor(Color.red);
		g.drawLine(centerXInt(), 
				centerYInt(), 
				centerXInt() + (int)(Math.cos(theta) * 50), 
				centerYInt() + (int)(Math.sin(theta) * 50));
		//g.drawImage(getImage(), leftInt(), topInt(), null);
	}

	@Override
	public void handleCollision(Entity e, Collision c) {
		if(e instanceof Block) {
			switch(c) {
			case ABOVE:
				setPosition(x, e.bottomInt() + 1);
				break;
			case BELOW:
				setPosition(x, e.topInt() - heightInt() - 1);
				break;
			case LEFT:
				setPosition(e.rightInt() + 1, y);
				break;
			case RIGHT:
				setPosition(e.leftInt() - widthInt() - 1, y);
				break;
			}
		}
		if(e instanceof Ball){
			e.destroy();
		}
	}

}
