package net.learn2develop.custommaps;

import android.os.Bundle;
import android.util.Log;

import com.google.android.maps.MapActivity;

public class MainActivity extends MapActivity  {
    CustomMapView mapView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        OnPanAndZoomListener listener = new OnPanAndZoomListener() {
            @Override
            public void onMapZoom() {            
                Log.d("CustomMaps","onMapZoom. Zoom level: " + 
                       mapView.getZoomLevel());
                
            }
            @Override
            public void onMapPan() {
                Log.d("CustomMaps","onMapPan. Center: " + 
                      mapView.getMapCenter().getLatitudeE6() / 1E6 + "," + 
                      mapView.getMapCenter().getLongitudeE6() / 1E6);
            }
        };

        mapView = (CustomMapView) findViewById(R.id.mapView);
        mapView.setOnPanAndZoomListener(listener);
        mapView.setBuiltInZoomControls(true);

    }
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
    
}
