package org.ctp.crashapi.db;

import java.sql.*;
import java.util.logging.Level;

import org.ctp.crashapi.CrashAPIPlugin;

abstract class Database {
	CrashAPIPlugin plugin;
	Connection connection;

	Database(CrashAPIPlugin instance) {
		plugin = instance;
	}

	public CrashAPIPlugin getInstance() {
		return plugin;
	}

	public abstract Connection getSQLConnection();

	public abstract void load();

	protected void initialize() {
		initialize(null);
	}

	protected void initialize(String name) {
		connection = getSQLConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + (name == null ? getDBName() : name));
			ResultSet rs = ps.executeQuery();
			ps.close();
			rs.close();

		} catch (SQLException ex) {
			plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
		}
	}

	protected abstract String getDBName();
}