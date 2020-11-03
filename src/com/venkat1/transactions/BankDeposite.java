package com.venkat1.transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BankDeposite {

	
	//private String aadhar="";  // Account number divede into 3 parts ---
	private String accNo="";	 // This equlas to aadhar Number  ------
	private String hName="";
	private String hMail="";
	private String hPass="";
	private String DOB="";		// DD-MM - YY format only 
	private int bal=000;		// MIN =500 Rs/-
	private String hAdd="";		// State , Distic, Town , PIN code 
	private String hPhone="";
	
	private String dAcc="";// deposite Account 
		
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

	
	public void accDeposite()
	{
		System.out.println("-------- Here I want to perform Deposite Transaction -------------");
		System.out.print("Enter Account Number :");
		dAcc=s.next();
		System.out.println(" I neeed Get the account Details from database \n_______________________________________-");
		try {Thread.sleep(1000);} catch (InterruptedException e) {	e.printStackTrace();}
		getAccDet();
		
		
		
		
		
	}
	private void getAccDet()
	{
		
		String sql="select * from vbank where accNo='"+dAcc+"'";
		
		System.out.println("Here I'm getting the details from database\n_____________________________________");
		try {Class.forName(dbClass);System.out.println("DB Class is connected ");} catch (ClassNotFoundException e1) {e1.printStackTrace();}
		try {
			
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
			System.out.println("Please Check details Before Deposite  in Account  ");
			System.out.println("Account Holder  Details \n_________________________________________________\n");
			System.out.println("Name            : "+hName);
			System.out.println("Address         : "+hAdd);
			System.out.println("Phone Number    : "+hPhone);
			System.out.println("Address         : "+hAdd);
			System.out.println("Date Of Birth   : "+DOB);
			System.out.println("Mail Id         : "+hMail);
			System.out.println("Account Number  : "+accNo);
			System.out.println("Account ballence: "+bal);
			//System.out.println("AADHAR Number   : "+accNo);
			System.out.println("________________________________________________________");
			
			System.out.print("Enter Ammount to deposite :");
			int dAmount=s.nextInt();
			System.out.println();
			int dabal=bal+dAmount;
			System.out.println("Update these details in account database");
			System.out.print("enter you're Option { yes / no } : ");
			String kk=s.next().trim();
			System.out.println();
			if(kk.equalsIgnoreCase("yes"))
			{
				String upquery="update  vbank set bal='"+dabal+"' where accno='"+dAcc+"'";
				
				st=con.createStatement();
				rs=st.executeQuery(upquery);
				
				System.out.println("Row Is Updated Sucessfully ");
				System.out.println(dAmount+"\t Amount is deposited to \t"+dAcc+"\t Sucessfully ");
			
			}// if block 
				else {
					System.out.println("Any problem is Occured ");
					System.out.println("Do you want to Exit from these details ( yes / no ) ");
					String gk=s.next().trim();
					if(gk.equalsIgnoreCase("yes"))
					{ System.out.println("Exit from this Oparation");
					//	Thread.sleep(2000);
					System.exit(0);
					}// if block 
					else {System.out.println("Do something ");}
			
			
			} // else -------------------
		System.out.println(" After Deposite Amount to youre Account \n______________________________________");
		
		String sqlres="select accno,bal from vbank where accno='"+dAcc+"'";
		pst=con.prepareStatement(sqlres);
		rs=pst.executeQuery();
		while(rs.next())
		{
			this.accNo=rs.getString("accno");
			this.bal=rs.getInt("bal");
		}// while Loop 
		System.out.println("Account Number : "+accNo+ "\n Main ballence : "+bal );
	System.out.println("________________________________________");
		} catch (SQLException e) 
			{
			System.out.println("Some Error is Occured To connect Database ");
			e.printStackTrace();
			}
		
		try {rs.close();pst.close();con.close();System.out.println("DB Resources are closed ");
		} catch (SQLException e) {	e.printStackTrace();
		
		}
		
	}

}
