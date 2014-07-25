package net.learn2develop.calllogs;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        GetCallLogs();
    }

    private void GetCallLogs() {
        final String[] projection = null;
        final String selection = null;
        final String[] selectionArgs = null;
        final String sortOrder = android.provider.CallLog.Calls.DATE + " DESC";
        Cursor cursor = null;
        try{
            cursor = this.getContentResolver().query(
                    Uri.parse("content://call_log/calls"),
                    projection,
                    selection,
                    selectionArgs,
                    sortOrder);
            while (cursor.moveToNext()) {
                //---id---
                String callLogID = cursor.getString(cursor.getColumnIndex(
                                       android.provider.CallLog.Calls._ID));
                
                //---phone number---
                String callNumber = cursor.getString(cursor.getColumnIndex(
                                        android.provider.CallLog.Calls.NUMBER));
                
                //---date of call---
                String callDate = cursor.getString(cursor.getColumnIndex(
                                      android.provider.CallLog.Calls.DATE));
                
                //---1-incoming; 2-outgoing; 3-missed---
                String callType = cursor.getString(cursor.getColumnIndex(
                                      android.provider.CallLog.Calls.TYPE));
                
                //---0-call has been acknowledged; 1-call 
                // has not been acknowledge---
                String isCallNew = cursor.getString(cursor.getColumnIndex(
                                       android.provider.CallLog.Calls.NEW));
                
                Log.d("", "callLogID: " + callLogID);
                Log.d("", "callNumber: " + callNumber);
                Log.d("", "callDate: " + callDate);
                Log.d("", "callType: " + callType);
                Log.d("", "isCallNew: " + isCallNew);
                
                //---check for missed call that have not been acknowledged---
                if (Integer.parseInt(callType) == 
                        android.provider.CallLog.Calls.MISSED_TYPE && 
                        Integer.parseInt(isCallNew) > 0) {
                    Log.d("", "Missed Call Found: " + callNumber);
                }                
            }
        } catch (Exception ex){
            Log.d("", "ERROR: " + ex.toString());
        } finally{
            cursor.close();
        }
    }
    
}
