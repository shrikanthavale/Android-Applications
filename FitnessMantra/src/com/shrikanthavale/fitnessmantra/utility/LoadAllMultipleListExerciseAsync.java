package com.shrikanthavale.fitnessmantra.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

import com.shrikanthavale.fitnessmantra.fragments.ExerciseMultipleListFragment;

/**
 * @author Shrikant Havale
 * 
 *         This is ASYNC task for loading the exercise list and sublist along
 *         with the group title , child title and difficulty level in the form
 *         of list to be displayed for editing
 */
public class LoadAllMultipleListExerciseAsync extends
		AsyncTask<Void, Integer, HashMap<String, List<String>>> {

	/**
	 * create a progress bar
	 */
	private ProgressDialog progressBar;

	/**
	 * Fragment which is used for displaying this list
	 */
	private ExerciseMultipleListFragment exerciseMultipleListFragment;

	public LoadAllMultipleListExerciseAsync(
			ExerciseMultipleListFragment fragment) {
		exerciseMultipleListFragment = fragment;
		progressBar = new ProgressDialog(
				exerciseMultipleListFragment.getActivity());
		progressBar.setMessage("Loading Exercises .... Please Wait");
		progressBar.getWindow().setGravity(Gravity.CENTER);
		WindowManager.LayoutParams wmlp = progressBar.getWindow()
				.getAttributes();
		wmlp.y = exerciseMultipleListFragment.getResources()
				.getDisplayMetrics().heightPixels / 4;
		progressBar.getWindow().setAttributes(wmlp);
		progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressBar.setCancelable(false);
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressBar.show();
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
	}

	@Override
	protected HashMap<String, List<String>> doInBackground(Void... params) {
		// map for group header and list under header
		HashMap<String, List<String>> exerciseMainList = new HashMap<String, List<String>>();
		try {

			// asset manager
			AssetManager am = exerciseMultipleListFragment.getActivity()
					.getAssets();

			// open file
			InputStream is = am.open("ExerciseMainList.txt");
			InputStreamReader ipsr = new InputStreamReader(is);

			// read file
			BufferedReader reader = new BufferedReader(ipsr);
			String line = null;

			// store data in map
			while ((line = reader.readLine()) != null) {

				List<String> subList = new ArrayList<String>();
				InputStream innerIS = am.open(line + ".txt");
				InputStreamReader innerIPSR = new InputStreamReader(innerIS);
				BufferedReader innerReader = new BufferedReader(innerIPSR);
				String innerLine = null;
				while ((innerLine = innerReader.readLine()) != null) {
					subList.add(innerLine);
				}
				innerIS.close();
				innerIPSR.close();
				innerReader.close();
				exerciseMainList.put(line, subList);
			}

			is.close();
			ipsr.close();
			reader.close();

		} catch (IOException e) {
			Toast.makeText(
					exerciseMultipleListFragment.getActivity(),
					"Exception occured while loading exercise details, please try again",
					Toast.LENGTH_LONG).show();
		}
		return exerciseMainList;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(HashMap<String, List<String>> exerciseMainList) {
		// update the list on successful loading of data
		exerciseMultipleListFragment.updateAdapterListData(exerciseMainList);
		progressBar.dismiss();
		super.onPostExecute(exerciseMainList);
	}
}
