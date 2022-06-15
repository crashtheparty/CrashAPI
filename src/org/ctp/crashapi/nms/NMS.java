package org.ctp.crashapi.nms;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.ctp.crashapi.CrashAPI;

import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.inventory.ContainerAnvil;
import net.minecraft.world.level.block.state.IBlockData;
import net.minecraft.world.phys.Vec3D;

public class NMS {
	
	public static int getVersionNumber() {
		return CrashAPI.getPlugin().getBukkitVersion().getVersionNumber();
	}

	public static String getVersion() {
		return CrashAPI.getPlugin().getBukkitVersion().getAPIVersion();
	}

	public static int[] getVersionNumbers() {
		return CrashAPI.getPlugin().getBukkitVersion().getVersionNumbers();
	}

	public static boolean isSimilarOrAbove(int[] version, int i, int j, int k) {
		return CrashAPI.getPlugin().getBukkitVersion().isSimilarOrAbove(version, i, j, k);
	}
	
	public static net.minecraft.world.level.block.Block getBlock(Block b){
		try {
			Class<?> c = Class.forName("org.bukkit.craftbukkit." + getVersion() + ".block.CraftBlock");
			Method m = c.getDeclaredMethod("getNMS");
			Object o = m.invoke(b);
			if (o instanceof IBlockData) return ((IBlockData) o).b();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static net.minecraft.world.phys.Vec3D getLocation(Location l){
		return new Vec3D(l.getX(), l.getY(), l.getZ());
	}

	public static net.minecraft.world.item.ItemStack asNMSCopy(ItemStack item) {
		try {
			Class<?> c = Class.forName("org.bukkit.craftbukkit." + getVersion() + ".inventory.CraftItemStack");
			Method m = c.getDeclaredMethod("asNMSCopy", ItemStack.class);
			Object o = m.invoke(c, item);
			if (o instanceof net.minecraft.world.item.ItemStack) return (net.minecraft.world.item.ItemStack) o;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static ItemStack asBukkitCopy(net.minecraft.world.item.ItemStack item) {
		try {
			Class<?> c = Class.forName("org.bukkit.craftbukkit." + getVersion() + ".inventory.CraftItemStack");
			Method m = c.getDeclaredMethod("asBukkitCopy", net.minecraft.world.item.ItemStack.class);
			Object o = m.invoke(c, item);
			if (o instanceof ItemStack) return (ItemStack) o;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static Entity getCraftBukkitEntity(org.bukkit.entity.Entity entity) {
		try {
			Class<?> c = Class.forName("org.bukkit.craftbukkit." + getVersion() + ".entity.CraftEntity");
			Method m = c.getDeclaredMethod("getHandle");
			Object o = m.invoke(entity);
			if (o instanceof Entity) return (Entity) o;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static WorldServer getCraftBukkitWorld(org.bukkit.World world) {
		try {
			Class<?> c = Class.forName("org.bukkit.craftbukkit." + getVersion() + ".CraftWorld");
			Method m = c.getDeclaredMethod("getHandle");
			Object o = m.invoke(world);
			if (o instanceof WorldServer) return (WorldServer) o;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static Inventory getInventory(ContainerAnvil container) {
		// Set the items to the items from the inventory given
		try {
			Class<?> c = Class.forName("org.bukkit.craftbukkit." + getVersion() + ".inventory.CraftInventoryView");
			Method m = ContainerAnvil.class.getDeclaredMethod("getBukkitView");
			Object o = m.invoke(container);
			Method m2 = c.getDeclaredMethod("getTopInventory");
			return (Inventory) m2.invoke(c.cast(o));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static void invokeAccessible(Method m, Object clazz, Object...args) {
		m.setAccessible(true);
		try {
			m.invoke(clazz, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public static Object returnAccessible(Method m, Object clazz, Object...args) {
		m.setAccessible(true);
		try {
			return m.invoke(clazz, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
}
