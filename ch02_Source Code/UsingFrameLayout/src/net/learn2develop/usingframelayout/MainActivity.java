package net.learn2develop.usingframelayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends Activity {
    int count = 1;
	FrameLayout frame;
	ImageView imageview;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		frame = (FrameLayout) findViewById(R.id.framelayout);
	}

	public void btnClick(View view) {
		//---hide the current one---
		imageview = (ImageView) frame.findViewWithTag(String.valueOf(count));
        imageview.setVisibility(android.view.View.INVISIBLE);

        //---go to the next image---
        count++;
        if (count>3) count = 1;
        
        //---show the next image---
        imageview = (ImageView) frame.findViewWithTag(String.valueOf(count));
        imageview.setVisibility(android.view.View.VISIBLE);
	}
}
