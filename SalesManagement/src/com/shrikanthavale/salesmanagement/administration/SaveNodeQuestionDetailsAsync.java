/**
 * 
 */
package com.shrikanthavale.salesmanagement.administration;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.shrikanthavale.salesmanagement.entities.SalesManagementQuestion;
import com.shrikanthavale.salesmanagement.webserviceutility.SalesManagementWriteData;

/**
 * @author Shrikant Havale
 * 
 *         This class is Asynchronous task for saving the details into database.
 *         This saves the changes related to the node question
 */
public class SaveNodeQuestionDetailsAsync extends
		AsyncTask<SalesManagementQuestion, Integer, Boolean> {

	/**
	 * create a progress bar
	 */
	private ProgressDialog progressBar;

	/**
	 * fragment from where this ASYNC call is made
	 */
	private NodeDescriptionFragment nodeDescriptionFragment;

	/**
	 * Constructor accepting the fragment details
	 * 
	 * @param fragment
	 *            fragment to be initialized with
	 */
	public SaveNodeQuestionDetailsAsync(NodeDescriptionFragment fragment) {
		nodeDescriptionFragment = fragment;
		progressBar = new ProgressDialog(nodeDescriptionFragment.getActivity());
		progressBar.setMessage("Saving Details .... Please Wait");
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
	protected Boolean doInBackground(SalesManagementQuestion... params) {
		Boolean result = SalesManagementWriteData
				.saveNodeQuestionDetails(params[0]);
		if (result)
			nodeDescriptionFragment.setSalesManagementQuestion(params[0]);
		return result;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(Boolean result) {
		progressBar.setMessage("Data Saved Successfully");
		progressBar.dismiss();
		nodeDescriptionFragment.requestRefreshNodeList();
		super.onPostExecute(result);
	}
}
