package net.learn2develop.imagebuttons;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //---Button view---
        Button btn = (Button) findViewById(R.id.imageTextButton6);
        btn.setText("Android");      
        btn.setCompoundDrawablesWithIntrinsicBounds(
      		  0,                       // left
      		  R.drawable.ic_launcher,  // top
      		  0,                       // right
      		  0);                      // bottom

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
