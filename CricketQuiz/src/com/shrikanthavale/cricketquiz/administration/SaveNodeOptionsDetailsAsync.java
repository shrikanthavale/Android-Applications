/**
 * 
 */
package com.shrikanthavale.cricketquiz.administration;

import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.shrikanthavale.cricketquiz.entities.SalesManagementQuestionOptions;
import com.shrikanthavale.cricketquiz.webserviceutility.SalesManagementWriteData;

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

	/**
	 * exception to be thrown
	 */
	private Exception exceptionThrown;

	public SaveNodeOptionsDetailsAsync(NodeOptionsFragment fragment) {
		nodeOptionsFragment = fragment;
		progressBar = new ProgressDialog(nodeOptionsFragment.getActivity());
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
	protected Boolean doInBackground(
			List<SalesManagementQuestionOptions>... params) {
		Boolean result = false;
		try {
			result = SalesManagementWriteData
					.saveNodeQuestionOptions(params[0]);
		} catch (Exception e) {
			exceptionThrown = e;
		}
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
		if (exceptionThrown != null) {
			Toast.makeText(
					nodeOptionsFragment.getActivity(),
					"Some Error Occured : Plese check your Internet connection.",
					Toast.LENGTH_LONG).show();

		} else {
			progressBar.setMessage("Data Saved Successfully");
		}
		progressBar.dismiss();
		super.onPostExecute(result);
	}
}
