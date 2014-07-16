package at.fhooe.mcm.it.adid.controller.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import at.fhooe.mcm.it.adid.R;
import at.fhooe.mcm.it.adid.controller.activities.analysis.AnalysisActivity;
import at.fhooe.mcm.it.adid.controller.activities.deployment.DeploymentActivity;
import at.fhooe.mcm.it.adid.controller.activities.design.DesignActivity;
import at.fhooe.mcm.it.adid.controller.activities.implementation.ImplementationActivity;
import at.fhooe.mcm.it.adid.controller.cube.CubeChangedEvent;
import at.fhooe.mcm.it.adid.controller.cube.CubeListener;
import at.fhooe.mcm.it.adid.controller.cube.MainGLView;
import at.fhooe.mcm.it.adid.controller.cube.TutorialCubeChangedEvent;
import at.fhooe.mcm.it.adid.model.DatabaseManager;
import at.fhooe.mcm.it.adid.model.Step;
import at.fhooe.mcm.it.adid.model.utility.StoreAnalysisStateApplication;
import at.fhooe.mcm.it.adid.model.utility.StoreDeploymentStateApplication;
import at.fhooe.mcm.it.adid.model.utility.StoreDesignStateApplication;
import at.fhooe.mcm.it.adid.model.utility.StoreImplementationStateApplication;

/**
 * MainActivity that shows the overview. User can walk trough the different
 * phases by moving the cube.
 * 
 * @author Mario
 * 
 */
/**
 * @author Mario
 * 
 */
