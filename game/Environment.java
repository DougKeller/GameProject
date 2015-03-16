package game;

import game.entities.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Environment {
	
	private String path;
	
	private GameWindow window;
	
	private EntityController entityController;
	
	public Environment(GameWindow w, String p) {
		window = w;
		entityController = new EntityController(this);
		path = p;
		try {
			loadFrom(path);
		} catch (FileNotFoundException e) {
		}
	}
	
	public void loadFrom(String p) throws FileNotFoundException {
		path = p;
		
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		
		try {
			for(int y = 0; y < 60; ++y) {
				char[] line = br.readLine().toCharArray();
				for(int x = 0; x < line.length; ++x) {
					switch(line[x]) {
					case 'P':
						entityController.createEntity((new Player(x*10, y*10)));
						break;
					case 'B':
						entityController.createEntity((new Block(x*10, y*10)));
						break;
					case 'O':
						entityController.createEntity((new Ball(x*10, y*10)));
						break;
					}
				}
			}
		} catch (IOException e) {
			
		}
	}
	
	public Entity at(int x, int y) {
		for(Entity e : entityController.getEntities())
			if(e.getBounds().contains(x, y))
				return e;
		return null;
	}
	
	public void update(long elapsed) {
		entityController.update(elapsed);
	}
	
	public GameWindow getGameWindow() {
		return window;
	}
	
	public EntityController getEntityController() {
		return entityController;
	}
}
