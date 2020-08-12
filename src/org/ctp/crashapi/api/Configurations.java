package org.ctp.crashapi.api;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.ctp.crashapi.CrashAPI;
import org.ctp.crashapi.config.*;
import org.ctp.crashapi.db.BackupDB;

public class Configurations implements CrashConfigurations {
	
	private final static Configurations CONFIGURATIONS = new Configurations();
	private MainConfiguration CONFIG;
	private LanguageConfiguration LANGUAGE;

	private List<APILanguageFile> LANGUAGE_FILES = new ArrayList<APILanguageFile>();

	public static Configurations getConfigurations() {
		return CONFIGURATIONS;
	}
	@Override
	public void onEnable() {
		File dataFolder = CrashAPI.getPlugin().getDataFolder();

		try {
			if (!dataFolder.exists()) dataFolder.mkdirs();
		} catch (final Exception e) {
			e.printStackTrace();
		}

		BackupDB db = CrashAPI.getPlugin().getBackupDB();
		CONFIG = new MainConfiguration(dataFolder, db);
		
		String languageFile = CONFIG.getString("language_file");
		Language lang = Language.getLanguage(CONFIG.getString("language"));
		if (!lang.getLocale().equals(CONFIG.getString("language"))) CONFIG.updatePath("language", lang.getLocale());

		File languages = new File(dataFolder + "/language");

		if (!languages.exists()) languages.mkdirs();

		LANGUAGE_FILES.add(new APILanguageFile(dataFolder, Language.US));

		for(CrashLanguageFile file: LANGUAGE_FILES)
			if (file.getLanguage() == lang) LANGUAGE = new LanguageConfiguration(dataFolder, languageFile, file, db);

		if (LANGUAGE == null) LANGUAGE = new LanguageConfiguration(dataFolder, languageFile, LANGUAGE_FILES.get(0), db);

		save();
	}

	public void revert() {
		CONFIG.revert();
		LANGUAGE.revert();
	}

	public void revert(Configuration config, int backup) {
		config.revert(backup);
	}

	@Override
	public void save() {
		CONFIG.setComments(CONFIG.getBoolean("use_comments"));
		LANGUAGE.setComments(CONFIG.getBoolean("use_comments"));

		CONFIG.save();
		LANGUAGE.save();
	}

	public void reload() {
		CONFIG.reload();
		LANGUAGE.reload();

		save();
	}

	public MainConfiguration getConfig() {
		return CONFIG;
	}

	public LanguageConfiguration getLanguage() {
		return LANGUAGE;
	}

	public List<APILanguageFile> getLanguageFiles() {
		return LANGUAGE_FILES;
	}

}
