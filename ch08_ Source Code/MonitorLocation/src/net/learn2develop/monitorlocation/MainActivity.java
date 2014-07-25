package net.learn2develop.monitorlocation;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends Activity {
    LocationManager lm;
    PendingIntent pendingIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //---use the LocationManager class to obtain locations data---
        lm = (LocationManager)
            getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public void onResume() {
        super.onResume();
    
        //---PendingIntent to launch activity if the user is within 
        // some locations---
        pendingIntent = PendingIntent.getActivity(
            this, 0, new
            Intent(android.content.Intent.ACTION_VIEW,
              Uri.parse("http://www.amazon.com")), 0);

        lm.addProximityAlert(37.422006, -122.084095, 5, -1, pendingIntent);
    }    

    @Override
    public void onDestroy() {
        super.onDestroy();
        
        lm.removeProximityAlert(pendingIntent);    
    }
    
}
