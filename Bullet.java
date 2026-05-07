import java.awt*;//Will use Rectangles class for bullets for hit detection


public class Bullet{
	int x, y, width=6, height=12, speed=8;
	
	boolean is_enemy;
	boolean exists = true;

	public Bullet(int initial_x,int initial_y,boolean enemy){
		x=initial_x;
		y=initial_y;
		is_enemy=enemy;
	}

	public void update_bullet(){
		if(is_enemy){//enemy bullet travels down
			y+=speed;
		}
		else{//Player bullet travels up
			y-=speed;
		}

		if(y+height<0){//If bullet reaches top of map it dies, for SWING, top left of map is (0,0)
			exists=false;
		}

		if(y>720){//If Reaches bottom
			exists=false;
		}
	}

	public Rectangle get_bounds(){//for collision detection
		return new Rectangle(x,y,width,height);//creates bullet hitbox
	}
}		

