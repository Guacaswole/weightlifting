package com.example.weightlifting;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

	private static final String TAG = "DEBUG";
	
	// DATABASE 
	private static final String DATABASE_NAME = "Weightlifting.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TYPE_TEXT = "TEXT NOT NULL";
	private static final String TYPE_INT  = "INTEGER NOT NULL";
	
	// TABLE COMMON
	private static final String COLUMN_ID       = "id";
	private static final String COLUMN_ID_TYPE  = "INTEGER PRIMARY KEY AUTOINCREMENT";
	
	// USER TABLE
	private static final String TABLE_USER = "User";
	private static final String COLUMN_NAME     = "name";
	private static final String COLUMN_USERNAME = "username";
	private static final String COLUMN_PASSWORD = "password";
	
	// WORKOUT TABLE
	private static final String TABLE_WORKOUT = "Workout";
	private static final String COLUMN_WORKOUT     = "workout";
	private static final String COLUMN_COMPLETED   = "completed";
	
	// USER WORKOUT TABLE
	private static final String TABLE_USER_WORKOUT = "UserWorkout";
	private static final String COLUMN_USER_ID          = "user_id";
	private static final String COLUMN_WORKOUT_ID       = "workout_id";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(TAG, "Creating database [" + DATABASE_NAME + " v." + DATABASE_VERSION + "]...");
		
		createUserTable(db);
		createWorkoutTable(db);
		createUserWorkoutTable(db);
	}

	private void createUserWorkoutTable(SQLiteDatabase db) {
		
		Log.i(TAG, "Creating table [" + TABLE_USER_WORKOUT + "]...");
		
		String CREATE_USER_WORKOUT_TABLE = 
				"CREATE TABLE " + TABLE_USER_WORKOUT + "("
				+ COLUMN_ID         + " " + COLUMN_ID_TYPE  + ", "
				+ COLUMN_USER_ID    + " " + TYPE_INT  + ", "
				+ COLUMN_WORKOUT_ID + " " + TYPE_INT  + ")";
		
		db.execSQL(CREATE_USER_WORKOUT_TABLE);
	}

	private void createWorkoutTable(SQLiteDatabase db) {
		
		Log.i(TAG, "Creating table [" + TABLE_WORKOUT + "]...");
		
		String CREATE_WORKOUT_TABLE = 
				"CREATE TABLE " + TABLE_WORKOUT + "("
				+ COLUMN_ID        + " " + COLUMN_ID_TYPE  + ", "
				+ COLUMN_WORKOUT   + " " + TYPE_TEXT       + ", "
				+ COLUMN_COMPLETED + " " + TYPE_INT + " DEFAULT 0" + ", "
				+ "FOREIGN KEY (" + COLUMN_ID + ") REFERENCES "
				+ TABLE_USER_WORKOUT + "(" + COLUMN_WORKOUT_ID + ")"
				+ ")";
		
		db.execSQL(CREATE_WORKOUT_TABLE);
		
	}

	private void createUserTable(SQLiteDatabase db) {
		
		Log.i(TAG, "Creating table [" + TABLE_USER + "]...");

		String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
				+ COLUMN_ID       + " " + COLUMN_ID_TYPE  + ", "
				+ COLUMN_NAME     + " " + TYPE_TEXT       + ", "
				+ COLUMN_USERNAME + " " + TYPE_TEXT       + ", "
				+ COLUMN_PASSWORD + " " + TYPE_TEXT       + ", "
				+ "FOREIGN KEY (" + COLUMN_ID + ") REFERENCES "
				+ TABLE_USER_WORKOUT + "(" + COLUMN_USER_ID + ")"
				+ ")";
		
		db.execSQL(CREATE_USER_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i(TAG, "Upgrading database [" + DATABASE_NAME + " v." + oldVersion +"] " +
				"to [" + DATABASE_NAME + " v." + newVersion + "]...");
		
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_WORKOUT);
		 
        // Create tables again
        onCreate(db);
	}
	
	// Adding new User
	public void addUser(User user) {
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(COLUMN_NAME, user.getName());
		values.put(COLUMN_USERNAME, user.getUsername());
		values.put(COLUMN_PASSWORD, user.getPassowrd());
		
		Log.i(TAG, "Adding User: " + user.getName() + "...");
		db.insert(TABLE_USER, null, values);
		db.close();
		Log.i(TAG, user.getName() + " was added successfully.");
	}
	 
	// Getting single User
	public User getUser(int id) { 
				
		SQLiteDatabase db = this.getReadableDatabase();
		
		String[] COLUMNS = new String[] { COLUMN_ID,
				COLUMN_NAME, COLUMN_USERNAME, COLUMN_PASSWORD };
		
		Cursor cursor = db.query(TABLE_USER, COLUMNS, COLUMN_ID + "?=",
				new String[] { String.valueOf(id) }, null, null, null);
		
		if(cursor != null) cursor.moveToFirst();
		
		db.close();
		return cursorToUser(cursor); 
	}
	
	public User cursorToUser(Cursor cursor){
		
		User user = new User();
		user.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
		user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
		user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
		user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
		
		Log.i(TAG, "Got User: " + user.getName());
		
		return user;
	}
	 
	// Getting All Users
	public List<User> getAllUsers() { 
	
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER, null);
		
		Log.i(TAG, "Loaded " + cursor.getCount() + " Users...");
		
		List<User> user_list = new ArrayList<User>();
		
		if(cursor.getCount() > 0){
			cursor.moveToFirst();
			while(!cursor.isAfterLast()){
				User user = cursorToUser(cursor);
				user_list.add(user);
				cursor.moveToNext();
			}
			Log.i(TAG, "Users loaded successfully.");
		}
		
		db.close();	
		return user_list;
	}
	 
	// Getting User count
	public int getUserCount() { 
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER, null);
		cursor.close();
		
		db.close();
		return cursor.getCount(); 
	}
	
	// Updating single User
	public int updateUser(User user) { 
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, user.getName());
		values.put(COLUMN_USERNAME, user.getUsername());
		values.put(COLUMN_PASSWORD, user.getPassowrd());
		
		int no_of_rows_updated = db.update(TABLE_USER, values, COLUMN_ID + "=?",
		            new String[] { String.valueOf(user.getId()) });	
		
		Log.i(TAG, "User: " + user.getName() + " updated.");
		db.close();
		return no_of_rows_updated;
	}
	 
	// Deleting single User
	public void deleteUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_USER, COLUMN_ID + "=?", 
				new String[] { String.valueOf(user.getId()) });

		Log.i(TAG, "User: " + user.getName() + " deleted.");
		db.close();	
	}
	
	public List<String> getAllWorkoutsForUser(User user) { 
	
		int id = user.getId();
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor workout_id_cursor = db.query(TABLE_USER_WORKOUT, new String[] { COLUMN_WORKOUT_ID },
		COLUMN_USER_ID + "?=", new String[] { String.valueOf(id) }, null, null, null);
		
		List<String> workout_list = new ArrayList<String>();
		
		int workout_id = 0;
		Cursor workout_cursor;
		String[] COLUMNS = new String[] { COLUMN_WORKOUT };
		
		if(workout_id_cursor.getCount() > 0){
			workout_id_cursor.moveToFirst();
			while(!workout_id_cursor.isAfterLast()){
				
				workout_id = workout_id_cursor.getInt(workout_id_cursor.getColumnIndex(COLUMN_WORKOUT_ID));
				workout_cursor = db.query(TABLE_WORKOUT, COLUMNS, COLUMN_ID + "?=", 
						new String[] { String.valueOf(workout_id) }, null, null, null);
				
				if (workout_cursor != null) workout_cursor.moveToFirst();
				
				Log.i(TAG, "Adding workout: " + workout_id + " to workout list for User: " + user.getName());
				workout_list.add(workout_cursor.getString(workout_cursor.getColumnIndex(COLUMN_WORKOUT)));
				workout_id_cursor.moveToNext();
			}
			Log.i(TAG, "Got list of workout IDs for User: " + user.getName());
		}
		
		db.close();	
		return workout_list; 
	}
	
	public String getNextWorkoutForUser(User user) { 
		return "";
	}
}
