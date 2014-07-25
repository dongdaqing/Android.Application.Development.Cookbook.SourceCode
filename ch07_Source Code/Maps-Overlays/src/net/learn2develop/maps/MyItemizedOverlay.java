package net.learn2develop.maps;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MyItemizedOverlay extends ItemizedOverlay<OverlayItem> {
    //---array of OverlayItem objects---
    private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
    Context mContext;
    
    public MyItemizedOverlay(Drawable defaultMarker) {
          super(boundCenterBottom(defaultMarker));
    }

    public MyItemizedOverlay(Drawable defaultMarker, Context context) {
          super(boundCenterBottom(defaultMarker));
          mContext = context;
    }

    //---add an OverlayItem object to the map---
    public void addOverlay(OverlayItem overlay) {
        mOverlays.add(overlay);
        //---call this to draw the OverLayItem objects---
        populate();
    }
    
    //---remove an OverlayItem object from the map---
    public void removeOverlay(OverlayItem overlay) {        
        mOverlays.remove(overlay);
        //---call this to draw the OverLayItem objects---
        populate();
    }
    
    //---called when populate() is called; returns each OverlayItem object
    // in the array---
    @Override
    protected OverlayItem createItem(int i) {
        return mOverlays.get(i);
    }

    //---returns the number of OverlayItem objects---
    @Override
    public int size() {
        return mOverlays.size();
    }
    
    //---called when the user taps on the OverlayItem objects---
    @Override
    protected boolean onTap(int index) {
        OverlayItem item = mOverlays.get(index);
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setTitle(item.getTitle());
        dialog.setMessage(item.getSnippet());
        dialog.show();
        return true;
    }
}

