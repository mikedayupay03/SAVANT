package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SurveyDatabaseHandler extends SQLiteOpenHelper{

	private static String DB_PATH = "";
	private static String DB_NAME = "survey.db";
	private static int DB_VERSION = 1;
	
	private final Context myContext;
	
	public SurveyDatabaseHandler(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
		this.myContext = context;
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
