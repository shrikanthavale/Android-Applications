/**
 * 
 */
package com.shrikanthavale.salesmanagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Button;

/**
 * @author Shrikant Havale
 * 
 *         This class represents holder containing the list of customer nodes,
 *         list of empty nodes and their combined list.
 */
public class NodeHolder {

	/**
	 * Activity in which nodes are to be placed
	 */
	private Activity playAreaActivity;

	/**
	 * list of customer nodes
	 */
	private List<Button> customerNodes = new ArrayList<Button>();

	/**
	 * list of empty nodes to fill the grid
	 */
	private List<Button> emptyNodes = new ArrayList<Button>();

	/**
	 * create start node
	 */
	private Button startNode;

	/**
	 * create restaurant node
	 */
	private Button restaurantNode;

	/**
	 * combined list of empty nodes and customer nodes
	 */
	private List<Button> combinedList = new ArrayList<Button>();

	/**
	 * shuffled list
	 */
	private List<Button> shuffledList = new ArrayList<Button>();

	private static List<Integer> shuffledNodeIDs = new ArrayList<Integer>();

	/**
	 * @return the shuffledNodeIDs
	 */
	public static List<Integer> getShuffledNodeIDs() {
		return shuffledNodeIDs;
	}

	/**
	 * @param shuffledNodeIDs
	 *            the shuffledNodeIDs to set
	 */
	public static void setShuffledNodeIDs(List<Integer> shuffledNodeIDs) {
		NodeHolder.shuffledNodeIDs = shuffledNodeIDs;
	}

	/**
	 * Initializing the holder with the activity
	 * 
	 * @param activity
	 */
	public NodeHolder(Activity activity) {
		playAreaActivity = activity;
	}

	/**
	 * This method specifically creates the customer nodes
	 */
	public void createCustomerNodes() {

		// clear the list before adding
		combinedList.clear();

		for (int nodeNumber = 1; nodeNumber <= 16; nodeNumber++) {
			Button customerNode = new Button(playAreaActivity);
			customerNode.setId(nodeNumber);
			customerNode.setFocusable(true);
			customerNode.setFocusableInTouchMode(true);
			customerNode.setGravity(Gravity.CENTER);
			customerNode
					.setBackgroundResource(R.drawable.customer_button_background);
			customerNode.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
			customerNode.setText(R.string.slashnstring);
			customerNodes.add(customerNode);

		}

		// add the node
		combinedList.addAll(customerNodes);
	}

	/**
	 * create the start button
	 */
	public void createStartButton() {

		startNode = new Button(playAreaActivity);
		startNode.setId(17);
		startNode.setFocusableInTouchMode(true);
		startNode.setGravity(Gravity.CENTER);
		startNode.setBackgroundResource(R.drawable.start_button_background);
		startNode.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
		startNode.setText(R.string.startNodeString);

	}

	/**
	 * create the restaurant button
	 */
	public void createRestaurantButton() {

		restaurantNode = new Button(playAreaActivity);
		restaurantNode.setId(18);
		restaurantNode.setFocusableInTouchMode(true);
		restaurantNode.setGravity(Gravity.CENTER);
		restaurantNode
				.setBackgroundResource(R.drawable.restaurant_button_background);
		restaurantNode.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
		restaurantNode.setText(R.string.restaurant_R);

	}

	/**
	 * create empty nodes
	 */
	public void createEmptyNodes() {

		for (int nodeNumber = 19; nodeNumber <= 36; nodeNumber++) {
			Button emptyNode = new Button(playAreaActivity);
			emptyNode.setId(nodeNumber);
			emptyNode.setFocusable(true);
			emptyNode.setFocusableInTouchMode(true);
			emptyNode.setGravity(Gravity.CENTER);
			emptyNode
					.setBackgroundResource(R.drawable.normal_button_background);
			emptyNode.setText(R.string.slashnstring);
			emptyNode.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
			emptyNodes.add(emptyNode);

		}

		combinedList.addAll(emptyNodes);

	}

