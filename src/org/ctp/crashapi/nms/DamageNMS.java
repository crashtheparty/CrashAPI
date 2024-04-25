package org.ctp.crashapi.nms;

import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.ctp.crashapi.nms.damage.*;

public class DamageNMS extends NMS{

	public static void damageEntity(LivingEntity entity, String cause, float damage) {
		switch (getVersionNumber()) {
			case 16:
				Damage_v1_16_R3.damageEntity(entity, cause, damage);
				break;
			default:
				if (isSimilarOrAbove(getVersionNumbers(), 1, 20, 5)) Damage_7.damageEntity(entity, cause, damage);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 20, 3)) Damage_6.damageEntity(entity, cause, damage);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 20, 2)) Damage_5.damageEntity(entity, cause, damage);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 20, 0)) Damage_4.damageEntity(entity, cause, damage);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 19, 4)) Damage_3.damageEntity(entity, cause, damage);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 18, 0)) Damage_2.damageEntity(entity, cause, damage);
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
				if (isSimilarOrAbove(getVersionNumbers(), 1, 20, 5)) Damage_7.damageEntity(entity, player, cause, damage);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 20, 3)) Damage_6.damageEntity(entity, player, cause, damage);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 20, 2)) Damage_5.damageEntity(entity, player, cause, damage);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 20, 0)) Damage_4.damageEntity(entity, player, cause, damage);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 19, 4)) Damage_3.damageEntity(entity, player, cause, damage);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 18, 0)) Damage_2.damageEntity(entity, player, cause, damage);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 17, 0)) Damage_1.damageEntity(entity, player, cause, damage);
				break;
		}
	}

	public static double getArrowDamage(LivingEntity entity, AbstractArrow arrow) {
		switch (getVersionNumber()) {
			case 16:
				return Damage_v1_16_R3.getArrowDamage(entity, arrow);
			default:
				if (isSimilarOrAbove(getVersionNumbers(), 1, 20, 5)) return Damage_11.getArrowDamage(entity, arrow);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 20, 3)) return Damage_10.getArrowDamage(entity, arrow);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 20, 2)) return Damage_9.getArrowDamage(entity, arrow);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 20, 0)) return Damage_8.getArrowDamage(entity, arrow);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 19, 4)) return Damage_7.getArrowDamage(entity, arrow);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 19, 3)) return Damage_6.getArrowDamage(entity, arrow);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 19, 2)) return Damage_5.getArrowDamage(entity, arrow);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 19, 0)) return Damage_4.getArrowDamage(entity, arrow);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 18, 2)) return Damage_3.getArrowDamage(entity, arrow);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 18, 0)) return Damage_2.getArrowDamage(entity, arrow);
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
				if (isSimilarOrAbove(getVersionNumbers(), 1, 20, 5)) Damage_11.updateHealth(entity);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 20, 3)) Damage_10.updateHealth(entity);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 20, 2)) Damage_9.updateHealth(entity);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 20, 0)) Damage_8.updateHealth(entity);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 19, 4)) Damage_7.updateHealth(entity);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 19, 3)) Damage_6.updateHealth(entity);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 19, 2)) Damage_5.updateHealth(entity);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 19, 0)) Damage_4.updateHealth(entity);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 18, 2)) Damage_3.updateHealth(entity);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 18, 0)) Damage_2.updateHealth(entity);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 17, 0)) Damage_1.updateHealth(entity);
				break;
		}
	}

}
