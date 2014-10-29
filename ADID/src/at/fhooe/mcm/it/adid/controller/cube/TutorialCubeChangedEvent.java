package at.fhooe.mcm.it.adid.controller.cube;

import java.util.EventObject;

import at.fhooe.mcm.it.adid.model.TutorialStep;

/**
 * Class that represents an Event that gets fired when the cube has been changed
 * in the TutorialActivity and therefore a new cube side is on the front of the
 * cube.
 * 
 * @author Mario Kraml
 */
public class TutorialCubeChangedEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5000076359343913168L;
	
	/**
	 * The tutorial step representation of the new cube side on the front of the
	 * cube.
	 */
	private TutorialStep mNewCubeSide;

	/**
	 * Constructor.
	 * 
	 * @param source
	 *            The Class that fired the event.
	 * @param _newCubeSide
	 *            The TutorialStep representation of the new cube side on the front of
	 *            the cube.
	 */
	public TutorialCubeChangedEvent(Object source, TutorialStep _newCubeSide) {
		super(source);
		mNewCubeSide = _newCubeSide;
	}

	/**
	 * Getter for the TutorialStep representation of the new cube side on the
	 * front of the cube.
	 * 
	 * @return TutorialStep representation of the new cube side on the front of
	 *         the cube.
	 */
	public TutorialStep getNewCubeSide() {
		return mNewCubeSide;
	}
}
