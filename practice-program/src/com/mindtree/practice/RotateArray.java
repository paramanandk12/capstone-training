package com.mindtree.practice;

import java.util.Arrays;

public class RotateArray {

	public static void main(String[] args) {

		int[] no = { 1, 2, 3, 4, 5 };

		int nextValue = 0;
		int preValue = 0;
		preValue = no[0];

		for (int i = 1; i < no.length; i++) {

			nextValue = no[i];
			no[i] = preValue;
			preValue = nextValue;

		}
		no[0] = preValue;

		System.out.println(Arrays.toString(no));
	}

}
