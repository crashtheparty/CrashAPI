package org.ctp.crashapi.config;

import java.io.File;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.ctp.crashapi.CrashAPIPlugin;
import org.ctp.crashapi.config.yaml.YamlConfigBackup;
import org.ctp.crashapi.db.BackupDB;
import org.ctp.crashapi.db.DBUtils;
import org.ctp.crashapi.utils.ChatUtils;

public abstract class Configuration implements Configurable, Revertable {

	private File file;
	private YamlConfigBackup config;
	private BackupDB backup;
	private boolean comments = true;
	private final CrashAPIPlugin plugin;

	public Configuration(CrashAPIPlugin plugin, File file, BackupDB backup, String[] header) {
		this.plugin = plugin;
		this.file = file;
		this.backup = backup;
		try {
			YamlConfiguration.loadConfiguration(file);

			config = new YamlConfigBackup(file, header);
			config.getFromConfig();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public File getFile() {
		return file;
	}

	@Override
	public YamlConfigBackup getConfig() {
		return config;
	}

	@Override
	public abstract void setDefaults();

	@Override
	public abstract void migrateVersion();

	@Override
	public void save() {
		setDefaults();
		config.setComments(comments);
		config.saveConfig();

		DBUtils.updateConfig(backup, config);
	}

	@Override
	public void revert() {
		config.revert();
	}

	@Override
	public void revert(int backupNum) {
		config.revert();

		String info = DBUtils.getBackup(backup, config, backupNum);
		config.setFromBackup(info);

		save();
	}

	@Override
	public void reload() {
		config.revert();
		config.getFromConfig();
		save();
	}

	@Override
	public void setComments(boolean comments) {
		this.comments = comments;
	}

	@Override
	public String getString(String s) {
		return config.getString(s);
	}

	@Override
	public int getInt(String s) {
		return config.getInt(s);
	}

	@Override
	public boolean getBoolean(String s) {
		return config.getBoolean(s);
	}

	@Override
	public double getDouble(String s) {
		return config.getDouble(s);
	}

	@Override
	public void updatePath(String s, Object value) {
		config.set(s, value);
	}

	@Override
	public List<String> getStringList(String s) {
		return config.getStringList(s);
	}

	public BackupDB getBackup() {
		return backup;
	}

	@Override
	public CrashAPIPlugin getPlugin() {
		return plugin;
	}

	@Override
	public ChatUtils getChat() {
		return ChatUtils.getUtils(plugin);
	}

	@Override
	public void repairConfig() {}
}
