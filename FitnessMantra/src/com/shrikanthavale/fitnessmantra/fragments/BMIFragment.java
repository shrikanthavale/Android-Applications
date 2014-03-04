/**
 * 
 */
package com.shrikanthavale.fitnessmantra.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bodymassindex.R;

/**
 * @author Shrikant Havale
 * 
 */
public class BMIFragment extends Fragment {

	/**
	 * view containing the UI components
	 */
	private View bmiFragmentView;

	// declaring adapters to bind with spinners
	private ArrayAdapter<String> heightFeetsAdapter;
	private ArrayAdapter<String> heightMetersAdapter;
	private ArrayAdapter<String> weightLibsAdapter;
	private ArrayAdapter<String> weightKgsAdapter;

	// ref for UI elements
	private Spinner weightSpinner;
	private Spinner heightSpinner;
	private Spinner weightUnitSpinner;
	private Spinner heightUnitSpinner;
	private TextView bmiValueText;
	private TextView bmiDescriptionText;
	private Button calculateButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// inflate the view
		bmiFragmentView = inflater.inflate(R.layout.bmi_fragment, container,
				false);

		// ref into the widgets
		weightSpinner = (Spinner) bmiFragmentView.findViewById(R.id.spinner1);
		weightUnitSpinner = (Spinner) bmiFragmentView
				.findViewById(R.id.spinner2);
		heightSpinner = (Spinner) bmiFragmentView.findViewById(R.id.spinner3);
		heightUnitSpinner = (Spinner) bmiFragmentView
				.findViewById(R.id.spinner4);
		bmiValueText = (TextView) bmiFragmentView.findViewById(R.id.textView4);
		bmiDescriptionText = (TextView) bmiFragmentView
				.findViewById(R.id.textView5);
		calculateButton = (Button) bmiFragmentView.findViewById(R.id.button1);

		calculateButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				calculateClickHandler();
			}
		});

		// value for spinners
		initializeSpinnerAdapters();

		// default value range for spinners
		loadLibsValueRange();
		loadFeetsValueRange();

		// listeners to conversion
		addListernsToUnitChanges();

		// return the node option view
		return bmiFragmentView;
	}

	public void calculateClickHandler() {

		float weight = getSelectedWeight();
		float height = getSelectedHeight();

		float bmiValue = CalculateBMI(weight, height);
		bmiValueText.setText(bmiValue + "");

		int bmiInterpretation = interpretBMI(bmiValue);
		bmiDescriptionText.setText(getResources().getString(bmiInterpretation));

		int bmiColor = colorBMI(bmiValue);
		bmiValueText.setTextColor(getResources().getColor(bmiColor));
		bmiDescriptionText.setTextColor(getResources().getColor(bmiColor));

	}

	private int colorBMI(float bmiValue) {
		if (bmiValue < 16) {
			return R.color.colorRed;
		} else if (bmiValue < 18.5) {

			return R.color.colorYellow;
		} else if (bmiValue < 25) {

			return R.color.colorGreen;
		} else if (bmiValue < 30) {

			return R.color.colorYellow;
		} else {
			return R.color.colorRed;
		}
	}

	private float CalculateBMI(float weight, float height) {
		return (float) (weight / (height * height));
	}

	private int interpretBMI(float bmiValue) {
		if (bmiValue < 16) {
			return R.string.bmiSUnder;
		} else if (bmiValue < 18.5) {

			return R.string.bmiUnder;
		} else if (bmiValue < 25) {

			return R.string.bmiNormal;
		} else if (bmiValue < 30) {

			return R.string.bmiOver;
		} else {
			return R.string.bmiObese;
		}
	}

	private float getSelectedHeight() {
		String selectedHeightValue = (String) heightSpinner.getSelectedItem();
		if (heightUnitSpinner.getSelectedItemPosition() == 0) {
			// the position is feets and inches, so convert to meters and return
			String feets = selectedHeightValue.substring(0, 1);
			String inches = selectedHeightValue.substring(2, 4);
			return (float) (Float.parseFloat(feets) * 0.3048)

			+ (float) (Float.parseFloat(inches) * 0.0254);
		} else {

			// already meters is selected, so no need to covert (just cast to
			// float)
			return Float.parseFloat(selectedHeightValue);
		}
	}

	private float getSelectedWeight() {
		String selectedWeightValue = (String) weightSpinner.getSelectedItem();
		if (weightUnitSpinner.getSelectedItemPosition() == 0) {

			// libs to kgs
			return (float) (Float.parseFloat(selectedWeightValue) * 0.45359237);
		} else {

			// if already kg is selected, so no need to covert (just cast to
			// float)
			return Float.parseFloat(selectedWeightValue);
		}
	}

	private void addListernsToUnitChanges() {
		weightUnitSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							View view, int row, long id) {

						// load the relevent units and the values
						if (row == 0) {
							// libs is selected
							loadLibsValueRange();
						} else {

							// kg is selected
							loadKgsValueRange();
						}
					}

					public void onNothingSelected(AdapterView<?> arg0) {

						// Nothing to do here
					}
				});

		// listener to the height unit
		heightUnitSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> parent,
							View view, int row, long id) {

						// load the relevent units and the values
						if (row == 0) {
							// feets is selected
							loadFeetsValueRange();
						} else {

							// meters is selected
							loadMetersValueRange();
						}
					}

					public void onNothingSelected(AdapterView<?> arg0) {

						// Nothing to do here
					}
				});
	}

	public void loadLibsValueRange() {

		weightSpinner.setAdapter(weightLibsAdapter);
		// set the default lib value
		weightSpinner.setSelection(weightLibsAdapter.getPosition("170"));
	}

	public void loadKgsValueRange() {
		weightSpinner.setAdapter(weightKgsAdapter);
		// set the default vaule for kg

		weightSpinner.setSelection(weightKgsAdapter.getPosition(" 77"));
	}

	// load the feets value range to the height spinner
	public void loadFeetsValueRange() {

		heightSpinner.setAdapter(heightFeetsAdapter);
		// set the default value to feets
		heightSpinner.setSelection(heightFeetsAdapter.getPosition("5\"05'"));
	}

	// load the meters value range to the height spinner
	public void loadMetersValueRange() {
		heightSpinner.setAdapter(heightMetersAdapter);
		// set the default value to meters

		heightSpinner.setSelection(heightMetersAdapter.getPosition("1.65"));
	}

	private void initializeSpinnerAdapters() {
		String[] weightLibs = new String[300];
		// loading 1.0 to 300 to the weight in libs

		int k = 299;
		for (int i = 1; i <= 300; i++) {

			weightLibs[k--] = String.format("%3d", i);
		}

		// initialize the weightLibsAdapter with the weightLibs values
		weightLibsAdapter = new ArrayAdapter<String>(this.getActivity(),
				android.R.layout.simple_spinner_item, weightLibs);

		String[] weightKgs = new String[200];
		// loading 1.0 to 200 to the weight in kgs

		k = 199;
		for (int i = 1; i <= 200; i++) {

			weightKgs[k--] = String.format("%3d", i);
		}

		// initialize the weightKgsAdapter with the weightKgs values
		weightKgsAdapter = new ArrayAdapter<String>(this.getActivity(),
				android.R.layout.simple_spinner_item, weightKgs);

		String[] heightFeets = new String[60];
		// loading 3"0' to 7"11' to the height in feet/inch

		k = 59;
		for (int i = 3; i < 8; i++) {

			for (int j = 0; j < 12; j++) {
				heightFeets[k--] = i + "\"" + String.format("%02d", j) + "'";
			}

		}
		// initialize the heightFeetAdapter with the heightFeets values
		heightFeetsAdapter = new ArrayAdapter<String>(this.getActivity(),
				android.R.layout.simple_spinner_item, heightFeets);

		String[] heightMeters = new String[300];
		// loading 0.0 to 2.9 to the height in m/cm

		k = 299;
		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 100; j++) {
				heightMeters[k--] = i + "." + j;
			}

		}
		// initialize the heightMetersAdapter with the heightMeters values
		heightMetersAdapter = new ArrayAdapter<String>(this.getActivity(),
				android.R.layout.simple_spinner_item, heightMeters);

	}

}
