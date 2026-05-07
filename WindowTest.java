import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WindowTest {
	public static void main(String[] args) {
		JFrame window = new JFrame("Gambling Hell"); //the JFrame will create the window for the game
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//means we can close the window through top right x
		//JPanels don't show up on the JFrame window by default
		JPanel visualPanel = new JPanel() {}; //JPanels are gonna hold all the visual elements/sprites
		visualPanel.setBackground(Color.GREEN);

		visualPanel.setPreferredSize(new Dimension(720, 1080)); //sets the visualPanel size, it'll get added to the JFrame and the JFrame will resize to fit the panel
		window.setVisible(true); //so the window shows up on screen
				
	}
} 
