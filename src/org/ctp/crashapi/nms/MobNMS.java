package org.ctp.crashapi.nms;

import org.bukkit.entity.Entity;
import org.ctp.crashapi.CrashAPI;
import org.ctp.crashapi.nms.mob.Mob_v1_16_R3;
import org.ctp.crashapi.nms.mob.Mob_v1_17_R1;
import org.ctp.crashapi.nms.mob.Mob_v1_18_R1;

public class MobNMS {
	
	public static String getNBTData(Entity entity, boolean husbandry) {
		switch (CrashAPI.getPlugin().getBukkitVersion().getVersionNumber()) {
			case 16:
				return Mob_v1_16_R3.getNBTData(entity, husbandry);
			case 18:
				return Mob_v1_17_R1.getNBTData(entity, husbandry);
			case 19:
			case 20:
				return Mob_v1_18_R1.getNBTData(entity, husbandry);
		}
		return "{}";
	}

	public static Entity setNBTData(Entity entity, String s) {
		switch (CrashAPI.getPlugin().getBukkitVersion().getVersionNumber()) {
			case 16:
				return Mob_v1_16_R3.setNBTData(entity, s);
			case 18:
				return Mob_v1_17_R1.setNBTData(entity, s);
			case 19:
			case 20:
				return Mob_v1_18_R1.setNBTData(entity, s);
		}
		return entity;
	}
}
