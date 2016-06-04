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

	/**
	 * getInitiatives
	 * 
	 * @return
	 */
	public List<InitiativeVo> getInitiatives(){
		InitiativeDAO initiativeDAO = (InitiativeDAO) AppContextHelper.getApplicationContext().getBean("initiativeDAO");
		
		return initiativeDAO.getInitiatives();
		
	}
	
	/**
	 * createInitiative
	 * 
	 * @param initiativeVo
	 * @return
	 */
	public InitiativeVo createInitiative(InitiativeVo initiativeVo, int userId){
				
		InitiativeDAO initiativeDAO = (InitiativeDAO) AppContextHelper.getApplicationContext().getBean("initiativeDAO");
		
		initiativeVo = initiativeDAO.createInitiative(initiativeVo, userId);
		
		initiativeDAO.bindUserToInitiative(initiativeVo, userId); //BE WARE: this is a business rule. For database structure dependencies the code remains in the DAO.
		
		return initiativeVo; 
	}
	
}
