package net.learn2develop.dialogs;

import net.learn2develop.dialogs.InputNameDialogFragment.InputNameDialogListener;
import net.learn2develop.dialogs.YesNoDialogFragment.YesNoDialogListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends FragmentActivity 
    implements InputNameDialogListener, YesNoDialogListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    
    }
    
    //========================================
    public void btnShowDialog(View view) {
        showInputNameDialog();	
    }
           
    private void showInputNameDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        InputNameDialogFragment inputNameDialog = new InputNameDialogFragment();
        inputNameDialog.setCancelable(false);
        inputNameDialog.setDialogTitle("Enter Name");
        inputNameDialog.show(fragmentManager, "input dialog");
    }

    @Override
    public void onFinishInputDialog(String inputText) {
        Toast.makeText(this, "Returned from dialog: " + inputText, 
        		Toast.LENGTH_SHORT).show();
    }
    //========================================
   
    
    
    //========================================
    public void btnShowYesNoDialog(View view) {
        showYesNoDialog();	
    }
           
    private void showYesNoDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        YesNoDialogFragment yesnoDialog = new YesNoDialogFragment();
        yesnoDialog.setCancelable(false);
        yesnoDialog.setDialogTitle("Status change");
        yesnoDialog.show(fragmentManager, "yes/no dialog");
    }
    
	@Override
	public void onFinishYesNoDialog(boolean state) {
		 Toast.makeText(this, "Returned from dialog: " + state, 
	        		Toast.LENGTH_SHORT).show();		
	}
    //========================================
	
}
