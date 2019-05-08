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
        String user_status = "logged_in";
    }

    public LoginDatabaseHelper(Context context){
        super(context, database_name, null, 1);
//        db = this.getWritableDatabase();
    }

    public boolean tableCheck(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + table_name, null);
        if(cursor.getCount()>0) {
            return true;
        } else return false;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE table " + table_name + " (username varchar primary key, password varchar, logged_in varchar)");

        if(!tableCheck()){
            ContentValues initial_data = new ContentValues();
            initial_data.put("username", "user");
            initial_data.put("password", "useruser");
            db.insert(table_name, null, initial_data);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists " + table_name);
        onCreate(db);
    }

    public boolean checkSession(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + table_name + "WHERE logged_in='yes'", null);
        if(cursor.moveToNext()) {
            return true;
        } else return false;
    }



    public boolean loginCheck(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + table_name + "WHERE username=? & password=?", new String[]{username, password});
        if(cursor.getCount()>0) return true;
        else return false;
    }

    public void saveLoginSession(String username, String password){
        db.execSQL("UPDATE " + table_name + "SET logged_in='yes' WHERE username=" + username + ", password=" + password);
    }

    public void killLoginSession(String username, String password){
        db.execSQL("UPDATE " + table_name + "SET logged_in='no' WHERE username=" + username + ", password=" + password);
    }

    public Cursor getLoginSession(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + table_name + "WHERE logged_in = 'yes'", null);
        return cursor;
    }

    public void deleteData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE from " + table_name);
    }

}
