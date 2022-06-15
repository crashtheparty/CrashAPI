package org.ctp.crashapi.nms.mob;

import java.lang.reflect.Method;

import org.bukkit.entity.Entity;
import org.ctp.crashapi.nms.NMS;

import net.minecraft.nbt.MojangsonParser;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.phys.Vec3D;

public class Mob_3 extends NMS {

	public static Entity setNBTData(Entity entity, String s) {
		net.minecraft.world.entity.Entity e = getCraftBukkitEntity(entity);
		try {
			Class<?> entityClass = net.minecraft.world.entity.Entity.class;
			Method posVector = entityClass.getDeclaredMethod("cZ");
			Vec3D vector = (Vec3D) posVector.invoke(e);
			Class<?> parser = MojangsonParser.class;
			Method parse = parser.getDeclaredMethod("a", String.class);
			NBTTagCompound compound = (NBTTagCompound) parse.invoke(null, s);
			Method load = entityClass.getDeclaredMethod("g", NBTTagCompound.class);
			load.invoke(e, compound);
			Class<?> vectorClass = Vec3D.class;
			Method x = vectorClass.getDeclaredMethod("a");
			Method y = vectorClass.getDeclaredMethod("b");
			Method z = vectorClass.getDeclaredMethod("c");
			Method setPosition = entityClass.getDeclaredMethod("b", double.class, double.class, double.class);
			setPosition.invoke(e, (double) x.invoke(vector), (double) y.invoke(vector), (double) z.invoke(vector));
			int[] rot = (int[]) compound.getClass().getDeclaredMethod("n", String.class).invoke(compound, "Rotation");
			if (rot.length == 2) entityClass.getDeclaredMethod("a", int.class).invoke(e, rot[0], rot[1]);
			return (Entity) entityClass.getDeclaredMethod("getBukkitEntity").invoke(e);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return entity;
	}
}
