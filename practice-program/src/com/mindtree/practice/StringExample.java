package com.mindtree.practice;

public class StringExample {

	public static void main(String[] args) {
		String str = "hello java";
		String st = "hello java";
		
		String str1 = new String("hello java");
		String st2 = new String("hello java");
		System.out.println(str.equals(st)); // true
		System.out.println(str == st); // true
		System.out.println(str1.equals(st2));// true
		System.out.println(str1 == st2); // false
		
		System.out.println(str.hashCode());
		
		System.out.println(str==str1); // false
		System.out.println(str.equals(str1)); // true
		
		StringBuffer sb = new StringBuffer("hello java");
		System.out.println(str1.equals(sb)); // false
		
		StringBuilder sb1 = new StringBuilder("hello java"); 
		
		
		System.out.println(str.equals(sb));// false
		System.out.println(str.equals(sb1)); // false
		System.out.println(str1.equals(sb1)); // false
		
		System.out.println(sb.equals(sb1)); // false
		

	}

}
