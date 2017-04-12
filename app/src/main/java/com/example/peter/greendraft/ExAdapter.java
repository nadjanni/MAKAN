package com.example.peter.greendraft;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.peter.greendraft.data.SlotModel;

import java.util.ArrayList;

/**
 * Created by David on 2016/05/13.
 */
public class ExAdapter extends BaseExpandableListAdapter {
    public ArrayList<SlotModel> dayModelList;

    private Context mContext;

    public ExAdapter(ArrayList<SlotModel> arrayList,Context c){
        mContext=c;
        dayModelList=arrayList;
    }



    public String [] slot_titles = {"First Slot","Second Slot","Third Slot","Fourth Slot","Fifth Slot"};

    @Override
    public int getGroupCount() {
        return 5;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 5;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return dayModelList.get(groupPosition).DAY;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if(childPosition==0)
            return dayModelList.get(groupPosition).SLOT1;
        else if(childPosition==1)
            return dayModelList.get(groupPosition).SLOT2;
        else if(childPosition==2)
            return dayModelList.get(groupPosition).SLOT3;
        else if(childPosition==3)
            return dayModelList.get(groupPosition).SLOT4;
        else
            return dayModelList.get(groupPosition).SLOT5;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view;
        TextView header;

        if (convertView == null) {
            view =  LayoutInflater.from(mContext).inflate(R.layout.group_header, parent, false);
        }else {
            view =  convertView;
        }
        header=(TextView)view.findViewById(R.id.group_header);
        _("Group Position "+groupPosition);

        Typeface header_font = Typeface.createFromAsset(mContext.getAssets(), "SAMBAHOLLYC.otf");
        _("DAY "+dayModelList.get(groupPosition).DAY);
        header.setTypeface(header_font);
        header.setText(dayModelList.get(groupPosition).DAY);
//        header.setText("HEADER");
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view;
        TextView m_8208;
        TextView m_8209;
        TextView m_8309;
        TextView m_8310;
        TextView slotTitle;

        Typeface hall_font = Typeface.createFromAsset(mContext.getAssets(), "Dyane Regular.ttf");


        view = LayoutInflater.from(mContext).inflate(R.layout.day_item, parent, false);



        slotTitle=(TextView)view.findViewById(R.id.slot_title);
        Typeface title_font = Typeface.createFromAsset(mContext.getAssets(), "CantataOne-Regular.ttf");
        slotTitle.setTypeface(title_font);
        slotTitle.setText(slot_titles[childPosition]);
        _(dayModelList.get(groupPosition).SLOT1.size()+"");
        _(dayModelList.get(groupPosition).SLOT2.size()+"");

        if ((dayModelList.get(groupPosition).SLOT1.size()>0 && childPosition==0) ||
                (dayModelList.get(groupPosition).SLOT2.size()>0 && childPosition==1 )||
                        (dayModelList.get(groupPosition).SLOT3.size()>0 && childPosition==2) ||
                                (dayModelList.get(groupPosition).SLOT4.size()>0 && childPosition==3 )||
                                        (dayModelList.get(groupPosition).SLOT5.size()>0 && childPosition==4))

           // m_8208 = (TextView)view.findViewById(R.id.m_8208);
        {
            if (childPosition == 0) {
                m_8208 = (TextView) view.findViewById(R.id.m_8208);
                m_8208.setText(dayModelList.get(groupPosition).SLOT1.get(0));
                m_8208.setTypeface(hall_font);
            }
            if (childPosition == 1) {
                m_8208 = (TextView) view.findViewById(R.id.m_8208);
                m_8208.setText(dayModelList.get(groupPosition).SLOT2.get(0));
                m_8208.setTypeface(hall_font);
            }
            if (childPosition == 2) {
                m_8208 = (TextView) view.findViewById(R.id.m_8208);
                m_8208.setText(dayModelList.get(groupPosition).SLOT3.get(0));
                m_8208.setTypeface(hall_font);
            }
            if (childPosition == 3) {
                m_8208 = (TextView) view.findViewById(R.id.m_8208);
                m_8208.setText(dayModelList.get(groupPosition).SLOT4.get(0));
                m_8208.setTypeface(hall_font);
            }
            if (childPosition == 4) {
                m_8208 = (TextView) view.findViewById(R.id.m_8208);
                m_8208.setText(dayModelList.get(groupPosition).SLOT5.get(0));
                m_8208.setTypeface(hall_font);
            }
        }

         if ((dayModelList.get(groupPosition).SLOT1.size()>1 && childPosition==0 )||
                ( dayModelList.get(groupPosition).SLOT2.size()>1 && childPosition==1 )||
                (dayModelList.get(groupPosition).SLOT3.size()>1 && childPosition==2 )||
                (dayModelList.get(groupPosition).SLOT4.size()>1 && childPosition==3 )||
                (  dayModelList.get(groupPosition).SLOT5.size()>1 && childPosition==4)) {
            if (childPosition == 0) {
                m_8209 = (TextView) view.findViewById(R.id.m_8209);
                m_8209.setText(dayModelList.get(groupPosition).SLOT1.get(1));
                m_8209.setTypeface(hall_font);
            }
            if (childPosition == 1) {
                m_8209 = (TextView) view.findViewById(R.id.m_8209);
                m_8209.setText(dayModelList.get(groupPosition).SLOT2.get(1));m_8209.setTypeface(hall_font);
            }
            if (childPosition == 2) {
                m_8209 = (TextView) view.findViewById(R.id.m_8209);
                m_8209.setText(dayModelList.get(groupPosition).SLOT3.get(1));m_8209.setTypeface(hall_font);
            }
            if (childPosition == 3) {
                m_8209 = (TextView) view.findViewById(R.id.m_8209);
                m_8209.setText(dayModelList.get(groupPosition).SLOT4.get(1));m_8209.setTypeface(hall_font);
            }
            if (childPosition == 4) {
                m_8209 = (TextView) view.findViewById(R.id.m_8209);
                m_8209.setText(dayModelList.get(groupPosition).SLOT5.get(1));m_8209.setTypeface(hall_font);
            }
        }



         if ((dayModelList.get(groupPosition).SLOT1.size()>2 && childPosition==0 )||
                ( dayModelList.get(groupPosition).SLOT2.size()>2 && childPosition==1) ||
                        (dayModelList.get(groupPosition).SLOT3.size()>2 && childPosition==2 )||
                                (dayModelList.get(groupPosition).SLOT4.size()>2 && childPosition==3 )||
                                ( dayModelList.get(groupPosition).SLOT5.size()>2 && childPosition==4)) {
            if (childPosition == 0) {
                m_8309 = (TextView) view.findViewById(R.id.m_8309);
                m_8309.setText(dayModelList.get(groupPosition).SLOT1.get(2));m_8309.setTypeface(hall_font);
            }
            if (childPosition == 1) {
                m_8309 = (TextView) view.findViewById(R.id.m_8309);
                m_8309.setText(dayModelList.get(groupPosition).SLOT2.get(2));m_8309.setTypeface(hall_font);
            }
            if (childPosition == 2) {
                m_8309 = (TextView) view.findViewById(R.id.m_8309);
                m_8309.setText(dayModelList.get(groupPosition).SLOT3.get(2));m_8309.setTypeface(hall_font);
            }
            if (childPosition == 3) {
                m_8309 = (TextView) view.findViewById(R.id.m_8309);
                m_8309.setText(dayModelList.get(groupPosition).SLOT4.get(2));m_8309.setTypeface(hall_font);
            }
            if (childPosition == 4) {
                m_8309 = (TextView) view.findViewById(R.id.m_8309);
                m_8309.setText(dayModelList.get(groupPosition).SLOT5.get(2));m_8309.setTypeface(hall_font);
            }
        }


        if (dayModelList.get(groupPosition).SLOT1.size()>3 && childPosition==0 || dayModelList.get(groupPosition).SLOT2.size()>3 &&
                childPosition==1 || dayModelList.get(groupPosition).SLOT3.size()>3 && childPosition==2 ||
                dayModelList.get(groupPosition).SLOT4.size()>3 && childPosition==3 ||dayModelList.get(groupPosition).SLOT5.size()>3
                && childPosition==4)
        { if (childPosition==0) {
                m_8310 = (TextView) view.findViewById(R.id.m_8310);
                m_8310.setText(dayModelList.get(groupPosition).SLOT1.get(3));m_8310.setTypeface(hall_font);
            }
        if (childPosition==1) {
            m_8310 = (TextView) view.findViewById(R.id.m_8310);
            m_8310.setText(dayModelList.get(groupPosition).SLOT2.get(3));m_8310.setTypeface(hall_font);
        }
        if (childPosition==2) {
            m_8310 = (TextView) view.findViewById(R.id.m_8310);
            m_8310.setText(dayModelList.get(groupPosition).SLOT3.get(3));m_8310.setTypeface(hall_font);
        }
        if (childPosition==3) {
            m_8310 = (TextView) view.findViewById(R.id.m_8310);
            m_8310.setText(dayModelList.get(groupPosition).SLOT4.get(3));m_8310.setTypeface(hall_font);
        }
        if (childPosition==4) {
            m_8310 = (TextView) view.findViewById(R.id.m_8310);
            m_8310.setText(dayModelList.get(groupPosition).SLOT5.get(3));m_8310.setTypeface(hall_font);
        }        }

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    private void _(String s){
        Log.d("GREENAPP ", "ADAPTER" + "######" + s);
    }
}
