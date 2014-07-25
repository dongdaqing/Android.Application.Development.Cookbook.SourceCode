package net.learn2develop.usingbluetooth;

import java.io.IOException;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class ServerThread extends Thread {    
    //---the server socket---
    private final BluetoothServerSocket bluetoothServerSocket; 
    
    public ServerThread(BluetoothAdapter bluetoothAdapter) {        
        BluetoothServerSocket tmp = null;
        try {
            //---UUID must be the same for both the client and 
            // the server---
            tmp =     
                bluetoothAdapter.listenUsingRfcommWithServiceRecord(
                    "BluetoothApp", UUID.fromString(MainActivity.UUID));
        } catch (IOException e) {
        	Log.d("ServerThread", e.getLocalizedMessage());
        }
        bluetoothServerSocket = tmp;
    }
 
    public void run() {
        BluetoothSocket socket = null;

        //---keep listening until exception occurs 
        // or a socket is returned---
        while (true) {
            try {
                socket = bluetoothServerSocket.accept();
            } catch (IOException e) {
            	Log.d("ServerThread", e.getLocalizedMessage());
                break;
            }
            //---if a connection was accepted---
            if (socket != null) {
                //---create a separate thread to listen for 
                // incoming data---
                CommsThread commsThread = new CommsThread(socket);
                commsThread.run();
            }
        }        
    }
 
    public void cancel() {
        try {
            bluetoothServerSocket.close();
        } catch (IOException e) { 
        	Log.d("ServerThread", e.getLocalizedMessage());
        }
    }
}

