import java.util.ArrayList;
import java.util.Random;



//NEED to create SpawnEnemy object in Game.java and use update_spawn every frame
public class SpawnEnemy{

	int spawn_time =0, shoot_time = 0;
	int spawn_freq = 90, shoot_freq = 60;

	Random rand = new Random();

	public void update_spawn(ArrayList<Enemy> enemies, ArrayList<Bullet> bullets){

		spawn_time++;
		shoot_time++;

		if(spawn_time >= spawn_freq){

			if(enemies.size() < 15){
				int enemy_x = rand.nextInt(1000);
				int enemy_y= 0;

				enemies.add(new Enemy(enemy_x,enemy_y));
				spawn_time=0;
			}
		}

		if(shoot_time >= shoot_freq){

			for(int i=0; i < enemies.size(); i++){
				Enemy ene = enemies.get(i);

				bullets.add(new Bullet(ene.x+ene.width/2, ene.y+ene.height,true));
			}
			shoot_time=0;
		}
	}
}
