package org.ctp.crashapi.entity;

import org.bukkit.entity.Entity;

public class MobInterface {
	private final Class<?> clazz;

	public MobInterface(String name) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName("org.bukkit.entity." + name);
		} catch (Exception ex) {

		}
		this.clazz = clazz;
	}

	public boolean hasInterface(Entity e) {
		return clazz != null && clazz.isInstance(e);
	}
}
