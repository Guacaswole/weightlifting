package com.example.weightlifting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		MainScreenButton workout = new MainScreenButton(this, WorkoutActivity.class, R.id.workout_button);
		MainScreenButton create_a_workout = new MainScreenButton(this, CreateAWorkoutActivity.class, R.id.create_a_workout_button);
		MainScreenButton wod_list = new MainScreenButton(this, WODListActivity.class, R.id.wod_list_button);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
