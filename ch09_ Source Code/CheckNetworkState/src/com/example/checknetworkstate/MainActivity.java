package com.example.checknetworkstate;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {
	ConnectivityManager connectivity;
	NetworkInfo wifiNetworkInfo, mobileNetworkInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

		wifiNetworkInfo =
				connectivity.getNetworkInfo(
						ConnectivityManager.TYPE_WIFI);
		mobileNetworkInfo =
				connectivity.getNetworkInfo(
						ConnectivityManager.TYPE_MOBILE);
		if (wifiNetworkInfo.isConnected()) 
			Toast.makeText(this, "WiFi is connected", Toast.LENGTH_LONG).show();
		else
			Toast.makeText(this, "WiFi not connected", Toast.LENGTH_LONG).show();
		if (mobileNetworkInfo.isConnected()) 
			Toast.makeText(this, "3G/4G is connected", Toast.LENGTH_LONG).show();
		else
			Toast.makeText(this, "3G/4G not connected", Toast.LENGTH_LONG).show();
    }
    
    public void TurnOnWifi() {
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
