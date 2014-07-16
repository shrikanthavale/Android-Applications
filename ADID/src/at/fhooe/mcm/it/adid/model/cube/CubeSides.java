package at.fhooe.mcm.it.adid.model.cube;

import java.util.Vector;

import at.fhooe.mcm.it.adid.model.Step;

public class CubeSides {

	private Vector<CubeSide> mCubeSides;

	/**
	 * Constructor. Creates the CubeSide objects that get saved in CubeSides.
	 * With this CubeSideObjects it is always clear where which side of the cube
	 * is.
	 */
	public CubeSides() {
		CubeSide analysis1CubeSide = new CubeSide(Step.ANALYSIS_1,
				Step.ANALYSIS_2, Step.ANALYSIS_1, Step.ANALYSIS_1,
				Step.ANALYSIS_1, Step.ANALYSIS_1);

		CubeSide analysis2CubeSide = new CubeSide(Step.ANALYSIS_2,
				Step.ANALYSIS_3, Step.ANALYSIS_1, Step.ANALYSIS_1,
				Step.ANALYSIS_2, Step.ANALYSIS_2);

		CubeSide analysis3CubeSide = new CubeSide(Step.ANALYSIS_3,
				Step.ANALYSIS_4, Step.ANALYSIS_3, Step.ANALYSIS_2,
				Step.ANALYSIS_3, Step.ANALYSIS_3);

		CubeSide analysis4CubeSide = new CubeSide(Step.ANALYSIS_4,
				Step.ANALYSIS_5, Step.ANALYSIS_4, Step.ANALYSIS_3,
				Step.ANALYSIS_4, Step.ANALYSIS_4);

		CubeSide analysis5CubeSide = new CubeSide(Step.ANALYSIS_5,
				Step.ANALYSIS_6, Step.ANALYSIS_5, Step.ANALYSIS_4,
				Step.ANALYSIS_5, Step.ANALYSIS_5);

		CubeSide analysis6CubeSide = new CubeSide(Step.ANALYSIS_6,
				Step.ANALYSIS_7, Step.ANALYSIS_6, Step.ANALYSIS_5,
				Step.ANALYSIS_6, Step.ANALYSIS_6);

		CubeSide analysis7CubeSide = new CubeSide(Step.ANALYSIS_7,
				Step.ANALYSIS_8, Step.ANALYSIS_7, Step.ANALYSIS_6,
				Step.ANALYSIS_7, Step.ANALYSIS_7);

		CubeSide analysis8CubeSide = new CubeSide(Step.ANALYSIS_8,
				Step.ANALYSIS_9, Step.ANALYSIS_8, Step.ANALYSIS_7,
				Step.ANALYSIS_8, Step.ANALYSIS_8);

		CubeSide analysis9CubeSide = new CubeSide(Step.ANALYSIS_9,
				Step.ANALYSIS_10, Step.ANALYSIS_9, Step.ANALYSIS_8,
				Step.ANALYSIS_9, Step.ANALYSIS_9);

		CubeSide analysis10CubeSide = new CubeSide(Step.ANALYSIS_10,
				Step.ANALYSIS_QUIZ, Step.ANALYSIS_10, Step.ANALYSIS_9,
				Step.ANALYSIS_10, Step.ANALYSIS_10);

		CubeSide analysisQuizCubeSide = new CubeSide(Step.ANALYSIS_QUIZ,
				Step.ANALYSIS_QUIZ, Step.DESIGN_1, Step.ANALYSIS_10,
				Step.ANALYSIS_QUIZ, Step.ANALYSIS_QUIZ);

		CubeSide design1CubeSide = new CubeSide(Step.DESIGN_1, Step.DESIGN_2,
				Step.DESIGN_1, Step.DESIGN_1, Step.ANALYSIS_QUIZ, Step.DESIGN_1);

		CubeSide design2CubeSide = new CubeSide(Step.DESIGN_2, Step.DESIGN_3,
				Step.DESIGN_2, Step.DESIGN_1, Step.ANALYSIS_QUIZ, Step.DESIGN_2);

		CubeSide design3CubeSide = new CubeSide(Step.DESIGN_3, Step.DESIGN_4,
				Step.DESIGN_3, Step.DESIGN_2, Step.ANALYSIS_QUIZ, Step.DESIGN_3);

		CubeSide design4CubeSide = new CubeSide(Step.DESIGN_4, Step.DESIGN_5,
				Step.DESIGN_4, Step.DESIGN_3, Step.ANALYSIS_QUIZ, Step.DESIGN_4);

		CubeSide design5CubeSide = new CubeSide(Step.DESIGN_5, Step.DESIGN_6,
				Step.DESIGN_5, Step.DESIGN_4, Step.ANALYSIS_QUIZ, Step.DESIGN_5);

		CubeSide design6CubeSide = new CubeSide(Step.DESIGN_6, Step.DESIGN_7,
				Step.DESIGN_6, Step.DESIGN_5, Step.ANALYSIS_QUIZ, Step.DESIGN_6);

		CubeSide design7CubeSide = new CubeSide(Step.DESIGN_7, Step.DESIGN_8,
				Step.DESIGN_7, Step.DESIGN_6, Step.ANALYSIS_QUIZ, Step.DESIGN_7);

		CubeSide design8CubeSide = new CubeSide(Step.DESIGN_8, Step.DESIGN_9,
				Step.DESIGN_8, Step.DESIGN_7, Step.ANALYSIS_QUIZ, Step.DESIGN_8);

		CubeSide design9CubeSide = new CubeSide(Step.DESIGN_9, Step.DESIGN_10,
				Step.DESIGN_9, Step.DESIGN_8, Step.ANALYSIS_QUIZ, Step.DESIGN_9);

		CubeSide design10CubeSide = new CubeSide(Step.DESIGN_10,
				Step.DESIGN_QUIZ, Step.DESIGN_10, Step.DESIGN_9,
				Step.ANALYSIS_QUIZ, Step.DESIGN_10);

		CubeSide designQuizCubeSide = new CubeSide(Step.DESIGN_QUIZ,
				Step.DESIGN_QUIZ, Step.IMPLEMENTATION_1, Step.DESIGN_10,
				Step.ANALYSIS_QUIZ, Step.DESIGN_QUIZ);

		CubeSide implementation1CubeSide = new CubeSide(Step.IMPLEMENTATION_1,
				Step.IMPLEMENTATION_2, Step.IMPLEMENTATION_1,
				Step.IMPLEMENTATION_1, Step.DESIGN_QUIZ, Step.IMPLEMENTATION_1);

		CubeSide implementation2CubeSide = new CubeSide(Step.IMPLEMENTATION_2,
				Step.IMPLEMENTATION_3, Step.IMPLEMENTATION_2,
				Step.IMPLEMENTATION_1, Step.DESIGN_QUIZ, Step.IMPLEMENTATION_2);

		CubeSide implementation3CubeSide = new CubeSide(Step.IMPLEMENTATION_3,
				Step.IMPLEMENTATION_QUIZ, Step.IMPLEMENTATION_3,
				Step.IMPLEMENTATION_2, Step.DESIGN_QUIZ, Step.IMPLEMENTATION_3);

		CubeSide implementationQuizCubeSide = new CubeSide(
				Step.IMPLEMENTATION_QUIZ, Step.IMPLEMENTATION_QUIZ,
				Step.DEPLOYMENT_1, Step.IMPLEMENTATION_3, Step.DESIGN_QUIZ,
				Step.IMPLEMENTATION_QUIZ);

		CubeSide deployment1CubeSide = new CubeSide(Step.DEPLOYMENT_1,
				Step.DEPLOYMENT_2, Step.DEPLOYMENT_1, Step.DEPLOYMENT_1,
				Step.IMPLEMENTATION_QUIZ, Step.DEPLOYMENT_1);

		CubeSide deployment2CubeSide = new CubeSide(Step.DEPLOYMENT_2,
				Step.DEPLOYMENT_3, Step.DEPLOYMENT_2, Step.DEPLOYMENT_1,
				Step.IMPLEMENTATION_QUIZ, Step.DEPLOYMENT_2);

		CubeSide deployment3CubeSide = new CubeSide(Step.DEPLOYMENT_3,
				Step.DEPLOYMENT_QUIZ, Step.DEPLOYMENT_3, Step.DEPLOYMENT_2,
				Step.IMPLEMENTATION_QUIZ, Step.DEPLOYMENT_3);

		CubeSide deploymentQuizCubeSide = new CubeSide(Step.DEPLOYMENT_QUIZ,
				Step.DEPLOYMENT_QUIZ, Step.DEPLOYMENT_QUIZ, Step.DEPLOYMENT_3,
				Step.IMPLEMENTATION_QUIZ, Step.DEPLOYMENT_QUIZ);

		mCubeSides = new Vector<CubeSide>();
		mCubeSides.add(analysis1CubeSide);
		mCubeSides.add(analysis2CubeSide);
		mCubeSides.add(analysis3CubeSide);
		mCubeSides.add(analysis4CubeSide);
		mCubeSides.add(analysis5CubeSide);
		mCubeSides.add(analysis6CubeSide);
		mCubeSides.add(analysis7CubeSide);
		mCubeSides.add(analysis8CubeSide);
		mCubeSides.add(analysis9CubeSide);
		mCubeSides.add(analysis10CubeSide);
		mCubeSides.add(analysisQuizCubeSide);
		mCubeSides.add(design1CubeSide);
		mCubeSides.add(design2CubeSide);
		mCubeSides.add(design3CubeSide);
		mCubeSides.add(design4CubeSide);
		mCubeSides.add(design5CubeSide);
		mCubeSides.add(design6CubeSide);
		mCubeSides.add(design7CubeSide);
		mCubeSides.add(design8CubeSide);
		mCubeSides.add(design9CubeSide);
		mCubeSides.add(design10CubeSide);
		mCubeSides.add(designQuizCubeSide);
		mCubeSides.add(implementation1CubeSide);
		mCubeSides.add(implementation2CubeSide);
		mCubeSides.add(implementation3CubeSide);
		mCubeSides.add(implementationQuizCubeSide);
		mCubeSides.add(deployment1CubeSide);
		mCubeSides.add(deployment2CubeSide);
		mCubeSides.add(deployment3CubeSide);
		mCubeSides.add(deploymentQuizCubeSide);
	}

