/**
 * 
 */
package com.shrikanthavale.cricketquiz;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.shrikanthavale.cricketquiz.administration.NodesListFragment;
import com.shrikanthavale.cricketquiz.entities.SalesManagementQuestion;
import com.shrikanthavale.cricketquiz.webserviceutility.SalesManagementReadData;

/**
 * @author Shrikant Havale
 * 
 *         This class loads the Node Description after clicking the customer
 *         node.
 */
public class LoadNodeDescriptionAsync extends
		AsyncTask<String, Integer, SalesManagementQuestion> {

	/**
	 * create a progress bar
	 */
	private ProgressDialog progressBar;

	/**
	 * case study description activity for updating the view with result
	 */
	private CaseStudyDescriptionActivity caseStudyDescriptionActivity;

	/**
	 * same async task can be used for fragments
	 */
	private NodesListFragment nodeListFragment;

	/**
	 * exception to be thrown
	 */
	private Exception exceptionThrown;

	/**
	 * constructor for description activity
	 * 
	 * @param mainActivity
	 */
	public LoadNodeDescriptionAsync(CaseStudyDescriptionActivity mainActivity) {
		caseStudyDescriptionActivity = mainActivity;
		progressBar = new ProgressDialog(mainActivity);
		progressBar.setMessage("Loading Description Details .... Please Wait");
		progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	}

	/**
	 * constructor for fragment
	 * 
	 * @param fragment
	 */
	public LoadNodeDescriptionAsync(NodesListFragment fragment) {
		this.nodeListFragment = fragment;
		progressBar = new ProgressDialog(fragment.getActivity());
		progressBar.setMessage("Loading Description Details .... Please Wait");
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
	protected SalesManagementQuestion doInBackground(String... params) {
		// get the description using webservice
		SalesManagementQuestion salesManagementQuestion = new SalesManagementQuestion();

		try {

			salesManagementQuestion = SalesManagementReadData
					.getSalesManagementQuestionDetailsForCustomer(params[0]);

		} catch (Exception e) {
			exceptionThrown = e;
		}
		return salesManagementQuestion;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(SalesManagementQuestion salesManagementQuestion) {

		if (exceptionThrown != null) {
			Toast.makeText(
					nodeListFragment.getActivity(),
					"Some Error Occured : Plese check your Internet connection or Refresh from options menu.",
					Toast.LENGTH_LONG).show();
		}

		// check if it is the activity
		if (caseStudyDescriptionActivity != null) {
			caseStudyDescriptionActivity
					.populateViewDetails(salesManagementQuestion);
		}

		// check if it is the fragment
		if (nodeListFragment != null) {
			nodeListFragment
					.communicateNodeDescriptionFragment(salesManagementQuestion);
		}

		progressBar.dismiss();
		super.onPostExecute(salesManagementQuestion);
	}
}
