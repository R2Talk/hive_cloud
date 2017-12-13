package com.ca.asapserver.dao;

import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.ca.asapserver.vo.InitiativeVo;
import com.ca.asapserver.vo.UserVo;

/**
 * JdbcInitiativeDAO
 * 
 * JdbcTemplate implementation of Initiative DAO Interface.
 * 
 * @author Rodrigo Carvalho.
 *
 */
public class JdbcInitiativeDAO implements InitiativeDAO {

	//JdbcTemplate is a spring object for boiler plate code for connection management.
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * setDataSource
	 * 
	 * Initialize the JdbcTemplate with a DataSource.
	 * 
	 * BEWARE: This DAO is configured as a Spring Bean, and the framework do the initialization following the Dispatcher xml definition
	 * 
	 *  <bean id="dataSource"
 	 *	class="org.apache.commons.dbcp2.BasicDataSource">
	 *	<property name="driverClassName" value="com.mysql.jdbc.Driver"/>
	 *	<property name="url" value="jdbc:mysql://localhost:3306/mydb"/>
	 *	<property name="username" value="root"/>
	 *	<property name="password" value="root"/>
	 *  <//bean>
     *
 	 *  <bean id="deliverableDAO"
	 *	 class="com.ca.asapserver.dao.JdbcDeliverableDAO">
	 *	 <property name="dataSource" ref="dataSource"/>
	 *  <//bean>
	 * 
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 * createInitiative
	 * 
	 * Create initiative
	 * 
	 * @return
	 */
	public InitiativeVo createInitiative(InitiativeVo initiativeVo, int userId) { 
				 
		//prepare SQL. Be ware that the primary key must be auto increment and is not passed to in the sql statement.
		final String sql = "INSERT INTO INITIATIVE (title, description, idowneruser) VALUES (?, ?, ?)";
		final String stmtTitle = initiativeVo.getInitiativeTitle();
		final String stmtDescription = initiativeVo.getInitiativeDescription();
		final int stmtUserId = userId;
		
		//create initiative and return the newly created initiativeVo with auto increment id identified
		KeyHolder keyHolder = new GeneratedKeyHolder();
    	this.jdbcTemplate.update(
    	    new PreparedStatementCreator() {
				@Override
				public java.sql.PreparedStatement createPreparedStatement(
						java.sql.Connection con) throws SQLException {
					java.sql.PreparedStatement pst =
	    	                (java.sql.PreparedStatement) con.prepareStatement(sql, new String[] {"id"});
	    	            pst.setString(1, stmtTitle);
	    	            pst.setString(2, stmtDescription);
	    	            pst.setInt(3, stmtUserId);
	    	            return pst;
				}
    	    },
    	    keyHolder);
    	
    	Long autoincrementedId = (Long)keyHolder.getKey();
		
		initiativeVo.setInitiativeId(autoincrementedId.toString());
		
		return initiativeVo;		
	}
	
	/**
	 * bindUserToInitiative
	 * 
	 * @param initiativeVo
	 * @param userId
	 */
	public void bindUserToInitiative(InitiativeVo initiativeVo, int userId){
		
		String stmtInitiativeId = initiativeVo.getInitiativeId();
		String stmtUserId = String.valueOf(userId);
		
		//TODO: check if the user is already bounded
		
		//prepare SQL. Be ware that the primary key must be auto increment and is not passed to in the sql statement.
		String sql = "INSERT INTO USER_INITIATIVE (USER_iduser,INITIATIVE_idinitiative) VALUES (?, ?)";
		//insert using jdbcTemplate 
		this.jdbcTemplate.update(sql, Long.valueOf(stmtUserId), Long.valueOf(stmtInitiativeId));
		
		return;
	}
	
	/**
	 * getInitiatives
	 * 
	 * Reads end return all initiatives in the repository (database)
	 * 
	 */
	public List<InitiativeVo> getInitiatives(){
			
		String sql = "SELECT * FROM INITIATIVE";
		
		List<InitiativeVo> initiatives = this.jdbcTemplate.query(sql, new InitiativeRowMapper()); 
			
		return initiatives;
	}
	
