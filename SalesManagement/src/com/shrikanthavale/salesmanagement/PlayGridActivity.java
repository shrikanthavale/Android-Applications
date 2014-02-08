package com.shrikanthavale.salesmanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shrikanthavale.salesmanagement.webserviceutility.SalesManagementSaveActivityState;

/**
 * 
 * @author Shrikant Havale
 * 
 *         This is the main activity containing the structure of the grid, with
 *         the controls to navigate, where the time spent and amount is updated
 *         accordingly
 * 
 */
public class PlayGridActivity extends Activity implements OnClickListener {

	/**
	 * Customer which currently in focus
	 */
	private Button focusedCustomer = null;

	/**
	 * hours integer to measure the time
	 */
	private int hours = 8;

	/**
	 * minutes to measure the time
	 */
	private int minutes = 0;

	/**
	 * money earned
	 */
	private int moneyEarned = 0;

	/**
	 * Map for storing the customers (A, B, C) ... and maximum amount that can
	 * be earned from them
	 */
	private Map<String, Integer> nodeMaxAmountMap = null;

	/**
	 * List for storing the visited customers, their nodes can be changed
	 * accordingly
	 */
	private List<Integer> visitedCustomers = new ArrayList<Integer>();

	/**
	 * load data ASYNC task
	 */
	private LoadNodeDataAsync loadNodeDataAsync;

	/**
	 * Node holder containing all the nodes to be dynamically added
	 */
	private NodeHolder nodeHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// call to super
		super.onCreate(savedInstanceState);

		// set the content view
		setContentView(R.layout.activity_play_area);

		// create the node holder
		nodeHolder = new NodeHolder(this);
		nodeHolder.createCustomerNodes();
		nodeHolder.createEmptyNodes();
		nodeHolder.createStartButton();
		nodeHolder.createRestaurantButton();

		// add all the listeners
		addOnClickListenersNode();

		// add anonymous listeners for navigation keys
		addOnClickListenerNavigationKeys();

		// assign the focus button to start
		focusedCustomer = nodeHolder.getStartNode();

