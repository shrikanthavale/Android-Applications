/**
 * 
 */
package com.shrikanthavale.salesmanagement.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.robotium.solo.Solo;
import com.shrikanthavale.salesmanagement.NodeHolder;
import com.shrikanthavale.salesmanagement.OptimalSolutionActivity;
import com.shrikanthavale.salesmanagement.PlayGridActivity;
import com.shrikanthavale.salesmanagement.R;
import com.shrikanthavale.salesmanagement.StartPageActivity;

/**
 * @author Shrikant Havale
 * 
 */
@Ignore
public class B_PlayGridActivityTest extends
		ActivityInstrumentationTestCase2<StartPageActivity> {

	/**
	 * Robotium solo object
	 */
	private Solo robotiumSolo;

	/**
	 * Map for storing the customers (A, B, C) ... and maximum amount that can
	 * be earned from them
	 */
	private Map<String, Integer> nodeMaxAmountMap = null;

	/**
	 * @param name
	 */
	public B_PlayGridActivityTest() {
		super(StartPageActivity.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		robotiumSolo = new Solo(getInstrumentation(), getActivity());
		nodeMaxAmountMap = new HashMap<String, Integer>();
		nodeMaxAmountMap.put("A", 4000);
		nodeMaxAmountMap.put("B", 4000);
		nodeMaxAmountMap.put("C", 8000);
		nodeMaxAmountMap.put("D", 2000);
		nodeMaxAmountMap.put("E", 2000);
		nodeMaxAmountMap.put("F", 8000);
		nodeMaxAmountMap.put("G", 4000);
		nodeMaxAmountMap.put("H", 6000);
		nodeMaxAmountMap.put("I", 1000);
		nodeMaxAmountMap.put("J", 1000);
		nodeMaxAmountMap.put("K", 9000);
		nodeMaxAmountMap.put("L", 9000);
		nodeMaxAmountMap.put("M", 8000);
		nodeMaxAmountMap.put("N", 9000);
		nodeMaxAmountMap.put("O", 8000);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.test.ActivityInstrumentationTestCase2#tearDown()
	 */
	protected void tearDown() throws Exception {
		robotiumSolo.finishOpenedActivities();
		super.tearDown();
	}

	/**
	 * Test method to check if the Sales Management Game is loaded with proper
	 * default initialization
	 */
	public void testPlayGridActivityLoadedWithData() {

		robotiumSolo.unlockScreen();

		// Test case 1 : Loading game first time , intially
		robotiumSolo.assertCurrentActivity("Found Wrong Activity",
				StartPageActivity.class);
		robotiumSolo.clickOnButton(0);
		robotiumSolo.waitForDialogToOpen();
		assertTrue("Loading Dialogue Missing",
				robotiumSolo
						.waitForText("Loading Node Details .... Please Wait"));
		robotiumSolo.waitForDialogToClose();

		// check game is loaded in proper initial state
		checkGameLoadedWithInitialSettings();

		// Test Case 2 : Refresh game for menu item, resetting all setting to
		// original
		robotiumSolo.sendKey(Solo.MENU);
		robotiumSolo.sleep(1000);
		robotiumSolo.waitForText(robotiumSolo.getString(R.string.refreshGrid));
		assertTrue("Refresh Menu Item not found in Action Bar",
				robotiumSolo.searchText(robotiumSolo
						.getString(R.string.refreshGrid)));
		assertTrue("End Game Menu Item not found in Action Bar",
				robotiumSolo.searchText(robotiumSolo
						.getString(R.string.stopGame)));
		robotiumSolo.clickOnActionBarItem(R.id.refresh_game);
		robotiumSolo.waitForDialogToOpen();
		assertTrue("Pop dialogue confirming to refresh game not found",
				robotiumSolo.searchText(robotiumSolo
						.getString(R.string.popupdialogTitle)));
		assertTrue("Pop dialogue confirming to refresh message not correct",
				robotiumSolo.searchText(robotiumSolo
						.getString(R.string.refreshgamewarningmessage)));
		robotiumSolo.clickOnButton(robotiumSolo
				.getString(R.string.popupdialogConfirmButton));
		robotiumSolo.waitForDialogToClose();
		robotiumSolo.waitForDialogToOpen();
		assertTrue("Loading Dialogue Missing",
				robotiumSolo
						.waitForText("Loading Node Details .... Please Wait"));
		robotiumSolo.waitForDialogToClose();
		checkGameLoadedWithInitialSettings();

		// Test case 3 : End Game scenario
		robotiumSolo.sendKey(Solo.MENU);
		robotiumSolo.sleep(1000);
		robotiumSolo.waitForText(robotiumSolo.getString(R.string.refreshGrid));
		assertTrue("Refresh Menu Item not found in Action Bar",
				robotiumSolo.searchText(robotiumSolo
						.getString(R.string.refreshGrid)));
		assertTrue("End Game Menu Item not found in Action Bar",
				robotiumSolo.searchText(robotiumSolo
						.getString(R.string.stopGame)));
		robotiumSolo.clickOnActionBarItem(R.id.stop_game);
		robotiumSolo.waitForDialogToOpen();
		assertTrue("Pop dialogue confirming to end game not found",
				robotiumSolo.searchText(robotiumSolo
						.getString(R.string.popupdialogTitle)));
		assertTrue("Pop dialogue confirming to end game message not correct",
				robotiumSolo.searchText(robotiumSolo
						.getString(R.string.backfromgamescreenerror)));
		robotiumSolo.clickOnButton(robotiumSolo
				.getString(R.string.popupdialogConfirmButton));
		robotiumSolo.waitForDialogToClose();
		robotiumSolo.waitForDialogToOpen();
		assertTrue("Loading Dialogue Missing",
				robotiumSolo.waitForText(robotiumSolo
						.getString(R.string.generatingtravelpathmessage)));
		robotiumSolo.waitForDialogToClose();
		robotiumSolo.assertCurrentActivity(
				"Current Activity is not optimal solution activity",
				OptimalSolutionActivity.class);
		assertTrue("Solution Title Not Found ",
				robotiumSolo.searchText(robotiumSolo
						.getString(R.string.optimalSolutionHeading)));
		assertNotNull("Continue to play area button not found",
				robotiumSolo.getButton(robotiumSolo
						.getString(R.string.continuePlayArea)));
		robotiumSolo.clickOnButton(robotiumSolo
						.getString(R.string.continuePlayArea));
		robotiumSolo.sleep(1000);
		robotiumSolo.waitForDialogToOpen();
		assertTrue("Loading Dialogue Missing",
				robotiumSolo
						.waitForText("Loading Node Details .... Please Wait"));
		robotiumSolo.waitForDialogToClose();
		checkGameLoadedWithInitialSettings();

		// Test Case 3: closing the game, by clicking back button on
		// PlayGridActivity.
		robotiumSolo.goBack();
		robotiumSolo.waitForDialogToOpen();
		robotiumSolo.clickOnButton(robotiumSolo
				.getString(R.string.popupdialogConfirmButton));
		robotiumSolo.waitForDialogToClose();
		robotiumSolo.waitForActivity(StartPageActivity.class);
	}

	private void checkGameLoadedWithInitialSettings() {

		// search for customer node text
		for (String node : nodeMaxAmountMap.keySet()) {
			assertTrue(
					"Customer Node " + node + " is not present",
					robotiumSolo.searchText(node
							+ robotiumSolo.getString(R.string.slashnstring)
							+ nodeMaxAmountMap.get(node)));
		}
		assertTrue("Start node is not present ",
				robotiumSolo.searchText(robotiumSolo
						.getString(R.string.startNodeString)));
		assertTrue("Restaurant node is not present ",
				robotiumSolo.searchText(robotiumSolo
						.getString(R.string.restaurant_R)));
		assertTrue("Application name is not present",
				robotiumSolo.searchText(robotiumSolo
						.getString(R.string.app_name)));
		assertTrue("At the Game start, time is incorrect",
				robotiumSolo.searchText(robotiumSolo
						.getString(R.string.startTime)));
		assertTrue("At the Game start, time is incorrect",
				robotiumSolo.searchText(robotiumSolo
						.getString(R.string.amstring)));
		assertTrue("At the Game start, Money set is incorrect",
				robotiumSolo.searchText("0000"));

		PlayGridActivity playGridActivity = (PlayGridActivity) robotiumSolo
				.getCurrentActivity();

		// count the number of customer and non customer nodes
		NodeHolder nodeHolder = playGridActivity.getNodeHolder();
		assertEquals("Number of Customer Nodes should be 16", 16, nodeHolder
				.getCustomerNodes().size());
		assertEquals("Number of Non Customer Nodes should be 18", 18,
				nodeHolder.getEmptyNodes().size());

		// Non customer nodes should be empty
		for (Button tempButton : nodeHolder.getEmptyNodes()) {
			assertTrue("All Non customer nodes are not empty", (tempButton
					.getText().toString().trim().equals("")));
		}
		// customer nodes should not be empty
		for (Button tempButton : nodeHolder.getCustomerNodes()) {
			assertTrue("All customer nodes are not filled", (!tempButton
					.getText().toString().trim().equals("")));
		}

		// start button should have start text
		assertTrue("Start button doesn't have 'Start' text", (nodeHolder
				.getStartNode().getText().toString().trim().equals(robotiumSolo
				.getString(R.string.startNodeString).trim())));

		// Restaurant button should have R text
		assertTrue("Restaurant button doesn't have 'R' text",
				(nodeHolder.getRestaurantNode().getText().toString().trim()
						.equals(robotiumSolo.getString(R.string.restaurant_R)
								.trim())));
	}

}
