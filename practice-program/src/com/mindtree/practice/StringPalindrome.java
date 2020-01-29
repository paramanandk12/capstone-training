package com.mindtree.practice;

import java.util.Scanner;

public class StringPalindrome {

	public void  isPalindrome(String input) {
		String reverse="";
	int length =input.length();
	for(int i = length-1;i>=0; i--) 
		reverse = reverse+input.charAt(i);
	
	if(input.equals(reverse))
		System.out.println("yes");
	
	else 
		System.out.println("no");
	}
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		System.out.println("enter any string");
		String str = s.nextLine();
		StringPalindrome sp = new StringPalindrome();
		sp.isPalindrome(str);
		

	}

}
