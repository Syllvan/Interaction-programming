package com.example.johan.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by johan on 2015-12-10.
 */
public class InteractiveSearcher extends LinearLayout {

    private ListPopupWindow popup;
    private int ID = 0;
    private String searchString = "";
    private Context context;
    private List<String> items;
    private MyAdapter adapter;
    private EditText searchField;

    public InteractiveSearcher(Context c){
        super(c);
        context = c;
        this.init();
        searchField = new EditText(context);
        searchField.addTextChangedListener(onTextChange);
        searchField.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        addView(searchField);
    }

    private void init(){
        this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        popup = new ListPopupWindow(context);
        items = new ArrayList<>();
        adapter = new MyAdapter(context, items);
        popup.setAdapter(adapter);
        popup.setAnchorView(this);
        popup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                searchField.setText(adapterView.getItemAtPosition(position).toString());
            }
        });
        //this.addTextChangedListener(onTextChange);
    }

    TextWatcher onTextChange = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            popup.dismiss();
            searchString = s.toString();
            data newThread = new data();
            if(!searchString.equals("")) {
                newThread.execute(new NameData(++ID, searchString));
            }
            else
                ++ID;
        }
        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private class data extends AsyncTask<NameData, Void, String> {

        @Override
        protected String doInBackground(NameData... nameData){
            int id = nameData[0].getID();
            String search = nameData[0].getString();

            HttpURLConnection urlConnection = null;
            URL url;
            try {
                url = new URL("http://flask-afteach.rhcloud.com/getnames/" + id + "/" + search);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                InputStreamReader inRead = new InputStreamReader(in);
                BufferedReader buffRead = new BufferedReader(inRead);

                StringBuffer sBuffer = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");

                while ((line = buffRead.readLine()) != null) {
                    sBuffer.append(line + NL);
                }
                buffRead.close();
                String result = sBuffer.toString();

                System.out.println("names: " + result);
                return result;
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                try {
                    urlConnection.disconnect();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            NameResults theResult = InteractiveSearcher.this.getJsonResults(s);
            String theSearchField = searchField.getText().toString();
            if(theSearchField.matches("") || theResult.getID() == -1){
                InteractiveSearcher.this.popup.dismiss();
            }
            else if (InteractiveSearcher.this.ID == theResult.getID()) {
                InteractiveSearcher.this.items = theResult.getList();
                InteractiveSearcher.this.adapter = new MyAdapter(context,items);
                InteractiveSearcher.this.popup.setAdapter(adapter);
                InteractiveSearcher.this.popup.show();
            }
            else{
                InteractiveSearcher.this.popup.dismiss();
            }
        }
    }

    private class NameData {
        private int ID;
        private String name;

        public NameData(int i, String n){
            ID = i;
            name = n;
        }
        public int getID() {
            return ID;
        }
        public String getString() {
            return name;
        }
    }

    private class NameResults {
        private int ID;
        private List<String> name;

        public NameResults(int i, List<String> n){
            ID = i;
            name = n;
        }
        public int getID() {
            return ID;
        }
        public List<String> getList() { return name; }
    }

    private NameResults getJsonResults(String data) {
        List<String> results = new ArrayList<>();
        int theID = -1;

        try {
            JSONObject JSON = new JSONObject(data);
            JSONArray jsonArray = JSON.getJSONArray("result");
            theID = JSON.getInt("id");
            for (int i = 0; i < jsonArray.length(); i++) {
                results.add(jsonArray.get(i).toString());
                if(i==4){break;}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new NameResults(ID, results);
    }
}


