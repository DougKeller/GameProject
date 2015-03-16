package game.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.EntityController;
import game.calculations.Collision;
import game.calculations.Constants;
import game.interfaces.Updateable;

public abstract class Entity implements Updateable {
	protected double x;
	protected double y;
	protected double theta;
	
	protected double width;
	protected double height;
	protected int depth = 10;
	
	protected boolean destroyed;
	protected boolean dying;
	
	protected EntityController entityController;
	
	protected BufferedImage texture;
	
	public Entity(int a, int b) {
		x = a;
		y = b;
		width = 10;
		height = 10;
		destroyed = false;
	}

	/// Abstract methods
	public abstract void repaint(Graphics2D g);
	public abstract void handleCollision(Entity e, Collision c);
	
	
	public double left() 	 { return x;  }
	public double right()  { return x + width;  }
	public double top() 	 { return y; }
	public double bottom() { return y + height; }
	public double centerX() { return x + width / 2; }
	public double centerY() { return y + height / 2; }
	public int leftInt() 	{ return (int)left();   }
	public int rightInt() 	{ return (int)right();  }
	public int topInt() 	{ return (int)top();    }
	public int bottomInt() 	{ return (int)bottom(); }
	public int centerXInt() { return (int)centerX(); }
	public int centerYInt() { return (int)centerY(); }
	
	/// Size accessors
	public double widthDouble()  { return width;  }
	public double heightDouble() { return height; }
	public int depth() { return depth; }
	public int widthInt()	{ return (int)widthDouble();  }
	public int heightInt()	{ return (int)heightDouble(); }
	public EntityController getController() { return entityController; }
	
	/// Tests
	public boolean collidesWith(Entity e) {
		if(e.destroyed)
			return false;
		return getBounds().intersects(e.getBounds());
	}
	public boolean destroyed() {
		return destroyed;
	}
	
	/// Setters
	public void setTexture(String filename) {
		try {
			texture = ImageIO.read(new File(filename));
		} catch(IOException e) {
			System.out.println("Unable to load image: " + filename);
		}
	}
	public void setPosition(double a, double b) {
		x = a;
		y = b;
	}
	public void setCenter(double a, double b) {
		x = a - width/2;
		y = b - height/2;
	}
	public void setController(EntityController c) {
		entityController = c;
	}
	
	public void move(double theta, double velocity, double elapsed) {
		y += Math.sin(theta) * velocity * elapsed;
		x += Math.cos(theta) * velocity * elapsed;
	}
	public void rotate(double a) {
		theta += a;
		if(theta > 2*Constants.PI)
				theta -= 2*Constants.PI;
		if(theta < 0)
			theta += 2*Constants.PI;
	}
	public void destroy() {
		destroyed = true;
	}
	
	public BufferedImage getImage() {

		BufferedImage bi = new BufferedImage(widthInt(), heightInt(), texture.getType());
		Graphics2D g = bi.createGraphics();
		g.rotate(theta, width/2, height/2);
		g.drawImage(texture, null, 0, 0);
		return bi;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(leftInt(), topInt(), widthInt(), heightInt());
	}
	
	public static void testCollision(Entity a, Entity b) {
		if(!a.collidesWith(b))
			return;
		
		Rectangle intersection = a.getBounds().intersection(b.getBounds());
		
		if(intersection.width > intersection.height) {
			if(intersection.getCenterY() < a.getBounds().getCenterY()){
				a.handleCollision(b, Collision.ABOVE);
				b.handleCollision(a, Collision.BELOW);
			} else {
				a.handleCollision(b, Collision.BELOW);
				b.handleCollision(a, Collision.ABOVE);
			}
		} else {
			if(intersection.getCenterX() < a.getBounds().getCenterX()) {
				a.handleCollision(b, Collision.LEFT);
				b.handleCollision(a, Collision.RIGHT);
			} else {
				a.handleCollision(b, Collision.RIGHT);
				b.handleCollision(a, Collision.LEFT);
			}
		}
	}
}
