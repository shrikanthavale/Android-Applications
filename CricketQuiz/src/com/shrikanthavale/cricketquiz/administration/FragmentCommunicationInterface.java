/**
 * 
 */
package com.shrikanthavale.cricketquiz.administration;

import java.util.List;

import com.shrikanthavale.cricketquiz.entities.SalesManagementQuestion;
import com.shrikanthavale.cricketquiz.entities.SalesManagementQuestionOptions;

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
	public void communicateCustomerDescription(
			SalesManagementQuestion salesManagementQuestion);

	/**
	 * Communicates the options for the selected node for editing the options
	 * 
	 * @param salesManagementQuestionOptions
	 *            list of the options for editing particular the node
	 */
	public void communicateCustomerOptions(
			List<SalesManagementQuestionOptions> salesManagementQuestionOptions);

	/**
	 * This will refresh the nodes after editing the node details
	 */
	public void refreshNodeList();

}
