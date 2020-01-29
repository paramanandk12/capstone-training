package com.mindtree.practice;

public class ConstructorExample {
	int id ;
	String name;
	
	
	
	
	public ConstructorExample() {
      
		
		System.out.println("Constructor 1");
	
	}




	public ConstructorExample(int id) {
	     this();
		this.id = id;
		System.out.println("constructor 2");
	}




	public ConstructorExample(int id, String name) {
		this(10);
		this.id = id;
		this.name = name;
		System.out.println("constructor 3");
		
	}




	public static void main(String[] args) {
		
		
		ConstructorExample c=new ConstructorExample(10,"param");
		
		System.out.println(c.id);
		System.out.println(c.name);
	}

}
