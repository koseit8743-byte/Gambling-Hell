import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Game {
	//bool flags for movement
	static boolean up, down, left, right;

	public static void main(String[] args) {
		JFrame window = new JFrame("Gambling Hell"); //the JFrame will create the window for the Game
		Player jPlayer = new Player(window); //window in param to get width/height of window
		JLabel testText = new JLabel();

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//means we can close the window through top right x


        //JPanels don't show up on the JFrame window by default
		JPanel visualPanel = new JPanel() {//defining cutom behavior for JPanel
		
			//this is gonna get called evey repaint automatically in the game loop,
			//anything that has to be updated visually should be added here
			public void paintComponent(Graphics sprite) {
				super.paintComponent(sprite); //clears old frame kinda like system("clear")
				testText.setText("just a test, Player X:" + jPlayer.x + " Y:" + jPlayer.y);
				jPlayer.renderPlayer(sprite);
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
				} else if (in.getKeyCode() == KeyEvent.VK_SHIFT) {
					jPlayer.speed = 6;
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
				}
			}
			public void keyTyped(KeyEvent doesntmatter) {}; //has to be here to avoid an error
		});

		visualPanel.setBackground(Color.DARK_GRAY);
		
		
        testText.setForeground(Color.WHITE);
		visualPanel.add(testText);

		visualPanel.setPreferredSize(new Dimension(1080, 720)); //sets the visualPanel size, it'll get added to the JFrame and the JFrame will resize to fit the panel
	//	visualPanel.repaint();
        window.add(visualPanel); //adds the panel to the window
		window.pack(); //resizes window to fit the panel
		

		window.setVisible(true); //so the window shows up on screen
					

        //game loop
        while (true) {
            jPlayer.updatePos(up, down, left, right);
            visualPanel.repaint();

            try {
                Thread.sleep(16); //sets the speed of the game basically, like the framerate. Won't work without the try/catch loop for some reason
            } catch (Exception e) {};        
        }
	}




}
