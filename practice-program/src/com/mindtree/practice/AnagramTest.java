package com.mindtree.practice;

import java.util.Scanner;

public class AnagramTest {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("enter any number !!");
		int num = s.nextInt();
		int sum=0 ;
		for(int i = 0; i <=num; i++) {
			sum = sum + i;
			
		}
		
		System.out.println(sum);
		

	}

}
