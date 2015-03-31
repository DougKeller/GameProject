package game;

import game.utilities.Location;

import java.awt.Rectangle;

public class Camera extends Rectangle {
	
	private Location loc;
	
	public Camera() {
		width = Environment.WIDTH;
		height = Environment.HEIGHT;
	}
	
	public void update() {
		/*
		x = loc.x() - width/2;
		y = loc.y() - height/2;
		
		if(x + width > Environment.WIDTH)
			x = Environment.WIDTH - width;
		if(y + height > Environment.HEIGHT)
			y = Environment.HEIGHT - height;
		if(x < 0)
			x = 0;
		if(y < 0)
			y = 0;
			*/
	}
	
	public void setSource(Location l) {
		loc = l;
	}
	
	public int left() {
		return x;
	}
	public int top() {
		return y;
	}
	public int right() {
		return x + width;
	}
	public int bottom() {
		return y + height;
	}
}
