import java.awt.Rectangle;

public class Enemy{
	int x,y,width=30,height=30, health=3, speed=4;
	boolean exists=true;

	public Enemy(int initial_x, int initial_y){
		x=initial_x;
		y=initial_y;
	}

	public void update_enenmy(){
		y+=speed;//enemy movement, just downwards for now

		if(health<=0){//death
			exists=false;
		}
	}

	public void take_damage(int damage){
		health -= damage;
	}

	public Rectangle get_bounds(){//collision detection
		return new Rectangle(x,y,width,height);
	}
}
