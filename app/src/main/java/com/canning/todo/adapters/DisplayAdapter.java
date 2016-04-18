package com.canning.todo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import listTable.todo.com.todo.R;

public class DisplayAdapter extends BaseAdapter {
    private Context mContext;

    private ArrayList<String> id;
    private ArrayList<String> title;
    private ArrayList<String> description;
    private ArrayList<String> priority;

    public DisplayAdapter(Context c, ArrayList<String> id,ArrayList<String> title, ArrayList<String> description, ArrayList<String> priority) {
        this.mContext = c;

        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public int getCount() {
        return id.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int pos, View child, ViewGroup parent) {
        Holder mHolder;
        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.listcell, null);

            mHolder = new Holder();

            mHolder.txt_id = (TextView) child.findViewById(R.id.txt_id);
            mHolder.txt_title = (TextView) child.findViewById(R.id.txt_title);
            mHolder.txt_desc = (TextView) child.findViewById(R.id.txt_desc);
            mHolder.txt_priority = (TextView) child.findViewById(R.id.txt_priority);

            child.setTag(mHolder);
        } else {
            mHolder = (Holder) child.getTag();
        }
        mHolder.txt_id.setText(id.get(pos));
        mHolder.txt_title.setText(title.get(pos));
        mHolder.txt_desc.setText(description.get(pos));
        mHolder.txt_priority.setText(priority.get(pos));

        return child;
    }

    public class Holder {
        TextView txt_id;
        TextView txt_title;
        TextView txt_desc;
        TextView txt_priority;
    }
}
