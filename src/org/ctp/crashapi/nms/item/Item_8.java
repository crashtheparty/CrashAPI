package org.ctp.crashapi.nms.item;

import java.lang.reflect.Method;
import java.util.Locale;

import org.bukkit.block.Block;
import org.ctp.crashapi.data.BlockSound;
import org.ctp.crashapi.nms.NMS;

import net.minecraft.sounds.SoundEffect;
import net.minecraft.world.level.block.SoundEffectType;
import net.minecraft.world.level.block.state.IBlockData;

public class Item_8 extends NMS {

	public static BlockSound getSound(Block block, String key) {
		net.minecraft.world.level.block.Block b = getBlock(block);
		try {
			Class<?> blockClazz = net.minecraft.world.level.block.state.BlockBase.class;
			Class<?> blockStateClazz = net.minecraft.world.level.block.state.BlockBase.class;
			Method m1 = blockStateClazz.getDeclaredMethod("g_", IBlockData.class);
			Method m2 = blockClazz.getDeclaredMethod("o");
			Object o = m1.invoke(b, (IBlockData) m2.invoke(b));
			if (o instanceof SoundEffectType) {
				SoundEffectType type = (SoundEffectType) o;
				SoundEffect effect = null;
				switch (key.toLowerCase(Locale.ROOT)) {
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

				return new BlockSound(effect.a().a(), eff.getDeclaredField("bg").getFloat(type), eff.getDeclaredField("bh").getFloat(type));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
