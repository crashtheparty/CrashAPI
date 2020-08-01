package org.ctp.crashapi.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.bukkit.Location;
import org.bukkit.Statistic;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.ctp.crashapi.api.Configurations;
import org.ctp.crashapi.compatibility.MMOUtils;
import org.ctp.crashapi.item.*;

public class ItemUtils {

	public static void giveItemsToPlayer(Player player, Collection<ItemStack> drops, Location fallback,
	boolean statistic) {
		for(ItemStack drop: drops)
			giveItemToPlayer(player, drop, fallback, statistic);
	}

	public static void giveItemToPlayer(Player player, ItemStack item, Location fallback, boolean statistic) {
		HashMap<Integer, ItemStack> leftOver = new HashMap<Integer, ItemStack>();
		int amount = item.getAmount();
		leftOver.putAll(player.getInventory().addItem(item));
		Location fallbackClone = fallback.clone();
		boolean dropNaturally = Configurations.getConfigurations().getConfig().getBoolean("drop_items_naturally");
		if (!leftOver.isEmpty()) for(Iterator<Entry<Integer, ItemStack>> it = leftOver.entrySet().iterator(); it.hasNext();) {
			Entry<Integer, ItemStack> e = it.next();
			amount -= e.getValue().getAmount();
			if (!dropNaturally) {
				Item droppedItem = fallbackClone.getWorld().dropItem(fallbackClone, e.getValue());
				droppedItem.setVelocity(new Vector(0, 0, 0));
				droppedItem.teleport(fallbackClone);
			} else
				fallbackClone.getWorld().dropItemNaturally(fallbackClone, item);
		}
		if (amount > 0 && statistic) player.incrementStatistic(Statistic.PICKUP, item.getType(), amount);
	}

	public static void dropItems(Collection<ItemStack> drops, Location loc) {
		for(ItemStack drop: drops)
			dropItem(drop, loc);
	}

	public static void dropItem(ItemStack item, Location loc) {
		Location location = loc.clone();
		if (!Configurations.getConfigurations().getConfig().getBoolean("drop_items_naturally")) {
			Item droppedItem = location.getWorld().dropItem(location, item);
			droppedItem.setVelocity(new Vector(0, 0, 0));
			droppedItem.teleport(location);
		} else
			location.getWorld().dropItemNaturally(location, item);
	}

	public static boolean checkItemType(ItemData item, CustomItemType type) {
		if (type.getVanilla() == VanillaItemType.VANILLA) {
			MatData data = new MatData(type.getType().split(":")[1]);
			return data.hasMaterial() && data.getMaterial() == item.getMaterial();
		}
		return MMOUtils.check(item, type);
	}
}
