package org.ctp.crashapi.api;

import java.io.File;

import org.ctp.crashapi.config.CrashLanguageFile;
import org.ctp.crashapi.config.Language;
import org.ctp.crashapi.config.yaml.YamlConfig;
import org.ctp.crashapi.utils.CrashConfigUtils;

public class APILanguageFile extends CrashLanguageFile {

	public APILanguageFile(File dataFolder, Language language) {
		super(dataFolder, language);

		YamlConfig config = getConfig();

		File tempFile = CrashConfigUtils.getTempFile(getClass(), "/resources/" + language.getLocale() + ".yml");
		YamlConfig defaultConfig = new YamlConfig(tempFile, new String[] {});
		defaultConfig.getFromConfig();
		for(String str: defaultConfig.getAllEntryKeys())
			if (defaultConfig.get(str) != null) if (str.startsWith("config_comments.")) config.addComments(str, defaultConfig.getStringList(str).toArray(new String[] {}));
			else
				config.addDefault(str, defaultConfig.get(str));
	}

}
