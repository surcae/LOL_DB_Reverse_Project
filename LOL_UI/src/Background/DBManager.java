package Background;

import java.sql.ResultSet;
import java.sql.SQLException;
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
	private String ID, PW;
	public void connectToDB(String string, String string2) {
		try {
			Database.connect(string, string2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public Boolean Execute(String arg) {
		// TODO: 쿼리문을 직접 수행할 수 있는 메소드다. True or False 반환
		return Database.Execute(arg);
	}
	
	public ResultSet ExecuteQuery(String arg) {
		// TODO: 보통 Select 할 때 많이 쓰고 ResultSet 을 리턴해준다.
		return Database.ExecuteQuery(arg);
	}
	
	public int ExecuteUpdate(String arg) {
		// TODO: Insert, update, delete 등 기타 Create, drop 등 여러 DML SELECT 제외를 쓸 때 많이 쓴다.
		return Database.ExecuteUpdate(arg);
	}
}
