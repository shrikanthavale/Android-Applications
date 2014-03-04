package com.shrikanthavale.fitnessmantra.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bodymassindex.R;
import com.shrikanthavale.fitnessmantra.utility.LoadAllSevenMinuteExerciseAsync;
import com.shrikanthavale.fitnessmantra.utility.SevenMinuteExerciseListAdapter;

/**
 * @author Shrikant Havale
 * 
 *         This class is list fragment containing list of seven minute exercises
 */
public class SevenMinuteListFragment extends ListFragment {

	/**
	 * List adapter for creating the data format
	 */
	private SevenMinuteExerciseListAdapter sevenMinuteExerciseListAdapter;

	/**
	 * data format required for node list adapter
	 */
	private List<String> sevenMinuteExerciseListString = new ArrayList<String>();

	/**
	 * ASYNC task for loading exercise details
	 */
	private LoadAllSevenMinuteExerciseAsync loadSevenMinuteExerciseDetailsAsync;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		sevenMinuteExerciseListAdapter = new SevenMinuteExerciseListAdapter(
				getActivity(), R.layout.sevenminute_fragment,
				sevenMinuteExerciseListString);

		setListAdapter(sevenMinuteExerciseListAdapter);
		loadSevenMinuteExerciseDetailsAsync = new LoadAllSevenMinuteExerciseAsync(
				this);
		loadSevenMinuteExerciseDetailsAsync.execute();
	}

	@Override
	public void onListItemClick(ListView listView, View v, int position, long id) {

		// get the data
		String completeText = (String) listView.getItemAtPosition(position);

		// split the data
		String imageName = completeText.split(":")[2].trim();
		String imageTitle = completeText.split(":")[0].trim();

		// open the image
		loadPhoto(imageName, imageTitle);

	}

	/**
	 * Displays a image and title in alert dialog
	 * 
	 * @param imageName
	 *            - accepts the image name to be displayed
	 * @param imageTitle
	 *            - accepts the image title
	 */
	private void loadPhoto(String imageName, String imageTitle) {

		// create the alert dialog
		AlertDialog.Builder imageDialog = new AlertDialog.Builder(
				this.getActivity());

		// inflater
		LayoutInflater inflater = (LayoutInflater) this.getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View layout = inflater.inflate(R.layout.sevenminute_image_dialogue,
				(ViewGroup) getActivity().findViewById(R.id.sevenminute_image));

		// view elements
		TextView textView = (TextView) layout
				.findViewById(R.id.custom_fullimage_placename);
		ImageView image = (ImageView) layout.findViewById(R.id.fullimage);

		// set the data
		image.setImageResource(getActivity().getResources().getIdentifier(
				imageName, "drawable", getActivity().getPackageName()));
		textView.setText(imageTitle);

		imageDialog.setView(layout);

		// add OK button
		imageDialog.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}

				});

		// show the dialog
		imageDialog.create();
		imageDialog.show();

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
		sevenMinuteExerciseListString.clear();

		for (String temp : tempExerciseList) {
			sevenMinuteExerciseListString.add(temp);
		}

		// notify the adapter data changed
		sevenMinuteExerciseListAdapter.notifyDataSetChanged();
	}
}
