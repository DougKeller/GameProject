package game.entities;

import game.utilities.Location;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

public class Explosion extends ConcreteEntity {
	
	protected double radius;

	public Explosion(Location p) {
		super(p, new Dimension(0,0),0f, false);
	}

	@Override
	public void repaint(Graphics2D g) {
		g.setColor(new Color(1f, 1f, 1f, (2000-age())/16000f));
		g.fillOval(left(), top(), width(), height());
	}

	@Override
	public void handleCollision(Entity e) {
	}

	@Override
	public void tick() {
		if(age() > 2000)
			destroy();
		
		size.width += 800;
		size.height += 800;
	}

}
