package net.learn2develop.detectoutgoingsms;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends Activity {
    ContentResolver contentResolver;
    ContentObserver smsContentObserver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        contentResolver = getContentResolver();
    }
    
    @Override
    public void onResume() {
        super.onResume();
        
        smsContentObserver = new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange) {
                Uri smsURI = Uri.parse("content://sms/sent");                
                Cursor c = getContentResolver().query(smsURI,
                    new String[] { "address", "date", "body", "type" }, 
                                   null, null, null);

                String[] columns = new String[] { 
                    "address", "date", "body","type" };
                
                //---go to the first row; which is the most recently 
                // sent message---
                c.moveToNext();                    
                
                //---get the various properties of the SMS message---
                String recipient = c.getString(c.getColumnIndex(columns[0]));
                String date = c.getString(c.getColumnIndex(columns[1]));
                String message = c.getString(c.getColumnIndex(columns[2]));
                String type = c.getString(c.getColumnIndex(columns[3]));
                
                //---print out the details of the message---
                Log.d("DetectOutoingSMS", recipient + ", " + date + ", " + 
                      message + ", " + type);                       
            }
            
            @Override
            public boolean deliverSelfNotifications() {
                return true;
            }
        };      
        contentResolver.registerContentObserver(
            Uri.parse("content://sms"), true, smsContentObserver);  
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();        
        contentResolver.unregisterContentObserver(
        		smsContentObserver);
    }

    
}
