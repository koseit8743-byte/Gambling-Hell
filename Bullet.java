import java.awt.*;//Will use Rectangles class for bullets for hit detection
import javax.swing.ImageIcon;

public class Bullet{
	int x, y, width=6, height=12;
	int diagx, diagy;
	
	boolean is_enemy;
	boolean exists = true;
	Color color;

	Image bSprite;
	Image pbSprite;

	public Bullet(int initial_x,int initial_y,boolean enemy){
		x=initial_x;
		y=initial_y;
		is_enemy=enemy;
		diagx=0;
		if(is_enemy){
			diagy=8;
		}
		else{
			diagy=-8;
		}
	}

	public Bullet(int initial_x,int initial_y,boolean enemy,int initial_diagx,int initial_diagy,Color c){
		x=initial_x;
		y=initial_y;
		color=c;

		is_enemy=enemy;

		diagx=initial_diagx;
		diagy=initial_diagy;
		
		bSprite = new ImageIcon("Sprites/bullet.png").getImage();
		pbSprite = new ImageIcon("Sprites/gbullet.png").getImage();
	}

	public void update_bullet(){
		
		x += diagx;
		y += diagy;

		if(y+height<0){//If bullet reaches top of map it dies, for SWING, top left of map is (0,0)
			exists=false;
		}

		if(y>720){//If Reaches bottom
			exists=false;
		}

		if(x + width <0){
			exists =false;
		}

		if(x>1080){
			exists = false;
		}
	}

	public Rectangle get_bounds(){//for collision detection
		return new Rectangle(x,y,width,height);//creates bullet hitbox
	}
	
	public void renderBullet(Graphics bulletsprite) {
		if (is_enemy) {
			//bulletsprite.setColor(Color.RED);
			bulletsprite.drawImage(bSprite, x,y,width,height, null);
		} else {
			//bulletsprite.setColor(Color.GREEN);
			bulletsprite.setColor(color);
			bulletsprite.fillRect(x,y,width,height);
		}
			//bulletsprite.fillOval(x,y,width,height);
	}
}		

