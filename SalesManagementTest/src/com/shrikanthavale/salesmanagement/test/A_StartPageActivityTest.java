/**
 * 
 */
package com.shrikanthavale.salesmanagement.test;

import org.junit.Ignore;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;
import com.shrikanthavale.salesmanagement.PlayGridActivity;
import com.shrikanthavale.salesmanagement.StartPageActivity;
import com.shrikanthavale.salesmanagement.administration.AdministrationActivity;
import com.shrikanthavale.salesmanagement.R;

/**
 * @author Shrikant Havale
 *
 */
@Ignore
public class A_StartPageActivityTest extends
		ActivityInstrumentationTestCase2<StartPageActivity> {
	
	/**
	 * Robotium solo object
	 */
	private Solo robotiumSolo;

	/**
	 * @param name
	 */
	public A_StartPageActivityTest() {
		super(StartPageActivity.class);
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		robotiumSolo = new Solo(getInstrumentation(), getActivity());
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#tearDown()
	 */
	protected void tearDown() throws Exception {
		robotiumSolo.finishOpenedActivities();
		super.tearDown();
	}

	/**
	 * Test method for testing Sales Management Application {@link StartPageActivity}
	 */
	public void testSalesManagementStartPage()  {
		
		// check that we have the right activity
	    robotiumSolo.assertCurrentActivity("Found Wrong Activity", StartPageActivity.class);
	    // click on start button
	    robotiumSolo.clickOnButton(0);
		robotiumSolo.waitForDialogToOpen();
		assertTrue("Loading Dialogue Missing",
				robotiumSolo
						.waitForText("Loading Node Details .... Please Wait"));
		robotiumSolo.waitForDialogToClose();
	    robotiumSolo.assertCurrentActivity("Found Wrong Activity", PlayGridActivity.class);
	    
	    robotiumSolo.goBack();
	    robotiumSolo.waitForDialogToOpen();
	    robotiumSolo.clickOnButton("Yes");
	    robotiumSolo.waitForDialogToClose();
	    robotiumSolo.waitForActivity(StartPageActivity.class);
	    robotiumSolo.assertCurrentActivity("Found Wrong Activity", StartPageActivity.class);
	    robotiumSolo.clickOnButton(1);
		robotiumSolo.waitForDialogToOpen();
		assertTrue("Loading Dialogue Missing",
				robotiumSolo
						.waitForText("Loading List .... Please Wait"));
		robotiumSolo.waitForDialogToClose();
	    robotiumSolo.assertCurrentActivity("Found Wrong Activity", AdministrationActivity.class);
	    
	    robotiumSolo.goBack();
	    robotiumSolo.sleep(1000);
	    robotiumSolo.waitForText(robotiumSolo.getString(R.string.app_name));
	    robotiumSolo.waitForActivity(StartPageActivity.class);
	    robotiumSolo.assertCurrentActivity("Found Wrong Activity", StartPageActivity.class);
	}

}
