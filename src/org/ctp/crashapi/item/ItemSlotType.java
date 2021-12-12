package org.ctp.crashapi.item;

import java.util.Arrays;
import java.util.List;

import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

/**
 * @author Arnah
 * @since Jul 30, 2015
 */
public enum ItemSlotType {
	MAIN_HAND(45, EquipmentSlot.HAND, 45), OFF_HAND(40, EquipmentSlot.OFF_HAND, 9), HELMET(36, EquipmentSlot.HEAD, 5), CHESTPLATE(37, EquipmentSlot.CHEST, 6),
	LEGGINGS(38, EquipmentSlot.LEGS, 7), BOOTS(39, EquipmentSlot.FEET, 8), INVENTORY_UNKNOWN(46, null), INVENTORY_0(0, null), INVENTORY_1(1, null),
	INVENTORY_2(2, null), INVENTORY_3(3, null), INVENTORY_4(4, null), INVENTORY_5(5, null), INVENTORY_6(6, null), INVENTORY_7(7, null), INVENTORY_8(8, null),
	INVENTORY_9(9, null), INVENTORY_10(10, null), INVENTORY_11(11, null), INVENTORY_12(12, null), INVENTORY_13(13, null), INVENTORY_14(14, null),
	INVENTORY_15(15, null), INVENTORY_16(16, null), INVENTORY_17(17, null), INVENTORY_18(18, null), INVENTORY_19(19, null), INVENTORY_20(20, null),
	INVENTORY_21(21, null), INVENTORY_22(22, null), INVENTORY_23(23, null), INVENTORY_24(24, null), INVENTORY_25(25, null), INVENTORY_26(26, null),
	INVENTORY_27(27, null), INVENTORY_28(28, null), INVENTORY_29(29, null), INVENTORY_30(30, null), INVENTORY_31(31, null), INVENTORY_32(32, null),
	INVENTORY_33(33, null), INVENTORY_34(34, null), INVENTORY_35(35, null);

	public static List<ItemSlotType> ARMOR = Arrays.asList(HELMET, CHESTPLATE, LEGGINGS, BOOTS);
	private final int slot, rawSlot;
	private final EquipmentSlot equipmentSlot;

	ItemSlotType(int slot, EquipmentSlot equipmentSlot) {
		this(slot, equipmentSlot, 46);
	}

	ItemSlotType(int slot, EquipmentSlot equipmentSlot, int rawSlot) {
		this.slot = slot;
		this.equipmentSlot = equipmentSlot;
		this.rawSlot = rawSlot;
	}

	public final static ItemSlotType matchArmorType(final ItemStack itemStack) {
		if (itemStack == null || MatData.isAir(itemStack.getType())) return null;
		String type = itemStack.getType().name();
		if (type.endsWith("_HELMET") || type.endsWith("_SKULL")) return HELMET;
		else if (type.endsWith("_CHESTPLATE") || type.contains("ELYTRA")) return CHESTPLATE;
		else if (type.endsWith("_LEGGINGS")) return LEGGINGS;
		else if (type.endsWith("_BOOTS")) return BOOTS;
		else
			return null;
	}

	public final static ItemSlotType getTypeFromSlot(int slot) {
		for(ItemSlotType value: values())
			if (value.getSlot() == slot) return value;
		return INVENTORY_UNKNOWN;
	}

	public int getSlot() {
		return slot;
	}

	public EquipmentSlot getEquipmentSlot() {
		return equipmentSlot;
	}

	public int getRawSlot() {
		return rawSlot;
	}
}