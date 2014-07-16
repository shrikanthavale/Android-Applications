package at.fhooe.mcm.it.adid.controller.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;
import at.fhooe.mcm.it.adid.R;
import at.fhooe.mcm.it.adid.controller.cube.CubeChangedEvent;
import at.fhooe.mcm.it.adid.controller.cube.CubeListener;
import at.fhooe.mcm.it.adid.controller.cube.TutorialCubeChangedEvent;
import at.fhooe.mcm.it.adid.controller.cube.TutorialGLView;
import at.fhooe.mcm.it.adid.model.TutorialStep;

/**
 * Activity that shows a tutorial where the user learns the navigation in the
 * app. Gets called on the first start of the app and when the user clicks on
 * the help action in the actionbar of the MainActivity.
 * 
 * @author Mario
 * 
 */
/**
 * @author Mario Kraml
 * 
 */
public class TutorialActivity extends Activity implements CubeListener,
		OnTouchListener {
	/**
	 * static final int for the treshold of of a cube pressed event
	 */
	private static final int CUBE_PRESSED_TRESHOLD = 10;

	/**
	 * the current step in the tutorial
	 */
	private TutorialStep mCurrentStep;

	/**
	 * the x coordinate of a touch of the cube layout
	 */
	private int mFramePressedX;

	/**
	 * the y coordinate of a touch of the cube layout
	 */
	private int mFramePressedY;

	/**
	 * image view of the up arrow
	 */
	private ImageView mUpArrow;

	/**
	 * image view of the down arrow
	 */
	private ImageView mDownArrow;

	/**
	 * image view of the left arrow
	 */
	private ImageView mLeftArrow;

	/**
	 * image view of the right arrow
	 */
	private ImageView mRightArrow;

	/**
	 * flag if the user is dragging the cube
	 */
	private boolean mDragging;

	/**
	 * the view on which the cube gets rendered
	 */
	private TutorialGLView mCubeView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tutorial_layout);

		// set current step
		mCurrentStep = TutorialStep.TUTORIAL_1;
		initCubeView();
		initArrows();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mCubeView.pause();
		mCubeView.getSceneRenderer().removeCubeListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mCubeView.resume();
		mCubeView.getSceneRenderer().addCubeListener(this);

		cubeHasChanged(new TutorialCubeChangedEvent(this, mCurrentStep));
		mCubeView.getSceneRenderer().setCurrentCubeSide(mCurrentStep);
	}

	/**
	 * Init the cube view
	 */
	public void initCubeView() {
		mCubeView = (TutorialGLView) findViewById(R.id.tutorial_surfaceView);
		mCubeView.setZOrderMediaOverlay(true);
		mCubeView.getHolder().setFormat(PixelFormat.TRANSPARENT);
		mCubeView.setOnTouchListener(this);
	}

	/**
	 * Init the arrows
	 */
	public void initArrows() {
		mUpArrow = (ImageView) findViewById(R.id.tutorial_arrow_up_iv);
		mDownArrow = (ImageView) findViewById(R.id.tutorial_arrow_down_iv);
		mLeftArrow = (ImageView) findViewById(R.id.tutorial_arrow_left_iv);
		mRightArrow = (ImageView) findViewById(R.id.tutorial_arrow_right_iv);
		changeArrows();
	}

	@Override
	public void cubeHasChanged(TutorialCubeChangedEvent _event) {
		mCurrentStep = _event.getNewCubeSide();
		changeArrows();
	}

	/**
	 * Change the visibility of the arrows
	 */
	private void changeArrows() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				switch (mCurrentStep) {
				case TUTORIAL_1:
					mUpArrow.setVisibility(View.VISIBLE);
					mRightArrow.setVisibility(View.GONE);
					mDownArrow.setVisibility(View.GONE);
					mLeftArrow.setVisibility(View.GONE);
					break;
				case TUTORIAL_2:
				case TUTORIAL_3:
				case TUTORIAL_4:
					mUpArrow.setVisibility(View.VISIBLE);
					mUpArrow.bringToFront();
					mDownArrow.setVisibility(View.VISIBLE);
					mDownArrow.bringToFront();
					mRightArrow.setVisibility(View.GONE);
					mLeftArrow.setVisibility(View.GONE);
					break;
				case TUTORIAL_5:
					mRightArrow.setVisibility(View.VISIBLE);
					mDownArrow.setVisibility(View.VISIBLE);
					mUpArrow.setVisibility(View.GONE);
					mLeftArrow.setVisibility(View.GONE);
					break;
				case TUTORIAL_6:
					mLeftArrow.setVisibility(View.GONE);
					mUpArrow.setVisibility(View.GONE);
					mRightArrow.setVisibility(View.GONE);
					mDownArrow.setVisibility(View.GONE);
					break;
				}
			}
		});
	}

	@Override
	public void cubeHasChanged(CubeChangedEvent _event) {
		// do nothing here
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		if (event.getY() > mCubeView.getTop()
				&& event.getY() < (mCubeView.getTop() + mCubeView.getHeight())) {

			mCubeView.onTouchEvent(event, mCurrentStep);

			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				mDragging = true;
				mFramePressedX = (int) event.getX();
				mFramePressedY = (int) event.getY();

			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				mDragging = false;
				if (Math.abs(event.getX() - mFramePressedX) < CUBE_PRESSED_TRESHOLD
						&& Math.abs(event.getY() - mFramePressedY) < CUBE_PRESSED_TRESHOLD
						&& (mCurrentStep == TutorialStep.TUTORIAL_4 || mCurrentStep == TutorialStep.TUTORIAL_6)) {

					// cube and front cube object bounce a small bit when
					// they gets pressed on a quiz side
					mCubeView.getSceneRenderer().getCube().translate(0, 0, -1);
					mCubeView.getSceneRenderer().getFrontSideObject()
							.translate(0, 0, -1);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					mCubeView.getSceneRenderer().getCube().translate(0, 0, 1);
					mCubeView.getSceneRenderer().getFrontSideObject()
							.translate(0, 0, 1);

					cubePressed();

				}
			} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
				mDragging = true;
			}
		} else {
			if (mDragging) {

				mDragging = false;
				mCubeView.stopDrag();
			}

		}
		return true;

	}

	/**
	 * Gets called when the user pressed on the cube. Makes a Toast when the
	 * user sees the quiz tutorial info side and starts the MainActivity when
	 * the user is on the last side of the tutorial.
	 */
	private void cubePressed() {
		switch (mCurrentStep) {
		case TUTORIAL_4:
			Toast.makeText(this, R.string.quiz_would_start, Toast.LENGTH_LONG)
					.show();
			break;
		case TUTORIAL_6:
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			finish();
		default:
			break;
		}
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}
