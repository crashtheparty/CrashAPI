package org.ctp.crashapi.data;

import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class DamageCauseType {

	private String name;
	private DamageCause cause;
	
	public DamageCauseType(String name) {
		this.name = name;
		try {
			cause = DamageCause.valueOf(name);
		} catch (Exception ex) {}
	}
	
	public static boolean isContactType(DamageCause cause) {
		switch (cause.name()) {
			case "BLOCK_EXPLOSION":
			case "CONTACT":
			case "CUSTOM":
			case "ENTITY_ATTACK":
			case "ENTITY_EXPLOSION":
			case "ENTITY_SWEEP_ATTACK":
			case "LIGHTNING":
			case "PROJECTILE":
			case "THORNS":
				return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public DamageCause getCause() {
		return cause;
	}
}
