package net.learn2develop.customlistview;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity  {
    String[] presidents = {
            "Dwight D. Eisenhower",
            "John F. Kennedy",
            "Lyndon B. Johnson",
            "Richard Nixon",
            "Gerald Ford",
            "Jimmy Carter",
            "Ronald Reagan",
            "George H. W. Bush",
            "Bill Clinton",
            "George W. Bush",
            "Barack Obama"
    };
    
    Integer[] imageIDs = {
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5,
            R.drawable.pic6,
            R.drawable.pic7,
            R.drawable.pic8,
            R.drawable.pic9,
            R.drawable.pic10,
            R.drawable.pic11
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        this.setListAdapter(new ArrayAdapter<String>(
                this, 
                R.layout.lvrowlayout,
                R.id.txtPresidentName, 
                presidents));
        */
        
        /*
        //---using custom array adapter---
        CustomArrayAdapter adapter = new CustomArrayAdapter(this, presidents, imageIDs);
        setListAdapter(adapter);
        */
        
        //---using custom array adapter (with recycling)---
        AdvancedCustomArrayAdapter adapter = 
            new AdvancedCustomArrayAdapter(this, presidents, imageIDs);
        setListAdapter(adapter);

    }
    
}
