import javax.swing.*;
import java.awt.*;

public class Chat {
	
	JFrame chatWindow;
	JPanel chatPanel;


	JTextArea chatArea;
	JTextField textInputBox;
	//JLabel message;
	
	public Chat() {
		chatWindow = new JFrame("Gambling Hell Chat");
		chatPanel = new JPanel();
		//message = new Jlabel();

		chatPanel.setLayout(new BorderLayout());
		chatArea = new JTextArea();
		chatArea.setEditable(false);
		

		chatWindow.add(textInputBox, BorderLayout.SOUTH);
		chatWindow.add(chatPanel);
		
		chatWindow.setSize(330,720);
		chatWindow.setVisible(true);
	}
}
