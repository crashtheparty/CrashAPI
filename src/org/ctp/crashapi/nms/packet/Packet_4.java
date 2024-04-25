package org.ctp.crashapi.nms.packet;

import java.lang.reflect.Method;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.ctp.crashapi.nms.NMS;

public class Packet_4 extends NMS {

	public static int addParticle(Block block, int stage) {
		int rand = Math.abs(new Random().nextInt());
		for(Player player: Bukkit.getOnlinePlayers()) {
			if (stage > 9) stage = 0;
			try {
				Class<?> c = Class.forName("org.bukkit.craftbukkit." + getVersion() + ".entity.CraftPlayer");
				Method m = c.getDeclaredMethod("sendBlockDamage", Location.class, float.class, int.class);
				m.invoke(c.cast(player), block.getLocation(), (float) stage / 9, rand);
				c.cast(player);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rand;
	}

	public static int updateParticle(Block block, int stage, int id) {
		for(Player player: Bukkit.getOnlinePlayers()) {
			if (stage > 9) stage = 0;
			try {
				Class<?> c = Class.forName("org.bukkit.craftbukkit." + getVersion() + ".entity.CraftPlayer");
				Method m = c.getDeclaredMethod("sendBlockDamage", Location.class, float.class, int.class);
				m.invoke(c.cast(player), block.getLocation(), (float) stage / 9, id);
				c.cast(player);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return id;
	}
}
