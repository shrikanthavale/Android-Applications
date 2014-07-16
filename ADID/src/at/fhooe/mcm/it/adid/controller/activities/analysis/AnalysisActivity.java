package at.fhooe.mcm.it.adid.controller.activities.analysis;

import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import at.fhooe.mcm.it.adid.R;
import at.fhooe.mcm.it.adid.model.utility.StoreAnalysisStateApplication;
import at.fhooe.mcm.it.adid.model.utility.StoreDesignStateApplication;
import at.fhooe.mcm.it.adid.model.utility.StoreImplementationStateApplication;
import at.fhooe.mcm.it.adid.model.utility.UtilityMethods;

/**
 * This contains analysis phase of the application. It is provided with drop
 * down for selecting of description and three questions, based on the selection
 * in single activity , interface is updated accordingly. Here all the features
 * are in locked state , and user will unlock the swipe+tab feature.
 * 
 * @author Shrikant Havale
 * 
 */
@SuppressLint("DefaultLocale")
public class AnalysisActivity extends Activity {

	/**
	 * folder name in assets directory for loading of the data
	 */
	private static final String FOLDER_NAME = "analysis";

	/**
	 * description file name in assets directory
	 */
	private static final String DESCRIPTION_FILE_NAME = "description.txt";

	/**
	 * help file name in assets directory
	 */
	private static final String HELP_FILE_NAME = "help.txt";

	// /**
	// * text file for saving notes in the internal storage
	// */
	// private static final String NOTES_FILE_INTERNAL_STORAGE = "notes.txt";
	/**
	 * description file name in assets directory
	 */
	private static final String DESCRIPTION_FILE_NAME_DE = "description_de.txt";

	/**
	 * help file name in assets directory
	 */
	private static final String HELP_FILE_NAME_DE = "help_de.txt";


	/**
	 * string variable for saving description data
	 */
	private String descriptionFileData = null;

	/**
	 * string variable for saving help file data
	 */
	private String helpFileData = null;

	/**
	 * description text view
	 */
	private TextView descriptionTextView = null;

	/**
	 * help text view
	 */
	private TextView helpTextView = null;

	/**
	 * description frame layout
	 */
	private LinearLayout descriptionFrame = null;

	/**
	 * help frame layout
	 */
	private LinearLayout helpFrame = null;

	// /**
	// * notes frame layout
	// */
	// private LinearLayout noteFrame = null;

	// /**
	// * Text for capturing notes
	// */
	// private EditText notesTaken = null;

	/**
	 * get the second image view
	 */
	private ImageView firstImageVievDescription;

	/**
	 * get the second image view
	 */
	private ImageView secondImageVievDescription;

	/**
	 * get the third image view
	 */
	private ImageView thirdImageVievDescription;

	/**
	 * button for show help
	 */
	private Button showHelp;

	/**
	 * image view for show help
	 */
	private ImageView showHelpIv;

	/**
	 * button for show details
	 */
	private Button showDetails;

	/**
	 * image view for show details
	 */
	private ImageView showDetailsIv;

	// /**
	// * button for take Notes
	// */
	// private Button showNotes;

	// /**
	// * button for save Notes
	// */
	// private Button saveNotes;
	//
	// /**
	// * button for cancel Notes
	// */
	// private Button cancelNotes;

	// /**
	// * string for storing notes read from the file
	// */
	// private String notesReadFromFileStorage = null;

	/**
	 * spinner for deciding what to show, based on selection
	 */
	private Spinner descriptionQuestionSpinner;

	/**
	 * layout containing imageview of the description
	 */
	private LinearLayout linearLayoutDescriptionAreaImages;

	/**
	 * layout containing description area
	 */
	private RelativeLayout linearLayoutDescriptionArea;

	/**
	 * layout containing imageview of the question
	 */
	private LinearLayout linearLayoutQuestionAreaImages;

	/**
	 * layout containing question area
	 */
	private RelativeLayout linearLayoutQuestionArea;

	/**
	 * question file name in assets directory
	 */
	private static final String QUESTION1_FILE_NAME = "question_1.txt";

	/**
	 * option file name in assets directory
	 */
	private static final String OPTIONS1_FILE_NAME = "option_1.txt";

	/**
	 * question file name in assets directory
	 */
	private static final String QUESTION2_FILE_NAME = "question_2.txt";

	/**
	 * option file name in assets directory
	 */
	private static final String OPTIONS2_FILE_NAME = "option_2.txt";

