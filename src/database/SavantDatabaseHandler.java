package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SavantDatabaseHandler extends SQLiteOpenHelper{

	private static final String DB_NAME = "savant.sqlite3";
	private static final int DB_VERSION = 1;
	
	
	private final Context myContext;
	
	public SavantDatabaseHandler(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
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
