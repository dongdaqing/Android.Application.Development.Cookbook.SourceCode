package net.learn2develop.imageswitcher;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity implements ViewFactory {
    //---the images to display---
    Integer[] imageIDs = {
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5,
            R.drawable.pic6,
            R.drawable.pic7
    };

    private ImageSwitcher imageSwitcher;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageSwitcher = (ImageSwitcher) findViewById(R.id.switcher1); 
        imageSwitcher.setFactory(this);

        imageSwitcher.setInAnimation(
            AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        imageSwitcher.setOutAnimation( AnimationUtils.loadAnimation(this,
            android.R.anim.fade_out));

        Gallery gallery = (Gallery) findViewById(R.id.gallery1); 
        gallery.setAdapter(new ImageAdapter(this)); 
        gallery.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                imageSwitcher.setImageResource(imageIDs[position]); 
            }
        });
    }

    @Override
    public View makeView() {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundColor(0xFF000000); 
        imageView.setScaleType(
            ImageView.ScaleType.FIT_CENTER); 
        imageView.setLayoutParams(new
            ImageSwitcher.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT)
        );
        return imageView;
    }

    public class ImageAdapter extends BaseAdapter {
        private Context context;
        private int itemBackground;

        public ImageAdapter(Context c)
        {
            context = c;
            //---sets a grey background; wraps around the images
            TypedArray a =
                obtainStyledAttributes(R.styleable.MyGallery); 
            itemBackground = a.getResourceId(
                R.styleable.MyGallery_android_galleryItemBackground, 0); 
            a.recycle();
        }

        //---returns the number of images---
        public int getCount()
        {
            return imageIDs.length;
        }

        //---returns the ID of an item---
        public Object getItem(int position)
        {
            return position;
        }

        public long getItemId(int position)
        {
            return position;
        }

        //---returns an ImageView view---
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(imageIDs[position]); 
            imageView.setScaleType(ImageView.ScaleType.FIT_XY); 
            imageView.setLayoutParams(new Gallery.LayoutParams(
                150, 120)); imageView.setBackgroundResource(itemBackground);
            return imageView;
        }
    }
}
