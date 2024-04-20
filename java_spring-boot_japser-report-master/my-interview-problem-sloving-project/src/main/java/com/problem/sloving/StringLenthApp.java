package com.problem.sloving;

public class StringLenthApp {

	public static int countUniqueCharacters(String str) {
		int uniqueCount = 0;

		for (int i = 0; i < str.length(); i++) {
			char currentChar = str.charAt(i);

			boolean isUnique = true;

			for (int j = 0; j < i; j++) {
				if (str.charAt(j) == currentChar) {
					isUnique = false;
					break;
				}
			}

			if (isUnique) {
				uniqueCount++;
			}
		}

		return uniqueCount;
	}

	public static void main(String[] args) {

		String str = "aabbccaaddaa";
		System.out.println(countUniqueCharacters(str));
	}
}
