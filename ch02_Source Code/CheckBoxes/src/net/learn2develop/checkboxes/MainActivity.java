package net.learn2develop.checkboxes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //---CheckBox---
        CheckBox checkBox = (CheckBox) findViewById(R.id.chkAutosave);
        checkBox.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) 
                	Toast.makeText(getBaseContext(), 
                			"CheckBox is checked", 
                    		Toast.LENGTH_LONG).show();
                else
                	Toast.makeText(getBaseContext(), 
                			"CheckBox is unchecked", 
                    		Toast.LENGTH_LONG).show();
            }
        });
    }
}
