package net.learn2develop.lbsreceiver_datalogging;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    LocationManager lm;
    PendingIntent pendingIntent;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {        
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();        

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
        
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        //---remove the pending intent---
        lm.removeUpdates(pendingIntent);

        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();        
    }
}

