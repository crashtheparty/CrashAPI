package org.ctp.crashapi.nms;

import org.bukkit.entity.Entity;
import org.ctp.crashapi.nms.mob.*;

public class MobNMS extends NMS {
	
	public static String getNBTData(Entity entity, boolean husbandry) {
		switch (getVersionNumber()) {
			case 16:
				return Mob_v1_16_R3.getNBTData(entity, husbandry);
			default:
				if (isSimilarOrAbove(getVersionNumbers(), 1, 18, 0)) return Mob_2.getNBTData(entity, husbandry);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 17, 0)) return Mob_1.getNBTData(entity, husbandry);
		}
		return "{}";
	}

	public static Entity setNBTData(Entity entity, String s) {
		switch (getVersionNumber()) {
			case 16:
				return Mob_v1_16_R3.setNBTData(entity, s);
			default:
				if (isSimilarOrAbove(getVersionNumbers(), 1, 19, 0)) return Mob_3.setNBTData(entity, s);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 18, 0)) return Mob_2.setNBTData(entity, s);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 17, 0)) return Mob_1.setNBTData(entity, s);
		}
		return entity;
	}
}
