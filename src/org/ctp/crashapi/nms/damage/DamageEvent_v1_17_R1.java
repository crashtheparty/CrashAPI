package org.ctp.crashapi.nms.damage;

import java.lang.reflect.Method;

import org.bukkit.craftbukkit.v1_17_R1.entity.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import net.minecraft.server.level.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.player.EntityHuman;
import net.minecraft.world.entity.projectile.EntityArrow;
import net.minecraft.world.phys.Vec3D;

public class DamageEvent_v1_17_R1 {

	public static void damageEntity(LivingEntity e, String cause, float damage) {
		DamageSource source = DamageSource.n;
		switch (cause) {
			case "drown":
				source = DamageSource.h;
		}
		try {
			Class<?> c = Entity.class;
			Method m = c.getDeclaredMethod("damageEntity", DamageSource.class, float.class);
			m.invoke(((CraftEntity) e).getHandle(), source, damage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void damageEntity(LivingEntity e, Player p, String cause, float damage) {
		DamageSource source = DamageSource.n;
		Entity entity = ((CraftEntity) e).getHandle();
		EntityPlayer player = ((CraftPlayer) p).getHandle();
		switch (cause) {
			case "arrow":
				try {
					Class<?> c = DamageSource.class;
					Method m = c.getDeclaredMethod("playerAttack", EntityHuman.class);
					Object o = m.invoke(null, player);
					if (o instanceof DamageSource) source = (DamageSource) o;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
		try {
			Class<?> c = Entity.class;
			Method m = c.getDeclaredMethod("damageEntity", DamageSource.class, float.class);
			m.invoke(entity, source, damage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static double getArrowDamage(LivingEntity le, Arrow a) {
		EntityArrow arrow = ((CraftArrow) a).getHandle();
		EntityLiving entity = ((CraftLivingEntity) le).getHandle();
		float f = 0;
		try {
			Class<?> c = Entity.class;
			Method m = c.getDeclaredMethod("getMot");
			Object o = m.invoke(arrow);
			if (o instanceof Vec3D) f = (float) ((Vec3D) o).f();
			Class<?> cArrow = EntityArrow.class;
			Method dm = cArrow.getDeclaredMethod("getDamage");
			Object dmo = dm.invoke(arrow);
			if (dmo instanceof Number) {
				int i = MathHelper.e(Math.max(f * (Integer) dmo, 0.0D));
				arrow.a(entity, i);
				return (int) dm.invoke(arrow) / 2;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	public static void updateHealth(LivingEntity le) {
		Entity entity = ((CraftEntity) le).getHandle();
		if (entity instanceof EntityLiving) {
			EntityLiving living = (EntityLiving) entity;
			try {
				Class<?> c = EntityLiving.class;
				Method m1 = c.getMethod("getHealth");
				Object o1 = m1.invoke(living);
				Method m2 = c.getMethod("setHealth", float.class);
				if (o1 instanceof Number) m2.invoke(living, (float) o1);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
