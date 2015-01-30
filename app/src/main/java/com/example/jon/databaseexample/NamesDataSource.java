package com.example.jon.databaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jon Beharrell
 * Date: 1/29/2015
 * Description: This class provides methods to access data from the Names database
 */

public class NamesDataSource {

	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allFields = { MySQLiteHelper.FIELD_ID, 
								   MySQLiteHelper.FIELD_FIRST_NAME, 
								   MySQLiteHelper.FIELD_LAST_NAME};

	public NamesDataSource(Context context){
		dbHelper = new MySQLiteHelper(context);
	}

	/** Call before using the database */
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	/** Call when finished with the database */
	public void close(){
		dbHelper.close();
	}

	/** Insert a name into the database */
	public void insert(String firstName, String lastName){

		// ContentValues stores key-value pairs to pass to the database.insert method
	    ContentValues values = new ContentValues();
	    values.put(MySQLiteHelper.FIELD_FIRST_NAME, firstName);
	    values.put(MySQLiteHelper.FIELD_LAST_NAME, lastName);

	    // SQLiteDatabase.insert(String table, String nullColumnHack, ContentValues values)
	    database.insert(MySQLiteHelper.TABLE_NAMES, null, values);
  	}

  	/** Delete a name from the database */
  	public void delete(Name name){
  		long id = name.getId();

  		//SQLiteDatabase.delete(String table, String whereClause, String[] whereArgs)
  		database.delete(MySQLiteHelper.TABLE_NAMES, MySQLiteHelper.FIELD_ID+"="+id, null);
  	}

  	/** Get all names in the database */
  	public List<Name> getAll(){
  		List<Name> names = new ArrayList<Name>();

  		// Cursor holds all data returned from a query
  		// SQLiteDatabase.query (String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)
  		Cursor cursor = database.query(MySQLiteHelper.TABLE_NAMES, allFields, null, null, null, null, null);

  		// Loop through the cursor adding names to the arrayList
  		cursor.moveToFirst();
  		while(!cursor.isAfterLast()){
  			Name name = cursorToName(cursor);
  			names.add(name);
  			cursor.moveToNext();
  		}

  		// Close cursor
  		cursor.close();
  		return names;
  	}

  	/** Converts current cursor position to Name */
  	private Name cursorToName(Cursor cursor){
  		Name name = new Name();
  		name.setId(cursor.getInt(cursor.getColumnIndexOrThrow(MySQLiteHelper.FIELD_ID)));
  		name.setFirstName(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.FIELD_FIRST_NAME)));
  		name.setLastName(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.FIELD_LAST_NAME)));
  		return name;
  	}
}
