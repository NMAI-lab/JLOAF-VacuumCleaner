package PerformanceTesting;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import PerformanceTesting.PerformanceTest;

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
		FixedSequenceAgent, MultipleSequenceAgent, WallFollowerAgent, ZigZagAgent;
		public String getFolder() {
			if (this.equals(TestType.FixedSequenceAgent)){
				return "fxdseq/";
			} else if (this.equals(TestType.ZigZagAgent)) {
				return "zz/";
			} else {
				return "";
			}
		}
	}
	
	/**
	 * Use the main method to send filenames and units to the evaluate function or use the evaluateAllFiles function
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		int mapNum1 = 1;
		int mapNum2 = 2;
		TestType testType = TestType.FixedSequenceAgent;
		String folder = testType.getFolder();
		String[] files = {"Traces/" + folder + "trace-m" + mapNum1 + "-" + testType + ".txt", "Traces/" + folder + "/trace-m" + mapNum2 + "-" + testType + ".txt"};
		//Units[] units = {Units.KORDERED, Units.KORDERED};
		evaluate(files, Units.values());
	}
	
	/**
	 * This will evaluateAllFiles in the given folder with the given TestType. Edit this code to your liking
	 */
	//public static void evaluateAllFiles(String folder, TestType testType, Units ... units) {
	//	ArrayList<String> filesToTest = new ArrayList<>();
	//	File[] files = listFiles(folder, ".txt");
	//	
	//	for(File f: files) {
	//		if (getTestType(f.getName()).equals(testType)) {
	//			filesToTest.add(f.getPath());
	//		}
	//	}
	//
	//	evaluate(filesToTest.toArray(new String[] {}), units);
	//}
	
	/**
	 * This will evaluateAllFiles. The testType is the testType of the first file inside this folder
	 */
	//public static void evaluateAllFiles(String folder, Units ... units) {
	//	evaluateAllFiles(folder, getTestType(listFiles(folder, ".txt")[0].getPath()), units);
	//}
	
	/**
	 * List the files of a given extension in a folder
	 */
	public static File[] listFiles(String folder, String ext) {
		return new File(folder).listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.toLowerCase().endsWith(ext);
		    }
		});
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
				String baseString = getTestType(filenames[0])+ " - CBR,weightedKNN,none,none," + u + ",none - m";
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
	/** 
	 * Assuming the file format is Trace/trace-m#-****.txt, this method will parse the string to get the #, which is referred as the map number
	 * @param filename - the file with the given format
	 */
	public static String getMapNumber(String f) {
		return f.substring(f.indexOf("trace-m") + 6 , f.lastIndexOf("."));
	}
}
