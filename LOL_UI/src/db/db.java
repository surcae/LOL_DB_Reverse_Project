package db;
import java.sql.*;
public class db {
	private Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public void connect() {
		try {
			System.out.println( System.getProperties());
			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("드라이버 연결 굿");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:4306/?autoReconnect=true&useSSL=false", "root", "root");
			System.out.println("db 접속 성공");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SHOW DATABASES");



			if (stmt.execute("SHOW DATABASES")) {

				rs = stmt.getResultSet();

			}
			while (rs.next()) {

				String str = rs.getNString(1);

				System.out.println(str);

			}
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
}
