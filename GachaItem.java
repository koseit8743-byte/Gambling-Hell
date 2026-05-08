import java.awt.Color;

public class GachaItem {
	String name;
	Rarity rarity;
	String type;
	Color color;
	public GachaItem(String n, Rarity r, String t, Color c) {
		name = n;
		rarity = r;
		type = t;
		color = c;
	}
	public String getName() {
		return name;
	}
	public Rarity getRarity() {
		return rarity;
	}
	public String getType() {
		return type;
	}
	public Color getColor() {
		return color;
	}
	public String toString() {
		return name + " [" + rarity + "]";
	}
}
