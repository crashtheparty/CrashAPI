package org.ctp.crashapi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.ctp.crashapi.config.CrashConfigurations;
import org.ctp.crashapi.config.yaml.YamlConfig;
import org.ctp.crashapi.item.ItemSerialization;
import org.ctp.crashapi.utils.ChatUtils;
import org.ctp.crashapi.version.PluginVersion;

public abstract class CrashAPIPlugin extends JavaPlugin {

	private static boolean MMO_ITEMS = false;

	public abstract String getStarter();

	public abstract ChatUtils getChat();

	public abstract ItemSerialization getItemSerial();

	public abstract CrashConfigurations getConfigurations();

	public void sendInfo(String message) {
		getChat().sendInfo(message);
	}

	public void sendWarning(String message) {
		getChat().sendWarning(message);
	}

	public void sendSevere(String message) {
		getChat().sendSevere(message);
	}

	public abstract YamlConfig getLanguageFile();

	public void addCompatibility() {
		if (Bukkit.getPluginManager().isPluginEnabled("MMOItems")) {
			MMO_ITEMS = true;
			getChat().sendInfo("MMOItems compatibility enabled!");
		}
	}

	public static boolean getMMOItems() {
		return MMO_ITEMS;
	}
	
	public abstract boolean isInitializing();

	public abstract PluginVersion getPluginVersion();
}
