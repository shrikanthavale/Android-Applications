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
		assertTrue(
				"com.shrikanthavale.salesmanagement.PlayGridActivity is not found!",
				robotiumSolo
						.waitForActivity(com.shrikanthavale.salesmanagement.PlayGridActivity.class));

		Timeout.setSmallTimeout(22015);

		int counter = 0;

		while (counter < 3) {

			int randomNumber = (int) (Math.random() * (5 - 1)) + 1;
			System.out.println("****************" + randomNumber);

			switch (randomNumber) {
			case 1:
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
								.getView(com.shrikanthavale.salesmanagement.R.id.rightNavigation));
				break;
			case 2:
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.rightNavigation));
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
								.getView(com.shrikanthavale.salesmanagement.R.id.downNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.leftNavigation));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.upNavigation));
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
			default:
				break;
			}

			if (((PlayGridActivity) robotiumSolo.getCurrentActivity())
					.checkSufficientTimeCustomerTravel()
					|| ((PlayGridActivity) robotiumSolo.getCurrentActivity())
							.checkSufficientTimeCustomerVisit()) {
				break;
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

				assertTrue(
						"com.shrikanthavale.salesmanagement.CaseStudyDescriptionActivity is not found!",
						robotiumSolo
								.waitForActivity(com.shrikanthavale.salesmanagement.CaseStudyDescriptionActivity.class));
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.optionsNavigate));

				assertTrue(
						"com.shrikanthavale.salesmanagement.CaseStudyOptionsActivity is not found!",
						robotiumSolo
								.waitForActivity(com.shrikanthavale.salesmanagement.CaseStudyOptionsActivity.class));
				Timeout.setSmallTimeout(23088);
				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.radio_optionC));

				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.submitSoultionButton));

				robotiumSolo
						.clickOnView(robotiumSolo
								.getView(com.shrikanthavale.salesmanagement.R.id.continuePlayArea));

				assertTrue(
						"com.shrikanthavale.salesmanagement.PlayGridActivity is not found!",
						robotiumSolo
								.waitForActivity(com.shrikanthavale.salesmanagement.PlayGridActivity.class));
				Timeout.setSmallTimeout(37517);
				counter++;

			}
		}

		robotiumSolo
				.clickOnActionBarItem(com.shrikanthavale.salesmanagement.R.id.stop_game);
		robotiumSolo.waitForDialogToOpen(5000);
		robotiumSolo.clickOnView(robotiumSolo.getView(android.R.id.button1));
		assertTrue(
				"com.shrikanthavale.salesmanagement.OptimalSolutionActivity is not found!",
				robotiumSolo
						.waitForActivity(com.shrikanthavale.salesmanagement.OptimalSolutionActivity.class));
	}
}
