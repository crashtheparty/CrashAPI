package org.ctp.crashapi.nms;

import org.bukkit.entity.LivingEntity;
import org.ctp.crashapi.CrashAPI;
import org.ctp.crashapi.nms.mob.*;

public class MobNMS {

	public static String getCatType(LivingEntity entity) {
		switch (CrashAPI.getPlugin().getBukkitVersion().getVersionNumber()) {
			case 1:
				return Mob_v1_13_R1.getCatType(entity);
			case 2:
			case 3:
				return Mob_v1_13_R2.getCatType(entity);
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
				return Mob_v1_14_R1.getCatType(entity);
			case 9:
			case 10:
			case 11:
				return Mob_v1_15_R1.getCatType(entity);
			case 12:
				return Mob_v1_16_R1.getCatType(entity);
			case 13:
				return Mob_v1_16_R2.getCatType(entity);
		}
		return "";
	}
}
