/**
 * 
 */
package com.shrikanthavale.salesmanagement.administration;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shrikanthavale.salesmanagement.R;
import com.shrikanthavale.salesmanagement.entities.SalesManagementQuestionOptions;
import com.shrikanthavale.salesmanagement.webserviceutility.SalesManagementSaveActivityState;

/**
 * @author Shrikant Havale
 * 
 *         This fragment is responsible for displaying the details of the
 *         options, their evaluation and money associated with it. It also
 *         allows to change the details related to options of questions
 * 
 */
public class NodeOptionsFragment extends Fragment {

	/**
	 * communication interface
	 */
	private FragmentCommunicationInterface communicationInterface;

	/**
	 * view containing the UI components
	 */
	private View nodeOptionView;

	/**
	 * ASYNC task for database operations options saving
	 */
	private SaveNodeOptionsDetailsAsync saveData;

	/**
	 * list for storing the list of options
	 */
	private List<SalesManagementQuestionOptions> salesManagementQuestionOptions;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// inflate the view
		nodeOptionView = inflater
				.inflate(R.layout.fragment_administration_node_options,
						container, false);

		// cancel the changes
		Button cancelChanges = (Button) nodeOptionView
				.findViewById(R.id.adminCancelChangesButton);

		cancelChanges.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				populateDetailsFromCustomer();
			}
		});

		// save the changes to the database
		Button saveChanges = (Button) nodeOptionView
				.findViewById(R.id.adminSaveChangesButton);

		// save changes on click listener
		saveChanges.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("unused")
			@Override
			public void onClick(View v) {
				if (true) {
					Toast.makeText(
							getActivity(),
							"Editing the case study details or options feature is not yet available",
							Toast.LENGTH_SHORT).show();
					return;

				}
				if (validateChanges())
					saveChangesDatabase();
				else
					Toast.makeText(NodeOptionsFragment.this.getActivity(),
							"Field Validation Error", Toast.LENGTH_SHORT)
							.show();
			}
		});

		// button for showing content related to option A
		final Button optionAButton = (Button) nodeOptionView
				.findViewById(R.id.adminOptionAbutton);

		// button for showing content related to option B
		final Button optionBButton = (Button) nodeOptionView
				.findViewById(R.id.adminOptionBbutton);

		// button for showing content related to option C
		final Button optionCButton = (Button) nodeOptionView
				.findViewById(R.id.adminOptionCbutton);

		// button for showing content related to option D
		final Button optionDButton = (Button) nodeOptionView
				.findViewById(R.id.adminOptionDbutton);

		// set on click listener for option A button
		optionAButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				nodeOptionView.findViewById(R.id.optionALayout).setVisibility(
						View.VISIBLE);
				nodeOptionView.findViewById(R.id.optionBLayout).setVisibility(
						View.INVISIBLE);
				nodeOptionView.findViewById(R.id.optionCLayout).setVisibility(
						View.INVISIBLE);
				nodeOptionView.findViewById(R.id.optionDLayout).setVisibility(
						View.INVISIBLE);
				optionAButton.setEnabled(false);
				optionBButton.setEnabled(true);
				optionCButton.setEnabled(true);
				optionDButton.setEnabled(true);
			}
		});

		// set on click listener for option B button
		optionBButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				nodeOptionView.findViewById(R.id.optionALayout).setVisibility(
						View.INVISIBLE);
				nodeOptionView.findViewById(R.id.optionBLayout).setVisibility(
						View.VISIBLE);
				nodeOptionView.findViewById(R.id.optionCLayout).setVisibility(
						View.INVISIBLE);
				nodeOptionView.findViewById(R.id.optionDLayout).setVisibility(
						View.INVISIBLE);
				optionAButton.setEnabled(true);
				optionBButton.setEnabled(false);
				optionCButton.setEnabled(true);
				optionDButton.setEnabled(true);

			}
		});

		// set on click listener for option C button
		optionCButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				nodeOptionView.findViewById(R.id.optionALayout).setVisibility(
						View.INVISIBLE);
				nodeOptionView.findViewById(R.id.optionBLayout).setVisibility(
						View.INVISIBLE);
				nodeOptionView.findViewById(R.id.optionCLayout).setVisibility(
						View.VISIBLE);
				nodeOptionView.findViewById(R.id.optionDLayout).setVisibility(
						View.INVISIBLE);
				optionAButton.setEnabled(true);
				optionBButton.setEnabled(true);
				optionCButton.setEnabled(false);
				optionDButton.setEnabled(true);
			}
		});

		// set on click listener for option D button
		optionDButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				nodeOptionView.findViewById(R.id.optionALayout).setVisibility(
						View.INVISIBLE);
				nodeOptionView.findViewById(R.id.optionBLayout).setVisibility(
						View.INVISIBLE);
				nodeOptionView.findViewById(R.id.optionCLayout).setVisibility(
						View.INVISIBLE);
				nodeOptionView.findViewById(R.id.optionDLayout).setVisibility(
						View.VISIBLE);
				optionAButton.setEnabled(true);
				optionBButton.setEnabled(true);
				optionCButton.setEnabled(true);
				optionDButton.setEnabled(false);
			}
		});

		// return the node option view
		return nodeOptionView;
	}

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);

		if (activity instanceof FragmentCommunicationInterface) {
			setCommunicationInterface((FragmentCommunicationInterface) activity);
		} else {
			throw new ClassCastException(activity.toString()
					+ " must implemenet MyListFragment.OnItemSelectedListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		setCommunicationInterface(null);
	}

	/**
	 * This method populates the option details, like option value , evaluation
	 * , money for one particular question of customer
	 * 
	 * @param customer
	 *            customer for which option details are to fetched/displayed
	 */
	public void populateDetailsFromCustomer() {

		// check for null
		if (nodeOptionView != null && salesManagementQuestionOptions != null) {

			// iterate and set data accordingly
			for (SalesManagementQuestionOptions salesManagementQuestionOption : salesManagementQuestionOptions) {

				switch (salesManagementQuestionOption
						.getCaseStudyOptionNumber()) {
				// for option A
				case 1:
					((EditText) nodeOptionView
							.findViewById(R.id.adminOptionADescription))
							.setText(salesManagementQuestionOption
									.getQuestionOptionDescription());
					((EditText) nodeOptionView
							.findViewById(R.id.adminOptionAEvaluation))
							.setText(salesManagementQuestionOption
									.getQuestionOptionEvaluation());
					((EditText) nodeOptionView
							.findViewById(R.id.adminOptionAMoney)).setText(""
							+ salesManagementQuestionOption
									.getQuestionOptionMoneyAssoicated());

					break;

				// for option B
				case 2:
					((EditText) nodeOptionView
							.findViewById(R.id.adminOptionBDescription))
							.setText(salesManagementQuestionOption
									.getQuestionOptionDescription());
					((EditText) nodeOptionView
							.findViewById(R.id.adminOptionBEvaluation))
							.setText(salesManagementQuestionOption
									.getQuestionOptionEvaluation());
					((EditText) nodeOptionView
							.findViewById(R.id.adminOptionBMoney)).setText(""
							+ salesManagementQuestionOption
									.getQuestionOptionMoneyAssoicated());
					break;
				// for option C
				case 3:
					((EditText) nodeOptionView
							.findViewById(R.id.adminOptionCDescription))
							.setText(salesManagementQuestionOption
									.getQuestionOptionDescription());
					((EditText) nodeOptionView
							.findViewById(R.id.adminOptionCEvaluation))
							.setText(salesManagementQuestionOption
									.getQuestionOptionEvaluation());
					((EditText) nodeOptionView
							.findViewById(R.id.adminOptionCMoney)).setText(""
							+ salesManagementQuestionOption
									.getQuestionOptionMoneyAssoicated());
					break;

				// for option D
				case 4:
					((EditText) nodeOptionView
							.findViewById(R.id.adminOptionDDescription))
							.setText(salesManagementQuestionOption
									.getQuestionOptionDescription());
					((EditText) nodeOptionView
							.findViewById(R.id.adminOptionDEvaluation))
							.setText(salesManagementQuestionOption
									.getQuestionOptionEvaluation());
					((EditText) nodeOptionView
							.findViewById(R.id.adminOptionDMoney)).setText(""
							+ salesManagementQuestionOption
									.getQuestionOptionMoneyAssoicated());
					break;
				default:
					break;
				}

			}
		}

	}

	/**
	 * Validate changes ,perform basic validations like mandatory field
	 * 
	 * @return true if validated succeeds
	 */
	private boolean validateChanges() {

		// validation
		boolean validated = true;

		// option description
		String optionADescription = ((EditText) nodeOptionView
				.findViewById(R.id.adminOptionADescription)).getText()
				.toString();
		String optionAEvaluation = ((EditText) nodeOptionView
				.findViewById(R.id.adminOptionAEvaluation)).getText()
				.toString();
		String optionAMoney = ((EditText) nodeOptionView
				.findViewById(R.id.adminOptionAMoney)).getText().toString();

		if (optionADescription.trim().length() == 0) {
			((EditText) nodeOptionView
					.findViewById(R.id.adminOptionADescription))
					.setError("Description is Mandatory");
			validated = false;
		}

		if (optionAEvaluation.trim().length() == 0) {
			((EditText) nodeOptionView
					.findViewById(R.id.adminOptionAEvaluation))
					.setError("Evaluation is Mandatory");
			validated = false;
		}

		if (optionAMoney.trim().length() == 0) {
			((EditText) nodeOptionView.findViewById(R.id.adminOptionAMoney))
					.setError("Amount is Mandatory");
			validated = false;
		}

		// option B description
		String optionBDescription = ((EditText) nodeOptionView
				.findViewById(R.id.adminOptionBDescription)).getText()
				.toString();
		String optionBEvaluation = ((EditText) nodeOptionView
				.findViewById(R.id.adminOptionBEvaluation)).getText()
				.toString();
		String optionBMoney = ((EditText) nodeOptionView
				.findViewById(R.id.adminOptionBMoney)).getText().toString();

		if (optionBDescription.trim().length() == 0) {
			((EditText) nodeOptionView
					.findViewById(R.id.adminOptionBDescription))
					.setError("Description is Mandatory");
			validated = false;
		}

		if (optionBEvaluation.trim().length() == 0) {
			((EditText) nodeOptionView
					.findViewById(R.id.adminOptionBEvaluation))
					.setError("Evaluation is Mandatory");
			validated = false;
		}

		if (optionBMoney.trim().length() == 0) {
			((EditText) nodeOptionView.findViewById(R.id.adminOptionBMoney))
					.setError("Amount is Mandatory");
			validated = false;
		}

		// option C description
		String optionCDescription = ((EditText) nodeOptionView
				.findViewById(R.id.adminOptionCDescription)).getText()
				.toString();
		String optionCEvaluation = ((EditText) nodeOptionView
				.findViewById(R.id.adminOptionCEvaluation)).getText()
				.toString();
		String optionCMoney = ((EditText) nodeOptionView
				.findViewById(R.id.adminOptionCMoney)).getText().toString();

		if (optionCDescription.trim().length() == 0) {
			((EditText) nodeOptionView
					.findViewById(R.id.adminOptionCDescription))
					.setError("Description is Mandatory");
			validated = false;
		}

		if (optionCEvaluation.trim().length() == 0) {
			((EditText) nodeOptionView
					.findViewById(R.id.adminOptionCEvaluation))
					.setError("Evaluation is Mandatory");
			validated = false;
		}

		if (optionCMoney.trim().length() == 0) {
			((EditText) nodeOptionView.findViewById(R.id.adminOptionCMoney))
					.setError("Amount is Mandatory");
			validated = false;
		}

		// option D description
		String optionDDescription = ((EditText) nodeOptionView
				.findViewById(R.id.adminOptionDDescription)).getText()
				.toString();
		String optionDEvaluation = ((EditText) nodeOptionView
				.findViewById(R.id.adminOptionDEvaluation)).getText()
				.toString();
		String optionDMoney = ((EditText) nodeOptionView
				.findViewById(R.id.adminOptionDMoney)).getText().toString();

		// empty field validation
		if (optionDDescription.trim().length() == 0) {
			((EditText) nodeOptionView
					.findViewById(R.id.adminOptionDDescription))
					.setError("Description is Mandatory");
			validated = false;
		}

		if (optionDEvaluation.trim().length() == 0) {
			((EditText) nodeOptionView
					.findViewById(R.id.adminOptionDEvaluation))
					.setError("Evaluation is Mandatory");
			validated = false;
		}

		if (optionDMoney.trim().length() == 0) {
			((EditText) nodeOptionView.findViewById(R.id.adminOptionDMoney))
					.setError("Amount is Mandatory");
			validated = false;
		}

		return validated;
	}

	/**
	 * save changes in database via webservice call using ASYNC Task
	 */
	@SuppressWarnings("unchecked")
	private void saveChangesDatabase() {

		// store all the four options in list
		List<SalesManagementQuestionOptions> salesManagementQuestionOptions = new ArrayList<SalesManagementQuestionOptions>();

		// capturing all the details related to option A
		SalesManagementQuestionOptions salesManagementQuestionOptionA = new SalesManagementQuestionOptions();
		String optionADescription = ((EditText) nodeOptionView
				.findViewById(R.id.adminOptionADescription)).getText()
				.toString();
		String optionAEvaluation = ((EditText) nodeOptionView
				.findViewById(R.id.adminOptionAEvaluation)).getText()
				.toString();
		String optionAMoney = ((EditText) nodeOptionView
				.findViewById(R.id.adminOptionAMoney)).getText().toString();

		salesManagementQuestionOptionA
				.setCaseStudyNode(SalesManagementSaveActivityState
						.getCustomerAdministration());
		salesManagementQuestionOptionA.setCaseStudyOptionNumber(1);
		salesManagementQuestionOptionA
				.setQuestionOptionDescription(optionADescription);
		salesManagementQuestionOptionA
				.setQuestionOptionEvaluation(optionAEvaluation);
		salesManagementQuestionOptionA.setQuestionOptionMoneyAssoicated(Integer
				.parseInt(optionAMoney));

		// capturing all the details related to option B
		SalesManagementQuestionOptions salesManagementQuestionOptionB = new SalesManagementQuestionOptions();
		String optionBDescription = ((EditText) nodeOptionView
				.findViewById(R.id.adminOptionBDescription)).getText()
				.toString();
		String optionBEvaluation = ((EditText) nodeOptionView
				.findViewById(R.id.adminOptionBEvaluation)).getText()
				.toString();
		String optionBMoney = ((EditText) nodeOptionView
				.findViewById(R.id.adminOptionBMoney)).getText().toString();

		salesManagementQuestionOptionB
				.setCaseStudyNode(SalesManagementSaveActivityState
						.getCustomerAdministration());
		salesManagementQuestionOptionB.setCaseStudyOptionNumber(2);
		salesManagementQuestionOptionB
				.setQuestionOptionDescription(optionBDescription);
		salesManagementQuestionOptionB
				.setQuestionOptionEvaluation(optionBEvaluation);
		salesManagementQuestionOptionB.setQuestionOptionMoneyAssoicated(Integer
				.parseInt(optionBMoney));

		// capturing all the details related to option C
		SalesManagementQuestionOptions salesManagementQuestionOptionC = new SalesManagementQuestionOptions();
		String optionCDescription = ((EditText) nodeOptionView
				.findViewById(R.id.adminOptionCDescription)).getText()
				.toString();
		String optionCEvaluation = ((EditText) nodeOptionView
				.findViewById(R.id.adminOptionCEvaluation)).getText()
				.toString();
		String optionCMoney = ((EditText) nodeOptionView
				.findViewById(R.id.adminOptionCMoney)).getText().toString();

		salesManagementQuestionOptionC
				.setCaseStudyNode(SalesManagementSaveActivityState
						.getCustomerAdministration());
		salesManagementQuestionOptionC.setCaseStudyOptionNumber(3);
		salesManagementQuestionOptionC
				.setQuestionOptionDescription(optionCDescription);
		salesManagementQuestionOptionC
				.setQuestionOptionEvaluation(optionCEvaluation);
		salesManagementQuestionOptionC.setQuestionOptionMoneyAssoicated(Integer
				.parseInt(optionCMoney));

		// capturing all the details related to option D
		SalesManagementQuestionOptions salesManagementQuestionOptionD = new SalesManagementQuestionOptions();
		String optionDDescription = ((EditText) nodeOptionView
				.findViewById(R.id.adminOptionDDescription)).getText()
				.toString();
		String optionDEvaluation = ((EditText) nodeOptionView
				.findViewById(R.id.adminOptionDEvaluation)).getText()
				.toString();
		String optionDMoney = ((EditText) nodeOptionView
				.findViewById(R.id.adminOptionDMoney)).getText().toString();

		salesManagementQuestionOptionD
				.setCaseStudyNode(SalesManagementSaveActivityState
						.getCustomerAdministration());
		salesManagementQuestionOptionD.setCaseStudyOptionNumber(4);
		salesManagementQuestionOptionD
				.setQuestionOptionDescription(optionDDescription);
		salesManagementQuestionOptionD
				.setQuestionOptionEvaluation(optionDEvaluation);
		salesManagementQuestionOptionD.setQuestionOptionMoneyAssoicated(Integer
				.parseInt(optionDMoney));

		// add all them to list
		salesManagementQuestionOptions.add(salesManagementQuestionOptionA);
		salesManagementQuestionOptions.add(salesManagementQuestionOptionB);
		salesManagementQuestionOptions.add(salesManagementQuestionOptionC);
		salesManagementQuestionOptions.add(salesManagementQuestionOptionD);

		// create the instance of ASYNC task
		saveData = new SaveNodeOptionsDetailsAsync(this);
		saveData.execute(salesManagementQuestionOptions);

	}

	/**
	 * @return the communicationInterface
	 */
	public FragmentCommunicationInterface getCommunicationInterface() {
		return communicationInterface;
	}

	/**
	 * @param communicationInterface
	 *            the communicationInterface to set
	 */
	public void setCommunicationInterface(
			FragmentCommunicationInterface communicationInterface) {
		this.communicationInterface = communicationInterface;
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
