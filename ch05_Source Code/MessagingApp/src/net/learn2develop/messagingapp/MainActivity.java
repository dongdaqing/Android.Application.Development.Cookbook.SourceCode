package net.learn2develop.messagingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Intent i = new Intent(android.content.Intent.ACTION_VIEW);
        i.putExtra("address", "5556; 5558; 5560");
        i.putExtra("sms_body", "Greetings!");
        i.setType("vnd.android-dir/mms-sms");
        startActivity(i);
        
        //---check the device manufacturer and model name---        
        String manufacturer = android.os.Build.MANUFACTURER;  //---level 4---
        String model = android.os.Build.MODEL;        
        Toast.makeText(this, manufacturer + ", " +
                             model, Toast.LENGTH_LONG).show();
    }

}
