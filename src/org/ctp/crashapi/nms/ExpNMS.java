package org.ctp.crashapi.nms;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.block.BlockExpEvent;
import org.ctp.crashapi.nms.exp.Exp_1;
import org.ctp.crashapi.nms.exp.Exp_2;
import org.ctp.crashapi.nms.exp.Exp_v1_16_R3;

public class ExpNMS extends NMS {

	public static void dropExp(Location location, int i, boolean callEvent, Player player) {
		int leftover = 0;
		Location loc = location.clone().add(0.5, 0.5, 0.5);

		if (callEvent) {
			BlockExpEvent exp = new BlockExpEvent(loc.getBlock(), i);
			Bukkit.getPluginManager().callEvent(exp);
			if (exp instanceof Cancellable && ((Cancellable) exp).isCancelled()) return;
		}

		switch (getVersionNumber()) {
			case 16:
				leftover = Exp_v1_16_R3.dropExp(loc, i);
				break;
			default:
				if (isSimilarOrAbove(getVersionNumbers(), 1, 18, 0)) leftover = Exp_2.dropExp(loc, i);
				else if (isSimilarOrAbove(getVersionNumbers(), 1, 17, 0)) leftover = Exp_1.dropExp(loc, i);
				break;
		}
		if (leftover > 0) loc.getWorld().spawn(loc, ExperienceOrb.class).setExperience(leftover);
	}
}
