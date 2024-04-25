package org.ctp.crashapi.data.inventory;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.ctp.crashapi.utils.ChatUtils;

public interface Pageable {

	default ItemStack nextPage() {
		return pagination("pagination.next_page");
	}

	default ItemStack previousPage() {
		return pagination("pagination.previous_page");
	}

	default ItemStack goBack() {
		return pagination("pagination.go_back");
	}

	default ItemStack pagination(String path) {
		ItemStack nextPage = new ItemStack(Material.ARROW);
		ItemMeta nextPageMeta = nextPage.getItemMeta();
		nextPageMeta.setDisplayName(getChat().getMessage(ChatUtils.getCodes(), path));
		nextPage.setItemMeta(nextPageMeta);
		return nextPage;
	}

	public int getPage();

	public void setPage(int page);

	public ChatUtils getChat();
}
