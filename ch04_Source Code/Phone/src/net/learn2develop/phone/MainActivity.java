package net.learn2develop.phone;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class MainActivity extends Activity {
    TelephonyManager manager;
    PhoneReceiver myPhoneStateListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPhoneStateListener = new PhoneReceiver(this);
        manager = ((TelephonyManager) 
                getSystemService(Context.TELEPHONY_SERVICE));
    }
    
    @Override
    public void onResume() {
        super.onResume();
        manager.listen(myPhoneStateListener,
                PhoneStateListener.LISTEN_CALL_STATE);
    }
    
    @Override
    public void onPause() {
        super.onPause();
        manager.listen(myPhoneStateListener, 
                PhoneStateListener.LISTEN_NONE);
    }    

    
}
