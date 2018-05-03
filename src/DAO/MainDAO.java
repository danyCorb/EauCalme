package DAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
		try {
			BufferedReader br=new BufferedReader(new FileReader("bdConfig.txt"));
			String url=br.readLine();
			//String url = "jdbc:mysql://localhost:3306/thud";
			String user = "thud";
			String passwd = " ";
			br.close();
			try {
				conn = DriverManager.getConnection(url, user, passwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("Fichier de configuration de la DB!");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
	public ResultSet getRequestvalue(String request) throws SQLException{
		Statement stmt = conn.createStatement();
	    ResultSet rs = stmt.executeQuery(request);
	    return rs;
	}
	
	public void executeQuery(String request) throws SQLException{
		Statement stmt = conn.createStatement();
		stmt.execute(request);
		stmt.close();
	}
	
	
	public void insert(String table, String champ[][]){
		String rqt="INSERT INTO "+table+"( ";
		
		for(int j=0;j<champ.length;j++){
			rqt+=champ[j][0];
			if(j!=champ.length-1){
				rqt+=" , ";
			}
		}
		rqt+=" ) VALUES (";
		for(int j=0;j<champ.length;j++){
			rqt+="'"+champ[j][1]+"'";
			if(j!=champ.length-1){
				rqt+=" , ";
			}
		}
		rqt+=" );";

		try {
			//System.out.println("Send RQT : "+rqt);
			executeQuery(rqt);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("RQT Excepetion "+rqt);
		}
	}
	

}