public class MainActivity extends Activity implements OnTouchListener,
		CubeListener, OnClickListener {

	/**
	 * static final int for the treshold of of a cube pressed event
	 */
	private static final int CUBE_PRESSED_TRESHOLD = 10;

	// static final int for the different layouts of the footer
	/**
	 * static final int for the make note footer layout
	 */
	private static final int MAKE_NOTE_FOOTER = 0;
	/**
	 * static final int for the show note footer layout
	 */
	private static final int SHOW_NOTE_FOOTER = 1;
	/**
	 * static final int for the start quiz footer layout
	 */
	private static final int START_QUIZ_FOOTER = 2;

	/**
	 * viewflipper for the footers
	 */
	private ViewFlipper mFooterViewFlipper;

	/**
	 * the current step in the design process
	 */
	private Step mCurrentStep;

	/**
	 * the x coordinate of a touch of the cube layout
	 */
	private int mFramePressedX;

	/**
	 * the y coordinate of a touch of the cube layout
	 */
	private int mFramePressedY;

	/**
	 * the shared prefs
	 */
	private SharedPreferences mPrefs;

	/**
	 * the view on which the cube gets rendered
	 */
	private MainGLView mCubeView;

	/**
	 * flag if the user is dragging the cube
	 */
	private boolean mDragging;

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
	 * text view for the indicator of the analysis phase
	 */
	private TextView mAnalysisTv;

	/**
	 * text view for the indicator of the design phase
	 */
	private TextView mDesignTv;

	/**
	 * text view for the indicator of the implementation phase
	 */
	private TextView mImplementationTv;

	/**
	 * text view for the indicator of the deployment phase
	 */
	private TextView mDeploymentTv;

	/**
	 * the make note button
	 */
	private Button mMakeNoteButton;

	/**
	 * the start quiz button
	 */
	private Button mStartQuizButton;

	/**
	 * the show note textview
	 */
	private TextView mShowNoteTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mFooterViewFlipper = (ViewFlipper) findViewById(R.id.main_footer_viewflipper);

		// get SharedPreferences
		mPrefs = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		// set current step
		mCurrentStep = Step.values()[mPrefs.getInt("currentStep", 0)];

		initCubeView();
		initArrowsAndIndicators();

		mMakeNoteButton = (Button) findViewById(R.id.make_note_button);
		mMakeNoteButton.setOnClickListener(this);
		mStartQuizButton = (Button) findViewById(R.id.start_quiz_button);
		mStartQuizButton.setOnClickListener(this);
		mShowNoteTv = (TextView) findViewById(R.id.show_note_tv);
		mShowNoteTv.setOnClickListener(this);

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
		// get current cube side from prefs
		if (mCubeView.getSceneRenderer().getCube() != null) {
			mCubeView.getSceneRenderer().setCurrentCubeSide(mCurrentStep);
			cubeHasChanged(new CubeChangedEvent(this, mCubeView
					.getSceneRenderer().getCurrentSideOfCubeSide()));
		} else {
			cubeHasChanged(new CubeChangedEvent(this, mCurrentStep));
			mCubeView.getSceneRenderer().setCurrentCubeSide(mCurrentStep);
		}
	}

	/**
	 * Init the cube view
	 */
	public void initCubeView() {
		mCubeView = (MainGLView) findViewById(R.id.surfaceView);
		mCubeView.setZOrderOnTop(true);
		mCubeView.setZOrderMediaOverlay(true);
		mCubeView.getHolder().setFormat(PixelFormat.TRANSPARENT);
		mCubeView.setOnTouchListener(this);
	}

	/**
	 * Init the arros and the indicators
	 */
	public void initArrowsAndIndicators() {
		mUpArrow = (ImageView) findViewById(R.id.mainmenu_arrow_up_iv);
		mDownArrow = (ImageView) findViewById(R.id.mainmenu_arrow_down_iv);
		mLeftArrow = (ImageView) findViewById(R.id.mainmenu_arrow_left_iv);
		mRightArrow = (ImageView) findViewById(R.id.mainmenu_arrow_right_iv);

		mAnalysisTv = (TextView) findViewById(R.id.mainmenu_indicator_analysis);
		mDesignTv = (TextView) findViewById(R.id.mainmenu_indicator_design);
		mImplementationTv = (TextView) findViewById(R.id.mainmenu_indicator_implementation);
		mDeploymentTv = (TextView) findViewById(R.id.mainmenu_indicator_deployment);

		changeArrowsAndIndicators();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_help:
			Intent intent = new Intent(this, TutorialActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.action_reset:
			resetApplicationProgress();
			break;
		}
		return super.onOptionsItemSelected(item);
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
						&& (mCurrentStep == Step.ANALYSIS_QUIZ
								|| mCurrentStep == Step.DESIGN_QUIZ
								|| mCurrentStep == Step.IMPLEMENTATION_QUIZ || mCurrentStep == Step.DEPLOYMENT_QUIZ)) {

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
		// }
		return true;
	}

	/**
	 * Start a quiz activity when the cube has been pressed on a cobe quiz side
	 */
	private void cubePressed() {
		Intent intent;
		switch (mCurrentStep) {
		case ANALYSIS_QUIZ:
			intent = new Intent(this, AnalysisActivity.class);
			startActivity(intent);
			break;
		case DESIGN_QUIZ:
			intent = new Intent(this, DesignActivity.class);
			startActivity(intent);
			break;
		case IMPLEMENTATION_QUIZ:
			intent = new Intent(this, ImplementationActivity.class);
			startActivity(intent);
			break;
		case DEPLOYMENT_QUIZ:
			intent = new Intent(this, DeploymentActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	@Override
	public void cubeHasChanged(CubeChangedEvent _event) {
		mCurrentStep = _event.getNewCubeSide();
		Editor editor = mPrefs.edit();
		editor.putInt("currentStep", mCurrentStep.getValue());
		editor.commit();

		changeArrowsAndIndicators();

		setFooter();
	}

	@Override
	public void cubeHasChanged(TutorialCubeChangedEvent _event) {
		// do nothing here
	}

	/**
	 * Change the visibility of the arrow and the text colors of the indicators
	 */
	private void changeArrowsAndIndicators() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				switch (mCurrentStep) {
				case ANALYSIS_1:
					mUpArrow.setVisibility(View.VISIBLE);
					mRightArrow.setVisibility(View.GONE);
					mDownArrow.setVisibility(View.GONE);
					mLeftArrow.setVisibility(View.GONE);

					mAnalysisTv.setTextColor(getResources().getColor(
							R.color.black));
					mDesignTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mImplementationTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mDeploymentTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					break;
				case ANALYSIS_2:
				case ANALYSIS_3:
				case ANALYSIS_4:
				case ANALYSIS_5:
				case ANALYSIS_6:
				case ANALYSIS_7:
				case ANALYSIS_8:
				case ANALYSIS_9:
				case ANALYSIS_10:
					mUpArrow.setVisibility(View.VISIBLE);
					mRightArrow.setVisibility(View.GONE);
					mDownArrow.setVisibility(View.VISIBLE);
					mLeftArrow.setVisibility(View.GONE);

					mAnalysisTv.setTextColor(getResources().getColor(
							R.color.black));
					mDesignTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mImplementationTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mDeploymentTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					break;
				case ANALYSIS_QUIZ:
					mUpArrow.setVisibility(View.INVISIBLE);
					if (mPrefs.getBoolean(
							getString(R.string.analysis_quiz_cleared), false)) {
						mRightArrow.setVisibility(View.VISIBLE);
					} else {
						mRightArrow.setVisibility(View.GONE);
					}
					mDownArrow.setVisibility(View.VISIBLE);
					mLeftArrow.setVisibility(View.GONE);

					mAnalysisTv.setTextColor(getResources().getColor(
							R.color.black));
					mDesignTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mImplementationTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mDeploymentTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					break;
				case DESIGN_1:
					mUpArrow.setVisibility(View.VISIBLE);
					mRightArrow.setVisibility(View.GONE);
					mDownArrow.setVisibility(View.GONE);
					mLeftArrow.setVisibility(View.VISIBLE);

					mAnalysisTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mDesignTv.setTextColor(getResources().getColor(
							R.color.black));
					mImplementationTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mDeploymentTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					break;
				case IMPLEMENTATION_1:
					mUpArrow.setVisibility(View.VISIBLE);
					mRightArrow.setVisibility(View.GONE);
					mDownArrow.setVisibility(View.GONE);
					mLeftArrow.setVisibility(View.VISIBLE);

					mAnalysisTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mDesignTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mImplementationTv.setTextColor(getResources().getColor(
							R.color.black));
					mDeploymentTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					break;
				case DESIGN_2:
				case DESIGN_3:
				case DESIGN_4:
				case DESIGN_5:
				case DESIGN_6:
				case DESIGN_7:
				case DESIGN_8:
				case DESIGN_9:
				case DESIGN_10:
					mUpArrow.setVisibility(View.VISIBLE);
					mRightArrow.setVisibility(View.GONE);
					mDownArrow.setVisibility(View.VISIBLE);
					mLeftArrow.setVisibility(View.VISIBLE);

					mAnalysisTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mDesignTv.setTextColor(getResources().getColor(
							R.color.black));
					mImplementationTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mDeploymentTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					break;
				case IMPLEMENTATION_2:
				case IMPLEMENTATION_3:
					mUpArrow.setVisibility(View.VISIBLE);
					mRightArrow.setVisibility(View.GONE);
					mDownArrow.setVisibility(View.VISIBLE);
					mLeftArrow.setVisibility(View.VISIBLE);

					mAnalysisTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mDesignTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mImplementationTv.setTextColor(getResources().getColor(
							R.color.black));
					mDeploymentTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					break;
				case DESIGN_QUIZ:
					mUpArrow.setVisibility(View.GONE);
					if (mPrefs.getBoolean(
							getString(R.string.design_quiz_cleared), false)) {
						mRightArrow.setVisibility(View.VISIBLE);
					} else {
						mRightArrow.setVisibility(View.GONE);
					}

					mDownArrow.setVisibility(View.VISIBLE);
					mLeftArrow.setVisibility(View.VISIBLE);

					mAnalysisTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mDesignTv.setTextColor(getResources().getColor(
							R.color.black));
					mImplementationTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mDeploymentTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					break;
				case IMPLEMENTATION_QUIZ:
					mUpArrow.setVisibility(View.GONE);
					if (mPrefs.getBoolean(
							getString(R.string.implementation_quiz_cleared), false)) {
						mRightArrow.setVisibility(View.VISIBLE);
					} else {
						mRightArrow.setVisibility(View.GONE);
					}

					mDownArrow.setVisibility(View.VISIBLE);
					mLeftArrow.setVisibility(View.VISIBLE);

					mAnalysisTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mDesignTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mImplementationTv.setTextColor(getResources().getColor(
							R.color.black));
					mDeploymentTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					break;
				case DEPLOYMENT_1:
					mUpArrow.setVisibility(View.VISIBLE);
					mRightArrow.setVisibility(View.GONE);
					mDownArrow.setVisibility(View.GONE);
					mLeftArrow.setVisibility(View.VISIBLE);

					mAnalysisTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mDesignTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mImplementationTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mDeploymentTv.setTextColor(getResources().getColor(
							R.color.black));
					break;
				case DEPLOYMENT_2:
				case DEPLOYMENT_3:
					mUpArrow.setVisibility(View.VISIBLE);
					mRightArrow.setVisibility(View.GONE);
					mDownArrow.setVisibility(View.VISIBLE);
					mLeftArrow.setVisibility(View.VISIBLE);

					mAnalysisTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mDesignTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mImplementationTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mDeploymentTv.setTextColor(getResources().getColor(
							R.color.black));
					break;
				case DEPLOYMENT_QUIZ:
					mUpArrow.setVisibility(View.GONE);
					mRightArrow.setVisibility(View.GONE);
					mDownArrow.setVisibility(View.VISIBLE);
					mLeftArrow.setVisibility(View.VISIBLE);

					mAnalysisTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mDesignTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mImplementationTv.setTextColor(getResources().getColor(
							R.color.stainless_steel));
					mDeploymentTv.setTextColor(getResources().getColor(
							R.color.black));
					break;
				}
			}
		});

	}

	/**
	 * Set the footer. If the cube is on a quiz side then a start quiz footer is
	 * shown. On the other cube sides a make a note footer is shown when no note
	 * has yet been made for this step, else the note is shown.
	 */
	private void setFooter() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (mCurrentStep == Step.ANALYSIS_QUIZ
						|| mCurrentStep == Step.DESIGN_QUIZ
						|| mCurrentStep == Step.IMPLEMENTATION_QUIZ
						|| mCurrentStep == Step.DEPLOYMENT_QUIZ) {

					mFooterViewFlipper.setDisplayedChild(START_QUIZ_FOOTER);
				} else {
					String noteText = DatabaseManager.getInstance(
							getApplicationContext()).getNote(
							mCurrentStep.getValue());
					if (noteText != null && noteText.length() > 0) {
						mFooterViewFlipper.setDisplayedChild(SHOW_NOTE_FOOTER);
						mShowNoteTv.setText(noteText);
					} else {
						mFooterViewFlipper.setDisplayedChild(MAKE_NOTE_FOOTER);
					}
				}
			}

		});
	}

	@Override
	public void onClick(View v) {
		String noteText = DatabaseManager.getInstance(getApplicationContext())
				.getNote(mCurrentStep.getValue());
		switch (v.getId()) {
		case R.id.make_note_button:
		case R.id.show_note_tv:

			// start a dialog where a note can be created or changed
			final Dialog d = new Dialog(MainActivity.this);
			d.getWindow().setBackgroundDrawable(
					new ColorDrawable(android.graphics.Color.TRANSPARENT));
			try {
				d.requestWindowFeature(Window.FEATURE_NO_TITLE);
			} catch (Exception e) {
				e.printStackTrace();
			}
			d.setContentView(R.layout.make_note_dialog);
			final EditText noteEt = ((EditText) d
					.findViewById(R.id.make_note_dialog_et));
			noteEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if (hasFocus) {
						d.getWindow()
								.setSoftInputMode(
										WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
					}
				}
			});
			if (noteText != null && noteText.length() > 0) {
				noteEt.setText(noteText);
			}
			Button saveButton = (Button) d
					.findViewById(R.id.make_note_dialog_save_btn);
			saveButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					DatabaseManager.getInstance(getApplicationContext())
							.insertNote(mCurrentStep.getValue(),
									noteEt.getText().toString());
					setFooter();
					d.dismiss();
				}
			});
			Button cancelButton = (Button) d
					.findViewById(R.id.make_note_dialog_cancel_btn);
			cancelButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					d.dismiss();
				}
			});
			d.show();
			break;
		case R.id.start_quiz_button:
			// start the quiz
			cubePressed();
			break;
		}
	}

	/**
	 * This method shows the alert dialog before resetting the application
	 * progress. If user confirms the reset all the static flag used for
	 * progress in all stages are resetted to false. And all the questions are
	 * unanswered automatically. His original saved notes are deleted as well.
	 */
	private void resetApplicationProgress() {

		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

		// Setting Dialog Title
		alertDialog.setTitle(getString(R.string.resetapplicationprogresstitle));

		// Setting Dialog Message
		alertDialog
				.setMessage(getString(R.string.resetapplicationprogressmessage));

		// Setting Positive "Yes" Btn
		alertDialog.setPositiveButton(getString(R.string.yesbutton),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						resetSavedFlagsAndNotes();
						mCubeView.getSceneRenderer()
								.setCubeBackToStartPosition();
						Toast.makeText(
								MainActivity.this,
								getString(R.string.progressettingclearedsuccessfullymessage),
								Toast.LENGTH_SHORT).show();
					}
				});

		// Setting Negative "No" Btn
		alertDialog.setNegativeButton(getString(R.string.nobutton),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});

		// Showing Alert Dialog
		alertDialog.show();
	}

	/**
	 * Resets the answered questions and the notes
	 */
	private void resetSavedFlagsAndNotes() {

		Editor editor = mPrefs.edit();
		editor.putBoolean(getString(R.string.analysis_quiz_cleared), false);
		editor.putBoolean(getString(R.string.design_quiz_cleared), false);
		editor.putBoolean(getString(R.string.implementation_quiz_cleared),
				false);
		editor.putBoolean(getString(R.string.deployment_quiz_cleard), false);
		editor.putBoolean(getString(R.string.analysis_quiz_1_answered), false);
		editor.putBoolean(getString(R.string.analysis_quiz_2_answered), false);
		editor.putBoolean(getString(R.string.analysis_quiz_3_answered), false);

		editor.putBoolean(getString(R.string.design_quiz_1_answered), false);
		editor.putBoolean(getString(R.string.design_quiz_2_answered), false);
		editor.putBoolean(getString(R.string.design_quiz_3_answered), false);

		editor.putBoolean(getString(R.string.implementation_quiz_1_answered),
				false);
		editor.putBoolean(getString(R.string.implementation_quiz_2_answered),
				false);
		editor.putBoolean(getString(R.string.implementation_quiz_3_answered),
				false);

		editor.putBoolean(getString(R.string.deployment_quiz_1_answered), false);
		editor.putBoolean(getString(R.string.deployment_quiz_2_answered), false);
		editor.putBoolean(getString(R.string.deployment_quiz_3_answered), false);
		editor.commit();

		// analysis flags
		StoreAnalysisStateApplication.question1Answered = false;
		StoreAnalysisStateApplication.question2Answered = false;
		StoreAnalysisStateApplication.question3Answered = false;
		StoreAnalysisStateApplication.stageCleared = false;

		// design flags
		StoreDesignStateApplication.question1Answered = false;
		StoreDesignStateApplication.question2Answered = false;
		StoreDesignStateApplication.question3Answered = false;
		StoreDesignStateApplication.stageCleared = false;

		// implementation flags
		StoreImplementationStateApplication.question1Answered = false;
		StoreImplementationStateApplication.question2Answered = false;
		StoreImplementationStateApplication.question3Answered = false;
		StoreImplementationStateApplication.stageCleared = false;

		// implementation flags
		StoreDeploymentStateApplication.question1Answered = false;
		StoreDeploymentStateApplication.question2Answered = false;
		StoreDeploymentStateApplication.question3Answered = false;
		StoreDeploymentStateApplication.stageCleared = false;

		DatabaseManager.getInstance(getApplicationContext()).deleteAllNotes();

	}

}
