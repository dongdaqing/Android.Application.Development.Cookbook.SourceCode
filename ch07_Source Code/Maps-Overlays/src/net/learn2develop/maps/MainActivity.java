package net.learn2develop.maps;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

public class MainActivity extends MapActivity {
    MapView mapView;
    MapController mc;
    
    GeoPoint p1 = new GeoPoint(37423021,-122083739);        
    GeoPoint p2 = new GeoPoint(37523021,-122183739);
 
    private class MapOverlay extends com.google.android.maps.Overlay
    {   

        @Override
        public boolean draw(Canvas canvas, MapView mapView,
        boolean shadow, long when)
        {
            super.draw(canvas, mapView, shadow);

            Bitmap bmp = BitmapFactory.decodeResource(
                    getResources(), R.drawable.ic_launcher);
            
            //---translate the GeoPoint to screen pixels---
            Point screenPts1 = new Point();
            mapView.getProjection().toPixels(p1, screenPts1);

            //---translate the GeoPoint to screen pixels---
            Point screenPts2 = new Point();
            mapView.getProjection().toPixels(p2, screenPts2);
            
            //---add the first marker---            
            canvas.drawBitmap(bmp, screenPts1.x - bmp.getWidth()/2, 
                                   screenPts1.y - bmp.getHeight()/2, null);

            //---add the second marker---
            canvas.drawBitmap(bmp, screenPts2.x - bmp.getWidth()/2, 
                                   screenPts2.y - bmp.getHeight()/2, null);

            Paint paint = new Paint();
            paint.setStyle(Style.STROKE);
            paint.setColor(0xFF000000);
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            
            //---draws a line connecting the 2 points---
            canvas.drawLine(screenPts1.x, screenPts1.y, 
                            screenPts2.x, screenPts2.y, paint);
            
            return true;
        }
    }

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.setBuiltInZoomControls(true);
        
        mc = mapView.getController();
        
        //---get your current location and display a blue dot---
        MyLocationOverlay myLocationOverlay = 
            new MyLocationOverlay(this, mapView);

        mapView.getOverlays().add(myLocationOverlay);
        myLocationOverlay.enableMyLocation();


        /*
        //==============================
        List<Overlay> listOfOverlays = mapView.getOverlays();
        Drawable drawable = 
            this.getResources().getDrawable(R.drawable.ic_launcher);
        
        MyItemizedOverlay itemizedoverlay = 
            new MyItemizedOverlay(drawable, this);        
        OverlayItem overlayitem1 = new OverlayItem(
            p1, "Hello Google!", "I'm an Android!");

        //---add an overlayitem---
        itemizedoverlay.addOverlay(overlayitem1);

        OverlayItem overlayitem2= new OverlayItem(
            p2, "Hello Google!", "I'm swimming!");

        //---add an overlayitem---
        itemizedoverlay.addOverlay(overlayitem2);       

        //---add the overlay---
        listOfOverlays.add(itemizedoverlay);

        mc.animateTo(p1);    
        mc.setZoom(12);
        */
        
        MapOverlay mapOverlay = new MapOverlay();            
        List<Overlay> listOfOverlays = mapView.getOverlays();        
        listOfOverlays.add(mapOverlay);  
        
        mc.animateTo(p1);    
        mc.setZoom(12);


    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
  
}
