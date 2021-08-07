package org.ctp.crashapi.nms;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.ctp.crashapi.CrashAPI;
import org.ctp.crashapi.nms.mob.*;

public class MobNMS {

	public static String getCatType(LivingEntity entity) {
		if (CrashAPI.getPlugin().getBukkitVersion().getVersionNumber() < 4) return CatType_1_13.getCatType(entity);
		return CatType_1_14.getCatType(entity);
	}
	
	public static String getNBTData(Entity entity, boolean husbandry) {
		switch (CrashAPI.getPlugin().getBukkitVersion().getVersionNumber()) {
			case 1:
				return Mob_v1_13_R1.getNBTData(entity, husbandry);
			case 2:
			case 3:
				return Mob_v1_13_R2.getNBTData(entity, husbandry);
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
				return Mob_v1_14_R1.getNBTData(entity, husbandry);
			case 9:
			case 10:
			case 11:
				return Mob_v1_15_R1.getNBTData(entity, husbandry);
			case 12:
				return Mob_v1_16_R1.getNBTData(entity, husbandry);
			case 13:
			case 14:
				return Mob_v1_16_R2.getNBTData(entity, husbandry);
			case 15:
			case 16:
				return Mob_v1_16_R3.getNBTData(entity, husbandry);
			case 17:
			case 18:
				return Mob_v1_17_R1.getNBTData(entity, husbandry);
		}
		return "{}";
	}

	public static Entity setNBTData(Entity entity, String s) {
		switch (CrashAPI.getPlugin().getBukkitVersion().getVersionNumber()) {
			case 1:
				return Mob_v1_13_R1.setNBTData(entity, s);
			case 2:
			case 3:
				return Mob_v1_13_R2.setNBTData(entity, s);
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
				return Mob_v1_14_R1.setNBTData(entity, s);
			case 9:
			case 10:
			case 11:
				return Mob_v1_15_R1.setNBTData(entity, s);
			case 12:
				return Mob_v1_16_R1.setNBTData(entity, s);
			case 13:
			case 14:
				return Mob_v1_16_R2.setNBTData(entity, s);
			case 15:
			case 16:
				return Mob_v1_16_R3.setNBTData(entity, s);
			case 17:
			case 18:
				return Mob_v1_17_R1.setNBTData(entity, s);
		}
		return entity;
	}
}
