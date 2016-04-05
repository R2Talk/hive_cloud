package com.ca.asapserver.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ca.asapserver.vo.MessageVo;

/**
 * JdbcMessageDAO
 * 
 * JdbcTemplate implementation of Message DAO Interface.
 * 
 * @author Rodrigo Carvalho
 *
 */
public class JdbcMessageDAO implements MessageDAO {

	//JdbcTemplate is a spring object for boiler plate code for connection management.
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * setDataSource
	 * 
	 * Initialize the JdbcTemplate with a DataSource.
	 * 
	 * BE WARE:
	 * 
	 * This DAO is configured as a Spring Bean, and the framework do the initailization following the Dispatcher xml definition
	 * 
	 *  <bean id="dataSource"
 	 *	class="org.apache.commons.dbcp2.BasicDataSource">
	 *	<property name="driverClassName" value="com.mysql.jdbc.Driver"/>
	 *	<property name="url" value="jdbc:mysql://localhost:3306/mydb"/>
	 *	<property name="username" value="root"/>
	 *	<property name="password" value="root"/>
	 *  <//bean>
     *
 	 *  <bean id="messageDAO"
	 *	 class="com.ca.asapserver.dao.JdbcMessageDAO">
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
	public List<MessageVo> getAllMessages(){
		
		String sql = "SELECT MESSAGE.idmessage, MESSAGE.text, MESSAGE.datetime, USER.name, MESSAGE.idfromuser, MESSAGE.user_iduser, MESSAGE.initiative_idinitiative, MESSAGE.deliverable_iddeliverable FROM MESSAGE INNER JOIN USER ON MESSAGE.idfromuser = USER.iduser";
		
		List<MessageVo> messages = this.jdbcTemplate.query(sql, new MessageRowMapper()); 
			
		return messages;
	}
	
	/**
	 * insert a new message into the repository (database)
	 * 
	 * Reads end return all messages in the repository (database)
	 * 
	 */
	public void insertMessage(MessageVo messageVo){
		
		//Get current date to message time stamp
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String stringDate = dateFormat.format(date); 
		 
		System.out.println("message date = " + stringDate);
		
		//prepare SQL for inserting message. Be ware that the primary key must be auto increment and is not passed to in the sql statement.
		String sql = "INSERT INTO MESSAGE (text, idfromuser, datetime, USER_iduser, INITIATIVE_idinitiative, DELIVERABLE_iddeliverable) VALUES (?, ?, ?, ?, ?, ?)";
		
		//insert message using jdbcTemplate 
		this.jdbcTemplate.update(sql, messageVo.getText(), "2", stringDate, "1", "1", "1");
		
		System.out.println("Created new Message = " + messageVo.getText());
		
		return;
		
	}
}
