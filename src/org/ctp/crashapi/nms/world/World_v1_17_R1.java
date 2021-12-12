package org.ctp.crashapi.nms.world;

import java.lang.reflect.Method;

import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

import net.minecraft.core.BlockPosition;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.DifficultyDamageScaler;
import net.minecraft.world.level.World;

public class World_v1_17_R1 {
	@SuppressWarnings("resource")
	public static float[] getRegionalDifficulty(Block block) {
		WorldServer server = ((CraftWorld) block.getWorld()).getHandle();
		float b = 0;
		float d = 0;
		try {
			Class<?> c = World.class;
			Method m = c.getDeclaredMethod("getDamageScaler", BlockPosition.class);
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
