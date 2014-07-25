package net.learn2develop.usingdatepicker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

public class MainActivity extends Activity {
    DatePicker datePicker;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        datePicker = (DatePicker) findViewById(R.id.datePicker);
    }

    public void btnClick(View view) {
        Toast.makeText(getBaseContext(),
                "Date selected:" + (datePicker.getMonth() + 1) + 
                "/" + datePicker.getDayOfMonth() +
                "/" + datePicker.getYear(), 
                Toast.LENGTH_SHORT).show();
    }    
}
