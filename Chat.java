import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

public class Chat {
	
	JFrame chatWindow;
	JPanel chatPanel;


	JTextArea chatArea;
	JTextField textInputBox;
	//JLabel message;
	String timeSent;
	public Chat() {
		chatWindow = new JFrame("Gambling Hell Chat");
		chatPanel = new JPanel();
		//message = new Jlabel();

		chatPanel.setLayout(new BorderLayout());
		chatArea = new JTextArea();
		
		
		textInputBox = new JTextField();
		chatArea.setEditable(false);
		
		textInputBox.addActionListener(event -> {
			String message = textInputBox.getText();
			timeSent = LocalTime.now().withNano(0).toString();
			//if we need to edit the message format its on this line here
			chatArea.append("[" + timeSent + "] Me: " + message + "\n"); 
			textInputBox.setText(""); 
		});
		chatPanel.add(new JScrollPane(chatArea), BorderLayout.CENTER);
		chatPanel.add(textInputBox, BorderLayout.SOUTH);
		chatWindow.add(chatPanel);
		
		chatWindow.setSize(330,720);
		chatWindow.setVisible(true);


		// Kwabe  new method
		public void addIncomingMessage(String sender, String message){
			timeSent = LocalTime.now().withNano(0).toString();
			chatArea.append("[" + timeSent + "] " + sender + ": " + message + "\n");

	}
}
