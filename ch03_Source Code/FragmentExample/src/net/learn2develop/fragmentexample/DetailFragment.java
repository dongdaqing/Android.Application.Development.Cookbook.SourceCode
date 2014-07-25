package net.learn2develop.fragmentexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v4.app.Fragment;

public class DetailFragment extends Fragment {    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_detail, container, false);
        return view;
    }

    public void setSelectedPresident(String name) {
        TextView view = (TextView) getView().findViewById(R.id.txtSelectedPresident);
        view.setText("You have selected " + name);
    }    
}

