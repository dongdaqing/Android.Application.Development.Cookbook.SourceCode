package net.learn2develop.blockoutgoingcall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class OutgoingCallsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String outgoingNumber = 
            intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER).toString();
        if (outgoingNumber.contentEquals("1234567")) {
            setResultData(null);
            Toast.makeText(context, "This call is not allowed!",
                    Toast.LENGTH_LONG).show();
        }
    }
}

