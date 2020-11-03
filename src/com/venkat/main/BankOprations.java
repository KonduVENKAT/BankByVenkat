package com.venkat.main;

import java.util.Scanner;

import com.venkat.mainThings.BankNewAccount;
import com.venkat1.transactions.BankAccDetails;
import com.venkat1.transactions.BankDeposite;
import com.venkat1.transactions.BankUpdateAccDet;
import com.venkat1.transactions.BankWithdraw;

public class BankOprations {

	public static void main(String[] args) {
		
		Scanner s=new Scanner(System.in);
		
		System.out.println("Banking things are Started in here Onwards \n__________________________________________________");
		
		System.out.println("Options are \n----------------------------- ");
		System.out.println("For New Account creation enter : ( newAcc )");
		System.out.println("For Deposite Amount Enter      : ( deposite )");
		System.out.println("For WithDraw Amount Enter      : ( withdraw)");
		System.out.println("For Getting the Bank Details   : ( getDet )");
		System.out.println("For Updating the Account Det   : ( update ) ");
		System.out.println("--------------------------------------------");
		System.out.println("_________________________________________________");
		System.out.print("Enter You're Opration option  :");
		String opr=s.next().trim();
		System.out.println();
		
		
		switch(opr)
		{
		case "newAcc"   :BankNewAccount nba=new BankNewAccount();
							nba.newAcc();
				break;
		case "deposite" :BankDeposite bd=new BankDeposite();
							bd.accDeposite();
				break;
		case "getDet"   :BankAccDetails bad=new BankAccDetails();
							bad.accDetails();
				break; 
		case "withdraw":BankWithdraw bwd=new BankWithdraw();
								bwd.withdraw();
			break;
		case "update" :BankUpdateAccDet bud=new BankUpdateAccDet();
						bud.updateAcc();
				break;
			
		default : System.out.println("----------------  Wrong Input ---------------- ");
		}
		
		/*
		
		if(opr.equalsIgnoreCase("newAcc"))
		{ 
			BankNewAccount nba=new BankNewAccount();
			nba.newAcc();
		}
		else if(opr.equalsIgnoreCase("deposite"))
		{
			BankDeposite bd=new BankDeposite();
			bd.accDeposite();
			
		}
		else if(opr.equalsIgnoreCase("getDet"))
		{
			
			BankAccDetails bad=new BankAccDetails();
			bad.accDetails();
						
		}else if(opr.equalsIgnoreCase("withdraw"))
		{
		
		
		}
		else
		{
			System.out.println("-------------  Wrong Input  --------------- ");
			
			
		}
		
		*/
		
		
		
	}

}
