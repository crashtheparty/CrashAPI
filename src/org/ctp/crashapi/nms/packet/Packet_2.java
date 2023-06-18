package org.ctp.crashapi.nms.packet;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.ctp.crashapi.CrashAPI;
import org.ctp.crashapi.nms.NMS;
import org.ctp.crashapi.utils.ChatUtils;

import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayOutBlockBreakAnimation;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.network.PlayerConnection;

public class Packet_2 extends NMS {

	public static int addParticle(Block block, int stage) {
		int rand = Math.abs(new Random().nextInt());
		PacketPlayOutBlockBreakAnimation packet = new PacketPlayOutBlockBreakAnimation(rand, new BlockPosition(block.getX(), block.getY(), block.getZ()), stage);
		for(Player player: Bukkit.getOnlinePlayers()) {
			PlayerConnection pc = null;
			EntityPlayer p = ((EntityPlayer) getCraftBukkitEntity(player));

			try {
				Class<?> clazz = EntityPlayer.class;
				Field f = clazz.getDeclaredField("b");
				if (f.get(p) instanceof PlayerConnection) pc = (PlayerConnection) f.get(p);
				else
					ChatUtils.getUtils(CrashAPI.getPlugin()).sendInfo("Issue with Packet NMS - Player Connection not found");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			Class<?> c = pc.getClass();
			Method m;
			try {
				m = c.getDeclaredMethod("a", Packet.class);
				m.invoke(pc, packet);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return rand;
	}

	public static int updateParticle(Block block, int stage, int id) {
		PacketPlayOutBlockBreakAnimation packet = new PacketPlayOutBlockBreakAnimation(id, new BlockPosition(block.getX(), block.getY(), block.getZ()), stage);
		for(Player player: Bukkit.getOnlinePlayers()) {
			PlayerConnection pc = null;
			EntityPlayer p = ((EntityPlayer) getCraftBukkitEntity(player));

			try {
				Class<?> clazz = EntityPlayer.class;
				Field f = clazz.getDeclaredField("b");
				if (f.get(p) instanceof PlayerConnection) pc = (PlayerConnection) f.get(p);
				else
					ChatUtils.getUtils(CrashAPI.getPlugin()).sendInfo("Issue with Packet NMS - Player Connection not found");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			Class<?> c = pc.getClass();
			Method m;
			try {
				m = c.getDeclaredMethod("a", Packet.class);
				m.invoke(pc, packet);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return id;
	}
}
