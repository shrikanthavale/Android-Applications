package com.shrikanthavale.fitnessmantra.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.widget.Toast;

import com.shrikanthavale.fitnessmantra.fragments.ExerciseListFragment;

/**
 * @author Shrikant Havale
 * 
 *         This is ASYNC task for loading the all the exercises along with the
 *         title and difficulty leve in the form of list to be displayed for
 *         editing. Currently NOT USED
 */
public class LoadAllExerciseAsync extends
		AsyncTask<Void, Integer, List<String>> {

	/**
	 * create a progress bar
	 */
	private ProgressDialog progressBar;

	/**
	 * Fragment which is used for displaying this list
	 */
	private ExerciseListFragment exerciseListFragment;

	public LoadAllExerciseAsync(ExerciseListFragment fragment) {
		exerciseListFragment = fragment;
		progressBar = new ProgressDialog(exerciseListFragment.getActivity());
		progressBar.setMessage("Loading Exercises .... Please Wait");
		progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressBar.setCancelable(true);
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
	protected List<String> doInBackground(Void... params) {

		// empty list
		List<String> exerciseMainList = new ArrayList<String>();

		try {
			// asset manager
			AssetManager am = exerciseListFragment.getActivity().getAssets();

			// open the file
			InputStream is = am.open("ExerciseMainList.txt");

			// read the file
			InputStreamReader ipsr = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(ipsr);
			String line = null;
			while ((line = reader.readLine()) != null) {
				exerciseMainList.add(line);
			}

			is.close();
			ipsr.close();
			reader.close();

		} catch (IOException e) {
			Toast.makeText(
					exerciseListFragment.getActivity(),
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
	protected void onPostExecute(List<String> exerciseMainList) {
		// after successful loading call the update method of the fragment
		exerciseListFragment.updateAdapterListData(exerciseMainList);
		progressBar.dismiss();
		super.onPostExecute(exerciseMainList);
	}
}
