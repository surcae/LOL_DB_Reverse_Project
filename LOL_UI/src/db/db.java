package db;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import Background.DBManager;
import javafx.fxml.Initializable;

public class db implements Initializable{
	private Connection conn = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	public boolean Execute(String arg) {
		try {
			if(statement.execute(arg))
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public void ExecuteUpdate(String arg) {
		try {
			statement.executeUpdate(arg);
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public ResultSet ExecuteQuery(String arg) {
		try {
			resultSet = statement.executeQuery(arg);
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
	
	
	
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	
	public void disconnect() {
		if(conn!=null) try { conn.close();} catch(SQLException se) {}
		if(statement!=null) try { statement.close();} catch(SQLException se) {}
		if(resultSet!=null) try { resultSet.close();} catch(SQLException se) {}
	}
	public void connect(String id, String pw) {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:4306/?autoReconnect=true&useSSL=false", id, pw);
			System.out.println("DB 접속 성공");
			
			DBManager.getDBManager().setID(id);
			DBManager.getDBManager().setPW(pw);
			
			statement = conn.createStatement();
			resultSet = statement.executeQuery("SHOW DATABASES");
			
			int c = statement.executeUpdate("CREATE DATABASE LOL_DB");
		      System.out.println("Table have been created.");
		      System.out.println(c+" Row(s) have been affected");

			if (statement.execute("SHOW DATABASES")) {

				resultSet = statement.getResultSet();

			}
			while (resultSet.next()) {
				String str = resultSet.getNString(1);
				System.out.println(str);
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		finally {
			//if(conn!=null) try { conn.close();} catch(SQLException se) {}
			//if(statement!=null) try { statement.close();} catch(SQLException se) {}
			//if(resultSet!=null) try { resultSet.close();} catch(SQLException se) {}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("드라이버 연결");
	}
}
