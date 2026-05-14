import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

public class GachaSystem {
	ArrayList<GachaItem> lootPool = new ArrayList<>();
	Random rand = new Random();
	int pullCost = 50;
	public GachaSystem() {
		lootPool.add(new GachaItem(
					"Blue Bullets",
					Rarity.RARE,
					"BULLET",
					Color.BLUE
					));
		lootPool.add(new GachaItem(
					"Green Skin",
					Rarity.RARE,
					"PLAYER",
					Color.GREEN
					));
		lootPool.add(new GachaItem(
					"Purple Bullets",
					Rarity.EPIC,
					"BULLET",
					Color.MAGENTA
					));
		lootPool.add(new GachaItem(
					"Purple Skin",
					Rarity.EPIC,
					"PLAYER",
					new Color(128, 0, 128)
					));
		lootPool.add(new GachaItem(
					"Gold Bullets",
					Rarity.LEGENDARY,
					"BULLET",
					Color.YELLOW
					));
		lootPool.add(new GachaItem(
					"Gold Skin",
					Rarity.LEGENDARY,
					"PLAYER",
					Color.ORANGE
					));
	}
	public GachaItem pull(Player player) {
		if (player.score < pullCost) {
			System.out.println("Not enough sore to pull.");
			return null;
		}
		player.score -= pullCost;
		int roll = rand.nextInt(100) + 1;
		Rarity selectedRarity;
		if (roll <= 2) {
			selectedRarity = Rarity.LEGENDARY;
		}
		else if (roll <= 15) {
			selectedRarity = Rarity.EPIC;
		}
		else {
			selectedRarity = Rarity.RARE;
		}
		ArrayList<GachaItem> validItems = new ArrayList<>();
		for (GachaItem item : lootPool) {
			if (item.getRarity() == selectedRarity) {
				validItems.add(item);
			}
		}
		// takes a random item from the validItems arrayList (has every reward of the correct rarity)
		GachaItem reward = validItems.get(rand.nextInt(validItems.size()));
		System.out.println("\n====================");
		System.out.println("YOU PULLED:");
		System.out.println(reward);
		System.out.println("\n====================");
		return reward;
	}
}
