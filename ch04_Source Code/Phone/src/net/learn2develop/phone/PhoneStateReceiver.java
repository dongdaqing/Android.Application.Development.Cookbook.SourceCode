package net.learn2develop.phone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class PhoneStateReceiver extends BroadcastReceiver {
    TelephonyManager manager;
    PhoneReceiver myPhoneStateListener;
    static boolean alreadyListening = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        myPhoneStateListener = new PhoneReceiver(context);
        manager = ((TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE));

        //---do not add the listener more than once---
        if (!alreadyListening) {
            manager.listen(myPhoneStateListener,
                    PhoneStateListener.LISTEN_CALL_STATE);
            alreadyListening = true;
        }
    }

    public class PhoneReceiver extends PhoneStateListener {
        Context context;
        public PhoneReceiver(Context context) {
            this.context = context;
        }        

        @Override
        public void onCallStateChanged(int state, 
                String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                Toast.makeText(context, "Idle", 
                        Toast.LENGTH_LONG).show();
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                Toast.makeText(context, "Ringing: " + incomingNumber,
                        Toast.LENGTH_LONG).show();
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                Toast.makeText(context, "Offhook", 
                        Toast.LENGTH_LONG).show();
                break;
            }
        }
    }
}

