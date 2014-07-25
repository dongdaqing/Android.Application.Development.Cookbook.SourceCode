package net.learn2develop.usingbroadcastreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
    MyBroadcastReceiver myReceiver;
    IntentFilter intentFilter;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        myReceiver = new MyBroadcastReceiver();
        intentFilter = new IntentFilter("MY_SPECIFIC_ACTION");
    }

    @Override
    public void onResume() {
        super.onResume();
        intentFilter.setPriority(10);
        registerReceiver(myReceiver, intentFilter);
    }    
    
    @Override
    public void onPause() {
        super.onPause();
        //---unregister the receiver---
        unregisterReceiver(myReceiver);
    }    
    
    public void onClick(View view) {
        Intent i = new Intent("MY_SPECIFIC_ACTION");
        i.putExtra("key", "some value from intent");
        //sendBroadcast(i);

        //---allows broadcast to be aborted---
        //---allows broadcast receivers to set priority---
        sendOrderedBroadcast(i, null);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent i) {
            Toast.makeText(context, 
                    "Received broadcast in MyBroadcastReceiver, value received: " + i.getStringExtra("key"),
                    Toast.LENGTH_LONG).show();
        }
    }
    
}
