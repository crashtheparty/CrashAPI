package org.ctp.crashapi.nms;

import org.bukkit.inventory.ItemStack;
import org.ctp.crashapi.CrashAPI;
import org.ctp.crashapi.nms.item.*;

public class ItemNMS {

	public static ItemStack addNBTData(ItemStack item, String key, int value) {
		switch (CrashAPI.getPlugin().getBukkitVersion().getVersionNumber()) {
			case 1:
				return ItemNMS_v1_13_R1.addNBTData(item, key, value);
			case 2:
			case 3:
				return ItemNMS_v1_13_R2.addNBTData(item, key, value);
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
				return ItemNMS_v1_14_R1.addNBTData(item, key, value);
			case 9:
			case 10:
			case 11:
				return ItemNMS_v1_15_R1.addNBTData(item, key, value);
			case 12:
				return ItemNMS_v1_16_R1.addNBTData(item, key, value);
			case 13:
			case 14:
				return ItemNMS_v1_16_R2.addNBTData(item, key, value);
			case 15:
			case 16:
				return ItemNMS_v1_16_R3.addNBTData(item, key, value);
			case 17:
			case 18:
				return ItemNMS_v1_17_R1.addNBTData(item, key, value);
		}
		return item;
	}

	public static int getNBTData(ItemStack item, String key) {
		switch (CrashAPI.getPlugin().getBukkitVersion().getVersionNumber()) {
			case 1:
				return ItemNMS_v1_13_R1.getNBTData(item, key);
			case 2:
			case 3:
				return ItemNMS_v1_13_R2.getNBTData(item, key);
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
				return ItemNMS_v1_14_R1.getNBTData(item, key);
			case 9:
			case 10:
			case 11:
				return ItemNMS_v1_15_R1.getNBTData(item, key);
			case 12:
				return ItemNMS_v1_16_R1.getNBTData(item, key);
			case 13:
			case 14:
				return ItemNMS_v1_16_R2.getNBTData(item, key);
			case 15:
			case 16:
				return ItemNMS_v1_16_R3.getNBTData(item, key);
			case 17:
			case 18:
				return ItemNMS_v1_17_R1.getNBTData(item, key);
		}
		return 0;
	}
}
