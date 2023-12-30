package org.ctp.crashapi.nms.damage;

import java.lang.reflect.Method;

import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.ctp.crashapi.CrashAPI;
import org.ctp.crashapi.nms.NMS;
import org.ctp.crashapi.utils.ChatUtils;

import net.minecraft.server.level.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.player.EntityHuman;
import net.minecraft.world.entity.projectile.EntityArrow;
import net.minecraft.world.phys.Vec3D;

public class Damage_3 extends NMS {

	public static void damageEntity(LivingEntity e, String cause, float damage) {
		try {
			EntityLiving l = (EntityLiving) getCraftBukkitEntity(e);
			Method m = Entity.class.getDeclaredMethod("dG");
			Class<?> c1 = Class.forName("net.minecraft.world.damagesource.DamageSources");
			Method m1 = c1.getDeclaredMethod("n"); // generic
			Object o = m1.invoke(m.invoke(l));
			switch (cause) {
				case "drown":
					Method m2 = c1.getDeclaredMethod("i");
					o = m2.invoke(m.invoke(l));
			}
			if (o instanceof DamageSource) getCraftBukkitEntity(e).a((DamageSource) o, damage);
			else
				ChatUtils.getUtils(CrashAPI.getPlugin()).sendInfo("Issue with DamageEntity NMS - field not a damage source");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void damageEntity(LivingEntity e, Player p, String cause, float damage) {
		try {
			EntityLiving l = (EntityLiving) getCraftBukkitEntity(e);
			DamageSource source = null;
			Method m = Entity.class.getDeclaredMethod("dG");
			Class<?> c1 = Class.forName("net.minecraft.world.damagesource.DamageSources");
			Method m1 = c1.getDeclaredMethod("n"); // generic
			Object o = m1.invoke(m.invoke(l));
			if (o instanceof DamageSource) {
				source = (DamageSource) o;
				Entity entity = getCraftBukkitEntity(e);
				EntityPlayer player = (EntityPlayer) getCraftBukkitEntity(p);
				switch (cause) {
					case "arrow":
						Method m2 = c1.getDeclaredMethod("a", EntityHuman.class);
						Object o2 = m2.invoke(m.invoke(l), player);
						if (o2 instanceof DamageSource) source = (DamageSource) o2;
						else
							ChatUtils.getUtils(CrashAPI.getPlugin()).sendInfo("Issue with DamageEntity NMS - object not a damage source");
				}
				entity.a(source, damage);
			} else
				ChatUtils.getUtils(CrashAPI.getPlugin()).sendInfo("Issue with DamageEntity NMS - generic object not a damage source");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static double getArrowDamage(LivingEntity le, AbstractArrow a) {
		EntityArrow arrow = (EntityArrow) getCraftBukkitEntity(a);
		EntityLiving entity = (EntityLiving) getCraftBukkitEntity(le);
		float f = 0;
		try {
			Class<?> c = Entity.class;
			Method m = c.getDeclaredMethod("da");
			Object o = m.invoke(arrow);
			if (o instanceof Vec3D) f = (float) ((Vec3D) o).f();
			Class<?> cArrow = EntityArrow.class;
			Method dm = cArrow.getDeclaredMethod("n");
			Object dmo = dm.invoke(arrow);
			if (dmo instanceof Number) {
				int i = (int) MathHelper.e((float) Math.max(f * (int) ((Double) dmo).doubleValue(), 0.0D));
				arrow.a(entity, i);
				return (int) ((Double) dm.invoke(arrow)).doubleValue() / 2;
			}
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
				Method m1 = c.getMethod("ea");
				Object o1 = m1.invoke(living);
				Method m2 = c.getMethod("c", float.class);
				if (o1 instanceof Number) m2.invoke(living, (float) o1);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
