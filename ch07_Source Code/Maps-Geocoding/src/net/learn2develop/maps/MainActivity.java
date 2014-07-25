package net.learn2develop.maps;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MainActivity extends MapActivity {
    MapView mapView;
    MapController mc;
    
    /*
    private class MapOverlay extends Overlay
    {   
        @Override
        public boolean onTouchEvent(MotionEvent event, MapView mapView)
        {
            //---when user lifts his finger---
            if (event.getAction() == 1) {
                GeoPoint p = mapView.getProjection().fromPixels(
                    (int) event.getX(),
                    (int) event.getY());
                    Toast.makeText(getBaseContext(),
                        "Location: "+
                        p.getLatitudeE6() / 1E6 + "," +
                        p.getLongitudeE6() /1E6 , 
                        Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    }
    */
    
    private class MapOverlay extends Overlay
    {           
        private class DoBackgroundTask extends AsyncTask 
        <GeoPoint, Void, List<Address>> {

            protected List<Address> doInBackground(GeoPoint... locations) {
                Geocoder geoCoder = new Geocoder(
                        getBaseContext(), Locale.getDefault());
                try {
                    List<Address> addresses = geoCoder.getFromLocation(
                            locations[0].getLatitudeE6()  / 1E6,
                            locations[0].getLongitudeE6() / 1E6, 1);
                    return addresses;
                }
                catch (IOException e) {
                    e.printStackTrace();
                }   
                return null;
            }

            protected void onPostExecute(List<Address> addresses) {        
                String add = "";
                if (addresses.size() > 0) 
                {
                    for (int i=0; 
                             i<addresses.get(0).getMaxAddressLineIndex();
                             i++)
                        add += addresses.get(0).getAddressLine(i) + "\n";
                }
                Toast.makeText(getBaseContext(), add, 
                    Toast.LENGTH_SHORT).show();
            }
        }    
        
        @Override
        public boolean onTouchEvent(MotionEvent event, MapView mapView)
        {
            //---when user lifts his finger---
            if (event.getAction() == 1) {
                GeoPoint p = mapView.getProjection().fromPixels(
                        (int) event.getX(),
                        (int) event.getY());
                Toast.makeText(getBaseContext(),
                        "Location: "+
                                p.getLatitudeE6() / 1E6 + "," +
                                p.getLongitudeE6() /1E6 , 
                                Toast.LENGTH_SHORT).show();

                //---reverse geocoding---
                new DoBackgroundTask().execute(p); 
                return true;                
            }
            return false;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.setBuiltInZoomControls(true);
        
        mc = mapView.getController();
      
        MapOverlay mapOverlay = new MapOverlay();
        List<Overlay> listOfOverlays = mapView.getOverlays();        
        listOfOverlays.add(mapOverlay);
        
        new DoBackgroundTask().execute("empire state building");
    }
    
    private class DoBackgroundTask extends AsyncTask 
    <String, Void, List<Address>> {
        protected List<Address> doInBackground(String... locationNames) {
            //---geo-coding---
            Geocoder geoCoder = 
                new Geocoder(getBaseContext(), Locale.getDefault());
            try {
                //---maximum 5 results---
                List<Address> addresses = geoCoder.getFromLocationName(
                        locationNames[0], 5);
                return addresses;
            } catch (IOException e) {
                e.printStackTrace();
            }        
            return null;
        }

        protected void onPostExecute(List<Address> addresses) {        
            if (addresses.size() > 0) {
                GeoPoint p = new GeoPoint(
                        (int) (addresses.get(0).getLatitude() * 1E6),
                        (int) (addresses.get(0).getLongitude() * 1E6));
                mc.animateTo(p);
                mc.setZoom(20);
            }
        }
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
   
}