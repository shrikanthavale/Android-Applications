/**
 * 
 */
package com.example.fragments;

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

import com.example.bodymassindex.R;

/**
 * @author Shrikant Havale
 * 
 */
public class ExerciseDetailsFragment extends Fragment {

	private View exerciseDetailsView;

	private ImageView imageView1;

	private ImageView imageView2;

	private TextView instructionDescrption;

	private TextView emptyErrorText;

	private TextView headingExercise;

	private TextView guideHeading;

	private TextView instructionHeading;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// inflate the view
		exerciseDetailsView = inflater.inflate(
				R.layout.exercise_details_fragment, container, false);

		imageView1 = (ImageView) exerciseDetailsView
				.findViewById(R.id.firstImage);

		imageView2 = (ImageView) exerciseDetailsView
				.findViewById(R.id.secondImage);

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

	public void populateData(String completeExerciseText) {

		emptyErrorText.setVisibility(View.GONE);
		headingExercise.setVisibility(View.VISIBLE);
		guideHeading.setVisibility(View.VISIBLE);
		instructionHeading.setVisibility(View.VISIBLE);

		String heading = completeExerciseText.split(":")[0].trim();
		String imagePath1 = completeExerciseText.split(":")[2].trim();
		String imagePath2 = completeExerciseText.split(":")[3].trim();
		String instructionsFilePath = completeExerciseText.split(":")[4].trim();
		String instructions = "";

		headingExercise.setText(heading);

		imageView1.setImageResource(getActivity().getResources().getIdentifier(
				imagePath1, "drawable", getActivity().getPackageName()));

		imageView2.setImageResource(getActivity().getResources().getIdentifier(
				imagePath2, "drawable", getActivity().getPackageName()));

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
			e.printStackTrace();
		}

		instructionDescrption.setText(instructions);

	}

	private void loadPhoto(ImageView imageView) {

		AlertDialog.Builder imageDialog = new AlertDialog.Builder(
				this.getActivity());

		LayoutInflater inflater = (LayoutInflater) this.getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View layout = inflater.inflate(R.layout.sevenminute_image_dialogue,
				(ViewGroup) getActivity().findViewById(R.id.sevenminute_image));

		ImageView image = (ImageView) layout.findViewById(R.id.fullimage);

		image.setImageDrawable(imageView.getDrawable());

		imageDialog.setView(layout);

		imageDialog.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}

				});

		imageDialog.create();
		imageDialog.show();

	}
}
