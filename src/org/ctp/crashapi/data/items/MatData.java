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
