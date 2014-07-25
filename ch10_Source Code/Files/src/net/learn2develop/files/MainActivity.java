package net.learn2develop.files;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {
    static final int READ_BLOCK_SIZE = 100;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//---writing to files---
		try {
			FileOutputStream fOut = openFileOutput("textfile.txt", MODE_PRIVATE);

			OutputStreamWriter osw = new OutputStreamWriter(fOut);

			//---write the string to the file---
			osw.write("The quick brown fox jumps over the lazy dog");
			osw.close();

			//---display file saved message---
			Toast.makeText(getBaseContext(), "File saved successfully!",
					Toast.LENGTH_SHORT).show();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		//---reading from files---		
        try 
        {  
            FileInputStream fIn = 
                openFileInput("textfile.txt");
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

}
