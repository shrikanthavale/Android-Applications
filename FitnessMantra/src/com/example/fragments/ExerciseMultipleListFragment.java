/**
 * 
 */
package com.example.fragments;

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
import com.example.utility.ExpandableListAdapter;
import com.example.utility.FragmentCommunicationInterface;
import com.example.utility.LoadAllMultipleListExerciseAsync;

/**
 * @author Shrikant Havale
 * 
 */
public class ExerciseMultipleListFragment extends Fragment implements
		OnChildClickListener {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader = new ArrayList<String>();
	HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();
	View exerciseMultipleListFragmentView;
	LoadAllMultipleListExerciseAsync loadMultipleExerciseData;
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

		listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader,
				listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);
		expListView.setOnChildClickListener(this);

		loadMultipleExerciseData = new LoadAllMultipleListExerciseAsync(this);
		loadMultipleExerciseData.execute();

		return exerciseMultipleListFragmentView;
	}

	public void updateAdapterListData(
			HashMap<String, List<String>> exerciseMainList) {

		// clear
		listDataHeader.clear();
		listDataChild.clear();

		listDataHeader.addAll(exerciseMainList.keySet());
		for (String temp : exerciseMainList.keySet()) {
			listDataChild.put(temp, exerciseMainList.get(temp));
		}

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

		String completeText = (String) expandableList
				.getExpandableListAdapter().getChild(groupPosition,
						childPosition);

		communicationInterface.communicateExercise(completeText);

		return true;
	}
}
