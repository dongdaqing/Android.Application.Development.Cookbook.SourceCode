package net.learn2develop.lbsreceiver_datalogging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.widget.Toast;

public class MyLocationReceiver extends BroadcastReceiver {    
    @Override
    public void onReceive(Context context, Intent intent) {
        String locationKey = LocationManager.KEY_LOCATION_CHANGED;
        String providerEnabledKey = LocationManager.KEY_PROVIDER_ENABLED;
        if (intent.hasExtra(providerEnabledKey)) {
            if (!intent.getBooleanExtra(providerEnabledKey, true)) {
                Toast.makeText(context,
                        "Provider disabled",
                        Toast.LENGTH_SHORT).show();              
            } else {
                Toast.makeText(context,
                        "Provider enabled",
                        Toast.LENGTH_SHORT).show();
            }
        }

        if (intent.hasExtra(locationKey)) {
            Location loc = (Location)intent.getExtras().get(locationKey);
            Toast.makeText(context,
                    "Location changed : Lat: " + loc.getLatitude() +
                    " Lng: " + loc.getLongitude(),
                    Toast.LENGTH_SHORT).show();

            DBAdapter db = new DBAdapter(context);
            db.open();
            db.insertLocation(String.valueOf(loc.getLatitude()), 
                              String.valueOf(loc.getLongitude()));
            db.close();
        }
    }
}

