package AgentModules;

import org.jLOAF.Agent;
import org.jLOAF.MotorControl;
import org.jLOAF.Perception;
import org.jLOAF.Reasoning;
import org.jLOAF.action.Action;
import org.jLOAF.action.AtomicAction;
import org.jLOAF.casebase.CaseBase;
import org.jLOAF.inputs.AtomicInput;
import org.jLOAF.inputs.ComplexInput;
import org.jLOAF.inputs.Input;
import org.jLOAF.reasoning.BayesianReasoner;
import org.jLOAF.reasoning.SimpleKNN;
import org.jLOAF.reasoning.TBReasoning;
import org.jLOAF.sim.SimilarityMetricStrategy;
import org.jLOAF.sim.atomic.Equality;
import org.jLOAF.sim.complex.GreedyMunkrezMatching;
import org.jLOAF.sim.complex.Mean;
import org.jLOAF.sim.complex.WeightedMean;
import org.jLOAF.weights.SimilarityWeights;

/*
 * VacuumCleanerAgent Object
 * 
 * 
 * @author Ibrahim Ali Fawaz
 * @since 2017 May
 */
public class VacuumCleanerAgent extends Agent{
	
	
	/*
	 * constructor 
	 * 
	 * @param casebase the casebase with all the cases the agent has observed.
	 */
	public VacuumCleanerAgent() {
		super(null,null,null,null);
		
		this.mc = new VacuumCleanerMotorControl();
		
		this.p = new VacuumCleanerPerception();
	
	}
	
	

	@Override
	public Action run(Input input) {
		AtomicAction a = (AtomicAction) this.r.selectAction(input);
		return (VacuumCleanerAction) a;
	}

	@Override
	public void train(CaseBase casebase) {
		this.cb = casebase;
		this.r = new BayesianReasoner(casebase);
	}
	
	

}
