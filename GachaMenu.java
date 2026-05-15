import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GachaMenu {
	JFrame window;
	JPanel panel;
	JTextArea inventoryArea;
	JLabel scoreLabel;
	JLabel resultLabel;
	JButton pullButton;
	
	Player player;
	GachaSystem gacha;

	public GachaMenu(Player p) {
		player = p;
		gacha = new GachaSystem();
		window = new JFrame("Gacha Menu");
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.BLACK);
		scoreLabel = new JLabel();
		scoreLabel.setBounds(30,20,300,40);
		scoreLabel.setForeground(Color.WHITE);
		panel.add(scoreLabel);
		resultLabel = new JLabel("Press pull to wish");
		resultLabel.setBounds(30,70,500,40);
		resultLabel.setForeground(Color.YELLOW);
		panel.add(resultLabel);
		pullButton = new JButton("Wish x1");
		pullButton.setBounds(30,130,200,50);
		panel.add(pullButton);
		inventoryArea = new JTextArea();
		inventoryArea.setEditable(false);
		inventoryArea.setBackground(Color.DARK_GRAY);
		inventoryArea.setForeground(Color.WHITE);
		JScrollPane scroll = new JScrollPane(inventoryArea);
		scroll.setBounds(30,220,320,300);
		panel.add(scroll);
		pullButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GachaItem reward = gacha.pull(player);
				if (reward != null) {
					player.playerinventory.addItem(reward);
					player.apply(reward);
					resultLabel.setText(
						"You pulled: " +
						reward.getName() +
						" [" +
						reward.getRarity() +
						"]"
					);
					refreshInventory();
				}
				updateLabels();
			}
		});
		
		updateLabels();
		refreshInventory();
		window.add(panel);
		window.setSize(400,600);
		window.setVisible(false);;
	
	}

	public void updateLabels() {
		scoreLabel.setText("Score: " + player.score);
	}

	public void showM(){
		window.setVisible(true);
		window.toFront();
	}

	public void refreshInventory() {
		inventoryArea.setText("");
		for (int i = 0; i < player.playerinventory.ownedItems.size(); i++) {
			GachaItem item = player.playerinventory.ownedItems.get(i);
			inventoryArea.append(i + ": " + item + "\n");
		}
	}
}
