package org.ctp.crashapi.version;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.ctp.crashapi.CrashAPI;
import org.ctp.crashapi.CrashAPIPlugin;
import org.ctp.crashapi.utils.ChatUtils;
import org.ctp.crashapi.utils.CrashConfigUtils;

public class BukkitVersion {

	private CrashAPIPlugin plugin;
	private String version;
	private String apiVersion;
	private boolean versionAllowed;
	private int versionNumber;

	public BukkitVersion(CrashAPIPlugin plugin) {
		this.plugin = plugin;

		version = getBukkitVersion();
		apiVersion = getBukkitApiVersion();
		versionAllowed = allowedBukkitVersion();
	}

	private String getBukkitVersion() {
		String a = Bukkit.getVersion();
		String version = a.substring(a.lastIndexOf(':') + 1, a.lastIndexOf(')')).trim();

		return version;
	}

	private String getBukkitApiVersion() {
		String a = Bukkit.getServer().getClass().getPackage().getName();
		String apiVersion = a.substring(a.lastIndexOf('.') + 1).trim();

		return apiVersion;
	}

	private Map<String, Integer> getBukkitVersionsFromFile(String file) {
		Map<String, Integer> map = new HashMap<String, Integer>();

		URL urlv;
		try {
			urlv = new URL("https://raw.githubusercontent.com/crashtheparty/CrashAPI/master/" + file);
			BufferedReader in = new BufferedReader(new InputStreamReader(urlv.openStream()));
			String line = in.readLine();
			while (line != null) {
				String[] strings = line.split(" ");
				if (strings.length > 0) map.put(strings[0], Integer.parseInt(strings[1]));
				line = in.readLine();
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (map.size() == 0) getBukkitVersionsFromLocal(file);
		return map;
	}

	private Map<String, Integer> getBukkitVersionsFromLocal(String file) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		File f = CrashConfigUtils.getTempFile(getClass(), "/" + file);
		try {
			for(String line: Files.readAllLines(f.toPath())) {
				String[] strings = line.split(" ");
				if (strings.length > 0) map.put(strings[0], Integer.parseInt(strings[1]));
			}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return map;
	}

	private boolean allowedBukkitVersion() {
		versionNumber = 0;
		ChatUtils chat = plugin.getChat();

		// BukkitVersion
		chat.sendInfo("Checking Bukkit Version: " + version);

		try {
			Map<String, Integer> versions = getBukkitVersionsFromFile("BukkitVersions");
			if (versions.get(version) != null) versionNumber = versions.get(version);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (versionNumber > CrashAPI.MAX_VERSION) chat.sendSevere("This version is not explicitly defined!");
		else if (versionNumber > 0) {
			chat.sendInfo("Found version " + version + ". Setting version number to " + versionNumber + ".");
			return true;
		} else
			chat.sendWarning("Could not find version " + version + ".");

		// BukkitApiVersion
		chat.sendInfo("Checking Bukkit API Version: " + apiVersion);

		try {
			Map<String, Integer> versions = getBukkitVersionsFromFile("BukkitAPIVersions");
			if (versions.get(apiVersion) != null) versionNumber = versions.get(apiVersion);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (versionNumber > CrashAPI.MAX_VERSION) {
			chat.sendSevere("This version is not defined! Features that require NMS have been disabled, and issues may arise with certain features. " + "Please wait for an update for this version.");
			return false;
		}
		if (versionNumber > 0) {
			chat.sendInfo("Found version " + apiVersion + ". Setting version number to " + versionNumber + ".");
			return true;
		}
		chat.sendSevere("This version is not defined! Features that require NMS have been disabled, and issues may arise with certain features. " + "Please wait for an update for this version.");
		return false;
	}

	public boolean isVersionAllowed() {
		return versionAllowed;
	}

	public int getVersionNumber() {
		return versionNumber;
	}

	public String getVersion() {
		return version;
	}

	public String getAPIVersion() {
		return apiVersion;
	}
}
