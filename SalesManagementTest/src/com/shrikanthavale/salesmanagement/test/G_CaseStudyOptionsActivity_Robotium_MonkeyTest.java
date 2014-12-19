/**
 * 
 */
package com.shrikanthavale.salesmanagement.test;

import android.app.Instrumentation;
import android.content.pm.PackageManager;
import android.test.ActivityInstrumentationTestCase2;
import android.view.Display;
import android.widget.Button;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;
import com.shrikanthavale.salesmanagement.PlayGridActivity;
import com.shrikanthavale.salesmanagement.R;
import com.shrikanthavale.salesmanagement.StartPageActivity;

import eu.fbk.se.androidmonkey.Monkey;

/**
 * @author Shrikant Havale
 * 
 */
public class G_CaseStudyOptionsActivity_Robotium_MonkeyTest extends
		ActivityInstrumentationTestCase2<StartPageActivity> {

	/**
	 * Robotium solo object
	 */
	private Solo robotiumSolo;
	private static final int NUM_EVENTS = 400;
	private static final String packageToTest = "com.shrikanthavale.salesmanagement";

	/**
	 * @param name
	 */
	public G_CaseStudyOptionsActivity_Robotium_MonkeyTest() {
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
				robotiumSolo.waitForText(robotiumSolo.getString(R.string.refreshGrid));
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
		
		robotiumSolo.clickOnRadioButton(1);

		Display display = robotiumSolo.getCurrentActivity().getWindowManager().getDefaultDisplay();
		Instrumentation inst = getInstrumentation();
		PackageManager pm = robotiumSolo.getCurrentActivity().getPackageManager();

		Monkey monkey = new Monkey(display, packageToTest, inst, pm);

		for (int i = 0; i < NUM_EVENTS; i++) {
			monkey.nextRandomEvent();
		}
	}
}
