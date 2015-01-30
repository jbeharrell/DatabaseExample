package com.example.jon.databaseexample;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

/**
 * Author: Jon Beharrell
 * Date: 1/29/2015
 * Description: This class manages the names database
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

	// Creating constants to represent the names of the database table and fields
	public static final String DATABASE_NAME = "names.db";
	public static final String TABLE_NAMES = "names";
	public static final String FIELD_ID = "_id"; // _id required to be the primary key
	public static final String FIELD_FIRST_NAME = "first_name";
	public static final String FIELD_LAST_NAME = "last_name";
	public static final int DATABASE_VERSION = 1;

	// sql to create the database
	public static final String DATABASE_CREATE = "CREATE TABLE "+TABLE_NAMES+" ("+
												 FIELD_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
												 FIELD_FIRST_NAME+" TEXT, "+
												 FIELD_LAST_NAME+" TEXT);";

	public MySQLiteHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// This method is called if the database is not created
	@Override
	public void onCreate(SQLiteDatabase database){
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
		database.execSQL("DROP TABLE IF EXISTS"+TABLE_NAMES);
		onCreate(database);
	}
}