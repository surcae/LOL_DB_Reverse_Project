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
	public int ExecuteUpdate(String arg) {
		try {
			return statement.executeUpdate(arg);
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
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
	public void connect(String id, String pw) throws SQLException {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false", id, pw);
			System.out.println("DB 접속 성공");
			
			DBManager.getDBManager().setID(id);
			DBManager.getDBManager().setPW(pw);
			
			statement = conn.createStatement();
			
			//resultSet = statement.executeQuery("SELECT * FROM SYSCAT.TABLES WHERE TABSCHEMA='LOL_DB");
			
			statement.executeUpdate("CREATE DATABASE LOL_DB");
			System.out.println("Database have been created!");
		}
		catch(SQLException se) {
			if (se.getErrorCode() == 1007) {
		        // Database already exists error
		        System.out.println(se.getMessage());
		        System.out.println("이미 존재하니 넘어갑니다.");
		    } else {
		        // Some other problems, e.g. Server down, no permission, etc
		    	se.printStackTrace();
		    }
		}
		finally {
			statement.execute("USE LOL_DB");
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
