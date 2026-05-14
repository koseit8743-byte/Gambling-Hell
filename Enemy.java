import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Enemy{
	int x,y,width=30,height=30, health=3, speed=4;
	boolean exists=true;
	
	Image eSprite;

	public Enemy(int initial_x, int initial_y){
		x=initial_x;
		y=initial_y;
		eSprite = new ImageIcon("Sprites/devil.gif").getImage();
	}

	public void update_enemy(){
		y+=speed;//enemy movement, just downwards for now

		if(health<=0){//death
			exists=false;
		}

		if(y>720){
			exists=false;
		}
	}

	public void take_damage(int damage){
		health -= damage;
	}

	public Rectangle get_bounds(){//collision detection
		return new Rectangle(x,y,width,height);
	}

	public void renderEnemy(Graphics enemysprite) {
		//enemysprite.setColor(Color.RED);
		//enemysprite.fillRect(x,y,width,height);
		enemysprite.drawImage(eSprite, x,y, width,height,null);
	}
}