	/**
	 * generate the random list using shuffle method. This method helps to
	 * refresh the grid and gives new structure if requested.
	 * 
	 * @return {@link List} buttons in specific order
	 */
	public List<Button> generateRandomList() {

		// generate the random sequence
		Collections.shuffle(combinedList, new Random());

		// add the nodes at this sepcific position
		combinedList.add(17, startNode);
		combinedList.add(20, restaurantNode);

		// clear the list
		shuffledNodeIDs.clear();

		// capture the shuffled node IDS
		for (Button button : combinedList) {
			shuffledNodeIDs.add(button.getId());
		}

		return combinedList;

	}

	/**
	 * Returns the shuffled node list, once the grid is created this method is
	 * used to keep its consistency unless they are explicitly refreshed using
	 * generateRandomList method.
	 * 
	 * @return
	 */
	public List<Button> getRestoredListUsingShuffledOrder() {

		// clear the shuffled list
		shuffledList.clear();

		// add it to combined list at specific position
		combinedList.add(17, startNode);
		combinedList.add(20, restaurantNode);

		for (Integer temp : shuffledNodeIDs) {
			for (Button button : combinedList) {
				if (button.getId() == temp) {
					shuffledList.add(button);
					break;
				}
			}
		}

		// return the list
		return shuffledList;
	}

	/**
	 * @return the playAreaActivity
	 */
	public Activity getPlayAreaActivity() {
		return playAreaActivity;
	}

	/**
	 * @param playAreaActivity
	 *            the playAreaActivity to set
	 */
	public void setPlayAreaActivity(Activity playAreaActivity) {
		this.playAreaActivity = playAreaActivity;
	}

	/**
	 * @return the customerNodes
	 */
	public List<Button> getCustomerNodes() {
		return customerNodes;
	}

	/**
	 * @param customerNodes
	 *            the customerNodes to set
	 */
	public void setCustomerNodes(List<Button> customerNodes) {
		this.customerNodes = customerNodes;
	}

	/**
	 * @return the emptyNodes
	 */
	public List<Button> getEmptyNodes() {
		return emptyNodes;
	}

	/**
	 * @param emptyNodes
	 *            the emptyNodes to set
	 */
	public void setEmptyNodes(List<Button> emptyNodes) {
		this.emptyNodes = emptyNodes;
	}

	/**
	 * @return the combinedList
	 */
	public List<Button> getCombinedList() {
		return combinedList;
	}

	/**
	 * @param combinedList
	 *            the combinedList to set
	 */
	public void setCombinedList(List<Button> combinedList) {
		this.combinedList = combinedList;
	}

	/**
	 * @return the startNode
	 */
	public Button getStartNode() {
		return startNode;
	}

	/**
	 * @param startNode
	 *            the startNode to set
	 */
	public void setStartNode(Button startNode) {
		this.startNode = startNode;
	}

	/**
	 * @return the restaurantNode
	 */
	public Button getRestaurantNode() {
		return restaurantNode;
	}

	/**
	 * @param restaurantNode
	 *            the restaurantNode to set
	 */
	public void setRestaurantNode(Button restaurantNode) {
		this.restaurantNode = restaurantNode;
	}

	/**
	 * @return the shuffledList
	 */
	public List<Button> getShuffledList() {
		return shuffledList;
	}

	/**
	 * @param shuffledList
	 *            the shuffledList to set
	 */
	public void setShuffledList(List<Button> shuffledList) {
		this.shuffledList = shuffledList;
	}

	/**
	 * This method converts the density in pixel to normal pixel value
	 * 
	 * @param dip
	 *            DIP
	 * @return pixel value on monitor
	 */
	public int convertDIPTOPixelUtility(int dip) {
		Resources resources = playAreaActivity.getResources();
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip,
				resources.getDisplayMetrics());
		return (int) px;
	}

}