	/**
	 * getInitiativesByUserId
	 * 
	 * Returns List with all user initiatives persisted in the database
	 * 
	 * @return
	 */
	public List<InitiativeVo> getInitiativesByUserId(String userId){
		
		String sql = "SELECT INITIATIVE.*, USER_INITIATIVE.* FROM INITIATIVE INNER JOIN USER_INITIATIVE ON INITIATIVE.idinitiative = USER_INITIATIVE.INITIATIVE_idinitiative WHERE USER_INITIATIVE.USER_iduser=" + userId;
		
		List<InitiativeVo> initiatives = this.jdbcTemplate.query(sql, new InitiativeRowMapper()); 
			
		return initiatives;
	}
	
	/**
	 * deleteInitiativeById
	 * 
	 * Delete the identified initiative and all it´s deliverables
	 * 
	 * @return
	 */
	public void deleteInitiativeById(String initiativeId){ //TODO: missing code for exceptions
		
		//BE WARE: The delete initiative operation has the premise that all deliverables were previously removed, this is done by the Initiative manager before calling this method
		//TO CONDIDER: this should be a database rule to ensure that child entities are remove before parent
		
		//delete initiative
		String deleteInitiativeSql = "DELETE FROM INITIATIVE WHERE idinitiative = ?";
		this.jdbcTemplate.update(deleteInitiativeSql, new Object[] { initiativeId });
				
		return;

	}
	
	/**
	 * getInitiativeUsersByInitiativeId
	 * 
	 * @param initiativeId
	 * @return
	 */
	public List<UserVo> getInitiativeUsersByInitiativeId(String initiativeId){
		String sql = "SELECT u.iduser, u.name, u.password, u.email, u.type FROM USER u INNER JOIN USER_INITIATIVE ui ON u.iduser = ui.USER_iduser WHERE ui.INITIATIVE_idinitiative = " + initiativeId;
		List<UserVo> userVoList = this.jdbcTemplate.query(sql, new UserRowMapper()); 
			
		return userVoList;
	}
	
	
	/**
	 * getKnownUsersByUserId
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserVo> getKnownUsersByUserId(String userId){
		String sql = "SELECT usr.iduser, usr.name, usr.password, usr.email, usr.type FROM USER usr INNER JOIN (SELECT DISTINCT ui.USER_iduser FROM USER_INITIATIVE ui INNER JOIN (SELECT INITIATIVE_idinitiative FROM USER_INITIATIVE WHERE USER_iduser = " + userId + ") ui2 ON ui.INITIATIVE_idinitiative = ui2.INITIATIVE_idinitiative) uid ON usr.iduser = uid.USER_iduser;";
		List<UserVo> userVoList = this.jdbcTemplate.query(sql, new UserRowMapper()); 
			
		return userVoList;
	}
	
	/**
	 * addUserToInitiative
	 * 
	 * @param userEmail
	 * @param initiativeId
	 * @return
	 */
	public String addUserToInitiative(String userId, String initiativeId){
		
		//TODO: check if the user is already bounded
		
		//prepare SQL. Be ware that the primary key must be auto increment and is not passed to in the sql statement.
		String sql = "INSERT INTO USER_INITIATIVE (USER_iduser, INITIATIVE_idinitiative) VALUES (?, ?)";
		//insert using jdbcTemplate 
		this.jdbcTemplate.update(sql, Long.valueOf(userId), Long.valueOf(initiativeId));
				
		return "success";
	}
	
	/**
	 * deleteUserFromInitiative
	 * 
	 * @param userId
	 * @param initiativeId
	 * @return
	 */
	public String deleteUserFromInitiative(String userId, String initiativeId){
		
		//Delete deliverable
		String deleteUserFromInitiativeSql = "DELETE FROM USER_INITIATIVE WHERE USER_iduser = ? AND INITIATIVE_idinitiative = ?";
		this.jdbcTemplate.update(deleteUserFromInitiativeSql, Long.valueOf(userId), Long.valueOf(initiativeId));
		
		return "success";
	}

}
	