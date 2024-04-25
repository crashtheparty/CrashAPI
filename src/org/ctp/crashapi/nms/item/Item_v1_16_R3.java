package org.ctp.crashapi.nms.item;

import java.util.Locale;

import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R3.block.CraftBlock;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.ctp.crashapi.data.BlockSound;

import net.minecraft.server.v1_16_R3.NBTTagCompound;
import net.minecraft.server.v1_16_R3.SoundEffect;
import net.minecraft.server.v1_16_R3.SoundEffectType;

public class Item_v1_16_R3 {

	public static ItemStack addNBTData(ItemStack item, String key, int value) {
		net.minecraft.server.v1_16_R3.ItemStack i = CraftItemStack.asNMSCopy(item);
		NBTTagCompound compound = i.getOrCreateTag();
		compound.setInt(key, value);
		return CraftItemStack.asBukkitCopy(i);
	}

	public static int getNBTData(ItemStack item, String key) {
		net.minecraft.server.v1_16_R3.ItemStack i = CraftItemStack.asNMSCopy(item);
		NBTTagCompound compound = i.getOrCreateTag();
		return compound.getInt(key);
	}

	public static BlockSound getSound(Block block, String key) {
		net.minecraft.server.v1_16_R3.Block b = ((CraftBlock) block).getNMS().getBlock();
		SoundEffectType type = b.getStepSound(b.getBlockData());
		SoundEffect effect = null;
		switch(key.toLowerCase(Locale.ROOT)) {
			case "break":
				effect = type.breakSound;
				break;
			case "step":
				effect = type.getStepSound();
				break;
			case "place":
				effect = type.getPlaceSound();
				break;
			case "hit":
				effect = type.hitSound;
				break;
			case "fall":
				effect = type.getFallSound();
				break;
			default:
				effect = type.breakSound;
		}
		
		return new BlockSound(effect.toString(), type.volume, type.pitch);
	}

}
