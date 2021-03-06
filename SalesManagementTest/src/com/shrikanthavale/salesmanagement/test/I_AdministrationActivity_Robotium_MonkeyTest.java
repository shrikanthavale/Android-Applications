/**
 * 
 */
package com.shrikanthavale.salesmanagement.test;

import android.app.Instrumentation;
import android.content.pm.PackageManager;
import android.test.ActivityInstrumentationTestCase2;
import android.view.Display;

import com.robotium.solo.Solo;
import com.shrikanthavale.salesmanagement.StartPageActivity;
import com.shrikanthavale.salesmanagement.administration.AdministrationActivity;

import eu.fbk.se.androidmonkey.Monkey;

/**
 * @author Shrikant Havale
 *
 */
public class I_AdministrationActivity_Robotium_MonkeyTest extends
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
	public I_AdministrationActivity_Robotium_MonkeyTest() {
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
	 * Test method for testing Sales Management Application
	 * {@link StartPageActivity}
	 */
	public void testSalesManagementStartPage() {

		// check that we have the right activity
		robotiumSolo.assertCurrentActivity("Found Wrong Activity",
				StartPageActivity.class);
		// click on start button
		robotiumSolo.clickOnButton(1);
		robotiumSolo.waitForDialogToOpen();
		assertTrue("Loading Dialogue Missing",
				robotiumSolo
						.waitForText("Loading List .... Please Wait"));
		robotiumSolo.waitForDialogToClose();
		robotiumSolo.waitForActivity(AdministrationActivity.class);
		robotiumSolo.assertCurrentActivity("Found Wrong Activity",
				AdministrationActivity.class);
		
		robotiumSolo.scrollListToBottom(0);
		robotiumSolo.scrollListToTop(0);
		robotiumSolo.clickInList(1);
		robotiumSolo.waitForDialogToOpen();
		assertTrue("Loading Dialogue Missing",
				robotiumSolo
						.waitForText("Loading Description Details .... Please Wait"));
		assertTrue("Loading Dialogue Missing",
				robotiumSolo
						.waitForText("Loading Option Details .... Please Wait"));
		robotiumSolo.waitForDialogToClose();
		
		robotiumSolo.scrollToSide(Solo.RIGHT);
		robotiumSolo.scrollToSide(Solo.LEFT);
		robotiumSolo.scrollToSide(Solo.LEFT);
		robotiumSolo.scrollListToBottom(0);
		robotiumSolo.scrollListToTop(0);
		
		robotiumSolo.clickInList(2);
		assertTrue("Loading Dialogue Missing",
				robotiumSolo
						.waitForText("Loading Description Details .... Please Wait"));
		assertTrue("Loading Dialogue Missing",
				robotiumSolo
						.waitForText("Loading Option Details .... Please Wait"));
		robotiumSolo.waitForDialogToClose();
		robotiumSolo.scrollToSide(Solo.RIGHT);
		
		robotiumSolo
		.clickOnView(robotiumSolo
				.getView(com.shrikanthavale.salesmanagement.R.id.adminOptionAbutton));
		robotiumSolo.sleep(1000);
		robotiumSolo
		.clickOnView(robotiumSolo
				.getView(com.shrikanthavale.salesmanagement.R.id.adminOptionBbutton));
		robotiumSolo.sleep(1000);
		robotiumSolo
		.clickOnView(robotiumSolo
				.getView(com.shrikanthavale.salesmanagement.R.id.adminOptionCbutton));
		robotiumSolo.sleep(1000);
		robotiumSolo
		.clickOnView(robotiumSolo
				.getView(com.shrikanthavale.salesmanagement.R.id.adminOptionDbutton));
		robotiumSolo.sleep(1000);
		
		robotiumSolo.clickOnView(robotiumSolo
				.getView(com.shrikanthavale.salesmanagement.R.id.administrationFragmentNodeList));

		robotiumSolo.clickOnView(robotiumSolo
				.getView(com.shrikanthavale.salesmanagement.R.id.administrationFragmentNodeOptionsID));

		robotiumSolo.clickOnView(robotiumSolo
				.getView(com.shrikanthavale.salesmanagement.R.id.administrationDescriptionLayoutFragmentID));

		
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		Instrumentation inst = getInstrumentation();
		PackageManager pm = getActivity().getPackageManager();

		Monkey monkey = new Monkey(display, packageToTest, inst, pm);

		for (int i = 0; i < NUM_EVENTS; i++) {
			monkey.nextRandomEvent();
		}
	}

}
