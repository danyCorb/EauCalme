package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Data.DataMain;

public class MainDAO {
	private static final MainDAO INSTANCE =new MainDAO();
	private Connection conn;
	
	public static MainDAO getInstance(){
		return INSTANCE;
    }
	
	public MainDAO(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:mysql://localhost:3306/thud";
			String user = "root";
			String passwd = "";
		try {
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getRequestvalue(String request) throws SQLException{
		Statement stmt = conn.createStatement();
	    ResultSet rs = stmt.executeQuery(request);
	    return rs;
	}
	
	public void executeQuery(String request) throws SQLException{
		ResultSet rs=getRequestvalue(request);
		rs.close();
	}
	

}
