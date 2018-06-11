package PerformanceTesting;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Tester {
	//quick reference to the options

	public static void main(String args[]) {
		File[] files = new File("Traces").listFiles();
		ArrayList<String> filesToTest = new ArrayList<>();
		for(File f: files) {
				filesToTest.add(f.getPath());
		}
		System.out.print(Arrays.toString(filesToTest.toArray(new String[] {})));
	}
}
