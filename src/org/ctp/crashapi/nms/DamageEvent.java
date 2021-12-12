package org.ctp.crashapi.nms;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.ctp.crashapi.CrashAPI;
import org.ctp.crashapi.nms.damage.DamageEvent_v1_16_R3;
import org.ctp.crashapi.nms.damage.DamageEvent_v1_17_R1;
import org.ctp.crashapi.nms.damage.DamageEvent_v1_18_R1;

public class DamageEvent {

	public static void damageEntity(LivingEntity entity, String cause, float damage) {
		switch (CrashAPI.getPlugin().getBukkitVersion().getVersionNumber()) {
			case 15:
			case 16:
				DamageEvent_v1_16_R3.damageEntity(entity, cause, damage);
				break;
			case 17:
			case 18:
				DamageEvent_v1_17_R1.damageEntity(entity, cause, damage);
				break;
			case 19:
			case 20:
				DamageEvent_v1_18_R1.damageEntity(entity, cause, damage);
				break;
		}
	}

	public static void damageEntity(LivingEntity entity, Player player, String cause, float damage) {
		switch (CrashAPI.getPlugin().getBukkitVersion().getVersionNumber()) {
			case 16:
				DamageEvent_v1_16_R3.damageEntity(entity, player, cause, damage);
				break;
			case 18:
				DamageEvent_v1_17_R1.damageEntity(entity, player, cause, damage);
				break;
			case 19:
			case 20:
				DamageEvent_v1_18_R1.damageEntity(entity, player, cause, damage);
				break;
		}
	}

	public static double getArrowDamage(LivingEntity entity, Arrow arrow) {
		switch (CrashAPI.getPlugin().getBukkitVersion().getVersionNumber()) {
			case 16:
				return DamageEvent_v1_16_R3.getArrowDamage(entity, arrow);
			case 18:
				return DamageEvent_v1_17_R1.getArrowDamage(entity, arrow);
			case 19:
			case 20:
				return DamageEvent_v1_18_R1.getArrowDamage(entity, arrow);
		}
		return 0;
	}

	public static void updateHealth(LivingEntity entity) {
		switch (CrashAPI.getPlugin().getBukkitVersion().getVersionNumber()) {
			case 16:
				DamageEvent_v1_16_R3.updateHealth(entity);
				break;
			case 18:
				DamageEvent_v1_17_R1.updateHealth(entity);
				break;
			case 19:
			case 20:
				DamageEvent_v1_18_R1.updateHealth(entity);
				break;
		}
	}

}
