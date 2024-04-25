package org.ctp.crashapi.nms.anvil;

import org.bukkit.entity.Player;
import org.ctp.crashapi.data.inventory.InventoryData;

public interface AnvilClickEventHandler {
	void onAnvilClick(AnvilClickEvent event);

	public static AnvilClickEventHandler getHandler(Player player, InventoryData data) {
		return event -> {
			if (event.getSlot() == null) {
				event.setWillClose(false);
				event.setWillDestroy(false);
				return;
			}
			if (event.getSlot().getSlot() != 2) {
				event.setWillClose(false);
				event.setWillDestroy(false);
				return;
			}
			if (event.getName().equals("")) {
				event.setWillClose(false);
				event.setWillDestroy(false);
				return;
			}
		};
	}
}