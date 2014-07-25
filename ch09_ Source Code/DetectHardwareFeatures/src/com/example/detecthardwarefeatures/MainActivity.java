package com.example.detecthardwarefeatures;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewConfiguration;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "GPS available: " + IsGPSPresent(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Front Camera available: " + IsFrontCameraPresent(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "NFC available: " + IsNFCPresent(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Menu button available: " + IsHardwareMenuButtonPresent(), Toast.LENGTH_SHORT).show();
     	Toast.makeText(this, "Keyboard available: " + IsKeyboardPresent(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    private boolean IsKeyboardPresent() {
    	return getResources().getConfiguration().keyboard != Configuration.KEYBOARD_NOKEYS; 
    }
    
    //---requires level 14---
    @SuppressLint("NewApi")
	private boolean IsHardwareMenuButtonPresent() {
        return ViewConfiguration.get(this).hasPermanentMenuKey();
    }

    private boolean IsNFCPresent() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC);
    }
    
    private boolean IsFrontCameraPresent() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT);
    }
    
    private boolean IsGPSPresent() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS);
    }    
    
}
