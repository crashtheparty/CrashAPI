package org.ctp.crashapi.nms.world;

import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

import net.minecraft.core.BlockPosition;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.DifficultyDamageScaler;

public class World_v1_18_R1 {
	@SuppressWarnings("resource")
	public static float[] getRegionalDifficulty(Block block) {
		WorldServer server = ((CraftWorld) block.getWorld()).getHandle();
		DifficultyDamageScaler scalar = server.d_(new BlockPosition(block.getX(), block.getY(), block.getZ()));
		return new float[] { scalar.b(), scalar.d() };
	}
}
