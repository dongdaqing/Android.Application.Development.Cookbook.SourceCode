package net.learn2develop.intentfilters;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Intent i = new Intent("net.learn2develop.MyBrowser");
        i.setData(Uri.parse("http://www.amazon.com"));
        startActivity(i);
        */
        
        /*
        Intent i = new Intent(android.content.Intent.ACTION_VIEW);
        i.setData(Uri.parse("http://www.amazon.com"));
        startActivity(i);
        */
        
        Intent i = new Intent(android.content.Intent.ACTION_VIEW);
        
        //---if you are using setType(), no need to use setData()---
        //i.setData(Uri.parse("http://www.amazon.com"));
        
        //---indicates the type that the target activity will handle--- 
        i.setType("text/html");
        i.putExtra("URL", "http://www.wrox.com");
        startActivity(i);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
