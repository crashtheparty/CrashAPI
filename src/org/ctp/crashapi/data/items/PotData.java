package org.ctp.crashapi.data.items;

import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.potion.PotionEffectType;

public class PotData {
	public static PotData JUMP = (new PotData("JUMP").getPotionEffect() == null ? new PotData("JUMP_BOOST") : new PotData("JUMP"));
	public static PotData SLOW = (new PotData("SLOW").getPotionEffect() == null ? new PotData("SLOWNESS") : new PotData("SLOW"));
	public static PotData HARM = (new PotData("HARM").getPotionEffect() == null ? new PotData("INSTANT_DAMAGE") : new PotData("HARM"));
	
	private PotionEffectType potion;
	private String potionName;

	public PotData(String name) {
		potionName = name.toUpperCase();
		try {
			potion = Registry.EFFECT.get(NamespacedKey.minecraft(potionName));
		} catch (Exception ex) {}
	}

	public PotionEffectType getPotionEffect() {
		return potion;
	}

	public String getPotionName() {
		return potionName;
	}
}
