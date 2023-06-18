package org.ctp.crashapi.nms.item;

import java.lang.reflect.Method;
import java.util.Locale;

import org.bukkit.block.Block;
import org.ctp.crashapi.item.BlockSound;
import org.ctp.crashapi.nms.NMS;

import net.minecraft.sounds.SoundEffect;
import net.minecraft.world.level.block.SoundEffectType;
import net.minecraft.world.level.block.state.IBlockData;

public class Item_5 extends NMS {

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

				return new BlockSound(effect.a().a(), eff.getDeclaredField("aZ").getFloat(type), eff.getDeclaredField("ba").getFloat(type));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
