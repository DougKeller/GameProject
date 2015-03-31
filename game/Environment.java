package game;

import game.entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Environment {
	
	public static String path;
	
	public static int WIDTH;
	public static int HEIGHT;
	
	public static void load(String p) {
		path = p;

		try {
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			ArrayList<String> lines = new ArrayList<String>();
			while(br.ready())
				lines.add(br.readLine());
			
			WIDTH = (lines.get(0).length()-1) * 10;
			HEIGHT = (lines.size() - 1) * 10;
			
			int rows = lines.size();
			for(int y = 0; y < rows; ++y) {
				String s = lines.get(y);
				char [] chars = s.toCharArray();
				for(int x = 0; x < chars.length; ++x) {
					switch(chars[x]) {
					case 'O':
						new Ball(x*10 + 5,y*10 + 5);
						break;
					case 'P':
						new Player(x*10 + 5,y*10 + 5);
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
