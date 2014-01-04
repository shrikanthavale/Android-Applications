package com.shrikanthavale.salesmanagement;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TextView;

import com.shrikanthavale.salesmanagement.entities.SalesManagementQuestionOptions;
import com.shrikanthavale.salesmanagement.webserviceutility.SalesManagementSaveActivityState;

/**
 * 
 * @author Shrikant Havale
 * 
 *         This activity shows the options for the question based on the node
 *         that was clicked in Play Grid Activity.
 * 
 */
public class CaseStudyOptionsActivity extends Activity {

	/**
	 * flag to capture if question answered or not
	 */
	private boolean questionAnswered = false;

	/**
	 * list of question options, evaluation and money associated
	 */
	private List<SalesManagementQuestionOptions> salesManagementQuestionOptions;

	/**
	 * ASYNC service to load the options asynchronously
	 */
	private LoadNodeOptionsAsync loadNodeOptionsAsync;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// create the view
		setContentView(R.layout.activity_case_study_options);

		// Show the Up button in the action bar.
		setupActionBar();

		// handle the event of submit solution
		findViewById(R.id.submitSoultionButton).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {

						int optionSelected = -1;

						// select the option selected
						if (((RadioButton) findViewById(R.id.radio_optionA))
								.isChecked()) {
							optionSelected = 0;
						} else if (((RadioButton) findViewById(R.id.radio_optionB))
								.isChecked()) {
							optionSelected = 1;
						} else if (((RadioButton) findViewById(R.id.radio_optionC))
								.isChecked()) {
							optionSelected = 2;
						} else if (((RadioButton) findViewById(R.id.radio_optionD))
								.isChecked()) {
							optionSelected = 3;
						}

						// get the evaluation for the option selected
						SalesManagementQuestionOptions salesManagementOptionEvaluation = salesManagementQuestionOptions
								.get(optionSelected);

						// set the evaluation text
						String evaluationText = salesManagementOptionEvaluation
								.getQuestionOptionEvaluation()
								+ "\n\n"
								+ "Your answer is worth : $"
								+ salesManagementOptionEvaluation
										.getQuestionOptionMoneyAssoicated();

						// option evaluation text
						((TextView) findViewById(R.id.optionEvaluationText))
								.setText(evaluationText);

						// set the amount of money earned
						SalesManagementSaveActivityState
								.setAmountEarned(salesManagementOptionEvaluation
										.getQuestionOptionMoneyAssoicated()
										+ SalesManagementSaveActivityState
												.getAmountEarned());

						// disable the group options
						findViewById(R.id.radioGroupOptions).setEnabled(false);

						// get the layout and make it invisible
						findViewById(R.id.backSubmitButtonLayout)
								.setVisibility(View.INVISIBLE);

						// make it visible
						findViewById(R.id.continuePlayAreaButtonLayout)
								.setVisibility(View.VISIBLE);

						// flag for question anwered
						questionAnswered = true;

					}
				});

		// navigate back to play area button
		findViewById(R.id.backPlayArea).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {

						// new intent
						Intent intent = new Intent(getApplicationContext(),
								CaseStudyDescriptionActivity.class);

						// start the activity
						startActivity(intent);
					}
				});

		// navigate to continue play area button
		findViewById(R.id.continuePlayArea).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// new intent
						Intent intent = new Intent(getApplicationContext(),
								PlayGridActivity.class);

						// start the activity
						startActivity(intent);
					}
				});

		// get the current customer
		String currentCustomer = SalesManagementSaveActivityState
				.getCurrentCustomer();

		// load the data ASYNC
		loadNodeOptionsAsync = new LoadNodeOptionsAsync(this);
		loadNodeOptionsAsync.execute(currentCustomer);

	}

	/**
	 * populate the options with data obtained from web service, this method is
	 * called from ASYNC task
	 */
	public void populateQuestionOptions() {

		// set them to radio options
		((RadioButton) findViewById(R.id.radio_optionA))
				.setText(salesManagementQuestionOptions.get(0)
						.getQuestionOptionDescription());
		((RadioButton) findViewById(R.id.radio_optionB))
				.setText(salesManagementQuestionOptions.get(1)
						.getQuestionOptionDescription());
		((RadioButton) findViewById(R.id.radio_optionC))
				.setText(salesManagementQuestionOptions.get(2)
						.getQuestionOptionDescription());
		((RadioButton) findViewById(R.id.radio_optionD))
				.setText(salesManagementQuestionOptions.get(3)
						.getQuestionOptionDescription());

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
		getMenuInflater().inflate(R.menu.case_study_options, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if (!questionAnswered) {
				// new intent
				Intent intent = new Intent(getApplicationContext(),
						CaseStudyDescriptionActivity.class);

				// start the activity
				startActivity(intent);
			} else {
				// new intent
				Intent intent = new Intent(getApplicationContext(),
						PlayGridActivity.class);

				// start the activity
				startActivity(intent);
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		if (!questionAnswered) {
			// new intent
			Intent intent = new Intent(getApplicationContext(),
					CaseStudyDescriptionActivity.class);

			// start the activity
			startActivity(intent);
		} else {
			// new intent
			Intent intent = new Intent(getApplicationContext(),
					PlayGridActivity.class);

			// start the activity
			startActivity(intent);
		}
	}

	/**
	 * @return the salesManagementQuestionOptions
	 */
	public List<SalesManagementQuestionOptions> getSalesManagementQuestionOptions() {
		return salesManagementQuestionOptions;
	}

	/**
	 * @param salesManagementQuestionOptions
	 *            the salesManagementQuestionOptions to set
	 */
	public void setSalesManagementQuestionOptions(
			List<SalesManagementQuestionOptions> salesManagementQuestionOptions) {
		this.salesManagementQuestionOptions = salesManagementQuestionOptions;
	}

}
