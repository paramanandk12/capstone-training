package com.mindtree.practice;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class HashSetExample {

	public static void main(String[] args) {
		Employee emp = new Employee(101, "param", "cse", 5000);
		Employee emp1 = new Employee(102, "param", "cse", 5000);
		Employee emp2 = new Employee(103, "param", "cse", 5000);
		Employee emp3 = new Employee(104, "param", "cse", 5000);
		Employee emp4 = new Employee(105, "param", "cse", 5000);

		HashSet<Employee> h = new HashSet<>();
		h.add(emp);
		h.add(emp4);
		h.add(null);
		h.add(null);
		h.add(emp3);
		h.add(emp3);
		System.out.println(h);
		
		LinkedHashSet<Employee> l = new LinkedHashSet<>();
		l.add(emp4);
		l.add(emp3);
		l.add(null);
		l.add(emp2);
		l.add(emp2);
		System.out.println(l);
	}
	
	

}
