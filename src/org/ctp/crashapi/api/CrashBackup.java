package org.ctp.crashapi.api;

import org.ctp.crashapi.CrashAPIPlugin;
import org.ctp.crashapi.db.BackupDB;

public class CrashBackup extends BackupDB {

	public CrashBackup(CrashAPIPlugin instance) {
		super(instance, "backups");
	}

}
