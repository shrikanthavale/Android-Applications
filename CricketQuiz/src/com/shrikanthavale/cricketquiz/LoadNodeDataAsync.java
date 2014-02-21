/**
 * 
 */
package com.shrikanthavale.cricketquiz;

import java.util.HashMap;
import java.util.Map;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.shrikanthavale.cricketquiz.webserviceutility.SalesManagementReadData;

/**
 * @author Shrikant Havale
 * 
 *         This class loads the node data setting up the maximum amount that can
 *         be earned and text for the customer node
 */
public class LoadNodeDataAsync extends
		AsyncTask<Void, Integer, Map<String, Integer>> {

	/**
	 * create a progress bar
	 */
	private ProgressDialog progressBar;

	/**
	 * play grid activity
	 */
	private PlayGridActivity playGridActivity;

	/**
	 * node label and max amount map
	 */
	private Map<String, Integer> nodeMaxAmountMap = new HashMap<String, Integer>();

	/**
	 * exception variable
	 */
	private Exception exceptionThrown;

	/**
	 * Constructor to initialize the progress bar and activity
	 * 
	 * @param mainActivity
	 */
	public LoadNodeDataAsync(PlayGridActivity mainActivity) {
		this.playGridActivity = mainActivity;
		progressBar = new ProgressDialog(mainActivity);
		progressBar.setMessage("Loading Node Details .... Please Wait");
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
	protected Map<String, Integer> doInBackground(Void... params) {
		// get the details using web service call
		try {
			nodeMaxAmountMap = SalesManagementReadData.getMapNodeMaxAmount();
		} catch (Exception e) {
			exceptionThrown = e;
		}
		return nodeMaxAmountMap;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(Map<String, Integer> result) {

		if (exceptionThrown != null) {
			Toast.makeText(
					playGridActivity,
					"Some Error Occured : Plese check your Internet connection or Refresh from options menu.",
					Toast.LENGTH_LONG).show();
		} else {
			// update the result to UI
			playGridActivity.setNodeMaxAmountMap(result);
			playGridActivity.initializeCustomerNodesText();

		}
		progressBar.dismiss();
		super.onPostExecute(result);
	}
}
