package net.learn2develop.autostartapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootupReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "App started", Toast.LENGTH_LONG).show();

        //---start the main activity of our app---
        Intent i = new Intent(context,MainActivity.class); 
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
        context.startActivity(i); 
    }
}

