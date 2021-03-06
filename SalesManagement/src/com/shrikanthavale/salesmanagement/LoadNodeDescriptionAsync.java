/**
 * 
 */
package com.shrikanthavale.salesmanagement;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.shrikanthavale.salesmanagement.administration.NodesListFragment;
import com.shrikanthavale.salesmanagement.entities.SalesManagementQuestion;
import com.shrikanthavale.salesmanagement.webserviceutility.SalesManagementReadData;

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
			Toast.makeText(
					nodeListFragment.getActivity(),
					"Some Error Occured : " + e.getMessage()
							+ ". Plese check your Internet connection.",
					Toast.LENGTH_LONG).show();
		}
		return salesManagementQuestion;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(SalesManagementQuestion salesManagementQuestion) {
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
