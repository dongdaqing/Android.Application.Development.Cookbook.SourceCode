package net.learn2develop.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;

public class InputNameDialogFragment extends DialogFragment{
	
    EditText txtName;
    Button btn;
    static String dialogTitle;
    
    //---Interface containing methods to be implemented 
    // by calling activity---
	public interface InputNameDialogListener {
        void onFinishInputDialog(String inputText);
    }

    public InputNameDialogFragment() {
    	//---empty constructor required---
    }
    
    //---set the title of the dialog window---
    public void setDialogTitle(String title) {
    	dialogTitle = title;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.fragment_inputname_dialog, container);
    	
    	//---get the EditText and Button views---
        txtName = (EditText) view.findViewById(R.id.txtName);
        btn = (Button) view.findViewById(R.id.btnDone);

        //---event handler for the button---
        btn.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View view) {
            	//---gets the calling activity---
            	InputNameDialogListener activity = (InputNameDialogListener) getActivity();
            	
                activity.onFinishInputDialog(txtName.getText().toString());
                //---dismiss the alert---
                dismiss();         	
            }
        });  
                
        //---show the keyboard automatically---
        txtName.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        //---set the title for the dialog---
        getDialog().setTitle(dialogTitle);
        
        return view;
    }
}
