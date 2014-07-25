package net.learn2develop.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {
    BluetoothAdapter bluetoothAdapter;
    static final int REQUEST_ENABLE_BT = 0;
    
    //---check if bluetooth is available on the device---
    private boolean BluetoothAvailable()
    {
        if (bluetoothAdapter == null) 
            return false;
        else 
            return true;
    }
    
    //---enable bluetooth on the device---
    private void EnableBluetooth() {        
        if (BluetoothAvailable() && !bluetoothAdapter.isEnabled()) {
            Intent i = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE); 
            startActivityForResult(i,
                    REQUEST_ENABLE_BT);
        }
    }


    public void onActivityResult(int requestCode, 
            int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK)
            {
                Toast.makeText(this, "Bluetooth turned on!",
                        Toast.LENGTH_SHORT).show();
            } 
        }
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Toast.makeText(this, "Bluetooth available: " + BluetoothAvailable(), 
                Toast.LENGTH_LONG).show();
        
        if (BluetoothAvailable())
            EnableBluetooth();
        
        
        MyBTBroadcastReceiver mReceiver = new MyBTBroadcastReceiver();

        IntentFilter intentFilter = new  
            IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED");
        registerReceiver(mReceiver, intentFilter);
        
    }
    
    
    public class MyBTBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {            
            int state = intent.getExtras().getInt(BluetoothAdapter.EXTRA_STATE);
            switch (state) {
            case BluetoothAdapter.STATE_OFF:
                Toast.makeText(context, "Off", Toast.LENGTH_SHORT).show();
                break;
            case BluetoothAdapter.STATE_TURNING_OFF:
                Toast.makeText(context, "Turning Off", Toast.LENGTH_SHORT).show();
                break;
            case BluetoothAdapter.STATE_ON:
                Toast.makeText(context, "On", Toast.LENGTH_SHORT).show();
                break;
            case BluetoothAdapter.STATE_TURNING_ON:
                Toast.makeText(context, "Turning On", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
    
    
}
