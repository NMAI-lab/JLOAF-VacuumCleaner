package PerformanceTesting;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import PerformanceTesting.PerformanceTest;
import sun.management.counter.Units;

public class Main {
	/**
	 * These are the options you can for the units
	 * @author frankliu197
	 *
	 */
	public enum Units {
		KORDERED_R, KORDERED, KUNORDERED;
		
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	}
	
	public enum TestType {
		FixedSequenceAgent, MultipleSequenceAgent, WallFollowerAgent, ZigZagAgent
	}
	
	/**
	 * Use the main method to send filenames and units to the evaluate function or use the evaluateAllFiles function
	 * @param args
	 */
	public static void main(String[] args) {
		//Example
		//String[] files = {"Trace/trace-m0-WallFollowerAgent.txt", "Trace/trace-m1-WallFollowerAgent.txt"};
		//Units[] units = Units.KORDERED;
		//evaluate(files, units);
	}
	
	/**
	 * This will evaluateAllFiles in the given folder with the given TestType. Edit this code to your liking
	 */
	public static void evaluateAllFiles(String folder, TestType testType, Units units) {
		File[] files = new File(folder).listFiles();
		ArrayList<String> filesToTest = new ArrayList<>();
		for(File f: files) {
			if (getTestType(f.getName()).equals(testType)) {
				filesToTest.add(f.getPath());
			}
			evaluate(filesToTest.toArray(new String[] {}), units);
		}
	}
	
	/**
	 * See Evaluate(String[], String, Units[]) 
	 * @param filenames
	 * @param units
	 */
	public static void evaluate(String[] filenames,  Units ... units){
		evaluate(filenames, null, units);
	}
	
	/**
	 * This will do a performance test with the given files.
	 * This will output files in the Statistics and Results folders
	 * 
	 * @param filenames - Files in the String names referenced from the base directory. These files are used to compare against each other
	 * @param comment - (Optional) A comment you would like to add. This is used for naming the output files
	 * @param units - The Units to use
	 * 
	 * 
	 */
	public static void evaluate(String[] filenames,  String comment, Units ... units){		
		for (Units u: units) {
			try {
				String baseString = getTestType(filenames[0])+ " - CBR,weightedKNN,none,none," + u + ",none";
				String descriptor; 
				if(comment != null) {
					descriptor = comment + " - " + baseString;
				} else {
					descriptor = baseString;
				}
				
				PerformanceTest pt = new PerformanceTest();
				//CaseBaseFilter ft = new HillClimbingFeatureSelection(null);
				pt.PerformanceEvaluatorMethod(filenames,null,descriptor,"weightedKNN",u.toString(),null);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/** 
	 * Assuming the file format is Trace/trace-m#-****.txt, this method will parse the string to get the ******, which is referred as the test type
	 * @param filename - the file with the given format
	 */
	public static TestType getTestType(String f) {
		return TestType.valueOf(f.substring(f.indexOf("-", f.indexOf("trace-") + 6) + 1, f.lastIndexOf(".")));
	}
}
