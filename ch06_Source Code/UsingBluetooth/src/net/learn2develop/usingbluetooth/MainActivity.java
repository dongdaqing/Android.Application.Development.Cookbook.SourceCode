package net.learn2develop.usingbluetooth;

import java.io.IOException;
import java.util.ArrayList;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	public final static String UUID = "3606f360-e4df-11e0-9572-0800200c9a66";
	
    BluetoothAdapter bluetoothAdapter;
    BroadcastReceiver discoverDevicesReceiver;
    BroadcastReceiver discoveryFinishedReceiver;
    
    //---store all the discovered devices---
    ArrayList<BluetoothDevice> discoveredDevices;
    ArrayList<String> discoveredDevicesNames;
    
    //---store all the paired devices---
    ArrayList<BluetoothDevice> pairedDevices;    
    
    static TextView txtData;
    EditText txtMessage;

    //---thread for running the server socket---
    ServerThread serverThread;

    //---thread for connecting to the client socket---
    ConnectToServerThread connectToServerThread;
        
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //---init the ArrayList objects and bluetooth adapter---
        discoveredDevices = new ArrayList<BluetoothDevice>();
        discoveredDevicesNames = new ArrayList<String>();
        pairedDevices = new ArrayList<BluetoothDevice>();
        
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        
        //---for displaying the messages received---
        txtData = (TextView) findViewById(R.id.txtData);
        txtMessage = (EditText) findViewById(R.id.txtMessage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
    //---make yourself discoverable---
    public void MakeDiscoverable(View view)    
    {     
        Intent i = new Intent(
            BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        i.putExtra(
            BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(i);
    }
    
    /*
    //---find all the previously paired devices---
    private void QueryPairedDevices(){
        Set<BluetoothDevice> allPairedDevices = 
            bluetoothAdapter.getBondedDevices();
        //---if there are paired devices---
        if (allPairedDevices.size() > 0) {
            //---loop through paired devices---
            for (BluetoothDevice device : allPairedDevices) {
                //---add the name and address to an array adapter 
                // to show in a ListView---
                Log.d("UsingBluetooth", device.getName() + "\n" + 
                    device.getAddress());
                pairedDevices.add(device);
            }
        }
    }    
    */
    
    //---used to discover other bluetooth devices---
    private void DiscoveringDevices() {  	
    	if (discoverDevicesReceiver == null) {
            discoverDevicesReceiver = new BroadcastReceiver() {            	
            	//---fired when a new device is discovered---
                @Override
                public void onReceive(Context context, Intent intent) {                
                    String action = intent.getAction();

                    //---a device is discovered---
                    if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                        //---get the BluetoothDevice object from 
                        // the Intent---
                        BluetoothDevice device = 
                            intent.getParcelableExtra(
                                BluetoothDevice.EXTRA_DEVICE);

                        //---add the name and address to an array 
                        // adapter to show in a ListView---
                        //---only add if the device is not already 
                        // in the list---
                        if (!discoveredDevices.contains(device)) {
                            //---add the device---
                            discoveredDevices.add(device);
                            
                            //---add the name of the device; used for 
                            // ListView---
                            discoveredDevicesNames.add(device.getName());

                            //---display the items in the ListView---
                            setListAdapter(new 
                                ArrayAdapter<String>(getBaseContext(),
                                android.R.layout.simple_list_item_1, 
                                discoveredDevicesNames));  
                        }                    
                    }
                }
            };
    	}
    	
    	if (discoveryFinishedReceiver==null) {
    		discoveryFinishedReceiver = new BroadcastReceiver() {
    			//---fired when the discovery is done---
    			@Override
				public void onReceive(Context context, Intent intent) {
    				//---enable the listview when discovery is over; about 12 seconds---
			        getListView().setEnabled(true);		
			        Toast.makeText(getBaseContext(), 
			        		"Discovery completed. Select a device to start chatting.", 
			        		Toast.LENGTH_LONG).show();
			        unregisterReceiver(discoveryFinishedReceiver);
				}    			
    		};
    	}
    	
        //---register the broadcast receivers---
        IntentFilter filter1 = new
            IntentFilter(BluetoothDevice.ACTION_FOUND);
        IntentFilter filter2 = new
            IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
            
        registerReceiver(discoverDevicesReceiver, filter1);
        registerReceiver(discoveryFinishedReceiver, filter2);
            
        //---disable the listview when discover is in progress---
        getListView().setEnabled(false);
        Toast.makeText(getBaseContext(), 
        		"Discovery in progress...please wait...", 
        		Toast.LENGTH_LONG).show();
        bluetoothAdapter.startDiscovery();
    }

    //---discover other bluetooth devices---
    public void DiscoverDevices(View view)    
    {
        //---query for all paired devices---
        //QueryPairedDevices();           

        //---discover other devices---
        DiscoveringDevices();        
    }
    
    @Override
    public void onPause() {
        super.onPause();        
        //---cancel discovery of other bluetooth devices
        bluetoothAdapter.cancelDiscovery();
        
        //---unregister the broadcast receiver for 
        // discovering devices--- 
        if (discoverDevicesReceiver != null) {
        	try {
            	unregisterReceiver(discoverDevicesReceiver);        		
        	} catch(Exception e) {
        		
        	}
        }
                
        //---if you are currently connected to someone...---
        if (connectToServerThread!=null) {            
            try {
                //---close the connection---
                connectToServerThread.bluetoothSocket.close();
            } catch (IOException e) {
            	Log.d("MainActivity", e.getLocalizedMessage());
            }
        }        
        //---stop the thread running---
        if (serverThread!=null) serverThread.cancel();
    }
    
    //---used for updating the UI on the main activity---
    static Handler UIupdater = new Handler() {
        @Override
        public void handleMessage(Message msg) {              
            int numOfBytesReceived = msg.arg1;
            byte[] buffer = (byte[]) msg.obj;
            //---convert the entire byte array to string---
            String strReceived = new String(buffer);
            //---extract only the actual string received---
            strReceived = strReceived.substring(
                0, numOfBytesReceived);
            //---display the text received on the TextView---              
            txtData.setText(txtData.getText().toString() + 
                strReceived);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        //---start the socket server---
        serverThread = new ServerThread(bluetoothAdapter);
        serverThread.start();
    }

    
    
    
    //---when a client is tapped in the ListView---
    public void onListItemClick(ListView parent, View v, 
    int position, long id) {
        //---if you are already talking to someone...---
        if (connectToServerThread!=null) {
            try {
                //---close the connection first---
                connectToServerThread.bluetoothSocket.close();
            } catch (IOException e) {
            	Log.d("MainActivity", e.getLocalizedMessage());            	
            }
        } 

        //---connect to the selected Bluetooth device---
        BluetoothDevice deviceSelected =
            discoveredDevices.get(position);        
        connectToServerThread = new 
            ConnectToServerThread(deviceSelected, bluetoothAdapter);
        connectToServerThread.start();
     
        //---tell the user that he is connected to who---
        Toast.makeText(this, "You have connected to " + 
                discoveredDevices.get(position).getName(), 
                Toast.LENGTH_SHORT).show();        
    }
    
     private class WriteTask extends AsyncTask<String, Void, Void> {
		protected Void doInBackground(String... args) {
			try {
	            connectToServerThread.commsThread.write(args[0]);
	        } catch (Exception e) {
	        	Log.d("MainActivity", e.getLocalizedMessage());
	        }
			return null;
		}
	}
        
    //---send a message to the connected socket client---
    public void SendMessage(View view) 
    {    
        if (connectToServerThread!=null) {
            ///=========
            //connectToServerThread.commsThread.write(
            //		txtMessage.getText().toString());
            
            new WriteTask().execute(txtMessage.getText().toString());
            ///=========

        } else {
            Toast.makeText(this, "Select a client first", 
                Toast.LENGTH_SHORT).show();
        }
    }
   
    
}
