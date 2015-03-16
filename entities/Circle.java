package game.entities;

import game.calculations.Collision;

import java.awt.Color;
import java.awt.Graphics2D;

public class Circle extends Entity {

	public Circle(int a, int b) {
		super(a, b);
		
		width = 600;
		height = 600;
	}

	@Override
	public void update(double elapsed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void repaint(Graphics2D g) {
		g.setColor(Color.white);
		g.drawOval((int)left(), (int)top(), (int)width, (int)height);
	}

	@Override
	public void handleCollision(Entity e, Collision c) {
		//e.setPosition(400, 300);
	}

}
