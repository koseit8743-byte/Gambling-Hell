import java.util.ArrayList;//basically a vector

public class ObjectCollision{

	//checks collision between every bullet and every enemy hitbox
	public void check_hit(ArrayList<Bullet> bullets, ArrayList<Enemy> enemies, Player player){

		for (int i=0;i<bullets.size();i++){

			Bullet bull=bullets.get(i);

			if (bull.is_enemy){
				if(bull.get_bounds().intersects(player.get_bounds())){
					player.take_damage(1);
					bull.exists=false;
				}
			}

			else{
				for(int j=0;j<enemies.size();j++){

					Enemy ene=enemies.get(j);

					if(bull.get_bounds().interescts(ene.get_bounds())){//If hitboxes interect then enemy takes damage and bullet ends
						ene.take_damage(1);
						bull.exists=false;

						if(!ene.exists){
							player.score += 10;//Score for killinmg enemy
						}
					}
				}
			}
		}
	}
	
	//removes dead objects from game
	public void remove_dead_object(ArrayList<Bullet> bullets, ArrayList<Enemy> enemies){
		for (int i=0; i<bullets.size(); i++){
			if(!bullets.get(i).exists){
				bullets.remove(i);
				i--;
			}
		}
		for (int i=0; i<enemies.size(); i++){
			if(!enemies.get(i).exists){
				enemies.remove(i);
				i--;
			}
		}
	}
}




