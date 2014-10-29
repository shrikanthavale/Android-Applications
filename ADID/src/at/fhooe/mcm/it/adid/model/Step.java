package at.fhooe.mcm.it.adid.model;

/**
 * Enum that represents the steps in the design process.
 * 
 * @author Mario Kraml
 * 
 */
public enum Step {
	// The different steps in the design process
	ANALYSIS_1(0), ANALYSIS_2(1), ANALYSIS_3(2), ANALYSIS_4(3), ANALYSIS_5(4), ANALYSIS_6(
			5), ANALYSIS_7(6), ANALYSIS_8(7), ANALYSIS_9(8), ANALYSIS_10(9), ANALYSIS_QUIZ(
			10), DESIGN_1(11), DESIGN_2(12), DESIGN_3(13), DESIGN_4(14), DESIGN_5(
			15), DESIGN_6(16), DESIGN_7(17), DESIGN_8(18), DESIGN_9(19), DESIGN_10(
			20), DESIGN_QUIZ(21), IMPLEMENTATION_1(22), IMPLEMENTATION_2(23), IMPLEMENTATION_3(
			24), IMPLEMENTATION_QUIZ(25), DEPLOYMENT_1(26), DEPLOYMENT_2(27), DEPLOYMENT_3(
			28), DEPLOYMENT_QUIZ(29);

	private int mValue;

	/**
	 * Constructor
	 * 
	 * @param value
	 *            The int value for the enum
	 */
	Step(int value) {
		mValue = value;
	}

	/**
	 * Getter for the value of the enum
	 * 
	 * @return The int value of the enum
	 */
	public int getValue() {
		return mValue;
	}
}
