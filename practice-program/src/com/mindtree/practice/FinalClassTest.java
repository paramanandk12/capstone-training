package com.mindtree.practice;

import java.util.HashMap;
import java.util.Iterator;

public final class FinalClassTest {

	private final int id;
	private final String name;
	private final HashMap<String, String> testMap;

	public FinalClassTest(int i, String n, HashMap<String, String> hm) {
		System.out.println("performing deep copy for object initailization !! ");
		this.id = i;
		this.name = n;
		HashMap<String, String> tmap = new HashMap<String, String>();
		String key;
		Iterator<String> it = hm.keySet().iterator();
		while (it.hasNext()) {
			key = it.next();
			tmap.put(key, hm.get(key));

		}
		this.testMap = tmap;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public HashMap<String, String> getTestMap() {
		return (HashMap<String, String>) testMap.clone();
	}

	public static void main(String[] args) {

		HashMap<String, String> h = new HashMap<>();
		h.put("1", "one");
		h.put("2", "two");
		h.put("3", "three");
		int i = 10;
		String temp = "original";
		FinalClassTest ftc = new FinalClassTest(i, temp, h);

		System.out.println(temp == ftc.getName());
		System.out.println(h == ftc.getTestMap());
		System.out.println("ftc id " + ftc.getId());
		System.out.println("ftc name " + ftc.getName());

		i = 20;
		temp = "modified";
		h.put("4", "four");

		System.out.println("ce id after local variable change:" + ftc.getId());
		System.out.println("ce name after local variable change:" + ftc.getName());
		System.out.println("ce testMap after local variable change:" + ftc.getTestMap());
	}

}
