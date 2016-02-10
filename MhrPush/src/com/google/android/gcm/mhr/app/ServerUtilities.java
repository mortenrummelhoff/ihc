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

import static com.google.android.gcm.mhr.app.CommonUtilities.SERVER_URL;
import static com.google.android.gcm.mhr.app.CommonUtilities.TAG;
import static com.google.android.gcm.mhr.app.CommonUtilities.displayMessage;

import com.google.android.gcm.GCMRegistrar;
import com.google.android.gcm.mhr.app.R;

import android.R.bool;
import android.content.Context;
import android.util.JsonWriter;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

/**
 * Helper class used to communicate with the demo server.
 */
public final class ServerUtilities {

    private static final int MAX_ATTEMPTS = 5;
    private static final int BACKOFF_MILLI_SECONDS = 2000;
    private static final Random random = new Random();

    
    static boolean setKitchenLight(final boolean on) {
    	Log.i(TAG, "setKitchenLight (Power = " + on + ")");
        String serverUrl = SERVER_URL+"/ihc/set_kitchen_light";
        
        
        
        Map<String, String> params = new HashMap<String, String>();
        params.put("boolean", Boolean.toString(on));
        try {
			post(serverUrl, params);
			return true;
		} catch (IOException e) {
			 Log.e(TAG, "Failed to post set_kitchen_light", e);
		}
        
        return false;
    }
    
    
    
    static boolean setTvPower(final Context context, final boolean on) {
    	Log.i(TAG, "setTvPower (tvPower = " + on + ")");
        String serverUrl = SERVER_URL+"/ihc/set_tv_power";
        Map<String, String> params = new HashMap<String, String>();
        params.put("tvPower", Boolean.toString(on));
        try {
			post(serverUrl, params);
			return true;
		} catch (IOException e) {
			 Log.e(TAG, "Failed to post set_tv_power", e);
		}
        
        return false;
    }
    
    public static String setResourceValue(String method, String resource, String value) {
    	String serverUrl = SERVER_URL+"/ihc/" + method;
        
    	Map<String, String> params = new HashMap<String, String>();
        
        params.put("resource", resource);
        params.put("value", value);
        try {
			return post(serverUrl, params);
		} catch (IOException e) {
			 Log.e(TAG, "Failed to post set_resource_value", e);
		}
        
        return null;
    }
    
    static String setResourceValue(String resource, boolean set) {
    	Log.i(TAG, "setResourceValue " + resource );
        String serverUrl = SERVER_URL+"/ihc/set_resource_value";
        Map<String, String> params = new HashMap<String, String>();
        params.put("resource", resource);
        params.put("boolean", Boolean.toString(set));
        try {
			return post(serverUrl, params);
		} catch (IOException e) {
			 Log.e(TAG, "Failed to post set_resource_value", e);
		}
        
        return null;
    }
    
    public static String getReceiverValue(String resource) {		
    	Log.i(TAG, "getReceiverValue " + resource );
        String serverUrl = SERVER_URL+"/ihc/get_receiver_value";
        Map<String, String> params = new HashMap<String, String>();
        params.put("resource", resource);
        try {
			return post(serverUrl, params);
		} catch (IOException e) {
			 Log.e(TAG, "Failed to post get_receiver_value", e);
		}
        
        return null;
    }
    
    public static String getResourceValue(String resource) {		
    	Log.i(TAG, "getResourceValue " + resource );
        String serverUrl = SERVER_URL+"/ihc/get_resource_value";
        Map<String, String> params = new HashMap<String, String>();
        params.put("resource", resource);
        try {
			return post(serverUrl, params);
		} catch (IOException e) {
			 Log.e(TAG, "Failed to post get_resource_value", e);
		}
        
        return null;
    }
    
    
    
