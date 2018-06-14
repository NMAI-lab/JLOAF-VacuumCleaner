package PerformanceTesting;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import org.jLOAF.reasoning.Reasoners;
import org.jLOAF.sim.StateBased.StSims;

import PerformanceTesting.PerformanceTest;

public class Main {
	
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
		//edit with your personal settings here
		int mapNum1 = 0;
		int mapNum2 = 1;
		TestType testType = TestType.FixedSequenceAgent; 
		StSims[] units = {StSims.kordered};
		Reasoners[] reasoners = {Reasoners.weightedKNN};
		
		
		//do not change
		String folder = testType.getFolder();
		String[] files = {"Traces/" + folder + "trace-m" + mapNum1 + "-" + testType + ".txt", "Traces/" + folder + "/trace-m" + mapNum2 + "-" + testType + ".txt"};
		System.out.println("XML files to test with LFO-Simulator: " + "Traces/" + folder + "trace-m" + mapNum1 + "-" + testType + ".xml" + " and/or " + "Traces/" + folder + "trace-m" + mapNum1 + "-" + testType + ".xml");
		evaluate(files, reasoners, units, new int[]{mapNum1, mapNum2});
	}
	

	
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
	 * This will do a performance test with the given files.
	 * This will output files in the Statistics and Results folders
	 * 
	 * @param filenames - Files in the String names referenced from the base directory. These files are used to compare against each other
	 * @param units - The Units to use
	 * 
	 * 
	 */
	public static void evaluate(String[] filenames, Reasoners[] reasoners, StSims[] units, int mapNums[]){
		for (Reasoners r: reasoners) {
			for (StSims u: units) {
				try {
					String descriptor = getTestType(filenames[0])+ " - " + r + "_" + u + "_compared-maps:" + mapNums[0] + "," + mapNums[1] + " - m";
					
					PerformanceTest pt = new PerformanceTest();
					//CaseBaseFilter ft = new HillClimbingFeatureSelection(null);
					pt.PerformanceEvaluatorMethod(filenames,null,descriptor,r.toString(),u.toString(),null, mapNums);

				} catch (IOException e) {
					e.printStackTrace();
				}
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
