package org.ctp.crashapi.nms.mob;

import org.bukkit.craftbukkit.v1_15_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.ctp.crashapi.CrashAPI;

import net.minecraft.server.v1_15_R1.MojangsonParser;
import net.minecraft.server.v1_15_R1.NBTTagCompound;
import net.minecraft.server.v1_15_R1.Vec3D;

public class Mob_v1_15_R1 {

	public static String getNBTData(Entity entity, boolean husbandry) {
		net.minecraft.server.v1_15_R1.Entity e = ((CraftEntity) entity).getHandle();
		NBTTagCompound compound = new NBTTagCompound();
		compound = e.save(compound);
		compound.remove("UUID");
		if (husbandry) {
			compound.remove("DeathTime");
			compound.remove("Health");
			compound.remove("SaddleItem");
		}
		return compound.asString();
	}

	public static Entity setNBTData(Entity entity, String s) {
		net.minecraft.server.v1_15_R1.Entity e = ((CraftEntity) entity).getHandle();
		Vec3D vector = e.getPositionVector();
		NBTTagCompound compound = new NBTTagCompound();
		CrashAPI.getPlugin().getChat().sendInfo(s);
		try {
			compound = MojangsonParser.parse(s);
			e.f(compound);
			e.setPosition(vector.getX(), vector.getY(), vector.getZ());
			int[] rot = compound.getIntArray("Rotation");
			if (rot.length == 2) e.setPositionRotation(e.getChunkCoordinates(), rot[0], rot[1]);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return e.getBukkitEntity();
	}
}
