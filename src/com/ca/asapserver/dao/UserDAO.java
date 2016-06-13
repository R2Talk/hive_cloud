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

	/**
	 * getUsersByName
	 * 
	 * @param userName
	 * @return
	 */
	public List<UserVo> getUsersByName(String userName);
	
	/**
	 * getUsersByEmail
	 * 
	 * @param userEmail
	 * @return
	 */
	public List<UserVo> getUsersByEmail(String userEmail);
	
	/**
	 * createUser
	 * 
	 * @param userVo
	 */
	public UserVo createUser(String name, String email, String password) throws UserAlreadyExistsException;
	
	/**
	 * removeUserDeliverableAssociation
	 * 
	 * @param deliverableId
	 */
	public void removeUserDeliverableAssociation(int deliverableId);
	
	/**
	 * getUserById
	 * 
	 * @return
	 */
	public UserVo getUserById(int userId);
	
	//TODO: public void updateUserPwd(Integer userd, String newPwd);
	//TODO: public void deleteUser(UserVo userVo);
		
}
