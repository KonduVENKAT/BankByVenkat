package com.venkat1.transactions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.venkat.utility.DBUtility;

public class BankWithdraw {
	
	DBUtility du=new DBUtility();
	Scanner s=new Scanner(System.in);
	
	
public void withdraw() 
{
	
	String accNo="";
	String name="";
	String accPass="";
	int mainBal=000;
		System.out.println("------------------ Here I want to perform WithDrawal Transaction --------------");
System.out.print("Enter Account number to withDraw from : ");
String wiAcc=s.next().trim();
System.out.println();
//System.out.print("Enter Withdrawal Amount : ");
//int wiAmount=s.nextInt();
System.out.println();

System.out.print("Enter Account password for Confermation : ");
String gPass=s.next().trim();
System.out.println();
String sql="select accno,hpass,hname,bal from vbank where accno='"+wiAcc+"'";
Connection con=du.conDb();
ResultSet rs=du.rest(sql,con);


try {
	while(rs.next())
	{
		accNo=rs.getString("accno");
		name=rs.getString("hname");		
		accPass=rs.getString("hpass");
		mainBal=rs.getInt("bal");
		
	}// while Loop
	
	} catch (SQLException e) {	e.printStackTrace(); }  // Try close()
if(mainBal==0)
{
System.out.println("Account Number = "+accNo+"\t Holder Name ="+name+"\t main Ballence= "+mainBal +" Rs/- You Have ");
	
System.out.println("You Have No sufficient Ballence To Process Transaction ");
System.out.println("Please Deposite To Run The Account ");

System.exit(0);
}

else {
	System.out.println("You Have Funds are Availablility ");
	System.out.println("Account Number = "+accNo+"\t Holder Name ="+name+"\t main Ballence= "+mainBal +" Rs/- You Have ");
	
	System.out.print("  ------------- Enter Withdral Amount : ");
	int wiAmount=s.nextInt();
	System.out.println();

	int awAmount=mainBal-wiAmount;
	
	
	
	if(awAmount>=500)
		{ 
			System.out.println("sufficient Funds Are availabele in youre Account \n So You Can Withdraw Amount ");
			mainBal=awAmount;

System.out.print("Enter Account 4digit password for Confermation : ");
String gPass1=du.set4Digit();
System.out.println();

if(gPass1.equalsIgnoreCase(accPass))
{
	System.out.println("--------------------------  Valid Password  ----------------------");

}else
{System.out.println("Password Is Wrongly Entered  \n Please Enter Correct Password Next Time "); System.exit(0);
}// Passsword 


String upsql="update vbank set bal="+awAmount+" where accno='"+wiAcc+"'";
			du.stDb(upsql);
			System.out.println("Row Is Updated Sucessfully ");
			System.out.println("Amount is Debeted from Account = '"+wiAcc+"'  With Amount ="+wiAmount+" \n Please Collect Money ");
			System.out.println("After debeted Account Information ");
			System.out.println("----------------------------------------------------------------");
			du.getAccDet(wiAcc);
			System.out.println("----------------------------------------------------------------");	
		}
		else if(awAmount<500&awAmount>=0)
		{
			System.out.println("After Withdraw Amount is Not Sufficient in you are AccountBallence <= 500 Rs/-  ");
			System.out.println("Do you want to Continue This Withdraw Amount ( yes / no )  :  ");
			String opt=s.next().trim();
			System.out.println();
			
			
			if(opt.equalsIgnoreCase("yes"))
			{
				String upsql="update vbank set bal="+awAmount+" where accno='"+wiAcc+"'";
			
				System.out.print("Enter Account password for Confermation : ");
				String gPass1=s.next().trim();
				System.out.println();

				if(gPass1.equalsIgnoreCase(accPass))
				{
					System.out.println("--------------------------  Valid Password  ----------------------");

				}else
				{System.out.println("Password Is Wrongly Entered  \n Please Enter Correct Password Next Time "); System.exit(0);
				}// Passsword 
			du.stDb(upsql);
			
			System.out.println("Row Is UpDated Sucessfully ");
			System.out.println("Amount is Debeted from Account = '"+wiAcc+"'  With Amount ="+wiAmount+" \n Please Collect Money ");
			System.out.println("After debeted Account Information ");
			System.out.println("----------------------------------------------------------------");
			du.getAccDet(wiAcc);
			System.out.println("----------------------------------------------------------------");	
			
			
			}else
			{
				System.out.println("Ok You choose  ( no )  option  Accepts \n So Exit from this oparation ");
				System.exit(0);
			}
			
		}else
		{System.out.println("You Entered Exceded Amount ");System.exit(0);}
	
	
	


}	

}// If_ Close (mainBal==0)
	
}
