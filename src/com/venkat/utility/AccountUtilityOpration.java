package com.venkat.utility;

import java.util.Scanner;

public class AccountUtilityOpration {
	Scanner sc=new Scanner(System.in);

	public int deposite(String accNo, int mainBal)
	{
		
		int depMainBal=mainBal;
		String depAccNo=accNo;
		
		System.out.print("Deposite Amount Bal : ");
		int depBal=sc.nextInt();
		System.out.println();
		
		
		return depMainBal;
		
	}
	
}
