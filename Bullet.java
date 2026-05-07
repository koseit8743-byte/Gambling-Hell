import java.awt*;//Will use Rectangles class for bullets for hit detection


public class Bullet{
	int x, y, width=6, height=12, speed=8;

	boolean exists = true;

	public Bullet(int initial_x,int initial_y){
		x=initial_x;
		y=initial_y;
	}

	public void update_bullet(){
		y-=speed;//speed of bullets going up

		if(y+height<0){//If bullet reaches top of map it dies, for SWING, top left of map is (0,0)
			exists=false;
		}
	}

	public Rectangle get_bounds(){//for collision detection
		return new Rectangle(x,y,with,height);//creates bullet hitbox
	}
}		

