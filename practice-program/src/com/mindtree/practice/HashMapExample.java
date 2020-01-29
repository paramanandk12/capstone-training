package com.mindtree.practice;

import java.util.HashMap;

public class HashMapExample {

	public static void main(String[] args) {
		Employee emp = new Employee(101, "param", "cse", 5000);
		Employee emp1 = new Employee(102, "param", "cse", 5000);
		Employee emp2 = new Employee(103, "param", "cse", 5000);
		Employee emp3 = new Employee(104, "param", "cse", 5000);
		Employee emp4 = new Employee(105, "param", "cse", 5000);
		       
		HashMap<Integer, Employee> map = new HashMap<>();
		map.put(101, emp);
		map.put(102, emp1);
		map.put(103, emp2);
		map.put(103, emp3);
		map.put(104, emp4);
		map.put(null, emp);
		map.put(null, emp4);
		map.put(null, emp3);
		map.put(null, null);
		

     System.out.println(map);		
		
	}

}