	/**
	 * question file name in assets directory
	 */
	private static final String QUESTION3_FILE_NAME = "question_3.txt";

	/**
	 * option file name in assets directory
	 */
	private static final String OPTIONS3_FILE_NAME = "option_3.txt";

	/**
	 * question file name in assets directory
	 */
	private static final String QUESTION1_FILE_NAME_DE = "question_1_de.txt";

	/**
	 * option file name in assets directory
	 */
	private static final String OPTIONS1_FILE_NAME_DE = "option_1_de.txt";

	/**
	 * question file name in assets directory
	 */
	private static final String QUESTION2_FILE_NAME_DE = "question_2_de.txt";

	/**
	 * option file name in assets directory
	 */
	private static final String OPTIONS2_FILE_NAME_DE = "option_2_de.txt";

	/**
	 * question file name in assets directory
	 */
	private static final String QUESTION3_FILE_NAME_DE = "question_3_de.txt";

	/**
	 * option file name in assets directory
	 */
	private static final String OPTIONS3_FILE_NAME_DE = "option_3_de.txt";

	/**
	 * string variable for saving question data
	 */
	private String questionText1 = null;

	/**
	 * list for list of options
	 */
	private List<String> listOptions1 = null;

	/**
	 * string variable for saving question data
	 */
	private String questionText2 = null;

	/**
	 * list for list of options
	 */
	private List<String> listOptions2 = null;

	/**
	 * string variable for saving question data
	 */
	private String questionText3 = null;

	/**
	 * list for list of options
	 */
	private List<String> listOptions3 = null;

	/**
	 * list for list of options
	 */
	private List<String> currentOptionPopulated = null;

	/**
	 * current question in focus
	 */
	private int currentQuestionNumber = 0;

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
	 * button for show question
	 */
	private Button showQuestionButton;

	/**
	 * image view for question button
	 */
	private ImageView showQuestionIv;

	/**
	 * button for show options
	 */
	private Button showOptionsButton;

	/**
	 * image view for options button
	 */
	private ImageView showOptionsIv;

	/**
	 * imageview for questions
	 */
	private ImageView firstImageVievQuestion;

	/**
	 * imageview for questions
	 */
	private ImageView secondImageVievQuestion;

	/**
	 * imageview for questions
	 */
	private ImageView thirdImageVievQuestion;

