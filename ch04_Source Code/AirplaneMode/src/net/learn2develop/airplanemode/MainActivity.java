package net.learn2develop.airplanemode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void SetAirplaneMode(boolean enabled){
        //---toggle airplane mode---
        Settings.System.putInt(
              getContentResolver(),
              Settings.System.AIRPLANE_MODE_ON, enabled ? 1 : 0);

        Intent i = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        i.putExtra("state", enabled);
        sendBroadcast(i);
    }    
    
    public boolean isAirplaneModeOn(){
        return Settings.System.getInt(
                getContentResolver(),
                Settings.System.AIRPLANE_MODE_ON, 0) != 0;
    }

    
}
