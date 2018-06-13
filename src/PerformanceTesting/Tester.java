package PerformanceTesting;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Tester {
	//quick reference to the options
	
	public static void test(int ... is) {
		System.out.print(is.length);
		if (is == null) {
			System.out.print("suscess");
		}
	}
	public static void main(String args[]) {
		test();
	}
}
