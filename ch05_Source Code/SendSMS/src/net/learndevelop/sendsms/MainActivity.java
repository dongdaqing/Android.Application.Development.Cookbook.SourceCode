package net.learndevelop.sendsms;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("5556", null, "Greetings!", 
                             null, null);
    }
   
}
