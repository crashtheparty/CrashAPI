package org.ctp.crashapi.nms;

import org.ctp.crashapi.enchantment.CrashEnchantment;

public class EnchantmentNMS extends NMS {
	public static void registerEnchantment(CrashEnchantment enchantment) {
		switch (getVersionNumber()) {
			case 16:
				break;
			default:
//				if (isSimilarOrAbove(getVersionNumbers(), 1, 20, 3)) Enchantment_1.registerEnchantment(enchantment);
				break;
		}
	}
}
