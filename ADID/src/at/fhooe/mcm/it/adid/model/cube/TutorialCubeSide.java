package at.fhooe.mcm.it.adid.model.cube;

import at.fhooe.mcm.it.adid.model.TutorialStep;

/**
 * Class that represents a side of a cube in the TutorialActivity and holds
 * infos where the other sides of the cube a relative to the current front side.
 * 
 * @author Mario Kraml
 */
public class TutorialCubeSide {
	private TutorialStep mCurrentCubeSide;
	private TutorialStep mUp;
	private TutorialStep mRight;
	private TutorialStep mDown;
	private TutorialStep mLeft;
	private TutorialStep mBack;

	/**
	 * Constructor.
	 * 
	 * @param currentSide
	 *            The side of the cube that is on the front of the cube.
	 * @param _up
	 *            The side of the cube that is above the current front side.
	 * @param _right
	 *            The side of the cube that is on the right of the current front
	 *            side.
	 * @param _down
	 *            The side of the cube that is under the current front side.
	 * @param _left
	 *            The side of the cube that is on the left of the current front
	 *            side.
	 * @param _back
	 *            The side of the cube that is on the other side of the current
	 *            front side.
	 */
	public TutorialCubeSide(TutorialStep currentSide, TutorialStep _up,
			TutorialStep _right, TutorialStep _down, TutorialStep _left,
			TutorialStep _back) {
		setCurrentCubeSide(currentSide);
		setUp(_up);
		setRight(_right);
		setDown(_down);
		setLeft(_left);
		setBack(_back);
	}

	/**
	 * Default Constructor.
	 */
	public TutorialCubeSide() {

	}

	/**
	 * Returns the current cube side on front of the cube.
	 * 
	 * @return The current cube side on front of the cube.
	 */
	public TutorialStep getCurrentCubeSide() {
		return mCurrentCubeSide;
	}

	/**
	 * Sets the current cube side on the front side of the cube.
	 * 
	 * @param _currentCubeSide
	 */
	public void setCurrentCubeSide(TutorialStep _currentCubeSide) {
		this.mCurrentCubeSide = _currentCubeSide;
	}

	/**
	 * Returns the side of the cube that is above the current front side.
	 * 
	 * @return The side of the cube that is above the current front side.
	 */
	public TutorialStep getUp() {
		return mUp;
	}

	/**
	 * Sets the cube side above the front side of the cube.
	 * 
	 * @param _currentCubeSide
	 *            The cube side above the front side of the cube.
	 */
	public void setUp(TutorialStep _up) {
		this.mUp = _up;
	}

	/**
	 * Returns the side of the cube that is on the right side of the current
	 * front side.
	 * 
	 * @return The side of the cube that is on the right side of the current
	 *         front side.
	 */
	public TutorialStep getRight() {
		return mRight;
	}

	/**
	 * Sets the cube side on the right side of the front side of the cube.
	 * 
	 * @param _currentCubeSide
	 *            The cube side on the right side of the front side of the cube.
	 */
	public void setRight(TutorialStep _right) {
		this.mRight = _right;
	}

	/**
	 * Returns the side of the cube that is under the current front side.
	 * 
	 * @return The side of the cube that is under the current front side.
	 */
	public TutorialStep getDown() {
		return mDown;
	}

	/**
	 * Sets the cube side under the front side of the cube.
	 * 
	 * @param _currentCubeSide
	 *            The cube side under the front side of the cube.
	 */
	public void setDown(TutorialStep _down) {
		this.mDown = _down;
	}

	/**
	 * Returns the side of the cube that is on the left side of the current
	 * front side.
	 * 
	 * @return The side of the cube that is on the left side of the current
	 *         front side.
	 */
	public TutorialStep getLeft() {
		return mLeft;
	}

	/**
	 * Sets the cube side on the left side of the front side of the cube.
	 * 
	 * @param _currentCubeSide
	 *            The cube side on the left side of the front side of the cube.
	 */
	public void setLeft(TutorialStep _left) {
		this.mLeft = _left;
	}

	/**
	 * Returns the side of the cube that is on the back side of the current
	 * front side.
	 * 
	 * @return The side of the cube that is on the back side of the current
	 *         front side.
	 */
	public TutorialStep getBack() {
		return mBack;
	}

	/**
	 * Sets the cube side on the back of the front side of the cube.
	 * 
	 * @param _currentCubeSide
	 *            The cube side on the back of the front side of the cube.
	 */
	public void setBack(TutorialStep _back) {
		this.mBack = _back;
	}
}
