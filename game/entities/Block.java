package game.entities;

import game.calculations.Collision;

import java.awt.Color;
import java.awt.Graphics2D;

public class Block extends Entity {

	public Block(int a, int b) {
		super(a, b);
		
	}

	@Override
	public void update(double elapsed) {
	}

	@Override
	public void repaint(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillRect(leftInt(), topInt(), widthInt(), heightInt());
	}

	@Override
	public void handleCollision(Entity e, Collision c) {
	}

}
