package dk.ihc.receiver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class TelnetManager {


	private Socket client;

	private final static String RECEIVER_IP = "192.168.0.45";
	private final static int RECEIVER_PORT = 23;	

	private Logger aLog = Logger.getLogger(getClass());

	private TelnetCommandHandler telnetCommandHandler;

	private ConnectionThread aConnectionThread;

	private List<ReceiverConnectionListener> aReceiverConnectionListenerList = new ArrayList<ReceiverConnectionListener>();
	private ReceiverConnectionListener connectionListener;


	public TelnetManager() {
		if (aLog.isDebugEnabled()) {
			aLog.debug("Starting Telnet Manager");
		}		
	}



	public void addConnectionListener(ReceiverConnectionListener listener) {
		if (!aReceiverConnectionListenerList.contains(listener)) {
			aReceiverConnectionListenerList.add(listener);

			if (aConnectionThread != null && aConnectionThread.isConnected()) {
				listener.connected();
			}
		}
	}

	public void removeConnectionListener(ReceiverConnectionListener listener) {
		aReceiverConnectionListenerList.remove(listener);
	}


	public void establishConnection() {

		if (aConnectionThread == null) {
			aConnectionThread = new ConnectionThread();
			aConnectionThread.start();
		}

	}


	public void closeTelnetConnection() {
		if (client != null) {
			try {
				client.close();
			} catch (IOException e) {
				//ignore
			}
		}
		aConnectionThread = null;
	}

	private void checkConnection() {

		if (aConnectionThread == null || 
				!aConnectionThread.isConnected()) {

			closeTelnetConnection();
			establishConnection();

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
		}

	}

	public String getValue(String resource) {
		String val = "UNKNOWN";
		
		if (aConnectionThread != null && aConnectionThread.isConnected()) {
			val = telnetCommandHandler.sendCommand(resource + "\r\n", true);
		}
		
		return val;
	}
	
	public void setPower(boolean set) {
		checkConnection();
		
		telnetCommandHandler.sendCommand(set ? "po\r\n" : "pf\r\n", false);
	}

	public void setMode(String mode) {
		checkConnection();
		
		if ("tuner".equalsIgnoreCase(mode)) {
			//put in in tuner mode
			String response = telnetCommandHandler.sendCommand("02FN\r\n", true);
			aLog.debug("Command: {}, Response: {}", "02FN", response);
		}
	}

	public synchronized void setTunerChannel(int channel) {
		checkConnection();
		
		
		String response = telnetCommandHandler.sendCommand(channel+"TP\r\n", true);
		aLog.debug("Command: {}, Response: {}", channel+"TP", response);	
	}

	public String setVolumen(int vol) {
		checkConnection();
		
		//int v =  (int) (vol*1.5);
		
		String response = telnetCommandHandler.sendCommand(String.format("%03d", vol)+"VL\r\n", true);
		aLog.debug("Command setVolumen response:" + response );
	
		return response;
	}
	
	private class TelnetCommandHandler {

		private BufferedWriter outputStream;
		private TelnetInputReader telnetInputReader;

		public TelnetCommandHandler(OutputStream os, TelnetInputReader reader) {
			telnetInputReader = reader;

			outputStream = new BufferedWriter(new OutputStreamWriter(os));
		}

		public String sendCommand(String command, boolean waitForResponse) {			
			if (outputStream != null) {
				try {					
					if (aLog.isDebugEnabled()) {
						aLog.debug("sendCommand called: {}", command);
					}

					telnetInputReader.clearResponse();

					outputStream.write(command);
					//outputStream.newLine();
					outputStream.flush();

					if (waitForResponse) {
						return telnetInputReader.getResponse();
					}

					//if no waitForResponse, then the interface description states that commands must be sent with an minimum frequence of 100 ms
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
					}

				} catch (IOException e) {
					aLog.error("Error sending command: " + command, e);
					
					//close connection
					closeTelnetConnection();
				}

			}
			return "";
		}

	}

	private class TelnetInputReader extends Thread {

		private boolean isRunning = true;
		private InputStream inputStream;
		private String response;

		BufferedReader in = null;
		
		private Object MUTEX = new Object();

		public TelnetInputReader(InputStream is) {
			this.inputStream = is;
			in = new BufferedReader(new InputStreamReader(is));
		}

		public void clearResponse() {
			response = null;
			
			
		}

		public String getResponse() {
			if (aLog.isDebugEnabled()) {
				aLog.debug("Start getResponse, response: {}", response);
			}

			String localResponse = null;
			if (response != null) {
				localResponse = response;
				response = null;
			}
			else {
				synchronized (MUTEX) {
					try {
						MUTEX.wait(2000);
					} catch (InterruptedException e) {
						//ignore
					}
				}
			}
			localResponse = response;
			response = null;

			if (aLog.isDebugEnabled()) {
				aLog.debug("Finished getResponse, response: {}", localResponse);
			}
			return localResponse;
		}

		@Override
		public void run() {
			try
			{	

				byte[] buff = new byte[512];
				int ret_read = 0;

				do
				{
					buff = new byte[512];
					
					response = in.readLine();
					
					synchronized (MUTEX) {
						MUTEX.notifyAll();
					}
										

				}
				while (true);
				
			}
			catch (IOException e) {
				aLog.error("Error reading message", e);
			}

		}								

	}


	private class ConnectionThread extends Thread {

		private boolean isConnected = false;
		private boolean isRunning = true;

		private ConnectionThread() {		
		}

		public boolean isConnected() {
			return isConnected;
		}

		@Override
		public void run() {
			while(!isConnected && isRunning) {


				try {
					isConnected = establishConnection();
				} catch (SocketException e) {
					aLog.error("Error connecting to Pioner Receiver" ,e);
				} catch (IOException e) {
					aLog.error("Error connecting to Pioner Receiver" ,e);
				} 

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					//ignore
				}

			}

			//notify receiverconnectionlisteners
			for (ReceiverConnectionListener listener : aReceiverConnectionListenerList) {
				listener.connected();
			}


			//Here we are connected, start Start Receiver
			String cmdRsp = telnetCommandHandler.sendCommand("\n", false);
			if (aLog.isDebugEnabled()) {
				aLog.debug("Send command finished");
			}

		}

		private boolean establishConnection() throws SocketException, IOException {


			if (aLog.isDebugEnabled()) {
				aLog.debug("Trying Connecting to IP {} on PORT {}", RECEIVER_IP, RECEIVER_PORT);
			}

			client = new Socket(RECEIVER_IP, RECEIVER_PORT);

			InputStream is = client.getInputStream();
			TelnetInputReader telnetInputReader = new TelnetInputReader(is);
			telnetInputReader.start();

			telnetCommandHandler = new TelnetCommandHandler(client.getOutputStream(), telnetInputReader);


			if (aLog.isDebugEnabled()) {
				aLog.debug("Successfuly Connected to IP {} on PORT {}", RECEIVER_IP, RECEIVER_PORT);
			}



			client.setTcpNoDelay(true);


			return true;

		}


	}

	public static void main(String[] args) {
		//new TelnetManager().startTuner();

	}

}
