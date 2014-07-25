package net.learn2develop.turnonbluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	BluetoothAdapter bluetoothAdapter;
	ToggleButton toggleButton1;
	static final int REQUEST_ENABLE_BT = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	}

	@Override
	public void onResume() {
		super.onResume();

		toggleButton1.setChecked(bluetoothAdapter.isEnabled());
	}

	public void btnCheckBluetoothAvailability(View view) {
		Toast.makeText(this, "Bluetooth available: " + IsBluetoothAvailable(),
				Toast.LENGTH_LONG).show();
	}

	public void btnToggleBluetooth(View view) {
		if (IsBluetoothAvailable()) {
			if (!bluetoothAdapter.isEnabled()) {
				// ---you can also use this; but do not use without explicit user
				// permission---
				// bluetoothAdapter.enable();

				Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
				startActivityForResult(i, REQUEST_ENABLE_BT);
			} else {
				bluetoothAdapter.disable();
			}
		}
	}

	protected void onActivityResult(int requestCode, int resultCode,
            Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                Log.d("onActivityResult","Bluetooth turned on.");
            } else {
                Log.d("onActivityResult","Bluetooth failed to turn on.");            	
            }
        }
    }
	
	// ---check if bluetooth is available on the device---
	private boolean IsBluetoothAvailable() {
		if (bluetoothAdapter == null)
			return false;
		else
			return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
