/**
 * 
 */
package com.shrikanthavale.salesmanagement.test;

import android.app.Instrumentation;
import android.content.pm.PackageManager;
import android.test.ActivityInstrumentationTestCase2;
import android.view.Display;

import com.robotium.solo.Solo;
import com.shrikanthavale.salesmanagement.PlayGridActivity;
import com.shrikanthavale.salesmanagement.StartPageActivity;

import eu.fbk.se.androidmonkey.Monkey;

/**
 * @author Shrikant Havale
 *
 */
public class E_PlayGameActivity_MonkeyTest extends
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
	public E_PlayGameActivity_MonkeyTest() {
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
		robotiumSolo.clickOnButton(0);
		robotiumSolo.waitForDialogToOpen();
		assertTrue("Loading Dialogue Missing",
				robotiumSolo
						.waitForText("Loading Node Details .... Please Wait"));
		robotiumSolo.waitForDialogToClose();
		robotiumSolo.assertCurrentActivity("Found Wrong Activity",
				PlayGridActivity.class);

		Display display = getActivity().getWindowManager().getDefaultDisplay();
		Instrumentation inst = getInstrumentation();
		PackageManager pm = getActivity().getPackageManager();

		Monkey monkey = new Monkey(display, packageToTest, inst, pm);

		for (int i = 0; i < NUM_EVENTS; i++) {
			monkey.nextRandomEvent();
		}
	}

}
