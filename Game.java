import java.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game {
	//bool flags for movement
	static boolean up, down, left, right;

	public static void main(String[] args) {
		JFrame window = new JFrame("Gambling Hell"); //the JFrame will create the window for the Game
		Player jPlayer = new Player(window); //window in param to get width/height of window

		JPanel visualPanel = new JPanel() {//defining cutom behavior for JPanel

			
			
			//this is gonna get called evey repaint automatically in the game loop,
			//anything that has to be updated visually should be added here
			public void paintComponent(Graphics sprite) {
				super.paintComponent(sprite); //clears old frame kinda like system("clear")
				testText.setText("just a test");
				jPlayer.renderPlayer(sprite);
			}//basically overloaded swing's paintComponent method here
		};

		//in
	}




}
