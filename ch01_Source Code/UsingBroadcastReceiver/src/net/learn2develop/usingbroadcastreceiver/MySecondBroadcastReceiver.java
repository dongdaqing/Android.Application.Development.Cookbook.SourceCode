package net.learn2develop.usingbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MySecondBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent i) {
        Toast.makeText(context, 
                "Received broadcast in MySecondBroadcastReceiver; value received: "
                        + i.getStringExtra("key"),
                        Toast.LENGTH_LONG).show();
        //---abort the broadcast----
        abortBroadcast();
    }
}

