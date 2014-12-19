package com.shrikanthavale.salesmanagement;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

/**
 * @author Shrikant Havale
 * 
 *         This class represent the UI for displaying the optimal solution
 *         activity. Node path is highlighted using traversal nodes
 */
public class OptimalSolutionActivity extends Activity {

	/**
	 * variable containing node holder
	 */
	private NodeHolder nodeHolder;

	/**
	 * node label and max amount map
	 */
	private Map<String, Integer> nodeMaxAmountMap;

	/**
	 * ASYNC Task for finding the optimal path
	 */
	private OptiomalSolutionAsync optiomalSolutionAsync;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_optimal_solution);

		// create the node holder
		nodeHolder = new NodeHolder(this);
		nodeHolder.createCustomerNodes();
		nodeHolder.createEmptyNodes();
		nodeHolder.createStartButton();
		nodeHolder.createRestaurantButton();

		// add all the nodes to the grid
		addNodesToGridDynamically(
				(GridLayout) findViewById(R.id.optimalSolutionGrid),
				nodeHolder.getRestoredListUsingShuffledOrder());

		// add the listener to button
		findViewById(R.id.optimalSolutionBackButton).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {

						finish();
						
						// start the activity play area again
						Intent intent = new Intent(getApplicationContext(),
								PlayGridActivity.class);

						// start the activity
						startActivity(intent);
					}
				});

		// Async data to find travel path
		optiomalSolutionAsync = new OptiomalSolutionAsync(this);
		optiomalSolutionAsync.execute();

		// request the focus to play area button
		findViewById(17).requestFocus();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.optimal_solution, menu);
		return true;
	}

	@Override
	public void onBackPressed() {

		finish();
		
		// on back pressed start new playing activity
		Intent intent = new Intent(getApplicationContext(),
				PlayGridActivity.class);

		startActivity(intent);
	}

	/**
	 * This methods add the button to grid dynamically in particular order
	 * 
	 * @param gridLayout
	 *            layout in which button to be added
	 * @param combinedShuffledNodes
	 */
	private void addNodesToGridDynamically(GridLayout gridLayout,
			List<Button> combinedShuffledNodes) {

		int dimensionWidth = getWidthBasedOnScreenSize();
		int dimensionHeight = getHeightBasedOnScreenSize();

		for (int nodeNumber = 0; nodeNumber < combinedShuffledNodes.size(); nodeNumber++) {
			// add the nodes
			gridLayout.addView(combinedShuffledNodes.get(nodeNumber),
					new GridLayout.LayoutParams(new ViewGroup.LayoutParams(
							convertDIPTOPixelUtility(dimensionWidth),
							convertDIPTOPixelUtility(dimensionHeight))));
		}

	}

	private int getWidthBasedOnScreenSize() {

		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		if (width >= 100 && width < 200) {
			return 39;
		} else if (width >= 200 && width < 400) {
			return 45;
		} else if (width >= 400 && width < 500) {
			return 47;
		} else if (width >= 500 && width < 750) {
			return 51;
		} else if (width > 750 && width < 900) {
			return 56;
		} else if (width >= 900 && width < 1050) {
			return 60;
		} else if (width >= 1050 && width < 1200) {
			return 64;
		} else if (width >= 1200 && width < 1400) {
			return 68;
		} else
			return 74;

	}

	private int getHeightBasedOnScreenSize() {

		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int height = size.y;
		if (height >= 100 && height < 250) {
			return 20;
		} else if (height >= 250 && height < 450) {
			return 23;
		} else if (height >= 450 && height < 550) {
			return 25;
		} else if (height >= 550 && height < 650) {
			return 30;
		} else if (height >= 650 && height < 750) {
			return 35;
		} else if (height >= 750 && height < 850) {
			return 40;
		} else if (height >= 850 && height < 1000) {
			return 45;
		} else if (height >= 1000 && height < 1400) {
			return 50;
		} else
			return 60;

	}

	/**
	 * This method texts of all the buttons wit the maximum amount that could be
	 * earned and node of the text
	 */
	public void initializeCustomerNodesText() {

		// setting nodes with the maximum amount
		for (String node : nodeMaxAmountMap.keySet()) {

			char nodeCharacter = node.charAt(0);

			switch (nodeCharacter) {
			case 'A':
				((Button) findViewById(1)).setText(node
						+ getResources().getString(R.string.slashnstring)
						+ nodeMaxAmountMap.get(node));
				break;
			case 'B':
				((Button) findViewById(2)).setText(node
						+ getResources().getString(R.string.slashnstring)
						+ nodeMaxAmountMap.get(node));
				break;
			case 'C':
				((Button) findViewById(3)).setText(node
						+ getResources().getString(R.string.slashnstring)
						+ nodeMaxAmountMap.get(node));
				break;
			case 'D':
				((Button) findViewById(4)).setText(node
						+ getResources().getString(R.string.slashnstring)
						+ nodeMaxAmountMap.get(node));
				break;
			case 'E':
				((Button) findViewById(5)).setText(node
						+ getResources().getString(R.string.slashnstring)
						+ nodeMaxAmountMap.get(node));
				break;
			case 'F':
				((Button) findViewById(6)).setText(node
						+ getResources().getString(R.string.slashnstring)
						+ nodeMaxAmountMap.get(node));
				break;
			case 'G':
				((Button) findViewById(7)).setText(node
						+ getResources().getString(R.string.slashnstring)
						+ nodeMaxAmountMap.get(node));
				break;
			case 'H':
				((Button) findViewById(8)).setText(node
						+ getResources().getString(R.string.slashnstring)
						+ nodeMaxAmountMap.get(node));
				break;
			case 'I':
				((Button) findViewById(9)).setText(node
						+ getResources().getString(R.string.slashnstring)
						+ nodeMaxAmountMap.get(node));
				break;
			case 'J':
				((Button) findViewById(10)).setText(node
						+ getResources().getString(R.string.slashnstring)
						+ nodeMaxAmountMap.get(node));
				break;
			case 'K':
				((Button) findViewById(11)).setText(node
						+ getResources().getString(R.string.slashnstring)
						+ nodeMaxAmountMap.get(node));
				break;
			case 'L':
				((Button) findViewById(12)).setText(node
						+ getResources().getString(R.string.slashnstring)
						+ nodeMaxAmountMap.get(node));
				break;
			case 'M':
				((Button) findViewById(13)).setText(node
						+ getResources().getString(R.string.slashnstring)
						+ nodeMaxAmountMap.get(node));
				break;
			case 'N':
				((Button) findViewById(14)).setText(node
						+ getResources().getString(R.string.slashnstring)
						+ nodeMaxAmountMap.get(node));
				break;
			case 'O':
				((Button) findViewById(15)).setText(node
						+ getResources().getString(R.string.slashnstring)
						+ nodeMaxAmountMap.get(node));
				break;
			case 'P':
				((Button) findViewById(16)).setText(node
						+ getResources().getString(R.string.slashnstring)
						+ nodeMaxAmountMap.get(node));
				break;
			default:
				break;
			}

		}

	}

	/**
	 * Highlight the background of all the nodes in travel path
	 * 
	 * @param travelNodePath
	 *            nodes in the travel path
	 */
	public void showTravelPath(List<Integer> travelNodePath) {

		for (Integer customerNode : travelNodePath) {
			// set the background
			Button button = (Button) findViewById(customerNode);
			button.setBackgroundResource(R.drawable.customer_visited_button_background);
		}
	}

	/**
	 * Set the total amount and time required to travel the optimal solution
	 * path
	 * 
	 * @param totalAmount
	 *            amount earned.
	 */
	public void setTotalAmount(int totalAmount) {

		((TextView) findViewById(R.id.optimalMoneyEarned)).setText("$ : "
				+ totalAmount);
		((TextView) findViewById(R.id.optimalHoursMinutes)).setText("04 : 00");
		((TextView) findViewById(R.id.optimalAMPM)).setText("P.M.");

	}

	/**
	 * @return the nodeMaxAmountMap
	 */
	public Map<String, Integer> getNodeMaxAmountMap() {
		return nodeMaxAmountMap;
	}

	/**
	 * @param nodeMaxAmountMap
	 *            the nodeMaxAmountMap to set
	 */
	public void setNodeMaxAmountMap(Map<String, Integer> nodeMaxAmountMap) {
		this.nodeMaxAmountMap = nodeMaxAmountMap;
	}

	/**
	 * This method converts the density in pixel to normal pixel value
	 * 
	 * @param dip
	 *            DIP
	 * @return pixel value on monitor
	 */
	private int convertDIPTOPixelUtility(int dip) {
		Resources resources = this.getResources();
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip,
				resources.getDisplayMetrics());
		return (int) px;
	}

}
