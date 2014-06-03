package com.example.weightlifting;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
 
public class WODListActivity extends Activity {
     
    private ListView wod_listview;
    private WorkoutListAdapter adapter;
    private EditText search_bar;
    private List<String> wod_list;
    
    private void loadWods() throws IOException{
        //Log.d(Constants.TAG, "Loading WODs...");
        
        Resources resources = getApplicationContext().getResources();
        InputStream input = resources.openRawResource(R.raw.wods_json);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        wod_list = new ArrayList<String>();
        
        try {
        	String line = "";
        	while ((line = reader.readLine()) != null){
        		wod_list.add(line);
        		//Log.d(Constants.TAG, "Added WOD [" + line +"]");
        	}
        	
        } finally {
        	reader.close();
        }
        
        //Log.d(Constants.TAG, "WODs loading completed.");
    }
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wod_list);
        
        try {
			loadWods();
		} catch (IOException e) {
			//Log.d(Constants.TAG, "Failed to load workouts");
			e.printStackTrace();
		}
         
        wod_listview = (ListView) findViewById(R.id.list_view);
        search_bar = (EditText) findViewById(R.id.search_bar);
        
        search_bar.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable arg0) { }
			@Override
			public void beforeTextChanged(CharSequence cs, int arg1, int arg2, int arg3) { }

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				adapter.getFilter().filter(cs);
			}
        });
        
        adapter = new WorkoutListAdapter(this, wod_list.toArray(new String[wod_list.size()]));
        wod_listview.setAdapter(adapter);       
    }

     
}