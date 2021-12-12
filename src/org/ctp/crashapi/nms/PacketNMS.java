package org.ctp.crashapi.nms;

import org.bukkit.block.Block;
import org.ctp.crashapi.CrashAPI;
import org.ctp.crashapi.nms.packet.Packet_v1_16_R3;
import org.ctp.crashapi.nms.packet.Packet_v1_17_R1;
import org.ctp.crashapi.nms.packet.Packet_v1_18_R1;

public class PacketNMS {

	public static int addParticle(Block block, int stage) {
		switch (CrashAPI.getPlugin().getBukkitVersion().getVersionNumber()) {
			case 16:
				return Packet_v1_16_R3.addParticle(block, stage);
			case 18:
				return Packet_v1_17_R1.addParticle(block, stage);
			case 19:
			case 20:
				return Packet_v1_18_R1.addParticle(block, stage);
		}
		return 0;
	}

	public static int updateParticle(Block block, int stage, int id) {
		switch (CrashAPI.getPlugin().getBukkitVersion().getVersionNumber()) {
			case 16:
				return Packet_v1_16_R3.updateParticle(block, stage, id);
			case 18:
				return Packet_v1_17_R1.updateParticle(block, stage, id);
			case 19:
			case 20:
				return Packet_v1_18_R1.updateParticle(block, stage, id);
		}
		return 0;
	}
}
