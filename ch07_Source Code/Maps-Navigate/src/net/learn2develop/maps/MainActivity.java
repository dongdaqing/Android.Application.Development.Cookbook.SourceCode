package net.learn2develop.maps;

import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class MainActivity extends MapActivity {
    MapView mapView;
    MapController mc;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.setBuiltInZoomControls(true);
        
        mc = mapView.getController();
        
        GeoPoint p = new GeoPoint(37423021,-122083739);
        
        mc.animateTo(p);
        mc.setZoom(13);
        mapView.invalidate();
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}	
    
}
