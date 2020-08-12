package org.ctp.crashapi.nms.item;

import org.bukkit.craftbukkit.v1_16_R2.entity.CraftTrident;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Trident;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_16_R2.EntityThrownTrident;

public class Item_v1_16_R2 {

	public static ItemStack getTrident(Trident trident) {
		EntityThrownTrident t = ((CraftTrident) trident).getHandle();
		net.minecraft.server.v1_16_R2.ItemStack a = t.trident;
		return CraftItemStack.asBukkitCopy(a);
	}
}
