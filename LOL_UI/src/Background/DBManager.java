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
	private String ID, PW;
	public void connectToDB(String string, String string2) {
		Database.connect(string, string2);
	}
	public void disconnectfromDB() {
		Database.disconnect();
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPW() {
		return PW;
	}

	public void setPW(String pW) {
		PW = pW;
	}
}
