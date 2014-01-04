/**
 * 
 */
package com.shrikanthavale.salesmanagement.administration;

import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.shrikanthavale.salesmanagement.entities.SalesManagementQuestionOptions;
import com.shrikanthavale.salesmanagement.webserviceutility.SalesManagementWriteData;

/**
 * @author Shrikant Havale
 * 
 *         This class is Asynchronous task for saving the details into database.
 *         This saves the changes related to the node question
 */
public class SaveNodeOptionsDetailsAsync extends
		AsyncTask<List<SalesManagementQuestionOptions>, Integer, Boolean> {

	/**
	 * create a progress bar
	 */
	private ProgressDialog progressBar;

	/**
	 * fragment from where this ASYNC call is made
	 */
	private NodeOptionsFragment nodeOptionsFragment;

	public SaveNodeOptionsDetailsAsync(NodeOptionsFragment fragment) {
		nodeOptionsFragment = fragment;
		progressBar = new ProgressDialog(nodeOptionsFragment.getActivity());
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
	protected Boolean doInBackground(
			List<SalesManagementQuestionOptions>... params) {
		Boolean result = SalesManagementWriteData
				.saveNodeQuestionOptions(params[0]);
		if (result)
			nodeOptionsFragment.setSalesManagementQuestionOptions(params[0]);
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
		super.onPostExecute(result);
	}
}
