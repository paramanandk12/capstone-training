package com.app.array;

public class ArrayStructures {

	private int[] theArray = new int[50];

	private int arraySize = 10;

	public void generateRandomarray() {

		for (int i = 0; i < arraySize; i++) {

			theArray[i] = (int) (Math.random() * 10) + 10;

		}

	}

	public int getValueAtIndex(int index) {

		if (index < arraySize)
			return theArray[index];

		return 0;
	}

	public boolean doesArrayContainsThisvalue(int searchValue) {

		boolean valueInArray = false;

		for (int i = 0; i < arraySize; i++) {

			if (theArray[i] == searchValue) {

				valueInArray = true;
			}

		}
		return valueInArray;

	}

	public void deleteIndex(int index) {

		if (index < arraySize) {

			for (int i = index; i < (arraySize - 1); i++) {

				theArray[i] = theArray[i + 1];
			}
			arraySize--;
		}
	}

	public void insertValue(int value) {

		if (arraySize < 50) {

			theArray[arraySize] = value;

			arraySize++;
		}
	}

	public String linearSearchForValue(int value) {

		boolean valueInArray = false;
		String indexWithValue = "";
		System.out.print("The Value was found in the followig: ");

		for (int i = 0; i < arraySize; i++) {

			if (theArray[i] == value) {

				valueInArray = true;

				System.out.print(i + " ");

				indexWithValue += i + " ";
			}

		}
		if (!valueInArray) {

			indexWithValue = "None";

			System.out.print(indexWithValue);

		}

		System.out.println();

		return indexWithValue;

	}

	public void printArray() {

		System.out.println("----------");
		for (int i = 0; i < arraySize; i++) {

			System.out.print("| " + i + " | ");

			System.out.println(theArray[i] + " | ");

			System.out.println("----------");

		}
	}

	public static void main(String[] args) {

		ArrayStructures newArray = new ArrayStructures();
		newArray.generateRandomarray();
		newArray.printArray();
		System.out.println(newArray.getValueAtIndex(3));
		System.out.println(newArray.doesArrayContainsThisvalue(10));
		newArray.deleteIndex(3);
		newArray.printArray();
		newArray.insertValue(55);
		newArray.printArray();
		newArray.linearSearchForValue(10);

	}

}
