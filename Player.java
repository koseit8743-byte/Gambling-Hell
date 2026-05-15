import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Player {
	public int health = 50;
	public int score = 500;//is 500 so kerney can test gacha system
	public int speed = 3;

	public int x = 100;
	public int y = 100;

	public int width=10;
	public int height=10;

	public int shoot_cooldown=0;
	public int shoot_delay=5;

	Image pSprite;

	public int shot_type=0;
	public Color bulletcolor = Color.WHITE;

	Inventory playerinventory = new Inventory();
	
	
	boolean alive=true;
	
	
	public Player() {
		x = 640; 
		y = 360;
		pSprite = new ImageIcon("Sprites/smile.gif").getImage();
	}
	
	public void teleportPlayer(JFrame framepanel) {
		x = framepanel.getWidth() / 2;
		y = framepanel.getHeight() / 2;
	}

	public void update_player(){
		if(shoot_cooldown>0)
			shoot_cooldown--;
	}
	//need gacha poweup to change shot type
	public void shoot(ArrayList<Bullet> bullets){
		if (shoot_cooldown<=0){
			int bullx = x+width/2;
			int bully = y;

			if(shot_type==0)
				bullets.add(new Bullet(bullx,bully,false,0,-10,bulletcolor));

			else if(shot_type==1){
				bullets.add(new Bullet(bullx,bully,false,-3,-8,bulletcolor));
				bullets.add(new Bullet(bullx,bully,false,0,-10,bulletcolor));
				bullets.add(new Bullet(bullx,bully,false,3,-8,bulletcolor));
			}

			else if(shot_type==2){
				bullets.add(new Bullet(bullx,bully,false,-3,-8,bulletcolor));
				bullets.add(new Bullet(bullx,bully,false,3,-8,bulletcolor));
				bullets.add(new Bullet(bullx,bully,false,0,-10,bulletcolor));
				bullets.add(new Bullet(bullx,bully,false,-5,-7,bulletcolor));
				bullets.add(new Bullet(bullx,bully,false,5,-7,bulletcolor));
			}
			shoot_cooldown=shoot_delay;
		}
	}

	//movement flags read here to start movement
	public void updatePos(boolean u, boolean d, boolean l, boolean r) {
		
		if (u) y -= speed;
		if (d) y += speed;
		if (l) x -= speed;
		if (r) x += speed;

		if (x<0){
			x=0;
		}
		if(y<0){
			y=0;
		}
		if(x+width>1080){
			x=1080-width;
		}
		if(y+height>720){
			y=720-height;
		}

	}
	
	public void renderPlayer(Graphics playersprite) {
		//playersprite.setColor(Color.GREEN);
		//playersprite.fillRect(x,y,width,height);
		playersprite.drawImage(pSprite, x , y, 20, 20, null);
	}

	public Rectangle get_bounds(){
		return new Rectangle(x,y,width,height);
	}

	public void take_damage(int damage){
		health -= damage;

		if (health <= 0){
			health = 0;
			alive=false;
		}
	}

	public void apply(GachaItem item){
		bulletcolor=item.getColor();
		if(item.getType().equals("PLAYER")){
			if(item.getRarity() == Rarity.RARE){
				speed=4;
			}
			if(item.getRarity() == Rarity.EPIC){
				speed=5;
			}
			if(item.getRarity() == Rarity.LEGENDARY){
				speed=6;
			}
		}
		if(item.getType().equals("BULLET")){
			if(item.getRarity() == Rarity.RARE){
				shot_type=1;
			}
			if(item.getRarity() == Rarity.EPIC){
				shot_type=2;
			}
			if(item.getRarity() == Rarity.LEGENDARY){
				shot_type=2;
				shoot_delay=3;
			}
		}
	}	

}
