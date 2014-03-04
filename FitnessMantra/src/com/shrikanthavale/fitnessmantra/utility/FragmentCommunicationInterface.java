package com.shrikanthavale.fitnessmantra.utility;

/**
 * This is used to communicates the fragment with other activity, which in turns
 * communicates to other fragments
 * 
 * @author Shrikant Havale
 * 
 */
public interface FragmentCommunicationInterface {

	/**
	 * Communicate the exercise based on the selected data
	 * 
	 * @param salesManagementQuestion
	 *            node details containing question details
	 */
	public void communicateExercise(String exerciseName);

}
