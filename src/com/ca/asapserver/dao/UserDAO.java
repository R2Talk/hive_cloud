package com.ca.asapserver.dao;

import java.util.List;

import com.ca.asapserver.vo.UserVo;


/**
 * UserDAO
 *  
 * User Entity DAO interface.
 * 
 * @author Rodrigo Carvalho.
 *
 */
public interface UserDAO {

	// getUserByName
	//
	public List<UserVo> getUsersByName(String userName);
	
	//public void createUser(UserVo userVo);
	//public void updateUserPwd(Integer userId, String newPwd);
	//public void deleteUser(UserVo userVo);
	
}
