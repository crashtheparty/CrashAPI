package org.ctp.crashapi.utils;

public class DamageResult {

	private final int damageAmount, oldDamage, newDamage, maxDamage;
	private final boolean broken, damaged;

	public DamageResult(boolean broken, int damageAmount, int oldDamage, int newDamage, int maxDamage) {
		this.broken = broken;
		this.damageAmount = damageAmount;
		this.damaged = damageAmount > 0;
		this.oldDamage = oldDamage;
		this.newDamage = newDamage;
		this.maxDamage = maxDamage;
	}

	public int getDamageAmount() {
		return damageAmount;
	}

	public int getOldDamage() {
		return oldDamage;
	}

	public int getNewDamage() {
		return newDamage;
	}

	public int getMaxDamage() {
		return maxDamage;
	}

	public boolean isBroken() {
		return broken;
	}

	public boolean wasDamaged() {
		return damaged;
	}
}
