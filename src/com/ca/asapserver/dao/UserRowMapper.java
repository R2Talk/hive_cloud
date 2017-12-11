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
 * BEWARE: used in other DAO than JdbcUserDAO (ex: JdbcInitiaticeDAO)
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
		userVo.setEmail(rs.getString("email"));
		userVo.setValidated(true);
		
		return userVo;
	}
	
}
