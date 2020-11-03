package org.ctp.crashapi.nms.mob;

import org.bukkit.entity.Cat;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Ocelot;

public class Mob_v1_16_R3 {

	public static String getCatType(LivingEntity entity) {
		if (entity instanceof Cat) {
			Cat cat = (Cat) entity;
			return cat.getCatType().name();
		}
		if (entity instanceof Ocelot) return "WILD_OCELOT";
		return "";
	}

}
