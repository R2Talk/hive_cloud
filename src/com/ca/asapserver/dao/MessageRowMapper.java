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
 * @author Rodrigo Carvalho.
 *
 */
public class MessageRowMapper implements RowMapper<MessageVo> {

	public MessageVo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		MessageVo messageVo = new MessageVo();
		
		messageVo.setIdMessage(rs.getInt("idmessage"));
		messageVo.setText(rs.getString("text"));
		messageVo.setDatetime(rs.getDate("datetime"));
		messageVo.setNameFromUser(rs.getString("name"));
		messageVo.setIdFromUser(rs.getInt("idfromuser"));
		messageVo.setUser_idUser(rs.getInt("user_iduser"));
		messageVo.setInitiative_idInitiative(rs.getInt("initiative_idinitiative"));
		messageVo.setDeliverable_idDeliverable(rs.getInt("deliverable_iddeliverable"));
		
		return messageVo;
	}
	
}
