package net.learn2develop.buttons;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		//---Button view---
		Button btn = (Button) findViewById(R.id.button3); 
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Toast.makeText(getBaseContext(), "Button 3 was clicked!", 
						Toast.LENGTH_SHORT).show();
			}
		});

		//---the two buttons are wired to the same event handler--- 
		Button btn4 = (Button) findViewById(R.id.button4);
		btn4.setOnClickListener(btnListener);

		Button btn5 = (Button) findViewById(R.id.button5); 
		btn5.setOnClickListener(btnListener);		
    }

	//---create an anonymous class to act as a button click listener---
	private OnClickListener btnListener = new OnClickListener() {
		public void onClick(View view)
		{
			Toast.makeText(getBaseContext(),
					((Button) view).getText() + " was clicked!",
					Toast.LENGTH_LONG).show();
		}
	};

	public void onClick(View view) {
		Button btn = (Button) view;
		Toast.makeText(this, btn.getText() + " was clicked!", 
				Toast.LENGTH_SHORT).show();
	}

	public void onToggle(View view) {
		ToggleButton btn = (ToggleButton) view;
		Toast.makeText(this, "Toggle mode: " + btn.isChecked(), 
				Toast.LENGTH_SHORT).show();
	}
    
}
