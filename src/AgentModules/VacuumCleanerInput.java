package AgentModules;

import org.jLOAF.inputs.AtomicInput;
import org.jLOAF.inputs.ComplexInput;
import org.jLOAF.inputs.Feature;
import org.jLOAF.inputs.Input;
import org.jLOAF.sim.ComplexSimilarityMetricStrategy;
import org.jLOAF.sim.SimilarityMetricStrategy;

/*
 * VacuumCleanerInput Object
 * represents the high level input that the vacuumCleaner agent will receive
 * @author Ibrahim Ali Fawaz
 * @since 2017 May
 */

public class VacuumCleanerInput extends ComplexInput {
	
	private static final long serialVersionUID = 1L;
	public static final String NAME="Surroundings";
	private static ComplexSimilarityMetricStrategy simMet;

	
	
	/*
	 * Constructor
	 * @param name the name associated with the input
	 * 
	 */
	public VacuumCleanerInput(String name,ComplexSimilarityMetricStrategy sim) {
		super(name,sim);
		
	}
	

}
