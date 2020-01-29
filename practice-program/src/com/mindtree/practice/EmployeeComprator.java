package com.mindtree.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class EmployeeComprator {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Map<String, Employee> hmap = new HashMap<>();
		hmap.put("e1", new Employee(101, "param", "cse", 5000));
		hmap.put("e2", new Employee(103, "aman", "ece", 23000));
		hmap.put("e3", new Employee(104, "banty", "me", 5400));
		hmap.put("e4", new Employee(102, "bablu", "ex", 6000));
		hmap.put("e5", new Employee(105, "naresh", "ce", 3000));
		hmap.put("e6", new Employee(107, "ramesh", "it", 7000));
		hmap.put("e7", new Employee(106, "john", "am", 1000));
		
		Set<Entry<String, Employee>> set=hmap.entrySet();
		List<Entry<String, Employee>> list=new ArrayList<>(set);

		System.out.println("sorted by employee id...");
		Collections.sort(list, new EmployeeCompratorById());

		Iterator<Map.Entry<String, Employee>> itr = list.iterator();

		while (itr.hasNext()) {
			Map.Entry<String, Employee> entry = itr.next();

			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
		
		System.out.println("sorted by employee id...");
		Collections.sort(list, new EmployeeCompratorByName());

		Iterator<Map.Entry<String, Employee>> itr1 = list.iterator();

		while (itr1.hasNext()) {
			Map.Entry<String, Employee> entry = itr1.next();

			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
		
		
	}

	 
	 

}

class EmployeeCompratorById implements Comparator<Entry<String,Employee>> {

	@Override
	public int compare(Entry<String, Employee> o1, Entry<String, Employee> o2) {
		
		return o1.getValue().getEid()-o2.getValue().getEid();
	}

	
	
}

class EmployeeCompratorByName implements Comparator<Entry<String, Employee>> {

	@Override
	public int compare(Entry<String, Employee> o1, Entry<String, Employee> o2) {

		return o1.getValue().getEname().compareTo(o2.getValue().getEname());
	}

}

class EmployeeCompratorByDept implements Comparator<Entry<String,Employee>> {

	@Override
	public int compare(Entry<String, Employee> o1, Entry<String, Employee> o2) {

		return o1.getValue().getDept().compareTo(o2.getValue().getDept());
	}

}

class EmployeeCompratorBySal implements Comparator<Entry<String,Employee>> {

	@Override
	public int compare(Entry<String, Employee> o1, Entry<String, Employee> o2) {

		return (int) (o1.getValue().getSal() - o2.getValue().getSal());
	}

}
