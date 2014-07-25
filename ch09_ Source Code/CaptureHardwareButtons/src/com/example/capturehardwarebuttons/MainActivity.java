package com.example.capturehardwarebuttons;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	public void onPause() {
		super.onPause();
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_CENTER:
			Toast.makeText(this, "The Center key was pressed",
					Toast.LENGTH_SHORT).show();
			return true;

		case KeyEvent.KEYCODE_DPAD_RIGHT:
			Toast.makeText(this, "The Right key was pressed",
					Toast.LENGTH_SHORT).show();
			return true;

		case KeyEvent.KEYCODE_DPAD_LEFT:
			Toast.makeText(this, "The Left key was pressed", Toast.LENGTH_SHORT)
					.show();
			return true;

		case KeyEvent.KEYCODE_BACK:
			Toast.makeText(this, "The Back key was pressed", Toast.LENGTH_SHORT)
					.show();			
			//---move the entire task to the background---
			moveTaskToBack(true);
			
			// ---this event has been handled---
			return true;
		}
		// ---this event has not been handled---
		return false;
	}

}
