package com.venkat1.DBDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConn {
	public static String dbClass="oracle.jdbc.driver.OracleDriver";
	public static String url="jdbc:oracle:thin:@localhost:1521:xe";
	public static String user="venkat";
	public static String password="durga";
	public static Connection con=null;
	public static Statement st=null;
	public static PreparedStatement pst=null;
	public static ResultSet rs=null;

	
	

}
