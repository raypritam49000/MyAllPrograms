package com.problem.sloving;

public class Test {
	public static void main(String[] args) {

		String str = "there there is is a program";
		int count = 0;
		String[] st = str.split(" ");
		for (int i = 0; i <= st.length - 1; i++) {
			for (int j = i + 1; j <= st.length - 1; j++) {
				if (st[i] == st[j]) {
					count++;
				}
			}
		}

		System.out.println(count);

	}
}
