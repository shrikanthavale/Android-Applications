package at.fhooe.mcm.it.adid.controller.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.preference.PreferenceManager;
import at.fhooe.mcm.it.adid.R;
import at.fhooe.mcm.it.adid.controller.cube.CubeChangedEvent;
import at.fhooe.mcm.it.adid.controller.cube.CubeListener;
import at.fhooe.mcm.it.adid.controller.cube.SplashGLView;
import at.fhooe.mcm.it.adid.controller.cube.TutorialCubeChangedEvent;
import at.fhooe.mcm.it.adid.model.utility.StoreAnalysisStateApplication;
import at.fhooe.mcm.it.adid.model.utility.StoreDeploymentStateApplication;
import at.fhooe.mcm.it.adid.model.utility.StoreDesignStateApplication;
import at.fhooe.mcm.it.adid.model.utility.StoreImplementationStateApplication;

/**
 * Activity that shows the splash screen with a welcome message.
 * 
 * @author Mario
 * 
 */
public class SplashActivity extends Activity implements CubeListener {

	/**
	 * the view on which the cube gets rendered
	 */
	private SplashGLView mCubeView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_layout);
		initCubeView();
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
		setQuizAnsweredFlags();
	}

	/**
	 * Sets the flags that indicates if a question has already been answered
	 * correctly.
	 */
	private void setQuizAnsweredFlags() {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());

		StoreAnalysisStateApplication.question1Answered = prefs.getBoolean(
				getString(R.string.analysis_quiz_1_answered), false);
		StoreAnalysisStateApplication.question2Answered = prefs.getBoolean(
				getString(R.string.analysis_quiz_2_answered), false);
		StoreAnalysisStateApplication.question3Answered = prefs.getBoolean(
				getString(R.string.analysis_quiz_3_answered), false);
		StoreAnalysisStateApplication.stageCleared = prefs.getBoolean(
				getString(R.string.analysis_quiz_cleared), false);

		StoreDesignStateApplication.question1Answered = prefs.getBoolean(
				getString(R.string.design_quiz_1_answered), false);
		StoreDesignStateApplication.question2Answered = prefs.getBoolean(
				getString(R.string.design_quiz_2_answered), false);
		StoreDesignStateApplication.question3Answered = prefs.getBoolean(
				getString(R.string.design_quiz_3_answered), false);
		StoreDesignStateApplication.stageCleared = prefs.getBoolean(
				getString(R.string.design_quiz_cleared), false);

		StoreImplementationStateApplication.question1Answered = prefs
				.getBoolean(getString(R.string.implementation_quiz_1_answered),
						false);
		StoreImplementationStateApplication.question2Answered = prefs
				.getBoolean(getString(R.string.implementation_quiz_2_answered),
						false);
		StoreImplementationStateApplication.question3Answered = prefs
				.getBoolean(getString(R.string.implementation_quiz_3_answered),
						false);
		StoreImplementationStateApplication.stageCleared = prefs.getBoolean(
				getString(R.string.implementation_quiz_cleared), false);

		StoreDeploymentStateApplication.question1Answered = prefs.getBoolean(
				getString(R.string.deployment_quiz_1_answered), false);
		StoreDeploymentStateApplication.question2Answered = prefs.getBoolean(
				getString(R.string.deployment_quiz_2_answered), false);
		StoreDeploymentStateApplication.question3Answered = prefs.getBoolean(
				getString(R.string.deployment_quiz_3_answered), false);
		StoreDeploymentStateApplication.stageCleared = prefs.getBoolean(
				getString(R.string.deployment_quiz_cleard), false);
	}

	/**
	 * Initialize the cube view
	 */
	public void initCubeView() {
		mCubeView = (SplashGLView) findViewById(R.id.splash_surfaceView);
		mCubeView.setZOrderOnTop(true);
		mCubeView.setZOrderMediaOverlay(true);
		mCubeView.getHolder().setFormat(PixelFormat.TRANSPARENT);
	}

	@Override
	public void cubeHasChanged(CubeChangedEvent _event) {
		Intent startIntent = new Intent(this, MainActivity.class);
		startActivity(startIntent);
		finish();
	}

	@Override
	public void cubeHasChanged(TutorialCubeChangedEvent _event) {
		// do noting here

	}
}
