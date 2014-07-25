package net.learn2develop.locationproviders;

import java.util.List;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    LocationManager lm;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);

        //---print out all the location providers---
        List<String> locationProviders = lm.getAllProviders();
        for (String provider : locationProviders) {
            Log.d("LocationProviders", provider);
        }                    
        
        //---set the criteria for best location provider---
        Criteria c = new Criteria();
        c.setAccuracy(Criteria.ACCURACY_FINE);
        //---OR---
        //c.setAccuracy(Criteria.ACCURACY_COARSE);
        c.setAltitudeRequired(false);
        c.setBearingRequired(false);
        c.setCostAllowed(true);
        c.setPowerRequirement(Criteria.POWER_HIGH);
        
        //---get the best location provider---
        String bestProvider = lm.getBestProvider(c, true);        
        Log.d("LocationProviders", "Best provider is " + bestProvider);
        
        //---get the last known location---
        Location location = lm.getLastKnownLocation(bestProvider);
        if (location!=null) Log.d("LocationProviders", location.toString());
    }
    
}
