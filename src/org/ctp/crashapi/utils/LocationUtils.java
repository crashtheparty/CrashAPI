package org.ctp.crashapi.utils;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.ctp.crashapi.data.inventory.InventoryData;
import org.ctp.crashapi.data.items.MatData;

public class LocationUtils {

	public static boolean isLocationDifferent(Location locOne, Location locTwo, boolean includeY) {
		return locOne.getX() != locTwo.getX() || locOne.getZ() != locTwo.getZ() || includeY && locTwo.getY() != locOne.getY();
	}

	public static boolean isLocationSame(Location locOne, Location locTwo, boolean includeY) {
		return locOne.getX() == locTwo.getX() && locOne.getZ() == locTwo.getZ() && (!includeY || locTwo.getY() == locOne.getY());
	}

	public static boolean hasBlockAbove(Player player) {
		for(int y = player.getLocation().getBlockY(); y < player.getWorld().getMaxHeight(); y++) {
			Location loc = player.getLocation().clone().add(0, y, 0);
			if (!MatData.isAir(loc.getBlock().getType())) return true;
		}
		return false;
	}

	public static boolean hasBlockBelow(Location location) {
		for(int y = location.getBlockY(); y >= location.getWorld().getMinHeight(); y--) {
			Location loc = location.clone();
			loc.setY(y);
			if (!MatData.isAir(loc.getBlock().getType())) return true;
		}
		return false;
	}

	public static boolean getIntersecting(Location loc1a, Location loc1b, Location loc2a, Location loc2b) {
		return getIntersecting(loc1a, loc1b, loc2a, loc2b, false);
	}

	public static boolean getIntersecting(Location loc1a, Location loc1b, Location loc2a, Location loc2b, boolean ignoreY) {
		if (loc1a.getWorld() != loc2a.getWorld()) return false;

		if (!intersectsDimension(loc1a.getBlockX(), loc1b.getBlockX(), loc2a.getBlockX(), loc2b.getBlockX())) return false;

		if (!ignoreY && !intersectsDimension(loc1a.getBlockY(), loc1b.getBlockY(), loc2a.getBlockY(), loc2b.getBlockY())) return false;

		if (!intersectsDimension(loc1a.getBlockZ(), loc1b.getBlockZ(), loc2a.getBlockZ(), loc2b.getBlockZ())) return false;
		return true;
	}

	private static boolean intersectsDimension(int aMin, int aMax, int bMin, int bMax) {
		int aOne = aMin > aMax ? aMax : aMin;
		int aTwo = aMin > aMax ? aMin : aMax;
		int bOne = aMin > aMax ? bMax : bMin;
		int bTwo = aMin > aMax ? bMin : bMax;
		return aOne <= bTwo && aTwo >= bOne;
	}

	public static Location stringToLocation(String string) {
		Location loc = null;
		try {
			String[] values = string.split(" @ ");
			if (values.length == 4) loc = new Location(Bukkit.getWorld(values[3]), Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]));
		} catch (Exception ex) {

		}
		return loc;
	}

	public static String locationToString(Location loc) {
		if (loc == null) return "";
		return loc.getBlockX() + " @ " + loc.getBlockY() + " @ " + loc.getBlockZ() + " @ " + loc.getWorld().getName();
	}

	public static Location offset(Location location) {
		return location.clone().add(0.5, 0.5, 0.5);
	}

	public static void dropExperience(Location loc, int amount, boolean offset) {
		Location location = offset ? offset(loc) : loc.clone();
		if (amount > 0) location.getWorld().spawn(location, ExperienceOrb.class).setExperience(amount);
	}

	public static void checkAnvilBreak(Player player, Block block, InventoryData anvil, boolean damageAnvil) {
		if (block == null) {
			player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
			return;
		}
		if (!damageAnvil || player.getGameMode().equals(GameMode.CREATIVE)) {
			block.getWorld().playSound(block.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
			return;
		}
		double chance = .12;
		double roll = Math.random();
		if (chance > roll) {
			Material material = Material.AIR;
			switch (block.getType().name()) {
				case "ANVIL":
					material = Material.CHIPPED_ANVIL;
					break;
				case "CHIPPED_ANVIL":
					material = Material.DAMAGED_ANVIL;
					break;
				default:

			}
			if (material == Material.AIR) {
				block.getWorld().playSound(block.getLocation(), Sound.BLOCK_ANVIL_DESTROY, 1, 1);
				if (anvil != null) anvil.close(false);
				block.setType(material);
			} else {
				block.getWorld().playSound(block.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1, 1);
				block.getWorld().playSound(block.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
				BlockFace facing = ((Directional) block.getBlockData()).getFacing();
				block.setType(material);
				Directional d = (Directional) block.getBlockData();
				d.setFacing(facing);
				block.setBlockData(d);
			}
		} else
			block.getWorld().playSound(block.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
	}

	public static boolean chunkShouldLoad(Location loc) {
		if (loc.getWorld().isChunkForceLoaded(loc.getBlockX() / 16, loc.getBlockZ() / 16)) return true;
		for(Player player: Bukkit.getOnlinePlayers()) {
			int blocks = Bukkit.getViewDistance() * 16;
			Location locOne = new Location(loc.getWorld(), loc.getBlockX() - blocks, 0, loc.getBlockZ() - blocks);
			Location locTwo = new Location(loc.getWorld(), loc.getBlockX() + blocks, 0, loc.getBlockZ() + blocks);
			if (getIntersecting(locOne, locTwo, player.getLocation(), player.getLocation(), true)) return true;
		}
		return false;
	}
	
	public static boolean isOnGround(Player player) {
		return player.getLocation().getY() % 1 == 0 && player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isSolid();
	}
}
