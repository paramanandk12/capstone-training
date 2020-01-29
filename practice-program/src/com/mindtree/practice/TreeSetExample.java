package com.mindtree.practice;

import java.util.TreeSet;

public class TreeSetExample {

	public static void main(String[] args) {
		
		Employee emp = new Employee(101, "param", "cse", 5000);
		Employee emp1 = new Employee(102, "param", "cse", 5000);
		Employee emp2 = new Employee(103, "param", "cse", 5000);
		Employee emp3 = new Employee(104, "param", "cse", 5000);
		
		
		TreeSet<Employee> ts = new TreeSet<>();
		ts.add(emp);
		ts.add(emp1);
		ts.add(emp2);
		ts.add(emp3);
		
		
		System.out.println(ts);

	}

}
