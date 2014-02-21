package com.shrikanthavale.cricketquiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.shrikanthavale.cricketquiz.entities.SalesManagementQuestion;
import com.shrikanthavale.cricketquiz.webserviceutility.SalesManagementSaveActivityState;

/**
 * @author Shrikant Havale
 * 
 *         This activity shows the question based on the node that was clicked
 *         in Play Grid Activity
 * 
 */
public class CaseStudyDescriptionActivity extends Activity implements
		OnClickListener {

	/**
	 * variable to capture current customer being visited
	 */
	private String currentCustomer = null;

	/**
	 * ASYNC Task to get the question details
	 */
	private LoadNodeDescriptionAsync loadNodeDescriptionAsync;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// set the view
		setContentView(R.layout.activity_case_study_description);

		// Show the Up button in the action bar.
		setupActionBar();

		// get the current customer clicked from previous activity
		currentCustomer = SalesManagementSaveActivityState.getCurrentCustomer();

		// add on click listener for start button
		findViewById(R.id.optionsNavigate).setOnClickListener(this);

		// add on click listener on the back button
		findViewById(R.id.backPlayFromActivityDescription).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						createAlertDialog();
					}
				});

		// initialize the ASYNC data loader
		loadNodeDescriptionAsync = new LoadNodeDescriptionAsync(this);
		loadNodeDescriptionAsync.execute(currentCustomer);

	}

	/**
	 * Fills the view with the details being passed from as
	 * SalesManagementQuestion
	 * 
	 * @param salesManagementQuestion
	 *            containing all the description of question
	 */
	public void populateViewDetails(
			SalesManagementQuestion salesManagementQuestion) {

		// set the customer
		((TextView) findViewById(R.id.customerValue)).setText(currentCustomer);

		// set the title
		((TextView) findViewById(R.id.customerCaseStudyTitleValue))
				.setText(salesManagementQuestion.getCaseStudyTitle());

		// set the organization description
		((TextView) findViewById(R.id.customerCaseStudyOrganizationValue))
				.setText(salesManagementQuestion.getCaseStudyOrganization());

		// set the case description
		((TextView) findViewById(R.id.questionDescription))
				.setText(salesManagementQuestion.getCaseStudyDescription());

	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.case_study_display, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			createAlertDialog();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// create the intent
		Intent intent = new Intent(CaseStudyDescriptionActivity.this,
				CaseStudyOptionsActivity.class);

		// start the options activity
		startActivity(intent);

	}

	@Override
	public void onBackPressed() {
		createAlertDialog();
	}

	/**
	 * on back pressed , show a dialog to user asking to confirm to go back or
	 * not
	 */
	private void createAlertDialog() {

		// create the alert dialog asking user to go back
		AlertDialog.Builder builder = new AlertDialog.Builder(
				CaseStudyDescriptionActivity.this);

		// set message and the title
		builder.setMessage(R.string.popupdialogerrorMessage);
		builder.setTitle(R.string.popupdialogTitle);

		// set the button handlers
		builder.setNegativeButton(R.string.popupdialogCancelButton,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {

					}
				});

		// set the button handlers
		builder.setPositiveButton(R.string.popupdialogConfirmButton,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {

						Intent intent = new Intent(getApplicationContext(),
								PlayGridActivity.class);
						startActivity(intent);

					}
				});

		// create the dialog
		AlertDialog alertDialog = builder.create();

		// show the dialog
		alertDialog.show();

	}

}
