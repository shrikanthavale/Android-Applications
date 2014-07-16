package at.fhooe.mcm.it.adid.model.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.widget.Toast;
import at.fhooe.mcm.it.adid.R;

/**
 * UtilityMethods class has several utility methods that could be used through the
 * projects. Refer to the comments over method and use them accordingly.
 * 
 * @author Shrikant Havale
 * 
 */
public class UtilityMethods {

	/**
	 * Accepts the file name to be read from assets folder and returns the
	 * content of the file. file is present, folder name is "design"
	 * 
	 * @param _fileName
	 *            name of the file to be opened from design folder
	 * @param _folderName
	 *            folder name in which file is present
	 * @param _activity
	 *            activity is required for accessing other methods
	 * 
	 * @return data in the string format
	 */
	public static String loadDataFromFile(String _fileName, String _folderName,
			Activity _activity) {

		// variables required for file access
		StringBuffer lineBuffer = new StringBuffer();
		AssetManager assetManager = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		String line = null;

		try {
			// asset manager
			assetManager = _activity.getAssets();

			// open the file
			inputStream = assetManager.open(_folderName + "/" + _fileName);

			// read the file
			inputStreamReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			while ((line = bufferedReader.readLine()) != null) {
				lineBuffer.append(line);
				lineBuffer.append("\n");
			}

		} catch (IOException e) {
			// handle the exception
			e.printStackTrace();
			Toast.makeText(_activity,
					_activity.getString(R.string.exceptionreadwrite),
					Toast.LENGTH_SHORT).show();
		} catch (Exception exception) {
			// handle the exception
			exception.printStackTrace();
			Toast.makeText(_activity,
					_activity.getString(R.string.exceptionreadwrite),
					Toast.LENGTH_SHORT).show();
		} finally {

			// close all the resources
			try {
				if (inputStream != null)
					inputStream.close();
				if (inputStreamReader != null)
					inputStreamReader.close();
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException e) {
				// handle the exception
				Toast.makeText(_activity,
						_activity.getString(R.string.exceptionreadwrite),
						Toast.LENGTH_LONG).show();
			} catch (Exception exception) {
				// handle the exception
				Toast.makeText(_activity,
						_activity.getString(R.string.exceptionreadwrite),
						Toast.LENGTH_SHORT).show();
			}
		}

		// return the string data result
		return lineBuffer.toString();

	}

	/**
	 * Accepts the file name to be read from assets folder and returns the
	 * content of the file in the format of list of options. file is present,
	 * folder name is "design"
	 * 
	 * @param _fileName
	 *            name of the file to be opened from design folder
	 * @param _folderName
	 *            folder name in which file is present
	 * @param _activity
	 *            activity is required for accessing other methods
	 * 
	 * @return list of options data in the list string format
	 */
	public static List<String> loadOptionListFromFile(String _fileName,
			String _folderName, Activity _activity) {

		// variables required for file access
		AssetManager assetManager = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		String line = null;
		List<String> listOptions = new ArrayList<String>();

		try {
			// asset manager
			assetManager = _activity.getAssets();

			// open the file
			inputStream = assetManager.open(_folderName + "/" + _fileName);

			// read the file
			inputStreamReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			while ((line = bufferedReader.readLine()) != null) {
				listOptions.add(line);
			}

		} catch (IOException e) {
			// handle the exception
			e.printStackTrace();
			Toast.makeText(_activity,
					_activity.getString(R.string.exceptionreadwrite),
					Toast.LENGTH_SHORT).show();
		} catch (Exception exception) {
			// handle the exception
			exception.printStackTrace();
			Toast.makeText(_activity,
					_activity.getString(R.string.exceptionreadwrite),
					Toast.LENGTH_SHORT).show();
		} finally {

			// close all the resources
			try {
				if (inputStream != null)
					inputStream.close();
				if (inputStreamReader != null)
					inputStreamReader.close();
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException e) {
				// handle the exception
				Toast.makeText(_activity,
						_activity.getString(R.string.exceptionreadwrite),
						Toast.LENGTH_LONG).show();
			} catch (Exception exception) {
				// handle the exception
				Toast.makeText(_activity,
						_activity.getString(R.string.exceptionreadwrite),
						Toast.LENGTH_SHORT).show();
			}
		}

		// return the string data result
		return listOptions;

	}

