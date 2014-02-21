/**
 * 
 */
package com.shrikanthavale.cricketquiz.webserviceutility;

import java.util.List;

/**
 * @author Shrikant Havale
 * 
 */
public class SalesManagementSaveActivityState {

	/**
	 * save the list of customers visited
	 */
	private static List<Integer> customersVisited = null;

	/**
	 * string for storing AMPM value
	 */
	private static String ampmTimeValue = null;

	/**
	 * integer for storing hours
	 */
	private static int hoursSpent = 0;

	/**
	 * integer for storing minutes
	 */
	private static int minutesSpent = 0;

	/**
	 * amount earned
	 */
	private static int amountEarned = 0;

	/**
	 * check if loaded for first time
	 */
	private static boolean isFirstTimeLoad = true;

	/**
	 * store the focused button ID
	 */
	private static int focusedButtonID = -1;

	/**
	 * current customer
	 */
	private static String currentCustomer = "";

	/**
	 * customer during the administration
	 */
	private static String customerAdministration = "";

	/**
	 * flag about to end game
	 */
	private static boolean aboutToEndGame = false;

	/**
	 * @return the ampmTimeValue
	 */
	public static String getAmpmTimeValue() {
		return ampmTimeValue;
	}

	/**
	 * @param ampmTimeValue
	 *            the ampmTimeValue to set
	 */
	public static void setAmpmTimeValue(String ampmTimeValue) {
		SalesManagementSaveActivityState.ampmTimeValue = ampmTimeValue;
	}

	/**
	 * @return the hoursSpent
	 */
	public static int getHoursSpent() {
		return hoursSpent;
	}

	/**
	 * @param hoursSpent
	 *            the hoursSpent to set
	 */
	public static void setHoursSpent(int hoursSpent) {
		SalesManagementSaveActivityState.hoursSpent = hoursSpent;
	}

	/**
	 * @return the minutesSpent
	 */
	public static int getMinutesSpent() {
		return minutesSpent;
	}

	/**
	 * @param minutesSpent
	 *            the minutesSpent to set
	 */
	public static void setMinutesSpent(int minutesSpent) {
		SalesManagementSaveActivityState.minutesSpent = minutesSpent;
	}

	/**
	 * @return the amountEarned
	 */
	public static int getAmountEarned() {
		return amountEarned;
	}

	/**
	 * @param amountEarned
	 *            the amountEarned to set
	 */
	public static void setAmountEarned(int amountEarned) {
		SalesManagementSaveActivityState.amountEarned = amountEarned;
	}

	/**
	 * @return the isFirstTimeLoad
	 */
	public static boolean isFirstTimeLoad() {
		return isFirstTimeLoad;
	}

	/**
	 * @param isFirstTimeLoad
	 *            the isFirstTimeLoad to set
	 */
	public static void setFirstTimeLoad(boolean isFirstTimeLoad) {
		SalesManagementSaveActivityState.isFirstTimeLoad = isFirstTimeLoad;
	}

	/**
	 * @return the focusedButtonID
	 */
	public static int getFocusedButtonID() {
		return focusedButtonID;
	}

	/**
	 * @param focusedButtonID
	 *            the focusedButtonID to set
	 */
	public static void setFocusedButtonID(int focusedButtonID) {
		SalesManagementSaveActivityState.focusedButtonID = focusedButtonID;
	}

	/**
	 * @return the customersVisited
	 */
	public static List<Integer> getCustomersVisited() {
		return customersVisited;
	}

	/**
	 * @param customersVisited
	 *            the customersVisited to set
	 */
	public static void setCustomersVisited(List<Integer> customersVisited) {
		SalesManagementSaveActivityState.customersVisited = customersVisited;
	}

	/**
	 * @return the currentCustomer
	 */
	public static String getCurrentCustomer() {
		return currentCustomer;
	}

	/**
	 * @param currentCustomer
	 *            the currentCustomer to set
	 */
	public static void setCurrentCustomer(String currentCustomer) {
		SalesManagementSaveActivityState.currentCustomer = currentCustomer;
	}

	/**
	 * @return the customerAdministration
	 */
	public static String getCustomerAdministration() {
		return customerAdministration;
	}

	/**
	 * @param customerAdministration
	 *            the customerAdministration to set
	 */
	public static void setCustomerAdministration(String customerAdministration) {
		SalesManagementSaveActivityState.customerAdministration = customerAdministration;
	}

	/**
	 * @return the aboutToEndGame
	 */
	public static boolean isAboutToEndGame() {
		return aboutToEndGame;
	}

	/**
	 * @param aboutToEndGame
	 *            the aboutToEndGame to set
	 */
	public static void setAboutToEndGame(boolean aboutToEndGame) {
		SalesManagementSaveActivityState.aboutToEndGame = aboutToEndGame;
	}

}
