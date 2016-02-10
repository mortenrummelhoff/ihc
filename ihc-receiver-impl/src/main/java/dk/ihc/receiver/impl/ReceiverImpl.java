package dk.ihc.receiver.impl;

import org.apache.log4j.Logger;

import dk.ihc.receiver.TelnetManager;
import dk.ihc.receiver.service.ReceiverService;

public class ReceiverImpl implements ReceiverService {

	private TelnetManager telnetManager;
	
	private Logger aLog = Logger.getLogger(getClass());
	
	
	public ReceiverImpl(TelnetManager manager) {
		telnetManager = manager;
	}

	
	
	@Override
	public String getValue(String resource) {
		
		return telnetManager.getValue(resource);
	}



	@Override
	public void setPower(boolean set) {
		aLog.debug("setPower called: {}", set);
		telnetManager.setPower(set);
	}

	@Override
	public void setReceiverMode(String mode) {
		aLog.debug("setReceiverMode called: {}", mode);
		telnetManager.setMode(mode);
	}

	@Override
	public void setTunerChannel(int channel) {
		aLog.debug("setTunerChannel called: {}", channel);
		telnetManager.setTunerChannel(channel);
	}

	@Override
	public String setVolumen(int vol) {
		aLog.debug("setVolumen called: {}", vol);
		return telnetManager.setVolumen(vol);
	}
	
}
