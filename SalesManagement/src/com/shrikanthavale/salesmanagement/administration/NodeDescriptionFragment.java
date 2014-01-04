/**
 * 
 */
package com.shrikanthavale.salesmanagement.administration;

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
import com.shrikanthavale.salesmanagement.entities.SalesManagementQuestion;
import com.shrikanthavale.salesmanagement.webserviceutility.SalesManagementSaveActivityState;

/**
 * @author Shrikant Havale
 * 
 *         This Fragment is part of Administration activity, and is responsible
 *         for allowing users to edit the title, organization and description of
 *         the question
 */
public class NodeDescriptionFragment extends Fragment {

	/**
	 * communication interface to communicate details to activity
	 */
	private FragmentCommunicationInterface communicationInterface;

	/**
	 * view representing the XML file
	 */
	private View nodeDescriptionView;

	/**
	 * ASYNC task for database operations
	 */
	private SaveNodeQuestionDetailsAsync saveData;

	/**
	 * sales management question variable to store data
	 */
	private SalesManagementQuestion salesManagementQuestion;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// get the XML view
		nodeDescriptionView = inflater.inflate(
				R.layout.fragment_administration_node_description, container,
				false);

		// cancel changes button
		Button cancelChanges = (Button) nodeDescriptionView
				.findViewById(R.id.adminCancelChangesButton);

		// on cancel changes just reload the same details again
		cancelChanges.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// populate the old data again
				populateDetailsFromCustomer();
			}
		});

		// save changes button to save changes to database
		Button saveChanges = (Button) nodeDescriptionView
				.findViewById(R.id.adminSaveChangesButton);

		// save change listener
		saveChanges.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (validateChanges())
					saveChangesDatabase();
				else
					Toast.makeText(NodeDescriptionFragment.this.getActivity(),
							"Field Validation Error", Toast.LENGTH_SHORT)
							.show();

			}
		});

		// return the view
		return nodeDescriptionView;
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
	 * Populate the details after accepting the customer. Data access call is
	 * made in this method and update is made in database.
	 * 
	 * @param customer
	 *            - customer whose case study is to be fetched
	 */
	public void populateDetailsFromCustomer() {

		// check for not null
		if (nodeDescriptionView != null && salesManagementQuestion != null) {

			// update the view with the details
			((EditText) nodeDescriptionView.findViewById(R.id.adminTitleEdit))
					.setText(salesManagementQuestion.getCaseStudyTitle());
			((EditText) nodeDescriptionView
					.findViewById(R.id.adminOrganizationEdit))
					.setText(salesManagementQuestion.getCaseStudyOrganization());
			((EditText) nodeDescriptionView
					.findViewById(R.id.adminDescriptionEdit))
					.setText(salesManagementQuestion.getCaseStudyDescription());
		}

	}

	/**
	 * Refresh the node list after updating the nodes
	 */
	public void requestRefreshNodeList() {
		communicationInterface.refreshNodeList();
	}

	/**
	 * Validate changes , performs basic mandatory field validation
	 * 
	 * @return true - if validation successful false if validation not
	 *         successful
	 */
	private boolean validateChanges() {

		// initialize the variable
		boolean validated = true;

		// get the details from view
		String title = ((EditText) nodeDescriptionView
				.findViewById(R.id.adminTitleEdit)).getText().toString();
		String organization = ((EditText) nodeDescriptionView
				.findViewById(R.id.adminOrganizationEdit)).getText().toString();
		String description = ((EditText) nodeDescriptionView
				.findViewById(R.id.adminDescriptionEdit)).getText().toString();

		// empty field validation
		if (title.trim().length() == 0) {
			((EditText) nodeDescriptionView.findViewById(R.id.adminTitleEdit))
					.setError("Title is mandatory");
			validated = false;
		}

		// empty field validation
		if (organization.trim().length() == 0) {
			((EditText) nodeDescriptionView
					.findViewById(R.id.adminOrganizationEdit))
					.setError("Organization is mandatory");
			validated = false;
		}

		// empty field validation
		if (description.trim().length() == 0) {
			((EditText) nodeDescriptionView
					.findViewById(R.id.adminDescriptionEdit))
					.setError("Description is mandatory");
			validated = false;
		}

		// returns validation
		return validated;
	}

	/**
	 * save the changes to database using web service call
	 */
	private void saveChangesDatabase() {

		// get the details from view
		String title = ((EditText) nodeDescriptionView
				.findViewById(R.id.adminTitleEdit)).getText().toString();
		String organization = ((EditText) nodeDescriptionView
				.findViewById(R.id.adminOrganizationEdit)).getText().toString();
		String description = ((EditText) nodeDescriptionView
				.findViewById(R.id.adminDescriptionEdit)).getText().toString();

		// create a sales management question entity
		SalesManagementQuestion salesManagementQuestion = new SalesManagementQuestion();
		salesManagementQuestion.setCaseStudyDescription(description);
		salesManagementQuestion
				.setCaseStudyNode(SalesManagementSaveActivityState
						.getCustomerAdministration());
		salesManagementQuestion.setCaseStudyOrganization(organization);
		salesManagementQuestion.setCaseStudyTitle(title);

		// create the instance of ASYNC task
		saveData = new SaveNodeQuestionDetailsAsync(this);

		// save in database
		saveData.execute(salesManagementQuestion);

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
	 * @return the salesManagementQuestion
	 */
	public SalesManagementQuestion getSalesManagementQuestion() {
		return salesManagementQuestion;
	}

	/**
	 * @param salesManagementQuestion
	 *            the salesManagementQuestion to set
	 */
	public void setSalesManagementQuestion(
			SalesManagementQuestion salesManagementQuestion) {
		this.salesManagementQuestion = salesManagementQuestion;
	}

}
