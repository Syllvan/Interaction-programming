package com.example.johan.lab2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;


import java.util.Arrays;

public class Lab2 extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    List<String> light;
    List<String> medium;
    List<String> dark;
    HashMap<String, List<String>> listDataChild;
    EditText TextField;

    int lastExpanded = -1;
    int lastGroup = -1;
    int lastChild = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2);
        TextField = (EditText) findViewById(R.id.editText);
        TextField.setText("€/€");

        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding parent
        listDataHeader.add("light");
        listDataHeader.add("medium");
        listDataHeader.add("dark");
        // Adding children
        light = new ArrayList<String>();
        light.add("green");
        light.add("red");
        light.add("blue");

        medium = new ArrayList<String>();
        medium.add("green");
        medium.add("red");
        medium.add("blue");

        dark = new ArrayList<String>();
        dark.add("green");
        dark.add("red");
        dark.add("blue");

        listDataChild.put(listDataHeader.get(0), light);
        listDataChild.put(listDataHeader.get(1), medium);
        listDataChild.put(listDataHeader.get(2), dark);

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        expListView.setFocusableInTouchMode(true);
        expListView.setItemsCanFocus(true);

        //Handle the group click navigation
        expListView.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (expListView.isGroupExpanded(groupPosition)) {
                    //close expanded group and set text
                    expListView.collapseGroup(groupPosition);
                    TextField.setText("€/€");
                } else {
                    //set text text to path
                    String groupName = getGroupName(groupPosition);
                    TextField.setText("/" + groupName);
                }
                return true;
            }
        });

        //Handles what happens when you click on a child object./*
        expListView.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String groupName = getGroupName(groupPosition);
                String childName = getChildName(groupPosition, childPosition);
                //set text text to path
                TextField.setText("/" + groupName + "/" + childName);
                return true;
            }
        });

        TextField = (EditText)findViewById(R.id.editText);
        TextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("") && s.charAt(0) == '/') {

                    //modify inputstring to first word(groupname) and second word(childname)
                    String groupName = getGroupString(s.toString());
                    String childName = getChildString(s.toString());
                    //find index of where in listDataheader and listDataChild the strings are.
                    int groupPos = groupPos(groupName);
                    int childPos = childPos(groupName, childName);

                    //if string does not exist in listDataHeader
                    if (groupPos != -1) {
                        expListView.expandGroup(groupPos);
                        TextField.setBackgroundColor(Color.parseColor("#f4f4f4"));
                    }
                    else {
                        expListView.collapseGroup(lastExpanded);
                        if (!groupName.equals("")) {
                            TextField.setBackgroundColor(Color.RED);
                        }
                    }

                    int pos = getPos(groupName, childName);

                    //if string does not exist in listDataChild
                    if (childPos != -1) {
                        int index =expListView.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPos, childPos));

                        expListView.setItemChecked(index, true);
                        TextField.setBackgroundColor(Color.parseColor("#f4f4f4"));
                        lastChild=childPos;
                        lastGroup=groupPos;
                        listAdapter.setIds(groupPos, childPos);
                    }
                    else {
                        if(lastChild!=-1 && lastGroup!=-1) {
                            try{
                                int index = expListView.getFlatListPosition(ExpandableListView.getPackedPositionForChild(lastGroup, lastChild));
                                expListView.setItemChecked(index, false);}catch(Exception e){}
                            System.out.println("Entering");
                        }
                        if (!childName.equals("")) {
                            TextField.setBackgroundColor(Color.RED);
                        }
                    }

                } else if (s.toString().equals("")) {
                    TextField.setBackgroundColor(Color.parseColor("#f4f4f4"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //save the last expanded position
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                expListView.clearChoices();
                lastExpanded = groupPosition;
            }
        });

        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                expListView.clearChoices();
            }
        });
    }
    //returns index of where string is in listDataHeader if it exists
    //found similar functions in a tutorial
    private int groupPos(String groupString) {
        int index = -1;

        for (int i = 0; i < listDataHeader.size(); i++) {
            if (listDataHeader.get(i).startsWith(groupString) && !groupString.equals("")) {
                index = i;
                break;
            }
        }
        return index;
    }

    //returns index of where string is in listDataChild if it exists
    private int childPos(String groupName, String childName) {
        int index = -1;
        if (!childName.equals("")) {
            List<String> listOfChild = listDataChild.get(groupName);

            for (int i = 0; i < listOfChild.size(); i++) {
                if (listOfChild.get(i).startsWith(childName)) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    private int getPos(String groupName, String childName) {
        int index = -1;
        if (!childName.equals("")) {
            for (String key : listDataChild.keySet()) {
                index++;
                for (String child : listDataChild.get(key)) {
                    index++;
                    if (key.equals(groupName) && child.equals(childName))
                        return index;
                }
            }
        }
        return -1;
    }

    //creates string of first word in searchpath (the group)
    private String getGroupString(String s) {
        //removes potential "/" sign
        if (s.charAt(0) == '/') {
            s = s.substring(1, s.length());

            //sets the string to what is before the "/" sign
            //you can also use ".split("/");
            if (s.contains("/")) {
                int index = s.indexOf('/');
                s = s.substring(0, index);
            }
        }
        return s;
    }

    //creates string of second word in searchpath (the child)
    private String getChildString(String s) {
        //removes potential "/" sign
        if (s.charAt(0) == '/') {
            s = s.substring(1, s.length());

            //sets the string to what is after the "/" sign
            if (s.contains("/")) {
                int index = s.indexOf('/');
                s = s.substring(index + 1, s.length());
            } else {
                s = "";
            }
        }
        return s;
    }

    //Get the group name from the position
    private String getGroupName(int groupPosition) {
        return listDataHeader.get(groupPosition);
    }

    //Get the child name from a position
    private String getChildName(int groupPosition, int childPosition) {
        String groupName = listDataHeader.get(groupPosition);
        List<String> theChildren = listDataChild.get(groupName);

        return theChildren.get(childPosition);
    }
}