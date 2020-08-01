package org.ctp.crashapi.api;

import java.io.File;

import org.ctp.crashapi.CrashAPI;
import org.ctp.crashapi.config.Configuration;
import org.ctp.crashapi.config.Language;
import org.ctp.crashapi.config.yaml.YamlConfigBackup;
import org.ctp.crashapi.db.BackupDB;

public class MainConfiguration extends Configuration {

	public MainConfiguration(File dataFolder, BackupDB db) {
		super(CrashAPI.getPlugin(), new File(dataFolder + "/config.yml"), db, new String[] {});

		migrateVersion();
		if (getConfig() != null) getConfig().writeDefaults();
	}

	@Override
	public void setDefaults() {
		if (getPlugin().isInitializing()) getPlugin().sendInfo("Loading main configuration...");

		YamlConfigBackup config = getConfig();

		config.addDefault("use_comments", true);
		config.addDefault("get_latest_version", true);
		config.addDefault("language", Language.US.getLocale());
		config.addDefault("language_file", "language.yml");
		config.addEnum("language", Language.getValues());

		if (getPlugin().isInitializing()) getPlugin().sendInfo("Main configuration initialized!");

		config.saveConfig();
	}

	@Override
	public void migrateVersion() {}

}
