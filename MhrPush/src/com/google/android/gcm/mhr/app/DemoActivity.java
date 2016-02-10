/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.gcm.mhr.app;

import static com.google.android.gcm.mhr.app.CommonUtilities.DISPLAY_MESSAGE_ACTION;
import static com.google.android.gcm.mhr.app.CommonUtilities.EXTRA_MESSAGE;
import static com.google.android.gcm.mhr.app.CommonUtilities.SENDER_ID;
import static com.google.android.gcm.mhr.app.CommonUtilities.SERVER_URL;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gcm.GCMRegistrar;
import com.google.android.gcm.mhr.app.receiver.ReceiverActivity;

/**
 * Main UI for the demo app.
 */
public class DemoActivity extends Activity {

   
	private final static int KITCHEN_PUSH_UP_LEFT = 404060;
	private final static int KITCHEN_PUSH_UP_RIGHT = 404316;
	
	private final static int ON_BUTTON_RESOUCE = 404572;
	private final static int OFF_BUTTON_RESOUCE = 404828;
	
	private final static int KITCHEN_DIMMER = 521490;
	
	private final static int TEL_SWITCH_OUT = 20062;
	private final static int TEL_BUTTON_OFF = 755473;
	private final static int TEL_BUTTON_ON = 777489;
	
    TextView mDisplay;
    AsyncTask<Void, Void, Void> mRegisterTask;
    AsyncTask<Void, Void, Void> mTvPowerTask; 
    AsyncTask<String, Void, String> mResourceValueTask;
    
    ToggleButton mButton;
    
