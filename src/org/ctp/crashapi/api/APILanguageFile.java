package org.ctp.crashapi.api;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.ctp.crashapi.config.CrashLanguageFile;
import org.ctp.crashapi.config.Language;
import org.ctp.crashapi.config.yaml.YamlConfig;
import org.ctp.crashapi.nms.ItemNMS;

public class APILanguageFile extends CrashLanguageFile {

	private Iterator<Material> iter;
	
	public APILanguageFile(File dataFolder, Language language) {
		super(dataFolder, language);
	}
	
	public boolean setAsync() {
		YamlConfig config = getConfig();
		config.getFromConfig();
		
		if (iter == null) iter = Arrays.asList(Material.values()).iterator();

		int mat = 0;
		
		while (mat < 50 && iter.hasNext()) {
			mat++;
			Material m = iter.next();
			if (m != null) config.set("vanilla." + m.name().toLowerCase(), ItemNMS.returnLocalizedItemName(getLanguage(), new ItemStack(m)));
		}

		config.saveConfig();
		
		return !iter.hasNext();
	}

}
