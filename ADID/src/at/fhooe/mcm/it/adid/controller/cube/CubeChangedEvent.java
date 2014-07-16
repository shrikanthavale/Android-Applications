
package at.fhooe.mcm.it.adid.controller.cube;

import java.util.EventObject;

import at.fhooe.mcm.it.adid.model.Step;

/**
 * Class that represents an Event that gets fired when the cube has been changed
 * and therefore a new cube side is on the front of the cube.
 * 
 * @author Mario Kraml
 */
public class CubeChangedEvent extends EventObject {

    /**
     * 
     */
    private static final long serialVersionUID = -5680948691588098518L;

    /**
     * The step representation of the new cube side on the front of the cube.
     */
    private Step mNewCubeSide;

    /**
     * Constructor.
     * 
     * @param source The Class that fired the event.
     * @param _newCubeSide The Step representation of the new cube side on the
     *            front of the cube.
     */
    public CubeChangedEvent(Object source, Step _newCubeSide) {
        super(source);
        mNewCubeSide = _newCubeSide;
    }

	/**
     * Getter for the Step representation of the new cube side on the front of
     * the cube.
     * 
     * @return Step representation of the new cube side on the front of the cube.
     */
    public Step getNewCubeSide() {
        return mNewCubeSide;
    }
}
