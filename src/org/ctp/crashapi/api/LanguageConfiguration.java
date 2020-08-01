package org.ctp.crashapi.api;

import java.io.File;

import org.ctp.crashapi.CrashAPI;
import org.ctp.crashapi.config.Configuration;
import org.ctp.crashapi.config.CrashLanguageFile;
import org.ctp.crashapi.config.Language;
import org.ctp.crashapi.config.yaml.YamlConfigBackup;
import org.ctp.crashapi.db.BackupDB;

public class LanguageConfiguration extends Configuration {

	private CrashLanguageFile language;

	public LanguageConfiguration(File file, String languageFile, CrashLanguageFile language, BackupDB db) {
		super(CrashAPI.getPlugin(), new File(file + "/" + languageFile), db, new String[] {}, false);

		this.language = language;

		setDefaults();
		migrateVersion();
		save();
	}

	@Override
	public void setDefaults() {
		YamlConfigBackup config = getConfig();
		config.addDefault("starter", "&8[&dCrash API&8]");
		config.copyDefaults(language.getConfig());

		config.writeDefaults();
	}

	@Override
	public void migrateVersion() {
	}

	@Override
	public void repairConfig() {}

	public Language getLanguage() {
		return language.getLanguage();
	}

}
