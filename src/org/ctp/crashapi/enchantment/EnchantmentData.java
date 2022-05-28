package org.ctp.crashapi.enchantment;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

public class EnchantmentData {

	private Enchantment enchant;
	private String enchantName;

	public EnchantmentData(String name) {
		enchantName = name.toUpperCase();
		try {
			enchant = Enchantment.getByKey(NamespacedKey.minecraft(name));
		} catch (Exception ex) {}
	}

	public Enchantment getEnchantment() {
		return enchant;
	}

	public String getEnchantmentName() {
		return enchantName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof EnchantmentData) {
			EnchantmentData data = (EnchantmentData) obj;
			return data.enchant != null && data.enchant == enchant;
		}
		return false;
	}

	public boolean hasEnchantment() {
		return enchant != null;
	}

}
