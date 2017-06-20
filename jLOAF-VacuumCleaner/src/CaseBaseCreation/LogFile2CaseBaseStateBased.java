package CaseBaseCreation;

import java.util.ArrayList;

import org.jLOAF.casebase.Case;
import org.jLOAF.casebase.CaseBase;
import org.jLOAF.inputs.AtomicInput;
import org.jLOAF.inputs.ComplexInput;
import org.jLOAF.inputs.Feature;

import AgentModules.StateBasedVacuumCleanerInput;
import AgentModules.VacuumCleanerAction;
import AgentModules.VacuumCleanerInput;
import AgentModules.VacuumCleanerAction.Actions;

public class LogFile2CaseBaseStateBased extends LogFile2CaseBase{
	
	@Override
	public void createCase(CaseBase cb2, double[] entry){
		VacuumCleanerAction act= new VacuumCleanerAction(Actions.values()[(int)entry[8]-1].getAction());
		VacuumCleanerInput vci = new VacuumCleanerInput(VacuumCleanerInput.NAME,vacumStrategy);
		StateBasedVacuumCleanerInput svci = new StateBasedVacuumCleanerInput(VacuumCleanerInput.NAME,vacumStrategy);
		for(int i=0;i<entry.length-1;i=i+2){
			ComplexInput ci =Inputs.values()[i/2].setFeat(complexStrategy);
			 AtomicInput a1 = new AtomicInput("Distance",new Feature(entry[i]-1),atomicStrategy);
			 AtomicInput a2 = new AtomicInput("Object",new Feature(entry[i+1]-1),atomicStrategy);
			 ci.add(a1);
			 ci.add(a2);
			 vci.add(ci);
			//System.out.println(vci.getChildNames().size());
		}
			if(cb2.getSize()>0){
				
				//svci.setTrace(new ArrayList<Object>(((StateBasedVacuumCleanerInput) new ArrayList<Case>(cb2.getCases()).get(cb2.getSize()-1).getInput()).getTrace()));
				//svci.addTrace(new ArrayList<Case>(cb2.getCases()).get(cb2.getSize()-1).getAction());
			}
			
			//svci.addTrace(vci);
		Case c =new Case(svci,act);
		
		cb2.add(c);
	}

}
