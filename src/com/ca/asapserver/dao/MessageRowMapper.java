package com.ca.asapserver.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ca.asapserver.vo.MessageVo;

/**
 * MessageRowMapper
 * 
 * Implements RowMapper for use with Spring JdbcTemplate methods.
 * 
 * @author Rodrigo Carvalho
 *
 */
public class MessageRowMapper implements RowMapper<MessageVo> {

	public MessageVo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		MessageVo messageVo = new MessageVo();
		
		messageVo.setIdMessage(rs.getInt("idMessage"));
		messageVo.setText(rs.getString("text"));
		messageVo.setDate(rs.getDate("date"));
		messageVo.setIdSenderUser(rs.getInt("idSenderUser"));
		messageVo.setUser_idUser(rs.getInt("User_idUser"));
		
		return messageVo;
	}
	
}
