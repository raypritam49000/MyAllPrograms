package com.problem.sloving;

import java.util.Arrays;

public class ArraryProject {

	public static void findDuplicates(int[] array) {

		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] == array[j]) {
					System.out.println("Duplicate found: " + array[i]);
				}
			}
		}
	}

	public static int[] sortDescending(int[] array) {
		for (int i = 0; i <= array.length - 1; i++) {
			for (int j = i + 1; j <= array.length - 1; j++) {
				if (array[i] < array[j]) {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
		return array;
	}

	public static int[] sortAscending(int[] array) {
		for (int i = 0; i <= array.length - 1; i++) {
			for (int j = i + 1; j <= array.length - 1; j++) {
				if (array[i] > array[j]) {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
		return array;
	}

	public static void findCommonElements(int[] arr1, int[] arr2, int[] arr3) {
		int i = 0, j = 0, k = 0;

		while (i < arr1.length && j < arr2.length && k < arr3.length) {
			// If the current elements are equal, it's a common element
			if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
				System.out.println("Common element: " + arr1[i]);
				i++;
				j++;
				k++;
			}
			// Move the pointers of the array with the smallest current element
			else if (arr1[i] < arr2[j]) {
				i++;
			} else if (arr2[j] < arr3[k]) {
				j++;
			} else {
				k++;
			}
		}
	}

	public static int findMinimumElement(int[] array) {

		int minElement = array[0];

		for (int i = 1; i < array.length; i++) {
			if (array[i] < minElement) {
				minElement = array[i];
			}
		}

		return minElement;
	}

	public static int findMaximumElement(int[] array) {

		int maxElement = array[0];

		for (int i = 1; i < array.length; i++) {
			if (array[i] > maxElement) {
				maxElement = array[i];
			}
		}

		return maxElement;
	}

	public static String reverseString(String str) {
		String reverse = "";
		for (int i = str.length() - 1; i >= 0; i--) {
			reverse = reverse + str.charAt(i);
		}
		return reverse;
	}

	public static void main(String[] args) {
//		int[] myArray = { 1, 2, 3, 1, 4, 2, 5, 6, 5, 7, 8, 9, 9 };
//		findDuplicates(myArray);
//
//		int[] nums = new int[] { 10, 49, 56, 39, 5, 15, 60 };
//
//		// Sort in ascending order
//		int[] ascendingOrder = sortAscending(nums);
//		System.out.println("Ascending Order: " + Arrays.toString(ascendingOrder));
//
//		// Sort in descending order
//		int[] descendingOrder = sortDescending(nums);
//		System.out.println("Descending Order: " + Arrays.toString(descendingOrder));
//
//		int[] arr1 = { 1, 5, 10, 20, 40, 80 };
//		int[] arr2 = { 6, 7, 20, 80, 100 };
//		int[] arr3 = { 3, 4, 15, 20, 30, 70, 80, 120 };
//
//		System.out.println("Common elements in three sorted arrays:");
//		findCommonElements(arr1, arr2, arr3);

//		int[] myArray = { 5, 2, 8, 1, 9, 4, 3 };
//
//		// Find minimum element
//		int minElement = findMinimumElement(myArray);
//		System.out.println("Minimum Element: " + minElement);
//
//		// Find maximum element
//		int maxElement = findMaximumElement(myArray);
//		System.out.println("Maximum Element: " + maxElement);

//		String reverseString = reverseString("Pritam Ray");
//		System.out.println(reverseString);

		int[] array = new int[] { 10, -4, -8, 29, -49, 32, 43 };
		System.out.println(Arrays.toString(sortAscending(array)));

	}
}
