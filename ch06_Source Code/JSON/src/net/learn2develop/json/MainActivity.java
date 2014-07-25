package net.learn2develop.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    public String readJSONFeed(String URL) {
        StringBuilder stringBuilder = new StringBuilder();
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();
            } else {
                Log.d("JSON", "Failed to download file");
            }
        } catch (Exception e) {
            Log.d("readJSONFeed", e.getLocalizedMessage());
        }        
        return stringBuilder.toString();
    }

    private class ReadWeatherJSONFeedTask extends AsyncTask
    <String, Void, String> {
        protected String doInBackground(String... urls) {
            return readJSONFeed(urls[0]);
        }
        
        protected void onPostExecute(String result) {
            try {
            	/*
            	//---sample result---
				{
				    "weatherObservation": {
				         "clouds":"scattered clouds",
				         "weatherCondition":"n/a",
				         "observation":"KCFV 090852Z AUTO 06005KT 10SM SCT090 SCT110 24/20 A3000 RMK AO2 SLP148 T02390200 53002",
				         "windDirection":60,
				         "ICAO":"KCFV",
				         "seaLevelPressure":1014.8,
				         "elevation":225,
				         "countryCode":"US",
				         "lng":-95.56666666666666,
				         "temperature":"23.9",
				         "dewPoint":"20",
				         "windSpeed":"05",
				         "humidity":78,
				         "stationName":"Coffeyville, Coffeyville Municipal Airport",
				         "datetime":"2012-07-09 08:52:00",
				         "lat":37.083333333333336
				    }
				}
                */
            	
            	JSONObject jsonObject = new JSONObject(result);
            	JSONObject weatherObservationItems = new JSONObject(jsonObject.getString("weatherObservation"));
            	
            	Toast.makeText(getBaseContext(), 
            			weatherObservationItems.getString("clouds") + 
                        " - " + weatherObservationItems.getString("stationName"), 
                        Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
            	Log.d("ReadWeatherJSONFeedTask", e.getLocalizedMessage());
            }          
        }
    }
    
    private class ReadPlacesFeedTask extends AsyncTask
    <String, Void, String> {
        protected String doInBackground(String... urls) {
            return readJSONFeed(urls[0]);
        }
        
        protected void onPostExecute(String result) {
            try {
            	/*
            	//---sample result---
            	{
            	    "postalCodes": [
            	        { 
            	            "adminCode3":"3203",
            	            "adminName2":"Wahlkreis St. Gallen",
            	            "adminName3":"St. Gallen",
            	            "adminCode2":"1721",
            	            "adminCode1":"SG",
            	            "postalCode":"9011",
            	            "countryCode":"CH",
            	            "lng":9.399858534040646,
            	            "placeName":"St. Gallen",
            	            "lat":47.414775328611945,
            	            "adminName1":"Kanton St. Gallen"
            	        },
            	        {
            	            "adminCode1":"GS",
            	            "postalCode":"9011",
            	            "countryCode":"HU",
            	            "lng":17.781944437499998,
            	            "placeName":"Gyor",
            	            "lat":47.607638900000005,
            	            "adminName1":"Gyor-Moson-Sopron"
            	        },
            	        {
            	            "adminName2":"Troms¿",
            	            "adminCode2":"1902",
            	            "adminCode1":"19",
            	            "postalCode":"9011",
            	            "countryCode":"NO",
            	            "lng":18.95508,
            	            "placeName":"Troms¿",
            	            "lat":69.6489,
            	            "adminName1":"Troms"
            	        }, 
            	        {
            	            ...
            	            ...
                        }
            	    ]
            	}
            	*/
            	
            	JSONObject jsonObject = new JSONObject(result);
            	JSONArray postalCodesItems = new JSONArray(jsonObject.getString("postalCodes"));
            	
            	//---print out the content of the json feed---
                for (int i = 0; i < postalCodesItems.length(); i++) {
                    JSONObject postalCodesItem = postalCodesItems.getJSONObject(i);        
                    Toast.makeText(getBaseContext(), 
                   		postalCodesItem.getString("postalCode") + " - " + 
                        postalCodesItem.getString("placeName") + ", " +
                        postalCodesItem.getString("countryCode"), 
                        Toast.LENGTH_SHORT).show();                                    
                }
            } catch (Exception e) {
                Log.d("ReadPlacesFeedTask", e.getLocalizedMessage());
            }          
        }
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void btnGetWeather(View view) {
    	EditText txtLat = (EditText) findViewById(R.id.txtLat);
    	EditText txtLong = (EditText) findViewById(R.id.txtLong);
    	    	
        new ReadWeatherJSONFeedTask().execute(
            "http://ws.geonames.org/findNearByWeatherJSON?lat=" + 
            txtLat.getEditableText().toString() + "&lng=" + 
            txtLong.getText().toString());    	
    }
    
    public void btnGetPlaces(View view) {
    	EditText txtPostalCode = (EditText) findViewById(R.id.txtPostalCode);
    	
        new ReadPlacesFeedTask().execute(        
            "http://api.geonames.org/postalCodeSearchJSON?postalcode=" + 
            txtPostalCode.getEditableText().toString() + 
            "&maxRows=10&username=demo");        	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
   
}
