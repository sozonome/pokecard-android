package umn.ac.id.projectuas_00000014472.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.squareup.picasso.Picasso;

public class LoginDatabaseHelper extends SQLiteOpenHelper {
    public static final String database_name = "credential.db";
    public static final String table_name = "users";

    private SQLiteDatabase db;

    public interface TableColumns{
        String user_username = "username";
        String user_password = "password";
    }

    public LoginDatabaseHelper(Context context){
        super(context, database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE table " + table_name + " (username varchar primary key, password varchar, logged_in varchar)");

        ContentValues contentValues = new ContentValues();
        contentValues.put(TableColumns.user_username, "user");
        contentValues.put(TableColumns.user_password, "useruser");
        db.insert(table_name, null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists " + table_name);
    }

    public boolean loginCheck(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + table_name + " WHERE username='" + username + "' AND password='" + password + "'", null);
        if(cursor.getCount()>0) return true;
        else return false;
    }

}
