package com.ca.asapserver.msgmanager;

import java.util.ArrayList;

/**
 * MsgRepository
 * 
 * FOR TEST PURPOSE ONLY
 * 
 * Demo version for message repository. Implemented as a singleton for maintain up to three messages.
 * 
 * @author Rodrigo Carvalho
 *
 */
public class MocMsgRepository {

	private static MocMsgRepository msgRepository = new MocMsgRepository();
	private static ArrayList<String> msgCollection = new ArrayList<String>(3); 
	
	private MocMsgRepository(){}
	
	/* Static 'instance' method */
	public static MocMsgRepository getInstance( ) {
		return msgRepository;
	}
	
	/**
	 * 
	 * @param msg
	 */
	protected void insertMessage(String msg) {
		if (msgCollection.size()  > 0){msgCollection.remove(0);}
		msgCollection.add(0, msg);
	}
	
	/**
	 * 
	 * @param msg
	 */
	protected ArrayList<String> getMessages() {
		return msgCollection;
	}
	
}
