package com.mindtree.practice;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

public class ArrayTest2 {

	public static void main(String[] args) {
		
		String[] candidate = {"Param","vivek","manish","vivek","Param","simant","manish"};
		
		HashMap<String, Integer> vote = new HashMap<>();
		
		for (String string : candidate) {
			if(!vote.containsKey(string))
				vote.put(string, 1);
				else
					vote.put(string, vote.get(string)+1);
		
			}
		LinkedList<Entry<String, Integer>> linkedList = new LinkedList<>(vote.entrySet());
		linkedList.sort(new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if(o1.getValue()>o2.getValue()) {
					return -1;
				}else if(o1.getValue()<o2.getValue()) {
					return 1;
				}else
				return 0;
			}
		});

	//	System.out.println("vote"+vote);
		System.out.println(linkedList);
		Entry<String, Integer> entry2 = linkedList.get(0);
		for (Iterator iterator = linkedList.iterator(); iterator.hasNext();) {
			
			Entry<String, Integer> entry = (Entry<String, Integer>) iterator.next();
			//System.out.println(entry);
			if(entry2.getValue()<=entry.getValue()) {
				entry2=entry;
			}else {
				System.out.println(entry2+"   is big  ");
				break;
			}
			
			
			
			
		}
		
		
		
		
		
				
			}


	

}
