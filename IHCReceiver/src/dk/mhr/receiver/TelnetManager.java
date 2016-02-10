package dk.mhr.receiver;

import java.awt.event.KeyEvent;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.commons.net.ProtocolCommandEvent;
import org.apache.commons.net.ProtocolCommandListener;
import org.apache.commons.net.telnet.EchoOptionHandler;
import org.apache.commons.net.telnet.InvalidTelnetOptionException;
import org.apache.commons.net.telnet.SuppressGAOptionHandler;
import org.apache.commons.net.telnet.TelnetClient;
import org.apache.commons.net.telnet.TelnetInputListener;
import org.apache.commons.net.telnet.TelnetNotificationHandler;
import org.apache.commons.net.telnet.TerminalTypeOptionHandler;

import sun.net.TelnetProtocolException;

public class TelnetManager {

	
	private TelnetClient client;
	
	private final static String RECEIVER_IP = "192.168.0.45";
	private final static int RECEIVER_PORT = 23;	
	
	private TelnetOutputStream telnetOutputStream;
	
	public TelnetManager() {
		System.out.println("Starting telnet manager");
		
		//openTelnetConnection();
	}
	
	public void startTuner() {
		openTelnetConnection();
		
		telnetOutputStream.sendCommand("po");
	}
	
	private void openTelnetConnection() {
		
		client = new TelnetClient();		
		TerminalTypeOptionHandler ttopt = new TerminalTypeOptionHandler("VT100", false, false, true, false);
		EchoOptionHandler echoopt = new EchoOptionHandler(true, false, true, false);
		SuppressGAOptionHandler gaopt = new SuppressGAOptionHandler(true, true, true, true);

        try  {
        	client.addOptionHandler(ttopt);
            client.addOptionHandler(echoopt);
            client.addOptionHandler(gaopt);
            establishConnection();
            
           client.setTcpNoDelay(true);
           client.registerNotifHandler(new TelnetNotificationHandler() {
			
			@Override
			public void receivedNegotiation(int arg0, int arg1) {
				System.out.println("receivedNegotiation: " + arg0 + ", " + arg1);
				
			}
		});
           
            
            Thread.sleep(1000);
            

            new Thread(new Runnable() {

				@Override
				public void run() {
					while(true) {
						telnetOutputStream.sendCommand("?P");
						
						
												
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						//telnetOutputStream.sendCommand("po");
						
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
            	
            }).start();
            
        }        
        catch (InvalidTelnetOptionException e) {
            System.err.println("Error registering option handlers: " + e.getMessage());
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void establishConnection() throws SocketException, IOException {
		
		System.out.println("Connecting");
		client.connect(RECEIVER_IP, RECEIVER_PORT);
		System.out.println("Connected");
		
		client.setReaderThread(true);
		
		client.registerInputListener(new TelnetInputListener() {
			
			@Override
			public void telnetInputAvailable() {
				
			}
		});
		
//		
		
		InputStream is = client.getInputStream();
		new TelnetInputReader(is).start();
		
		telnetOutputStream = new TelnetOutputStream(client.getOutputStream());
		
		
        		
	}
	
	
	private class TelnetOutputStream {
		
		private BufferedWriter outputStream;
		
		public TelnetOutputStream(OutputStream os) {
			
			
			outputStream = new BufferedWriter(new OutputStreamWriter(os));
		}
		
		public void sendCommand(String command) {			
			if (outputStream != null) {
				try {					
					System.out.println("sendCommand called: " + command);
					outputStream.write(command);
					outputStream.newLine();
					outputStream.flush();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	private class TelnetInputReader extends Thread {
		
		private boolean isRunning = true;
		private InputStream inputStream;
		
		public TelnetInputReader(InputStream is) {
			System.out.println("Starting TelnetInputReader");
			this.inputStream = is;
		}

		@Override
		public void run() {
			try
	        {	
	            
				
				byte[] buff = new byte[1024];
	            int ret_read = 0;

	            do
	            {
	            	ret_read = inputStream.read(buff);
	                if(ret_read > 0)
	                {
	                    System.out.print("Received: " + new String(buff, 0, ret_read));
	                }
	            }
	            while (ret_read >= 0);
	        }
	        catch (IOException e)
	        {
	            System.err.println("Exception while reading socket:" + e.getMessage());
	        }
					
		}								
		
	}
	
	public static void main(String[] args) {
		new TelnetManager();
	}
	
}
