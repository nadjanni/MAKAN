package com.example.peter.greendraft;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.peter.greendraft.data.DataContract;
import com.example.peter.greendraft.data.DbHelper;

/**
 * Created by Peter on 5/13/2016.
 */
public class SingleSlot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findmeslot);
        int slot=getIntent().getIntExtra("slot", 1);
//        String slot=(getIntent().getExtras().getString("slot"));
        Log.d("SLOT HERE ", slot+"");
        String day=getIntent().getExtras().getString("today");
        TextView date=(TextView) findViewById(R.id.date_day);
        TextView now_slot=(TextView) findViewById(R.id.date_slot);
        TextView available=(TextView) findViewById(R.id.emptynow);
//        Log.d("DAY NOW ", day);
        date.setText(day);
        now_slot.setText("Slot #"+slot);
        Typeface day_font = Typeface.createFromAsset(getAssets(), "SAMBAHOLLYC.otf");
        Typeface hall_font = Typeface.createFromAsset(getAssets(), "Dyane Regular.ttf");
        date.setTypeface(day_font);
        now_slot.setTypeface(hall_font);
        DbHelper helper=new DbHelper(this);
        String sl="Slot_"+now_slot;
        SQLiteDatabase database=helper.getWritableDatabase();
        String query = "Select * FROM " + DataContract.TABLE_NAME + " WHERE " +
                DataContract.DAY + " =  \"" + day + "\""+" AND "+
                "slot_"+slot + " =  \"" + "false" + "\"" ;
        Log.d("QUERY",query.toString());
        Cursor cursor= database.rawQuery(query, null);
        if(cursor.moveToFirst())
        {available.setText(cursor.getString(cursor.getColumnIndex(DataContract.HALL))+" is available now");
            available.setTypeface(hall_font);}
        else
        {available.setText("No halls are available right now");available.setTypeface(hall_font);}
    }
}
