package com.example.utility;

/**
 * This is used to communicates the fragment with other activity, which in turns
 * communicates to other fragments
 * 
 * @author Shrikant Havale
 * 
 */
public interface FragmentCommunicationInterface {

	/**
	 * Communicate the node description based on the nodes selected for editing
	 * 
	 * @param salesManagementQuestion
	 *            node details containing question details
	 */
	public void communicateExercise(String exerciseName);

}
