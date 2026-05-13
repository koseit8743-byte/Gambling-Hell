import java.util.ArrayList;
import java.util.Random;



//NEED to create SpawnEnemy object in Game.java and use update_spawn every frame
public class SpawnEnemy{

	int spawn_time =0, shoot_time = 0;
	int spawn_freq = 150, shoot_freq = 120;

	Random rand = new Random();

	public void update_spawn(ArrayList<Enemy> enemies, ArrayList<Bullet> bullets){

		spawn_time++;
		shoot_time++;

		if(spawn_time >= spawn_freq){
			//MAX 15 enemies on screen at once
			if(enemies.size() < 25){
				int enemy_x = rand.nextInt(1000);
				int enemy_y= 0;

				enemies.add(new Enemy(enemy_x,enemy_y));
			}
			spawn_time=0;
			
		}

		if(shoot_time >= shoot_freq){

			for(int i=0; i < enemies.size(); i++){
				Enemy ene = enemies.get(i);

				//where the bullets will start from based on enemy position
				int sx = ene.x+ene.width/2;
				int sy= ene.y+ene.height;

				
				int patternchoice = rand.nextInt(3);//random shot pattern assignment

				if(patternchoice==0){//straight down shot
					bullets.add(new Bullet(sx,sy,true,0,8));
				}
				else if(patternchoice==2){
					bullets.add(new Bullet(sx,sy,true,0, -6));
					bullets.add(new Bullet(sx,sy,true,0, 6));

					bullets.add(new Bullet(sx,sy,true,-6, 0));
					bullets.add(new Bullet(sx,sy,true,6, 0));

					bullets.add(new Bullet(sx,sy,true,-4, -4));
					bullets.add(new Bullet(sx,sy,true,4, -4));

					bullets.add(new Bullet(sx,sy,true,-4, 4));
					bullets.add(new Bullet(sx,sy,true,4, 4));

				}
				else{//diagonal 3 way shot
					bullets.add(new Bullet(sx,sy,true,-3, 6));
					bullets.add(new Bullet(sx,sy,true,0, 7));
					bullets.add(new Bullet(sx,sy,true,3, 6));
			}
			shoot_time=0;
		}
	}
}
