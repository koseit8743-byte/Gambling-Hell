import java.util.ArrayList;

public class Inventory {
	ArrayList<GachaItem> ownedItems = new ArrayList<>();
	public void addItem(GachaItem item) {
		ownedItems.add(item);
		System.out.println("Added to inventory: " + item);
	}
	public void showInventory() {
		System.out.println("\n=== INVENTORY ===");
		if (ownedItems.size() == 0) {
			System.out.println("Inventory empty");
			return;
		}
		for (int i = 0; i < ownedItems.size(); i++) {
			System.out.println(i + ": " + ownedItems.get(i));
		}
	}
	public GachaItem getItem(int index) {
		if (index < 0 || index >= ownedItems.size() {
			return null;
		}
		retrun ownedItems.get(index);
	}
}
