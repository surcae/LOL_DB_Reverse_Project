package Background;

import db.db;

public class DBManager {
	// 싱글톤
	private static DBManager dbManager = null;
	private DBManager() {
		Database = new db();
	}
	
	public static DBManager getDBManager() {
		if(dbManager==null) {
			dbManager = new DBManager();
		}
		return dbManager;
	}
	
	// 매개변수와 메소드들...
	private db Database;
	
	public void connectToDB() {
		Database.connect();
	}
}
