package org.ctp.crashapi.nms;

import org.bukkit.inventory.ItemStack;
import org.ctp.crashapi.CrashAPI;
import org.ctp.crashapi.nms.item.ItemNMS_v1_16_R3;
import org.ctp.crashapi.nms.item.ItemNMS_v1_17_R1;
import org.ctp.crashapi.nms.item.ItemNMS_v1_18_R1;

public class ItemNMS {

	public static ItemStack addNBTData(ItemStack item, String key, int value) {
		switch (CrashAPI.getPlugin().getBukkitVersion().getVersionNumber()) {
			case 16:
				return ItemNMS_v1_16_R3.addNBTData(item, key, value);
			case 18:
				return ItemNMS_v1_17_R1.addNBTData(item, key, value);
			case 19:
			case 20:
				return ItemNMS_v1_18_R1.addNBTData(item, key, value);
		}
		return item;
	}

	public static int getNBTData(ItemStack item, String key) {
		switch (CrashAPI.getPlugin().getBukkitVersion().getVersionNumber()) {
			case 16:
				return ItemNMS_v1_16_R3.getNBTData(item, key);
			case 18:
				return ItemNMS_v1_17_R1.getNBTData(item, key);
			case 19:
			case 20:
				return ItemNMS_v1_18_R1.getNBTData(item, key);
		}
		return 0;
	}
}
