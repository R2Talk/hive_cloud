package com.ca.asapserver.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ca.asapserver.vo.DeliverableVo;

/**
 * MessageRowMapper
 * 
 * Implements RowMapper for use with Spring JdbcTemplate methods.
 * 
 * @author Rodrigo Carvalho.
 *
 */
public class DeliverableRowMapper implements RowMapper<DeliverableVo> {

	public DeliverableVo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		DeliverableVo deliverableVo = new DeliverableVo();
		
		deliverableVo.setComments(rs.getString("comments"));
		deliverableVo.setDeliverable_isLate("");
		deliverableVo.setDeliverableValue((new Integer((rs.getInt("deliverableValue"))).toString()));
		deliverableVo.setDescription(rs.getString("description"));
		deliverableVo.setDuedate((rs.getDate("duedate")).toString());
		deliverableVo.setIddeliverable((new Integer((rs.getInt("iddeliverable"))).toString()));
		deliverableVo.setIdinitiative((new Integer((rs.getInt("initiative_idinitiative"))).toString()));
		deliverableVo.setIdresponsibleuser((new Integer((rs.getInt("idresponsibleuser"))).toString()));
		deliverableVo.setIsPriority(rs.getString("isPriority"));
		deliverableVo.setPrioritizedBy((new Integer((rs.getInt("prioritizedBy"))).toString()));
		deliverableVo.setPriorityComment(rs.getString("prioritiyComment")); //TODO? check spell in the database column
		deliverableVo.setRating((new Integer((rs.getInt("rating"))).toString()));
		deliverableVo.setStatus(rs.getString("status"));
		deliverableVo.setTitle(rs.getString("title"));
		
		return deliverableVo;
	}
	
}
