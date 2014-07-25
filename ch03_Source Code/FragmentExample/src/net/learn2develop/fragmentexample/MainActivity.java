package net.learn2develop.fragmentexample;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class MainActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        
        if (getResources().getConfiguration().orientation == 
                Configuration.ORIENTATION_LANDSCAPE) { 
            //---landscape mode---
            setContentView(R.layout.activity_landscape_main);            
        } else {
            //---portrait mode---
            setContentView(R.layout.activity_main);            
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
