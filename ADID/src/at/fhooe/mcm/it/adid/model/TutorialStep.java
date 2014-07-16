package at.fhooe.mcm.it.adid.model;

/**
 * Enum that represents the steps in the tutorial.
 * 
 * @author Mario Kraml
 * 
 */
public enum TutorialStep {
	// The different steps in the tutorial
	TUTORIAL_1(0), TUTORIAL_2(1), TUTORIAL_3(2), TUTORIAL_4(3), TUTORIAL_5(4), TUTORIAL_6(
			5);

	int mValue;

	/**
	 * Constructor
	 * 
	 * @param value
	 *            The int value for the enum
	 */
	TutorialStep(int phase) {
		mValue = phase;
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
