package net.learn2develop.contextandoptionsmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btn = (Button) findViewById(R.id.button1);   
        btn.setOnCreateContextMenuListener(this);
    }

    //---Options Menu---
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void onGroupItemClick (MenuItem item) {
    	
    	
    	if (item.isChecked()) {
    	    item.setChecked(false);	
    	} else {
    	    item.setChecked(true);
    	}
    	
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {    
    	Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
         return true;    
    }    
    //------------------
    
    //---Context Menu---
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, 
    ContextMenuInfo menuInfo) 
    {
        getMenuInflater().inflate(R.menu.activity_main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {    
    	Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
    	return true;
    }
    //------------------
    
}
