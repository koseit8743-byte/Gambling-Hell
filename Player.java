import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Player {
	int health = 5;
	int score = 0;
	int speed = 3;

	int x = 100;
	int y = 100;

	int width=10;
	int height=10;

	int shoot_cooldown=0;
	int shoot_delay=5;

	int shot_type=0;

	Inventory playerinventory = new Inventory();
	

	boolean alive=true;

	/*
	public Player(JFrame framepanel) {
		x = framepanel.getWidth() / 2;
		y = framepanel.getHeight() / 2;
	}*/
	
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
				bullets.add(new Bullet(bullx,bully,false,0,-10));

			else if(shot_type==1){
				bullets.add(new Bullet(bullx,bully,false,-3,-8));
				bullets.add(new Bullet(bullx,bully,false,0,-10));
				bullets.add(new Bullet(bullx,bully,false,3,-8));
			}

			else if(shot_type==2){
				bullets.add(new Bullet(bullx,bully,false,-3,-8));
				bullets.add(new Bullet(bullx,bully,false,3,-8));
				bullets.add(new Bullet(bullx,bully,false,0,-10));
				bullets.add(new Bullet(bullx,bully,false,-5,-7));
				bullets.add(new Bullet(bullx,bully,false,5,-7));
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
	}
	
	public void renderPlayer(Graphics playersprite) {
		playersprite.setColor(Color.GREEN);
		playersprite.fillRect(x,y,width,height);
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

}
