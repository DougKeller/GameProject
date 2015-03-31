package game.entities;

import game.utilities.Location;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

public class Tail extends ConcreteEntity {

	public Tail(Location p) {
		super(p, new Dimension(6,6), 0, false);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void repaint(Graphics2D g) {
		g.setColor(Color.yellow);
		g.fillRect(left(), top(), width(), height());
	}

	@Override
	public void handleCollision(Entity e) {
		
	}

	@Override
	public void tick() {
	}

}
