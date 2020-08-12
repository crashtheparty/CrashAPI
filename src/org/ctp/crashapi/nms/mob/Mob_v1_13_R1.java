package org.ctp.crashapi.nms.mob;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Ocelot;

public class Mob_v1_13_R1 {

	public static String getCatType(LivingEntity entity) {
		if (entity instanceof Ocelot) {
			Ocelot ocelot = (Ocelot) entity;
			return ocelot.getCatType().name();
		}
		return "";
	}
	
}
