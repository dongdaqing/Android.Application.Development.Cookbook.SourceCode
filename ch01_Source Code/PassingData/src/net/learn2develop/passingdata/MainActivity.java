package net.learn2develop.passingdata;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent i = new 
                Intent("net.learn2develop.SecondActivity");

        //---use putExtra() to add new key/value pairs---            
        i.putExtra("str1", "This is a string");
        i.putExtra("age1", 25);
        
        //---use a Bundle object to add new key/values 
        // pairs---  
        Bundle extras = new Bundle();
        extras.putString("str2", "This is another string");
        extras.putInt("age2", 35);                

        //---attach the Bundle object to the Intent object---
        i.putExtras(extras);                
        
        //---create my own custom object---
        MyCustomClass myObject = new MyCustomClass();
        myObject.setName("Wei-Meng Lee");
        myObject.setEmail("weimenglee@learn2develop.net");
        i.putExtra("MyObject", myObject);
        
        //---start the activity to get a result back---
        startActivityForResult(i, 1);        
    }
    
    public void onActivityResult(int requestCode, 
            int resultCode, Intent data)
    {
        //---check if the request code is 1---
        if (requestCode == 1) {

            //---if the result is OK--- 
            if (resultCode == RESULT_OK) {

                //---get the result using getIntExtra()---
                Toast.makeText(this, Integer.toString(
                        data.getIntExtra("age3", 0)), 
                        Toast.LENGTH_SHORT).show();      

                //---get the result using getData()---
                Uri url = data.getData();
                Toast.makeText(this, url.toString(), 
                        Toast.LENGTH_SHORT).show();
            }            
        }
    }

}
