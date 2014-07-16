package at.fhooe.mcm.it.adid.controller.cube;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import at.fhooe.mcm.it.adid.controller.cube.MainGLView.DragDirection;
import at.fhooe.mcm.it.adid.model.TutorialStep;

/**
 * 
 * GLSurfaceView of the TutorialActivity on which the cube will be drawn
 * 
 * @author Mario Kraml
 * 
 */
public class TutorialGLView extends GLSurfaceView {

	private TutorialSceneRenderer renderer;
	private float dragStartX;
	private float dragStartY;
	private Context context;
	private float deltaX;
	private float deltaY;
	private boolean alreadyDragging;
	private boolean mFirstTime = true;

	/**
	 * Contructor
	 * 
	 * @param context
	 *            The application context
	 * @param attributeSet
	 *            The specified AttributeSet
	 */
	public TutorialGLView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		this.context = context;
		initRenderer();
	}

	/**
	 * Initializes the renderer for the GLSurfaceView
	 */
	private void initRenderer() {
		this.renderer = new TutorialSceneRenderer(this.context);
		this.setRenderer(renderer);
	}

	/**
	 * Pause the GLSurfaceView. Called when the activity gets paused.
	 */
	public void pause() {
		this.onPause();
	}

	/**
	 * Resume the GLSurfaceView. Called when the activity gets resumed.
	 */
	public void resume() {
		this.onResume();
	}

	/**
	 * Dragging of the cube has been started
	 * 
	 * @param dragCurrentX
	 *            The x coordinate of the touch
	 * @param dragCurrentY
	 *            The y coordinate of the touch
	 */
	public void startDrag(float dragCurrentX, float dragCurrentY) {
		this.dragStartX = dragCurrentX;
		this.dragStartY = dragCurrentY;
		alreadyDragging = true;
	}

	/**
	 * Dragging of the cube continues
	 * 
	 * @param dragCurrentX
	 *            The x coordinate of the touch
	 * @param dragCurrentY
	 *            The y coordinate of the touch
	 * @param currentStep
	 *            A TutorialStep representation of the current step in the
	 *            design process.
	 */
	public void continueDrag(float dragCurrentX, float dragCurrentY,
			TutorialStep currentStep) {
		deltaX = dragCurrentX - this.dragStartX;
		deltaY = dragCurrentY - this.dragStartY;
		if (isDragAllowed(currentStep)) {

			// ignore first move touch
			if (!mFirstTime) {
				this.renderer.continueDragCube(deltaX, deltaY);
			}
			this.dragStartX = dragCurrentX;
			this.dragStartY = dragCurrentY;
			mFirstTime = false;
		} else {
			stopDrag();
		}

	}

	/**
	 * Dragging has been stopped
	 * 
	 */
	public void stopDrag() {
		this.renderer.stopDragCube();
		alreadyDragging = false;
		mFirstTime = true;
	}

	/**
	 * Gets called when a touch of the user has been registered by the
	 * underlying activity.
	 * 
	 * @param event
	 *            The MotionEvent of the touch
	 * @param currentStep
	 *            A TutorialStep representation of the current step in the
	 *            design process.
	 * @return True because the touch event has been consumed
	 */
	public boolean onTouchEvent(MotionEvent event, TutorialStep currentStep) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			startDrag(event.getX(), event.getY());
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			if (alreadyDragging) {
				stopDrag();
			}

		} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			if (alreadyDragging) {
				continueDrag(event.getX(), event.getY(), currentStep);
			} else {
				startDrag(event.getX(), event.getY());
			}
		}

		try {
			Thread.sleep(15);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * Checks if the drag of the user results in an allowed move in the current
	 * step of the tutorial.
	 * 
	 * @param currentStep
	 *            A TutorialStep representation of the current step in the
	 *            tutorial.
	 * @return True if the drag of the user results in an allowed move in the
	 *         current step of the tutorial, false otherwise.
	 */
	private boolean isDragAllowed(TutorialStep currentStep) {
		DragDirection drag;
		// check in what drag direction the touch has been performed
		if (Math.abs(deltaX) >= Math.abs(deltaY)) {
			if (deltaX >= 0) {
				drag = DragDirection.RIGHT;
			} else {
				drag = DragDirection.LEFT;
			}
		} else {
			if (deltaY >= 0) {
				drag = DragDirection.DOWN;
			} else {
				drag = DragDirection.UP;
			}
		}
		// check if the drag is allowed in the current step
		switch (currentStep) {
		case TUTORIAL_1:
			switch (drag) {
			case RIGHT:
			case LEFT:
			case UP:
				return false;
			default:
				return true;
			}
		case TUTORIAL_2:
		case TUTORIAL_3:
		case TUTORIAL_4:
			switch (drag) {
			case RIGHT:
			case LEFT:
				return false;
			default:
				return true;
			}
		case TUTORIAL_5:
			switch (drag) {
			case RIGHT:
			case DOWN:
				return false;
			default:
				return true;
			}
		case TUTORIAL_6:
			return false;
			
		}
		return false;
	}

	/**
	 * Getter for the renderer of this GLSurfaceView
	 * 
	 * @return The renderer of this GLSurfaceView
	 */
	public TutorialSceneRenderer getSceneRenderer() {
		return renderer;
	}

}
