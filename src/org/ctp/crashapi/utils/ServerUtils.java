package org.ctp.crashapi.utils;

public class ServerUtils {
	
	// same as CraftBukkit installation
	public static long getCurrentTick() {
		return (int) System.currentTimeMillis() / 50;
	}
	
	// For EnchantmentSolution and enchantments that have cooldowns
	public static boolean hasOverrunCooldown(long oldTick) {
		return Math.abs(Math.abs(oldTick) - Math.abs(getCurrentTick())) > Integer.MAX_VALUE;
	}
}