	/**
	 * locale for internationalization
	 */
	private Locale locale = Locale.getDefault();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_analysis);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		// get description related components
		getDescriptionRelatedComponents();

		// get the question related components
		getQuestionRelatedComponents();

		// restore previous state
		restorePreviousState();

	}

	private void getDescriptionRelatedComponents() {

		// get the all the required components
		helpFrame = ((LinearLayout) findViewById(R.id.helpframe));
		descriptionFrame = ((LinearLayout) findViewById(R.id.descriptionframe));
		// noteFrame = ((LinearLayout) findViewById(R.id.notesFrame));

		// description related linear layouts
		linearLayoutDescriptionArea = ((RelativeLayout) findViewById(R.id.descriptionlinearlayout));
		linearLayoutDescriptionAreaImages = ((LinearLayout) findViewById(R.id.descriptionImageView));

		// question related linear layouts
		linearLayoutQuestionArea = ((RelativeLayout) findViewById(R.id.questionslinearlayout));
		linearLayoutQuestionAreaImages = ((LinearLayout) findViewById(R.id.questionImageView));

		descriptionTextView = (TextView) findViewById(R.id.description_text);
		helpTextView = (TextView) findViewById(R.id.help_text);
		// notesTaken = (EditText) findViewById(R.id.noteEditText);

		descriptionTextView.setMovementMethod(new ScrollingMovementMethod());
		helpTextView.setMovementMethod(new ScrollingMovementMethod());

		// get the buttons
		showDetails = ((Button) findViewById(R.id.showdescription));
		showDetailsIv = (ImageView) findViewById(R.id.showdescription_iv);

		showHelp = ((Button) findViewById(R.id.showhelp));
		showHelpIv = (ImageView) findViewById(R.id.showhelp_iv);
		// showNotes = ((Button) findViewById(R.id.takenotes));
		// saveNotes = ((Button) findViewById(R.id.saveNotes));
		// cancelNotes = ((Button) findViewById(R.id.cancelNotes));

		// get the image view
		firstImageVievDescription = ((ImageView) findViewById(R.id.imageTab));
		secondImageVievDescription = ((ImageView) findViewById(R.id.imageVoiceRecognition));
		thirdImageVievDescription = ((ImageView) findViewById(R.id.imageVoiceInput));

		// get the spinner
		descriptionQuestionSpinner = (Spinner) findViewById(R.id.selectdescriptionquestion);

		// add button click listeners
		// help button
		showHelp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				descriptionFrame.setVisibility(View.GONE);
				// noteFrame.setVisibility(View.GONE);
				helpFrame.setVisibility(View.VISIBLE);
				showHelpIv.setSelected(true);
				showDetailsIv.setSelected(false);
				// showNotes.setSelected(false);
			}
		});

		// description button
		showDetails.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				helpFrame.setVisibility(View.GONE);
				// noteFrame.setVisibility(View.GONE);
				descriptionFrame.setVisibility(View.VISIBLE);
				showDetailsIv.setSelected(true);
				showHelpIv.setSelected(false);
				// showNotes.setSelected(false);
			}
		});

		// // take notes button
		// showNotes.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// helpFrame.setVisibility(View.GONE);
		// descriptionFrame.setVisibility(View.GONE);
		// noteFrame.setVisibility(View.VISIBLE);
		// showNotes.setSelected(true);
		// showDetails.setSelected(false);
		// showHelp.setSelected(false);
		// }
		// });

		// // save notes
		// saveNotes.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// UtilityMethods.saveNotes(AnalysisActivity.this, FOLDER_NAME,
		// NOTES_FILE_INTERNAL_STORAGE, notesTaken.getText()
		// .toString());
		// notesReadFromFileStorage = notesTaken.getText().toString();
		// }
		// });
		//
		// // clear notes
		// cancelNotes.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// // restore the previous notes
		// notesTaken.setText(notesReadFromFileStorage);
		// }
		// });

		// selection listener for spinner
		descriptionQuestionSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						String selectedItem = descriptionQuestionSpinner
								.getSelectedItem().toString();
						controlVisibilityAndAssignParameters(selectedItem);
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}

				});

		if (locale.getLanguage().equalsIgnoreCase(
				getString(R.string.germalanguagelocale))) {
			// load the description for description fragment
			descriptionFileData = UtilityMethods.loadDataFromFile(
					DESCRIPTION_FILE_NAME_DE, FOLDER_NAME, this);

			// load the help file data
			helpFileData = UtilityMethods.loadDataFromFile(HELP_FILE_NAME_DE,
					FOLDER_NAME, this);
		} else {

			// load the description for description fragment
			descriptionFileData = UtilityMethods.loadDataFromFile(
					DESCRIPTION_FILE_NAME, FOLDER_NAME, this);

			// load the help file data
			helpFileData = UtilityMethods.loadDataFromFile(HELP_FILE_NAME,
					FOLDER_NAME, this);

		}

		// // notes read from the file storage
		// notesReadFromFileStorage =
		// UtilityMethods.readNotesFromInternalStorage(
		// this, FOLDER_NAME, NOTES_FILE_INTERNAL_STORAGE);

		// set the text data
		helpTextView.setText(helpFileData);
		descriptionTextView.setText(descriptionFileData);
		// notesTaken.setText(notesReadFromFileStorage);

		// initially help view is visible
		helpFrame.setVisibility(View.VISIBLE);
		showHelpIv.setSelected(true);

	}

	private void getQuestionRelatedComponents() {

		// get the all the required components
		questionFrame = ((LinearLayout) findViewById(R.id.questionframe));
		optionsFrame = ((LinearLayout) findViewById(R.id.optionframe));
		questionTextView = (TextView) findViewById(R.id.questiontext);
		questionTextView.setMovementMethod(new ScrollingMovementMethod());
		radioButtonA = ((RadioButton) findViewById(R.id.radio_optionA));
		radioButtonB = ((RadioButton) findViewById(R.id.radio_optionB));
		radioButtonC = ((RadioButton) findViewById(R.id.radio_optionC));
		radioButtonD = ((RadioButton) findViewById(R.id.radio_optionD));
		firstImageVievQuestion = ((ImageView) findViewById(R.id.imageQuestion1));
		secondImageVievQuestion = ((ImageView) findViewById(R.id.imageQuestion2));
		thirdImageVievQuestion = ((ImageView) findViewById(R.id.imageQuestion3));
		showQuestionButton = ((Button) findViewById(R.id.showquestion));
		showOptionsButton = ((Button) findViewById(R.id.showoption));
		showQuestionIv = ((ImageView) findViewById(R.id.showquestion_iv));
		showOptionsIv = ((ImageView) findViewById(R.id.showoption_iv));
		radioGroup = ((RadioGroup) findViewById(R.id.radioGroupOptions));

		// add button click listeners
		// question button
		showQuestionButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				optionsFrame.setVisibility(View.GONE);
				questionFrame.setVisibility(View.VISIBLE);
				showQuestionIv.setSelected(true);
				showOptionsIv.setSelected(false);
			}
		});

		// options button
		showOptionsButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				questionFrame.setVisibility(View.GONE);
				optionsFrame.setVisibility(View.VISIBLE);
				showOptionsIv.setSelected(true);
				showQuestionIv.setSelected(false);
			}
		});

		// on select listeners for radio buttons
		radioButtonA.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (checkWithCorrectAnswer(currentOptionPopulated) == 1) {
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
				if (checkWithCorrectAnswer(currentOptionPopulated) == 2) {
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
				if (checkWithCorrectAnswer(currentOptionPopulated) == 3) {
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
				if (checkWithCorrectAnswer(currentOptionPopulated) == 4) {
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
				if (currentQuestionNumber == 1
						&& StoreAnalysisStateApplication.question1Answered) {
					Toast.makeText(AnalysisActivity.this,
							getString(R.string.alreadyansweredquestion),
							Toast.LENGTH_SHORT).show();
				}
				if (currentQuestionNumber == 2
						&& StoreAnalysisStateApplication.question2Answered) {
					Toast.makeText(AnalysisActivity.this,
							getString(R.string.alreadyansweredquestion),
							Toast.LENGTH_SHORT).show();
				}
				if (currentQuestionNumber == 3
						&& StoreAnalysisStateApplication.question3Answered) {
					Toast.makeText(AnalysisActivity.this,
							getString(R.string.alreadyansweredquestion),
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		if (locale.getLanguage().equalsIgnoreCase(
				getString(R.string.germalanguagelocale))) {

			// load the question for question fragment
			questionText1 = UtilityMethods.loadDataFromFile(
					QUESTION1_FILE_NAME_DE, FOLDER_NAME, this);
			listOptions1 = UtilityMethods.loadOptionListFromFile(
					OPTIONS1_FILE_NAME_DE, FOLDER_NAME, this);

			// load the question for question fragment
			questionText2 = UtilityMethods.loadDataFromFile(
					QUESTION2_FILE_NAME_DE, FOLDER_NAME, this);
			listOptions2 = UtilityMethods.loadOptionListFromFile(
					OPTIONS2_FILE_NAME_DE, FOLDER_NAME, this);

			// load the question for question fragment
			questionText3 = UtilityMethods.loadDataFromFile(
					QUESTION3_FILE_NAME_DE, FOLDER_NAME, this);
			listOptions3 = UtilityMethods.loadOptionListFromFile(
					OPTIONS3_FILE_NAME_DE, FOLDER_NAME, this);

		}else{


			// load the question for question fragment
			questionText1 = UtilityMethods.loadDataFromFile(
					QUESTION1_FILE_NAME, FOLDER_NAME, this);
			listOptions1 = UtilityMethods.loadOptionListFromFile(
					OPTIONS1_FILE_NAME, FOLDER_NAME, this);

			// load the question for question fragment
			questionText2 = UtilityMethods.loadDataFromFile(
					QUESTION2_FILE_NAME, FOLDER_NAME, this);
			listOptions2 = UtilityMethods.loadOptionListFromFile(
					OPTIONS2_FILE_NAME, FOLDER_NAME, this);

			// load the question for question fragment
			questionText3 = UtilityMethods.loadDataFromFile(
					QUESTION3_FILE_NAME, FOLDER_NAME, this);
			listOptions3 = UtilityMethods.loadOptionListFromFile(
					OPTIONS3_FILE_NAME, FOLDER_NAME, this);

		}

		// initially help view is visible
		questionFrame.setVisibility(View.VISIBLE);
		showQuestionIv.setSelected(true);

	}

	/**
	 * populate the options with data
	 */
	private void populateQuestionOptions(List<String> tempList) {

		// set them to radio options
		radioButtonA.setText(tempList.get(0));
		radioButtonB.setText(tempList.get(1));
		radioButtonC.setText(tempList.get(2));
		radioButtonD.setText(tempList.get(3));

	}

	/**
	 * populate the question with data
	 */
	private void populateQuestion(String tempQuestionText) {

		// set them to radio options
		questionTextView.setText(tempQuestionText);

	}

	/**
	 * correct answer is stored in options file of question at the end of file,
	 * so last options in list is our answer
	 * 
	 * @param list
	 *            of options where last item in list is the answer
	 * 
	 * @return integer number of correct option
	 */
	private int checkWithCorrectAnswer(List<String> listOptions) {
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
				AnalysisActivity.this);
	}

	/**
	 * method for showing incorrect answer dialogue box
	 */
	private void showCorrectAnswerDialogueBox() {

		updateImageView(currentQuestionNumber);

		// check if all other questions are answered
		if (StoreAnalysisStateApplication.stageCleared) {
			// this call is specifically for updating fragment in description
			// tab

			String _title = getString(R.string.levelcleared);
			String _message = getString(R.string.analysislevelclearedmessage);
			UtilityMethods.createAlertDialog(_title, _message,
					AnalysisActivity.this);

		} else {

			String _title = getString(R.string.correctansweralertdialoguetitle);
			String _message = getString(R.string.correctansweralertdialoguemessage);
			UtilityMethods.createAlertDialog(_title, _message,
					AnalysisActivity.this);
		}

		disableRadioOptions();
	}

	/**
	 * update the corresponding image view to represent answer correctly
	 * 
	 * @param currentQuestionAnswered
	 *            which question was answered
	 */
	private void updateImageView(int currentQuestionAnswered) {

		// in order to restore state set this variable true, indicating that
		// question answered correctly, so next time
		// user comes in again, he doesn't have to answer the question again
		if (currentQuestionAnswered == 1) {
			firstImageVievQuestion
					.setImageResource(R.drawable.swipegestureenabled);
			StoreAnalysisStateApplication.question1Answered = true;
			SharedPreferences prefs1 = PreferenceManager
					.getDefaultSharedPreferences(getApplicationContext());
			Editor editor1 = prefs1.edit();
			editor1.putBoolean(getString(R.string.analysis_quiz_1_answered),
					true);
			editor1.commit();
		} else if (currentQuestionAnswered == 2) {
			secondImageVievQuestion
					.setImageResource(R.drawable.swipegestureenabled);
			StoreAnalysisStateApplication.question2Answered = true;
			SharedPreferences prefs1 = PreferenceManager
					.getDefaultSharedPreferences(getApplicationContext());
			Editor editor1 = prefs1.edit();
			editor1.putBoolean(getString(R.string.analysis_quiz_2_answered),
					true);
			editor1.commit();
		} else if (currentQuestionAnswered == 3) {
			thirdImageVievQuestion
					.setImageResource(R.drawable.swipegestureenabled);
			StoreAnalysisStateApplication.question3Answered = true;
			SharedPreferences prefs1 = PreferenceManager
					.getDefaultSharedPreferences(getApplicationContext());
			Editor editor1 = prefs1.edit();
			editor1.putBoolean(getString(R.string.analysis_quiz_3_answered),
					true);
			editor1.commit();
		}

		if (StoreAnalysisStateApplication.question1Answered
				&& StoreAnalysisStateApplication.question2Answered
				&& StoreAnalysisStateApplication.question3Answered) {
			firstImageVievDescription
					.setImageResource(R.drawable.swipegestureenabled);
			StoreAnalysisStateApplication.stageCleared = true;
			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(getApplicationContext());
			Editor editor = prefs.edit();
			editor.putBoolean(getString(R.string.analysis_quiz_cleared), true);
			editor.commit();
		}

	}

	/**
	 * Radio Options are to be disabled once the question is answered , not
	 * allowing him to answer again, unless he resets the settings
	 */
	private void disableRadioOptions() {
		int correctAnswer = checkWithCorrectAnswer(currentOptionPopulated);
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

	/**
	 * Radio Options are to be enabled if the question is not answered ,
	 * allowing allowing him to answer again, after he resets the settings
	 */
	private void enableRadioOptions() {
		radioButtonA.setEnabled(true);
		radioButtonB.setEnabled(true);
		radioButtonC.setEnabled(true);
		radioButtonD.setEnabled(true);
		radioButtonA.setChecked(false);
		radioButtonB.setChecked(false);
		radioButtonC.setChecked(false);
		radioButtonD.setChecked(false);
	}

	/**
	 * restore previous state of the analysis phase , if the user clicks back
	 * buttons and comes back
	 * 
	 */
	private void restorePreviousState() {

		if (StoreAnalysisStateApplication.question1Answered) {
			firstImageVievQuestion
					.setImageResource(R.drawable.swipegestureenabled);
		}

		if (StoreAnalysisStateApplication.question2Answered) {
			secondImageVievQuestion
					.setImageResource(R.drawable.swipegestureenabled);
		}

		if (StoreAnalysisStateApplication.question3Answered) {
			thirdImageVievQuestion
					.setImageResource(R.drawable.swipegestureenabled);
		}

		if (StoreAnalysisStateApplication.stageCleared) {
			firstImageVievDescription
					.setImageResource(R.drawable.swipegestureenabled);
		}

		if (StoreDesignStateApplication.stageCleared) {
			secondImageVievDescription
					.setImageResource(R.drawable.voicereadingenabled);
		}

		if (StoreImplementationStateApplication.stageCleared) {
			thirdImageVievDescription
					.setImageResource(R.drawable.microphonenabled);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.analysis, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@SuppressLint("DefaultLocale")
	private void controlVisibilityAndAssignParameters(String selectedItem) {

		if (selectedItem == null
				|| selectedItem.toLowerCase().equals(
						getResources().getStringArray(
								(R.array.analysisdescriptionquestionsarray))[0]
								.toLowerCase())) {

			// let the default visibility of description remain.
			linearLayoutDescriptionArea.setVisibility(View.VISIBLE);
			linearLayoutDescriptionAreaImages.setVisibility(View.VISIBLE);
			linearLayoutQuestionArea.setVisibility(View.GONE);
			linearLayoutQuestionAreaImages.setVisibility(View.GONE);

		} else if (selectedItem.toLowerCase().equals(
				getResources().getStringArray(
						(R.array.analysisdescriptionquestionsarray))[1]
						.toLowerCase())) {
			linearLayoutQuestionArea.setVisibility(View.VISIBLE);
			linearLayoutQuestionAreaImages.setVisibility(View.VISIBLE);
			linearLayoutDescriptionArea.setVisibility(View.GONE);
			linearLayoutDescriptionAreaImages.setVisibility(View.GONE);

			// load the option file data
			populateQuestionOptions(listOptions1);

			// set the text data
			populateQuestion(questionText1);

			currentOptionPopulated = listOptions1;
			currentQuestionNumber = 1;

			if (StoreAnalysisStateApplication.question1Answered) {
				disableRadioOptions();
			} else {
				enableRadioOptions();
			}
			// initially question view is visible
			questionFrame.setVisibility(View.VISIBLE);
			optionsFrame.setVisibility(View.GONE);
			showQuestionIv.setSelected(true);
			showOptionsIv.setSelected(false);
		} else if (selectedItem.toLowerCase().equals(
				getResources().getStringArray(
						(R.array.analysisdescriptionquestionsarray))[2]
						.toLowerCase())) {
			linearLayoutQuestionArea.setVisibility(View.VISIBLE);
			linearLayoutQuestionAreaImages.setVisibility(View.VISIBLE);
			linearLayoutDescriptionArea.setVisibility(View.GONE);
			linearLayoutDescriptionAreaImages.setVisibility(View.GONE);

			// load the option file data
			populateQuestionOptions(listOptions2);

			// set the text data
			populateQuestion(questionText2);

			currentOptionPopulated = listOptions2;
			currentQuestionNumber = 2;

			if (StoreAnalysisStateApplication.question2Answered) {
				disableRadioOptions();
			} else {
				enableRadioOptions();
			}
			// initially question view is visible
			questionFrame.setVisibility(View.VISIBLE);
			optionsFrame.setVisibility(View.GONE);
			showQuestionIv.setSelected(true);
			showOptionsIv.setSelected(false);
		} else if (selectedItem.toLowerCase().equals(
				getResources().getStringArray(
						(R.array.analysisdescriptionquestionsarray))[3]
						.toLowerCase())) {
			linearLayoutQuestionArea.setVisibility(View.VISIBLE);
			linearLayoutQuestionAreaImages.setVisibility(View.VISIBLE);
			linearLayoutDescriptionArea.setVisibility(View.GONE);
			linearLayoutDescriptionAreaImages.setVisibility(View.GONE);

			// load the option file data
			populateQuestionOptions(listOptions3);

			// set the text data
			populateQuestion(questionText3);

			currentOptionPopulated = listOptions3;
			currentQuestionNumber = 3;

			if (StoreAnalysisStateApplication.question3Answered) {
				disableRadioOptions();
			} else {
				enableRadioOptions();
			}
			// initially question view is visible
			questionFrame.setVisibility(View.VISIBLE);
			optionsFrame.setVisibility(View.GONE);
			showQuestionIv.setSelected(true);
			showOptionsIv.setSelected(false);
		}

	}
}
