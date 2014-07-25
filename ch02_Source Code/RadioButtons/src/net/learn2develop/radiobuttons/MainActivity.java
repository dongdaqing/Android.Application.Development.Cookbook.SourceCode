package net.learn2develop.radiobuttons;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //---RadioButton---
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rdbGp1);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb1 = (RadioButton) findViewById(R.id.rdb1);
                if (rb1.isChecked()) {
                    Toast.makeText(getBaseContext(), 
                    		"Option 1 checked!", 
                    		Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getBaseContext(), 
                    		"Option 2 checked!", 
                    		Toast.LENGTH_LONG).show();
                }
            }
        });
    }    
}
