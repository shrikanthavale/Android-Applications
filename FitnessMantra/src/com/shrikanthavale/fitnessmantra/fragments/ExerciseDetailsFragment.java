package com.shrikanthavale.fitnessmantra.fragments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bodymassindex.R;

/**
 * @author Shrikant Havale
 * 
 *         This class displays and populates data in exercise details fragment
 *         based on the selected exercise.
 */
public class ExerciseDetailsFragment extends Fragment {

	/**
	 * exercise details view
	 */
	private View exerciseDetailsView;

	/**
	 * first image view
	 */
	private ImageView imageView1;

	/**
	 * second image view
	 */
	private ImageView imageView2;

	/**
	 * instructions explaining to carry out exercise
	 */
	private TextView instructionDescrption;

	/**
	 * error text view if none of the exercise is selected
	 */
	private TextView emptyErrorText;

	/**
	 * heading of exercise
	 */
	private TextView headingExercise;

	/**
	 * guide heading for visibility purpose
	 */
	private TextView guideHeading;

	/**
	 * instruction heading for visibility purpose
	 */
	private TextView instructionHeading;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// inflate the view
		exerciseDetailsView = inflater.inflate(
				R.layout.exercise_details_fragment, container, false);

		// getting image views
		imageView1 = (ImageView) exerciseDetailsView
				.findViewById(R.id.firstImage);

		imageView2 = (ImageView) exerciseDetailsView
				.findViewById(R.id.secondImage);

		// on click listeners
		imageView1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				loadPhoto(imageView1);
			}
		});

		imageView2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				loadPhoto(imageView2);
			}
		});

		instructionDescrption = (TextView) exerciseDetailsView
				.findViewById(R.id.instructionDescription);

		emptyErrorText = (TextView) exerciseDetailsView
				.findViewById(R.id.emptyErrorText);

		headingExercise = (TextView) exerciseDetailsView
				.findViewById(R.id.headingExercise);

		guideHeading = (TextView) exerciseDetailsView
				.findViewById(R.id.guideHeading);

		instructionHeading = (TextView) exerciseDetailsView
				.findViewById(R.id.instructionHeading);

		return exerciseDetailsView;
	}

	/**
	 * Populate data method called from exercise list fragment, giving the data
	 * details to be populated
	 * 
	 * @param completeExerciseText
	 *            Colon separated string containing important selected
	 *            information
	 */
	public void populateData(String completeExerciseText) {

		// control visibility
		emptyErrorText.setVisibility(View.GONE);
		headingExercise.setVisibility(View.VISIBLE);
		guideHeading.setVisibility(View.VISIBLE);
		instructionHeading.setVisibility(View.VISIBLE);

		// split string get data
		String heading = completeExerciseText.split(":")[0].trim();
		String imagePath1 = completeExerciseText.split(":")[2].trim();
		String imagePath2 = completeExerciseText.split(":")[3].trim();
		String instructionsFilePath = completeExerciseText.split(":")[4].trim();
		String instructions = "";

		// set the data
		headingExercise.setText(heading);

		// set the images
		imageView1.setImageResource(getActivity().getResources().getIdentifier(
				imagePath1, "drawable", getActivity().getPackageName()));

		imageView2.setImageResource(getActivity().getResources().getIdentifier(
				imagePath2, "drawable", getActivity().getPackageName()));

		// read the file path
		try {
			AssetManager am = getActivity().getAssets();
			InputStream is = am.open(instructionsFilePath);
			InputStreamReader ipsr = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(ipsr);
			String line = null;
			while ((line = reader.readLine()) != null) {
				instructions = instructions + line + "\n" + "\n";
			}
		} catch (IOException e) {
			Toast.makeText(
					getActivity(),
					"Exception occured while loading exercise details, please try again",
					Toast.LENGTH_LONG).show();
			;
		}

		// set instructions text
		instructionDescrption.setText(instructions);

	}

	/**
	 * Image view click listener showing the image on click event
	 * 
	 * @param imageView
	 *            accepts the image to be shown
	 */
	private void loadPhoto(ImageView imageView) {

		// creates the alert dialog with OK button
		AlertDialog.Builder imageDialog = new AlertDialog.Builder(
				this.getActivity());

		// use the layout xml
		LayoutInflater inflater = (LayoutInflater) this.getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View layout = inflater.inflate(R.layout.sevenminute_image_dialogue,
				(ViewGroup) getActivity().findViewById(R.id.sevenminute_image));

		// set the image
		ImageView image = (ImageView) layout.findViewById(R.id.fullimage);
		image.setImageDrawable(imageView.getDrawable());
		imageDialog.setView(layout);

		// create the OK button
		imageDialog.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}

				});

		// create the dialog and show
		imageDialog.create();
		imageDialog.show();

	}
}
