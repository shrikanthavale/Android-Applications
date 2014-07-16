package at.fhooe.mcm.it.adid.controller.activities.design;

import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import at.fhooe.mcm.it.adid.R;
import at.fhooe.mcm.it.adid.model.utility.FragmentCommunicationInterface;
import at.fhooe.mcm.it.adid.model.utility.StoreDesignStateApplication;
import at.fhooe.mcm.it.adid.model.utility.UtilityMethods;

/**
 * This contains one section of Design. Level 3 of the design phase involves
 * answering the question 3. Based on the correct answer users moves one step
 * closer to the unlocking feature.
 * 
 * @author Shrikant Havale
 * 
 */
public class DesignQuestion3Fragment extends Fragment {

	/**
	 * folder name in assets directory for loading of the data
	 */
	private static final String FOLDER_NAME = "design";

	/**
	 * question file name in assets directory
	 */
	private static final String QUESTION_FILE_NAME = "question_3.txt";

	/**
	 * option file name in assets directory
	 */
	private static final String OPTIONS_FILE_NAME = "option_3.txt";

	/**
	 * question file name in assets directory
	 */
	private static final String QUESTION_FILE_NAME_DE = "question_3_de.txt";

	/**
	 * option file name in assets directory
	 */
	private static final String OPTIONS_FILE_NAME_DE = "option_3_de.txt";

	/**
	 * string variable for saving question data
	 */
	private String questionText = null;

	/**
	 * question text view
	 */
	private TextView questionTextView = null;

	/**
	 * question frame layout
	 */
	private LinearLayout questionFrame = null;

	/**
	 * options frame layout
	 */
	private LinearLayout optionsFrame = null;

	/**
	 * list for list of options
	 */
	private List<String> listOptions = null;

	/**
	 * radio option A
	 */
	private RadioButton radioButtonA;

	/**
	 * radio option B
	 */
	private RadioButton radioButtonB;

	/**
	 * radio option C
	 */
	private RadioButton radioButtonC;

	/**
	 * radio option D
	 */
	private RadioButton radioButtonD;

	/**
	 * radio option group, containing all the radio buttons
	 */
	private RadioGroup radioGroup;

	/**
	 * get the first image view
	 */
	private ImageView firstImageViev;

	/**
	 * get the second image view
	 */
	private ImageView secondImageViev;

	/**
	 * get the third image view
	 */
	private ImageView thirdImageViev;

	/**
	 * button for show question
	 */
	private Button showQuestionButton;

	/**
	 * image view for show question
	 */
	private ImageView showQuestionButtonIv;

	/**
	 * button for show options
	 */
	private Button showOptionsButton;

	/**
	 * image view for show options
	 */
	private ImageView showOptionsButtonIv;

	/**
	 * communication interface to communicate to activity
	 */
	private FragmentCommunicationInterface communicationInterface;

