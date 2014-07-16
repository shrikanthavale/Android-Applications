package at.fhooe.mcm.it.adid.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

/**
 * The database manager for the sqlite database in which the notes get saved.
 * 
 * @author Mario Kraml
 * 
 */
public class DatabaseManager {

	private static final String DATABASE_NAME = "adid.sqlite";
	private static final int DATABASE_VERSION = 1;

	/**
	 * The table in which the notes get saved.
	 * 
	 * @author Mario Kraml
	 * 
	 */
	public static final class NotesTable {

		public static final String TABLE_NAME = "notes";
		public static final String STEP_ID = "step_id";
		public static final String TEXT = "note_text";

		/**
		 * Lesson table sql create statement
		 */
		public static final String TABLE_CREATE = "create table " + TABLE_NAME
				+ " (" + STEP_ID + " INTEGER PRIMARY KEY, " + TEXT + " text);";
	}

	/**
	 * The Helper class for the dsqlite database
	 * 
	 * @author Mario Kraml
	 * 
	 */
	private static class DatabaseHelper extends SQLiteOpenHelper {

		private static DatabaseHelper mDbHelper;

		/**
		 * Private Constructor for singleton
		 * 
		 * @param _context
		 *            The application context
		 */
		private DatabaseHelper(Context _context) {
			super(_context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		/**
		 * Returns an instance of the singleton object
		 * 
		 * @param context
		 *            The application context
		 * @return An instance of the singleton object
		 */
		public static DatabaseHelper getInstance(Context context) {
			if (mDbHelper == null) {
				mDbHelper = new DatabaseHelper(context);
			}
			return mDbHelper;
		}

		@Override
		public void onCreate(SQLiteDatabase _db) {
			_db.execSQL(NotesTable.TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int _oldVersion,
				int _newVersion) {
		}

	}

	private static DatabaseManager mDbManager;
	private final Context mContext;
	private DatabaseHelper mDbHelper;

	/**
	 * Private Constructor for the singleton. Takes the context to allow the
	 * database to be opened/created
	 */
	private DatabaseManager(Context context) {
		mContext = context;
		mDbHelper = DatabaseHelper.getInstance(mContext);
	}

	/**
	 * Returns an instance of the singleton object
	 * 
	 * @param context
	 *            The application context
	 * @return An instance of the singleton object
	 */
	public static DatabaseManager getInstance(Context _ctx) {
		if (mDbManager == null) {
			mDbManager = new DatabaseManager(_ctx);
		}
		return mDbManager;
	}

	/**
	 * This method returns a String with the note of the specified step. The
	 * step is specified by the stepId. Returns null when no note with the given
	 * stepId is in the database.
	 * 
	 * @param step_id
	 *            The id of the note
	 * @return The note string associated with the step id OR null if there is
	 *         no such note in the database
	 */
	public String getNote(int stepId) {
		String selection = NotesTable.STEP_ID + "= " + stepId;
		String[] columns = new String[] { NotesTable.TEXT };

		SQLiteQueryBuilder qBuilder = new SQLiteQueryBuilder();
		qBuilder.setTables(NotesTable.TABLE_NAME);
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		Cursor cursor = qBuilder.query(db, columns, selection, null, null,
				null, null);

		if (cursor != null) {
			cursor.moveToFirst();
		}

		String text = null;
		if (cursor.getCount() > 0) {
			text = cursor.getString(cursor.getColumnIndex(NotesTable.TEXT));
		}
		cursor.close();

		return text;
	}

	/**
	 * Inserts a note string into the database. If the particular object already
	 * exists in the database then it will be updated.
	 * 
	 * @param stepId
	 *            The stepId of the note to insert. Is the primary key of the
	 *            database table.
	 * @param text
	 *            The note string to insert
	 */
	public void insertNote(int stepId, String text) {
		SQLiteDatabase db = mDbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(NotesTable.STEP_ID, stepId);
		values.put(NotesTable.TEXT, text);

		db.insertWithOnConflict(NotesTable.TABLE_NAME, null, values,
				SQLiteDatabase.CONFLICT_REPLACE);
	}

	/**
	 * Delete a note string with the step id from the database
	 * 
	 * @param stepId
	 *            The step id of the entry
	 */
	public void deleteNote(int stepId, String text) {
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		String selection = NotesTable.STEP_ID + "= " + stepId;
		db.delete(NotesTable.TABLE_NAME, selection, null);
	}

	/**
	 * Deletes all notes in the database
	 * 
	 */
	public void deleteAllNotes() {
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		db.delete(NotesTable.TABLE_NAME, null, null);
	}
}
