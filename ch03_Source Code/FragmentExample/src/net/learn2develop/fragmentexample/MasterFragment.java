package net.learn2develop.fragmentexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.app.ListFragment;

public class MasterFragment extends ListFragment {
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

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(getActivity(), 
                android.R.layout.simple_list_item_1,
                presidents));
    }

    public void onListItemClick(ListView parent, View v, int position, long id) {
        Toast.makeText(getActivity(), 
            "You have selected " + presidents[position], 
            Toast.LENGTH_SHORT).show();
        String selectedPresident = presidents[position];
        
        DetailFragment detailFragment = (DetailFragment) 
            getFragmentManager().findFragmentById(R.id.detailFragment);
        
        //---if the detail fragment is not in the current activity as myself---
        if (detailFragment != null && detailFragment.isInLayout()) {
            //---the detail fragment is in the same activity as the master---
            detailFragment.setSelectedPresident(selectedPresident);
        } else {
            //---the detail fragment is in its own activity---
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("president", selectedPresident);
            startActivity(intent);
        }
    }
}

