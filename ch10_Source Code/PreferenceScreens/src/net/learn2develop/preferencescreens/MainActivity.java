package net.learn2develop.preferencescreens;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /*
        Intent i = new Intent("net.learn2develop.AppPreferenceActivity");
        startActivity(i);
        */

        /*
        SharedPreferences appPrefs = getSharedPreferences(
            "net.learn2develop.PreferenceScreens_preferences", 
            MODE_PRIVATE);                
        */
        SharedPreferences appPrefs = 
                getSharedPreferences("appPreferences", MODE_PRIVATE);

        Toast.makeText(getBaseContext(), 
            appPrefs.getString("editTextPref", ""), 
            Toast.LENGTH_LONG).show();    
    }
    
}
