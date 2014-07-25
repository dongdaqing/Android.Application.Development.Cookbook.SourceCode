package net.learn2develop.autocalls;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;

public class IncomingCallsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!intent.getAction().equals("android.intent.action.PHONE_STATE")) return;
        String extraState = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

        if (extraState.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            String incomingNumber = 
                intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            if (incomingNumber.contentEquals("1234567")) {
                //---answer the call---
                Intent i = new Intent(Intent.ACTION_MEDIA_BUTTON);
                i.putExtra(Intent.EXTRA_KEY_EVENT, 
                        new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK));
                context.sendOrderedBroadcast(i, null);
            } 
        }
        return;
    }
};

