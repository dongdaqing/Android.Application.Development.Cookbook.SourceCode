package net.learn2develop.swiping;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        MyPageAdapter adapter = new MyPageAdapter();
		ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
		viewPager.setAdapter(adapter);

		//---starts with the second page---
		viewPager.setCurrentItem(1);
    }

    public void onClick(View view) {
        int buttonTag = Integer.valueOf(view.getTag().toString());
        Toast.makeText(this, "Button " + Integer.toString(buttonTag)+ 
        		" clicked", Toast.LENGTH_LONG).show();
	}    
}
