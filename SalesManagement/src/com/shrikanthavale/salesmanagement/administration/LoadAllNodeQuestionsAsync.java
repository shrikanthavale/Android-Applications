/**
 * 
 */
package com.shrikanthavale.salesmanagement.administration;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.shrikanthavale.salesmanagement.entities.SalesManagementQuestion;
import com.shrikanthavale.salesmanagement.webserviceutility.SalesManagementReadData;

/**
 * @author Shrikant Havale
 * 
 *         This is ASYNC task for loading the all the questions list along with
 *         the title and organization in the form of list to be displayed for
 *         editing
 */
public class LoadAllNodeQuestionsAsync extends
		AsyncTask<Void, Integer, List<SalesManagementQuestion>> {

	/**
	 * create a progress bar
	 */
	private ProgressDialog progressBar;

	/**
	 * Fragment which is used for displaying this list
	 */
	private NodesListFragment allNodesList;

	public LoadAllNodeQuestionsAsync(NodesListFragment fragment) {
		allNodesList = fragment;
		progressBar = new ProgressDialog(allNodesList.getActivity());
		progressBar.setMessage("Loading List .... Please Wait");
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
	protected List<SalesManagementQuestion> doInBackground(Void... params) {
		List<SalesManagementQuestion> salesManagementQuestionOptions = new ArrayList<SalesManagementQuestion>();
		try {
			salesManagementQuestionOptions = SalesManagementReadData
					.getSalesManagementQuestionList();
		} catch (Exception e) {
			Toast.makeText(
					allNodesList.getActivity(),
					"Some Error Occured : " + e.getMessage()
							+ ". Plese check your Internet connection.",
					Toast.LENGTH_LONG).show();
		}
		return salesManagementQuestionOptions;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(
			List<SalesManagementQuestion> salesManagementQuestions) {
		allNodesList.updateAdapterListData(salesManagementQuestions);
		progressBar.dismiss();
		super.onPostExecute(salesManagementQuestions);
	}
}