    /**
     * Register this account/device pair within the server.
     *
     * @return whether the registration succeeded or not.
     */
    static boolean register(final Context context, final String regId) {
        Log.i(TAG, "registering device (regId = " + regId + ")");
        String serverUrl = SERVER_URL + "/ihc/register";
        Map<String, String> params = new HashMap<String, String>();
        params.put("regId", regId);
        long backoff = BACKOFF_MILLI_SECONDS + random.nextInt(1000);
        // Once GCM returns a registration id, we need to register it in the
        // demo server. As the server might be down, we will retry it a couple
        // times.
        for (int i = 1; i <= MAX_ATTEMPTS; i++) {
            Log.d(TAG, "Attempt #" + i + " to register");
            try {
                displayMessage(context, context.getString(
                        R.string.server_registering, i, MAX_ATTEMPTS));
                post(serverUrl, params);
                GCMRegistrar.setRegisteredOnServer(context, true);
                String message = context.getString(R.string.server_registered);
                CommonUtilities.displayMessage(context, message);
                return true;
            } catch (IOException e) {
                // Here we are simplifying and retrying on any error; in a real
                // application, it should retry only on unrecoverable errors
                // (like HTTP error code 503).
                Log.e(TAG, "Failed to register on attempt " + i, e);
                if (i == MAX_ATTEMPTS) {
                    break;
                }
                try {
                    Log.d(TAG, "Sleeping for " + backoff + " ms before retry");
                    Thread.sleep(backoff);
                } catch (InterruptedException e1) {
                    // Activity finished before we complete - exit.
                    Log.d(TAG, "Thread interrupted: abort remaining retries!");
                    Thread.currentThread().interrupt();
                    return false;
                }
                // increase backoff exponentially
                backoff *= 2;
            }
        }
        String message = context.getString(R.string.server_register_error,
                MAX_ATTEMPTS);
        CommonUtilities.displayMessage(context, message);
        return false;
    }

    /**
     * Unregister this account/device pair within the server.
     */
    static void unregister(final Context context, final String regId) {
        Log.i(TAG, "unregistering device (regId = " + regId + ")");
        String serverUrl = SERVER_URL + "/ihc/unregister";
        Map<String, String> params = new HashMap<String, String>();
        params.put("regId", regId);
        try {
            post(serverUrl, params);
            GCMRegistrar.setRegisteredOnServer(context, false);
            String message = context.getString(R.string.server_unregistered);
            CommonUtilities.displayMessage(context, message);
        } catch (IOException e) {
            // At this point the device is unregistered from GCM, but still
            // registered in the server.
            // We could try to unregister again, but it is not necessary:
            // if the server tries to send a message to the device, it will get
            // a "NotRegistered" error message and should unregister the device.
            String message = context.getString(R.string.server_unregister_error,
                    e.getMessage());
            CommonUtilities.displayMessage(context, message);
        }
    }

    
    private static void writeJSONStream(OutputStream out, Map<String, String> params) throws IOException {
    	
    	JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
    	writer.beginObject();
    	for (Entry<String, String> entry: params.entrySet()) {
    			writer.name(entry.getKey()).value(entry.getValue());
    	}
    	writer.endObject();
    	writer.close();
    }
    
    /**
     * Issue a POST request to the server.
     *
     * @param endpoint POST address.
     * @param params request parameters.
     *
     * @throws IOException propagated from POST.
     */
    private static String post(String endpoint, Map<String, String> params)
            throws IOException {
    
    	String result = null;
    	URL url;
        try {
            url = new URL(endpoint);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("invalid url: " + endpoint);
        }
        // constructs the POST body using the parameters
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            //conn.setFixedLengthStreamingMode(bytes.length);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded;charset=UTF-8");
            // post the request
            OutputStream out = conn.getOutputStream();
            writeJSONStream(out, params);
            // handle the response
            int status = conn.getResponseCode();
            if (status != 200) {
              throw new IOException("Post failed with error code " + status);
            }
            
            byte[] b = new byte[4096];
            
            int bRead = conn.getInputStream().read(b);
            
            if (bRead != -1) {
            	byte[] bytesRead = new byte[bRead]; 
            	System.arraycopy(b, 0, bytesRead, 0, bytesRead.length);
                result = new String(bytesRead);
            }
            
            
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    return result;  
    }
    
    
}
