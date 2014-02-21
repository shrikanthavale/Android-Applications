/**
 * 
 */
package com.shrikanthavale.cricketquiz;

import java.util.List;
import java.util.Map;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.shrikanthavale.cricketquiz.bestsolutionalgorithm.WeightedCustomerGraph;
import com.shrikanthavale.cricketquiz.webserviceutility.SalesManagementReadData;

/**
 * @author Shrikant Havale
 * 
 *         This class performs all the activities in the background for getting
 *         the optimal path. With the use of {@link WeightedCustomerGraph} it
 *         calculates matrix positions, node max amount , travel path
 */
public class OptiomalSolutionAsync extends
		AsyncTask<Void, Integer, Map<String, Integer>> {

	/**
	 * create a progress bar
	 */
	private ProgressDialog progressBar;

	/**
	 * activity for ASYNC task
	 */
	private OptimalSolutionActivity optimalSolutionActivity;

	/**
	 * weighted customer graph
	 */
	private WeightedCustomerGraph weightedCustomerGraph;

	/**
	 * node containing the path to be traversed
	 */
	private List<Integer> travelPathNodes;

	/**
	 * node label and max amount map
	 */
	private Map<String, Integer> nodeMaxAmountMap;

	/**
	 * exception thrown
	 */
	private Exception exceptionThrown;

	/**
	 * total amount earned
	 */
	private int totalAmount;

	/**
	 * Constructor to initialize the ASYNC details
	 * 
	 * @param mainActivity
	 */
	public OptiomalSolutionAsync(OptimalSolutionActivity mainActivity) {
		this.optimalSolutionActivity = mainActivity;
		weightedCustomerGraph = new WeightedCustomerGraph();
		progressBar = new ProgressDialog(mainActivity);
		progressBar.setMessage("Generating Travel Path .... Please Wait");
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

		// get the max node amount map
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
					optimalSolutionActivity,
					"Some Error Occured : Plese check your Internet connection.",
					Toast.LENGTH_LONG).show();
		} else {

			// calculate positions
			weightedCustomerGraph.calculatePositionCustomerNodes(NodeHolder
					.getShuffledNodeIDs());

			// create weight matrix
			weightedCustomerGraph.createNodeWeightMatrix(NodeHolder
					.getShuffledNodeIDs());

			// set the node amount map
			weightedCustomerGraph.setNodeMaxAmountMap(nodeMaxAmountMap);

			// calculate the path of traversal
			travelPathNodes = weightedCustomerGraph.getTravelPath();

			// get the total amount earned
			totalAmount = weightedCustomerGraph.getTotalAmountEarned();

			// set the node max mount
			optimalSolutionActivity.setNodeMaxAmountMap(result);

			// Initialize the nodes with labels and amounts
			optimalSolutionActivity.initializeCustomerNodesText();

			// set the travel path
			optimalSolutionActivity.showTravelPath(travelPathNodes);

			// set the total amount
			optimalSolutionActivity.setTotalAmount(totalAmount);

		}
		// dismiss the progress bar
		progressBar.dismiss();
		super.onPostExecute(result);
	}
}
