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
		int[] maps = {0, 1, 2, 3, 4, 5};
		TestType testType = TestType.FixedSequenceAgent; 
		StSims[] units = {StSims.kordered};
		Reasoners[] reasoners = {Reasoners.weightedKNN};
		
		
		//do not change
		evaluate(testType, reasoners, units, maps);
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
	public static void evaluate(String[] filenames, Reasoners[] reasoners, StSims[] units, int map1, int map2){
		for (Reasoners r: reasoners) {
			for (StSims u: units) {
				try {
					String descriptor = getTestType(filenames[0])+ " - " + r + "_" + u + "_compared-maps:" + map1 + "," + map2;
					
					PerformanceTest pt = new PerformanceTest();
					//CaseBaseFilter ft = new HillClimbingFeatureSelection(null);
					pt.PerformanceEvaluatorMethod(filenames,null,descriptor,r.toString(),u.toString(),null, new int[] {map1, map2});

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Compare every possible arrangement of maps into the other evaulate function given the array of maps
	 * @param testType
	 * @param reasoners
	 * @param units
	 * @param maps
	 */
	public static void evaluate(TestType t, Reasoners[] reasoners, StSims[] units, int[] maps) {
		//get every possibility of 2 files
		for (int i = 0; i < maps.length; i++) {
			for (int j = i + 1; j < maps.length; j++) {
				//get the file names and send to evaulate
				String folder = t.getFolder();
				String file0 = folder + "trace-m" + maps[i] + "-" + t;
				String file1 = folder + "trace-m" + maps[j] + "-" + t;
				String[] files = {"Traces/" + file0 + ".txt", "Traces/" + file1 + ".txt"};
				System.out.println("XML files to test with LFO-Simulator: " + file0 + ".xml" + " and/or " + file1 + ".xml");
				evaluate(files, reasoners, units, maps[i], maps[j]);
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
