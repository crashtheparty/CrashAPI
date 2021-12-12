package org.ctp.crashapi.nms.item;

import java.lang.reflect.Method;

import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.nbt.NBTTagCompound;

public class ItemNMS_v1_17_R1 {

	public static ItemStack addNBTData(ItemStack item, String key, int value) {
		net.minecraft.world.item.ItemStack i = CraftItemStack.asNMSCopy(item);
		try {
			Class<?> c = net.minecraft.world.item.ItemStack.class;
			Method get = c.getDeclaredMethod("getOrCreateTag");
			Object o = get.invoke(i);
			if (o instanceof NBTTagCompound) {
				NBTTagCompound compound = (NBTTagCompound) o;
				Method set = NBTTagCompound.class.getDeclaredMethod("setInt", String.class, int.class);
				set.invoke(compound, key, value);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CraftItemStack.asBukkitCopy(i);
	}

	public static int getNBTData(ItemStack item, String key) {
		net.minecraft.world.item.ItemStack i = CraftItemStack.asNMSCopy(item);
		try {
			Class<?> c = net.minecraft.world.item.ItemStack.class;
			Method get = c.getDeclaredMethod("getOrCreateTag");
			Object o = get.invoke(i);
			if (o instanceof NBTTagCompound) {
				NBTTagCompound compound = (NBTTagCompound) o;
				Method getInt = NBTTagCompound.class.getDeclaredMethod("getInt", String.class);
				return (int) getInt.invoke(compound, key);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

}
