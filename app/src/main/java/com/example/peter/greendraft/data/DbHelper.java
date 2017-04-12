package com.example.peter.greendraft.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Peter on 5/6/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

        public static final String DATABASE_NAME="favoritesDB";
        public static final int DATABASE_VERSION=1;

        public DbHelper(Context context) {
            super(context,DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
//            String sql = "DELETE FROM " + DataContract.TABLE_NAME;
//            db.execSQL(sql);
//
//            Log.d("DATABASE", "Dropped");
            final String CREATE_SLOTS_TABLE = "CREATE TABLE " + DataContract.TABLE_NAME + "(" +
                    DataContract.KEY+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DataContract.DAY+ " TEXT NOT NULL," +
                    DataContract.ADMIN+ " TEXT NOT NULL," +
                    DataContract.HALL + " TEXT NOT NULL," +
                    DataContract.SLOT_1 + " TEXT NOT NULL," +
                    DataContract.SLOT_2+ " TEXT NOT NULL," +
                    DataContract.SLOT_3+ " TEXT NOT NULL," +
                    DataContract.SLOT_4+ " TEXT NOT NULL," +
                    DataContract.SLOT_5+ " TEXT NOT NULL" +
                    ");";
            db.execSQL(CREATE_SLOTS_TABLE);
            Log.d("DATABASE", "CREATED");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DataContract.TABLE_NAME);
            onCreate(db);

        }
    public void Update(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + DataContract.TABLE_NAME);
        onCreate(db);

    }


    public boolean searchHallAdmin(String s,String col){

        String query = "Select * FROM " + DataContract.TABLE_NAME + " WHERE " + DataContract.HALL + " =  \"" + s +"\""+" AND "+DataContract.ADMIN+" =  \"" +col + "\"";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()==true)
        {return true;}
        else
        {   return false;}
    }

//    private void dropTable() {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        String sql = "drop table " + DataContract.TABLE_NAME;
//        try {
//            db.execSQL(sql);
//        } catch (SQLException e) {
//        }
//    }
//
//
//        public void addNode(SlotModel slotModel){
//            SQLiteDatabase database=this.getWritableDatabase();
//            ContentValues contentValues= new ContentValues();
//            contentValues.put(DataContract.DAY,slotModel.DAY);
//            contentValues.put(DataContract.HALL,slotModel.HALL);
//            contentValues.put(DataContract.SLOT_1,slotModel.SLOT1);
//            contentValues.put(DataContract.SLOT_2,slotModel.SLOT2);
//            contentValues.put(DataContract.SLOT_3,slotModel.SLOT3);
//            contentValues.put(DataContract.SLOT_4,slotModel.SLOT4);
//            contentValues.put(DataContract.SLOT_5,slotModel.SLOT5);
//            Log.d("TILL HERE", "OOK");
//            database.insert(DataContract.TABLE_NAME, null, contentValues);
//            Log.d("DATABASE", "INSERTED");
//            database.close();
//
//        }
//        public ArrayList<SlotModel> getNode(){
//            String selectAll="SELECT * FROM"+DataContract.TABLE_NAME;
//            SQLiteDatabase db= this.getReadableDatabase();
//            Cursor cursor=db.query(DataContract.TABLE_NAME,null,null,null,null,null,null);
//            if(cursor.moveToFirst()){
//
//                do{
//                    SlotModel slotModel =new SlotModel();
//                    slotModel.setActive(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(DataContract.ACTIVE))));
//                    slotModel.setCreated(cursor.getString(cursor.getColumnIndex(DataContract.DATE_CREATED)));
//                    slotModel.setOwner(cursor.getString(cursor.getColumnIndex(DataContract.OWNER)));
//                    slotModel.setType(cursor.getString(cursor.getColumnIndex(DataContract.TYPE)));
//                    slotModel.setId(cursor.getInt(cursor.getColumnIndex(DataContract.ID)));
//                    slotModel.setAlarm(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(DataContract.ALARM))));
//                    myList.add(slotModel);
//                }while(cursor.moveToNext());
//            }
//            return myList;
//        }
//        public boolean searchDbById(int s){
//            String query = "Select * FROM " + DataContract.TABLE_NAME + " WHERE " + DataContract.ID + " =  \"" + s + "\"";
//            SQLiteDatabase db = this.getWritableDatabase();
//
//            Cursor cursor = db.rawQuery(query, null);
//            if(cursor.moveToFirst()==true)
//            {db.close();
//                return true;}
//            else
//            {db.close(); return false;}
//        }
//    public boolean searchDbByColumn(int s,String col,String value){
//        String query = "Select * FROM " + DataContract.TABLE_NAME + " WHERE " + DataContract.ID + " =  \"" + s +"\""+" AND "+col+" =  \""
//                +value + "\"";
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        Cursor cursor = db.rawQuery(query, null);
//        if(cursor.moveToFirst()==true)
//        {db.close();return true;}
//        else
//        {db.close();    return false;}
//    }
//
////        public boolean deleteslotModel(int s) {
////
////            boolean result = false;
////
////            String query = "Select * FROM " + DataContract.TABLE_NAME + " WHERE " + DataContract.ID + " =  \"" + s + "\"";
////
////            SQLiteDatabase db = this.getWritableDatabase();
////
////            Cursor cursor = db.rawQuery(query, null);
////
////            if (cursor.moveToFirst()) {
////                db.delete(DataContract.TABLE_NAME, DataContract.ID + " = ?",
////                        new String[] { String.valueOf(s) });
////                cursor.close();
////                result = true;
////            }
////            db.close();
////            return result;
////        }
////
    }


