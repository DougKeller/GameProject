package game.entities;

import game.Game;
import game.utilities.Time;
import game.utilities.Location;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class Entity {
	// *** Private member declarations *** //
	private long timeCreated;
	
	// *** Member declarations *** //
	protected Location loc;
	protected Dimension size;
	protected double theta;
	
	protected boolean solid;
	protected boolean dead;
	
	// *** Constructors *** //
	public Entity(Location p, Dimension d, double t,boolean s) {
		timeCreated = Time.RUNTIME();
		loc = p;
		size = d;
		theta = t;
		solid = s;
		dead = false;
		entities.add(this);
	}
	
	// *** Abstract functions *** //
	public abstract void handleCollision(Entity e);
	public abstract void tick();
	
	// *** Getters *** //
	public Location location() 	{ return (Location)loc.clone(); 			}
	public Dimension size() 	{ return (Dimension)size.clone();  			}
	public int width() 			{ return size.width; 						}
	public int height() 		{ return size.height;				 		}
	public double theta() 		{ return theta; 							}
	public long age() 			{ return Time.RUNTIME() - timeCreated; }
	public boolean solid() 		{ return solid; 							}
	
	// *** Side-getters *** //
	public int left()   { return loc.x() - size.width / 2;  }
	public int right()  { return loc.x() + size.width / 2;  }
	public int top()    { return loc.y() - size.height / 2; }
	public int bottom() { return loc.y() + size.height / 2; }
	
	// The origin is the top-left corner of the bounds, used in painting
	public Point origin() { return new Point(left(), top()); }
	
	// *** Setters *** //
	public void setLocation(double x, double y) { loc.x = x; loc.y = y; }
	public void setLocation(Location l)	{ loc = l;   }
	public void setSize(int w, int h) 	{ size.width = w; size.height = h; }
	public void setSize(Dimension d) 	{ size = d;  }
	public void setTheta(double t) 		{ theta = t; }
	public void setSolid(boolean s) 	{ solid = s; }
	
	// *** Side-Setters *** //
	// Useful for collisions, etc
	public void setLeft(int x)   { loc.x += (x - left());   }
	public void setRight(int x)  { loc.x -= (x - right());  }
	public void setTop(int y)    { loc.y += (y - top());    }
	public void setBottom(int y) { loc.y -= (y - bottom()); }
	
	// *** Relative Movement *** //
	public void move(double amt) { 
		move(new Location(amt * Math.cos(theta), amt * Math.sin(theta)));
	}
	public void move(Location l) { 
		loc.x += l.x; 
		loc.y += l.y;
	}
	public void rotate(double t) {
		theta += t;
		while(theta > Math.PI)
			theta -= 2*Math.PI;
		while(theta < -Math.PI)
			theta += 2*Math.PI;
	}
	
	// *** Member functions *** //
	public Rectangle bounds() {
		return new Rectangle(origin(), size);
	}
	public boolean collidesWith(Entity e) {
		if(destroyed())
			return false;
		return bounds().intersects(e.bounds());
	}
	public boolean visible() {
		return Game.camera.intersects(bounds());
	}
	public boolean destroyed() {
		return removed.contains(this);
	}
	public boolean alive() {
		return !destroyed();
	}
	public void destroy() {
		removed.add(this);
	}
	public void remove() {
		entities.remove(this);
		removed.remove(this);
	}
	
	// *** Class functions *** //
	public static void testCollision(Entity a, Entity b) {
		if(a.alive() && b.alive() && a.collidesWith(b)) {
			a.handleCollision(b);
			b.handleCollision(a);
			// TODO: Face detection
		}
	}

	// *** Class variables *** //
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
	public static ArrayList<Entity> removed = new ArrayList<Entity>();
}
