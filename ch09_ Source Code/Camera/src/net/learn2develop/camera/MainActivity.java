package net.learn2develop.camera;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	static int TAKE_PICTURE = 1;
	Uri outputFileUri;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    public void btnTakePhoto(View view) {
    	Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);		
        File file = new File(Environment.getExternalStorageDirectory(), "MyPhoto.jpg"); 
		outputFileUri = Uri.fromFile(file);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
		startActivityForResult(intent, TAKE_PICTURE); 
    }
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
		if (requestCode == TAKE_PICTURE && resultCode==RESULT_OK){
			Toast.makeText(this, outputFileUri.toString(), Toast.LENGTH_LONG).show();
		}
	}
    
}
