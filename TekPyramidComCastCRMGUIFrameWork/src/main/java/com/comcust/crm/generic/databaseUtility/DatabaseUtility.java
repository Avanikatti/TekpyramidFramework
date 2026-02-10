package com.comcust.crm.generic.databaseUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DatabaseUtility {
	Connection conn;
	public void togetDBConnection() throws SQLException
	{
		try {
		Driver driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
		 conn = DriverManager.getConnection("jdbc:mysql://49.249.28.218:3307/ninza_hrm", "root@%", "root");
		}catch(Exception e) {
		                    }
	}
	
	public void closeDBconnection() throws SQLException
	{
		try {
		conn.close();
		}catch(Exception e) {
	                      }
		
	}
	
	public ResultSet executConSelectQuery(String query) throws SQLException
	{
		 ResultSet resultset=null;
		try {
		 Statement stat = conn.createStatement();
		 resultset = stat.executeQuery(query);
		}catch(Exception e) {
		                  }
		return resultset;
	}
		
	public int executeNonSelectQuery(String query)
	{
		int result=0;
		try {
			Statement stat = conn.createStatement();
			 result = stat.executeUpdate(query);
		}catch (Exception e) {
		          }
		return result;
	}
	
}
