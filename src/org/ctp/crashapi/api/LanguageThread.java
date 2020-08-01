package org.ctp.crashapi.api;

import org.bukkit.Bukkit;
import org.ctp.crashapi.CrashAPI;
import org.ctp.crashapi.utils.ChatUtils;

public class LanguageThread implements Runnable {

	private final APILanguageFile file;
	private int scheduler;
	
	public LanguageThread(APILanguageFile file) {
		this.file = file;
	}
	
	@Override
	public void run() {
		if (file.setAsync()) {
			ChatUtils.getUtils(CrashAPI.getPlugin()).sendInfo("Language file " + file.getLanguage().getLocale() + " fully loaded!");
			Bukkit.getScheduler().cancelTask(scheduler);
			Configurations.getConfigurations().save();
		}
	}

	public int getScheduler() {
		return scheduler;
	}

	public void setScheduler(int scheduler) {
		this.scheduler = scheduler;
	}

}
