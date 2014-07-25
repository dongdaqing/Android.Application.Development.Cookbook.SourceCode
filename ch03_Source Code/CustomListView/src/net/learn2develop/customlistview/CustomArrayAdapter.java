package net.learn2develop.customlistview;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] presidents;
    private final Integer[] imageIds;

    public CustomArrayAdapter(Activity context, 
    String[] presidents, Integer[] imageIds) {
        super(context, R.layout.lvrowlayout2, presidents);
        this.context = context;
        this.presidents = presidents;
        this.imageIds = imageIds;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {     
        //---print the index of the row to examine---
        Log.d("CustomArrayAdapter",String.valueOf(position));

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.lvrowlayout2, null, true);

        //---get a reference to all the views on the xml layout---
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txtPresidentName); 
        TextView txtDescription = (TextView) rowView.findViewById(R.id.txtDescription); 
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        //---customize the content of each row based on position---
        txtTitle.setText(presidents[position]);
        txtDescription.setText(presidents[position] + "...Some descriptions here...");
        imageView.setImageResource(imageIds[position]);
        return rowView;
    }
}

