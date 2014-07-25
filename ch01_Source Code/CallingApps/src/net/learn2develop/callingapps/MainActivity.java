package net.learn2develop.callingapps;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*
        Intent i = new Intent(android.content.Intent.ACTION_VIEW);
        i.setData(Uri.parse("geo:37.827500,-122.481670"));
        startActivity(i);
		 */


		/*
        Intent i = new Intent(android.content.Intent.ACTION_VIEW);
        i.setData(Uri.parse("market://details?id=com.zinio.mobile.android.reader"));        
        startActivity(i);
		 */

		/*
        Intent i = new Intent(Intent.ACTION_SEND); 
        i.setData(Uri.parse("mailto:"));
        String[] to = { "someone1@example.com" , "someone2@example.com" };
        String[] cc = { "someone3@example.com" , "someone4@example.com" }; 
        i.putExtra(Intent.EXTRA_EMAIL, to); 
        i.putExtra(Intent.EXTRA_CC, cc); 
        i.putExtra(Intent.EXTRA_SUBJECT, "Subject here..."); 
        i.putExtra(Intent.EXTRA_TEXT, "Message here..."); 
        i.setType("message/rfc822"); 
        startActivity(Intent.createChooser(i, "Email"));
		 */

		/*
        Intent i = new Intent(android.content.Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Subject...");
        i.putExtra(Intent.EXTRA_TEXT, "Text...");
        startActivity(Intent.createChooser(i, "Apps that can respond to this"));
		 */

		/*
        //---sending binary content---
        Uri uriToImage = 
            Uri.parse("android.resource://net.learn2develop.CallingApps/drawable/" + 
            Integer.toString(R.drawable.android));

        Intent i = new Intent(android.content.Intent.ACTION_SEND);
        i.setType("image/jpeg");
        i.putExtra(Intent.EXTRA_STREAM, uriToImage);
        i.putExtra(Intent.EXTRA_TEXT, "Text...");
        startActivity(Intent.createChooser(i, "Apps that can respond to this"));
		 */

		
		/*
        Uri uriToImage1 = 
                Uri.parse("android.resource://net.learn2develop.CallingApps/drawable/" + 
                Integer.toString(R.drawable.android));
        Uri uriToImage2 = 
                Uri.parse("android.resource://net.learn2develop.CallingApps/drawable/" + 
                Integer.toString(R.drawable.jellybean));
            
        ArrayList<Uri> urisToImages = new ArrayList<Uri>();
        urisToImages.add(uriToImage1); 
        urisToImages.add(uriToImage2);
            
        Intent i = new Intent(android.content.Intent.ACTION_SEND_MULTIPLE);
        i.setType("image/*");
            
        i.putExtra(Intent.EXTRA_STREAM, urisToImages);
        i.putExtra(Intent.EXTRA_SUBJECT, "Subject...");
        i.putExtra(Intent.EXTRA_TEXT, "Text...");        
        startActivity(Intent.createChooser(i, "Apps that can respond to this"));
        */
        
        Intent i = new Intent(android.content.Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file:///storage/sdcard0/MyPhoto.jpg"), "image/*");        
        startActivity(i);
        
	}

}
