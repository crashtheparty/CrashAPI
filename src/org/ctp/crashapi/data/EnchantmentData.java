package org.ctp.crashapi.data;

import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;

public class EnchantmentData {

	public static final Enchantment UNBREAKING = new EnchantmentData("unbreaking").getEnchantment();
	public static final Enchantment AQUA_AFFINITY = new EnchantmentData("aqua_affinity").getEnchantment();
	public static final Enchantment BANE_OF_ARTHROPODS = new EnchantmentData("bane_of_arthropods").getEnchantment();
	public static final Enchantment BLAST_PROTECTION = new EnchantmentData("blast_protection").getEnchantment();
	public static final Enchantment EFFICIENCY = new EnchantmentData("efficiency").getEnchantment();
	public static final Enchantment FEATHER_FALLING = new EnchantmentData("feather_falling").getEnchantment();
	public static final Enchantment FIRE_PROTECTION = new EnchantmentData("fire_protection").getEnchantment();
	public static final Enchantment FLAME = new EnchantmentData("flame").getEnchantment();
	public static final Enchantment FORTUNE = new EnchantmentData("fortune").getEnchantment();
	public static final Enchantment INFINITY = new EnchantmentData("infinity").getEnchantment();
	public static final Enchantment LOOTING = new EnchantmentData("looting").getEnchantment();
	public static final Enchantment LUCK_OF_THE_SEA = new EnchantmentData("luck_of_the_sea").getEnchantment();
	public static final Enchantment POWER = new EnchantmentData("power").getEnchantment();
	public static final Enchantment PROJECTILE_PROTECTION = new EnchantmentData("projectile_protection").getEnchantment();
	public static final Enchantment PROTECTION = new EnchantmentData("protection").getEnchantment();
	public static final Enchantment PUNCH = new EnchantmentData("punch").getEnchantment();
	public static final Enchantment RESPIRATION = new EnchantmentData("respiration").getEnchantment();
	public static final Enchantment SHARPNESS = new EnchantmentData("sharpness").getEnchantment();
	public static final Enchantment SMITE = new EnchantmentData("smite").getEnchantment();
	public static final Enchantment SWEEPING_EDGE = (new EnchantmentData("sweeping").getEnchantment() == null ? new EnchantmentData("sweeping_edge").getEnchantment() : new EnchantmentData("sweeping").getEnchantment());
	public static final Enchantment SWIFT_SNEAK = (new EnchantmentData("swift_sneak").getEnchantment() == null ? null : new EnchantmentData("swift_sneak").getEnchantment());
	public static final Enchantment BREACH = (new EnchantmentData("breach").getEnchantment() == null ? null : new EnchantmentData("breach").getEnchantment());
	public static final Enchantment DENSITY = (new EnchantmentData("density").getEnchantment() == null ? null : new EnchantmentData("density").getEnchantment());
	public static final Enchantment WIND_BURST = (new EnchantmentData("wind_burst").getEnchantment() == null ? null : new EnchantmentData("wind_burst").getEnchantment());
	
	private Enchantment enchant;
	private String enchantName;

	public EnchantmentData(String name) {
		enchantName = name.toUpperCase();
		try {
			enchant = Registry.ENCHANTMENT.get(NamespacedKey.minecraft(name.toLowerCase()));
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
