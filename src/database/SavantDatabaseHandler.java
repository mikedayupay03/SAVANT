package database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import objects.Site;
import objects.SurveyItemAdaptiveCapacity;
import objects.SurveyItemSensitivity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class SavantDatabaseHandler extends SQLiteOpenHelper{

	private static String DB_PATH = "";
	private static final String DB_NAME = "savant.db";
	private static final int DB_VERSION = 1;
	
	private static String TABLE_SITE_SUGGESTION = "site_suggestion";
	private static String TABLE_SURVEY_ITEM_SENSITIVITY = "survey_item_sensitivity";
	private static String TABLE_SURVEY_ITEM_ADAPTIVE_CAPACITY = "survey_item_adaptive_capacity";
    private static String TABLE_SURVEY_ITEM_EXPOSURE = "survey_item_exposure";
    private static String TABLE_EXPOSURE_SCORE = "site_exposure_score";
	
	private SQLiteDatabase myDB;
	private final Context myContext;
	
	public SavantDatabaseHandler(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.myContext = context;
		DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
	}
	
	private boolean checkDatabase()
	{
		SQLiteDatabase checkDB = null;
		
		try{
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		}catch(SQLiteException e){
		}
		
		if(checkDB != null)
		{
			checkDB.close();
		}
		
		return checkDB != null ? true:false;
	}
	
	public void copyDatabase() throws IOException
	{
		InputStream myInput = myContext.getAssets().open(DB_NAME);
		 	
    	String outFileName = DB_PATH + DB_NAME;
 
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
	}
	
	public void createDatabase() throws IOException
	{
		boolean dbExist = checkDatabase();
		if(dbExist = false){
			
		}else {
			this.getReadableDatabase();
			
			try{
				copyDatabase();
			}catch(IOException e)
			{
				throw new Error("Error copying database");
			}
		}
	}
	
	public void openDataBase() throws SQLException {
    	String myPath = DB_PATH + DB_NAME;
    	myDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }
	
	@Override
	public synchronized void close() {
  	    if(myDB != null)
   		    myDB.close();
   	    super.close();
 	}
	
	public ArrayList <Site> getAllSuggestedSites() {
    	ArrayList <Site> results = new ArrayList <Site> ();
    	String query = "SELECT * FROM " + TABLE_SITE_SUGGESTION + ";";
    	Cursor cursor  = myDB.rawQuery(query, null);
    	
    	while (cursor.moveToNext()) {
    		Site site = new Site(-1, cursor.getString(cursor.getColumnIndex("name")),
    				cursor.getString(cursor.getColumnIndex("municipality")),
    				cursor.getString(cursor.getColumnIndex("province")),
    				"N/A");
    		results.add(site);
    	}
    	
    	return results;
    }
	
	public boolean checkSiteSuggestionExists(String siteName, String municipality, String province) {
    	String query = "SELECT * FROM " + TABLE_SITE_SUGGESTION + " WHERE name = '" + siteName + "' AND municipality = '"+municipality+"' AND province = '"+province +"' ;";
    	Cursor cursor = myDB.rawQuery(query, null);
    	
    	if (cursor.moveToNext()) {
    		return true;
    	}
    	return false;
    }
	
	public int getSiteSuggestionId(String siteName, String municipality, String province) {
    	String query = "SELECT * FROM " + TABLE_SITE_SUGGESTION + " WHERE name = '" + siteName + "' AND municipality = '"+municipality+"' AND province = '"+province +"' ;";
    	System.out.println(query);
    	Cursor cursor = myDB.rawQuery(query, null);
    	
    	if (cursor.moveToNext()) {
    		return cursor.getInt(cursor.getColumnIndex("_id"));
    	}
    	return -1;
    }
	
	public String[] getExposureScores(int site_suggestion_id) {
    	String query = "SELECT * FROM " + TABLE_EXPOSURE_SCORE + " WHERE site_suggestion_id = '" + site_suggestion_id + "';";
    	Cursor cursor = myDB.rawQuery(query, null);
    	ArrayList<String> scores = new ArrayList<String>();
    	
    	if (cursor.moveToNext()) {
    		scores.add(cursor.getString(cursor.getColumnIndex("wave_exposure")));
    		scores.add(cursor.getString(cursor.getColumnIndex("sea_surface_temp")));
    		scores.add(cursor.getString(cursor.getColumnIndex("rainfall")));
    	}
    	
    	String[] result = new String[scores.size()];
    	
    	for (int i = 0; i < scores.size(); i++) {
    		result[i] = scores.get(i);
		}
    	
    	return result;
    }
	
	public ArrayList <SurveyItemSensitivity> getAllSurveyItemsSensitivity() {
    	ArrayList <SurveyItemSensitivity> results = new ArrayList <SurveyItemSensitivity> ();
    	String query = "SELECT * FROM " + TABLE_SURVEY_ITEM_SENSITIVITY + ";";
    	Cursor cursor = myDB.rawQuery(query, null);
    	
    	while (cursor.moveToNext()) {
    		SurveyItemSensitivity item = new SurveyItemSensitivity(cursor.getString(cursor.getColumnIndex("category_name")),
    			cursor.getString(cursor.getColumnIndex("main_question")),
    			cursor.getString(cursor.getColumnIndex("sub_question")),
    			cursor.getString(cursor.getColumnIndex("value_1_desc")),
    			cursor.getString(cursor.getColumnIndex("value_2_desc")),
    			cursor.getString(cursor.getColumnIndex("value_3_desc")),
    			cursor.getString(cursor.getColumnIndex("value_4_desc")),
    			cursor.getString(cursor.getColumnIndex("value_5_desc")),
    			cursor.getString(cursor.getColumnIndex("image_1")),
    			cursor.getString(cursor.getColumnIndex("image_2")),
    			cursor.getString(cursor.getColumnIndex("image_3")),
    			cursor.getString(cursor.getColumnIndex("image_4")),
    			cursor.getString(cursor.getColumnIndex("image_5")));
    		results.add(item);
    	}
    	
    	return results;
    }
	
	public ArrayList <SurveyItemAdaptiveCapacity> getAllSurveyItemsAdaptiveCapacity() {
    	ArrayList <SurveyItemAdaptiveCapacity> results = new ArrayList <SurveyItemAdaptiveCapacity> ();
    	String query = "SELECT * FROM " + TABLE_SURVEY_ITEM_ADAPTIVE_CAPACITY + ";";
    	Cursor cursor = myDB.rawQuery(query, null);
    	
    	while (cursor.moveToNext()) {
    		SurveyItemAdaptiveCapacity item = new SurveyItemAdaptiveCapacity(cursor.getString(cursor.getColumnIndex("category_name")),
    			cursor.getString(cursor.getColumnIndex("main_question")),
    			cursor.getString(cursor.getColumnIndex("sub_question")),
    			cursor.getString(cursor.getColumnIndex("value_2_desc")),
    			cursor.getString(cursor.getColumnIndex("value_3_desc")),
    			cursor.getString(cursor.getColumnIndex("value_4_desc")),
    			cursor.getString(cursor.getColumnIndex("value_5_desc")),
    			cursor.getString(cursor.getColumnIndex("image_2")),
    			cursor.getString(cursor.getColumnIndex("image_3")),
    			cursor.getString(cursor.getColumnIndex("image_4")),
    			cursor.getString(cursor.getColumnIndex("image_5")));
    		results.add(item);
    	}
    	
    	return results;
    }
	
	public String[] getAllSurveyItemsExposure()
    {
    	ArrayList <String> results = new ArrayList <String> ();
    	String query = "SELECT * FROM " + TABLE_SURVEY_ITEM_EXPOSURE+ ";";
    	Cursor cursor = myDB.rawQuery(query, null);
    	
    	while (cursor.moveToNext()) {
    		results.add(cursor.getString(cursor.getColumnIndex("question")));
    	}
    	
    	String[] resultArr = new String[results.size()];
    	
    	for (int i = 0; i < results.size(); i++) {
    		resultArr[i] = results.get(i);
		}
    	
    	return resultArr;
    }
	
	
	
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
