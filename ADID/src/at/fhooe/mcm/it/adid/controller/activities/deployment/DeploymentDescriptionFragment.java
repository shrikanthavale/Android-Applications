package at.fhooe.mcm.it.adid.controller.activities.deployment;

import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import at.fhooe.mcm.it.adid.R;
import at.fhooe.mcm.it.adid.model.utility.UtilityMethods;

/**
 * This contains one section of Deployment. Description phase of the
 * application. It will contain text displaying the description, button for
 * taking notes, and image section for displaying features locked unlocked.
 * 
 * @author Shrikant Havale
 * 
 */
public class DeploymentDescriptionFragment extends Fragment {

	/**
	 * folder name in assets directory for loading of the data
	 */
	private static final String FOLDER_NAME = "deployment";

	/**
	 * description file name in assets directory
	 */
	private static final String DESCRIPTION_FILE_NAME = "description.txt";

	/**
	 * help file name in assets directory
	 */
	private static final String HELP_FILE_NAME = "help.txt";

//	/**
//	 * text file for saving notes in the internal storage
//	 */
//	private static final String NOTES_FILE_INTERNAL_STORAGE = "notes.txt";

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

//	/**
//	 * notes frame layout
//	 */
//	private LinearLayout noteFrame = null;

//	/**
//	 * Text for capturing notes
//	 */
//	private EditText notesTaken = null;

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

//	/**
//	 * button for take Notes
//	 */
//	private Button showNotes;
	
//	/**
//	 * string for storing notes read from the file
//	 */
//	private String notesReadFromFileStorage = null;

	/**
	 * locale for internationalization
	 */
	private Locale locale = Locale.getDefault();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_deployment_description, container, false);

		// get the all the required components
		helpFrame = ((LinearLayout) rootView.findViewById(R.id.helpframe));
		descriptionFrame = ((LinearLayout) rootView
				.findViewById(R.id.descriptionframe));
//		noteFrame = ((LinearLayout) rootView.findViewById(R.id.notesFrame));
		descriptionTextView = (TextView) rootView
				.findViewById(R.id.description_text);
		helpTextView = (TextView) rootView.findViewById(R.id.help_text);
//		notesTaken = (EditText) rootView.findViewById(R.id.noteEditText);
		descriptionTextView.setMovementMethod(new ScrollingMovementMethod());
		helpTextView.setMovementMethod(new ScrollingMovementMethod());

		// get the buttons
		showDetails = ((Button) rootView.findViewById(R.id.showdescription));
		showDetailsIv = (ImageView) rootView.findViewById(R.id.showdescription_iv);
		showHelp = ((Button) rootView.findViewById(R.id.showhelp));
		showHelpIv = (ImageView) rootView.findViewById(R.id.showhelp_iv);
//		showNotes = ((Button) rootView.findViewById(R.id.takenotes));

		// add button click listeners
		// help button
		showHelp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				descriptionFrame.setVisibility(View.GONE);
//				noteFrame.setVisibility(View.GONE);
				helpFrame.setVisibility(View.VISIBLE);
				showHelpIv.setSelected(true);
				showDetailsIv.setSelected(false);
//				showNotes.setSelected(false);
			}
		});

		// description button
		showDetails.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				helpFrame.setVisibility(View.GONE);
//				noteFrame.setVisibility(View.GONE);
				descriptionFrame.setVisibility(View.VISIBLE);
				showDetailsIv.setSelected(true);
				showHelpIv.setSelected(false);
//				showNotes.setSelected(false);
			}
		});

//		// take notes button
//		showNotes.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				helpFrame.setVisibility(View.GONE);
//				descriptionFrame.setVisibility(View.GONE);
//				noteFrame.setVisibility(View.VISIBLE);
//				showNotes.setSelected(true);
//				showDetails.setSelected(false);
//				showHelp.setSelected(false);
//			}
//		});
//		// save notes
//		rootView.findViewById(R.id.saveNotes).setOnClickListener(
//				new OnClickListener() {
//					@Override
//					public void onClick(View v) {
//						UtilityMethods.saveNotes(
//								DeploymentDescriptionFragment.this.getActivity(), FOLDER_NAME,
//								NOTES_FILE_INTERNAL_STORAGE, notesTaken
//										.getText().toString());
//						notesReadFromFileStorage = notesTaken.getText()
//								.toString();
//					}
//				});
//		// clear notes
//		rootView.findViewById(R.id.cancelNotes).setOnClickListener(
//				new OnClickListener() {
//					@Override
//					public void onClick(View v) {
//						clearNotes();
//					}
//				});

		if (locale.getLanguage().equalsIgnoreCase(
				getString(R.string.germalanguagelocale))) {
			// load the description for description fragment
			descriptionFileData = UtilityMethods.loadDataFromFile(
					DESCRIPTION_FILE_NAME_DE, FOLDER_NAME, this.getActivity());

			// load the help file data
			helpFileData = UtilityMethods.loadDataFromFile(HELP_FILE_NAME_DE,
					FOLDER_NAME, this.getActivity());
		}else{
			
			// load the description for description fragment
			descriptionFileData = UtilityMethods.loadDataFromFile(
					DESCRIPTION_FILE_NAME, FOLDER_NAME, this.getActivity());

			// load the help file data
			helpFileData = UtilityMethods.loadDataFromFile(HELP_FILE_NAME,
					FOLDER_NAME, this.getActivity());
			
		}

//		// notes read from the file storage
//		notesReadFromFileStorage = UtilityMethods.readNotesFromInternalStorage(this.getActivity(), FOLDER_NAME, NOTES_FILE_INTERNAL_STORAGE);

		// set the text data
		helpTextView.setText(helpFileData);
		descriptionTextView.setText(descriptionFileData);
//		notesTaken.setText(notesReadFromFileStorage);

		// initially help view is visible
		helpFrame.setVisibility(View.VISIBLE);
		showHelpIv.setSelected(true);

		return rootView;
	}

//	/**
//	 * This method clears the current written text from notes section
//	 * 
//	 * if the file is already saved contents are appended accordingly
//	 */
//	private void clearNotes() {
//		// restore the previous notes
//		notesTaken.setText(notesReadFromFileStorage);
//	}

}
