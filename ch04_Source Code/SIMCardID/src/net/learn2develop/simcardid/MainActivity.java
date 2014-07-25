package net.learn2develop.simcardid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TelephonyManager tm = (TelephonyManager) 
                getSystemService(Context.TELEPHONY_SERVICE);     
        
        //---get the SIM card ID---
        String simID = tm.getSimSerialNumber();
        if (simID != null)
            Toast.makeText(this, "SIM card ID: " + simID, Toast.LENGTH_LONG).show();

        //---get the phone number---
        String telNumber = tm.getLine1Number();
        if (telNumber != null)        
            Toast.makeText(this, "Phone number: " + telNumber, Toast.LENGTH_LONG).show();

        //---get the IMEI number---
        String IMEI = tm.getDeviceId();
        if (IMEI != null)        
            Toast.makeText(this, "IMEI number: " + IMEI, Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
