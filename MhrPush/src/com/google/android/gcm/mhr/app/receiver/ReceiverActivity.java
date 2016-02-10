package com.google.android.gcm.mhr.app.receiver;

import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import android.app.ActionBar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.ToggleButton;

import com.google.android.gcm.mhr.app.R;
import com.google.android.gcm.mhr.app.R.id;
import com.google.android.gcm.mhr.app.ServerUtilities;

public class ReceiverActivity extends FragmentActivity {
	private static final String TAG = "ReceiverActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.receiver);

		// Set up action bar.
		final ActionBar actionBar = getActionBar();

		// Specify that the Home button should show an "Up" caret, indicating that touching the
		// button will take the user one step up in the application's hierarchy.
		actionBar.setDisplayHomeAsUpEnabled(true);



		//add listener for volumenbar
		SeekBar sBar = (SeekBar) findViewById(R.id.seekBar1);

		sBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

				final String vol = seekBar.getProgress()+"";
				Log.i(TAG, "setVolumenOnClick, Bar value: " + seekBar.getProgress());

				AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {
					@Override
					protected String doInBackground(String... params) {
						return 	ServerUtilities.setResourceValue("set_receiver_volumen", vol, 
								vol);
					}
				}.execute("");
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {	
			}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {	
			}
		});

		updateGUI();
	}

	private void updateGUI() {
		new AsyncTask<String, Void, String[]>() {

			@Override
			protected String[] doInBackground(String... params) {
				String[] result = new String[params.length];
				result[0] = ServerUtilities.getReceiverValue("?P");
				result[1] = ServerUtilities.getReceiverValue("?V");
				result[2] = ServerUtilities.getReceiverValue("?PR");

				return result;
			}

			@Override
			protected void onPostExecute(String[] result) {
				Log.i(TAG, "getReceiverValue returned : " + Arrays.toString(result));	
				try {
					JSONObject obj = new JSONObject(result[0]);
					Log.i(TAG, "getReceiverValue JSON returned : " + obj.getString("Value"));
					ToggleButton mButton = (ToggleButton) findViewById(R.id.receiverPowerButton);
					mButton.setChecked("PWR0".equals(obj.getString("Value")));

					obj = new JSONObject(result[1]);
					Log.i(TAG, "getReceiverValue JSON returned : " + obj.getString("Value"));
					SeekBar sBar = (SeekBar) findViewById(R.id.seekBar1);

					try {
						int vol = Integer.parseInt(obj.getString("Value").substring(3));
						sBar.setProgress(vol);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}

					obj = new JSONObject(result[2]);
					Log.i(TAG, "getReceiverValue JSON returned : " + obj.getString("Value"));
					try {
						int ch = Integer.parseInt(obj.getString("Value").substring(3));
						RadioButton button = null;
						switch (ch) {
						case 1:
							button = (RadioButton) findViewById(id.radio0_drp3);
							break;
						case 2:
							button = (RadioButton) findViewById(id.radio1_novafm);
							break;
						case 3:
							button = (RadioButton) findViewById(id.radio2_ramasjang);
							break;
						case 4:
							button = (RadioButton) findViewById(id.radio4_p4);
							break;
						case 5:
							button = (RadioButton) findViewById(id.radio5_p5);
							break;
						case 6:
							button = (RadioButton) findViewById(id.radio6_p6);
							break;
						case 7:
							button = (RadioButton) findViewById(id.radio7_p7);
							break;
						
						}
						 
						if (button != null) {
							button.setChecked(true);
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
					

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}.execute(new String[] {"", "", ""});


	}

	public void receiverTunerChannel1OnClick(View view) {
		setTunerChannel("1");
	}
	public void receiverTunerChannel2OnClick(View view) {
		setTunerChannel("2");
	}
	public void receiverTunerChannel3OnClick(View view) {
		setTunerChannel("3");
	}
	public void receiverTunerChannel4OnClick(View view) {
		setTunerChannel("4");
	}
	public void receiverTunerChannel5OnClick(View view) {
		setTunerChannel("5");
	}
	public void receiverTunerChannel6OnClick(View view) {
		setTunerChannel("6");
	}
	public void receiverTunerChannel7OnClick(View view) {
		setTunerChannel("7");
	}

	private void setTunerChannel(final String ch) {
		AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... params) {
				return 	ServerUtilities.setResourceValue("set_tuner_channel", ch, 
						ch);
			}

		}.execute("");

	}

	public void receiverPowerOnClick(View view) {


		final ToggleButton mButton = (ToggleButton) findViewById(R.id.receiverPowerButton);

		Log.i(TAG, "receiverPowerOnClick, Clicked: " + mButton.isChecked());


		AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... params) {
				return 	ServerUtilities.setResourceValue("set_receiver_power", null, 
						mButton.isChecked()+"");
			}

		}.execute("");


	}


}
