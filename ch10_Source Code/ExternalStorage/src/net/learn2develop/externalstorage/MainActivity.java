package net.learn2develop.externalstorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

public class MainActivity extends Activity {
    static final int READ_BLOCK_SIZE = 100;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //---writing to files---
        try 
        { 
            if (IsExternalStorageAvailableAndWriteable()) {
                //---external Storage---
                File extStorage = 
                        getExternalFilesDir(null);
                File file = new File(extStorage, "textfile.txt");                                

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
        } 
        catch (IOException ioe) 
        { 
            ioe.printStackTrace(); 
        }      

        //---reading from files---
        try 
        {             
            if (IsExternalStorageAvailableAndWriteable()) {
                //---External Storage---                  
                File extStorage = 
                        getExternalFilesDir(null);
                File file = new File(extStorage, "textfile.txt");                                

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
        } 
        catch (IOException ioe) { 
            ioe.printStackTrace(); 
        }

    }

    public boolean IsExternalStorageAvailableAndWriteable() {
        boolean externalStorageAvailable = false;
        boolean externalStorageWriteable = false;
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            //---you can read and write the media---
            externalStorageAvailable = externalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            //---you can only read the media---
            externalStorageAvailable = true;
            externalStorageWriteable = false;
        } else {
            //---you cannot read nor write the media---
            externalStorageAvailable = externalStorageWriteable = false;
        }
        return externalStorageAvailable && externalStorageWriteable;
    }
    
}
