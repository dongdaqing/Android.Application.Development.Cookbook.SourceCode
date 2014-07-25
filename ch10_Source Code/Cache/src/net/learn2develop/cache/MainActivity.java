package net.learn2develop.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {
    static final int READ_BLOCK_SIZE = 100;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //---Saving to the file in the Cache folder---
        try 
        { 
            //---get the Cache directory---
            File cacheDir = getCacheDir();            
            File file = new File(cacheDir.getAbsolutePath(), "textfile.txt");                                

            FileOutputStream fOut = new 
                FileOutputStream(file);

            OutputStreamWriter osw = new 
                OutputStreamWriter(fOut);  

            //---write the string to the file--- 
            osw.write("The quick brown fox jumps over the lazy dog");              
            osw.flush(); 
            osw.close();
            
            //---display file saved message---
            Toast.makeText(getBaseContext(), 
                "File saved successfully!", 
                Toast.LENGTH_SHORT).show();         
        } 
        catch (IOException ioe) 
        { 
             ioe.printStackTrace(); 
        }        

        //---Reading from the file in the Cache folder---
 
        try 
        {             
            //---get the Cache directory---                  
            File cacheDir = getCacheDir();
            File file = new File(cacheDir, "textfile.txt");
            FileInputStream fIn = new FileInputStream(file);                
            
            InputStreamReader isr = new 
                InputStreamReader(fIn); 
 
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;
            while ((charRead = isr.read(inputBuffer))>0)
            {                    
                //---convert the chars to a String---
                String readString = 
                    String.copyValueOf(inputBuffer, 0, 
                    charRead);                    
                s += readString;

               inputBuffer = new char[READ_BLOCK_SIZE];
            } 
            isr.close();
            Toast.makeText(getBaseContext(),
                "File loaded successfully! " + s, 
                Toast.LENGTH_SHORT).show();
        } 
        catch (IOException ioe) { 
            ioe.printStackTrace(); 
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
