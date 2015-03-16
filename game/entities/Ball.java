package game.entities;

import game.calculations.Collision;

import java.util.Random;
import java.awt.Color;
import java.awt.Graphics2D;

public class Ball extends Entity {
	
	Color color;
	Random r;
	
	double rpm;
	int velocity;
	
	public Ball(int a, int b) {
		super(a, b);
		r = new Random();
		rpm = r.nextDouble() * 2;
		velocity = r.nextInt(150) + 50;
		color = new Color(r.nextInt(128)+128, r.nextInt(128)+128, r.nextInt(128)+128);
	}

	@Override
	public void update(double elapsed) {
		rotate(rpm * Math.PI * elapsed);
		move(theta, 300, elapsed);
	}
	
	@Override
	public void destroy() {
		destroyed = true;
		entityController.getEnvironment().getGameWindow().incrementScore();
	}

	@Override
	public void repaint(Graphics2D g) {
		g.setColor(color);
		g.drawOval(leftInt(), topInt(), widthInt(), widthInt());
	}

	@Override
	public void handleCollision(Entity e, Collision c) {
	}

}
