package game.entities;

import game.utilities.Location;

import java.awt.Dimension;
import java.util.ArrayList;

public abstract class AbstractEntity extends Entity {

	// *** Constructors *** //
	public AbstractEntity(Location p, Dimension d, double t, boolean s) {
		super(p, d, t, s);
	}

	// *** Inherited functions *** //
	@Override
	public abstract void handleCollision(Entity e);
	@Override
	public abstract void tick();
	@Override
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
	public static ArrayList<AbstractEntity> entities = new ArrayList<AbstractEntity>();
	public static ArrayList<AbstractEntity> removed = new ArrayList<AbstractEntity>();
}
