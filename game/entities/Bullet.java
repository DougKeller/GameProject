package game.entities;

import game.calculations.Collision;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends Entity {
	
	private double velocity = 300;
	
	private long created;

	public Bullet(int a, int b, double th) {
		super(a, b);
		
		created = System.currentTimeMillis();
		theta = th;
		width = height = 5;
	}

	@Override
	public void update(double elapsed) {
		if(System.currentTimeMillis() - created > 3000)
			destroy();
		
		move(theta, velocity, elapsed);
	}

	@Override
	public void repaint(Graphics2D g) {
		g.setColor(Color.white);
		g.drawOval(leftInt(), topInt(), widthInt(), heightInt());
	}

	@Override
	public void handleCollision(Entity e, Collision c) {
		if(e instanceof Ball)
			e.destroy();
	}

}
