package com.example.weightlifting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WorkoutListAdapter extends ArrayAdapter<String>{
	
	private final Context context;
	private String[] wod_list;
	private Workout wod;

	public WorkoutListAdapter(Context context, String[] wod_list) {
		super(context, R.layout.wod_list_item, wod_list);
		this.wod_list = wod_list;
		this.context = context;
	}
	
	@Override
	public View getView(final int position, final View convertView, ViewGroup parent) {
		
		GsonBuilder gson_builder = new GsonBuilder();
    	Gson gson = gson_builder.create();
    	final String wod_json = wod_list[position];
    	wod = gson.fromJson(wod_json, Workout.class);
    	//Log.d(Constants.TAG, "Got wod: " + wod.getName());

		LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View row_view = inflater.inflate(R.layout.wod_list_item, null);
		TextView wod_name = (TextView) row_view.findViewById(R.id.wod_name);
		wod_name.setText(wod.getName());
		
		row_view.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Context this_context = v.getContext();
				Bundle bundle = new Bundle();
				bundle.putString(Constants.WOD, wod_json);
				Intent intent = new Intent(this_context, WODActivity.class);
				intent.putExtras(bundle);
				this_context.startActivity(intent);
			}
		});
		 
		return row_view;
	}
}
