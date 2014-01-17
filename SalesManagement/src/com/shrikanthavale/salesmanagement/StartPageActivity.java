package com.shrikanthavale.salesmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import com.shrikanthavale.salesmanagement.administration.AdministrationActivity;

/**
 * Start page activity containing the main page of the application, containing
 * the buttons to start, administration, about, exit
 * 
 * @author Shrikant Havale
 * 
 */
public class StartPageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_page);

		// start page button
		findViewById(R.id.startPageStartButton).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {

						Intent intent = new Intent(getApplicationContext(),
								PlayGridActivity.class);

						// call the activity
						startActivity(intent);

					}
				});

		// admin section button
		findViewById(R.id.startPageAdminButton).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {

						Intent intent = new Intent(getApplicationContext(),
								AdministrationActivity.class);

						// call the activity
						startActivity(intent);

					}
				});

		// exit button
		findViewById(R.id.startPageExitButton).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent startMain = new Intent(Intent.ACTION_MAIN);
						startMain.addCategory(Intent.CATEGORY_HOME);
						startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(startMain);
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start_page, menu);
		return true;
	}

}
