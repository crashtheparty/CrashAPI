package org.ctp.crashapi.nms;

import org.ctp.crashapi.CrashAPI;

public class ServerNMS {

	public static long getCurrentTick() {
		long tick = (int) System.currentTimeMillis() / 50;
		switch (CrashAPI.getPlugin().getBukkitVersion().getVersionNumber()) {
			case 1:
				tick = net.minecraft.server.v1_13_R1.MinecraftServer.currentTick;
				break;
			case 2:
			case 3:
				tick = net.minecraft.server.v1_13_R2.MinecraftServer.currentTick;
				break;
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
				tick = net.minecraft.server.v1_14_R1.MinecraftServer.currentTick;
				break;
			case 9:
			case 10:
			case 11:
				tick = net.minecraft.server.v1_15_R1.MinecraftServer.currentTick;
				break;
			case 12:
				tick = net.minecraft.server.v1_16_R1.MinecraftServer.currentTick;
				break;
			case 13:
			case 14:
				tick = net.minecraft.server.v1_16_R2.MinecraftServer.currentTick;
				break;
			case 15:
				tick = net.minecraft.server.v1_16_R3.MinecraftServer.currentTick;
				break;
		}
		return tick - Integer.MIN_VALUE;
	}

	public static boolean hasOverrun(long oldTick) {
		return Math.abs(Math.abs(oldTick) - Math.abs(getCurrentTick())) > Integer.MAX_VALUE;
	}

}
