/**
 * 
 */
package com.shrikanthavale.salesmanagement;

import java.util.Map;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.shrikanthavale.salesmanagement.webserviceutility.SalesManagementReadData;

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
	private Map<String, Integer> nodeMaxAmountMap;

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
			Toast.makeText(
					playGridActivity,
					"Some Error Occured : " + e.getMessage()
							+ ". Plese check your Internet connection.",
					Toast.LENGTH_LONG).show();
		}
		return nodeMaxAmountMap;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(Map<String, Integer> result) {

		// update the result to UI
		playGridActivity.setNodeMaxAmountMap(result);
		playGridActivity.initializeCustomerNodesText();
		progressBar.hide();
		super.onPostExecute(result);
	}
}
