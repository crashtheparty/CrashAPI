package org.ctp.crashapi.nms.item;

import java.lang.reflect.Method;
import java.util.Locale;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.ctp.crashapi.data.BlockSound;
import org.ctp.crashapi.nms.NMS;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.sounds.SoundEffect;
import net.minecraft.world.level.block.SoundEffectType;
import net.minecraft.world.level.block.state.IBlockData;

public class Item_1 extends NMS {

	public static ItemStack addNBTData(ItemStack item, String key, int value) {
		net.minecraft.world.item.ItemStack i = asNMSCopy(item);
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

		return asBukkitCopy(i);
	}

	public static int getNBTData(ItemStack item, String key) {
		net.minecraft.world.item.ItemStack i = asNMSCopy(item);
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

	public static BlockSound getSound(Block block, String key) {
		net.minecraft.world.level.block.Block b = getBlock(block);
		try {
			Class<?> clazz = net.minecraft.world.level.block.Block.class;
			Method m1 = clazz.getDeclaredMethod("m", IBlockData.class);
			Method m2 = clazz.getDeclaredMethod("n");
			Object o = m1.invoke(b, (IBlockData) m2.invoke(b));
			if (o instanceof SoundEffectType) {
				SoundEffectType type = (SoundEffectType) o;
				SoundEffect effect = null;
				switch(key.toLowerCase(Locale.ROOT)) {
					case "break":
						effect = type.c();
						break;
					case "step":
						effect = type.d();
						break;
					case "place":
						effect = type.e();
						break;
					case "hit":
						effect = type.f();
						break;
					case "fall":
						effect = type.g();
						break;
					default:
						effect = type.c();
				}
				Class<?> eff = SoundEffectType.class;

				return new BlockSound(effect.a().a(), eff.getDeclaredField("ay").getFloat(type), eff.getDeclaredField("az").getFloat(type));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
