package org.ctp.crashapi.data.items;

public class CustomItemType extends ItemType {

	private final VanillaItemType vanilla;

	public CustomItemType(String type, VanillaItemType vanilla) {
		super(type);
		this.vanilla = vanilla;
	}

	public VanillaItemType getVanilla() {
		return vanilla;
	}

}
