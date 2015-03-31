package game.textures;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Texture extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public Texture(String s) {
		boolean loaded = false;
		int c = 0;
		while(!loaded || c < 10) {
			try {
				Image img = ImageIO.read(new File(s));
				JLabel label = new JLabel(new ImageIcon(img));
				add(label);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//g.drawImage(this, 0, 0, null);
	}

}
