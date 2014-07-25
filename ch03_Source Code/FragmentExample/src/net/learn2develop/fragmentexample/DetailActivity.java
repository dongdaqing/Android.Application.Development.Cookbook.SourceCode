package net.learn2develop.fragmentexample;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);     

        //---if the user switches to landscape mode; destroy the activity---
        if (getResources().getConfiguration().orientation == 
                Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        //---get the data passed from the master fragment---
        String name = getIntent().getStringExtra("president");
        TextView view = (TextView) findViewById(R.id.txtSelectedPresident);
        view.setText("You have selected " + name);
    }
}
