package com.shrikanthavale.salesmanagement.test;


import com.robotium.solo.Solo;
import com.shrikanthavale.salesmanagement.StartPageActivity;

import eu.fbk.se.androidmonkey.Monkey;
import android.app.Instrumentation;
import android.content.pm.PackageManager;
import android.test.ActivityInstrumentationTestCase2;
import android.view.Display;

public class RandomTest_A extends ActivityInstrumentationTestCase2<StartPageActivity> {
	
	private static final int NUM_EVENTS = 500;
	private static final String packageToTest = "com.shrikanthavale.salesmanagement";
	private Solo robotiumSolo;
	
	public RandomTest_A(){
		super(StartPageActivity.class);
	}

	/**
	 * Trigger the monkey tester
	 */
	public void testMonkeyEvents1(){
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		Instrumentation inst = getInstrumentation();
		PackageManager pm = getActivity().getPackageManager();

		Monkey monkey = new Monkey(display, packageToTest, inst, pm);
		
		for (int i = 0; i < NUM_EVENTS; i++){
			monkey.nextRandomEvent();
		}
		
	}
	
	/**
	 * Trigger the monkey tester
	 */
	public void testMonkeyEvents2(){
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		Instrumentation inst = getInstrumentation();
		PackageManager pm = getActivity().getPackageManager();

		Monkey monkey = new Monkey(display, packageToTest, inst, pm);
		
		for (int i = 0; i < NUM_EVENTS; i++){
			monkey.nextRandomEvent();
		}
		
	}
	
	/**
	 * Trigger the monkey tester
	 */
	public void testMonkeyEvents3(){
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		Instrumentation inst = getInstrumentation();
		PackageManager pm = getActivity().getPackageManager();

		Monkey monkey = new Monkey(display, packageToTest, inst, pm);
		
		for (int i = 0; i < NUM_EVENTS; i++){
			monkey.nextRandomEvent();
		}
		
	}
	
	/**
	 * Trigger the monkey tester
	 */
	public void testMonkeyEvents4(){
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		Instrumentation inst = getInstrumentation();
		PackageManager pm = getActivity().getPackageManager();

		Monkey monkey = new Monkey(display, packageToTest, inst, pm);
		
		for (int i = 0; i < NUM_EVENTS; i++){
			monkey.nextRandomEvent();
		}
		
	}
	
	/**
	 * Trigger the monkey tester
	 */
	public void testMonkeyEvents5(){
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		Instrumentation inst = getInstrumentation();
		PackageManager pm = getActivity().getPackageManager();

		Monkey monkey = new Monkey(display, packageToTest, inst, pm);
		
		for (int i = 0; i < NUM_EVENTS; i++){
			monkey.nextRandomEvent();
		}
		
	}
	
	/**
	 * Trigger the monkey tester
	 */
	public void testMonkeyEvents6(){
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		Instrumentation inst = getInstrumentation();
		PackageManager pm = getActivity().getPackageManager();

		Monkey monkey = new Monkey(display, packageToTest, inst, pm);
		
		for (int i = 0; i < NUM_EVENTS; i++){
			monkey.nextRandomEvent();
		}
		
	}
	
	/**
	 * Trigger the monkey tester
	 */
	public void testMonkeyEvents7(){
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		Instrumentation inst = getInstrumentation();
		PackageManager pm = getActivity().getPackageManager();

		Monkey monkey = new Monkey(display, packageToTest, inst, pm);
		
		for (int i = 0; i < NUM_EVENTS; i++){
			monkey.nextRandomEvent();
		}
		
	}
	
	/**
	 * Trigger the monkey tester
	 */
	public void testMonkeyEvents8(){
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		Instrumentation inst = getInstrumentation();
		PackageManager pm = getActivity().getPackageManager();

		Monkey monkey = new Monkey(display, packageToTest, inst, pm);
		
		for (int i = 0; i < NUM_EVENTS; i++){
			monkey.nextRandomEvent();
		}
		
	}
	
	/**
	 * Trigger the monkey tester
	 */
	public void testMonkeyEvents9(){
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		Instrumentation inst = getInstrumentation();
		PackageManager pm = getActivity().getPackageManager();

		Monkey monkey = new Monkey(display, packageToTest, inst, pm);
		
		for (int i = 0; i < NUM_EVENTS; i++){
			monkey.nextRandomEvent();
		}
		
	}
	
	/**
	 * Trigger the monkey tester
	 */
	public void testMonkeyEvents10(){
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
		super.setUp();
		setActivityInitialTouchMode(false);
		robotiumSolo = new Solo(getInstrumentation(), getActivity());
	}

	@Override
	protected void tearDown() throws Exception {
		robotiumSolo.finishOpenedActivities();
		super.tearDown();
		
	}
	
	

}
