package PerformanceTesting;

import java.io.IOException;

import org.jLOAF.Agent;
import org.jLOAF.casebase.CaseBase;
import org.jLOAF.performance.PerformanceEvaluator;

import AgentModules.VacuumCleanerAgent;
import CaseBaseCreation.LogFile2CaseBase;
import CaseBaseCreation.LogFile2CaseBaseStateBased;


/***
 * class PerformanceTest will create an agent with one caseBase and use a testBase to measure its performance. It will output all the performance measures such as
 * accuracy, recall, precision, f-measure.
 * @author Ibrahim Ali Fawaz								
 * @since 2017 May 
 ***/
public class PerformanceTest extends PerformanceEvaluator {
	
	
	public static void main(String[] args){
		
		
		String matchType = "default";
		String [] cbname = {"vc3.txt","vc8.txt"};
		
		PerformanceTest pt = new PerformanceTest();
		try {
			pt.PerformanceEvaluatorMethod(cbname,null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		LogFile2CaseBaseStateBased lfcb = new LogFile2CaseBaseStateBased();
		String[] outputs = new String[filenames.length];
		
		String outputFile ="vcb";
		int i=0;
		
		for(String s:filenames){
			outputs[i]=lfcb.parseLogFile(s,outputFile+i);
					i++;
		}
		
			return outputs;
	}

	

}
