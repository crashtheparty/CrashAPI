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
		save();
	}

	@Override
	public void setDefaults() {
		if (Configurations.isInitializing()) getPlugin().sendInfo("Initializing main configuration...");

		YamlConfigBackup config = getConfig();

		config.addDefault("use_comments", true);
		config.addDefault("get_latest_version", true);
		config.addDefault("language", Language.US.getLocale());
		config.addDefault("language_file", "language.yml");
		config.addEnum("language", Language.getValues());

		config.writeDefaults();

		if (Configurations.isInitializing()) getPlugin().sendInfo("Main configuration initialized!");
	}

	@Override
	public void migrateVersion() {}

}
