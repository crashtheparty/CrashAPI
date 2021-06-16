package org.ctp.crashapi.nms.mob;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Ocelot;

public class CatType_1_13 {
	public static String getCatType(LivingEntity entity) {
		if (entity instanceof Ocelot) {
			Ocelot ocelot = (Ocelot) entity;
			return ocelot.getCatType().name();
		}
		return "";
	}
}
