package game.utilities;

import java.awt.Point;

public class Location {
	public double x, y;
	
	public Location() {
		this(0, 0);
	}
	public Location(double a, double b) {
		x = a;
		y = b;
	}
	public Location(Point p) {
		x = p.x;
		y = p.y;
	}
	public Location(Location l) {
		x = l.x;
		y = l.y;
	}
	public int x() {
		return (int)x;
	}
	public int y() {
		return (int)y;
	}
	public Point toPoint() {
		return new Point(x(), y());
	}
	
	@Override
	public Object clone() {
		return new Location(x, y);
	}
}
