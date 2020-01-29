package com.mindtree.practice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayCustomSort {
	

	public static void main(String[] args) {
		
	List<Integer> number = Arrays.asList(7,2,3,15,5);
	
	List<Integer> square = number.stream().map(x -> x*x).collect(Collectors.toList());
	System.out.println(square);
	
	List<Integer> sortedArray = number.stream().sorted().collect(Collectors.toList());
	System.out.println(sortedArray);
	
	List<String> str = Arrays.asList("ram","mohan","","sita");
	
	List<String> str1 = str.stream().filter(s -> s.startsWith("m")).collect(Collectors.toList());
	
	System.out.println(str1);
	
	List<String> show = str.stream().sorted().collect(Collectors.toList());
	System.out.println(show);
		

	}

}
