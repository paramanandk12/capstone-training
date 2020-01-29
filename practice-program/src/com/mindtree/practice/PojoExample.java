package com.mindtree.practice;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PojoExample {
	
	//private int id;
	//private String name;
	
	
	public int getId() {
		return 1;
	}
	/*public void setId(int id) {
		this.id = id;
	}*/
	public String getName() {
		return "java";
	}
	/*public void setName(String name) {
		this.name = name;
	}*/
	
	public static void main(String[] args) throws Exception {
		PojoExample p = new PojoExample();
		
		System.out.println("id :"+p.getId()+" name :"+p.getName());
		Class cls = p.getClass();
		System.out.println(cls.getName());
		
		Constructor ctr = cls.getConstructor();
		
		System.out.println(ctr.getName());
		
		Field f = cls.getField("name");
		
	System.out.println(f);
	}
	
	

}
