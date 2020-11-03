package com.venkat.utility;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBUtility {
	public static String url="jdbc:oracle:thin:@localhost:1521:XE";
	public static String user="venkat";
	public static String password="durga";
	public static String dbClass="oracle.jdbc.driver.OracleDriver";	
	//public static Connection conDb=null;
	//public static Statement stDb=null;
	//public static PreparedStatement pstDb=null;
	//public static ResultSet rsDb=null;
public static Scanner sc=new Scanner(System.in);
	
	
	public static void  getAccDet(String accNo)
	{
		String aadhar="";  		String accNu="";	String hName="";
		String hMail="";		String hPass="";	String DOB="";	 
		int bal=000;			String hAdd="";		String hPhone="";

		String sql="select * from vbank where accno='"+accNo+"'";
		Connection con=conDb();
		ResultSet rs = rest(sql,con);
		

	try {	while(rs.next())
				{
			accNu=rs.getString("accno");		hName=rs.getString("hname");
			hMail=rs.getString("hmail");		hPass=rs.getString("hpass");
			DOB=rs.getString("cdate");			bal=rs.getInt("bal");
			hAdd= rs.getString("hadd");			hPhone=rs.getString("hphone");
						
				}
		
		System.out.println("You Enter Account number \t"+accNo+"\t are ");
		System.out.println("Account Holder  Details \n_________________________________________________\n");
System.out.println("Name            : "+hName);		System.out.println("Address         : "+hAdd);
System.out.println("Phone Number    : "+hPhone);	System.out.println("Address         : "+hAdd);
System.out.println("Date Of Birth   : "+DOB);		System.out.println("Mail Id         : "+hMail);
System.out.println("Mail Password   : "+hPass);		System.out.println("Account Number  : "+accNu);
System.out.println("Account ballence: "+bal);		System.out.println("AADHAR Number   : "+accNu);
System.out.println("________________________________________________________");

		} catch (SQLException e) {e.printStackTrace();	}	
	colseResources(rs,con);
	
	}
	// ---  Get SingleAccount Details from vbank Database Table ------------
	
	
	//-----  Get All Account Numbers With all details  --------------------
	
	public static void getAllAccDet(String tname)
	{
		String sql="select * from "+tname+"";
	Connection con=conDb();
		
	ResultSet rs=rest(sql,con);
		
System.out.println("AccNumber   holder Name     MailId     password       Created Date               Address               Phone Number   MainBallence  ");
System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
		try {
				while(rs.next())
				{
System.out.println(rs.getString("accno")+"\t"+rs.getString("hname")+"\t"+rs.getString("hmail")+"\t"+rs.getString("hpass")+"\t"+rs.getString("cdate")+"\t"+rs.getString("hadd")+"\t"+rs.getString("hphone")+"\t"+rs.getString("bal")+"\t");				
				}

System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
		} catch (SQLException e) {
				e.printStackTrace();	}
		
		colseResources(rs,con);
		
	}
	
	// ------------- GetAllAccDet() close ------------------
	//----------------- Connection conDb() Start -----------
	
	public static Connection conDb() {
		 try {Class.forName(dbClass);System.out.println("Db Class is loaded ");
		 	} catch (ClassNotFoundException e) {e.printStackTrace();	}
		 
		Connection conDb = null;
		 
		try {conDb = DriverManager.getConnection(url, user, password);System.out.println("DB is Connected ");
				return conDb;}	catch (SQLException e) {e.printStackTrace(); return conDb=null;}
		 
	}
	
	//-----------------  Connection conDb()  Close -----------
	
	//----------------- PreparedStatement pstDb() Close -----------
	/* 
	 public static PreparedStatement pstDb(String sql){
		 PreparedStatement pst=null;
			
		// Connection pstCon=conDb();
		 
		 try {
			pst=pstCon.prepareStatement(sql); System.out.println("Query is passed To database ");return pst;
		} catch (SQLException e) {	e.printStackTrace();return pst=null;		}
			 	 
	 }
	 */
	 //----------------- PreparedStatement pstDb() Close -----------
	 
	 
	 // ------------------- Statement stDb() Start  -----------
	 
	 public static void stDb(String sql)
	 {
		 Statement st=null;
		 Connection stCon=conDb();
		 
		 try {
			st=stCon.createStatement();/* System.out.println("Statement is passed to database  ");*/
			int i=st.executeUpdate(sql);System.out.println("\t "+i+" \t  Row Is Updated In database " );
			
			st.close();stCon.close();	 System.out.println("---- Database  Resources are Closed ");
		} catch (SQLException e) {		e.printStackTrace();		}
	
	
		 
		 
	 }
	 
	// ------------------- Statement stDb() close  -----------
	
	 // ------------- ResultSet rest() Start --------------------------------
	 public static ResultSet rest(String sql,Connection con)
	 {
		 Connection conDb1=null;
		 ResultSet rsDb=null;
		 PreparedStatement pstRs=null;
		 try {
			 conDb1=con;
		  pstRs=conDb1.prepareStatement(sql);
					 
		  rsDb=pstRs.executeQuery(); System.out.println("Query is Exicuted");	 return rsDb;
				} catch (SQLException e) {	e.printStackTrace(); return rsDb=null;	}
		
	
		//rsDb.close();pstRs.close();pstRs.close(); System.out.println(" ---------- Database Resources are Closed ----------- ");
			
			 
	 }
	 public static void colseResources(ResultSet rs,Connection con)
	 {
		 
		 try {
			rs.close();con.close();System.out.println("DB resources aare closed ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	
	// ------------- ResultSet rest()  Close -------------------------------------------
	 
	 //------------- Normal Account Set the Predefined Details -----------------------------
	 
	 
	 //  -------------------------  Date() ----------------
	 public static String setDate()
	 {
		 
		 String date="";
		 
		 System.out.println("Enter Date Format Like DD-MM-YY is Accpted Date Format ");
		 System.out.print("Enter Day  ( 1 < day < 31 )=");
		 int day=sc.nextInt();
		 	if(day>0&day<31)
		 	{ System.out.println("\n You Entered Valid Day ");
		 		date=""+day;
		 		System.out.println("Day Added to Date() Sucessfully \t="+day);
		 	}else
		 	{ System.out.println("\nYou Entered Invalid day ");
		 	System.out.print("Please Enter VAlid Day Again :");
		 	int day1=sc.nextInt();
		 		if(day1>0&day1<=31)
		 			{ System.out.println("\n You Entered Valid Day ");
		 			date=""+day1;

			 		System.out.println("Day Added to Date() Sucessfully \t="+day1);
		 			}else
		 			{ System.out.println("\nYou Entered Invalid day ");
		 			System.exit(0);
		 			}
		 	
		 	}
		 
		 System.out.println();
		 System.out.print("Enter Month ( 1 < month < = 12 ) ");
		 int month=sc.nextInt();
		 System.out.println();
		 	if(month>0&month<=12)
		 	{ 
		 		System.out.println("You Entered Valid Month ");
		 		date=date+"-"+month;
		 		System.out.println("Mont Added to date() Sucessfully \t="+month);
		 		
		 	}else
		 	{
		 		System.out.print("You Entered InValid month \n Pleaase enter VAlid Value between ( month >= 1 AND month<=12 ) ");
		 		int month1=sc.nextInt();
		 		System.out.println();
			 	if(month1>0&month1<=12)
			 	{ 
			 		System.out.println("You Entered Valid Month ");
			 		date=date+"-"+month1;
			 		System.out.println("Mont Added to date() Sucessfully \t="+month1);
			 		
			 	}else
			 	{
			 		System.out.print("You Entered InValid month Again ");
			 		System.exit(0);
			 	}
		 	}// if- else 
		 	
		 	System.out.print("Enter Year between 1920 < = Year <= 2020 only =");
		 	int year=sc.nextInt();
		 	System.out.println();
		 	if(year<=2020&year>=1920)
		 	{
		 		System.out.println("You entered valid Year ");
		 		date=date+"-"+year;
		 		System.out.println("Year added to Date() Sucessfully \t="+year);
		 	}else
		 	{
		 	System.out.println("You Enter InVAlid Year Please Enter Again VAlid ( 1920 <= Year <=2020 ) ");
		 	int year1=sc.nextInt();
		 	System.out.println();
		 	if(year1<=2020&year1>=1920)
		 	{
		 		System.out.println("You entered valid Year ");
		 		date=date+"-"+year1;
		 		System.out.println("Year added to Date() Sucessfully \t="+year1);
		 	}else
		 	{
		 	System.out.println("You Enter InVAlid Year Again ");
		 	System.exit(0);
		 	}
		 	}// if-else 
		 	
		 	System.out.println(" Return this Date() ="+date);
		 
			return date;
		 
	 }
	 
	 
	 // ---------------------------------- 4 digit Valid --------------
	 
	 public static String set4Digit()
	 {
		 String digit4="";
			
		 System.out.println("--------  Here I want to perform 4 digit opertion --------- ");
		 

		 System.out.println("Enter 4 digit ( Numbers only ( 0 - 9 ) : ");
		// digit4=sc.next().trim();
		 
		 
		 int[] di=new int[4];
		
			
		 for(int i=0;i<di.length;i++)
		 {
			 System.out.print("\t"+(i+1)+"\t Enter Single Digit only :");
			 di[i]=sc.nextInt();
			 System.out.println();
					 
			 String lenin=""+di[i];
			 
			 char[] lenInt=lenin.toCharArray();
			  int k=lenInt.length;
			  if(k>1)
			  	{
				  System.out.println("You're Not Entered Single Digit ");
				  System.out.println(di[i]+"\t This Number Is Not Allowed  ");
				  System.out.println("So clean All this opration ");
				  System.exit(0);
			  	}
			  }
			
		 for(int i:di)
			 System.out.print(i+"\t");
		 
		 for(int i=0;i<di.length;i++)
		 {
			 digit4=digit4+di[i];
			 
		 }
		 System.out.println();
		 System.out.println("Return this digit 4 = "+digit4);
		 return digit4;
		 
	 }
	 
	 
}
