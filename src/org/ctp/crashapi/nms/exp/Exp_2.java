package org.ctp.crashapi.nms.exp;

import java.lang.reflect.Method;

import org.bukkit.Location;
import org.ctp.crashapi.nms.NMS;

import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.EntityExperienceOrb;
import net.minecraft.world.phys.Vec3D;

public class Exp_2 extends NMS {
	
	@SuppressWarnings("resource")
	public static int dropExp(Location loc, int i) {
		Vec3D l = new Vec3D(loc.getX(), loc.getY(), loc.getZ());
		WorldServer server = getCraftBukkitWorld(loc.getWorld());
		try {
			Class<?> clazz = EntityExperienceOrb.class;
			Method m = clazz.getDeclaredMethod("a", WorldServer.class, Vec3D.class, int.class);
			m.invoke(null, server, l, i);
		} catch (Exception ex) {
			ex.printStackTrace();
			return i;
		}
		return 0;
	}

}
