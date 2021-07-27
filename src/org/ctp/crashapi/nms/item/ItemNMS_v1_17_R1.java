package org.ctp.crashapi.nms.item;

import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.nbt.NBTTagCompound;

public class ItemNMS_v1_17_R1 {

	public static ItemStack addNBTData(ItemStack item, String key, int value) {
		net.minecraft.world.item.ItemStack i = CraftItemStack.asNMSCopy(item);
		NBTTagCompound compound = i.getOrCreateTag();
		compound.setInt(key, value);
		return CraftItemStack.asBukkitCopy(i);
	}

	public static int getNBTData(ItemStack item, String key) {
		net.minecraft.world.item.ItemStack i = CraftItemStack.asNMSCopy(item);
		NBTTagCompound compound = i.getOrCreateTag();
		return compound.getInt(key);
	}

}
