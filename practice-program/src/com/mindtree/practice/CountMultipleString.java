package com.mindtree.practice;

import java.util.Arrays;

public class CountMultipleString {

	public static void main(String[] args) {

		String str = "ABCEFGXYZABCCDABCAXYZABC";
		int count = 0;
		String strArray[] = str.split("ABC");
		 count = strArray.length;
		System.out.println(Arrays.toString(strArray));
		System.out.println("ABC = "+ count);

	}

}
