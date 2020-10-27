package org.ctp.crashapi.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Statistic;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.ctp.crashapi.api.Configurations;
import org.ctp.crashapi.compatibility.MMOUtils;
import org.ctp.crashapi.events.ItemAddEvent;
import org.ctp.crashapi.events.ItemEquipEvent;
import org.ctp.crashapi.events.ItemEquipEvent.HandMethod;
import org.ctp.crashapi.item.*;

public class ItemUtils {

	public static void giveItemsToPlayer(Player player, Collection<ItemStack> drops, Location fallback,
	boolean statistic) {
		giveItemsToPlayer(player, drops, fallback, statistic, HandMethod.COMMAND);
	}

	public static void giveItemsToPlayer(Player player, Collection<ItemStack> drops, Location fallback,
	boolean statistic, HandMethod method) {
		for(ItemStack drop: drops)
			giveItemToPlayer(player, drop, fallback, statistic, method);
	}

	public static void giveItemToPlayer(Player player, ItemStack item, Location fallback, boolean statistic) {
		giveItemToPlayer(player, item, fallback, statistic, HandMethod.COMMAND);
	}

	public static void giveItemToPlayer(Player player, ItemStack item, Location fallback, boolean statistic, HandMethod method) {
		int addedAmount = 0;
		addedAmount += addItems(player, item, method, false);
		if (item.getAmount() > 0) addedAmount += addItems(player, item, method, true);
		Location fallbackClone = fallback.clone();
		boolean dropNaturally = Configurations.getConfigurations().getConfig().getBoolean("drop_items_naturally");
		if (item.getAmount() > 0 && !dropNaturally) {
			Item droppedItem = fallbackClone.getWorld().dropItem(fallbackClone, item);
			droppedItem.setVelocity(new Vector(0, 0, 0));
			droppedItem.teleport(fallbackClone);
		} else if (item.getAmount() > 0) fallbackClone.getWorld().dropItemNaturally(fallbackClone, item);
		if (addedAmount > 0 && statistic) player.incrementStatistic(Statistic.PICKUP, item.getType(), addedAmount);
	}

	private static int addItems(Player player, ItemStack item, HandMethod method, boolean empty) {
		int addedAmount = 0;
		for(int i = 0; i < 36; i++) {
			ItemStack prevItem = null;
			if (player.getInventory().getItem(i) != null) prevItem = player.getInventory().getItem(i).clone();

			if (empty && (prevItem == null || MatData.isAir(prevItem.getType())) || !empty && prevItem != null && prevItem.isSimilar(item)) {
				Event event = null;
				ItemStack finalItem = player.getInventory().getItem(i);
				if (finalItem == null || MatData.isAir(finalItem.getType())) {
					finalItem = item.clone();
					addedAmount += item.getAmount();
					item.setAmount(0);
					player.getInventory().setItem(i, finalItem);
				} else {
					int amount = Math.min(prevItem.getType().getMaxStackSize(), prevItem.getAmount() + item.getAmount());
					int leftover = prevItem.getAmount() + item.getAmount() - amount;
					addedAmount += item.getAmount() - amount;
					item.setAmount(leftover);
					finalItem.setAmount(amount);
				}
				if (i == player.getInventory().getHeldItemSlot()) event = new ItemEquipEvent(player, method, ItemSlotType.MAIN_HAND, prevItem, finalItem);
				else
					event = new ItemAddEvent(player, finalItem);
				Bukkit.getPluginManager().callEvent(event);
			}
		}
		return addedAmount;
	}

	public static void dropItems(Collection<ItemStack> drops, Location loc, boolean natural) {
		for(ItemStack drop: drops)
			dropItem(drop, loc, natural);
	}

	public static void dropItem(ItemStack item, Location loc, boolean natural) {
		Location location = loc.clone();
		if (!natural) {
			Item droppedItem = location.getWorld().dropItem(location, item);
			droppedItem.setVelocity(new Vector(0, 0, 0));
			droppedItem.teleport(location);
		} else
			location.getWorld().dropItemNaturally(location, item);
	}

	public static Item spawnItem(ItemStack item, Location loc) {
		Location location = loc.clone();
		return location.getWorld().dropItemNaturally(location, item);
	}

	public static boolean checkItemType(ItemData item, CustomItemType type) {
		if (type.getVanilla() == VanillaItemType.VANILLA) {
			MatData data = new MatData(type.getType().split(":")[1]);
			return data.hasMaterial() && data.getMaterial() == item.getMaterial();
		}
		return MMOUtils.check(item, type);
	}

	public static List<Item> itemStacksToItems(List<ItemStack> items, Location loc) {
		Item[] array = new Item[items.size()];
		for(int i = 0; i < items.size(); i++)
			array[i] = spawnItem(items.get(i), loc);
		return Arrays.asList(array);
	}
}
