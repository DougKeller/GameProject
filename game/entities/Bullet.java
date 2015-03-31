package game.entities;

import game.utilities.Location;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

public class Bullet extends ConcreteEntity {

	public Bullet(double x, double y, double t) {
		this(new Location(x, y), t);
	}
	public Bullet(Location p, double t) {
		super(p, new Dimension(5, 5), t, false);
	}
	public Bullet(Entity source) {
		super(source.location(), new Dimension(5,5), source.theta(), false);
	}

	@Override
	public void repaint(Graphics2D g) {
		float alpha = (3000-age())/4500f;
		if(alpha < 0)
			alpha = 0;
		g.setColor(new Color(1f,1f,1f,alpha));
		g.fillOval(left(), top(), width(), height());
	}

	@Override
	public void handleCollision(Entity e) {
	}

	@Override
	public void tick() {
		if(age() > 2000)
			destroy();
		move(6);
	}

}
