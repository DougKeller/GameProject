package game.entities;

import game.utilities.Location;

import java.awt.Dimension;
import java.util.ArrayList;

public abstract class PlayerEntity extends ConcreteEntity {

	public PlayerEntity(double x, double y) {
		this(new Location(x, y));
	}
	public PlayerEntity(Location p) {
		super(p, new Dimension(14, 14), 0, false);
		entities.add(this);
	}

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
	public static ArrayList<PlayerEntity> entities = new ArrayList<PlayerEntity>();
	public static ArrayList<PlayerEntity> removed = new ArrayList<PlayerEntity>();
}
