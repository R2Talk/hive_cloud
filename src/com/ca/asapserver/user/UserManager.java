package com.ca.asapserver.user;

import java.util.Iterator;
import java.util.List;

import com.ca.asapserver.dao.MessageDAO;
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

	//validateUser
	//
	// Checks if there is an user with the same name and password. Returns UserVo with validate flag true or false.
	//
	public UserVo validateUser(String name, String password){
		List<UserVo> users = null;
		UserVo userVo = null;
		
		UserDAO userDAO = (UserDAO) AppContextHelper.getApplicationContext().getBean("userDAO");
		
		users = userDAO.getUsersByName(name);
		
		Iterator<UserVo> usersIterator = users.iterator();
		while (usersIterator.hasNext()) {
			userVo = usersIterator.next();
		}
		
		if (userVo == null){
			
			userVo = new UserVo(0, "", "", false);
			
		} else if (userVo.getPassword().equals(password)){
			
			userVo.setPassword("");
			userVo.setValidated(true);
			
		} else {
			
			userVo.setPassword("userVo pwd = " + userVo.getPassword() + " password = " + password + new Boolean(userVo.getPassword().equals(password)));
			userVo.setValidated(false);
		}
		
		return userVo;
	} 
	
}
