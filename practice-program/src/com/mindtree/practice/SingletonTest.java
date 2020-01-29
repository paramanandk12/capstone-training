package com.mindtree.practice;

public class SingletonTest {

	public static SingletonTest get_single_instance= null;
	
	public String s;
	private SingletonTest() {
		
		s= "hello singleton class";
	}
	public static SingletonTest getInstance() {
		
		if(get_single_instance == null) {
			
			get_single_instance = new SingletonTest();
		}
		
		return get_single_instance;
		
	}
	
}
