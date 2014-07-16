package at.fhooe.mcm.it.adid.controller.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import at.fhooe.mcm.it.adid.R;

/**
 * Launcher Activity that simply checks if the activity has been started for the
 * first time. If so, then the tutorial activity gets started. otherwise the
 * splash activity gets started.
 * 
 * @author Mario
 */
public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		// start tutorial necause user started the app for the first time
		if (prefs.getBoolean("firstStart", true)) {
			Editor editor = prefs.edit();
			editor.putBoolean("firstStart", false);
			editor.commit();
			Intent tutorialIntent = new Intent(this, TutorialActivity.class);
			startActivity(tutorialIntent);
		}
		// start the splash activity because user has already started the app
		// once
		else {
			Intent splashIntent = new Intent(this, SplashActivity.class);
			startActivity(splashIntent);
		}
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

}
