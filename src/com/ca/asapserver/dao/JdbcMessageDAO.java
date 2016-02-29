package com.ca.asapserver.dao;

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

	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<MessageVo> getAllMessages(){
		String sql = "SELECT * FROM MESSAGE";
		List<MessageVo> messages = this.jdbcTemplate.query(sql, new MessageRowMapper()); 
			
		return messages;
	}
}
