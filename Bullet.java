import java.awt.*;//Will use Rectangles class for bullets for hit detection


public class Bullet{
	int x, y, width=6, height=12;
	int diagx, diagy;
	
	boolean is_enemy;
	boolean exists = true;

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

	public Bullet(int initial_x,int initial_y,boolean enemy,int initial_diagx,int initial_diagy){
		x=initial_x;
		y=initial_y;

		is_enemy=enemy;

		diagx=initial_diagx;
		diagy=initial_diagy;
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
	
	public void renderBullet(Graphics bulletspirte) {
		bulletspirte.setColor(Color.WHITE);
		bulletsprite.fillOval(x,y,width,height);
	}
}		

