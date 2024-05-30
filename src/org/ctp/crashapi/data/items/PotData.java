package org.ctp.crashapi.data.items;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Arrow;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

@SuppressWarnings("deprecation")
public class PotData {
	public static PotData JUMP = (new PotData("JUMP").getPotionEffect() == null ? new PotData("JUMP_BOOST") : new PotData("JUMP"));
	public static PotData SLOW = (new PotData("SLOW").getPotionEffect() == null ? new PotData("SLOWNESS") : new PotData("SLOW"));
	public static PotData HARM = (new PotData("HARM").getPotionEffect() == null ? new PotData("INSTANT_DAMAGE") : new PotData("HARM"));
	public static PotData NAUSEA = (new PotData("CONFUSION").getPotionEffect() == null ? new PotData("NAUSEA") : new PotData("CONFUSION"));
	public static PotData MINING_FATIGUE = (new PotData("SLOW_DIGGING").getPotionEffect() == null ? new PotData("MINING_FATIGUE") : new PotData("SLOW_DIGGING"));
	public static List<PotData> BAD_POTIONS = Arrays.asList(SLOW, HARM, NAUSEA, MINING_FATIGUE, new PotData("BLINDNESS"), new PotData("DARKNESS"), new PotData("HUNGER"), new PotData("POISON"), new PotData("UNLUCK"), new PotData("WEAKNESS"), new PotData("WITHER"));

	private PotionEffectType potion;
	private String potionName;

	@SuppressWarnings("unchecked")
	public PotData(String name) {
		potionName = name.toUpperCase();
		try {
			Class<?> clazz = Registry.class;
			Field field = clazz.getDeclaredField("EFFECT");
			if (field != null) potion = ((Registry<PotionEffectType>) field.get(null)).get(NamespacedKey.minecraft(potionName));
		} catch (Exception ex) {
			potion = PotionEffectType.getByName(name);
		}
	}

	public PotionEffectType getPotionEffect() {
		return potion;
	}

	public String getPotionName() {
		return potionName;
	}
	
	public static boolean isBadPotionEffect(PotionEffectType type) {
		for (PotData data : BAD_POTIONS)
			if (data.getPotionEffect() == type) return true;
		return false;
	}

	public static PotionEffectType getPotionEffectType(AreaEffectCloud cloud) {
		try {
			Class<?> clazz = AreaEffectCloud.class;
			Method method = clazz.getDeclaredMethod("getBasePotionData");
			Object o = method.invoke(cloud);
			if (o instanceof PotionData) return ((PotionData) o).getType().getEffectType();
		} catch (Exception ex) {
			try {
				Class<?> clazz = AreaEffectCloud.class;
				Method method = clazz.getDeclaredMethod("getBasePotionType");
				Object o = method.invoke(cloud);
				if (o instanceof PotionType) return ((PotionType) o).getEffectType();
			} catch (Exception ex2) {

			}
		}
		return null;
	}

	public static void setArrowPotionType(ItemStack item, Arrow arrow) {
		PotionMeta meta = (PotionMeta) item.getItemMeta();
		try {
			Class<?> clazz1 = Arrow.class;
			Class<?> clazz2 = PotionMeta.class;
			Method method1 = clazz1.getDeclaredMethod("setBasePotionType", PotionType.class);
			Method method2 = clazz2.getDeclaredMethod("getBasePotionType");
			method1.invoke(arrow, method2.invoke(meta));
		} catch (Exception ex) {
			try {
				Class<?> clazz1 = Arrow.class;
				Class<?> clazz2 = PotionMeta.class;
				Method method1 = clazz1.getDeclaredMethod("setBasePotionData", PotionData.class);
				Method method2 = clazz2.getDeclaredMethod("getBasePotionData");
				method1.invoke(arrow, method2.invoke(meta));
			} catch (Exception ex2) {

			}
		}
	}
}
