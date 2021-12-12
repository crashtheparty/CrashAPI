package org.ctp.crashapi.nms.mob;

import org.bukkit.craftbukkit.v1_18_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;

import net.minecraft.nbt.MojangsonParser;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.phys.Vec3D;

public class Mob_v1_18_R1 {

	public static String getNBTData(Entity entity, boolean husbandry) {
		net.minecraft.world.entity.Entity e = ((CraftEntity) entity).getHandle();
		NBTTagCompound compound = new NBTTagCompound();
		compound = e.f(compound);
		compound.r("UUID");
		if (husbandry) {
			compound.r("DeathTime");
			compound.r("Health");
			compound.r("SaddleItem");
		}
		return compound.toString();
	}

	public static Entity setNBTData(Entity entity, String s) {
		net.minecraft.world.entity.Entity e = ((CraftEntity) entity).getHandle();
		Vec3D vector = e.cV();
		NBTTagCompound compound = new NBTTagCompound();
		try {
			compound = MojangsonParser.a(s);
			e.g(compound);
			e.b(vector.a(), vector.b(), vector.c());
			int[] rot = compound.n("Rotation");
			if (rot.length == 2) e.a(rot[0], rot[1]);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return e.getBukkitEntity();
	}
}
