package com.problem.sloving;

import java.util.Arrays;

public class AscendingDescendingApp {

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

	public static void main(String[] args) {
		int[] nums = new int[] { 10, 49, 56, 39, 5, 15, 60 };

		// Sort in ascending order
        int[] ascendingOrder = sortAscending(nums);
        System.out.println("Ascending Order: " + Arrays.toString(ascendingOrder));
        
        // Sort in descending order
        int[] descendingOrder = sortDescending(nums);
        System.out.println("Descending Order: " + Arrays.toString(descendingOrder));
       
	}
}
