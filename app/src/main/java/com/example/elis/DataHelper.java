package com.example.elis;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "perangkat.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    public DataHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public DataHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        String sql = "create table perangkat (id integer primary key, jenis text null, ruang text null, timestamp text null);";
        Log.d("Data","onCreate: "+sql);
        db.execSQL(sql);

        db.execSQL("create table pemadaman (id integer primary key, hari text , tanggal text , waktu text , tempat text , timestamp text);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

    public long insertInfo(String jenis, String ruangan, String timestamp){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("jenis", jenis);
        values.put("Ruang", ruangan);
        values.put("Timestamp",timestamp);

        long id = db.insert("perangkat",null,values);
        db.close();
        return  id;
    }

    public ArrayList<kelolacardview> getAllData(String orderBy) {
        ArrayList<kelolacardview> arrayList = new ArrayList<>();

        String selectQuery = "select * from perangkat order by " + orderBy;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToNext()){
            do {
                kelolacardview kll = new kelolacardview(
                        ""+cursor.getInt(cursor.getColumnIndex("id")),
                        ""+cursor.getString(cursor.getColumnIndex("jenis")),
                        ""+cursor.getString(cursor.getColumnIndex("ruang")),
                        ""+cursor.getInt(cursor.getColumnIndex("timestamp"))
                );
                arrayList.add(kll);
            } while (cursor.moveToNext());
        }

        db.close();
        return arrayList;
    }

    public int jumlahPerangkat(){
        int jumlah=0;

        String sql = "SELECT COUNT(*) FROM perangkat";
        Cursor cursor = getReadableDatabase().rawQuery(sql,null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            jumlah=cursor.getInt(0);
        }
        cursor.close();
        return jumlah;
    }

    public ArrayList<pemadamanCardview> getAllDataPemadaman(String orderBy) {
        ArrayList<pemadamanCardview> arrayList = new ArrayList<>();

        String selectQuery = "select * from pemadaman order by " + orderBy;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToNext()){
            do {
                pemadamanCardview pemadam = new pemadamanCardview(
                        ""+cursor.getInt(cursor.getColumnIndex("id")),
                        ""+cursor.getString(cursor.getColumnIndex("hari")),
                        ""+cursor.getString(cursor.getColumnIndex("tempat")),
                        ""+cursor.getString(cursor.getColumnIndex("tanggal")),
                        ""+cursor.getString(cursor.getColumnIndex("waktu")),
                        ""+cursor.getInt(cursor.getColumnIndex("timestamp"))
                );
                arrayList.add(pemadam);
            } while (cursor.moveToNext());
        }

        db.close();
        return arrayList;
    }
}
