package org.ctp.crashapi.nms;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.ctp.crashapi.item.BlockSound;
import org.ctp.crashapi.nms.item.Item_1;
import org.ctp.crashapi.nms.item.Item_2;
import org.ctp.crashapi.nms.item.Item_v1_16_R3;

public class ItemNMS extends NMS {

	public static ItemStack addNBTData(ItemStack item, String key, int value) {
		switch (getVersionNumber()) {
			case 16:
				return Item_v1_16_R3.addNBTData(item, key, value);
			default:
				if (isSimilarOrAbove(getVersionNumbers(), 1, 18, 0)) return Item_2.addNBTData(item, key, value);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 17, 0)) return Item_1.addNBTData(item, key, value);
		}
		return item;
	}

	public static int getNBTData(ItemStack item, String key) {
		switch (getVersionNumber()) {
			case 16:
				return Item_v1_16_R3.getNBTData(item, key);
			default:
				if (isSimilarOrAbove(getVersionNumbers(), 1, 18, 0)) return Item_2.getNBTData(item, key);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 17, 0)) return Item_1.getNBTData(item, key);
		}
		return 0;
	}
	
	public static BlockSound getBlockSound(Block b, String s) {
		switch (getVersionNumber()) {
			case 16:
				return Item_v1_16_R3.getSound(b, s);
			default:
				if (isSimilarOrAbove(getVersionNumbers(), 1, 17, 0)) return Item_1.getSound(b, s);
		}
		return new BlockSound("", 1, 1);
	}
}
