package net.learn2develop.togglegps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        LocationManager lm = (LocationManager)
				getSystemService(Context.LOCATION_SERVICE);
        boolean gpsStatus = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean networkStatus = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!gpsStatus || !networkStatus) {
        	//---display the "Location services" settings page---
        	Intent in = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);   
        	startActivity(in);        	
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
