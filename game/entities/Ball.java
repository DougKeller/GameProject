package game.entities;

import game.Environment;
import game.PaintController;
import game.utilities.Location;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Random;

public class Ball extends ConcreteEntity {
	
	Color color;
	
	public Ball(double x, double y) {
		this(new Location(x, y));
	}
	public Ball(Location p) {
		super((Location) p.clone(), new Dimension(10, 10), 0, true);
		Random r = new Random();
		color = new Color(r.nextInt(128) + 128, r.nextInt(128) + 128, r.nextInt(128) + 128);
	}

	@Override
	public void handleCollision(Entity e) {
			Random r = new Random();
			loc = new Location(r.nextInt(Environment.WIDTH/10)*10 + 5, r.nextInt(Environment.HEIGHT/10)*10 + 5);
			PaintController.score++;

	}

	@Override
	public void tick() {
		rotate(2*Math.PI);
		//size.width = (int)(Math.sin(theta)*10) + 10;
		//size.height = (int)(Math.sin(-theta)*10s) + 10;
	}

	@Override
	public void repaint(Graphics2D g) {
		g.setColor(color);
		g.drawOval(left(), top(), size.width, size.height);
	}
}
