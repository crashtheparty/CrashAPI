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
import org.bukkit.inventory.PlayerInventory;
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
		addedAmount += addItems(player, item, method);
		Location fallbackClone = fallback.clone();
		boolean dropNaturally = Configurations.getConfigurations().getConfig().getBoolean("drop_items_naturally");
		if (item.getAmount() > 0 && !dropNaturally) {
			Item droppedItem = fallbackClone.getWorld().dropItem(fallbackClone, item);
			droppedItem.setVelocity(new Vector(0, 0, 0));
			droppedItem.teleport(fallbackClone);
		} else if (item.getAmount() > 0) fallbackClone.getWorld().dropItemNaturally(fallbackClone, item);
		if (addedAmount > 0 && statistic) player.incrementStatistic(Statistic.PICKUP, item.getType(), addedAmount);
	}

	private static int addItems(Player player, ItemStack item, HandMethod method) {
		int prevAmount = item.getAmount();
		int fullAmount = item.getAmount();
		PlayerInventory inv = player.getInventory();
		int maxStack = item.getType().getMaxStackSize();
		int partial = 0, empty = 0;
		while (true) {
			partial = firstPartial(inv, item, partial);
			if (partial == -1) {
				empty = firstEmpty(inv, empty);

				if (empty == -1) break;
				else {
					ItemStack newStack = item.clone();
					if (prevAmount > maxStack) {
						newStack.setAmount(maxStack);
						inv.setItem(empty, newStack);
						callItemEvent(player, empty, method, null, newStack);
						prevAmount -= maxStack;
					} else {
						newStack.setAmount(prevAmount);
						inv.setItem(empty, newStack);
						callItemEvent(player, empty, method, null, newStack);
						prevAmount = 0;
						break;
					}
				}
			} else {
				ItemStack partialItem = inv.getItem(partial);
				ItemStack currentItem = partialItem.clone();
				int partialAmount = partialItem.getAmount();

				if (prevAmount + partialAmount <= maxStack) {
					currentItem.setAmount(prevAmount + partialAmount);
					inv.setItem(partial, currentItem);
					callItemEvent(player, partial, method, partialItem, currentItem);
					prevAmount = 0;
					break;
				}
				currentItem.setAmount(maxStack);
				inv.setItem(partial, currentItem);
				callItemEvent(player, partial, method, partialItem, currentItem);
				prevAmount = prevAmount + partialAmount - maxStack;
			}
		}
		item.setAmount(prevAmount);
		return fullAmount - prevAmount;
	}

	private static int firstPartial(PlayerInventory inv, ItemStack item, int from) {
		ItemStack[] inventory = inv.getStorageContents();
		if (item == null) return -1;
		for(int i = from; i < inventory.length; i++) {
			ItemStack cItem = inventory[i];
			if (cItem != null && cItem.getAmount() < cItem.getMaxStackSize() && cItem.isSimilar(item)) return i;
		}
		return -1;
	}

	private static int firstEmpty(PlayerInventory inv, int from) {
		if (from == 0) return inv.firstEmpty();
		ItemStack[] inventory = inv.getStorageContents();
		for(int i = from; i < inventory.length; i++) {
			ItemStack cItem = inventory[i];
			if (cItem == null || MatData.isAir(cItem.getType())) return i;
		}
		return -1;
	}

	private static void callItemEvent(Player player, int slot, HandMethod method, ItemStack prev, ItemStack current) {
		Event event = null;
		if (slot == player.getInventory().getHeldItemSlot()) event = new ItemEquipEvent(player, method, ItemSlotType.MAIN_HAND, prev, current);
		else
			event = new ItemAddEvent(player, current);
		Bukkit.getPluginManager().callEvent(event);
	}

	public static void dropItems(Collection<ItemStack> drops, Location loc) {
		for(ItemStack drop: drops)
			dropItem(drop, loc);
	}

	public static void dropItem(ItemStack item, Location loc) {
		Location location = loc.clone();
		boolean natural = Configurations.getConfigurations().getConfig().getBoolean("drop_items_naturally");
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
