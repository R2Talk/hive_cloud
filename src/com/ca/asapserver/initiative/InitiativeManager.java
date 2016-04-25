package com.ca.asapserver.initiative;

import java.util.List;

import com.ca.asapserver.dao.InitiativeDAO;
import com.ca.asapserver.springutils.AppContextHelper;
import com.ca.asapserver.vo.InitiativeVo;

/**
 * InitiativeManager
 * 
 * Functions to create, read, update and delete deliverables.
 *  
 * @author Rodrigo Carvalho
 *
 */
public class InitiativeManager {

	public List<InitiativeVo> getInitiatives(){
		InitiativeDAO initiativeDAO = (InitiativeDAO) AppContextHelper.getApplicationContext().getBean("initiativeDAO");
		
		return initiativeDAO.getInitiatives();
		
	}
	
}
