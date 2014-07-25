package com.example.usingtimepicker;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {
	TimePicker timePicker;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
    }

    public void btnClick(View view) {
    	NumberFormat formatter = new DecimalFormat("00");
        Toast.makeText(getBaseContext(), 
                "Time selected:" + 
                  timePicker.getCurrentHour() + 
                ":" + formatter.format(timePicker.getCurrentMinute()), 
                Toast.LENGTH_SHORT).show();
    }   
    
}
