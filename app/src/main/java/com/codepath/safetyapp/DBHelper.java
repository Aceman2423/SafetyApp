package com.codepath.safetyapp;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
//import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    private static final String Database_Name = "contactDatabase.db";
    private static final String Table_name = "contacts";
    public static final String col_ID = "ID";
    public static final String col_name = "Name";
    public static final String col_email = "Email";
    public static final String col_location = "Location";
    public static final String col_phone = "Phone";
    private HashMap hp;

    public DBHelper(Context context){
        super(context, Database_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table contacts" + "(id integer primary key, name text, phone text, email text,  location text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("Drop Table if exists contacts");
        onCreate(db);
    }

    public boolean insertContact(String name, String phone, String email, String location){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contValues = new ContentValues();
        contValues.put("name", name);
        contValues.put("phone", phone);
        contValues.put("email", email);
        contValues.put("place", location);
        db.insert("contacts", null, contValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from contacts where id="+id+"", null);
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) android.database.DatabaseUtils.queryNumEntries(db, Table_name);
        return numRows;
    }

    public boolean updateContact(Integer id, String name, String phone, String email, String location){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contValues = new ContentValues();
        contValues.put("name", name);
        contValues.put("phone", phone);
        contValues.put("email", email);
        contValues.put("place", location);
        db.update("contacts", contValues,"id = ? ", new String[]{ Integer.toString(id)});
        return true;
    }

    public Integer deleteContact(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts", "id = ? ", new String[] {Integer.toString(id)} );
    }

    public ArrayList<String> getAllContacts(){
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from contacts ", null);
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(col_name)));
            res.moveToNext();
        }
        return array_list;
    }
}//end class