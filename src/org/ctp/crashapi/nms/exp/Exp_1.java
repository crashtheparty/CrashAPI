package org.ctp.crashapi.nms.exp;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.bukkit.Location;
import org.ctp.crashapi.nms.NMS;

import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityExperienceOrb;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.phys.Vec3D;

public class Exp_1 extends NMS {

	@SuppressWarnings("resource")
	public static int dropExp(Location loc, int i) {
		Vec3D l = new Vec3D(loc.getX(), loc.getY(), loc.getZ());
		WorldServer server = getCraftBukkitWorld(loc.getWorld());
		while (i > 0) {
			int k = 0;
			try {
				Class<?> clazz = EntityExperienceOrb.class;
				Method m = clazz.getMethod("getOrbValue", int.class);
				Object o = m.invoke(null, i);
				if (o instanceof Integer) k = ((Integer) o).intValue();
			} catch (Exception ex) {
				ex.printStackTrace();
				break;
			}
			i -= k;
			try {
				Class<?> entityClass = EntityExperienceOrb.class;
				Constructor<?> c = entityClass.getDeclaredConstructor(EntityTypes.class, double.class, double.class, double.class, int.class);
				Method getX = l.getClass().getDeclaredMethod("getX");
				Method getY = l.getClass().getDeclaredMethod("getY");
				Method getZ = l.getClass().getDeclaredMethod("getZ");

				Object o = c.newInstance(EntityTypes.A, getX.invoke(l), getY.invoke(l), getZ.invoke(l), k);

				Class<?> clazz = server.getClass();
				Method m = clazz.getDeclaredMethod("addEntity", Entity.class);
				m.invoke(server, o);
			} catch (Exception ex) {
				ex.printStackTrace();
				i += k;
				break;
			}
		}
		return i;
	}

}
