package com.mindtree.practice;

import java.util.Scanner;

public class IntegerPalindrom {

	private void isPalindrom(int i) {
		int temp = 0, r, sum = 0;

		temp = i;
		while (i > 0) {

			r = i % 10;
			sum = (sum * 10) + r;
			i = i / 10;

		}

		if (temp == sum)

			System.out.println("yes");

		else

			System.out.println("not");

	}

	public static void main(String[] args) {
		IntegerPalindrom ip = new IntegerPalindrom();
		Scanner s = new Scanner(System.in);
		System.out.println("enter number to check palindron!! ");
		int input = s.nextInt();
		ip.isPalindrom(input);

	}

}
