package net.learn2develop.lbsreceiver;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;

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
        Intent i = new Intent(this, MyLocationReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(
                this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        //---request for location updates using GPS---
        lm.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                60000,
                100,
                pendingIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
