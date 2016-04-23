package com.ca.asapserver.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ca.asapserver.vo.MessageVo;
import com.ca.asapserver.vo.UserVo;

/**
 * MessageRowMapper
 * 
 * Implements RowMapper for use with Spring JdbcTemplate methods.
 * 
 * @author Rodrigo Carvalho.
 *
 */
public class UserRowMapper implements RowMapper<UserVo> {

	public UserVo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		UserVo userVo = new UserVo();
		
		userVo.setUserId(rs.getInt("iduser"));
		userVo.setName (rs.getString("name"));
		userVo.setPassword(rs.getString("password"));
		userVo.setValidated(true);
		
		return userVo;
	}
	
}
