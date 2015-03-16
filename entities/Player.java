package game.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import game.calculations.Collision;
import game.calculations.Constants;
import game.interfaces.Controllable;
import game.utilities.Keyboard;

public class Player extends Entity implements Controllable {
	
	private double velocity = 100;
	private double running = 1;
	private double theta = -Constants.PI / 2;
	
	private Keyboard keyboard;
	
	public Player(int a, int b) {
		super(a, b);
		
		keyboard = new Keyboard();
		
		setTexture("C:\\Users\\Douglas\\Documents\\eclipse\\workspace\\Game\\src\\star.png");
		width = texture.getWidth();
		height = texture.getHeight();
	}
	
	public void update(double elapsed) {
		running = keyboard.isPressed(KeyEvent.VK_SPACE) ? 3.0 : 1.0;
		
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
		
		if(right) {
			rotate(2 * Constants.PI * elapsed);
		}
		
		if(left) {
			rotate(2 * Constants.PI * elapsed);
		}
		
		move(theta, running * velocity, elapsed);
		
		if(keyboard.isPressed(KeyEvent.VK_R))
			setPosition(400, 300);
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
		g.drawImage(rotatedImage(), (int)left(), (int)top(), null);
	}
	
	public void rotate(double a) {
		theta += a;
		if(theta > 2*Constants.PI)
				theta -= 2*Constants.PI;
		if(theta < 0)
			theta += 2*Constants.PI;
	}
	
	public BufferedImage rotatedImage() {

		BufferedImage bi = new BufferedImage((int)width, (int)height, texture.getType());
		Graphics2D g = bi.createGraphics();
		g.rotate(theta, width/2, height/2);
		g.drawImage(texture, null, 0, 0);
		return bi;
	}

	@Override
	public void handleCollision(Entity e, Collision c) {
	}

}
