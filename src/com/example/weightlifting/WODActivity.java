package com.example.weightlifting;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WODActivity extends Activity {
	
	private Workout wod;
	private WODExerciseListAdapter adapter;
	
	private int no_of_rounds;
	private int current_round;
	private List<String> split_time_list;
	private TextView tv_rounds;
	
	private final int MSG_START_TIMER = 0;
    private final int MSG_STOP_TIMER = 1;
    private final int MSG_UPDATE_TIMER = 2;
    private final int REFRESH_RATE = 100;
    private StopWatch timer;
	private static TextView tv_timer;
	private boolean workout_in_progress;
	private boolean next_round_is_clicked;
	private boolean single_round_wod;
	private boolean workout_is_finished;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wod);
		
		SetUpActivity();
		Bundle bundle = getIntent().getExtras();
		wod = new Workout();
		
		if(bundle != null){
			GsonBuilder gson_builder = new GsonBuilder();
	    	Gson gson = gson_builder.create();
	    	wod = gson.fromJson(bundle.getString(Constants.WOD), Workout.class);
	    	//Log.d(Constants.TAG, "Got wod in ExerciseAdapter: " + wod.getName());
		}
		
		tv_rounds = (TextView) findViewById(R.id.rounds);
		no_of_rounds = wod.getRounds();
		tv_rounds.setText("ROUND " + current_round + "/" + String.valueOf(no_of_rounds));
		
		tv_timer = (TextView) findViewById(R.id.timer);
		tv_timer.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view) {
				if(!workout_in_progress){
					timer_msg_handler.sendEmptyMessage(MSG_START_TIMER);
				}
				else {
					if(current_round < no_of_rounds || single_round_wod){
						next_round_is_clicked = true;
						timer_msg_handler.sendEmptyMessage(MSG_UPDATE_TIMER);
					}
					else {
						timer_msg_handler.sendEmptyMessage(MSG_STOP_TIMER);
					}
				}
			}
		});
		
	    ListView lv_wod_list = (ListView) findViewById(R.id.wod_view);
		adapter = new WODExerciseListAdapter(this, wod);
		lv_wod_list.setAdapter(adapter);       
	}
	
	@Override
	public void onBackPressed (){
		super.onBackPressed();
		timer_msg_handler.sendEmptyMessage(MSG_STOP_TIMER);
	}
	
    private void SetUpActivity() {
		split_time_list = new ArrayList<String>();
	    timer = new StopWatch();
		workout_in_progress = false;
		workout_is_finished = false;
		next_round_is_clicked = false;
		single_round_wod = false;
		no_of_rounds = 0;
		current_round = 1; // Rounds start from 1, not 0
		
		if(no_of_rounds == 1 && current_round == no_of_rounds){
			single_round_wod = true;
		}
		else{
			single_round_wod = false;
		}		
	}

	Handler timer_msg_handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String time = getTimefromMilliseconds(timer.getElapsedTime());
            
            switch (msg.what) {
            case MSG_START_TIMER:
            	//Log.d(Constants.TAG, "START");
                timer.start(); 
                workout_in_progress = true; // reset to false using a reset button
                timer_msg_handler.sendEmptyMessage(MSG_UPDATE_TIMER);
                break;
            case MSG_UPDATE_TIMER: 
            	//Log.d(Constants.TAG, "UPDATE");
            	tv_timer.setText(time);
            	if(next_round_is_clicked){
            		split_time_list.add(time);
            		if(current_round < no_of_rounds) current_round++;
            		tv_rounds.setText("ROUND " + current_round + "/" + String.valueOf(no_of_rounds));
            		next_round_is_clicked = false;
            	} 
            	else {
            		if(single_round_wod){
            			single_round_wod = false;
            			timer_msg_handler.sendEmptyMessage(MSG_STOP_TIMER);
            		}
            		else
            			timer_msg_handler.sendEmptyMessageDelayed(MSG_UPDATE_TIMER,REFRESH_RATE);
            	}
                break;                                  
            case MSG_STOP_TIMER:
            	//Log.d(Constants.TAG, "STOP");
            	if(!workout_is_finished){
            		timer_msg_handler.removeMessages(MSG_UPDATE_TIMER);
            		timer.stop();//stop timer
            		tv_timer.setText(time);
            		workout_is_finished = true;
            	}
                break;
            default:
                break;
            }
        }
        
        @SuppressLint("SimpleDateFormat")
		public String getTimefromMilliseconds(long millis){
        	Date date = new Date(millis);
        	SimpleDateFormat formatter = new SimpleDateFormat("mm:ss:SSS");
        	String time = formatter.format(date);
        	return time;
        }
    };
}
