package org.ctp.crashapi.nms.world;

import java.lang.reflect.Method;

import org.bukkit.block.Block;
import org.ctp.crashapi.nms.NMS;

import net.minecraft.core.BlockPosition;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.DifficultyDamageScaler;
import net.minecraft.world.level.World;

public class World_2 extends NMS {
	
	@SuppressWarnings("resource")
	public static float[] getRegionalDifficulty(Block block) {
		WorldServer server = getCraftBukkitWorld(block.getWorld());
		float b = 0;
		float d = 0;
		try {
			Class<?> c = World.class;
			Method m = c.getDeclaredMethod("d_", BlockPosition.class);
			Object o = m.invoke(server, new BlockPosition(block.getX(), block.getY(), block.getZ()));
			if (o instanceof DifficultyDamageScaler) {
				b = ((DifficultyDamageScaler) o).b();
				d = ((DifficultyDamageScaler) o).d();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new float[] { b, d };
	}
}