    private static final String TAG = "DemoActivity";

    
    public void onKitchenPowerClick(View view) {
    	final ToggleButton mButton = (ToggleButton) findViewById(R.id.KitchenToggleButton);
    	
    	mDisplay.append("Kitchen Light Clicked" + (mButton.isChecked() ? 
    			", Power On" : ", Power Off") + "\n");
    	final Context context = this;
    	mTvPowerTask = new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				ServerUtilities.setKitchenLight(mButton.isChecked());
				return null;
			}
		
    		
    	};
    	mTvPowerTask.execute(null, null, null);
    }
    
    public void onTVPowerClick(View view) {
    	final ToggleButton mButton = (ToggleButton) findViewById(R.id.toggleButton1);
    	
    	mDisplay.append("Power Button Clicked" + (mButton.isChecked() ? 
    			", Power On" : ", Power Off") + "\n");
    	final Context context = this;
    	mTvPowerTask = new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				ServerUtilities.setTvPower(context, mButton.isChecked());
				return null;
			}
		
    		
    	};
    	mTvPowerTask.execute(null, null, null);
    	
    }
    
    
    public void setResourceValueClick(View view) {
    	final ToggleButton mButton = (ToggleButton) findViewById(R.id.toggleButton2);
    
    	EditText mResourceText = (EditText) findViewById(R.id.editResourceId); 
    	final String resource = mResourceText.getText().toString();
    	
    	mDisplay.append("\nResource Button Checked:" + (mButton.isChecked()));
    	
    	mResourceValueTask = new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... params) {
				return ServerUtilities.setResourceValue(resource, mButton.isChecked());
			}
	
    	};
    	mResourceValueTask.execute(null, null, null);
    	
    	try {
    		
    		String value = mResourceValueTask.get();
    		mDisplay.append("\n" + value);
			Log.i(TAG, "setResourceValue returned: " + mResourceValueTask.get());
			
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
    
    public void getResourceValueClick(View view) {
    	EditText mResourceText = (EditText) findViewById(R.id.editResourceId); 
    	
    	Log.i(TAG, "getResourceValueClick called, ResourceId Text: " + mResourceText.getText());
    	
    	final String resource = mResourceText.getText().toString();
    	
    	mResourceValueTask = new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... params) {
				return ServerUtilities.getResourceValue(resource);
			}
	
    	};
    	mResourceValueTask.execute(null, null, null);
    	
    	try {
    		
    		String value = mResourceValueTask.get();
    		mDisplay.append("\n" + value);
			Log.i(TAG, "getResourceValue returned: " + mResourceValueTask.get());
			
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkNotNull(SERVER_URL, "SERVER_URL");
        checkNotNull(SENDER_ID, "SENDER_ID");
        // Make sure the device has the proper dependencies.
        GCMRegistrar.checkDevice(this);
        // Make sure the manifest was properly set - comment out this line
        // while developing the app, then uncomment it when it's ready.
        GCMRegistrar.checkManifest(this);
        setContentView(R.layout.main);
        mDisplay = (TextView) findViewById(R.id.display);
        
        registerReceiver(mHandleMessageReceiver,
                new IntentFilter(DISPLAY_MESSAGE_ACTION));
        final String regId = GCMRegistrar.getRegistrationId(this);
        if (regId.equals("")) {
        	
            // Automatically registers application on startup.
            GCMRegistrar.register(this, SENDER_ID);
        } else {
        	GCMRegistrar.unregister(this);
        	
        	// Automatically registers application on startup.
            GCMRegistrar.register(this, SENDER_ID);
        	
        	// Device is already registered on GCM, check server.
            if (GCMRegistrar.isRegisteredOnServer(this)) {
                // Skips registration.
                mDisplay.append(getString(R.string.already_registered) + "\n");
            } else {
                // Try to register again, but not in the UI thread.
                // It's also necessary to cancel the thread onDestroy(),
                // hence the use of AsyncTask instead of a raw thread.
                final Context context = this;
                mRegisterTask = new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... params) {
                        boolean registered =
                                ServerUtilities.register(context, regId);
                        // At this point all attempts to register with the app
                        // server failed, so we need to unregister the device
                        // from GCM - the app will try to register again when
                        // it is restarted. Note that GCM will send an
                        // unregistered callback upon completion, but
                        // GCMIntentService.onUnregistered() will ignore it.
                        if (!registered) {
                            GCMRegistrar.unregister(context);
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        mRegisterTask = null;
                    }

                };
                mRegisterTask.execute(null, null, null);
                
                
               
            }
            updateGUI();
        }
    }

    private void updateGUI() {
    	
    	
    	AsyncTask<String, Void, String[]> task = new AsyncTask<String, Void, String[]>() {

			@Override
			protected String[] doInBackground(String... params) {
				
				String[] result = new String[params.length];
				
				for (int i = 0; i < params.length; i++) {
					result[i] = ServerUtilities.getResourceValue(params[i]);
					Log.i(TAG, "getResourceValue returned: " + result[i]);
				    
				}	
				return result;
			}

			@Override
			protected void onPostExecute(String[] result) {

				String telString = result[0];
				String dinnerLight = result[1];
				
				//update gui    
				boolean on = false;
		    	if (telString != null) {
		    		
		    		try {
						JSONObject jsonObject = new JSONObject(telString);
						Log.i(TAG, "TV POWER TURNED ON");
		    	    	on = Boolean.parseBoolean(jsonObject.getString("value"));		
					} catch (JSONException e) {
						e.printStackTrace();
					}
		    	}
		     	
		    	//get tv power button and set it
		    	ToggleButton mButton = (ToggleButton) findViewById(R.id.toggleButton1);
		    	mButton.setChecked(on);
		    			
		    	
		    	//request kitchen light status
		    	on = false;
		    	if (dinnerLight != null) {
		    		
		    		try {
						JSONObject jsonObject = new JSONObject(dinnerLight);
						on = Boolean.parseBoolean(jsonObject.getString("value"));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		
		    	}
		     	
		    	//get tv power button and set it
		    	mButton = (ToggleButton) findViewById(R.id.KitchenToggleButton);
		    	mButton.setChecked(on);
		    	
			}
			
			
    	};
    	
    	task.execute(TEL_SWITCH_OUT+"", KITCHEN_DIMMER+"");
    	
    }
    
    @Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		updateGUI();
		
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		
		//updateGUI();
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            /*
             * Typically, an application registers automatically, so options
             * below are disabled. Uncomment them if you want to manually
             * register or unregister the device (you will also need to
             * uncomment the equivalent options on options_menu.xml).
             */
            /*
            case R.id.options_register:
                GCMRegistrar.register(this, SENDER_ID);
                return true;
            case R.id.options_unregister:
                GCMRegistrar.unregister(this);
                return true;
             */
            case R.id.options_clear:
                mDisplay.setText(null);
                return true;
            case R.id.options_exit:
                finish();
                return true;
                
            case R.id.open_receiver_screen:
            	mDisplay.append("\nOpening Pioneer Receiver Screen");
            	
            	Intent intent = new Intent(this, ReceiverActivity.class);
                startActivity(intent);
            	
            	return true;
            	
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        if (mRegisterTask != null) {
            mRegisterTask.cancel(true);
        }
        unregisterReceiver(mHandleMessageReceiver);
        
        final String regId = GCMRegistrar.getRegistrationId(this);
        if (regId.equals("")) {
        	GCMRegistrar.onDestroy(this);	
        }
        
        super.onDestroy();
    }

    private void checkNotNull(Object reference, String name) {
        if (reference == null) {
            throw new NullPointerException(
                    getString(R.string.error_config, name));
        }
    }

    private final BroadcastReceiver mHandleMessageReceiver =
            new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String newMessage = intent.getExtras().getString(EXTRA_MESSAGE);
            mDisplay.append(newMessage + "\n");
        }
    };

}