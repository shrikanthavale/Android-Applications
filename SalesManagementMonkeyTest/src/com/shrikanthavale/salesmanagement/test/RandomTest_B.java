package com.shrikanthavale.salesmanagement.test;


import com.shrikanthavale.salesmanagement.StartPageActivity;

import eu.fbk.se.androidmonkey.Monkey;
import android.app.Instrumentation;
import android.content.pm.PackageManager;
import android.test.ActivityInstrumentationTestCase2;
import android.view.Display;

public class RandomTest_B extends ActivityInstrumentationTestCase2<StartPageActivity> {
	
	private static final int NUM_EVENTS = 1000;
	private static final String packageToTest = "com.shrikanthavale.salesmanagement";
	
	public RandomTest_B(){
		super(packageToTest, StartPageActivity.class);
	}

	/**
	 * Trigger the monkey tester
	 */
	public void testMonkeyEvents(){
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		Instrumentation inst = getInstrumentation();
		PackageManager pm = getActivity().getPackageManager();

		Monkey monkey = new Monkey(display, packageToTest, inst, pm);
		
		for (int i = 0; i < NUM_EVENTS; i++){
			monkey.nextRandomEvent();
		}
		
	}
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		
		setActivityInitialTouchMode(false);
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	

}
