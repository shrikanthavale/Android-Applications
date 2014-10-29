package at.fhooe.mcm.it.adid.controller.activities.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
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
import at.fhooe.mcm.it.adid.model.utility.StoreImplementationStateApplication;
import at.fhooe.mcm.it.adid.model.utility.UtilityMethods;

/**
 * This contains one section of Implementation. Level 1 of the implementation
 * phase involves answering the question . Based on the correct answer users
 * moves one step closer to the unlocking feature. Here the feature of voice
 * reading is implemented
 * 
 * @author Shrikant Havale
 * 
 */
public class ImplementationQuestion1Fragment extends Fragment implements
		TextToSpeech.OnInitListener {

	/**
	 * application name folder
	 */
	private static final String APPLICATION_FOLDER_NAME = "ADID";

	/**
	 * folder name in assets directory for loading of the data
	 */
	private static final String FOLDER_NAME = "implementation";

	/**
	 * question file name in assets directory
	 */
	private static final String QUESTION_FILE_NAME = "question_1.txt";

	/**
	 * option file name in assets directory
	 */
	private static final String OPTIONS_FILE_NAME = "option_1.txt";

	/**
	 * wav file name in internal storage for storing audio
	 */
	private static final String AUDIO_QUESTION_FILE_NAME = "question_1.mp3";

	/**
	 * wav file name storing in internal storage
	 */
	private static final String AUDIO_OPTION_FILE_NAME = "options_1.mp3";

	/**
	 * question file name in assets directory
	 */
	private static final String QUESTION_FILE_NAME_DE = "question_1_de.txt";

	/**
	 * option file name in assets directory
	 */
	private static final String OPTIONS_FILE_NAME_DE = "option_1_de.txt";

	/**
	 * wav file name in internal storage for storing audio
	 */
	private static final String AUDIO_QUESTION_FILE_NAME_DE = "question_1_de.mp3";

	/**
	 * wav file name storing in internal storage
	 */
	private static final String AUDIO_OPTION_FILE_NAME_DE = "options_1_de.mp3";

	/**
	 * Utterance ID
	 */
	private static final String UTTERANCE_ID_SYNTHESIZE = "synthesize";

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
	 * locale for internationalization
	 */
	private Locale locale = Locale.getDefault();

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
	 * button for show options
	 */
	private Button showOptionsButton;

	/**
	 * button for speak out button
	 */
	private Button speakOutButton;
	
	/**
	 * image view for show question
	 */
	private ImageView showQuestionButtonIv;
	
	/**
	 * image view for show options
	 */
	private ImageView showOptionsButtonIv;
	
	/**
	 * image view for speak out button
	 */
	private ImageView speakOutButtonIv;

	/**
	 * communication interface to communicate to activity
	 */
	private FragmentCommunicationInterface communicationInterface;

	/**
	 * Text to speech API
	 */
	private TextToSpeech textToSpeech;

	/**
	 * Media player
	 */
	private MediaPlayer mediaPlayerQuestion;

	/**
	 * Media player
	 */
	private MediaPlayer mediaPlayerOptions;

	/**
	 * boolean variable to check if mediaplayer is initialized or not
	 */
	private boolean mediaPlayersInitialized = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_implementation_question_1, container, false);

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
		speakOutButton = ((Button) rootView.findViewById(R.id.readText));
		
		showQuestionButtonIv = ((ImageView) rootView.findViewById(R.id.showquestion_iv));
		showOptionsButtonIv = ((ImageView) rootView.findViewById(R.id.showoption_iv));
		speakOutButtonIv = ((ImageView) rootView.findViewById(R.id.readText_iv));
		
		radioGroup = ((RadioGroup) rootView
				.findViewById(R.id.radioGroupOptions));

		// Creating an instance of MediaPlayer
		mediaPlayerQuestion = new MediaPlayer();
		mediaPlayerOptions = new MediaPlayer();

		// set on completion listener to check if playing was completed
		mediaPlayerOptions.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				speakOutButton.setText(getString(R.string.playbuttontext));
				speakOutButtonIv.setSelected(false);
			}
		});

		mediaPlayerQuestion.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				speakOutButton.setText(getString(R.string.playbuttontext));
				speakOutButtonIv.setSelected(false);
			}
		});

		// add button click listeners
		// question button
		showQuestionButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				optionsFrame.setVisibility(View.GONE);
				questionFrame.setVisibility(View.VISIBLE);
				showQuestionButtonIv.setSelected(true);
				showOptionsButtonIv.setSelected(false);
				speakOutButtonIv.setSelected(false);
				if (mediaPlayerOptions.isPlaying()) {
					mediaPlayerOptions.pause();
					speakOutButton.setText(ImplementationQuestion1Fragment.this
							.getString(R.string.playbuttontext));
				}
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
				speakOutButtonIv.setSelected(false);
				if (mediaPlayerQuestion.isPlaying()) {
					mediaPlayerQuestion.pause();
					speakOutButton.setText(ImplementationQuestion1Fragment.this
							.getString(R.string.playbuttontext));
				}
			}
		});

		// read text button
		speakOutButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if (!mediaPlayersInitialized) {
					initializeMediaPlayer();
				}

				if (speakOutButton
						.getText()
						.toString()
						.equals(ImplementationQuestion1Fragment.this
								.getString(R.string.playbuttontext))) {
					speakOutButtonIv.setSelected(true);
					speakOutButton.setText(ImplementationQuestion1Fragment.this
							.getString(R.string.pausebuttontext));
					readTextAloud();
				} else {
					speakOutButtonIv.setSelected(false);
					speakOutButton.setText(ImplementationQuestion1Fragment.this
							.getString(R.string.playbuttontext));
					pauseTextAloud();
				}

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
				if (StoreImplementationStateApplication.question1Answered) {
					Toast.makeText(
							ImplementationQuestion1Fragment.this.getActivity(),
							getString(R.string.alreadyansweredquestion),
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		if (locale.getLanguage().equalsIgnoreCase(getString(R.string.germalanguagelocale))) {
			// load the question for question fragment
			questionText = UtilityMethods.loadDataFromFile(QUESTION_FILE_NAME_DE,
					FOLDER_NAME, this.getActivity());
			listOptions = UtilityMethods.loadOptionListFromFile(
					OPTIONS_FILE_NAME_DE, FOLDER_NAME, this.getActivity());
		}else{
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

		// initially help view is visible
		questionFrame.setVisibility(View.VISIBLE);
		showQuestionButtonIv.setSelected(true);

		// check for TTS installed or not
		checkTextToSpeechInstalled();

		// try to restore the previous state of this phase,
		restorePreviousState();

		return rootView;
	}

	
	private void synthesizeTextStoreExternalMemory() {

		HashMap<String, String> synthesizeMap = new HashMap<String, String>();
		synthesizeMap.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,
				UTTERANCE_ID_SYNTHESIZE);

		// get the external storage path
		String exStoragePath = Environment.getExternalStorageDirectory()
				.getAbsolutePath();

		// complete path
		File appTmpPath = new File(exStoragePath + "/"
				+ APPLICATION_FOLDER_NAME + "/" + FOLDER_NAME);

		// create directory if it doesn't exists
		if (!appTmpPath.exists()) {
			appTmpPath.mkdirs();
		}

		if (locale.getLanguage().equalsIgnoreCase("de")) {

			File filePathQuestion = new File(appTmpPath.getAbsoluteFile() + "/"
					+ AUDIO_QUESTION_FILE_NAME_DE);
			File filePathOptions = new File(appTmpPath.getAbsoluteFile() + "/"
					+ AUDIO_OPTION_FILE_NAME_DE);

			if (!filePathQuestion.exists()) {
				Toast.makeText(
						getActivity(),
						getActivity().getString(
								R.string.mediaplayerinitializationwait),
						Toast.LENGTH_LONG).show();
				speakOutButton.setEnabled(false);
				textToSpeech.synthesizeToFile(questionText, synthesizeMap,
						filePathQuestion.getAbsolutePath());
			}

			if (!filePathOptions.exists()) {
				// read all the options

				String textRead = getString(R.string.optionAtext) + ","
						+ listOptions.get(0) + ","
						+ getString(R.string.optionBtext) + ","
						+ listOptions.get(1) + ","
						+ getString(R.string.optionCtext) + ","
						+ listOptions.get(2) + ","
						+ getString(R.string.optionDtext) + ","
						+ listOptions.get(3);

				textToSpeech.synthesizeToFile(textRead, null,
						filePathOptions.getAbsolutePath());
			}

		} else {

			File filePathQuestion = new File(appTmpPath.getAbsoluteFile() + "/"
					+ AUDIO_QUESTION_FILE_NAME);
			File filePathOptions = new File(appTmpPath.getAbsoluteFile() + "/"
					+ AUDIO_OPTION_FILE_NAME);

			if (!filePathQuestion.exists()) {
				Toast.makeText(
						getActivity(),
						getActivity().getString(
								R.string.mediaplayerinitializationwait),
						Toast.LENGTH_LONG).show();
				speakOutButton.setEnabled(false);
				textToSpeech.synthesizeToFile(questionText, synthesizeMap,
						filePathQuestion.getAbsolutePath());
			}

			if (!filePathOptions.exists()) {
				// read all the options

				String textRead = getString(R.string.optionAtext) + ","
						+ listOptions.get(0) + ","
						+ getString(R.string.optionBtext) + ","
						+ listOptions.get(1) + ","
						+ getString(R.string.optionCtext) + ","
						+ listOptions.get(2) + ","
						+ getString(R.string.optionDtext) + ","
						+ listOptions.get(3);

				textToSpeech.synthesizeToFile(textRead, null,
						filePathOptions.getAbsolutePath());
			}

		}

	}

	private void initializeMediaPlayer() {

		try {
			File baseDir = Environment.getExternalStorageDirectory();

			if (locale.getLanguage().equalsIgnoreCase(getString(R.string.germalanguagelocale))) {
			
			
			String audioQuestionPath = baseDir.getAbsolutePath() + "/"
					+ APPLICATION_FOLDER_NAME + "/" + FOLDER_NAME + "/"
					+ AUDIO_QUESTION_FILE_NAME_DE;
			FileInputStream fileInputStreamQuestion = new FileInputStream(
					audioQuestionPath);

			String audioOptionPath = baseDir.getAbsolutePath() + "/"
					+ APPLICATION_FOLDER_NAME + "/" + FOLDER_NAME + "/"
					+ AUDIO_OPTION_FILE_NAME_DE;
			FileInputStream fileInputStreamOption = new FileInputStream(
					audioOptionPath);
			
			mediaPlayerQuestion.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayerQuestion.setDataSource(fileInputStreamQuestion.getFD());
			mediaPlayerQuestion.prepare();

			mediaPlayerOptions.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayerOptions.setDataSource(fileInputStreamOption.getFD());
			mediaPlayerOptions.prepare();

			mediaPlayersInitialized = true;

			fileInputStreamQuestion.close();
			fileInputStreamOption.close();
			
			}else{
				
				String audioQuestionPath = baseDir.getAbsolutePath() + "/"
						+ APPLICATION_FOLDER_NAME + "/" + FOLDER_NAME + "/"
						+ AUDIO_QUESTION_FILE_NAME;
				FileInputStream fileInputStreamQuestion = new FileInputStream(
						audioQuestionPath);

				String audioOptionPath = baseDir.getAbsolutePath() + "/"
						+ APPLICATION_FOLDER_NAME + "/" + FOLDER_NAME + "/"
						+ AUDIO_OPTION_FILE_NAME;
				FileInputStream fileInputStreamOption = new FileInputStream(
						audioOptionPath);
				
				mediaPlayerQuestion.setAudioStreamType(AudioManager.STREAM_MUSIC);
				mediaPlayerQuestion.setDataSource(fileInputStreamQuestion.getFD());
				mediaPlayerQuestion.prepare();

				mediaPlayerOptions.setAudioStreamType(AudioManager.STREAM_MUSIC);
				mediaPlayerOptions.setDataSource(fileInputStreamOption.getFD());
				mediaPlayerOptions.prepare();

				mediaPlayersInitialized = true;

				fileInputStreamQuestion.close();
				fileInputStreamOption.close();
			}

		} catch (Exception e) {

			// disable the button
			speakOutButton.setEnabled(false);

			Toast.makeText(
					getActivity(),
					getActivity().getString(
							R.string.mediaplayerinitializationfailed),
					Toast.LENGTH_SHORT).show();

		}
	}

	private void checkTextToSpeechInstalled() {

		// get the package manager
		PackageManager pm = this.getActivity().getPackageManager();

		// get the intent
		Intent checkIntent = new Intent();

		// check for TTS
		checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);

		// check for intent preset
		ResolveInfo resolveInfo = pm.resolveActivity(checkIntent,
				PackageManager.MATCH_DEFAULT_ONLY);

		if (resolveInfo == null) {
			// Not able to find the activity which should be started for this
			// intent
			Toast.makeText(this.getActivity(),
					getString(R.string.texttospeechdisablederrormessage),
					Toast.LENGTH_SHORT).show();
			speakOutButton.setEnabled(false);
		} else {
			startActivityForResult(checkIntent, 0);
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0) {
			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
				// initialize text to speech
				textToSpeech = new TextToSpeech(this.getActivity(), this);

			} else {
				// missing data, install it
				Intent installIntent = new Intent();
				installIntent
						.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(installIntent);
			}
		}
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

	@Override
	public void onDestroy() {
		if (textToSpeech != null) {
			textToSpeech.stop();
			textToSpeech.shutdown();
		}
		if (mediaPlayerQuestion != null) {
			mediaPlayerOptions.stop();
			mediaPlayerQuestion.stop();
			mediaPlayerOptions.release();
			mediaPlayerQuestion.release();
		}
		super.onDestroy();
	}

	@Override
	public void onInit(int initStatus) {

		// check for successful instantiation
		if (initStatus == TextToSpeech.SUCCESS) {
			textToSpeech
					.setOnUtteranceCompletedListener(new OnUtteranceCompletedListener() {
						@Override
						public void onUtteranceCompleted(
								final String utteranceId) {

							ImplementationQuestion1Fragment.this.getActivity()
									.runOnUiThread(new Runnable() {
										@Override
										public void run() {
											if (utteranceId
													.equals(UTTERANCE_ID_SYNTHESIZE)) {
												// set the message
												Toast.makeText(
														getActivity(),
														getActivity()
																.getString(
																		R.string.mediaplayerinitializationwaitstop),
														Toast.LENGTH_LONG)
														.show();
												speakOutButton.setEnabled(true);
											}
										}
									});

						}
					});
			// synthesize the files and store them on internal storage
			synthesizeTextStoreExternalMemory();
			if (textToSpeech.isLanguageAvailable(locale) == TextToSpeech.LANG_AVAILABLE) {
				textToSpeech.setLanguage(locale);
			}
		} else if (initStatus == TextToSpeech.ERROR) {
			Toast.makeText(this.getActivity(),
					getString(R.string.texttospeechinitializationerrormessage),
					Toast.LENGTH_SHORT).show();
		}

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
				ImplementationQuestion1Fragment.this.getActivity());
	}

	/**
	 * method for showing incorrect answer dialogue box
	 */
	private void showCorrectAnswerDialogueBox() {
		// in order to restore state set this variable true, indicating that
		// question answered correctly, so next time
		// user comes in again, he doesn't have to answer the question again
		StoreImplementationStateApplication.question1Answered = true;
		SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
		Editor editor1 = prefs1.edit();
		editor1.putBoolean(getString(R.string.implementation_quiz_1_answered), true);
		editor1.commit();

		// check if all other questions are answered
		if (StoreImplementationStateApplication.question2Answered
				&& StoreImplementationStateApplication.question3Answered) {
			// this call is specifically for updating fragment in description
			// tab
			communicationInterface.notifyDesciptionFragmentsUpdateImageView();
			
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
			Editor editor = prefs.edit();
			editor.putBoolean(getString(R.string.implementation_quiz_cleared), true);
			editor.commit();

			String _title = getString(R.string.levelcleared);
			String _message = getString(R.string.implementationlevelclearedmessage);
			UtilityMethods.createAlertDialog(_title, _message,
					ImplementationQuestion1Fragment.this.getActivity());

		} else {

			String _title = getString(R.string.correctansweralertdialoguetitle);
			String _message = getString(R.string.correctansweralertdialoguemessage);
			UtilityMethods.createAlertDialog(_title, _message,
					ImplementationQuestion1Fragment.this.getActivity());
		}

		disableRadioOptions();
		updateImageView();
		communicationInterface.notifyOtherFragmentsUpdateImageView(1);
	}

	/**
	 * update the corresponding image view to represent answer correctly
	 */
	private void updateImageView() {
		firstImageViev.setImageResource(R.drawable.voicereadingenabled);
	}

	/**
	 * Reads the text , basically plays the media file which was synthesized
	 * during start of the application and stored in external storage of mobile
	 * devices
	 */
	private void readTextAloud() {
		// check which text is currently visible
		if (questionFrame.getVisibility() == View.VISIBLE) {
			mediaPlayerQuestion.start();

		} else if (optionsFrame.getVisibility() == View.VISIBLE) {
			mediaPlayerOptions.start();
		}
	}

	/**
	 * pauses the current playing media file
	 */
	private void pauseTextAloud() {
		// check which text is currently visible
		if (questionFrame.getVisibility() == View.VISIBLE) {
			mediaPlayerQuestion.pause();
		} else if (optionsFrame.getVisibility() == View.VISIBLE) {
			mediaPlayerOptions.pause();
		}
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
			firstImageViev.setImageResource(R.drawable.microphonenabled);
		} else if (imageViewNumber == 2) {
			secondImageViev.setImageResource(R.drawable.microphonenabled);
		} else if (imageViewNumber == 3) {
			thirdImageViev.setImageResource(R.drawable.microphonenabled);
		}
	}

	/**
	 * Try to restore previous state of the application using static variables.
	 * See if user has already answered the question , try to restore that fact
	 * 
	 */
	private void restorePreviousState() {
		if (StoreImplementationStateApplication.question1Answered) {
			firstImageViev.setImageResource(R.drawable.microphonenabled);
			disableRadioOptions();
		}
		if (StoreImplementationStateApplication.question2Answered) {
			secondImageViev.setImageResource(R.drawable.microphonenabled);
		}
		if (StoreImplementationStateApplication.question3Answered) {
			thirdImageViev.setImageResource(R.drawable.microphonenabled);
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