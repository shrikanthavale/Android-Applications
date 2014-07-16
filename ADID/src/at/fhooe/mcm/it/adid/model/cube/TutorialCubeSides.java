package at.fhooe.mcm.it.adid.model.cube;

import java.util.Vector;

import at.fhooe.mcm.it.adid.model.TutorialStep;

public class TutorialCubeSides {

	private Vector<TutorialCubeSide> mCubeSides;

	/**
	 * Constructor. Creates the TutorialCubeSide objects that get saved in
	 * mCubeSides. With this CubeSideObjects it is always clear where which side
	 * of a cube is in the TutorialActivity.
	 */
	public TutorialCubeSides() {
		TutorialCubeSide tutorial1CubeSide = new TutorialCubeSide(
				TutorialStep.TUTORIAL_1, TutorialStep.TUTORIAL_2,
				TutorialStep.TUTORIAL_1, TutorialStep.TUTORIAL_1,
				TutorialStep.TUTORIAL_1, TutorialStep.TUTORIAL_1);

		TutorialCubeSide tutorial2CubeSide = new TutorialCubeSide(
				TutorialStep.TUTORIAL_2, TutorialStep.TUTORIAL_3,
				TutorialStep.TUTORIAL_2, TutorialStep.TUTORIAL_1,
				TutorialStep.TUTORIAL_2, TutorialStep.TUTORIAL_2);

		TutorialCubeSide tutorial3CubeSide = new TutorialCubeSide(
				TutorialStep.TUTORIAL_3, TutorialStep.TUTORIAL_4,
				TutorialStep.TUTORIAL_3, TutorialStep.TUTORIAL_2,
				TutorialStep.TUTORIAL_3, TutorialStep.TUTORIAL_3);

		TutorialCubeSide tutorial4CubeSide = new TutorialCubeSide(
				TutorialStep.TUTORIAL_4, TutorialStep.TUTORIAL_5,
				TutorialStep.TUTORIAL_4, TutorialStep.TUTORIAL_3,
				TutorialStep.TUTORIAL_4, TutorialStep.TUTORIAL_4);

		TutorialCubeSide tutorial5CubeSide = new TutorialCubeSide(
				TutorialStep.TUTORIAL_5, TutorialStep.TUTORIAL_5,
				TutorialStep.TUTORIAL_6, TutorialStep.TUTORIAL_4,
				TutorialStep.TUTORIAL_5, TutorialStep.TUTORIAL_5);

		TutorialCubeSide tutorial6CubeSide = new TutorialCubeSide(
				TutorialStep.TUTORIAL_6, TutorialStep.TUTORIAL_6,
				TutorialStep.TUTORIAL_6, TutorialStep.TUTORIAL_6,
				TutorialStep.TUTORIAL_5, TutorialStep.TUTORIAL_6);

		mCubeSides = new Vector<TutorialCubeSide>();
		mCubeSides.add(tutorial1CubeSide);
		mCubeSides.add(tutorial2CubeSide);
		mCubeSides.add(tutorial3CubeSide);
		mCubeSides.add(tutorial4CubeSide);
		mCubeSides.add(tutorial5CubeSide);
		mCubeSides.add(tutorial6CubeSide);
	}

	/**
	 * Returns the CubeSide object at the given index.
	 * 
	 * @param _index
	 *            The index of the element.
	 * @return A CubeSide object.
	 */
	public TutorialCubeSide getCubeSideAt(TutorialStep _index) {
		return mCubeSides.elementAt(_index.getValue());
	}

	/**
	 * Returns the current cube side of the the CubeSide object at the given
	 * index.
	 * 
	 * @param _index
	 *            The index of the element.
	 * @return The TutorialStep representation of the current cube side of the CubeSide
	 *         object.
	 */
	public TutorialStep getCurrentSideOfCubeSideAt(TutorialStep _index) {
		return mCubeSides.elementAt(_index.getValue()).getCurrentCubeSide();
	}

	/**
	 * Returns the upper cube side of the the CubeSide object at the given
	 * index.
	 * 
	 * @param _index
	 *            The index of the element.
	 * @return The TutorialStep representation of the upper cube side of the CubeSide
	 *         object.
	 */
	public TutorialStep getUpperSideOfCubeSideAt(TutorialStep _index) {
		return mCubeSides.elementAt(_index.getValue()).getUp();
	}

	/**
	 * Returns the right cube side of the the CubeSide object at the given
	 * index.
	 * 
	 * @param _index
	 *            The index of the element.
	 * @return The TutorialStep representation of the right cube side of the CubeSide
	 *         object.
	 */
	public TutorialStep getRightSideOfCubeSideAt(TutorialStep _index) {
		return mCubeSides.elementAt(_index.getValue()).getRight();
	}

	/**
	 * Returns the down cube side of the the CubeSide object at the given index.
	 * 
	 * @param _index
	 *            The index of the element.
	 * @return The TutorialStep representation of the current cube side of the CubeSide
	 *         object.
	 */
	public TutorialStep getDownSideOfCubeSideAt(TutorialStep _index) {
		return mCubeSides.elementAt(_index.getValue()).getDown();
	}

	/**
	 * Returns the left cube side of the the CubeSide object at the given index.
	 * 
	 * @param _index
	 *            The index of the element.
	 * @return The TutorialStep representation of the left cube side of the CubeSide
	 *         object.
	 */
	public TutorialStep getLeftSideOfCubeSideAt(TutorialStep _index) {
		return mCubeSides.elementAt(_index.getValue()).getLeft();
	}

	/**
	 * Returns the back cube side of the the CubeSide object at the given index.
	 * 
	 * @param _index
	 *            The index of the element.
	 * @return The TutorialStep representation of the back cube side of the CubeSide
	 *         object.
	 */
	public TutorialStep getBackSideOfCubeSideAt(TutorialStep _index) {
		return mCubeSides.elementAt(_index.getValue()).getBack();
	}
}
