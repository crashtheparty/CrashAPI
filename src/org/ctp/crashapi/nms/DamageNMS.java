package org.ctp.crashapi.nms;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.ctp.crashapi.nms.damage.Damage_1;
import org.ctp.crashapi.nms.damage.Damage_2;
import org.ctp.crashapi.nms.damage.Damage_v1_16_R3;

public class DamageNMS extends NMS{

	public static void damageEntity(LivingEntity entity, String cause, float damage) {
		switch (getVersionNumber()) {
			case 16:
				Damage_v1_16_R3.damageEntity(entity, cause, damage);
				break;
			default:
				if (isSimilarOrAbove(getVersionNumbers(), 1, 18, 0)) Damage_2.damageEntity(entity, cause, damage);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 17, 0)) Damage_1.damageEntity(entity, cause, damage);
				break;
		}
	}

	public static void damageEntity(LivingEntity entity, Player player, String cause, float damage) {
		switch (getVersionNumber()) {
			case 16:
				Damage_v1_16_R3.damageEntity(entity, player, cause, damage);
				break;
			default:
				if (isSimilarOrAbove(getVersionNumbers(), 1, 18, 0)) Damage_2.damageEntity(entity, player, cause, damage);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 17, 0)) Damage_1.damageEntity(entity, player, cause, damage);
				break;
		}
	}

	public static double getArrowDamage(LivingEntity entity, Arrow arrow) {
		switch (getVersionNumber()) {
			case 16:
				return Damage_v1_16_R3.getArrowDamage(entity, arrow);
			default:
				if (isSimilarOrAbove(getVersionNumbers(), 1, 18, 0)) return Damage_2.getArrowDamage(entity, arrow);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 17, 0)) return Damage_1.getArrowDamage(entity, arrow);
		}
		return 0;
	}

	public static void updateHealth(LivingEntity entity) {
		switch (getVersionNumber()) {
			case 16:
				Damage_v1_16_R3.updateHealth(entity);
				break;
			default:
				if (isSimilarOrAbove(getVersionNumbers(), 1, 18, 0)) Damage_2.updateHealth(entity);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 17, 0)) Damage_1.updateHealth(entity);
				break;
		}
	}

}
