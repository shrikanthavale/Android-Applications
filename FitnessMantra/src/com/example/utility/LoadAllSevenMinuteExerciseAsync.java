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

import com.example.fragments.SevenMinuteListFragment;

/**
 * @author Shrikant Havale
 * 
 *         This is ASYNC task for loading the all the questions list along with
 *         the title and organization in the form of list to be displayed for
 *         editing
 */
public class LoadAllSevenMinuteExerciseAsync extends
		AsyncTask<Void, Integer, List<String>> {

	/**
	 * create a progress bar
	 */
	private ProgressDialog progressBar;

	/**
	 * Fragment which is used for displaying this list
	 */
	private SevenMinuteListFragment sevenMinuteExerciseListFragment;

	public LoadAllSevenMinuteExerciseAsync(SevenMinuteListFragment fragment) {
		sevenMinuteExerciseListFragment = fragment;
		progressBar = new ProgressDialog(
				sevenMinuteExerciseListFragment.getActivity());
		progressBar.setMessage("Loading 7 Minute .... Please Wait");
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
		List<String> sevenMinuteExerciseMainList = new ArrayList<String>();
		try {
			AssetManager am = sevenMinuteExerciseListFragment.getActivity()
					.getAssets();
			InputStream is = am.open("SevenMinuteExerciseList.txt");
			InputStreamReader ipsr = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(ipsr);
			String line = null;
			while ((line = reader.readLine()) != null) {
				sevenMinuteExerciseMainList.add(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return sevenMinuteExerciseMainList;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(List<String> sevenMinuteExerciseMainList) {
		sevenMinuteExerciseListFragment
				.updateAdapterListData(sevenMinuteExerciseMainList);
		progressBar.dismiss();
		super.onPostExecute(sevenMinuteExerciseMainList);
	}
}
