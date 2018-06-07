package PerformanceTesting;

import java.io.IOException;

import org.jLOAF.Reasoning;
import org.jLOAF.performance.TestingConfig;
import org.jLOAF.preprocessing.filter.CaseBaseFilter;

public class TestingConfigVacuumCleaner {
	
	public static void main(String[] args){
		TestingConfig tc = new TestingConfig();
		
		
		String [] filenames = tc.getFileNames(args);
		
		String output_filename = tc.getOutputFileName(args);
		
		
		CaseBaseFilter ft =tc.createCaseBaseFilter(args);
<<<<<<< HEAD
		//tc.changeSimilarity(args);
=======
		
		
		
>>>>>>> ec92234eb734353e24bef312dbd2c9e6be6a81eb
		String r = tc.getReasoner(args);
		String stSim =tc.getStSim(args);
		String cpSim = tc.getCpSim(args);
		
<<<<<<< HEAD
		try {
			pt.PerformanceEvaluatorMethod(filenames, ft, output_filename,r,null,null);
=======
		PerformanceTest pt = new PerformanceTest(); 
		
		try {
			pt.PerformanceEvaluatorMethod(filenames, ft, output_filename,r,stSim,cpSim);
>>>>>>> ec92234eb734353e24bef312dbd2c9e6be6a81eb
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	
	
	
	}


}
