package org.ctp.crashapi.compatibility;

import org.bukkit.inventory.ItemStack;
import org.ctp.crashapi.CrashAPIPlugin;
import org.ctp.crashapi.data.items.CustomItemType;
import org.ctp.crashapi.data.items.ItemData;

import net.Indyuce.mmoitems.api.Type;

public class MMOUtils {

	private static boolean isEnabled() {
		return CrashAPIPlugin.getMMOItems();
	}

	public static String getMMOTypeString(ItemStack item) {
		if (!isEnabled()) return null;
		Type type = Type.get(item);
		if (type == null) return "";
		return type.getId();
	}

	public static String getMMOTypeSetString(ItemStack item) {
		if (!isEnabled()) return null;
		Type type = Type.get(item);
		if (type == null) return "";
		return type.getItemSet().name();
	}

	public static boolean check(ItemData item, CustomItemType mmo) {
		if (!isEnabled()) return false;

		if (item.getMMOType() == null || !Type.isValid(item.getMMOType())) return false;
		Type type = Type.get(item.getMMOType());
		String customString = mmo.getType().split(":")[2];

		switch (mmo.getVanilla().name().toUpperCase()) {
			case "TYPE":
				return type.getId().equalsIgnoreCase(customString);
			case "TYPE_SET":
				return type.getItemSet().name().equalsIgnoreCase(customString);
		}
		return false;
	}
}
