package com.example.peter.greendraft;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ExpandableListView;

import com.example.peter.greendraft.data.DataContract;
import com.example.peter.greendraft.data.DbHelper;
import com.example.peter.greendraft.data.SlotModel;

import java.util.ArrayList;

/**
 * Created by Peter on 5/13/2016.
 */
public class Slots extends AppCompatActivity {
    public ArrayList<SlotModel> slotModelArrayList=new ArrayList<>();
    public String[] DaysArray={"Sun","Mon","Tue","Wed","Thu"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slots);
        ExpandableListView listView = (ExpandableListView)findViewById(R.id.expandablelist);

        DbHelper helper=new DbHelper(this);
        SQLiteDatabase database=helper.getWritableDatabase();
        Log.d("DATABASE", "created");



        for(int i=0;i<5;i++)
        {
            SlotModel model=new SlotModel();

            ArrayList<String> Slot1True=new ArrayList();
            ArrayList<String> Slot2True=new ArrayList();
            ArrayList<String> Slot3True=new ArrayList();
            ArrayList<String> Slot4True=new ArrayList();
            ArrayList<String> Slot5True=new ArrayList();
            ArrayList<String> Slot1False=new ArrayList();
            ArrayList<String> Slot2False=new ArrayList();
            ArrayList<String> Slot3False=new ArrayList();
            ArrayList<String> Slot4False=new ArrayList();
            ArrayList<String> Slot5False=new ArrayList();

            String query = "Select * FROM " + DataContract.TABLE_NAME + " WHERE " +
                DataContract.DAY + " =  \"" + DaysArray[i] + "\"" ;
            Cursor cursor= database.rawQuery(query, null);
            _("Cursor Size "+cursor.getCount());

            if(cursor.moveToFirst())
        {
            model.DAY=cursor.getString(cursor.getColumnIndex(DataContract.DAY));
//            boolean SLOT1,SLOT2,SLOT3,SLOT4=true;
            do{

               if(cursor.getString(cursor.getColumnIndex(DataContract.SLOT_1)).equals("false"))
               {if(!Slot1False.contains(cursor.getString(cursor.getColumnIndex(DataContract.HALL))))
                   Slot1False.add(cursor.getString(cursor.getColumnIndex(DataContract.HALL)));}
                else
               if(!Slot1True.contains(cursor.getString(cursor.getColumnIndex(DataContract.HALL))))
                    Slot1True.add(cursor.getString(cursor.getColumnIndex(DataContract.HALL)));
                if(cursor.getString(cursor.getColumnIndex(DataContract.SLOT_2)).equals("false"))
                {if(!Slot2False.contains(cursor.getString(cursor.getColumnIndex(DataContract.HALL))))
                    Slot2False.add(cursor.getString(cursor.getColumnIndex(DataContract.HALL)));}
                else
                if(!Slot2True.contains(cursor.getString(cursor.getColumnIndex(DataContract.HALL))))
                    Slot2True.add(cursor.getString(cursor.getColumnIndex(DataContract.HALL)));
//                {if(!model.SLOT2.contains(cursor.getString(cursor.getColumnIndex(DataContract.HALL))))
//                {model.SLOT2.add(cursor.getString(cursor.getColumnIndex(DataContract.HALL)));_("SLOT2 ADDED"+cursor.getString(cursor.getColumnIndex(DataContract.HALL)));}}
//                else
//                if(model.SLOT2!=null && model.SLOT2.contains(cursor.getString(cursor.getColumnIndex(DataContract.HALL))))
//                {model.SLOT2.remove(cursor.getString(cursor.getColumnIndex(DataContract.HALL)));_("SLOT2 REMOVED"+cursor.getString(cursor.getColumnIndex(DataContract.HALL)));}
                if(cursor.getString(cursor.getColumnIndex(DataContract.SLOT_3)).equals("false"))
                {if(!Slot3False.contains(cursor.getString(cursor.getColumnIndex(DataContract.HALL))))
                    Slot3False.add(cursor.getString(cursor.getColumnIndex(DataContract.HALL)));}
                else
                if(!Slot3True.contains(cursor.getString(cursor.getColumnIndex(DataContract.HALL))))
                    Slot3True.add(cursor.getString(cursor.getColumnIndex(DataContract.HALL)));
//                {if(!model.SLOT3.contains(cursor.getString(cursor.getColumnIndex(DataContract.HALL))))
//                    model.SLOT3.add(cursor.getString(cursor.getColumnIndex(DataContract.HALL)));}
//                else
//                if(model.SLOT3!=null && model.SLOT3.contains(cursor.getString(cursor.getColumnIndex(DataContract.HALL))))
//                    model.SLOT3.remove(cursor.getString(cursor.getColumnIndex(DataContract.HALL)));
                if(cursor.getString(cursor.getColumnIndex(DataContract.SLOT_4)).equals("false"))
                {if(!Slot4False.contains(cursor.getString(cursor.getColumnIndex(DataContract.HALL))))
                    Slot4False.add(cursor.getString(cursor.getColumnIndex(DataContract.HALL)));}
                else
                if(!Slot4True.contains(cursor.getString(cursor.getColumnIndex(DataContract.HALL))))
                    Slot4True.add(cursor.getString(cursor.getColumnIndex(DataContract.HALL)));
//                {if(!model.SLOT4.contains(cursor.getString(cursor.getColumnIndex(DataContract.HALL))))
//                    model.SLOT4.add(cursor.getString(cursor.getColumnIndex(DataContract.HALL)));}
//                else
//                if(model.SLOT4!=null && model.SLOT4.contains(cursor.getString(cursor.getColumnIndex(DataContract.HALL))))
//                    model.SLOT4.remove(cursor.getString(cursor.getColumnIndex(DataContract.HALL)));
                if(cursor.getString(cursor.getColumnIndex(DataContract.SLOT_5)).equals("false"))
                {if(!Slot5False.contains(cursor.getString(cursor.getColumnIndex(DataContract.HALL))))
                    Slot5False.add(cursor.getString(cursor.getColumnIndex(DataContract.HALL)));}
                else
                if(!Slot5True.contains(cursor.getString(cursor.getColumnIndex(DataContract.HALL))))
                    Slot5True.add(cursor.getString(cursor.getColumnIndex(DataContract.HALL)));
//                {if(!model.SLOT5.contains(cursor.getString(cursor.getColumnIndex(DataContract.HALL))))
//                    model.SLOT5.add(cursor.getString(cursor.getColumnIndex(DataContract.HALL)));}
//                else
//                if(model.SLOT5!=null && model.SLOT5.contains(cursor.getString(cursor.getColumnIndex(DataContract.HALL))))
//                    model.SLOT5.remove(cursor.getString(cursor.getColumnIndex(DataContract.HALL)));

//                _(model.DAY);
            }while(cursor.moveToNext());



        }
            _("NAMEDay " + model.DAY);
            for (int k =0;k<Slot1False.size();k++)
            {if (Slot1True.contains(Slot1False.get(k)))
                Slot1False.remove(k);
            }
            for (int k =0;k<Slot2False.size();k++)
            {if (Slot2True.contains(Slot2False.get(k)))
                Slot2False.remove(k);
            }
            for (int k =0;k<Slot3False.size();k++)
            {if (Slot3True.contains(Slot3False.get(k)))
                Slot3False.remove(k);
            }
            for (int k =0;k<Slot4False.size();k++)
            {if (Slot4True.contains(Slot4False.get(k)))
                Slot4False.remove(k);
            }
            for (int k =0;k<Slot5False.size();k++)
            {if (Slot5True.contains(Slot5False.get(k)))
                Slot5False.remove(k);
            }
            model.SLOT1=Slot1False;
            model.SLOT2=Slot2False;
            model.SLOT3=Slot3False;
            model.SLOT4=Slot4False;
            model.SLOT5=Slot5False;
            slotModelArrayList.add(i, model);
            model=null;
            if (i != 0)
                _("DAYS AHEEH " + slotModelArrayList.get(i-1).DAY);
            _("DAYS AHEEH "+slotModelArrayList.get(i).DAY);

            _("ARRAY SIZE " + slotModelArrayList.size());
        }

