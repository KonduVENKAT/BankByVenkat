package com.venkat.mainThings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.venkat.utility.DBUtility;

public class BankNewAccount {
	DBUtility dbu=new DBUtility();
	
	
	private String aadhar="";  // Account number divede into 3 parts ---
	private String accNo="";	 // This equlas to aadhar Number  ------
	private String hName="";
	private String hMail="";
	private String hPass="";
	private String DOB="";		// DD - MM - YY format only 
	private int bal=000;		// MIN =500 Rs/-
	private String hAdd="";		// State , Distic, Town , PIN code 
	private String hPhone="";
		
	//----------------------- DB Connection things -------------
	
	String dbClass="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="venkat";
	String password="durga";
	
	
	Connection con=null;
	Statement  st=null;
	ResultSet rs=null;
	
	
	Scanner sc=new Scanner(System.in);	
	public void newAcc()
	{
	System.out.println("-------------------Youre choosing new account  creation -------------------");
		System.out.println("----------------------Helo New Account Holder  ---------------------");
		System.out.println("----------------------Enter Account Holder Details -------------------------");
		
	System.out.println("here I wantt to add AADHAR Number into 3 parts each part contains 4 digits youre AADHAR Number  ");	

	String aad[]=new String[3];
	System.out.println("Enter Youre AADHAR NUMBER :\n----------------------------------");
	for(int i=0;i<aad.length;i++)
		{ 
		System.out.print("Enter Youre AADHAR 4 digits :");
		aad[i]=dbu.set4Digit();
		aad[i].trim();
		aadhar=aadhar+aad[i]; // for complete Aadhar Number  
		accNo=accNo+aad[i];  // for Account Number -------------------------
				//---------------------------- for Account Password  ---------------------------------- 
			if(i==1)
				{   hPass=aad[i]; }
	System.out.println();
		}  // -------------  close For Loop 	
	
	
	System.out.print("Enter Account Holder Name : ");
	hName=sc.next();
	System.out.println();
	System.out.print("Enter gamil mail : ");
	hMail=sc.next();
	System.out.println();
	System.out.print("Enter phone Number : ");
	hPhone=sc.next().trim();
	System.out.println();
	
	/*
	String dob[]=new String[3];
	System.out.println("Enter DATE OF BIRTH format (DD-MM-YY) :");
	System.out.print("Enter Day : ");
	dob[0]=sc.next().trim();
	System.out.println();
	System.out.print("Enter Month : ");
	dob[1]=sc.next().trim();
	System.out.println();
	System.out.print("Enter year :");
	dob[2]=sc.next().trim();
	System.out.println();
		
	DOB=dob[0]+"-"+dob[1]+"-"+dob[2];
	*/
	System.out.println("Enter DATE OF BIRTH format (DD-MM-YY) :");
	DOB=dbu.setDate();
	
	System.out.println("Enter Initial Deposite Ammount Must Be >= 500");
	System.out.print("deposite Ammount :");
	int bal1=sc.nextInt();
		if(bal1>=0)
		{ bal=bal1;}
		else {System.out.println("You deposite below 500 Rs/-" );System.out.print("You Have One more Chance Deposite Ammount :");
			int bal2=sc.nextInt();
				if(bal2>=0)
				{ bal2=bal;}
				else 
				{System.out.println("You Deposite Again >=500 Amount So I'm clearing the abow details { System.exit()}");System.exit(0);}
				
		} // first if - else Close -----------------------
	
		
// ---------- Entering the Adddress Details ------------
		
		String add[]=new String[4];
		System.out.print("Enter State : ");
		add[0]=sc.next().trim();
		System.out.println();
		System.out.print("Enter Distic : ");
		add[1]=sc.next().trim();
		System.out.println();
		
		System.out.print("Enter Town : ");
		add[2]=sc.next().trim();
		System.out.println();
		
		System.out.print("Enter PIN code : ");
		add[3]=sc.next().trim();
		System.out.println();
		hAdd=add[0]+","+add[1]+","+add[0]+","+add[1];
		
		System.out.println("Please Check details Before Strint in Database ");
		System.out.println("Account Details \n_________________________________________________\n");
		System.out.println("You Entered Name            : "+hName);
		System.out.println("You Entered Address         : "+hAdd);
		System.out.println("You Entered Phone Number    : "+hPhone);
		System.out.println("You Entered Address         : "+hAdd);
		System.out.println("You Entered Date Of Birth   : "+DOB);
		System.out.println("You You entered Mail        : "+hMail);
		System.out.println("You Entered Account Number  : "+accNo);
		System.out.println("You Entered AADHAR Number   : "+aadhar);
		System.out.println("________________________________________________________");
		
	
	System.out.println("I Want to store These Details in database Enter ( Yes )");
	System.out.print("Enter youre option ( yes / no ) :");
	String dbT=sc.next().trim();
	if(dbT.equalsIgnoreCase("yes"))
	{	dbConn();	
	}else {
		System.out.println("You Enter Wrong option ");
	}
	
	
	}// newAcc() close thing 
	
	
	private void dbConn()
	{
	//	insert into vbank values('327199294228','20-04-2020',500,'venkat','9885861920','venkat@gmail','9929', 'AP,guntur dt,Macherla,522426');

		String sql="insert into vbank values('"+accNo+"','"+DOB+"',"+bal+",'"+hName+"','"+hPhone+"','"+hMail+"','"+hPass+"','"+hAdd+"')";
		try {Class.forName(dbClass);System.out.println("DB Driver class is Loaded ");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();	}
		
		try {
			con=DriverManager.getConnection(url, user, password);System.out.println("DB is Connected ");
			st=con.createStatement();System.out.println("SQL statement is passed ");
			rs=st.executeQuery(sql);System.out.println("Query is Exicuted ");
			System.out.println("Values are Inserted Sucessfully  in Database Row ");
			
		
		} catch (SQLException e) {	e.printStackTrace();System.out.println("Some Errors are Occured at DB Things ");}
		
		try {
			rs.close();st.close();con.close();
			System.out.println("DB resources are Closed ");
		} catch (SQLException e) {	e.printStackTrace();	}
		
	}// dbConn()  is close 
	
	
	

}// -------------------- class level close 
