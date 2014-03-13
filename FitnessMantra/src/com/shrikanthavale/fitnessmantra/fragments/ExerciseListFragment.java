package com.shrikanthavale.fitnessmantra.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.shrikanthavale.fitnessmantra.bodymassindex.R;
import com.shrikanthavale.fitnessmantra.utility.ExerciseListAdapter;
import com.shrikanthavale.fitnessmantra.utility.LoadAllExerciseAsync;

/**
 * @author Shrikant Havale
 * 
 *         This class is used to display the list of exericses. Currently NOT
 *         USED, instead multiple list fragment is used
 */
public class ExerciseListFragment extends ListFragment {

	/**
	 * List adapter for creating the data format
	 */
	private ExerciseListAdapter exerciseListAdapter;

	/**
	 * data format required for node list adapter
	 */
	private List<String> exerciseListString = new ArrayList<String>();

	/**
	 * ASYNC task for loading exercise details
	 */
	private LoadAllExerciseAsync loadExerciseDetailsAsync;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		// create the adapter
		exerciseListAdapter = new ExerciseListAdapter(getActivity(),
				R.layout.exercise_fragment, exerciseListString);

		setListAdapter(exerciseListAdapter);

		// load the data
		loadExerciseDetailsAsync = new LoadAllExerciseAsync(this);
		loadExerciseDetailsAsync.execute();
	}

	@Override
	public void onListItemClick(ListView listView, View v, int position, long id) {
		// do something with the data
		String completeText = (String) listView.getItemAtPosition(position);
		System.out.println(completeText);
	}

	/**
	 * Once the ASYNC task is completed this method populates the data to the
	 * list
	 * 
	 * @param tempExerciseList
	 *            - List of all the questions returned by the AsyncTask
	 */
	public void updateAdapterListData(List<String> tempExerciseList) {

		// clear before populating again
		exerciseListString.clear();

		for (String temp : tempExerciseList) {
			exerciseListString.add(temp);
		}

		// notify the adapter data changed
		exerciseListAdapter.notifyDataSetChanged();
	}
}
