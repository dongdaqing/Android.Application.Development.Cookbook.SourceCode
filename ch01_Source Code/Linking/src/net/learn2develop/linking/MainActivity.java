package net.learn2develop.linking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        //---link to Activity2---
        Intent i = new Intent("net.learn2develop.Activity2");
        startActivity(i);
        */

        /*
        //---link to Activity2---
        Intent i = new Intent();
        i.setAction("net.learn2develop.Activity2");
        startActivity(i);
        */
        
        /*
        //
        Intent i = new Intent(this, Activity2.class);
        startActivity(i);
        */
        
        
        Intent i = new Intent("net.learn2develop.Activity2");
        startActivity(Intent.createChooser(i, "Choose an application"));
        
        
        /*
        //---the following will never link to Activity2---
        Intent i = new Intent(this, Activity2.class);
        startActivity(Intent.createChooser(i, "Choose an application"));
        */        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
