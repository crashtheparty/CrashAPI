package org.ctp.crashapi.nms.item;

import java.lang.reflect.Method;

import org.bukkit.inventory.ItemStack;
import org.ctp.crashapi.nms.NMS;

import net.minecraft.nbt.NBTTagCompound;

public class Item_2 extends NMS {

	public static ItemStack addNBTData(ItemStack item, String key, int value) {
		net.minecraft.world.item.ItemStack i = asNMSCopy(item);
		try {
			Class<?> c = net.minecraft.world.item.ItemStack.class;
			Method get = c.getDeclaredMethod("t");
			Object o = get.invoke(i);
			if (o instanceof NBTTagCompound) {
				NBTTagCompound compound = (NBTTagCompound) o;
				Method set = NBTTagCompound.class.getDeclaredMethod("a", String.class, int.class);
				set.invoke(compound, key, value);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return asBukkitCopy(i);
	}

	public static int getNBTData(ItemStack item, String key) {
		net.minecraft.world.item.ItemStack i = asNMSCopy(item);
		try {
			Class<?> c = net.minecraft.world.item.ItemStack.class;
			Method get = c.getDeclaredMethod("t");
			Object o = get.invoke(i);
			if (o instanceof NBTTagCompound) {
				NBTTagCompound compound = (NBTTagCompound) o;
				Method getInt = NBTTagCompound.class.getDeclaredMethod("h", String.class);
				return (int) getInt.invoke(compound, key);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}
}
