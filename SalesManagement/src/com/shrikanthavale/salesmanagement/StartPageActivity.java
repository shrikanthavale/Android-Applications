package com.shrikanthavale.salesmanagement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

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

		
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if(connectivityManager != null){
			
			NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
			if(networkInfo == null || !networkInfo.isConnected()){
				Toast.makeText(this, R.string.internetaccesserrormessage, Toast.LENGTH_LONG).show();
			}
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start_page, menu);
		return true;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if(connectivityManager != null){
			
			NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
			if(networkInfo == null || !networkInfo.isConnected()){
				Toast.makeText(this, R.string.internetaccesserrormessage, Toast.LENGTH_LONG).show();
			}
			
		}
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
	}
	
}

