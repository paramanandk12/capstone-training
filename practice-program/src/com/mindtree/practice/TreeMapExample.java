package com.mindtree.practice;

import java.util.TreeMap;

public class TreeMapExample {

	
	public static void main(String[] args) {
		Employee emp = new Employee(101, "param", "cse", 5000);
		Employee emp1 = new Employee(102, "param", "cse", 5000);
		Employee emp2 = new Employee(103, "param", "cse", 5000);
		Employee emp3 = new Employee(104, "param", "cse", 5000);
		Employee emp4 = new Employee(105, "param", "cse", 5000);
		
		TreeMap<Integer, Employee> tm = new TreeMap<>();
		//tm.put(null, emp4);
		tm.put(101, emp);
		tm.put(101, null);
		tm.put(102, emp4);
		tm.put(104, emp4);
		tm.put(104, emp);
		
		
		
		System.out.println(tm);
		
		
	}
}
