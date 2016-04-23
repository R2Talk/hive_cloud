package com.ca.asapserver.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ca.asapserver.vo.MessageVo;
import com.ca.asapserver.vo.UserVo;

/**
 * JdbcUserDAO
 * 
 * JdbcTemplate implementation of User DAO Interface.
 * 
 * @author Rodrigo Carvalho.
 *
 */
public class JdbcUserDAO implements UserDAO {

	//JdbcTemplate is a spring object for boiler plate code for connection management.
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * setDataSource
	 * 
	 * Initialize the JdbcTemplate with a DataSource.
	 * 
	 * BEWARE:This DAO is configured as a Spring Bean, and the framework do the initialization following the Dispatcher xml definition
	 * 
	 *  <bean id="dataSource"
 	 *	class="org.apache.commons.dbcp2.BasicDataSource">
	 *	<property name="driverClassName" value="com.mysql.jdbc.Driver"/>
	 *	<property name="url" value="jdbc:mysql://localhost:3306/mydb"/>
	 *	<property name="username" value="root"/>
	 *	<property name="password" value="root"/>
	 *  <//bean>
     *
 	 *  <bean id="userDAO"
	 *	 class="com.ca.asapserver.dao.JdbcUserDAO">
	 *	 <property name="dataSource" ref="dataSource"/>
	 *  <//bean>
	 * 
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 * getAllMessages
	 * 
	 * Reads end return all messages in the repository (database)
	 * 
	 */
	public List<UserVo> getUsersByName(String userName){
		
		String sql = "SELECT iduser, name, password, type FROM USER where name = '" + userName + "'";
		
		List<UserVo> users = this.jdbcTemplate.query(sql, new UserRowMapper()); 
			
		return users;
	}
	
}
