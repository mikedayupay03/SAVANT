package database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import objects.Site;
import objects.SurveyItem;
import objects.SurveyType;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class SurveyDatabaseHandler extends SQLiteOpenHelper{
	
	private static String DB_PATH = "";
	private static String DB_NAME = "survey.sqlite3";
	private static int DB_VERSION = 1;
	
    private static String TABLE_SITE = "site";
    private static String TABLE_ANSWER_SENSITIVITY = "answer_sensitivity";
    private static String TABLE_ANSWER_ADAPTIVE_CAPACITY = "answer_adaptive_capacity";
    private static String TABLE_ANSWER_EXPOSURE = "answer_exposure";
	
    private SQLiteDatabase myDB;
	private final Context myContext;
	
	public SurveyDatabaseHandler(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stutb
		this.myContext = context;
		DB_PATH = "/data/data/" + context.getApplicationContext().getPackageName() + "/databases/";
		
		boolean dbexist = checkDatabase();
		
		if (dbexist) {
			
		} else {
			System.out.println("Database doesn't exist");
			try {
				createDatabase();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private boolean checkDatabase() {
		// TODO Auto-generated method stub
    	SQLiteDatabase checkDB = null;
    	
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    	}catch(SQLiteException e){
    	}
    	
    	if(checkDB != null){
    		checkDB.close();
    	}
    	
    	return checkDB != null ? true : false;
	}

	public void createDatabase() throws IOException{
		// TODO Auto-generated method stub

		boolean dbExist = checkDatabase();
    	if (dbExist) {
    		
    	} else {
        	this.getReadableDatabase();
        	this.close();
        	try {
    			copyDataBase();
    		} catch (IOException e) {
        		throw new Error("Error copying database");
        	}
    	}
	}

	private void copyDataBase() throws IOException{
		// TODO Auto-generated method stub
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
	
    public void openDataBase() throws SQLException {
    	String myPath = DB_PATH + DB_NAME;
    	myDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }
    
    @Override
	public synchronized void close() {
  	    if(myDB != null)
   		    myDB.close();
   	    super.close();
 	}
    
    public void saveSite(String siteName, String municipality, String province) {
    	String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
    	
    	String query = "INSERT INTO " + TABLE_SITE + "(name, municipality, province, date_created, sensitivity_score, adaptive_capacity_score, exposure_score) VALUES ('" + siteName + "', '" + municipality + "', '" + province + "', '" + timeStamp + "', -1, -1, -1);";
    	myDB.execSQL(query);
    }
    
    public void saveSensitivitySurveyAnswers(int siteId, int[] answers, float result) {
    	String query = "UPDATE " + TABLE_SITE + " SET sensitivity_score = " + result + " WHERE _id = " + siteId + ";";
    	myDB.execSQL(query);
    	
    	for (int i = 0; i < answers.length; i++) {
    		String temp = "INSERT INTO " + TABLE_ANSWER_SENSITIVITY + " (site_id, item_id, value) VALUES (" + siteId + ", " + (i + 1) + ", " + (answers[i] + 1) + ");";
    		myDB.execSQL(temp);
    	}
    }    public void saveAdaptiveCapacitySurveyAnswers(int siteId, int[] answers, float result) {
    	String query = "UPDATE " + TABLE_SITE + " SET adaptive_capacity_score = " + result + " WHERE _id = " + siteId + ";";
    	myDB.execSQL(query);
    	
    	for (int i = 0; i < answers.length; i++) {
    		String temp = "INSERT INTO " + TABLE_ANSWER_ADAPTIVE_CAPACITY + " (site_id, item_id, value) VALUES (" + siteId + ", " + (i + 1) + ", " + (answers[i] + 2) + ");";
    		myDB.execSQL(temp);
    	}
    }
    
    public Site getSite(int id) {
    	Site result;
    	String query = "SELECT * FROM " + TABLE_SITE + " WHERE _id = " + id + ";";
    	Cursor cursor = myDB.rawQuery(query, null);
    	
    	if (cursor.moveToNext()) {
    		result = new Site(cursor.getInt(cursor.getColumnIndex("_id")),
        		cursor.getString(cursor.getColumnIndex("name")),
        		cursor.getString(cursor.getColumnIndex("municipality")),
        		cursor.getString(cursor.getColumnIndex("province")),
        		cursor.getString(cursor.getColumnIndex("date_created")));
    		if (cursor.getFloat(cursor.getColumnIndex("sensitivity_score")) > -1.0) {
    			result.setSensitivityScore(cursor.getFloat(cursor.getColumnIndex("sensitivity_score")));
    		}
    		if (cursor.getFloat(cursor.getColumnIndex("adaptive_capacity_score")) > -1.0) {
    			result.setAdaptiveCapacityScore(cursor.getFloat(cursor.getColumnIndex("adaptive_capacity_score")));
    		}
    			
    		result.setExposureScore(cursor.getString(cursor.getColumnIndex("exposure_score")));
    		
    		return result;
    	}
    	return null;
    }
    
    public ArrayList <SurveyItem> getAllSurveyAnswerValues(int siteId, SurveyType type) {
    	ArrayList <SurveyItem> results = new ArrayList <SurveyItem> ();
    	String query = "";
    	if (type == SurveyType.SURVEY_TYPE_SENSITIVITY) {
    		query = "SELECT * FROM " + TABLE_ANSWER_SENSITIVITY + " WHERE site_id = " + siteId + " ORDER BY item_id ASC;";
    	}
    	else {
    		query = "SELECT * FROM " + TABLE_ANSWER_ADAPTIVE_CAPACITY + " WHERE site_id = " + siteId + " ORDER BY item_id ASC;";
    	}
    	Cursor cursor = myDB.rawQuery(query, null);
    	
    	while (cursor.moveToNext()) {
    		SurveyItem surveyItem = new SurveyItem(cursor.getInt(cursor.getColumnIndex("item_id")),
    			"",
    			cursor.getString(cursor.getColumnIndex("value")));
    		
    		results.add(surveyItem);
    	}
    	
    	return results;
    }
    
    public ArrayList <SurveyItem> getAllSurveyAnswers(int siteId, SurveyType type, String[] surveyTextList) {
    	ArrayList <SurveyItem> results = new ArrayList <SurveyItem> ();
    	String query = "";
    	if (type == SurveyType.SURVEY_TYPE_SENSITIVITY) {
    		query = "SELECT * FROM " + TABLE_ANSWER_SENSITIVITY + " WHERE site_id = " + siteId + " ORDER BY item_id ASC;";
    	}
    	else if (type == SurveyType.SURVEY_TYPE_ADAPTIVE_CAPACITY) {
    		query = "SELECT * FROM " + TABLE_ANSWER_ADAPTIVE_CAPACITY + " WHERE site_id = " + siteId + " ORDER BY item_id ASC;";
    	}
    	else{
    		query = "SELECT * FROM " + TABLE_ANSWER_EXPOSURE + " WHERE site_id = " + siteId + " ORDER BY item_id ASC;";
    	}
    	
    	Cursor cursor = myDB.rawQuery(query, null);
    	
    	while (cursor.moveToNext()) {
    		SurveyItem surveyItem = new SurveyItem(cursor.getInt(cursor.getColumnIndex("item_id")),
    			surveyTextList[cursor.getInt(cursor.getColumnIndex("item_id")) - 1],
    			cursor.getString(cursor.getColumnIndex("value")));
    		
    		results.add(surveyItem);
    	}
    	
    	return results;
    }
    
    public ArrayList <Site> getAllSites() {
    	ArrayList <Site> results = new ArrayList <Site> ();
    	String query = "SELECT * FROM " + TABLE_SITE + " ORDER BY DATE(date_created) DESC;";
    	Cursor cursor = myDB.rawQuery(query, null);
    	
    	while (cursor.moveToNext()) {
    		Site site = new Site(cursor.getInt(cursor.getColumnIndex("_id")),
    			cursor.getString(cursor.getColumnIndex("name")),
    			cursor.getString(cursor.getColumnIndex("municipality")),
    			cursor.getString(cursor.getColumnIndex("province")),
    			cursor.getString(cursor.getColumnIndex("date_created")));
    		
    		if (cursor.getFloat(cursor.getColumnIndex("sensitivity_score")) > -1) {
    			site.setSensitivityScore(cursor.getFloat(cursor.getColumnIndex("sensitivity_score")));
    		}
    		if (cursor.getFloat(cursor.getColumnIndex("adaptive_capacity_score")) > -1) {
    			site.setAdaptiveCapacityScore(cursor.getFloat(cursor.getColumnIndex("adaptive_capacity_score")));
    		}
    		
    		results.add(site);
    	}
    	
    	return results;
    }
    
    public int getLatestSiteId() {
    	String query = "SELECT _id FROM " + TABLE_SITE + " ORDER BY _id DESC;";
    	Cursor cursor = myDB.rawQuery(query, null);
    	
    	if (cursor.moveToNext()) {
    		return cursor.getInt(cursor.getColumnIndex("_id"));
    	}
    	
    	return -1;
    }
    
	public void saveExposureScores(int id, String[] scores) {
		
		String exposureScore = computeExposureScore(scores);
		String query = "UPDATE " + TABLE_SITE + " SET exposure_score = '" + exposureScore + "' WHERE _id = " + id + ";";
    	myDB.execSQL(query);
		
		for (int i = 0; i < scores.length; i++) {
			String query2 = "INSERT INTO " + TABLE_ANSWER_EXPOSURE + " (site_id, item_id, value) VALUES ('" + id + "', '" + (i+1) + "', '" + scores[i] + "');";
	    	myDB.execSQL(query2);	
		}
	}
	
	private String computeExposureScore(String[] scores){
		String result = "N/A";
		
		String waveExposure = scores[0];
		String seaSurfaceTemp = scores[1];
		String rainfall = scores[2];
		
		String sstAndRainfall = computeScore(seaSurfaceTemp,rainfall);
		
		result = computeScore(sstAndRainfall,waveExposure);
		
		return result;
	}
	
	private String computeScore(String score1, String score2) {
		
		String score="";
		
		if(score1.equalsIgnoreCase(score2))
		{
			score = score1;
		}
		else if((score1+score2).contains("L") && (score1+score2).contains("M"))
		{
			score = "L";
		}
		else if((score1+score2).contains("M") && (score1+score2).contains("H"))
		{
			score = "H";
		}
		else if((score1+score2).contains("H") && (score1+score2).contains("L"))
		{
			score = "M";
		}
		
		return score;
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
