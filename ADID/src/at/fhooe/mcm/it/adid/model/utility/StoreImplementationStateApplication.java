package at.fhooe.mcm.it.adid.model.utility;

/**
 * This class is responsible for storing the state of implementation phase of the application.
 * It remembers the questions that were answered , levels cleared and try to restore them 
 * when user comes back on to same stage again. So that user has not to answer all the questions
 * again as he comes back to this phase again. 
 * 
 * @author Shrikant Havale
 *
 */
public class StoreImplementationStateApplication {
	
	/**
	 * variable for question1Answered
	 */
	public static boolean question1Answered = false;
	
	/**
	 * variable for question2Answered
	 */
	public static boolean question2Answered = false;
	
	/**
	 * variable for question3Answered
	 */
	public static boolean question3Answered = false;
	
	/**
	 * variable for stage cleared
	 */
	public static boolean stageCleared = false;
	

}
