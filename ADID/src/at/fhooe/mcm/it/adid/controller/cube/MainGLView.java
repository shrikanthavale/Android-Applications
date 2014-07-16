package at.fhooe.mcm.it.adid.controller.cube;

import android.content.Context;
import android.content.SharedPreferences;
import android.opengl.GLSurfaceView;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import at.fhooe.mcm.it.adid.R;
import at.fhooe.mcm.it.adid.model.Step;

/**
 * 
 * GLSurfaceView of the MainActivity on which the cube will be drawn
 * 
 * @author Mario Kraml
 * 
 */
/**
 * @author Mario
 * 
 */
public class MainGLView extends GLSurfaceView {

	/**
	 * Enum that indicates the direction in which the user has dragged the cube.
	 * 
	 * @author Mario
	 * 
	 */
	public enum DragDirection {
		LEFT, RIGHT, UP, DOWN;
	}

	/**
	 * the renderer of the scene
	 */
	private SceneRenderer renderer;

	/**
	 * the starttng x coordinate of the drag
	 */
	private float dragStartX;

	/**
	 * the starting y coordinate of the drag
	 */
	private float dragStartY;

	/**
	 * the application context
	 */
	private Context context;

	/**
	 * the current delta x of the drag
	 */
	private float deltaX;

	/**
	 * the current delta y of the drag
	 */
	private float deltaY;

	/**
	 * flag if move is already dragged
	 */
	private boolean alreadyDragging;

	/**
	 * flag if a touch event is the first touch event
	 */
	private boolean mFirstTime = true;

	/**
	 * the shared prefs
	 */
	private SharedPreferences mPrefs;

	/**
	 * Contructor
	 * 
	 * @param context
	 *            The application context
	 * @param attributeSet
	 *            The specified AttributeSet
	 */
	public MainGLView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		this.context = context;
		initRenderer();
		mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
	}

	/**
	 * Initializes the renderer for the GLSurfaceView
	 */
	private void initRenderer() {
		this.renderer = new SceneRenderer(this.context);
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
	 *            A Step representation of the current step in the design
	 *            process.
	 */
	public void continueDrag(float dragCurrentX, float dragCurrentY,
			Step currentStep) {
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
	 *            A Step representation of the current step in the design
	 *            process.
	 * @return True because the touch event has been consumed
	 */
	public boolean onTouchEvent(MotionEvent event, Step currentStep) {
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
	 * step of the design process.
	 * 
	 * @param currentStep
	 *            A Step representation of the current step in the design
	 *            process.
	 * @return True if the drag of the user results in an allowed move in the
	 *         current step of the design process, false otherwise.
	 */
	protected boolean isDragAllowed(Step currentStep) {
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
		case ANALYSIS_1:
			switch (drag) {
			case RIGHT:
			case LEFT:
			case UP:
				return false;
			default:
				return true;
			}
		case ANALYSIS_2:
		case ANALYSIS_3:
		case ANALYSIS_4:
		case ANALYSIS_5:
		case ANALYSIS_6:
		case ANALYSIS_7:
		case ANALYSIS_8:
		case ANALYSIS_9:
		case ANALYSIS_10:
			switch (drag) {
			case RIGHT:
			case LEFT:
				return false;
			default:
				return true;
			}
		case ANALYSIS_QUIZ:
			switch (drag) {
			case RIGHT:
			case DOWN:
				return false;
			case LEFT:
				if (mPrefs.getBoolean(
						context.getString(R.string.analysis_quiz_cleared),
						false)) {
					return true;
				} else {
					return false;
				}
			default:
				return true;
			}
		case DESIGN_1:
		case IMPLEMENTATION_1:
			switch (drag) {
			case LEFT:
			case UP:
				return false;
			default:
				return true;
			}
		case DESIGN_2:
		case DESIGN_3:
		case DESIGN_4:
		case DESIGN_5:
		case DESIGN_6:
		case DESIGN_7:
		case DESIGN_8:
		case DESIGN_9:
		case DESIGN_10:
		case IMPLEMENTATION_2:
		case IMPLEMENTATION_3:
			switch (drag) {
			case LEFT:
				return false;
			default:
				return true;
			}
		case DESIGN_QUIZ:
			switch (drag) {
			case DOWN:
				return false;
			case LEFT:
				if (mPrefs.getBoolean(
						context.getString(R.string.design_quiz_cleared), false)) {
					return true;
				} else {
					return false;
				}
			default:
				return true;
			}
		case IMPLEMENTATION_QUIZ:
			switch (drag) {
			case DOWN:
				return false;
			case LEFT:
				if (mPrefs
						.getBoolean(
								context.getString(R.string.implementation_quiz_cleared),
								false)) {
					return true;
				} else {
					return false;
				}
			default:
				return true;
			}
		case DEPLOYMENT_1:
			switch (drag) {
			case UP:
			case LEFT:
				return false;
			default:
				return true;
			}
		case DEPLOYMENT_2:
		case DEPLOYMENT_3:
			switch (drag) {
			case LEFT:
				return false;
			default:
				return true;
			}
		case DEPLOYMENT_QUIZ:
			switch (drag) {
			case DOWN:
			case LEFT:
				return false;
			default:
				return true;
			}
		}
		return false;
	}

	/**
	 * Getter for the renderer of this GLSurfaceView
	 * 
	 * @return The renderer of this GLSurfaceView
	 */
	public SceneRenderer getSceneRenderer() {
		return renderer;
	}

}
