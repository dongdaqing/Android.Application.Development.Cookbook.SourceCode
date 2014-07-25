package net.learn2develop.custommaps;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

public class CustomMapView extends MapView {

    private int previousZoomLevel = -1;
    private GeoPoint previousMapCenter;
    private OnPanAndZoomListener panAndZoomListener;

    public CustomMapView(Context context, String apiKey) {
        super(context, apiKey);
    }

    public CustomMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomMapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnPanAndZoomListener(OnPanAndZoomListener listener) {
        panAndZoomListener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            //---get the center of the map---
            GeoPoint currentMapCenter = this.getMapCenter();

            if (previousMapCenter == null || 
               (previousMapCenter.getLatitudeE6() != 
                  currentMapCenter.getLatitudeE6()) ||
               (previousMapCenter.getLongitudeE6() != 
                  currentMapCenter.getLongitudeE6()) ) {

                //---the user has panned the map---
                panAndZoomListener.onMapPan();
            }

            //---save the current map center for comparison later on---
            previousMapCenter = this.getMapCenter();
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        if (getZoomLevel() != previousZoomLevel) {
            //---the user has zoomed the map---
            panAndZoomListener.onMapZoom();

            //---save the current zoom level for comparison later on---             
            previousZoomLevel = getZoomLevel();
        }
    }
}

