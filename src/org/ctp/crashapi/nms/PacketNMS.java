package org.ctp.crashapi.nms;

import org.bukkit.block.Block;
import org.ctp.crashapi.nms.packet.*;

public class PacketNMS extends NMS {

	public static int addParticle(Block block, int stage) {
		switch (getVersionNumber()) {
			case 16:
				return Packet_v1_16_R3.addParticle(block, stage);
			default:
				if (isSimilarOrAbove(getVersionNumbers(), 1, 20, 0)) return Packet_3.addParticle(block, stage);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 18, 0)) return Packet_2.addParticle(block, stage);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 17, 0)) return Packet_1.addParticle(block, stage);
		}
		return 0;
	}

	public static int updateParticle(Block block, int stage, int id) {
		switch (getVersionNumber()) {
			case 16:
				return Packet_v1_16_R3.updateParticle(block, stage, id);
			default:
				if (isSimilarOrAbove(getVersionNumbers(), 1, 20, 0)) return Packet_3.updateParticle(block, stage, id);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 18, 0)) return Packet_2.updateParticle(block, stage, id);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 17, 0)) return Packet_1.updateParticle(block, stage, id);
		}
		return 0;
	}
}
