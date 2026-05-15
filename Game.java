import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	//bool flags for movement
	static boolean up, down, left, right, paused, gameover=false, startscreen = true, shoot;
	static String username;
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.print("Enter your name: ");
		username = scanner.nextLine();
		scanner.close();
	

		JFrame window = new JFrame("Gambling Hell"); //the JFrame will create the window for the Game
		Player jPlayer = new Player(); //window in param to get width/height of window
		GachaMenu menu = new GachaMenu(jPlayer);
		JLabel testText = new JLabel();
		
		Chat jChat = new Chat();
		// kwabe
		Client chatClient = new Client();
        chatClient.setChatWindow(jChat);
		jChat.setClient(chatClient);
		chatClient.connect(username);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//means we can close the window through top right x

		//title screen images
		Image titleSprite = new ImageIcon("Sprites/Title.png").getImage();
		Image titleBG = new ImageIcon("Sprites/bliss.jpg").getImage();
		Image gameBG = new ImageIcon("Sprites/blissgrass.jpg").getImage();

		JButton startButton = new JButton("GET ME IN THERE, and FLIRT");
		startButton.setBounds(390,350,300,80);
			
		//arraylists
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		ArrayList<Bullet> bullets = new ArrayList<Bullet>();

		SpawnEnemy spawner = new SpawnEnemy();
		ObjectCollision collision = new ObjectCollision();
		
        //JPanels don't show up on the JFrame window by default
		JPanel visualPanel = new JPanel() {//defining cutom behavior for JPanel
		
			//this is gonna get called evey repaint automatically in the game loop,
			//anything that has to be updated visually should be added here
			public void paintComponent(Graphics sprite) {
				super.paintComponent(sprite); //clears old frame kinda like system("clear")
				
				if (startscreen) {
					sprite.drawImage(titleBG, 0,0,1080,720,null);
					sprite.drawImage(titleSprite,250,100,100*6,34*6,null);
					return;
				}
				sprite.drawImage(gameBG, 0,0,1080,720,null);
				testText.setText("X:" + jPlayer.x + " Y:" + jPlayer.y + " HP:" + jPlayer.health + " SCORE: " + jPlayer.score);
				//testText.setText("Ene:" + enemies.size() + " bull:" + bullets.size() + " HP:" + jPlayer.health + " SCORE: " + jPlayer.score);
				//jPlayer.renderPlayer(sprite);

				//render bullets and enemies
				
				for(int i= 0;i<enemies.size();i++){
					enemies.get(i).renderEnemy(sprite);
				}
				for(int i= 0;i<bullets.size();i++){
					bullets.get(i).renderBullet(sprite);
				}

				jPlayer.renderPlayer(sprite);
				if(gameover){
					sprite.setColor(Color.WHITE);
					sprite.setFont(new Font("Arial",Font.BOLD,60));
					sprite.drawString("GAME OVER",360,300);
					sprite.setFont(new Font("Arial",Font.PLAIN,30));
					sprite.drawString("Final Score: "+jPlayer.score,420,360);
				}


			}//basically overloaded swing's paintComponent method here
		};

		//input reader
		window.addKeyListener(new KeyListener() {//defining KeyListener so input can be read
			//set flags true on key down
			//set flags false on key off
			
			//the flags are checked in Player to start movement			
			public void keyPressed(KeyEvent in) {//behavior only when the key is pressed down
				if        (in.getKeyCode() == KeyEvent.VK_W) {
					up = true;
				} else if (in.getKeyCode() == KeyEvent.VK_A) {
					left = true;
				} else if (in.getKeyCode() == KeyEvent.VK_S) {
					down = true;
				} else if (in.getKeyCode() == KeyEvent.VK_D) {
					right = true;
				} else if (in.getKeyCode() == KeyEvent.VK_SHIFT) {// hold shift to sprint, might convert to a dash with cooldown later
					jPlayer.speed = 6;
				} else if (in.getKeyCode() == KeyEvent.VK_SPACE) {
					//jPlayer.shoot(bullets);
					shoot=true;
				}  else if (in.getKeyCode() == KeyEvent.VK_G) {// hold shift to sprint, might convert to a dash with cooldown later
					menu.showM();
				}  else if (in.getKeyCode() == KeyEvent.VK_0) {
					jPlayer.shot_type=0;
				}  else if (in.getKeyCode() == KeyEvent.VK_1) {
					jPlayer.shot_type=1;
				}  else if (in.getKeyCode() == KeyEvent.VK_2) {
					jPlayer.shot_type=2;
				}
			}

			public void keyReleased(KeyEvent in) {//same for released
				if        (in.getKeyCode() == KeyEvent.VK_W) {
					up = false;

                } else if (in.getKeyCode() == KeyEvent.VK_A) {
                    left = false;
                } else if (in.getKeyCode() == KeyEvent.VK_S) {
                    down = false;
                } else if (in.getKeyCode() == KeyEvent.VK_D) {
                    right = false;
                } else if (in.getKeyCode() == KeyEvent.VK_SHIFT) {
					jPlayer.speed = 3;
				} else if (in.getKeyCode() == KeyEvent.VK_SPACE) {
					shoot = false;
				}
			}
			public void keyTyped(KeyEvent doesntmatter) {} //has to be here to avoid an error
		});
		//button stuff
		visualPanel.setLayout(null);
		visualPanel.setBackground(Color.DARK_GRAY);
		visualPanel.add(startButton);
		startButton.addActionListener(new ActionListener(){
			//logic for when button is clicked
			public void actionPerformed(ActionEvent e) {
				startscreen = false;
				startButton.setVisible(false);
				window.requestFocus();//gets input to work on the game window again
			}
		});
		
        testText.setForeground(Color.WHITE);
		testText.setBounds(490,20,300,40);
		visualPanel.add(testText);

		visualPanel.setPreferredSize(new Dimension(1080, 720)); //sets the visualPanel size, it'll get added to the JFrame and the JFrame will resize to fit the panel
	//	visualPanel.repaint();
        window.add(visualPanel); //adds the panel to the window
		window.pack(); //resizes window to fit the panel
		
		jPlayer.teleportPlayer(window);	

		window.setVisible(true); //so the window shows up on screen
					

        //game loop
        while (true) {

			if(!gameover && !startscreen){
				jPlayer.updatePos(up, down, left, right);
				jPlayer.update_player();
				if(shoot){
					jPlayer.shoot(bullets);
				}
				spawner.update_spawn(enemies,bullets);

				for(int i= 0;i<enemies.size();i++){
					enemies.get(i).update_enemy();
				}
				for(int i= 0;i<bullets.size();i++){
					bullets.get(i).update_bullet();
				}

				collision.check_hit(bullets,enemies,jPlayer);
				collision.remove_dead_object(bullets,enemies);

				if(jPlayer.health<=0){
					gameover=true;
				}
			}
			
            visualPanel.repaint();
			
            try {
                Thread.sleep(16); //sets the speed of the game basically, like the framerate. Won't work without the try/catch loop for some reason
            } catch (Exception e) {};        
        }
	}




}
