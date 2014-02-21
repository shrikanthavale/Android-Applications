/**
 * 
 */
package com.shrikanthavale.cricketquiz.administration;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.shrikanthavale.cricketquiz.entities.SalesManagementQuestion;
import com.shrikanthavale.cricketquiz.webserviceutility.SalesManagementWriteData;

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
	 * exception to be thrown
	 */
	private Exception exceptionThrown;

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
	protected Boolean doInBackground(SalesManagementQuestion... params) {
		Boolean result = false;
		try {
			result = SalesManagementWriteData
					.saveNodeQuestionDetails(params[0]);
		} catch (Exception e) {
			Toast.makeText(
					nodeDescriptionFragment.getActivity(),
					"Some Error Occured : Plese check your Internet connection.",
					Toast.LENGTH_LONG).show();
		}
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
		if (exceptionThrown != null) {
			Toast.makeText(
					nodeDescriptionFragment.getActivity(),
					"Some Error Occured : Plese check your Internet connection.",
					Toast.LENGTH_LONG).show();

		} else {
			progressBar.setMessage("Data Saved Successfully");
		}
		progressBar.dismiss();
		nodeDescriptionFragment.requestRefreshNodeList();
		super.onPostExecute(result);
	}
}
