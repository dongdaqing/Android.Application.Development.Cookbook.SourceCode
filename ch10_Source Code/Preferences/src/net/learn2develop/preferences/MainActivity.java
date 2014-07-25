package net.learn2develop.preferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {
    SharedPreferences prefs;
    String prefName = "MyPref";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //---get the SharedPreferences object---
        prefs = getSharedPreferences(prefName, MODE_PRIVATE);
        
        SharedPreferences.Editor editor = prefs.edit(); 
        
        //---save some values using the SharedPreferences object---        
        editor.putFloat("temperature", 85);
        editor.putBoolean("authenticated", true);
        editor.putString("username", "Wei-Meng Lee"); 
        
        //---saves the values---
        editor.commit();

        readPrefValues();
    }

    public void readPrefValues() {
        prefs = getSharedPreferences(prefName, MODE_PRIVATE);
        float temperature = prefs.getFloat("temperature", 50);
        boolean authenticated = prefs.getBoolean("authenticated", false);
        String username = prefs.getString("username", "");    	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
