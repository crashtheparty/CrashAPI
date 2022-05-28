package org.ctp.crashapi.nms.exp;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;

import net.minecraft.server.v1_16_R3.EntityExperienceOrb;
import net.minecraft.server.v1_16_R3.Vec3D;
import net.minecraft.server.v1_16_R3.WorldServer;

public class Exp_v1_16_R3 {

	@SuppressWarnings("resource")
	public static int dropExp(Location loc, int i) {
		Vec3D l = new Vec3D(loc.getX(), loc.getY(), loc.getZ());
		WorldServer server = ((CraftWorld) loc.getWorld()).getHandle();
		while (i > 0) {
			int k = EntityExperienceOrb.getOrbValue(i);
			
	        i -= k;
	        server.addEntity(new EntityExperienceOrb(server, l.x, l.y, l.z, k));
		}
		return i;
	}
	
}
