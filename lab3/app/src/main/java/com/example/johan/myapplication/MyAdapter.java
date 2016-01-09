package com.example.johan.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;
/**
 * Created by johan on 2015-12-10.
 */
public class MyAdapter extends BaseAdapter {
    Context context;
    List<String> items;

    public MyAdapter(Context c, List<String> l){
        context = c;
        items = l;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = new Item(context, items.get(position));
        return item;
    }
}