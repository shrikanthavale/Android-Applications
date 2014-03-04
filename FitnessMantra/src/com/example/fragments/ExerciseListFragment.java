/**
 * 
 */
package com.example.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.example.bodymassindex.R;
import com.example.utility.ExerciseListAdapter;
import com.example.utility.LoadAllExerciseAsync;

/**
 * @author Shrikant Havale
 * 
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

		exerciseListAdapter = new ExerciseListAdapter(getActivity(),
				R.layout.exercise_fragment, exerciseListString);

		setListAdapter(exerciseListAdapter);
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
