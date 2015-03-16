package game.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.calculations.Collision;
import game.calculations.Movement;
import game.interfaces.Updateable;

public abstract class Entity implements Updateable {
	protected double x;
	protected double y;
	
	protected double width;
	protected double height;
	
	protected boolean destroyed;
	protected boolean dying;
	
	protected BufferedImage texture;
	
	public Entity(int a, int b) {
		x = a;
		y = b;
		width = 10;
		height = 10;
		destroyed = false;
	}
	
	public void destroy() {
		destroyed = true;
	}
	
	public boolean destroyed() {
		return destroyed;
	}
	
	public abstract void repaint(Graphics2D g);
	
	public double getX() { return x; }
	public double getY() { return y; }
	public double getWidth() { return width; }
	public double getHeight() { return height; }
	
	public double left() { return x - width / 2; }
	public double right() { return x + width / 2; }
	public double top() { return y - height / 2; }
	public double bottom() { return y + height / 2; }
	
	public abstract void handleCollision(Entity e, Collision c);
	
	public static void checkCollision(Entity a, Entity b) {
		if(!a.collidesWith(b))
			return;
		
		a.handleCollision(b, Collision.ABOVE);
		b.handleCollision(a, Collision.ABOVE);
	}
	
	public boolean collidesWith(Entity e) {
		return e.right() >= left() 
				&& right() >= e.left()
				&& e.bottom() >= top()
				&& bottom() >= e.top();
	}
	
	public void setTexture(String filename) {
		try {
			texture = ImageIO.read(new File(filename));
		} catch(IOException e) {
			System.out.println("Unable to load image: " + filename);
		}
	}
	
	public void move(double theta, double velocity, double elapsed) {
		y += Math.sin(theta) * velocity * elapsed;
		x += Math.cos(theta) * velocity * elapsed;

		System.out.println(theta);
	}
	
	public void setPosition(double a, double b) {
		x = a;
		y = b;
	}
}
