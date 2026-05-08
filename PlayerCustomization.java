import java.awt.Color;

public class PlayerCustomization {
	Color equippedPlayerColor = Color.GREEN;
	Color equippedBulletColor = Color.WHITE;
	public void equipItem(GachaItem item) {
		if (item == null) {
			return;
		}
		if (item.getType().equals("PLAYER")) {
			equippedPlayerColor = item.getColor();
			System.out.println("Equipped player cosmetic: " + item.getName());
		}
		else if (item.getType().equals("BULLET")) {
			equippedBulletColor = item.getColor();
			System.out.println("Equipped bullet cosmetic: " + item.getName());
		}
	}

	public Color getPlayerColor() {
		return equippedPlayerColor;
	}
	public Color getBulletColor() {
		return equippedBulletColor;
	}
}
