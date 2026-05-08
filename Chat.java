import javax.swing.*;
import java.awt.*;

public class Chat {
	
	JFrame chatWindow;
	JPanel chatPanel;


	JTextArea chatArea;
	JTextField inputField;
	//JLabel message;
	
	public Chat() {
		chatWindow = new JFrame("Gambling Hell Chat");
		chatPanel = new JPanel();
		
		chatPanel.setLayout(new BorderLayout());
		chatArea = new JTextArea();
		chatArea.setEditable(false);
	}
}
