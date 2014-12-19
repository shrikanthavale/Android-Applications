package com.shrikanthavale.salesmanagement.test;


import com.robotium.solo.Solo;
import com.shrikanthavale.salesmanagement.StartPageActivity;

import eu.fbk.se.androidmonkey.Monkey;
import android.app.Instrumentation;
import android.content.pm.PackageManager;
import android.test.ActivityInstrumentationTestCase2;
import android.view.Display;

public class D_Monkey_RandomTest extends ActivityInstrumentationTestCase2<StartPageActivity> {
	
	private static final int NUM_EVENTS = 400;
	private static final String packageToTest = "com.shrikanthavale.salesmanagement";
	private Solo robotiumSolo;
	
	public D_Monkey_RandomTest(){
		super(StartPageActivity.class);
	}

	/**
	 * Trigger the monkey tester
	 */
	public void testMonkeyEvents(){
		robotiumSolo.waitForActivity(
				com.shrikanthavale.salesmanagement.StartPageActivity.class,
				2000);
		assertTrue(
				"com.shrikanthavale.salesmanagement.StartPageActivity is not found!",
				robotiumSolo
						.waitForActivity(com.shrikanthavale.salesmanagement.StartPageActivity.class));
		
		Display display = robotiumSolo.getCurrentActivity().getWindowManager().getDefaultDisplay();
		Instrumentation inst = getInstrumentation();
		PackageManager pm = robotiumSolo.getCurrentActivity().getPackageManager();

		Monkey monkey = new Monkey(display, packageToTest, inst, pm);
		
		for (int i = 0; i < NUM_EVENTS; i++){
			monkey.nextRandomEvent();
		}
		
	}
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		robotiumSolo = new Solo(getInstrumentation(), getActivity());
	}

	@Override
	protected void tearDown() throws Exception {
		robotiumSolo.finishOpenedActivities();
		super.tearDown();
		
	}
	
}
