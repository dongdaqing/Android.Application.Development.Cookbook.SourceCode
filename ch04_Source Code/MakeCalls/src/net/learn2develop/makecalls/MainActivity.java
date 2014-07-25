package net.learn2develop.makecalls;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String phoneNumber = "+13175723496";
        
        
        //---dial---        
        Intent i = new
                Intent(android.content.Intent.ACTION_DIAL, 
                        Uri.parse("tel:+" + phoneNumber));        
        startActivity(i);
        
        
        /*
        Intent i = new
                Intent(android.content.Intent.ACTION_CALL, 
                        Uri.parse("tel:+" + phoneNumber));        
        startActivity(i);     
        */        
    }
    
}
