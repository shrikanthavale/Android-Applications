/**
 * 
 */
package com.shrikanthavale.cricketquiz.administration;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.shrikanthavale.cricketquiz.LoadNodeDescriptionAsync;
import com.shrikanthavale.cricketquiz.LoadNodeOptionsAsync;
import com.shrikanthavale.cricketquiz.R;
import com.shrikanthavale.cricketquiz.entities.SalesManagementQuestion;
import com.shrikanthavale.cricketquiz.entities.SalesManagementQuestionOptions;
import com.shrikanthavale.cricketquiz.webserviceutility.SalesManagementSaveActivityState;

/**
 * @author Shrikant Havale
 * 
 *         This fragment is responsible for loading all the nodes list, with
 *         their title and organization in the form of list, so user can select
 *         one of the and choose to edit
 */
public class NodesListFragment extends ListFragment {

	/**
	 * communication interface to communicate to activity
	 */
	private FragmentCommunicationInterface communicationInterface;

	/**
	 * List adapter for creating the data format
	 */
	private NodeListAdapter nodeListAdapter;

	/**
	 * data format required for node list adapter
	 */
	private List<String> colonFormatterStringData = new ArrayList<String>();

	/**
	 * ASYNC task for loading all the question details
	 */
	private LoadAllNodeQuestionsAsync loadAllNodeQuestionsAsync;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		nodeListAdapter = new NodeListAdapter(getActivity(),
				R.layout.fragment_administration_node_list,
				colonFormatterStringData);

		setListAdapter(nodeListAdapter);

		loadAllNodeQuestionsAsync = new LoadAllNodeQuestionsAsync(this);
		loadAllNodeQuestionsAsync.execute();
	}

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);

		if (activity instanceof FragmentCommunicationInterface) {
			communicationInterface = (FragmentCommunicationInterface) activity;
		} else {
			throw new ClassCastException(activity.toString()
					+ " must implemenet MyListFragment.OnItemSelectedListener");
		}

	}

	/**
	 * Once the ASYNC task is completed this method populates the data to the
	 * list
	 * 
	 * @param salesManagementQuestions
	 *            - List of all the questions returned by the AsyncTask
	 */
	public void updateAdapterListData(
			List<SalesManagementQuestion> salesManagementQuestions) {

		// clear before populating again
		colonFormatterStringData.clear();

		// create colon formatted string
		for (SalesManagementQuestion temp : salesManagementQuestions) {

			String appendedString = temp.getCaseStudyNode()
					+ getResources().getString(R.string.colonstring)
					+ temp.getCaseStudyTitle()
					+ getResources().getString(R.string.colonstring)
					+ temp.getCaseStudyOrganization();

			colonFormatterStringData.add(appendedString);
		}

		// notify the adapter data changed
		nodeListAdapter.notifyDataSetChanged();
	}

	@Override
	public void onDetach() {
		super.onDetach();
		communicationInterface = null;
	}

	@Override
	public void onListItemClick(ListView listView, View v, int position, long id) {
		// do something with the data
		String completeText = (String) listView.getItemAtPosition(position);
		String customerSelected = completeText.split(getResources().getString(
				R.string.colonstring))[0];
		SalesManagementSaveActivityState
				.setCustomerAdministration(customerSelected);

		// Load ASYNC data for Description
		LoadNodeDescriptionAsync loadNodeDescriptionAsync = new LoadNodeDescriptionAsync(
				this);
		loadNodeDescriptionAsync.execute(customerSelected);

	}

	/**
	 * This method communicates the sales management question based on the
	 * selection made in the list.
	 * 
	 * @param salesManagementQuestion
	 *            - complete question to be communicated
	 */
	public void communicateNodeDescriptionFragment(
			SalesManagementQuestion salesManagementQuestion) {
		communicationInterface
				.communicateCustomerDescription(salesManagementQuestion);

		// Load ASYNC data for options
		LoadNodeOptionsAsync loadNodeOptionsAsync = new LoadNodeOptionsAsync(
				this);
		loadNodeOptionsAsync.execute(SalesManagementSaveActivityState
				.getCustomerAdministration());

	}

	/**
	 * This method communicates the sales management question based on the
	 * selection made in the list.
	 * 
	 * @param salesManagementQuestion
	 *            - complete question to be communicated
	 */
	public void communicateNodeOptionsFragment(
			List<SalesManagementQuestionOptions> salesManagementQuestionOptions) {
		communicationInterface
				.communicateCustomerOptions(salesManagementQuestionOptions);
		showToast();
	}

	/**
	 * refresh the nodes list
	 */
	public void refreshNodeList() {
		loadAllNodeQuestionsAsync = new LoadAllNodeQuestionsAsync(this);
		loadAllNodeQuestionsAsync.execute();
	}

	/**
	 * show toast text
	 */
	private void showToast() {

		Toast toast = Toast.makeText(this.getActivity(),
				"Data for the selected node successfully loaded",
				Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

}