	/**
	 * Returns the CubeSide object at the given index.
	 * 
	 * @param _index
	 *            The index of the element.
	 * @return A CubeSide object.
	 */
	public CubeSide getCubeSideAt(Step _index) {
		return mCubeSides.elementAt(_index.getValue());
	}

	/**
	 * Returns the current cube side of the the CubeSide object at the given
	 * index.
	 * 
	 * @param _index
	 *            The index of the element.
	 * @return The Step representation of the current cube side of the CubeSide
	 *         object.
	 */
	public Step getCurrentSideOfCubeSideAt(Step _index) {
		return mCubeSides.elementAt(_index.getValue()).getCurrentCubeSide();
	}

	/**
	 * Returns the upper cube side of the the CubeSide object at the given
	 * index.
	 * 
	 * @param _index
	 *            The index of the element.
	 * @return The Step representation of the upper cube side of the CubeSide
	 *         object.
	 */
	public Step getUpperSideOfCubeSideAt(Step _index) {
		return mCubeSides.elementAt(_index.getValue()).getUp();
	}

	/**
	 * Returns the right cube side of the the CubeSide object at the given
	 * index.
	 * 
	 * @param _index
	 *            The index of the element.
	 * @return The Step representation of the right cube side of the CubeSide
	 *         object.
	 */
	public Step getRightSideOfCubeSideAt(Step _index) {
		return mCubeSides.elementAt(_index.getValue()).getRight();
	}

	/**
	 * Returns the down cube side of the the CubeSide object at the given index.
	 * 
	 * @param _index
	 *            The index of the element.
	 * @return The Step representation of the current cube side of the CubeSide
	 *         object.
	 */
	public Step getDownSideOfCubeSideAt(Step _index) {
		return mCubeSides.elementAt(_index.getValue()).getDown();
	}

	/**
	 * Returns the left cube side of the the CubeSide object at the given index.
	 * 
	 * @param _index
	 *            The index of the element.
	 * @return The Step representation of the left cube side of the CubeSide
	 *         object.
	 */
	public Step getLeftSideOfCubeSideAt(Step _index) {
		return mCubeSides.elementAt(_index.getValue()).getLeft();
	}

	/**
	 * Returns the back cube side of the the CubeSide object at the given index.
	 * 
	 * @param _index
	 *            The index of the element.
	 * @return The Step representation of the back cube side of the CubeSide
	 *         object.
	 */
	public Step getBackSideOfCubeSideAt(Step _index) {
		return mCubeSides.elementAt(_index.getValue()).getBack();
	}
}
