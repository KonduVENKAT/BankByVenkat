package com.venkat1.transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BankUpdateAccDet {
	Scanner s=new Scanner(System.in);	
	

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


public void updateAcc()
{
System.out.print("Enter Account Number To Update Details :");	
String upAcc=s.next().trim();
System.out.println();

String sql="select * from vbank where accno='"+upAcc+"'";


System.out.print("Enter Account 4 digit Password For Confermation Account  ");
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
////insert into vbank values('327199294228','20-04-2020',500,'venkat','9885861920','venkat@gmail','9929', 'AP,guntur dt,Macherla,522426');
		
//create table vbank(accno ,cdate , bal ,hname ,hphone ,hmail ,hpass ,hadd );

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
	
	
	// table vbank(accno ,cdate , bal ,hname ,hphone ,hmail ,hpass ,hadd );
	System.out.println("Which Record value  Do you want to Update  or Modify");
 System.out.println("\n Name of the Account Holder                : ( -- hname --)");
	System.out.println("Address of the Account Holder             : ( -- hadd  --)");
	System.out.println("Mobile Number of the Account Holder       : ( -- hphone --)");
	System.out.println("4 digit of the Account Password           : ( -- hpass --)");
	System.out.println("Data of birth of the Account Holder       : ( -- cdate --) ");
	System.out.println("Mail id ( --gmail-- )of the Account Holder: (  --hmail --) ");
	


	System.out.print("Enter How Many options are Do you want to Update ");
	int op=s.nextInt();
	System.out.println();
	String ops[]=new  String[op];
	
	for(int i=0;i<ops.length;i++)
	{
	System.out.print("Enter Youre  "+(i+1)+"\t Options : ");
	ops[i]=s.next().trim();
	System.out.println();
	}
	System.out.println("Enter Values of the Respective Options ");
	String opsVa[]=new String[op];
	for(int i=0;i<opsVa.length;i++)
	{   
		System.out.print("Enter Value of "+ops[i]+" : ");
		opsVa[i]=s.next().trim();
		System.out.println();
		
	}
	System.out.println();
	String upsql="update vbank set ";
	for(int i=0;i<ops.length;i++)
	{			
		upsql=upsql+ ops[i]+"='"+opsVa[i]+"'";
		System.out.println(upsql);
			
		if(i==ops.length-1)
		{upsql=upsql+" ";}
		else{upsql=upsql+", ";}
	
	} // for loop for SQl statement 
		upsql=upsql+" where accno='"+upAcc+"'";
		System.out.println(upsql);
		st=con.createStatement();
		int j=st.executeUpdate(upsql);
		System.out.println("  "+j+" Rows are Updated Sucessfully ");
		System.out.println("Account Number \t"+accNo+"\tWith details Like ");
		for(String st:ops)
		{System.out.print(st+"\t");}
		System.out.println("\n-------------------------------------------------------------------");
		for(String st:opsVa)
		{System.out.print(st+"\t");}
		System.out.println("\n-------------------------------------------------------------------");
	
		
}catch(SQLException e)	{e.printStackTrace();		}

try {rs.close();pst.close();con.close();System.out.println("DB Resources are closed ");
} catch (SQLException e) {	e.printStackTrace();	}

}



}
