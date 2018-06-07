package PerformanceTesting;

import java.io.IOException;

import org.jLOAF.Agent;
import org.jLOAF.casebase.CaseBase;
import org.jLOAF.performance.PerformanceEvaluator;
import org.jLOAF.preprocessing.filter.CaseBaseFilter;
import org.jLOAF.preprocessing.filter.featureSelection.HillClimbingFeatureSelection;

import AgentModules.VacuumCleanerAgent;
import CaseBaseCreation.LogFile2CaseBase;



/***
 * class PerformanceTest will create an agent with one caseBase and use a testBase to measure its performance. It will output all the performance measures such as
 * accuracy, recall, precision, f-measure.
 * @author Ibrahim Ali Fawaz								
 * @since 2017 May 
 ***/
public class PerformanceTest extends PerformanceEvaluator {
	
	
	public static void main(String[] args){
		
		
<<<<<<< HEAD
<<<<<<< HEAD
		String [] filenames = {"trace-m0-WallFollowerAgent.txt","trace-m1-WallFollowerAgent.txt", "trace-m0-WallFollowerAgent.txt"};
=======
		String [] filenames = {"trace-m0-WallFollowerAgent.txt","trace-m1-WallFollowerAgent.txt"};
>>>>>>> ec92234eb734353e24bef312dbd2c9e6be6a81eb
=======
		String [] filenames = {"trace-m0-MultipleSequenceAgent.txt","trace-m1-MultipleSequenceAgent.txt"};
>>>>>>> b885ed28f4fe9277625a84875b589520d4fb0a14
		
		PerformanceTest pt = new PerformanceTest();
		CaseBaseFilter ft = new HillClimbingFeatureSelection(null);
		try {
<<<<<<< HEAD
			
			pt.PerformanceEvaluatorMethod(filenames,null,"vcOutput.txt","weightedKNN",null,null);
=======

<<<<<<< HEAD
			pt.PerformanceEvaluatorMethod(filenames,ft,"vcOutput.txt",null,null,null);
>>>>>>> ec92234eb734353e24bef312dbd2c9e6be6a81eb
=======
			pt.PerformanceEvaluatorMethod(filenames,null,"vcOutput4.csv","weightedKNN","weighted",null);
>>>>>>> b885ed28f4fe9277625a84875b589520d4fb0a14

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public VacuumCleanerAgent createAgent() {
		VacuumCleanerAgent agent = new VacuumCleanerAgent();
	
		return agent;
	}

	@Override
	public String[] createArrayOfCasebaseNames(String[] filenames) throws IOException {
		LogFile2CaseBase lfcb = new LogFile2CaseBase();
		String[] outputs = new String[filenames.length];
		
		String outputFile ="vcb";
		int i=0;
		
		for(String s:filenames){
			outputs[i]=lfcb.parseLogFile(s,outputFile+i+".cb");
					i++;
		}
		
			return outputs;
	}

	

}