		// request focus
		focusedCustomer.requestFocus();

	}

	@Override
	protected void onStart() {
		super.onStart();
		if (!SalesManagementSaveActivityState.isFirstTimeLoad()) {
			// add all the nodes to the grid
			addNodesToGridDynamically(
					(GridLayout) findViewById(R.id.buttongridview),
					nodeHolder.getRestoredListUsingShuffledOrder());

			updateFromSavedState();
		} else {

			// disable the flag
			SalesManagementSaveActivityState.setAboutToEndGame(false);

			// add all the nodes to the grid
			addNodesToGridDynamically(
					(GridLayout) findViewById(R.id.buttongridview),
					nodeHolder.generateRandomList());

		}

		// ASYNC loading of data
		loadNodeDataAsync = new LoadNodeDataAsync(this);
		loadNodeDataAsync = (LoadNodeDataAsync) loadNodeDataAsync.execute();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (!SalesManagementSaveActivityState.isAboutToEndGame())
			saveDataFromCurrentState();
	}

	@Override
	public void onBackPressed() {
		createAlertDialogExitGame();
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
		Log.e("Shrikant", "value is width " + width);
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
		Log.e("Shrikant", "value is height " + height);
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
	 * creates the alert dialog asking for the users confirmation for ending
	 * game and back navigation
	 */
	private void createAlertDialog() {

		// create the alert dialog asking user to go back
		AlertDialog.Builder builder = new AlertDialog.Builder(
				PlayGridActivity.this);

		// set message and the title
		builder.setMessage(R.string.backfromgamescreenerror);
		builder.setTitle(R.string.popupdialogTitle);

		// set the button handlers
		builder.setNegativeButton(R.string.popupdialogCancelButton,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						dialog.dismiss();
					}
				});

		// set the button handlers
		builder.setPositiveButton(R.string.popupdialogConfirmButton,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {

						// reset the current game progress
						resetAllCurrentGameProgress();

						Intent intent = new Intent(getApplicationContext(),
								OptimalSolutionActivity.class);
						startActivity(intent);

					}
				});

		// create the dialog
		AlertDialog alertDialog = builder.create();

		// show the dialog
		alertDialog.show();

	}

	/**
	 * creates the alert dialog asking for the users confirmation for ending
	 * game and back navigation
	 */
	private void createAlertDialogRefreshingGame() {

		// create the alert dialog asking user to go back
		AlertDialog.Builder builder = new AlertDialog.Builder(
				PlayGridActivity.this);

		// set message and the title
		builder.setMessage(R.string.refreshgamewarningmessage);
		builder.setTitle(R.string.popupdialogTitle);

		// set the button handlers
		builder.setNegativeButton(R.string.popupdialogCancelButton,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {

					}
				});

		// set the button handlers
		builder.setPositiveButton(R.string.popupdialogConfirmButton,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						resetAllCurrentGameProgress();
						Intent intent = new Intent(getApplicationContext(),
								PlayGridActivity.class);
						startActivity(intent);
					}
				});

		// create the dialog
		AlertDialog alertDialog = builder.create();

		// show the dialog
		alertDialog.show();
	}

	/**
	 * creates the alert dialog asking for the users confirmation for ending
	 * game and back navigation
	 */
	private void createAlertDialogExitGame() {

		// create the alert dialog asking user to go back
		AlertDialog.Builder builder = new AlertDialog.Builder(
				PlayGridActivity.this);

		// set message and the title
		builder.setMessage(R.string.backfromgamescreenerror);
		builder.setTitle(R.string.popupdialogTitle);

		// set the button handlers
		builder.setNegativeButton(R.string.popupdialogCancelButton,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						resetAllCurrentGameProgress();
						dialog.dismiss();
					}
				});

		// set the button handlers
		builder.setPositiveButton(R.string.popupdialogConfirmButton,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						resetAllCurrentGameProgress();
						Intent intent = new Intent(getApplicationContext(),
								StartPageActivity.class);
						startActivity(intent);
					}
				});

		// create the dialog
		AlertDialog alertDialog = builder.create();

		// show the dialog
		alertDialog.show();
	}

	/**
	 * resets all the current game progress. All the state of activity saved in
	 * static variables is reseted to default
	 */
	private void resetAllCurrentGameProgress() {

		SalesManagementSaveActivityState.setAboutToEndGame(true);
		SalesManagementSaveActivityState.setCustomersVisited(null);
		SalesManagementSaveActivityState.setAmpmTimeValue(null);
		SalesManagementSaveActivityState.setAmountEarned(0);
		SalesManagementSaveActivityState.setCurrentCustomer(null);
		SalesManagementSaveActivityState.setFirstTimeLoad(true);
		SalesManagementSaveActivityState.setFocusedButtonID(0);
		SalesManagementSaveActivityState.setHoursSpent(0);
		SalesManagementSaveActivityState.setMinutesSpent(0);

	}

	/**
	 * save the current state during change of the activity
	 */
	protected void saveDataFromCurrentState() {

		// save the UI state
		SalesManagementSaveActivityState
				.setAmpmTimeValue(((TextView) findViewById(R.id.AMPM))
						.getText().toString());
		SalesManagementSaveActivityState.setHoursSpent(hours);
		SalesManagementSaveActivityState.setMinutesSpent(minutes);
		SalesManagementSaveActivityState.setAmountEarned(moneyEarned);
		SalesManagementSaveActivityState.setFocusedButtonID(focusedCustomer
				.getId());
		SalesManagementSaveActivityState.setCustomersVisited(visitedCustomers);
		SalesManagementSaveActivityState.setFirstTimeLoad(false);

	}

	/**
	 * update the data during reloading of state
	 */
	@SuppressWarnings("deprecation")
	private void updateFromSavedState() {

		hours = SalesManagementSaveActivityState.getHoursSpent();
		minutes = SalesManagementSaveActivityState.getMinutesSpent();
		moneyEarned = SalesManagementSaveActivityState.getAmountEarned();
		setMoneyEarnedView(moneyEarned);
		((TextView) findViewById(R.id.AMPM))
				.setText(SalesManagementSaveActivityState.getAmpmTimeValue());
		updateTimeForCustomerVisit();
		focusedCustomer = (Button) findViewById(SalesManagementSaveActivityState
				.getFocusedButtonID());
		focusedCustomer.requestFocus();
		visitedCustomers = SalesManagementSaveActivityState
				.getCustomersVisited();

		// disable the visited customers
		for (int viewID : visitedCustomers) {
			findViewById(viewID).setEnabled(false);
			findViewById(viewID).setBackgroundDrawable(
					getResources().getDrawable(
							R.drawable.customer_visited_button_background));
		}

	}

	/**
	 * This method texts of all the buttons wit the maximum amount that could be
	 * earned and node of the text
	 */
	public void initializeCustomerNodesText() {

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
	 * This method adds the anonymous listener to navigation keys and two
	 * special buttons Start and Restaurant button
	 */
	private void addOnClickListenerNavigationKeys() {

		// add on click listener for up navigation
		((Button) findViewById(R.id.upNavigation))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {

						if (!checkSufficientTimeCustomerTravel()) {

							// get the index of button clicked
							int positionFocusedButton = getIndexButtonClicked(getFocusedButton());

							if (positionFocusedButton % getRowCount() == 0) {
								setMessage(R.string.invalidmove);
							} else {
								positionFocusedButton = positionFocusedButton - 1;
								setFocusedButton((Button) getGridLayoutNodes()
										.getChildAt(positionFocusedButton));
								updateTime();
							}
						} else {
							setMessage(R.string.insufficienttimetravel);
						}

					}
				});

		// add on click listener for up navigation
		findViewById(R.id.leftNavigation).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {

						if (!checkSufficientTimeCustomerTravel()) {
							// current focused button
							int positionFocusedButton = getIndexButtonClicked(getFocusedButton());

							if (positionFocusedButton < getColumnCount()) {
								setMessage(R.string.invalidmove);
							} else {
								positionFocusedButton = positionFocusedButton
										- getColumnCount();
								setFocusedButton((Button) getGridLayoutNodes()
										.getChildAt(positionFocusedButton));
								updateTime();
							}
						} else {
							setMessage(R.string.insufficienttimetravel);
						}
					}
				});

		// add on click listener for up navigation
		findViewById(R.id.rightNavigation).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {

						if (!checkSufficientTimeCustomerTravel()) {
							// current focused button
							int positionFocusedButton = getIndexButtonClicked(getFocusedButton());

							if (positionFocusedButton >= (getColumnCount()
									* getRowCount() - 6)) {
								setMessage(R.string.invalidmove);
							} else {
								positionFocusedButton = positionFocusedButton
										+ getColumnCount();
								setFocusedButton((Button) getGridLayoutNodes()
										.getChildAt(positionFocusedButton));
								updateTime();
							}
						} else {
							setMessage(R.string.insufficienttimetravel);
						}

					}
				});

		// add on click listener for up navigation
		findViewById(R.id.downNavigation).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {

						if (!checkSufficientTimeCustomerTravel()) {
							// current focused button
							int positionFocusedButton = getIndexButtonClicked(getFocusedButton());

							if (positionFocusedButton % getRowCount() == getRowCount() - 1) {
								setMessage(R.string.invalidmove);
							} else {
								positionFocusedButton = positionFocusedButton + 1;
								setFocusedButton((Button) getGridLayoutNodes()
										.getChildAt(positionFocusedButton));
								updateTime();
							}
						} else {
							setMessage(R.string.insufficienttimetravel);
						}

					}
				});

		// add on click listener for up navigation
		findViewById(R.id.confirmSelection).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// process the customer click node
						performCustomerVisit();
					}

				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.refresh_game:
			createAlertDialogRefreshingGame();
			return true;
		case R.id.stop_game:
			createAlertDialog();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * This method is responsible for accepting the customer node and then
	 * passing the customer , searching the database and navigate to the next
	 * activity showing the description
	 */
	private void performCustomerVisit() {

		// get the focused button
		Button focusedButton = getFocusedButton();

		if (focusedButton.getText().toString().trim()
				.equals(getResources().getString(R.string.emptystring))
				|| focusedButton.getText().toString().trim().contains("Start")
				|| focusedButton.getText().toString().trim().contains("R")) {

			setMessage(R.string.nonnodeclickederrormessage);

		} else {

			if (!visitedCustomers.contains(focusedButton.getId())) {

				// update the time that will be required for customer visit,
				// also check if has sufficient time for doing customer visit
				if (!checkSufficientTimeCustomerVisit()) {

					// add the button to visited list
					visitedCustomers.add(focusedButton.getId());

					// change the style of visited customer
					focusedButton.setEnabled(false);

					Intent intent = new Intent(getApplicationContext(),
							CaseStudyDescriptionActivity.class);

					// store the current customer in static variable
					String currentCustomer = focusedButton.getText().toString()
							.split("\n")[0];

					// set the current customer
					SalesManagementSaveActivityState
							.setCurrentCustomer(currentCustomer);

					// call the activity
					startActivity(intent);

				} else {
					setMessage(R.string.insufficienttimevisit);
				}

			} else {
				setMessage(R.string.alreadyvisitednode);
			}

		}

	}

	/**
	 * This method will add the on click listeners to buttons in the grid. This
	 * method specifically adds to the listener in Grid and not on the direction
	 * buttons or any other components.
	 */
	private void addOnClickListenersNode() {

		// get all nodes
		List<Button> combinedNodes = nodeHolder.getCombinedList();

		// Listener for all node
		for (Button button : combinedNodes) {
			button.setOnClickListener(this);
		}

		// listener for start and restaurant node
		nodeHolder.getStartNode().setOnClickListener(this);
		nodeHolder.getRestaurantNode().setOnClickListener(this);

	}

	@Override
	public void onClick(View _viewClicked) {
		setMessage(R.string.touchnodeerrormessage);
		getFocusedButton().requestFocus();
	}

	/**
	 * Logic for getting the index number in the entire grid for the button that
	 * was clicked
	 * 
	 * @param _viewClicked
	 *            accepts the button or view for getting the index
	 * 
	 * @return
	 */
	private int getIndexButtonClicked(View _viewClicked) {

		GridLayout gridLayout = getGridLayoutNodes();
		int clickedViewID = _viewClicked.getId();

		int buttonPosition = -1;

		for (int index = 0; index < gridLayout.getChildCount(); index++) {
			int tempID = gridLayout.getChildAt(index).getId();
			if (tempID == clickedViewID) {
				buttonPosition = index;
				break;
			}
		}
		return buttonPosition;
	}

	/**
	 * Update the Time method This will update the time accordingly after each
	 * and every move
	 */

	private void updateTime() {

		String amPMText = ((TextView) findViewById(R.id.AMPM)).getText()
				.toString();
		String timeText = getResources().getString(R.string.emptystring);

		// logic to increase minutes
		if (minutes < 45) {
			minutes = minutes + 15;
		} else {
			hours = hours + 1;
			minutes = 0;
		}

		// logic for counting from 1 after reach of 12
		if (hours > 12) {
			hours = hours - 12;
		}

		// logic for changing AM to PM
		if (hours == 12) {
			amPMText = getResources().getString(R.string.pmstring);
		}

		if (minutes == 0) {
			if (hours > 0 && hours < 10) {
				timeText = getResources().getString(R.string.zerostring)
						+ hours
						+ getResources().getString(R.string.colonstring)
						+ getResources().getString(R.string.zerozerostring); //$NON-NLS-2$
			} else {
				timeText = hours
						+ getResources().getString(R.string.colonstring)
						+ getResources().getString(R.string.zerozerostring); //$NON-NLS-2$
			}

		} else {
			if (hours > 0 && hours < 10) {
				timeText = getResources().getString(R.string.zerostring)
						+ hours
						+ getResources().getString(R.string.colonstring)
						+ minutes;
			} else {
				timeText = hours
						+ getResources().getString(R.string.colonstring)
						+ minutes;
			}

		}

		setTimeTextView(amPMText, timeText);

	}

	/**
	 * Update time after the customer visit
	 */
	private void updateTimeForCustomerVisit() {

		String ampmText = ((TextView) findViewById(R.id.AMPM)).getText()
				.toString();
		String timeText = getResources().getString(R.string.emptystring);

		if (minutes == 15 || minutes == 0) {
			minutes = minutes + 30;
		} else if (minutes == 30) {
			minutes = 0;
			hours = hours + 1;
		} else if (minutes == 45) {
			minutes = 15;
			hours = hours + 1;
		}

		// logic for counting from 1 after reach of 12
		if (hours > 12) {
			hours = hours - 12;
		}

		// logic for changing AM to PM
		if (hours == 12) {
			ampmText = (getResources().getString(R.string.pmstring));
		}

		if (minutes == 0) {

			if (hours > 0 && hours < 10) {
				timeText = getResources().getString(R.string.zerostring)
						+ hours
						+ getResources().getString(R.string.colonstring)
						+ getResources().getString(R.string.zerozerostring); //$NON-NLS-2$
			} else {
				timeText = hours
						+ getResources().getString(R.string.colonstring)
						+ getResources().getString(R.string.zerozerostring); //$NON-NLS-2$
			}

		} else {
			if (hours > 0 && hours < 10) {
				timeText = getResources().getString(R.string.zerostring)
						+ hours
						+ getResources().getString(R.string.colonstring)
						+ minutes;
			} else {
				timeText = hours
						+ getResources().getString(R.string.colonstring)
						+ minutes;
			}
		}

		setTimeTextView(ampmText, timeText);

	}

	/**
	 * Set the AM/PM String and time accordingly to text view
	 * 
	 * @param amPMString
	 *            AM/PM string
	 * @param time
	 *            current time
	 */
	private void setTimeTextView(String amPMString, String time) {

		TextView ampmLabel = (TextView) findViewById(R.id.AMPM);
		TextView timeSpent = (TextView) findViewById(R.id.hoursMinutes);

		ampmLabel.setText(amPMString);
		timeSpent.setText(time);

	}

	/**
	 * Set the amount earned to the view
	 * 
	 * @param moneyEarned
	 *            accepts the amount
	 */
	private void setMoneyEarnedView(int moneyEarned) {

		String text = getResources().getString(R.string.dollarstring)
				+ getResources().getString(R.string.spacestring)
				+ getResources().getString(R.string.colonstring)
				+ getResources().getString(R.string.spacestring);

		if (moneyEarned == 0) {
			text = text + getResources().getString(R.string.fivezerostring);
		} else {
			text = text + moneyEarned;
		}

		TextView textView = (TextView) findViewById(R.id.moneyEarned);
		textView.setText(text);

	}

	/**
	 * check if sufficient time is there for travel
	 * 
	 * @return true/false
	 */
	private boolean checkSufficientTimeCustomerTravel() {
		return hours == 4;
	}

	/**
	 * check if sufficient time is there for visit
	 * 
	 * @return true/false
	 */
	private boolean checkSufficientTimeCustomerVisit() {
		return ((hours == 3 && minutes == 45) || hours == 4);
	}

	private GridLayout getGridLayoutNodes() {
		return (GridLayout) findViewById(R.id.buttongridview);
	}

	/**
	 * Set toast message about the activities happening during play
	 * 
	 * @param messageID
	 *            message id from string xml file
	 */
	private void setMessage(int messageID) {
		String _message = getResources().getString(messageID);
		Toast toast = Toast.makeText(this, _message, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM, 0, 0);
		toast.show();
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

	private int getColumnCount() {
		return (getGridLayoutNodes()).getColumnCount();
	}

	private int getRowCount() {
		return (getGridLayoutNodes()).getRowCount();
	}

	public Button getFocusedButton() {
		return focusedCustomer;
	}

	public void setFocusedButton(Button focusedButton) {
		focusedButton.requestFocus();
		this.focusedCustomer = focusedButton;
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

}
