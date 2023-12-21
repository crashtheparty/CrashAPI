package org.ctp.crashapi.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.Event.Result;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseArmorEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.ctp.crashapi.events.EquipEvent;
import org.ctp.crashapi.events.EquipEvent.EquipMethod;
import org.ctp.crashapi.item.ItemSlotType;
import org.ctp.crashapi.item.MatData;
import org.ctp.crashapi.utils.DamageUtils;

/**
 * @author Arnah
 * @since Jul 30, 2015
 */
public class EquipListener implements Listener {
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onWorldChange(final PlayerChangedWorldEvent event) {
		Player player = event.getPlayer();
		
		ItemStack mainHand = player.getInventory().getItemInMainHand();
		EquipEvent e = new EquipEvent(event.getPlayer(), EquipMethod.JOIN, ItemSlotType.MAIN_HAND, mainHand, mainHand);
		Bukkit.getServer().getPluginManager().callEvent(e);

		ItemStack offHand = player.getInventory().getItemInOffHand();
		e = new EquipEvent(event.getPlayer(), EquipMethod.JOIN, ItemSlotType.MAIN_HAND, offHand, offHand);
		Bukkit.getServer().getPluginManager().callEvent(e);
		
		for(ItemStack item: player.getInventory().getArmorContents()) {
			ItemSlotType type = ItemSlotType.matchArmorType(item);
			if (type != null) Bukkit.getServer().getPluginManager().callEvent(new EquipEvent(player, EquipMethod.JOIN, type, item, item));
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onInventoryClickArmor(final InventoryClickEvent e) {
		boolean shift = false, numberkey = false;
		if (e.isCancelled()) return;
		if (e.getAction() == InventoryAction.NOTHING) return;
		if (e.getClick().equals(ClickType.SHIFT_LEFT) || e.getClick().equals(ClickType.SHIFT_RIGHT)) shift = true;
		if (e.getClick().equals(ClickType.NUMBER_KEY)) numberkey = true;
		if (e.getSlotType() != SlotType.ARMOR && e.getSlotType() != SlotType.QUICKBAR && e.getSlotType() != SlotType.CONTAINER) return;
		if (e.getClickedInventory() != null && !e.getClickedInventory().getType().equals(InventoryType.PLAYER)) return;
		if (!e.getInventory().getType().equals(InventoryType.CRAFTING) && !e.getInventory().getType().equals(InventoryType.PLAYER)) return;
		if (!(e.getWhoClicked() instanceof Player)) return;
		ItemSlotType newItemSlotType = ItemSlotType.matchArmorType(shift ? e.getCurrentItem() : e.getCursor());
		if (!shift && newItemSlotType != null && e.getRawSlot() != newItemSlotType.getRawSlot()) return;
		if (shift) {
			newItemSlotType = ItemSlotType.matchArmorType(e.getCurrentItem());
			if (newItemSlotType != null) {
				boolean equipping = true;
				if (e.getRawSlot() == newItemSlotType.getRawSlot()) equipping = false;
				if (newItemSlotType.equals(ItemSlotType.HELMET) && (equipping ? isAirOrNull(e.getWhoClicked().getInventory().getHelmet()) : !isAirOrNull(e.getWhoClicked().getInventory().getHelmet())) || newItemSlotType.equals(ItemSlotType.CHESTPLATE) && (equipping ? isAirOrNull(e.getWhoClicked().getInventory().getChestplate()) : !isAirOrNull(e.getWhoClicked().getInventory().getChestplate())) || newItemSlotType.equals(ItemSlotType.LEGGINGS) && (equipping ? isAirOrNull(e.getWhoClicked().getInventory().getLeggings()) : !isAirOrNull(e.getWhoClicked().getInventory().getLeggings())) || newItemSlotType.equals(ItemSlotType.BOOTS) && (equipping ? isAirOrNull(e.getWhoClicked().getInventory().getBoots()) : !isAirOrNull(e.getWhoClicked().getInventory().getBoots()))) {
					EquipEvent equipEvent = new EquipEvent(e.getWhoClicked(), EquipMethod.SHIFT_CLICK, newItemSlotType, equipping ? null : e.getCurrentItem(), equipping ? e.getCurrentItem() : null);
					Bukkit.getServer().getPluginManager().callEvent(equipEvent);
					if (equipEvent.isCancelled()) e.setCancelled(true);
				}
			}
		} else {
			ItemStack newArmorPiece = e.getCursor();
			ItemStack oldArmorPiece = e.getCurrentItem();
			if (numberkey) {
				if (e.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
					ItemStack hotbarItem = e.getClickedInventory().getItem(e.getHotbarButton());
					if (!isAirOrNull(hotbarItem)) {// Equipping
						newItemSlotType = ItemSlotType.matchArmorType(hotbarItem);
						newArmorPiece = hotbarItem;
						oldArmorPiece = e.getClickedInventory().getItem(e.getSlot());
					} else
						newItemSlotType = ItemSlotType.matchArmorType(!isAirOrNull(e.getCurrentItem()) ? e.getCurrentItem() : e.getCursor());
				}
			} else if (isAirOrNull(e.getCursor()) && !isAirOrNull(e.getCurrentItem())) newItemSlotType = ItemSlotType.matchArmorType(e.getCurrentItem());
			if (newItemSlotType != null && e.getRawSlot() == newItemSlotType.getRawSlot()) {
				EquipMethod method = EquipMethod.PICK_DROP;
				if (e.getAction().equals(InventoryAction.HOTBAR_SWAP) || numberkey) method = EquipMethod.HOTBAR_SWAP;
				EquipEvent equipEvent = new EquipEvent(e.getWhoClicked(), method, newItemSlotType, oldArmorPiece, newArmorPiece);
				Bukkit.getServer().getPluginManager().callEvent(equipEvent);
				if (equipEvent.isCancelled()) e.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerInteractArmor(PlayerInteractEvent e) {
		if (e.getAction() == Action.PHYSICAL) return;
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			final Player player = e.getPlayer();
			if (e.getClickedBlock() != null && e.getAction() == Action.RIGHT_CLICK_BLOCK) // checks is useless, might
				// as well do it though.
				if (e.getClickedBlock().getType().isInteractable()) return;
			ItemSlotType newItemSlotType = ItemSlotType.matchArmorType(e.getItem());
			if (newItemSlotType != null) if (newItemSlotType.equals(ItemSlotType.HELMET) && isAirOrNull(e.getPlayer().getInventory().getHelmet()) || newItemSlotType.equals(ItemSlotType.CHESTPLATE) && isAirOrNull(e.getPlayer().getInventory().getChestplate()) || newItemSlotType.equals(ItemSlotType.LEGGINGS) && isAirOrNull(e.getPlayer().getInventory().getLeggings()) || newItemSlotType.equals(ItemSlotType.BOOTS) && isAirOrNull(e.getPlayer().getInventory().getBoots())) {
				EquipEvent equipEvent = new EquipEvent(e.getPlayer(), EquipMethod.HOTBAR, ItemSlotType.matchArmorType(e.getItem()), null, e.getItem());
				Bukkit.getServer().getPluginManager().callEvent(equipEvent);
				if (equipEvent.isCancelled()) {
					e.setCancelled(true);
					player.updateInventory();
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJoinArmor(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		for(ItemStack item: player.getInventory().getArmorContents()) {
			ItemSlotType type = ItemSlotType.matchArmorType(item);
			if (type != null) Bukkit.getServer().getPluginManager().callEvent(new EquipEvent(player, EquipMethod.JOIN, type, null, item));
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onInventoryDragArmor(InventoryDragEvent event) {
		ItemSlotType type = ItemSlotType.matchArmorType(event.getOldCursor());
		if (event.getRawSlots().isEmpty()) return;// Idk if this will ever happen
		if (type != null && type.getRawSlot() == event.getRawSlots().stream().findFirst().orElse(0)) {
			EquipEvent equipEvent = new EquipEvent(event.getWhoClicked(), EquipMethod.DRAG, type, null, event.getOldCursor());
			Bukkit.getServer().getPluginManager().callEvent(equipEvent);
			if (equipEvent.isCancelled()) {
				event.setResult(Result.DENY);
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onItemBreakArmor(PlayerItemBreakEvent e) {
		ItemSlotType type = ItemSlotType.matchArmorType(e.getBrokenItem());
		if (type != null) {
			Player p = e.getPlayer();
			EquipEvent equipEvent = new EquipEvent(p, EquipMethod.BROKE, type, e.getBrokenItem(), null);
			Bukkit.getServer().getPluginManager().callEvent(equipEvent);
			if (equipEvent.isCancelled()) {
				ItemStack i = e.getBrokenItem().clone();
				i.setAmount(1);
				DamageUtils.setDamage(i, DamageUtils.getDamage(i) - 1);
				if (type.equals(ItemSlotType.HELMET)) p.getInventory().setHelmet(i);
				else if (type.equals(ItemSlotType.CHESTPLATE)) p.getInventory().setChestplate(i);
				else if (type.equals(ItemSlotType.LEGGINGS)) p.getInventory().setLeggings(i);
				else if (type.equals(ItemSlotType.BOOTS)) p.getInventory().setBoots(i);
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerDeathArmor(PlayerDeathEvent e) {
		Player p = e.getEntity();
		for(ItemStack i: p.getInventory().getArmorContents())
			if (!isAirOrNull(i)) Bukkit.getServer().getPluginManager().callEvent(new EquipEvent(p, EquipMethod.DEATH, ItemSlotType.matchArmorType(i), i, null));
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerRespawnArmor(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		for(ItemStack i: p.getInventory().getArmorContents())
			if (!isAirOrNull(i)) Bukkit.getServer().getPluginManager().callEvent(new EquipEvent(p, EquipMethod.DEATH, ItemSlotType.matchArmorType(i), null, i));
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onDispenseArmor(BlockDispenseArmorEvent event) {
		ItemSlotType type = ItemSlotType.matchArmorType(event.getItem());
		if (type != null) if (event.getTargetEntity() instanceof Player) {
			Player p = (Player) event.getTargetEntity();
			EquipEvent equipEvent = new EquipEvent(p, EquipMethod.DISPENSER, type, null, event.getItem());
			Bukkit.getServer().getPluginManager().callEvent(equipEvent);
			if (equipEvent.isCancelled()) event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onInventoryClickHandEquip(InventoryClickEvent e) {
		boolean shift = false, numberkey = false, dropping = false;

		if (e.isCancelled() || !(e.getWhoClicked() instanceof Player) || e.getSlotType() == InventoryType.SlotType.OUTSIDE) return;

		if (!e.getInventory().getType().equals(InventoryType.CRAFTING) && !e.getInventory().getType().equals(InventoryType.WORKBENCH)) return;

		if (e.getClick().equals(ClickType.SHIFT_LEFT) || e.getClick().equals(ClickType.SHIFT_RIGHT)) shift = true;

		if (e.getClick().equals(ClickType.NUMBER_KEY)) numberkey = true;

		if (e.getClick().equals(ClickType.DROP) || e.getClick().equals(ClickType.CONTROL_DROP)) dropping = true;

		ItemStack currentItem = e.getCurrentItem();
		ItemStack cursorItem = e.getCursor();

		// consider AIR as null
		if (isAirOrNull(currentItem)) currentItem = null;
		if (isAirOrNull(cursorItem)) cursorItem = null;

		if (currentItem == null && cursorItem == null) return;

		int holdslot = e.getWhoClicked().getInventory().getHeldItemSlot();
		int clicked = e.getRawSlot();

		if (dropping) {
			if (e.getSlotType().equals(InventoryType.SlotType.QUICKBAR) && e.getSlot() == holdslot && currentItem.getAmount() == 1) {

				EquipEvent event = new EquipEvent(e.getWhoClicked(), EquipMethod.DROP, ItemSlotType.MAIN_HAND, e.getCurrentItem(), null);

				Bukkit.getServer().getPluginManager().callEvent(event);

				if (event.isCancelled()) e.setCancelled(true);

			} else if (clicked == 45 && currentItem.getAmount() == 1) {
				EquipEvent event = new EquipEvent(e.getWhoClicked(), EquipMethod.DROP, ItemSlotType.OFF_HAND, e.getCurrentItem(), null);

				Bukkit.getServer().getPluginManager().callEvent(event);

				if (event.isCancelled()) e.setCancelled(true);
			}
		} else if (shift) {
			if (clicked - 36 == holdslot) {
				EquipEvent event = new EquipEvent(e.getWhoClicked(), EquipMethod.HELD_SWAP, ItemSlotType.MAIN_HAND, e.getCurrentItem(), null);
				Bukkit.getServer().getPluginManager().callEvent(event);

				if (event.isCancelled()) e.setCancelled(true);

			} else {
				if (clicked == 45) {
					EquipEvent event = new EquipEvent(e.getWhoClicked(), EquipMethod.DROP, ItemSlotType.OFF_HAND, e.getCurrentItem(), null);

					Bukkit.getServer().getPluginManager().callEvent(event);

					if (event.isCancelled()) e.setCancelled(true);
					return;
				} else if (currentItem != null && currentItem.getType() == Material.SHIELD && isAirOrNull(e.getWhoClicked().getInventory().getItemInOffHand())) {
					EquipEvent event = new EquipEvent(e.getWhoClicked(), EquipMethod.DROP, ItemSlotType.OFF_HAND, null, e.getCurrentItem());

					Bukkit.getServer().getPluginManager().callEvent(event);

					if (event.isCancelled()) e.setCancelled(true);
					return;
				}
				int empty = -1;

				if (e.getSlotType() == InventoryType.SlotType.QUICKBAR) return;

				if (e.getSlotType() == InventoryType.SlotType.RESULT) {

				} else
					for(int i = 0; i <= 8; i++) {
						ItemStack item = e.getWhoClicked().getInventory().getItem(i);

						if (isAirOrNull(item)) {
							empty = i;
							break;
						}
					}

				if (empty == holdslot) {
					EquipEvent event = new EquipEvent(e.getWhoClicked(), EquipMethod.HELD_SWAP, ItemSlotType.MAIN_HAND, currentItem, null);
					Bukkit.getServer().getPluginManager().callEvent(event);

					if (event.isCancelled()) e.setCancelled(true);
				}
			}

		} else {
			ItemStack newItem = null;
			ItemStack oldItem = null;

			if (numberkey) {
				if (e.getHotbarButton() == clicked - 36) return;

				if (e.getHotbarButton() == holdslot) {
					newItem = e.getCursor();
					oldItem = e.getWhoClicked().getInventory().getItem(e.getHotbarButton());
				} else if (clicked - 36 == holdslot) {
					oldItem = e.getCursor();
					newItem = e.getWhoClicked().getInventory().getItem(e.getHotbarButton());
				}

				if (oldItem == null || newItem == null) return;

				EquipEvent event = new EquipEvent(e.getWhoClicked(), EquipMethod.HOT_BAR, ItemSlotType.MAIN_HAND, oldItem, newItem);

				Bukkit.getServer().getPluginManager().callEvent(event);

				if (event.isCancelled()) e.setCancelled(true);

			} else if (e.getSlot() == holdslot) {

				newItem = cursorItem;
				oldItem = currentItem;

				EquipEvent event = new EquipEvent(e.getWhoClicked(), EquipMethod.HOT_BAR, ItemSlotType.MAIN_HAND, oldItem, newItem);

				Bukkit.getServer().getPluginManager().callEvent(event);

				if (event.isCancelled()) e.setCancelled(true);

			} else if (clicked == 45) {
				newItem = cursorItem;
				oldItem = currentItem;

				EquipEvent event = new EquipEvent(e.getWhoClicked(), EquipMethod.HOT_BAR, ItemSlotType.OFF_HAND, oldItem, newItem);

				Bukkit.getServer().getPluginManager().callEvent(event);

				if (event.isCancelled()) e.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onCraftItemHandEquip(CraftItemEvent e) {
		if (e.isCancelled()) return;

		if (!(e.getWhoClicked() instanceof Player)) return;

		if (!e.getClick().equals(ClickType.SHIFT_LEFT) && !e.getClick().equals(ClickType.SHIFT_RIGHT)) return; // only handle shift click

		Player player = (Player) e.getWhoClicked();

		ItemStack currentItem = e.getCurrentItem();

		if (isAirOrNull(currentItem)) return;

		boolean fits = fitsInInventory(player, currentItem, e);

		int empty = -1;

		for(int i = 8; i >= 0 && !fits; i--) {
			ItemStack item = e.getWhoClicked().getInventory().getItem(i);

			if (isAirOrNull(item)) {
				empty = i;
				break;
			}
		}

		if (empty == player.getInventory().getHeldItemSlot()) {
			EquipEvent event = new EquipEvent(player, EquipMethod.CRAFTED, ItemSlotType.MAIN_HAND, null, currentItem);

			Bukkit.getServer().getPluginManager().callEvent(event);

			if (event.isCancelled()) e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerItemHeldEvent(PlayerItemHeldEvent event) {
		Player player = event.getPlayer();
		ItemStack mainHand = player.getInventory().getItem(event.getNewSlot());
		EquipEvent e = new EquipEvent(event.getPlayer(), EquipMethod.JOIN, ItemSlotType.MAIN_HAND, null, mainHand);
		Bukkit.getServer().getPluginManager().callEvent(e);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJoinHand(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		ItemStack mainHand = player.getInventory().getItemInMainHand();
		EquipEvent e = new EquipEvent(event.getPlayer(), EquipMethod.JOIN, ItemSlotType.MAIN_HAND, null, mainHand);
		Bukkit.getServer().getPluginManager().callEvent(e);

		ItemStack offHand = player.getInventory().getItemInOffHand();
		e = new EquipEvent(event.getPlayer(), EquipMethod.JOIN, ItemSlotType.MAIN_HAND, null, offHand);
		Bukkit.getServer().getPluginManager().callEvent(e);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onSwitchOffHandEquip(PlayerSwapHandItemsEvent e) {
		if (e.isCancelled()) return;

		ItemStack newItem = e.getMainHandItem();
		ItemStack oldItem = e.getOffHandItem();

		if (isAirOrNull(newItem)) newItem = null;

		if (isAirOrNull(oldItem)) oldItem = null;

		if (oldItem == null && newItem == null) return;

		EquipEvent event = new EquipEvent(e.getPlayer(), EquipMethod.HELD_SWITCH, ItemSlotType.MAIN_HAND, oldItem, newItem);

		Bukkit.getServer().getPluginManager().callEvent(event);

		if (event.isCancelled()) e.setCancelled(true);

		EquipEvent offhand = new EquipEvent(e.getPlayer(), EquipMethod.HELD_SWITCH, ItemSlotType.OFF_HAND, newItem, oldItem);

		Bukkit.getServer().getPluginManager().callEvent(offhand);

		if (offhand.isCancelled()) e.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerDropItemHandEquip(PlayerDropItemEvent e) {
		// TODO - might not have to check this, let's see
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerConsumeHandEquip(PlayerItemConsumeEvent e) {
		// TODO - shouldn't matter for ES currently
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onInventoryPickupHandEquip(EntityPickupItemEvent e) {
		if (e.isCancelled() || e.getItem() == null || e.getItem().getItemStack() == null || MatData.isAir(e.getItem().getItemStack().getType())) return;

		ItemStack item = e.getItem().getItemStack();

		if (!(e.getEntity() instanceof Player)) return;

		Player player = (Player) e.getEntity();

		boolean fits = fitsInInventory(player, item, e);

		int empty = -1;

		for(int i = 0; i <= 8 && !fits; i++) {
			ItemStack t = player.getInventory().getItem(i);

			if (isAirOrNull(t)) {
				empty = i;
				break;
			}
		}

		if (empty == player.getInventory().getHeldItemSlot()) {
			EquipEvent event = new EquipEvent(player, EquipMethod.PICK_UP, ItemSlotType.MAIN_HAND, null, item);
			Bukkit.getServer().getPluginManager().callEvent(event);

			if (event.isCancelled()) e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onItemBreakHandEquip(PlayerItemBreakEvent e) {
		Player player = e.getPlayer();

		ItemSlotType type = ItemSlotType.matchArmorType(e.getBrokenItem());

		if (type == null) return;

		EquipEvent event = new EquipEvent(player, EquipMethod.BROKE, type, e.getBrokenItem(), null);
		Bukkit.getServer().getPluginManager().callEvent(event);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerDeathHandEquip(PlayerDeathEvent e) {
		Player p = e.getEntity();

		Bukkit.getServer().getPluginManager().callEvent(new EquipEvent(p, EquipMethod.DEATH, ItemSlotType.MAIN_HAND, p.getInventory().getItemInMainHand(), null));
		Bukkit.getServer().getPluginManager().callEvent(new EquipEvent(p, EquipMethod.DEATH, ItemSlotType.OFF_HAND, p.getInventory().getItemInOffHand(), null));
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerRespawnHandEquip(PlayerRespawnEvent event) {
		Player player = event.getPlayer();

		Bukkit.getServer().getPluginManager().callEvent(new EquipEvent(player, EquipMethod.PICK_UP, ItemSlotType.MAIN_HAND, null, player.getInventory().getItemInMainHand()));
		Bukkit.getServer().getPluginManager().callEvent(new EquipEvent(player, EquipMethod.PICK_UP, ItemSlotType.OFF_HAND, null, player.getInventory().getItemInOffHand()));
	}

	private boolean fitsInInventory(HumanEntity who, ItemStack item, Event e) {
		boolean fits = false;

		if (isAirOrNull(item) || item.getMaxStackSize() == 1) return false;

		int amount = item.getAmount();

		if (e instanceof CraftItemEvent) amount = getCraftAmount((CraftItemEvent) e);

		for(int i = 9; i < who.getInventory().getSize() && !fits; i++) {
			ItemStack t = who.getInventory().getItem(i);

			if (isAirOrNull(t) || t.getAmount() == t.getMaxStackSize()) continue;

			if (t.isSimilar(item)) amount = amount - (t.getMaxStackSize() - t.getAmount());

			if (amount <= 0) fits = true;
		}

		return fits;
	}

	private int getCraftAmount(CraftItemEvent e) {
		if (e.isCancelled()) return 0;

		Player p = (Player) e.getWhoClicked();

		if (e.isShiftClick()) {
			int itemsChecked = 0;
			int possibleCreations = 1;

			int amountCanBeMade = 0;

			for(ItemStack item: e.getInventory().getMatrix())
				if (!isAirOrNull(item)) if (itemsChecked == 0) {
					possibleCreations = item.getAmount();
					itemsChecked++;
				} else
					possibleCreations = Math.min(possibleCreations, item.getAmount());

			int amountOfItems = e.getRecipe().getResult().getAmount() * possibleCreations;

			ItemStack i = e.getRecipe().getResult();

			for(int s = 0; s <= 35; s++) {
				ItemStack test = p.getInventory().getItem(s);
				if (isAirOrNull(test)) {
					amountCanBeMade += i.getMaxStackSize();
					continue;
				}
				if (test.isSimilar(i)) amountCanBeMade += i.getMaxStackSize() - test.getAmount();
			}

			return amountOfItems > amountCanBeMade ? amountCanBeMade : amountOfItems;
		} else
			return e.getRecipe().getResult().getAmount();
	}

	private boolean isAirOrNull(ItemStack item) {
		return item == null || MatData.isAir(item.getType());
	}
}
