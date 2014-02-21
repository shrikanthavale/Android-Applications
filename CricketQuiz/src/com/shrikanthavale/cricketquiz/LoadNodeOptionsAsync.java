/**
 * 
 */
package com.shrikanthavale.cricketquiz;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.shrikanthavale.cricketquiz.administration.NodesListFragment;
import com.shrikanthavale.cricketquiz.entities.SalesManagementQuestionOptions;
import com.shrikanthavale.cricketquiz.webserviceutility.SalesManagementReadData;

/**
 * @author Shrikant Havale
 * 
 *         This is ASYNC task for loading the options of the question
 *         associated. Loading is separated with activity UI
 */
public class LoadNodeOptionsAsync extends
		AsyncTask<String, Integer, List<SalesManagementQuestionOptions>> {

	/**
	 * create a progress bar
	 */
	private ProgressDialog progressBar;

	/**
	 * case study options activity for updating the view with result
	 */
	private CaseStudyOptionsActivity caseStudyOptions;

	/**
	 * Node list fragment for populating the options
	 */
	private NodesListFragment nodesListFragment;

	/**
	 * exception to be thrown
	 */
	private Exception exceptionThrown;

	/**
	 * Constructor for the options activity
	 * 
	 * @param mainActivity
	 */
	public LoadNodeOptionsAsync(CaseStudyOptionsActivity mainActivity) {
		caseStudyOptions = mainActivity;
		progressBar = new ProgressDialog(mainActivity);
		progressBar.setMessage("Loading Option Details .... Please Wait");
		progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressBar.setCancelable(false);
	}

	/**
	 * Constructor for the fragment activity
	 * 
	 * @param nodesListFragment
	 */
	public LoadNodeOptionsAsync(NodesListFragment nodesListFragment) {
		this.nodesListFragment = nodesListFragment;
		progressBar = new ProgressDialog(nodesListFragment.getActivity());
		progressBar.setMessage("Loading Option Details .... Please Wait");
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
	protected List<SalesManagementQuestionOptions> doInBackground(
			String... params) {

		// get the question options using web service call
		List<SalesManagementQuestionOptions> salesManagementQuestionOptions = new ArrayList<SalesManagementQuestionOptions>();
		try {
			salesManagementQuestionOptions = SalesManagementReadData
					.getSalesManagementQuestionOptionsMoneyEvaluation(params[0]);
		} catch (Exception e) {
			exceptionThrown = e;
		}
		return salesManagementQuestionOptions;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(
			List<SalesManagementQuestionOptions> salesManagementQuestionOptions) {

		if (exceptionThrown != null) {
			Toast.makeText(
					caseStudyOptions,
					"Some Error Occured : Plese check your Internet connection or Refresh from options menu.",
					Toast.LENGTH_LONG).show();
		}

		// publish the data for activity
		if (caseStudyOptions != null) {
			caseStudyOptions
					.setSalesManagementQuestionOptions(salesManagementQuestionOptions);
			caseStudyOptions.populateQuestionOptions();
		}

		// publish the data for fragment
		if (nodesListFragment != null) {
			nodesListFragment
					.communicateNodeOptionsFragment(salesManagementQuestionOptions);
		}
		progressBar.dismiss();
		super.onPostExecute(salesManagementQuestionOptions);
	}
}
