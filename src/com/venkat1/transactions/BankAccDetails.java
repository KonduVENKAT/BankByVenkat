package com.venkat1.transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BankAccDetails {
	

	private String aadhar="";  
	private String accNo="";	
	private String hName="";
	private String hMail="";
	private String hPass="";
	private String DOB="";	 
	private int bal=000;	
	private String hAdd="";	 
	private String hPhone="";
		
	//----------------------- DB Connection things -------------
	
	String dbClass="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="venkat";
	String password="durga";
	
	
	Connection con=null;
	Statement  st=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
//--------------------------------------------------------------------	
	Scanner s=new Scanner(System.in);	

	public void accDetails() {
		System.out.println("----------------------- Here I want to get the Account Full detils ------------------");
		System.out.print("Enter Account Number : ");
		String gAcc=s.next().trim();
		System.out.println();
String sql="select * from vbank where accNo='"+gAcc+"'";
System.out.print("Enter Account holder 4 Digit Password : ");
String gPass=s.next().trim();
		
		System.out.println("Here I'm getting the details from database\n_____________________________________");
		//try {Class.forName(dbClass);System.out.println("DB Class is connected ");} catch (ClassNotFoundException e1) {e1.printStackTrace();}
		
		try {
			try {Class.forName(dbClass);} catch (ClassNotFoundException e) {	e.printStackTrace();	}
			System.out.println("DB Class is connected ");
			con=DriverManager.getConnection(url, user, password);
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next())
			{
////	insert into vbank values('327199294228','20-04-2020',500,'venkat','9885861920','venkat@gmail','9929', 'AP,guntur dt,Macherla,522426');
				
//	create table vbank(accno ,cdate , bal ,hname ,hphone ,hmail ,hpass ,hadd );
		
			this.accNo=rs.getString("accno");
			this.hName=rs.getString("hname");
			this.hMail=rs.getString("hmail");
			this.hPass=rs.getString("hpass");
			this.DOB=rs.getString("cdate");
			this.bal=rs.getInt("bal");
			this.hAdd= rs.getString("hadd");
			this.hPhone=rs.getString("hphone");
			}	
			
			if(gPass.equalsIgnoreCase(hPass))
			{
			System.out.println("You Enter Account number \t"+accNo+"\t are ");
		System.out.println("Account Holder  Details \n_________________________________________________\n");
			System.out.println("Name            : "+hName);
			System.out.println("Address         : "+hAdd);
			System.out.println("Phone Number    : "+hPhone);
			System.out.println("Address         : "+hAdd);
			System.out.println("Date Of Birth   : "+DOB);
			System.out.println("Mail Id         : "+hMail);
			System.out.println("Mail Password   : "+hPass);
			System.out.println("Account Number  : "+accNo);
			System.out.println("Account ballence: "+bal);
			System.out.println("AADHAR Number   : "+accNo);
			System.out.println("________________________________________________________");
		
			} // if Block 
			else
			{
				System.out.println("You Entered Wrong Password Number Please Check Once Again ");
				
			}
		}catch(SQLException e)	{e.printStackTrace();		}
		
		try {rs.close();pst.close();con.close();System.out.println("DB Resources are closed ");
		} catch (SQLException e) {	e.printStackTrace();	}
	} //accDetails() method Block -----------------------
	

}
