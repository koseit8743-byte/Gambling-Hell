public enum Rarity {
	RARE(85),
	EPIC(13),
	LEGENDARY(2);

	private final int chance;
	Rarity(int chance) {
		this.chance = chance;
	}
	public int getChance() {
		return chance;
	}
}
