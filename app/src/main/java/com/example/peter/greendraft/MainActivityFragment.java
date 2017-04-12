package com.example.peter.greendraft;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peter.greendraft.data.DbHelper;
import com.example.peter.greendraft.data.GetData;

import java.util.Calendar;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    public MainActivityFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    String today="";

    int Slot=1;
//    static Calendar c;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);


        Calendar c = Calendar.getInstance();
        _("C "+c.toString());
        _(String.valueOf(c.get(Calendar.DAY_OF_WEEK)));
        _("Time "+c.get(Calendar.HOUR_OF_DAY)+" "+c.get(Calendar.MINUTE));

        switch (c.get(Calendar.DAY_OF_WEEK)){
            case 1: today="Sun";
                break;
            case 2: today="Mon";
                break;
            case 3: today="Tue";
                break;
            case 4: today="Wed";
                break;
            case 5: today="Thu";
                break;
        }
        if(c.get(Calendar.HOUR_OF_DAY)<10)
            Slot=1;
        else if (c.get(Calendar.HOUR_OF_DAY)==10 || (c.get(Calendar.HOUR_OF_DAY)==11 && c.get(Calendar.MINUTE)<=45))
            Slot=2;
        else if ((c.get(Calendar.HOUR_OF_DAY)==11&&c.get(Calendar.MINUTE)>45)||(c.get(Calendar.HOUR_OF_DAY)==12)
                || (c.get(Calendar.HOUR_OF_DAY)==13 && c.get(Calendar.MINUTE)<=45))
            Slot=3;
        else if((c.get(Calendar.HOUR_OF_DAY)==13&&c.get(Calendar.MINUTE)>45)||(c.get(Calendar.HOUR_OF_DAY)==14)
                || (c.get(Calendar.HOUR_OF_DAY)==15 && c.get(Calendar.MINUTE)<=45))
//((c.get(Calendar.HOUR_OF_DAY)>=13&&c.get(Calendar.MINUTE)>45) && (c.get(Calendar.HOUR_OF_DAY)<=15 && c.get(Calendar.MINUTE)<=30))
            Slot=4;
        else if ((c.get(Calendar.HOUR_OF_DAY)==15&&c.get(Calendar.MINUTE)>45)||(c.get(Calendar.HOUR_OF_DAY)==16)
                || (c.get(Calendar.HOUR_OF_DAY)==17 && c.get(Calendar.MINUTE)<=45))
//                ((c.get(Calendar.HOUR_OF_DAY)>=15&&c.get(Calendar.MINUTE)>30) && (c.get(Calendar.HOUR_OF_DAY)<=17 && c.get(Calendar.MINUTE)<=15))
            Slot=5;
        Log.d("Date Now is ",today);
        Log.d("Slot Now is ",Slot+"");
        ImageView checkByDays = (ImageView)rootView.findViewById(R.id.checkbydays);
        ImageView findAPlace= (ImageView)rootView.findViewById(R.id.findaplace);
//        Button myTables = (Button)rootView.findViewById(R.id.mytable);
        TextView login = (TextView)rootView.findViewById(R.id.login);
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "Sofia-Regular.otf");
        login.setTypeface(custom_font);
        login.setClickable(true);
        checkByDays.setClickable(true);
        findAPlace.setClickable(true);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),Login.class);
                startActivity(i);
            }
        });
        checkByDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getActivity(),Slots.class);
                startActivity(i);
            }
        });
        findAPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getActivity(),SingleSlot.class);
                i.putExtra("today",today);
                i.putExtra("slot",  Slot);
                startActivity(i);
            }
        });
//        myTables.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), Slots.class);
//                startActivity(i);
//
//            }
//        });
        return rootView;

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        // Actions according to the item selected
        if (id == R.id.refresh) {
            DbHelper helper=new DbHelper(this.getActivity());
            SQLiteDatabase database=helper.getWritableDatabase();
            Log.d("DATABASE", "created");
            ////////////////////////DELETE DATABASE///////////////////////////////////
//            database.delete(DataContract.TABLE_NAME, null, null);
            ////////////////////////DELETE DATABASE///////////////////////////////////
            new GetData(this.getActivity(),database,helper).execute(0);
        }


        return super.onOptionsItemSelected(item);
    }
    private void _(String s){
        Log.d("MY APPLICATION", "MAIN ACT" + "######" + s);
    }
}
