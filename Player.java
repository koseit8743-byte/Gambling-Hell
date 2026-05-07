import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;

public class Player {
	int health = 5;
	int score = 0;
	int speed = 3;
	int x = 100;
	int y = 100;
	public Player(JFrame framepanel) {
		x = framepanel.getWidth() / 2;
		y = framepanel.getHeight() / 2;
	};
	
	//movement flags read here to start movement
	public void updatePos(boolean u, boolean d, boolean l, boolean r) {
		if (u) y -= speed;
		if (d) y += speed;
		if (l) x -= speed;
		if (r) x += speed;
	}
	
	public void renderPlayer(Graphics playersprite) {
		playersprite.setColor(Color.GREEN);
		playersprite.fillRect(x,y,10,10);
	};
}
