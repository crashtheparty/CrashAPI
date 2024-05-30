package org.ctp.crashapi.data.items;

import org.bukkit.Material;

public class MatData {

	private Material material;
	private String materialName;

	public MatData(String name) {
		if (name == null) name = "air";
		materialName = name.toUpperCase();
		try {
			material = Material.valueOf(materialName);
		} catch (Exception ex) {}
	}

	public Material getMaterial() {
		return material;
	}

	public String getMaterialName() {
		return materialName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MatData) {
			MatData data = (MatData) obj;
			return data.material != null && data.material == material;
		}
		return false;
	}

	public boolean hasMaterial() {
		return material != null;
	}
	
	public static boolean isShulkerBox(Material type) {
		switch(type.name()) {
			case "BLACK_SHULKER_BOX":
			case "BLUE_SHULKER_BOX":
			case "BROWN_SHULKER_BOX":
			case "CYAN_SHULKER_BOX":
			case "GRAY_SHULKER_BOX":
			case "GREEN_SHULKER_BOX":
			case "LIGHT_BLUE_SHULKER_BOX":
			case "LIME_SHULKER_BOX":
			case "MAGENTA_SHULKER_BOX":
			case "ORANGE_SHULKER_BOX":
			case "PINK_SHULKER_BOX":
			case "PURPLE_SHULKER_BOX":
			case "RED_SHULKER_BOX":
			case "LIGHT_GRAY_SHULKER_BOX":
			case "WHITE_SHULKER_BOX":
			case "YELLOW_SHULKER_BOX":
			case "SHULKER_BOX":
				return true;
		}
		return false;
	}

	public static boolean isAir(Material type) {
		switch (type.name()) {
			case "AIR":
			case "CAVE_AIR":
			case "VOID_AIR":
				return true;
		}
		return false;
	}

	public static boolean isBook(Material type) {
		switch (type.name()) {
			case "BOOK":
			case "ENCHANTED_BOOK":
				return true;
		}
		return false;
	}
}
