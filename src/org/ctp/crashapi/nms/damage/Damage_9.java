package org.ctp.crashapi.nms.damage;

import java.lang.reflect.Method;

import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.LivingEntity;
import org.ctp.crashapi.CrashAPI;
import org.ctp.crashapi.nms.NMS;
import org.ctp.crashapi.utils.ChatUtils;

import net.minecraft.util.MathHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.projectile.EntityArrow;
import net.minecraft.world.phys.Vec3D;

public class Damage_9 extends NMS {

	public static double getArrowDamage(LivingEntity le, AbstractArrow a) {
		EntityArrow arrow = (EntityArrow) getCraftBukkitEntity(a);
		EntityLiving entity = (EntityLiving) getCraftBukkitEntity(le);
		float f = 1;
		try {
			Class<?> c = Entity.class;
			Method m = c.getDeclaredMethod("do");
			Object o = m.invoke(arrow);
			if (o instanceof Vec3D) f = (float) ((Vec3D) o).f();
			else
				ChatUtils.getUtils(CrashAPI.getPlugin()).sendInfo("Issue with Hollow Point NMS - getDeltaMovement() not found");
			Class<?> cArrow = EntityArrow.class;
			Method dm = cArrow.getDeclaredMethod("x");
			Object dmo = dm.invoke(arrow);
			if (dmo instanceof Number) {
				int i = (int) MathHelper.e((float) Math.max(f * (int) ((Double) dmo).doubleValue(), 0.0D));
				arrow.a(entity, i);
				return (int) ((Double) dm.invoke(arrow)).doubleValue() / 2;
			}
			else
				ChatUtils.getUtils(CrashAPI.getPlugin()).sendInfo("Issue with Hollow Point NMS - getArrowDamage() not found");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	public static void updateHealth(LivingEntity le) {
		Entity entity = getCraftBukkitEntity(le);
		if (entity instanceof EntityLiving) {
			EntityLiving living = (EntityLiving) entity;
			try {
				Class<?> c = EntityLiving.class;
				Method m1 = c.getMethod("eu");
				Object o1 = m1.invoke(living);
				Method m2 = c.getMethod("c", float.class);
				if (o1 instanceof Number) m2.invoke(living, (float) o1);
				else
					ChatUtils.getUtils(CrashAPI.getPlugin()).sendInfo("Issue with Update Health NMS - getHealth() not found");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
