package org.ctp.crashapi.nms;

import org.bukkit.block.Block;
import org.ctp.crashapi.CrashAPI;
import org.ctp.crashapi.nms.world.World_v1_16_R3;
import org.ctp.crashapi.nms.world.World_v1_17_R1;
import org.ctp.crashapi.nms.world.World_v1_18_R1;

public class WorldNMS {

	public static float[] getRegionalDifficulty(Block block) {
		switch (CrashAPI.getPlugin().getBukkitVersion().getVersionNumber()) {
			case 16:
				return World_v1_16_R3.getRegionalDifficulty(block);
			case 18:
				return World_v1_17_R1.getRegionalDifficulty(block);
			case 19:
			case 20:
				return World_v1_18_R1.getRegionalDifficulty(block);
		}
		return new float[2];
	}
}
