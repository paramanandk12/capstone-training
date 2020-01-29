package com.mindtree.practice;

public class SingletonMainClass {

	public static void main(String[] args) {
		
		SingletonTest x = SingletonTest.getInstance();
		SingletonTest y = SingletonTest.getInstance();
		SingletonTest z = SingletonTest.getInstance();
		
		// changing variable of instance x 
        x.s = (x.s).toUpperCase(); 
  
        System.out.println("String from x is " + x.s); 
        System.out.println("String from y is " + y.s); 
        System.out.println("String from z is " + z.s); 
        System.out.println("\n"); 
  
        // changing variable of instance z 
        z.s = (z.s).toLowerCase(); 
  
        System.out.println("String from x is " + x.s); 
        System.out.println("String from y is " + y.s); 
        System.out.println("String from z is " + z.s); 

	}

}