        for(int j = 0; j < 5; j++) {
            _("FULL ARRAY "+j+" "+slotModelArrayList.get(j).SLOT1.toString());
            _("FULL ARRAY "+j+" "+slotModelArrayList.get(j).SLOT2.toString());
            _("FULL ARRAY "+j+" "+slotModelArrayList.get(j).SLOT3.toString());}

        ExAdapter adapter=new ExAdapter(slotModelArrayList,this);
        listView.setAdapter(adapter);
        }


    private void _(String s){
        Log.d("GREENAPP ", "SLOTS " + "######" + s);
    }
}














//
//if(cursor.moveToFirst())
//        {
//        do{
//
//        _(cursor.getString(cursor.getColumnIndex(DataContract.DAY)));
//        }while(cursor.moveToNext());
//        }

//        String query = "Select * FROM " + DataContract.TABLE_NAME + " WHERE " + DataContract.DAY + " =  \"" + "Sun" + "\"";
//        Cursor cursor = database.rawQuery(query, null);
//        if(cursor.moveToFirst())
//        {
//            do{
//                _("CURSON "+cursor.getString(cursor.getColumnIndex(DataContract.SLOT_1)));
//            }while(cursor.moveToNext());
//        }
//        }
//
//    private void _(String s){
//        Log.d("GREENAPP ", "SLOTS " + "######" + s);
//    }
//    public ArrayList<String> getStrings(Cursor c)
//    {
//        ArrayList slot=null;
//
//
//        return slot;