package org.ctp.crashapi.nms;

import org.bukkit.entity.Trident;
import org.bukkit.inventory.ItemStack;
import org.ctp.crashapi.CrashAPI;
import org.ctp.crashapi.nms.item.*;

public class ItemNMS {

	public static ItemStack getTrident(Trident trident) {
		switch (CrashAPI.getPlugin().getBukkitVersion().getVersionNumber()) {
			case 1:
				return Item_v1_13_R1.getTrident(trident);
			case 2:
			case 3:
				return Item_v1_13_R2.getTrident(trident);
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
				return Item_v1_14_R1.getTrident(trident);
			case 9:
			case 10:
			case 11:
				return Item_v1_15_R1.getTrident(trident);
			case 12:
				return Item_v1_16_R1.getTrident(trident);
			case 13:
				return Item_v1_16_R2.getTrident(trident);
		}
		return null;
	}
}
