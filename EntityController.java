package game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import game.entities.Circle;
import game.entities.Entity;
import game.entities.Player;
import game.interfaces.Controllable;


public class EntityController {
	
	private GameWindow gamewindow;
	
	private ArrayList<Entity> entities;
	private ArrayList<Controllable> players;
	
	public EntityController(GameWindow w) {
		gamewindow = w;
		entities = new ArrayList<Entity>();
		players = new ArrayList<Controllable>();
		
		createEntity(new Player(400, 300));
		createEntity(new Circle(400,300));
	}

	public void update(long elapsed) {
		double seconds = elapsed / 1000f;
			
		for(int i = 0; i < entities.size(); ++i) {
			Entity a = entities.get(i);
			if(a.destroyed()) {
				destroyEntity(a);
				i--;
			} else {
				for(int j = i + 1; j < entities.size(); ++j) {
					Entity b = entities.get(j);
					if(b.destroyed()) {
						destroyEntity(b);
						j--;
					} else
						Entity.checkCollision(a, entities.get(j));
				}
				a.update(seconds);
			}
		}
	}
	
	public void addEvent(int action, KeyEvent e) {
		for(Controllable entity : players) {
			switch(action){
			case KeyEvent.KEY_PRESSED:
				entity.keyPressed(e);
				break;
			case KeyEvent.KEY_RELEASED:
				entity.keyReleased(e);
				break;
			case KeyEvent.KEY_TYPED:
				entity.keyTyped(e);
				break;
			}
		}
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	public void createEntity(Entity e) {
		entities.add(e);
		if(e instanceof Controllable)
			players.add((Controllable)e);
	}
	
	public void destroyEntity(Entity e) {
		entities.remove(e);
		if(e instanceof Controllable)
			players.remove(e);
	}
}
