package org.ctp.crashapi.nms.mob;

import java.lang.reflect.Method;

import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;

import net.minecraft.nbt.MojangsonParser;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.phys.Vec3D;

public class Mob_v1_17_R1 {

	public static String getNBTData(Entity entity, boolean husbandry) {
		net.minecraft.world.entity.Entity e = ((CraftEntity) entity).getHandle();
		NBTTagCompound compound = new NBTTagCompound();
		try {
			Class<?> entityClass = net.minecraft.world.entity.Entity.class;
			Method save = entityClass.getDeclaredMethod("save", NBTTagCompound.class);
			Object o = save.invoke(e, compound);
			Class<?> compoundClass = NBTTagCompound.class;
			if (o instanceof NBTTagCompound) {
				compound = (NBTTagCompound) o;
				Method remove = compoundClass.getDeclaredMethod("remove", String.class);
				remove.invoke(compound, "UUID");
				if (husbandry) {
					remove.invoke(compound, "DeathTime");
					remove.invoke(compound, "Health");
					remove.invoke(compound, "SaddleItem");
				}
			}
			return compound.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "{}";
	}

	public static Entity setNBTData(Entity entity, String s) {
		net.minecraft.world.entity.Entity e = ((CraftEntity) entity).getHandle();
		try {
			Class<?> entityClass = net.minecraft.world.entity.Entity.class;
			Method posVector = entityClass.getDeclaredMethod("getPositionVector");
			Vec3D vector = (Vec3D) posVector.invoke(e);
			Class<?> parser = MojangsonParser.class;
			Method parse = parser.getDeclaredMethod("parse", String.class);
			NBTTagCompound compound = (NBTTagCompound) parse.invoke(null, s);
			Method load = entityClass.getDeclaredMethod("load", NBTTagCompound.class);
			load.invoke(e, compound);
			Class<?> vectorClass = Vec3D.class;
			Method x = vectorClass.getDeclaredMethod("getX");
			Method y = vectorClass.getDeclaredMethod("getY");
			Method z = vectorClass.getDeclaredMethod("getZ");
			Method setPosition = entityClass.getDeclaredMethod("setPosition", double.class, double.class, double.class);
			setPosition.invoke(e, (double) x.invoke(vector), (double) y.invoke(vector), (double) z.invoke(vector));
			int[] rot = (int[]) compound.getClass().getDeclaredMethod("getIntArray", String.class).invoke(compound, "Rotation");
			if (rot.length == 2) {
				entityClass.getDeclaredMethod("setXRot", int.class).invoke(e, rot[0]);
				entityClass.getDeclaredMethod("setYRot", int.class).invoke(e, rot[1]);
			}
			return (Entity) entityClass.getDeclaredMethod("getBukkitEntity").invoke(e);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return entity;
	}
}
