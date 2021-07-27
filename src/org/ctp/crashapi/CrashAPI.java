package org.ctp.crashapi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.ctp.crashapi.api.Configurations;
import org.ctp.crashapi.api.CrashBackup;
import org.ctp.crashapi.config.yaml.YamlConfig;
import org.ctp.crashapi.db.BackupDB;
import org.ctp.crashapi.item.ItemSerialization;
import org.ctp.crashapi.listeners.EquipListener;
import org.ctp.crashapi.listeners.ItemEnterInventoryListener;
import org.ctp.crashapi.utils.ChatUtils;
import org.ctp.crashapi.version.*;
import org.ctp.crashapi.version.Version.VersionType;

import net.milkbowl.vault.economy.Economy;

public class CrashAPI extends CrashAPIPlugin {

	private static CrashAPI PLUGIN;
	private static CrashBackup BACKUP;
	private Configurations config;
	private BukkitVersion bukkitVersion;
	private PluginVersion pluginVersion;
	private VersionCheck check;
	public static int MAX_VERSION = 18;
	private static Economy ECON;
	private static Boolean VAULT;

	@Override
	public void onLoad() {
		PLUGIN = this;
		bukkitVersion = new BukkitVersion(this);
		pluginVersion = new PluginVersion(this, new Version(getDescription().getVersion(), VersionType.UNKNOWN));

		if (!getDataFolder().exists()) getDataFolder().mkdirs();

		BACKUP = new CrashBackup(PLUGIN);
		BACKUP.load();

		config = Configurations.getConfigurations();
		config.onEnable();
	}

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new EquipListener(), this);
		getServer().getPluginManager().registerEvents(new ItemEnterInventoryListener(), this);

		check = new VersionCheck(pluginVersion, "https://raw.githubusercontent.com/crashtheparty/CrashAPI/master/VersionHistory", "https://www.spigotmc.org/resources/crashapi.82229/", "https://github.com/crashtheparty/CrashAPI", config.getConfig().getBoolean("get_latest_version"), false);
		Bukkit.getPluginManager().registerEvents(check, this);
		checkVersion();
		Bukkit.getScheduler().runTaskLater(this, () -> {
			addCompatibility();
		}, 1l);
	}

	private void checkVersion() {
		Bukkit.getScheduler().runTaskTimerAsynchronously(this, check, 20l, 20 * 60 * 60 * 4l);
	}

	public static CrashAPI getPlugin() {
		return PLUGIN;
	}

	public BukkitVersion getBukkitVersion() {
		return bukkitVersion;
	}

	@Override
	public PluginVersion getPluginVersion() {
		return pluginVersion;
	}

	@Override
	public String getStarter() {
		return getLanguageFile().getString("starter");
	}

	@Override
	public ChatUtils getChat() {
		return ChatUtils.getUtils(PLUGIN);
	}

	@Override
	public ItemSerialization getItemSerial() {
		return ItemSerialization.getItemSerial(PLUGIN);
	}

	@Override
	public Configurations getConfigurations() {
		return config;
	}

	@Override
	public YamlConfig getLanguageFile() {
		return config.getLanguage().getConfig();
	}

	public BackupDB getBackupDB() {
		return BACKUP;
	}

	public static boolean hasEconomy() {
		if (VAULT == null) {
			if (!Bukkit.getPluginManager().isPluginEnabled("Vault")) return false;
			RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
			if (rsp == null) return false;
			ECON = rsp.getProvider();
			VAULT = ECON != null;
		}
		return VAULT;
	}

	public static Economy getEconomy() {
		return ECON;
	}

}
