package com.ca.asapserver.user;

import java.util.Iterator;
import java.util.List;

import com.ca.asapserver.dao.MessageDAO;
import com.ca.asapserver.dao.UserAlreadyExistsException;
import com.ca.asapserver.dao.UserDAO;
import com.ca.asapserver.springutils.AppContextHelper;
import com.ca.asapserver.vo.UserVo;

/**
 * UserManager
 * 
 * Create, delete, update and get user state.
 * 
 * @author Rodrigo Carvalho.
 *
 */
public class UserManager {

	/**
	 * validateUser
	 * 
	 * Checks if there is an user with the same name and password. Returns UserVo with validate flag true or false.
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	public UserVo validateUser(String email, String password){
		List<UserVo> users = null;
		UserVo userVo = null;
		
		UserDAO userDAO = (UserDAO) AppContextHelper.getApplicationContext().getBean("userDAO");
		
		users = userDAO.getUsersByEmail(email);
		
		Iterator<UserVo> usersIterator = users.iterator();
		while (usersIterator.hasNext()) {
			userVo = usersIterator.next();
		}
		
		if (userVo == null){
			
			userVo = new UserVo(0, "", "", "", false);
			
		} else if (userVo.getPassword().equals(password)){
			
			userVo.setPassword("");
			userVo.setValidated(true);
			
		} else {
			
			userVo.setPassword("");
			userVo.setValidated(false);
		}
		
		return userVo;
	} 
	
	
	/**
	 * createUser
	 * 
	 * @param name
	 * @param email
	 * @param password
	 * @return
	 */
	public UserVo createUser(String name, String email, String password)  throws NotUniqueEmailException {
		
		UserVo userVo = null;
		
		try {
			UserDAO userDAO = (UserDAO) AppContextHelper.getApplicationContext().getBean("userDAO");
		
			userVo = userDAO.createUser(name, email, password);
			
			//TODO: call function to check if there are invites pending for the user email
			
			return userVo;
			
		} catch(UserAlreadyExistsException e){
			throw new NotUniqueEmailException();
		}
	}
	
	/**
	 * removeUserDeliverableAssociation
	 * 
	 * @param deliverableId
	 * @return
	 */
	public void removeUserDeliverableAssociation(int deliverableId)  { //TODO: Need refactoring to throw exceptions
		
		UserDAO userDAO = (UserDAO) AppContextHelper.getApplicationContext().getBean("userDAO");
		
		userDAO.removeUserDeliverableAssociation(deliverableId);
		
		return;
	}
}
