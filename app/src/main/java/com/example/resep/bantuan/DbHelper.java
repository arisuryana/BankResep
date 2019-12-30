package com.example.resep.bantuan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.resep.model.Favorite;
import com.example.resep.model.Profile;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="resep2.sql";
    private static final int DATABASE_VERSION=8;

    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE_FAVORITE="CREATE TABLE "+ Favorite.Entry.TABLE_NAME+" ( "+
                Favorite.Entry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                Favorite.Entry.COLUMN_ID+" TEXT,"+
                Favorite.Entry.COLUMN_USERS_ID+" TEXT,"+
                Favorite.Entry.COLUMN_RESEP_ID+" TEXT,"+
                Favorite.Entry.COLUMN_NAMA_PEMILIK+" TEXT,"+
                Favorite.Entry.COLUMN_NAMA_RESEP+" TEXT,"+
                Favorite.Entry.COLUMN_DESKRIPSI+" TEXT,"+
                Favorite.Entry.COLUMN_GAMBAR+" TEXT,"+
                Favorite.Entry.COLUMN_BAHAN+" TEXT,"+
                Favorite.Entry.COLUMN_CARA_MASAK+" TEXT,"+
                Favorite.Entry.COLUMN_NAMA_LOKASI+" TEXT,"+
                Favorite.Entry.COLUMN_NAMA_JENIS+" TEXT,"+
                Favorite.Entry.COLUMN_CREATED_AT+" TEXT );";

        String CREATE_TABLE_PROFILE="CREATE TABLE "+ Profile.Entry.TABLE_NAME+" ( "+
                Profile.Entry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                Profile.Entry.COLUMN_ID+" INTEGER,"+
                Profile.Entry.COLUMN_NAME+" TEXT,"+
                Profile.Entry.COLUMN_EMAIL+" TEXT );";
        Log.d("debug", "Tabel Berhasil dibuat");
        sqLiteDatabase.execSQL(CREATE_TABLE_FAVORITE);
        sqLiteDatabase.execSQL(CREATE_TABLE_PROFILE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_TABLE_FAVORITE="DROP TABLE "+Favorite.Entry.TABLE_NAME+";";
        String DROP_TABLE_PROFILE="DROP TABLE "+Profile.Entry.TABLE_NAME+";";

        sqLiteDatabase.execSQL(DROP_TABLE_FAVORITE);
        sqLiteDatabase.execSQL(DROP_TABLE_PROFILE);
        Log.d("debug", "Tabel Berhasil di UodTW");
        onCreate(sqLiteDatabase);

    }
    //------------------------ Local Resep Favorite -----------------------------//
    public long insertFavorite(String id, String users_id, String resep_id, String nama_pemilik, String nama_resep, String deskripsi,
                               String gambar, String bahan, String cara_masak, String nama_lokasi, String nama_jenis, String created_at){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Favorite.Entry.COLUMN_ID, id);
        contentValues.put(Favorite.Entry.COLUMN_USERS_ID, users_id);
        contentValues.put(Favorite.Entry.COLUMN_RESEP_ID, resep_id);
        contentValues.put(Favorite.Entry.COLUMN_NAMA_PEMILIK, nama_pemilik);
        contentValues.put(Favorite.Entry.COLUMN_NAMA_RESEP, nama_resep);
        contentValues.put(Favorite.Entry.COLUMN_DESKRIPSI, deskripsi);
        contentValues.put(Favorite.Entry.COLUMN_GAMBAR, gambar);
        contentValues.put(Favorite.Entry.COLUMN_BAHAN, bahan);
        contentValues.put(Favorite.Entry.COLUMN_CARA_MASAK, cara_masak);
        contentValues.put(Favorite.Entry.COLUMN_NAMA_LOKASI, nama_lokasi);
        contentValues.put(Favorite.Entry.COLUMN_NAMA_JENIS, nama_jenis);
        contentValues.put(Favorite.Entry.COLUMN_CREATED_AT, created_at);

        long lastID=sqLiteDatabase.insert(Favorite.Entry.TABLE_NAME, null, contentValues);

        return lastID;
    }

    public List<Favorite> selectFavorite(){
        List<Favorite> favorites = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase=getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(Favorite.Entry.TABLE_NAME, null, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
            do {
                String id = cursor.getString(cursor.getColumnIndex(Favorite.Entry.COLUMN_ID));
                String users_id = cursor.getString(cursor.getColumnIndex(Favorite.Entry.COLUMN_USERS_ID));
                String resep_id = cursor.getString(cursor.getColumnIndex(Favorite.Entry.COLUMN_RESEP_ID));
                String nama_pemilik = cursor.getString(cursor.getColumnIndex(Favorite.Entry.COLUMN_NAMA_PEMILIK));
                String nama_resep = cursor.getString(cursor.getColumnIndex(Favorite.Entry.COLUMN_NAMA_RESEP));
                String deskripsi = cursor.getString(cursor.getColumnIndex(Favorite.Entry.COLUMN_DESKRIPSI));
                String gambar = cursor.getString(cursor.getColumnIndex(Favorite.Entry.COLUMN_GAMBAR));
                String bahan = cursor.getString(cursor.getColumnIndex(Favorite.Entry.COLUMN_BAHAN));
                String cara_masak = cursor.getString(cursor.getColumnIndex(Favorite.Entry.COLUMN_CARA_MASAK));
                String nama_lokasi = cursor.getString(cursor.getColumnIndex(Favorite.Entry.COLUMN_NAMA_LOKASI));
                String nama_jenis = cursor.getString(cursor.getColumnIndex(Favorite.Entry.COLUMN_NAMA_JENIS));
                String created_at = cursor.getString(cursor.getColumnIndex(Favorite.Entry.COLUMN_CREATED_AT));

                Favorite tmp = new Favorite(id, users_id, resep_id, nama_pemilik, nama_resep, deskripsi, gambar, bahan, cara_masak,
                        nama_lokasi, nama_jenis, created_at);

                favorites.add(tmp);
            }while(cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();

        return favorites;
    }

    public void deleteFavorite(){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.delete(Favorite.Entry.TABLE_NAME, null, null);
    }

    //----------------------- End of Local Resep Favorite ----------------------//

    //------------------------ Local Profile User ------------------------------//
    public long insertProfile(int id, String name, String email){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Profile.Entry.COLUMN_ID, id);
        contentValues.put(Profile.Entry.COLUMN_NAME, name);
        contentValues.put(Profile.Entry.COLUMN_EMAIL, email);

        long lastID=sqLiteDatabase.insert(Profile.Entry.TABLE_NAME, null, contentValues);

        return lastID;
    }

    public Profile selectProfile(){
        Profile profile;
        profile = null;
        SQLiteDatabase sqLiteDatabase;
        sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(Profile.Entry.TABLE_NAME, null, null, null,null,
                null,null);

        if (cursor != null){
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(cursor.getColumnIndex(Profile.Entry.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(Profile.Entry.COLUMN_NAME));
                String email = cursor.getString(cursor.getColumnIndex(Profile.Entry.COLUMN_EMAIL));

                profile = new Profile(id, name, email);

            } while (cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();

        return profile;
    }

    public void deleteProfile(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(Profile.Entry.TABLE_NAME, null, null);
    }
    //------------------------ End of Local Profile User ------------------------------//


}
