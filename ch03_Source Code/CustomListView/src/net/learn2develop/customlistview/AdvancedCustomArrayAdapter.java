package net.learn2develop.customlistview;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdvancedCustomArrayAdapter extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] presidents;
    private final Integer[] imageIds;

    public AdvancedCustomArrayAdapter(
    Activity context, String[] presidents, Integer[] imageIds) {
        super(context, R.layout.lvrowlayout2, presidents);
        this.context = context;
        this.presidents = presidents;
        this.imageIds = imageIds;
    }

    static class ViewContainer {
        public ImageView imageView;
        public TextView txtTitle;
        public TextView txtDescription;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewContainer viewContainer;
        View rowView = view;

        //---print the index of the row to examine---
        Log.d("CustomArrayAdapter",String.valueOf(position));

        //---if the row is displayed for the first time---
        if (rowView == null) {

            Log.d("CustomArrayAdapter", "New");
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.lvrowlayout2, null, true);

            //---create a view container object---
            viewContainer = new ViewContainer();        

            //---get the references to all the views in the row---
            viewContainer.txtTitle = (TextView) 
                rowView.findViewById(R.id.txtPresidentName); 
            viewContainer.txtDescription = (TextView) 
                rowView.findViewById(R.id.txtDescription); 
            viewContainer.imageView = (ImageView) rowView.findViewById(R.id.icon);

            //---assign the view container to the rowView---
            rowView.setTag(viewContainer);
        } else { 

            //---view was previously created; can recycle---            
            Log.d("CustomArrayAdapter", "Recycling");
            //---retrieve the previously assigned tag to get
            // a reference to all the views; bypass the findViewByID() process,
            // which is computationally expensive---
            viewContainer = (ViewContainer) rowView.getTag();
        }

        //---customize the content of each row based on position---
        viewContainer.txtTitle.setText(presidents[position]);
        viewContainer.txtDescription.setText(presidents[position] + 
            " ...Some descriptions here...");
        viewContainer.imageView.setImageResource(imageIds[position]);
        return rowView;
    }
}


