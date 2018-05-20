package db;
import java.sql.*;
public class db {
	String url = "jdbc:mysql://127.0.0.1/?useSSL=false&user=root&";
	private Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public void connect() {
		try {
			Class.forName("com.mysql.jdc.Driver");
			System.out.println("드라이버 연결 굿");
			
			conn = DriverManager.getConnection(url);
			System.out.println("db 접속 성공");
			
			stmt = conn.createStatement();
		}
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		finally {
			if(conn!=null) try { conn.close();} catch(SQLException se) {}
			if(stmt!=null) try { stmt.close();} catch(SQLException se) {}
			if(rs!=null) try { rs.close();} catch(SQLException se) {}
		}
	}
	
	
	
	public String geturl() {
		return url;
	}
	public void seturl(String _string) {
		url = _string;
	}
}
