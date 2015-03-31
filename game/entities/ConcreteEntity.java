package game.entities;

import game.utilities.Location;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class ConcreteEntity extends Entity {

	protected int depth;

	// *** Constructors *** //
	public ConcreteEntity(Location p, Dimension d, double t, boolean s) {
		super(p, d, t, s);
		depth = 0;
		entities.add(this);
	}
	
	// *** Abstract functions *** //
	public abstract void repaint(Graphics2D g);

	// *** Getters *** //
	public int depth() { return depth; }

	// *** Setters *** //
	public void setDepth(int d) { depth = d; }

	// *** Inherited functions *** //
	public void destroy() {
		super.destroy();
		removed.add(this);
	}
	public void remove() {
		super.remove();
		entities.remove(this);
		removed.remove(this);
	}
	
	// *** Class variables *** //
	public static ArrayList<ConcreteEntity> entities = new ArrayList<ConcreteEntity>();
	public static ArrayList<ConcreteEntity> removed = new ArrayList<ConcreteEntity>();
}
