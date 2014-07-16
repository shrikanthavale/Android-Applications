package at.fhooe.mcm.it.adid.controller.cube;

/**
 * Interface that has to be implemented by other classes when they want to be
 * informed of CubeChangedEvents.
 * 
 * @author Mario Kraml
 */
public interface CubeListener {
	/**
	 * Method that gets called when the cube has been changed. In the parameter
	 * event a Step representation of the new cube side on front of the cube is
	 * saved.
	 * 
	 * @param _event
	 *            A Step representation of the new cube side on front of the
	 *            cube.
	 */
	public void cubeHasChanged(CubeChangedEvent _event);

	/**
	 * Method that gets called when the cube has been changed in the tutorial.
	 * In the parameter event a TutorialStep representation of the new cube side
	 * on front of the cube is saved.
	 * 
	 * @param _event
	 *            A TutorialStep representation of the new cube side on front
	 *            of the cube.
	 */
	public void cubeHasChanged(TutorialCubeChangedEvent _event);
}
