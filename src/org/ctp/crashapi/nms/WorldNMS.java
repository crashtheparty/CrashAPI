package org.ctp.crashapi.nms;

import org.bukkit.block.Block;
import org.ctp.crashapi.nms.world.World_1;
import org.ctp.crashapi.nms.world.World_2;
import org.ctp.crashapi.nms.world.World_v1_16_R3;

public class WorldNMS extends NMS {

	public static float[] getRegionalDifficulty(Block block) {
		switch (getVersionNumber()) {
			case 16:
				return World_v1_16_R3.getRegionalDifficulty(block);
			default:
				if (isSimilarOrAbove(getVersionNumbers(), 1, 18, 0)) return World_2.getRegionalDifficulty(block);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 17, 0)) return World_1.getRegionalDifficulty(block);
		}
		return new float[2];
	}
}
