package com.shrikanthavale.fitnessmantra.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.example.bodymassindex.R;
import com.shrikanthavale.fitnessmantra.utility.ExpandableListAdapter;
import com.shrikanthavale.fitnessmantra.utility.FragmentCommunicationInterface;
import com.shrikanthavale.fitnessmantra.utility.LoadAllMultipleListExerciseAsync;

/**
 * @author Shrikant Havale
 * 
 *         This class represnts a expandable list fragment, list inside list.
 *         used to represent main group name and their sub child
 * 
 */
public class ExerciseMultipleListFragment extends Fragment implements
		OnChildClickListener {

	/**
	 * expandable list adapter
	 */
	private ExpandableListAdapter listAdapter;

	/**
	 * expandable list view
	 */
	private ExpandableListView expListView;

	/**
	 * list of group headers
	 */
	private List<String> listDataHeader = new ArrayList<String>();

	/**
	 * list of child elements
	 */
	private HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();

	/**
	 * multiple list fragment view
	 */
	private View exerciseMultipleListFragmentView;

	/**
	 * load data async task
	 */
	private LoadAllMultipleListExerciseAsync loadMultipleExerciseData;

	/**
	 * communication interface
	 */
	private FragmentCommunicationInterface communicationInterface;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// inflate the view
		exerciseMultipleListFragmentView = inflater.inflate(
				R.layout.exercise_multiple_list_fragment, container, false);

		// get the list view
		expListView = (ExpandableListView) exerciseMultipleListFragmentView
				.findViewById(R.id.expandableListView);

		// create the adapter
		listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader,
				listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);
		expListView.setOnChildClickListener(this);

		// load the multiple list data
		loadMultipleExerciseData = new LoadAllMultipleListExerciseAsync(this);
		loadMultipleExerciseData.execute();

		return exerciseMultipleListFragmentView;
	}

	/**
	 * Called from ASYNC task for loading of the data
	 * 
	 * @param exerciseMainList
	 */
	public void updateAdapterListData(
			HashMap<String, List<String>> exerciseMainList) {

		// clear
		listDataHeader.clear();
		listDataChild.clear();

		listDataHeader.addAll(exerciseMainList.keySet());
		for (String temp : exerciseMainList.keySet()) {
			listDataChild.put(temp, exerciseMainList.get(temp));
		}

		// notify the adapter
		listAdapter.notifyDataSetChanged();
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

	@Override
	public void onDetach() {
		super.onDetach();
		communicationInterface = null;
	}

	@Override
	public boolean onChildClick(ExpandableListView expandableList, View view,
			int groupPosition, int childPosition, long rowId) {

		// get the selected element
		String completeText = (String) expandableList
				.getExpandableListAdapter().getChild(groupPosition,
						childPosition);

		// use the communication interface
		communicationInterface.communicateExercise(completeText);

		return true;
	}
}
