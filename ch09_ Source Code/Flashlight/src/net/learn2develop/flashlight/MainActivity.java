package net.learn2develop.flashlight;

import java.io.IOException;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class MainActivity extends Activity implements SurfaceHolder.Callback {

	SurfaceView surfaceView;
	SurfaceHolder surfaceHolder;	
	Camera camera;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        surfaceView = (SurfaceView) this.findViewById(R.id.surface1);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
	}

	public void surfaceCreated(SurfaceHolder arg0) {
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {
	}

	@Override
    public void onResume() {
    	super.onResume();
    	try {
        	camera = Camera.open();     
    	} catch (Exception e) {
    		//---exception handling here---
    		Log.d("Flashlight", e.toString());
    	}
    }

	@Override
    public void onPause() {
    	super.onPause();
    	TurnOff(null);
    	camera.release();
    }
    
    public void TurnOn(View view) {
    	if (FlashAvailable() && camera != null) {
    		Parameters p = camera.getParameters();
            //---works with Android 2.x as well---
    		p.setFlashMode(Parameters.FLASH_MODE_TORCH);
    		camera.setParameters(p);
    		camera.startPreview();
    		    		
    		try {
				camera.setPreviewDisplay(surfaceHolder);
			} catch (IOException e) {
				e.printStackTrace();
			}			
    	}
    }

    public void TurnOff(View view) {
    	if (FlashAvailable() && camera != null) {
    		Parameters p = camera.getParameters();
    		p.setFlashMode(Parameters.FLASH_MODE_OFF);
    		camera.setParameters(p);
    		camera.stopPreview();    		
    	}
    }
    
    private Boolean FlashAvailable() {
    	return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

}
