package org.ctp.crashapi.nms;

import org.bukkit.entity.LivingEntity;
import org.ctp.crashapi.CrashAPI;
import org.ctp.crashapi.nms.mob.CatType_1_13;
import org.ctp.crashapi.nms.mob.CatType_1_14;

public class MobNMS {

	public static String getCatType(LivingEntity entity) {
		if (CrashAPI.getPlugin().getBukkitVersion().getVersionNumber() < 4) return CatType_1_13.getCatType(entity);
		return CatType_1_14.getCatType(entity);
	}
}
