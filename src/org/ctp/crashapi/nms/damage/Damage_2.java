package org.ctp.crashapi.nms.damage;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.ctp.crashapi.nms.NMS;

import net.minecraft.server.level.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.projectile.EntityArrow;

public class Damage_2 extends NMS {

	public static void damageEntity(LivingEntity e, String cause, float damage) {
		DamageSource source = DamageSource.n;
		switch (cause) {
			case "drown":
				source = DamageSource.h;
		}
		getCraftBukkitEntity(e).a(source, damage);
	}

	public static void damageEntity(LivingEntity e, Player p, String cause, float damage) {
		DamageSource source = DamageSource.n;
		Entity entity = getCraftBukkitEntity(e);
		EntityPlayer player = (EntityPlayer) getCraftBukkitEntity(p);
		switch (cause) {
			case "arrow":
				source = DamageSource.a(player);
		}
		entity.a(source, damage);
	}

	public static double getArrowDamage(LivingEntity le, Arrow a) {
		EntityArrow arrow = (EntityArrow) getCraftBukkitEntity(a);
		EntityLiving entity = (EntityLiving) getCraftBukkitEntity(le);
		float f = (float) arrow.da().f();
		int i = MathHelper.e(Math.max(f * arrow.n(), 0.0D));
		arrow.a(entity, i);
		return (int) arrow.n() / 2;
	}

	public static void updateHealth(LivingEntity le) {
		Entity entity = getCraftBukkitEntity(le);
		if (entity instanceof EntityLiving) {
			EntityLiving living = (EntityLiving) entity;
			living.c(living.dZ());
		}
	}
}