	/**
	 * Creates the alert dialog based on error message and title of the
	 * dialogue. Needs the activity where the dialogue is to be displayed.
	 * 
	 * @param _title
	 *            title of the dialogue
	 * @param _message
	 *            message in the dialogue
	 * @param _activity
	 *            fragment where dialogue is displayed
	 */
	public static void createAlertDialog(String _title, String _message,
			Activity _activity) {

		AlertDialog.Builder alertDialog = new AlertDialog.Builder(_activity);

		// Setting Dialog Title
		alertDialog.setTitle(_title);

		// Setting Dialog Message
		alertDialog.setMessage(_message);

		// Setting Positive "Ok" Btn
		alertDialog.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});

		// Showing Alert Dialog
		alertDialog.show();
	}

	/**
	 * This method reads the data from internal storage , and notes created in
	 * specific folder and file. if file is not present , it returns standard
	 * text.
	 * 
	 * @param Activity
	 *            activity from where this method is called
	 * @param folderName
	 *            folder name to be created inside the internal storage
	 * @param fileName
	 *            fileName to be created inside the folder specified
	 * 
	 * @return string text containing the data from notes
	 */
	public static String readNotesFromInternalStorage(Activity activity,
			String folderName, String fileName) {

		// context wrapper for accessing the package and directory
		ContextWrapper contextWrapper = new ContextWrapper(activity);

		// get the folder name
		File folderNameFile = contextWrapper.getDir(folderName,
				Context.MODE_PRIVATE); // Creating an internal dir;

		// get the file name
		File fileNameFile = new File(folderNameFile, fileName);

		// set of variables
		StringBuilder lineBuffer = new StringBuilder();
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		String line = null;

		try {

			// if the file doesn't exists or is deleted , then return standard
			// message
			if (!fileNameFile.exists()) {
				return activity.getString(R.string.defaultnotetext);
			}
			fileInputStream = new FileInputStream(fileNameFile);
			inputStreamReader = new InputStreamReader(fileInputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			while ((line = bufferedReader.readLine()) != null) {
				lineBuffer.append(line);
				lineBuffer.append("\n");
			}
		} catch (FileNotFoundException e) {
			// return default text for file not found
			return activity.getString(R.string.defaultnotetext);
		} catch (IOException e) {
			// return default text IOException
			return activity.getString(R.string.defaultnotetext);
		} catch (Exception exception) {
			// handle the exception
			Toast.makeText(activity,
					activity.getString(R.string.exceptionreadwrite),
					Toast.LENGTH_SHORT).show();
		} finally {

			// close all the resources
			try {
				if (fileInputStream != null)
					fileInputStream.close();
				if (inputStreamReader != null)
					inputStreamReader.close();
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException e) {
				// handle the exception
				Toast.makeText(activity,
						activity.getString(R.string.exceptionreadwrite),
						Toast.LENGTH_LONG).show();
			} catch (Exception exception) {
				// handle the exception
				Toast.makeText(activity,
						activity.getString(R.string.exceptionreadwrite),
						Toast.LENGTH_SHORT).show();
			}
		}

		return lineBuffer.toString();
	}

	/**
	 * This method saves the notes in the internal storage at a particular
	 * location Folder and in aFile name.
	 * 
	 * @param Activity
	 *            activity from where this method is called
	 * @param folderName
	 *            folder name to be created inside the internal storage
	 * @param fileName
	 *            fileName to be created inside the folder specified
	 * @param notes
	 *            notes to be saved
	 * 
	 */
	public static void saveNotes(Activity activity, String folderName,
			String fileName, String notes) {

		// context wrapped for getting directory
		ContextWrapper contextWrapper = new ContextWrapper(activity);

		// create a folder in internal storage
		File folderNameFile = contextWrapper.getDir(folderName,
				Context.MODE_PRIVATE); // Creating an internal dir;

		// create the directory
		if (!folderNameFile.exists()) {
			folderNameFile.mkdir();
		}

		// create a file
		File fileNameFile = new File(folderNameFile, fileName);

		// get the text from notes
		String notesText = notes;

		// file output stream to read file
		FileOutputStream outputStream = null;

		// file writer
		OutputStreamWriter outputStreamWriter = null;

		try {
			// if file doesn't exist create the file firs
			if (!fileNameFile.exists()) {
				fileNameFile.createNewFile();
			}
			outputStream = new FileOutputStream(fileNameFile);
			outputStreamWriter = new OutputStreamWriter(outputStream);
			outputStreamWriter.write(notesText);
			outputStreamWriter.close();
			Toast.makeText(activity,
					activity.getString(R.string.notessavedsuccess),
					Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			// handle the exception
			Toast.makeText(activity,
					activity.getString(R.string.exceptionreadwrite),
					Toast.LENGTH_SHORT).show();
		} catch (Exception exception) {
			// handle the exception
			Toast.makeText(activity,
					activity.getString(R.string.exceptionreadwrite),
					Toast.LENGTH_SHORT).show();
		} finally {

			// close all the resources
			try {
				if (outputStream != null)
					outputStream.close();
			} catch (IOException e) {
				// handle the exception
				Toast.makeText(activity,
						activity.getString(R.string.exceptionreadwrite),
						Toast.LENGTH_LONG).show();
			} catch (Exception exception) {
				// handle the exception
				Toast.makeText(activity,
						activity.getString(R.string.exceptionreadwrite),
						Toast.LENGTH_SHORT).show();
			}
		}

	}

	/**
	 * Asking the permission for installing Google Voice Search. If permission
	 * granted – sent user to Google Play
	 * 
	 * @param activity
	 *            activity
	 */
	public static void installGoogleVoiceSearch(final Activity activity) {

		// creating a dialog asking user if he want
		// to install the Voice Search
		Dialog dialog = new AlertDialog.Builder(activity)
				.setMessage(
						activity.getString(R.string.googlevoiceinputdisablederrormessage))
				// dialog message
				.setTitle(
						activity.getString(R.string.googlevoiceinputdisableddialoguetitle))
				// dialog header
				.setPositiveButton(
						activity.getString(R.string.googlevoiceinputdisableddialogueinstallbutton),
						new DialogInterface.OnClickListener() { // confirm
																// button

							// Install Button click handler
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								try {

									// creating an Intent for opening
									// applications page in Google Play
									// Voice Search package name:
									// com.google.android.voicesearch
									Intent intent = new Intent(
											Intent.ACTION_VIEW,
											Uri.parse("market://details?id=com.google.android.voicesearch"));

									// setting flags to avoid going in
									// application history (Activity call stack)
									intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY
											| Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

									// sending an Intent
									activity.startActivity(intent);

								} catch (Exception ex) {
									// if something going wrong
									// doing nothing
								}

							}
						})

				.setNegativeButton(
						activity.getString(R.string.googlevoiceinputdisableddialoguecancelbutton),
						null) // cancel button
				.create();

		dialog.show(); // showing dialog
	}

}
