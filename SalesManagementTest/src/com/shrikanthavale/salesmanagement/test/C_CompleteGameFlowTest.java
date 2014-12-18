/**
 * 
 */
package com.shrikanthavale.salesmanagement.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;
import com.shrikanthavale.salesmanagement.PlayGridActivity;
import com.shrikanthavale.salesmanagement.R;
import com.shrikanthavale.salesmanagement.StartPageActivity;

/**
 * @author Shrikant Havale
 * 
 */
public class C_CompleteGameFlowTest extends
		ActivityInstrumentationTestCase2<StartPageActivity> {

	/**
	 * Robotium solo object
	 */
	private Solo robotiumSolo;

	/**
	 * @param name
	 */
	public C_CompleteGameFlowTest() {
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

		robotiumSolo.waitForActivity(
				com.shrikanthavale.salesmanagement.StartPageActivity.class,
				2000);
		assertTrue(
				"com.shrikanthavale.salesmanagement.StartPageActivity is not found!",
				robotiumSolo
						.waitForActivity(com.shrikanthavale.salesmanagement.StartPageActivity.class));
		robotiumSolo
				.clickOnView(robotiumSolo
						.getView(com.shrikanthavale.salesmanagement.R.id.startPageStartButton));
		robotiumSolo.waitForDialogToOpen();
		assertTrue("Loading Dialogue Missing",
				robotiumSolo
						.waitForText("Loading Node Details .... Please Wait"));
		robotiumSolo.waitForDialogToClose();
		assertTrue(
				"com.shrikanthavale.salesmanagement.PlayGridActivity is not found!",
				robotiumSolo
						.waitForActivity(com.shrikanthavale.salesmanagement.PlayGridActivity.class));

		Timeout.setSmallTimeout(22015);

		while (true) {

			int randomNumber = (int) (Math.random() * (5 - 1)) + 1;
			System.out.println("****************" + randomNumber);

			switch (randomNumber) {
			case 1:
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.upNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.upNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.leftNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.rightNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.downNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.rightNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.downNavigation));

				break;
			case 2:
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.rightNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.leftNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.downNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.upNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.leftNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.downNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.upNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.leftNavigation));

			case 3:
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.rightNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.leftNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.upNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.downNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.leftNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.upNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.downNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.leftNavigation));
			case 4:
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.downNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.rightNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.leftNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.upNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.rightNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.leftNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.downNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.rightNavigation));
			default:
				break;
			}

			if (((PlayGridActivity) robotiumSolo.getCurrentActivity())
					.checkSufficientTimeCustomerTravel()
					|| ((PlayGridActivity) robotiumSolo.getCurrentActivity())
							.checkSufficientTimeCustomerVisit()) {
				robotiumSolo.sendKey(Solo.MENU);
				robotiumSolo.sleep(1000);
				robotiumSolo.waitForText(robotiumSolo
						.getString(R.string.refreshGrid));
				robotiumSolo.clickOnActionBarItem(R.id.refresh_game);
				robotiumSolo.waitForDialogToOpen();
				robotiumSolo.clickOnButton(robotiumSolo
						.getString(R.string.popupdialogConfirmButton));
				robotiumSolo.waitForDialogToClose();
				robotiumSolo.waitForDialogToOpen();
				robotiumSolo.waitForDialogToClose();
				continue;
			}

			robotiumSolo
					.clickOnView(robotiumSolo
							.getView(com.shrikanthavale.salesmanagement.R.id.confirmSelection));

			// get the current focus button
			Button focusedButton = ((PlayGridActivity) robotiumSolo
					.getCurrentActivity()).getFocusedButton();

			if (focusedButton.getText().toString().trim()
					.equals(robotiumSolo.getString(R.string.emptystring))
					|| focusedButton.getText().toString().trim()
							.contains("Start")
					|| focusedButton.getText().toString().trim().contains("R")) {
				// non customer node was clicked
				assertTrue(
						"Non Customer node clicked error message missing",
						robotiumSolo.searchText(robotiumSolo
								.getString(R.string.nonnodeclickederrormessage)));
				continue;

			} else if (((PlayGridActivity) robotiumSolo.getCurrentActivity())
					.getVisitedCustomers().contains(focusedButton.getId())) {

				assertTrue(
						"Visited Customer node clicked error message missing",
						robotiumSolo.searchText(robotiumSolo
								.getString(R.string.alreadyvisitednode)));

				continue;

			} else {
				robotiumSolo.waitForDialogToOpen();
				robotiumSolo.waitForDialogToClose();
				assertTrue(
						"com.shrikanthavale.salesmanagement.CaseStudyDescriptionActivity is not found!",
						robotiumSolo
								.waitForActivity(com.shrikanthavale.salesmanagement.CaseStudyDescriptionActivity.class));
				break;

			}
		}

		robotiumSolo.clickOnView(robotiumSolo.getView(R.id.scrollView1));
		robotiumSolo.scrollDown();
		robotiumSolo.scrollUp();
		robotiumSolo.scrollDown();
		robotiumSolo.scrollUp();
		
		robotiumSolo
				.clickOnView(robotiumSolo
						.getView(com.shrikanthavale.salesmanagement.R.id.optionsNavigate));
		robotiumSolo.waitForDialogToOpen();
		assertTrue(
				"Loading Dialogue Missing",
				robotiumSolo
						.waitForText("Loading Option Details .... Please Wait"));
		robotiumSolo.waitForDialogToClose();
		assertTrue(
				"com.shrikanthavale.salesmanagement.CaseStudyDescriptionActivity is not found!",
				robotiumSolo
						.waitForActivity(com.shrikanthavale.salesmanagement.CaseStudyOptionsActivity.class));
		
		robotiumSolo.clickOnView(robotiumSolo.getView(R.id.scrollViewOptions));
		robotiumSolo.scrollDown();
		robotiumSolo.scrollUp();
		robotiumSolo.scrollDown();
		robotiumSolo.scrollUp();
		
		robotiumSolo.clickOnRadioButton(0);
		robotiumSolo.clickOnRadioButton(1);
		robotiumSolo.clickOnRadioButton(2);
		robotiumSolo.clickOnRadioButton(3);
		
		robotiumSolo.clickOnRadioButton(1);
		
		robotiumSolo
		.clickOnView(robotiumSolo
				.getView(com.shrikanthavale.salesmanagement.R.id.submitSoultionButton));
		assertTrue("No Explanation Message Displayed", robotiumSolo.getText(2)!=null && !robotiumSolo.getText(2).getText().equals("") );
		robotiumSolo.sleep(1000);

		robotiumSolo
		.clickOnView(robotiumSolo
				.getView(com.shrikanthavale.salesmanagement.R.id.continuePlayArea));
		
		robotiumSolo.waitForDialogToOpen();
		assertTrue("Loading Dialogue Missing",
				robotiumSolo
						.waitForText("Loading Node Details .... Please Wait"));
		robotiumSolo.waitForDialogToClose();
	    robotiumSolo.assertCurrentActivity("Found Wrong Activity", PlayGridActivity.class);
		
		
		robotiumSolo
				.clickOnActionBarItem(com.shrikanthavale.salesmanagement.R.id.stop_game);
		robotiumSolo.waitForDialogToOpen(2000);
		robotiumSolo.clickOnView(robotiumSolo.getView(android.R.id.button1));
		assertTrue(
				"com.shrikanthavale.salesmanagement.OptimalSolutionActivity is not found!",
				robotiumSolo
						.waitForActivity(com.shrikanthavale.salesmanagement.OptimalSolutionActivity.class));
		
		robotiumSolo
		.clickOnView(robotiumSolo
				.getView(com.shrikanthavale.salesmanagement.R.id.optimalSolutionBackButton));
		robotiumSolo.waitForDialogToOpen();
		assertTrue("Loading Dialogue Missing",
				robotiumSolo
						.waitForText("Loading Node Details .... Please Wait"));
		robotiumSolo.waitForDialogToClose();
		
	    robotiumSolo.goBack();
	    robotiumSolo.waitForDialogToOpen();
	    robotiumSolo.clickOnButton("Yes");
	    robotiumSolo.waitForDialogToClose();
	    robotiumSolo.waitForActivity(StartPageActivity.class);
	    robotiumSolo.assertCurrentActivity("Found Wrong Activity", StartPageActivity.class);
		
	}
}
