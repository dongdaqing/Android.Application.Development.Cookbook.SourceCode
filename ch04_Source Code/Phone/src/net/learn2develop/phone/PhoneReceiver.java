package net.learn2develop.phone;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class PhoneReceiver extends PhoneStateListener {
    Context context;
    public PhoneReceiver(Context context) {
        this.context = context;
    }

    @Override
    public void onCallStateChanged(int state, 
            String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);

        Toast.makeText(context, "onCallStateChanged state=" + 
                state + "incomingNumber=" + incomingNumber, 
                Toast.LENGTH_LONG).show();        

        switch (state) {
        case TelephonyManager.CALL_STATE_IDLE:
            Toast.makeText(context, "idle", 
                    Toast.LENGTH_LONG).show();
            break;
        case TelephonyManager.CALL_STATE_RINGING:
            Toast.makeText(context, "ringing", 
                    Toast.LENGTH_LONG).show();
            break;
        case TelephonyManager.CALL_STATE_OFFHOOK:
            Toast.makeText(context, "offhook", 
                    Toast.LENGTH_LONG).show();
            break;
        }
    }
}

