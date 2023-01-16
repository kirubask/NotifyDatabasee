package com.example.notifydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final  String DB_NAME = "mySqliteDataBase";
    public static final  String TAble_NAME = "users";
    public static final  String COLUMN_ID = "id";
    public static final  String COLUMN_USER_NAME = "username";
    public  static  final String COLUMN_PHONE = "phonenumber";
    public static final  String DB_VERSION= "3";

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null,Integer.parseInt(DB_VERSION));

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TAble_NAME+"("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+COLUMN_USER_NAME+" VARCHAR ,"+COLUMN_PHONE+" VARCHAR)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TAble_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public  boolean addUser( String name,String phone_number){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(COLUMN_USER_NAME,name,COLUMN_PHONE,phonenumber);
//        sqLiteDatabase.insert(TAble_NAME,null,contentValues);
        contentValues.put(COLUMN_USER_NAME,name);
        contentValues.put(COLUMN_PHONE,phone_number);
        return sqLiteDatabase.insert(TAble_NAME,null,contentValues) !=-1;


    }
    public Cursor getUsers(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = " SELECT * FROM "+ TAble_NAME + ";";
        return sqLiteDatabase.rawQuery(sql,null);
    }

}
