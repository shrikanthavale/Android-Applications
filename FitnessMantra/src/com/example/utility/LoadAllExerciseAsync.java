/**
 * 
 */
package com.example.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import com.example.fragments.ExerciseListFragment;

/**
 * @author Shrikant Havale
 * 
 *         This is ASYNC task for loading the all the questions list along with
 *         the title and organization in the form of list to be displayed for
 *         editing
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
		List<String> exerciseMainList = new ArrayList<String>();
		try {

			Thread.sleep(2000);
			AssetManager am = exerciseListFragment.getActivity().getAssets();
			InputStream is = am.open("ExerciseMainList.txt");
			InputStreamReader ipsr = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(ipsr);
			String line = null;
			while ((line = reader.readLine()) != null) {
				exerciseMainList.add(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return exerciseMainList;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(List<String> exerciseMainList) {
		exerciseListFragment.updateAdapterListData(exerciseMainList);
		progressBar.dismiss();
		super.onPostExecute(exerciseMainList);
	}
}
