package com.example.weightlifting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class MainScreenButton{
	
	Button button;
	
	public Button getButton() { return button; }

	public MainScreenButton(final Context context, final Class<?> to, int id) {
		final Activity from = (Activity) context;
		button = (Button) from.findViewById(id);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v){
				Intent intent = new Intent(context, to);
				context.startActivity(intent);
			}
		});
		
		button.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
		        if(event.getAction() == MotionEvent.ACTION_DOWN) {
		            button.setBackgroundColor(from.getResources().getColor(R.color.textcolor));
		            button.setTextColor(from.getResources().getColor(R.color.transparent));
		        } 
		        else if (event.getAction() == MotionEvent.ACTION_UP) {
		        	button.setBackgroundColor(from.getResources().getColor(R.color.transparent));
		            button.setTextColor(from.getResources().getColor(R.color.textcolor));
		            v.performClick();
		        }
		        return true;
		    }
		});
	}
}
