package org.ctp.crashapi.item;

import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.ctp.crashapi.CrashAPI;
import org.ctp.crashapi.nms.ItemNMS;
import org.ctp.crashapi.utils.ChatUtils;

public class BlockSound {

	private final String sound;
	private final float volume, pitch;

	public BlockSound(String sound, float volume, float pitch) {
		this.sound = sound;
		this.volume = volume;
		this.pitch = pitch;
	}

	public Sound getSound() {
		for (Sound s : Sound.values())
			if (s.getKey().getKey().equalsIgnoreCase(sound)) return s;
		ChatUtils.getUtils(CrashAPI.getPlugin()).sendInfo("Failed to get break sound.");
		return Sound.BLOCK_STONE_BREAK;
	}

	public String getSoundString() {
		return sound;
	}

	public float getVolume() {
		return volume;
	}

	public float getPitch() {
		return pitch;
	}

	public static BlockSound getSound(Block b, String s) {
		return ItemNMS.getBlockSound(b, s);
	}
}
