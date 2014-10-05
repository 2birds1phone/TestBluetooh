package com.example.bluetoothsynchrotest;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;
import android.bluetooth.*;
import android.content.*;

import java.util.*;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    protected void onStart() {
    	super.onStart();
    	TextView text = (TextView) this.findViewById(R.id.textview);
    	text.setText("Test");
    	
    	BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
    	if (adapter == null) {
    		//Bluetooth not supported on this device
    	}
    	
    	if (!adapter.isEnabled()) {
    	    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
    	    startActivityForResult(enableBtIntent, 1);
    	}
    	
        Set<BluetoothDevice> setdevices = adapter.getBondedDevices();
        
        if(adapter.startDiscovery())
        	text.append("\r\nDiscovery ok ");
        
        Iterator<BluetoothDevice> it = setdevices.iterator();
        while(it.hasNext()) 
        	text.append(setdevices.toString()+ "  " + it.next().getName() );
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	
    	
    	//turn off the bluetooth if opened by the app
    	
    	BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
    	if (adapter == null) {
    		//Bluetooth not supported on this device
    	}
    }
    
    protected void client() {
    	
    	
    }
    
    protected void server() {
    	
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
