package com.ca.asapserver.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ca.asapserver.vo.DeliverableVo;
import com.ca.asapserver.vo.InitiativeVo;

/**
 * MessageRowMapper
 * 
 * Implements RowMapper for use with Spring JdbcTemplate methods.
 * 
 * @author Rodrigo Carvalho.
 *
 */
public class InitiativeRowMapper implements RowMapper<InitiativeVo> {

	public InitiativeVo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		InitiativeVo initiativeVo = new InitiativeVo();
		
		initiativeVo.setInitiativeId((new Integer((rs.getInt("idinitiative"))).toString()));
		initiativeVo.setInitiativeTitle(rs.getString("title"));
		initiativeVo.setInitiativeDescription(rs.getString("description"));
		
		return initiativeVo;
	}
	
}
