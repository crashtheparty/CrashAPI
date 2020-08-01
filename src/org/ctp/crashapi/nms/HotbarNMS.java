package org.ctp.crashapi.nms;

import org.bukkit.entity.Player;
import org.ctp.crashapi.CrashAPI;
import org.ctp.crashapi.nms.hotbar.*;

public class HotbarNMS {

	public static void sendHotBarMessage(Player player, String message) {
		switch (CrashAPI.getPlugin().getBukkitVersion().getVersionNumber()) {
			case 1:
				Hotbar_v1_13_R1.sendHotBarMessage(player, message);
				break;
			case 2:
			case 3:
				Hotbar_v1_13_R2.sendHotBarMessage(player, message);
				break;
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
				Hotbar_v1_14_R1.sendHotBarMessage(player, message);
				break;
			case 9:
			case 10:
			case 11:
				Hotbar_v1_15_R1.sendHotBarMessage(player, message);
				break;
			case 12:
				Hotbar_v1_16_R1.sendHotBarMessage(player, message);
				break;
		}
	}
}