	/**
	 * locale for internationalization
	 */
	private Locale locale = Locale.getDefault();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_design_question_3,
				container, false);

		// get the all the required components
		questionFrame = ((LinearLayout) rootView
				.findViewById(R.id.questionframe));
		optionsFrame = ((LinearLayout) rootView.findViewById(R.id.optionframe));
		questionTextView = (TextView) rootView.findViewById(R.id.questiontext);
		questionTextView.setMovementMethod(new ScrollingMovementMethod());
		radioButtonA = ((RadioButton) rootView.findViewById(R.id.radio_optionA));
		radioButtonB = ((RadioButton) rootView.findViewById(R.id.radio_optionB));
		radioButtonC = ((RadioButton) rootView.findViewById(R.id.radio_optionC));
		radioButtonD = ((RadioButton) rootView.findViewById(R.id.radio_optionD));
		firstImageViev = ((ImageView) rootView
				.findViewById(R.id.imageTextSpeech1));
		secondImageViev = ((ImageView) rootView
				.findViewById(R.id.imageTextSpeech2));
		thirdImageViev = ((ImageView) rootView
				.findViewById(R.id.imageTextSpeech3));
		showQuestionButton = ((Button) rootView.findViewById(R.id.showquestion));
		showOptionsButton = ((Button) rootView.findViewById(R.id.showoption));
		showQuestionButtonIv = ((ImageView) rootView
				.findViewById(R.id.showquestion_iv));
		showOptionsButtonIv = ((ImageView) rootView
				.findViewById(R.id.showoption_iv));
		radioGroup = ((RadioGroup) rootView
				.findViewById(R.id.radioGroupOptions));

		// add button click listeners
		// question button
		showQuestionButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				optionsFrame.setVisibility(View.GONE);
				questionFrame.setVisibility(View.VISIBLE);
				showQuestionButtonIv.setSelected(true);
				showOptionsButtonIv.setSelected(false);
			}
		});

		// options button
		showOptionsButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				populateQuestionOptions();
				questionFrame.setVisibility(View.GONE);
				optionsFrame.setVisibility(View.VISIBLE);
				showOptionsButtonIv.setSelected(true);
				showQuestionButtonIv.setSelected(false);
			}
		});

		// on select listeners for radio buttons
		radioButtonA.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (checkWithCorrectAnswer() == 1) {
					showCorrectAnswerDialogueBox();
				} else {
					showIncorrectAnswerDialogueBox();

				}
			}
		});

		// on select listeners for radio buttons
		radioButtonB.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (checkWithCorrectAnswer() == 2) {
					showCorrectAnswerDialogueBox();
				} else {
					showIncorrectAnswerDialogueBox();
				}
			}
		});

		// on select listeners for radio buttons
		radioButtonC.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (checkWithCorrectAnswer() == 3) {
					showCorrectAnswerDialogueBox();
				} else {
					showIncorrectAnswerDialogueBox();
				}
			}
		});

		// on select listeners for radio buttons
		radioButtonD.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (checkWithCorrectAnswer() == 4) {
					showCorrectAnswerDialogueBox();
				} else {
					showIncorrectAnswerDialogueBox();
				}
			}
		});

		// add the listener to the radio group
		radioGroup.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (StoreDesignStateApplication.question3Answered) {
					Toast.makeText(DesignQuestion3Fragment.this.getActivity(),
							getString(R.string.alreadyansweredquestion),
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		if (locale.getLanguage().equalsIgnoreCase(
				getString(R.string.germalanguagelocale))) {
			// load the question for question fragment
			questionText = UtilityMethods.loadDataFromFile(
					QUESTION_FILE_NAME_DE, FOLDER_NAME, this.getActivity());
			listOptions = UtilityMethods.loadOptionListFromFile(
					OPTIONS_FILE_NAME_DE, FOLDER_NAME, this.getActivity());
		} else {

			// load the question for question fragment
			questionText = UtilityMethods.loadDataFromFile(QUESTION_FILE_NAME,
					FOLDER_NAME, this.getActivity());
			listOptions = UtilityMethods.loadOptionListFromFile(
					OPTIONS_FILE_NAME, FOLDER_NAME, this.getActivity());

		}

		// load the option file data
		populateQuestionOptions();

		// set the text data
		questionTextView.setText(questionText);
		showQuestionButtonIv.setSelected(true);

		// initially help view is visible
		questionFrame.setVisibility(View.VISIBLE);

		// try to restore the previous state of this phase,
		restorePreviousState();

		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof FragmentCommunicationInterface) {
			communicationInterface = (FragmentCommunicationInterface) activity;
		} else {
			throw new ClassCastException(activity.toString()
					+ " must implemenet FragmentCommunicationInterface");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		communicationInterface = null;
	}

	/**
	 * populate the options with data
	 */
	private void populateQuestionOptions() {

		// set them to radio options
		radioButtonA.setText(listOptions.get(0));
		radioButtonB.setText(listOptions.get(1));
		radioButtonC.setText(listOptions.get(2));
		radioButtonD.setText(listOptions.get(3));

	}

	/**
	 * correct answer is stored in options file of question at the end of file,
	 * so last options in list is our answer
	 * 
	 * @return integer number of correct option
	 */
	private int checkWithCorrectAnswer() {
		// return the correct answer from last entry in the list options file
		return Integer.parseInt(listOptions.get(listOptions.size() - 1).split(
				":")[1]);

	}

	/**
	 * method for showing incorrect answer dialogue box
	 */
	private void showIncorrectAnswerDialogueBox() {
		String _title = getString(R.string.incorrectansweralertdialoguetitle);
		String _message = getString(R.string.incorrectansweralertdialoguemessage);
		UtilityMethods.createAlertDialog(_title, _message,
				DesignQuestion3Fragment.this.getActivity());
	}

	/**
	 * method for showing incorrect answer dialogue box
	 */
	private void showCorrectAnswerDialogueBox() {

		// in order to restore state set this variable true, indicating that
		// question answered correctly, so next time
		// user comes in again, he doesn't have to answer the question again
		StoreDesignStateApplication.question3Answered = true;
		SharedPreferences prefs1 = PreferenceManager
				.getDefaultSharedPreferences(getActivity()
						.getApplicationContext());
		Editor editor1 = prefs1.edit();
		editor1.putBoolean(getString(R.string.design_quiz_3_answered), true);
		editor1.commit();

		// check if all other questions are answered
		if (StoreDesignStateApplication.question1Answered
				&& StoreDesignStateApplication.question2Answered) {
			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(getActivity()
							.getApplicationContext());
			Editor editor = prefs.edit();
			editor.putBoolean(getString(R.string.design_quiz_cleared), true);
			editor.commit();
			// this call is specifically for updating fragment in description
			// tab
			communicationInterface.notifyDesciptionFragmentsUpdateImageView();

			String _title = getString(R.string.levelcleared);
			String _message = getString(R.string.designlevelclearedmessage);
			UtilityMethods.createAlertDialog(_title, _message,
					DesignQuestion3Fragment.this.getActivity());

		} else {

			String _title = getString(R.string.correctansweralertdialoguetitle);
			String _message = getString(R.string.correctansweralertdialoguemessage);
			UtilityMethods.createAlertDialog(_title, _message,
					DesignQuestion3Fragment.this.getActivity());
		}

		disableRadioOptions();
		updateImageView();
		communicationInterface.notifyOtherFragmentsUpdateImageView(3);
	}

	/**
	 * update the corresponding image view to represent answer correctly
	 */
	private void updateImageView() {
		thirdImageViev.setImageResource(R.drawable.voicereadingenabled);
	}

	/**
	 * Updates the image view because answer in some other fragment was given
	 * correctly.
	 * 
	 * @param imageViewNumber
	 *            which image view to update
	 */
	public void updateImageViewFromOtherFragment(int imageViewNumber) {

		if (imageViewNumber == 1) {
			firstImageViev.setImageResource(R.drawable.voicereadingenabled);
		} else if (imageViewNumber == 2) {
			secondImageViev.setImageResource(R.drawable.voicereadingenabled);
		} else if (imageViewNumber == 3) {
			thirdImageViev.setImageResource(R.drawable.voicereadingenabled);
		}
	}

	/**
	 * Try to restore previous state of the application using static variables.
	 * See if user has already answered the question , try to restore that fact
	 * 
	 */
	private void restorePreviousState() {
		if (StoreDesignStateApplication.question1Answered) {
			firstImageViev.setImageResource(R.drawable.voicereadingenabled);
		}
		if (StoreDesignStateApplication.question2Answered) {
			secondImageViev.setImageResource(R.drawable.voicereadingenabled);
		}
		if (StoreDesignStateApplication.question3Answered) {
			thirdImageViev.setImageResource(R.drawable.voicereadingenabled);
			disableRadioOptions();
		}
	}

	/**
	 * Radio Options are to be disabled once the question is answered , not
	 * allowing him to answer again, unless he resets the settings
	 */
	private void disableRadioOptions() {
		int correctAnswer = checkWithCorrectAnswer();
		switch (correctAnswer) {
		case 1:
			radioButtonA.setChecked(true);
			break;
		case 2:
			radioButtonB.setChecked(true);
			break;
		case 3:
			radioButtonC.setChecked(true);
			break;
		case 4:
			radioButtonD.setChecked(true);
			break;
		default:
			break;
		}
		radioButtonA.setEnabled(false);
		radioButtonB.setEnabled(false);
		radioButtonC.setEnabled(false);
		radioButtonD.setEnabled(false);
	}

}