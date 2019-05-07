package umn.ac.id.projectuas_00000014472.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LoginDatabaseHelper extends SQLiteOpenHelper {
    public LoginDatabaseHelper(Context context){
        super(context, "credential.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(username text primary key, password text)");

        ContentValues initial_data = new ContentValues();
        initial_data.put("username", "user");
        initial_data.put("password", "useruser");
        db.insert("user", null, initial_data);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists user");
    }

    public boolean loginCheck(String uname, String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username=? & password=?", new String[] {uname, pass});
        if(cursor.getCount()>0) return true;
        else return false;
    }
}
