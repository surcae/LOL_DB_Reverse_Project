package Background;

import db.db;

public class DBManager {
	// �̱���
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
	
	// �Ű������� �޼ҵ��...
	private db Database;
	
	public void connectToDB() {
		Database.connect();
	}
}
