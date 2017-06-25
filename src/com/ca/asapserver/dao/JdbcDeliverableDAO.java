package com.ca.asapserver.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.ca.asapserver.vo.DeliverableVo;
import com.ca.asapserver.vo.InitiativeVo;

/**
 * JdbcDeliverableDAO
 * 
 * JdbcTemplate implementation of Deliverable DAO Interface.
 * 
 * @author Rodrigo Carvalho.
 *
 */
public class JdbcDeliverableDAO implements DeliverableDAO {

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
	 * getDeliverablesByInitiative
	 * 
	 * Reads end return all deliverables associated with the initiative in the repository (database)
	 * 
	 */
	public List<DeliverableVo> getDeliverablesByInitiative(InitiativeVo initiativeVo){
		
		String sql = "SELECT * FROM DELIVERABLE WHERE initiative_idinitiative = " + initiativeVo.getInitiativeId() + " AND status = 'OPEN'";
		
		//String sql = "SELECT DELIVERABLE.iddeliverable, ..., USER.name FROM DELIVERABLE INNER JOIN USER ON DELIVERABLE.idresponsibleuser = USER.iduser"
		
		List<DeliverableVo> deliverables = this.jdbcTemplate.query(sql, new DeliverableRowMapper()); 
			
		return deliverables;
	}
	
	/**
	 * getPrioritizedDeliverables
	 * 
	 * Reads end return all prioritized deliverables in the repository (database)
	 * 
	 */
	public List<DeliverableVo> getPrioritizedDeliverables(){
			
		String sql = "SELECT * FROM DELIVERABLE WHERE isPriority = 'YES'  AND status = 'OPEN'";
		
		List<DeliverableVo> deliverables = this.jdbcTemplate.query(sql, new DeliverableRowMapper()); 
			
		return deliverables;
	}
	
	/**
	 * createDeliverable
	 * 
	 * Create deliverable
	 * 
	 * @return
	 */
	public DeliverableVo createDeliverable(DeliverableVo deliverableVo) {  
				 
		//prepare SQL. Be ware that the primary key must be auto increment and is not passed to in the sql statement.
		final String sql = "INSERT INTO DELIVERABLE (title, description, duedate, rating, status, idresponsibleuser, "
				+ "initiative_idinitiative, isPriority, deliverableValue, currentUserName) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		final String stmtTitle = deliverableVo.getTitle();
		final String stmtDescription = deliverableVo.getDescription();
		final String stmtDueDate = deliverableVo.getDuedate();
		final String stmtRating = deliverableVo.getRating();
		final String stmtStatus = deliverableVo.getStatus();
		final String stmtIdResponsibleUser = deliverableVo.getIdresponsibleuser();
		final String stmtIdInitiative = deliverableVo.getIdinitiative();
		final String stmtIsPriority = deliverableVo.getIsPriority();
		final String stmtDeliverableValue = deliverableVo.getDeliverableValue();
		final String stmtCurrentUserName = deliverableVo.getCurrentusername();
		
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
	    	            pst.setString(3, stmtDueDate);
	    	            pst.setInt(4, Integer.parseInt(stmtRating));
	    	            pst.setString(5, stmtStatus);
	    	            pst.setInt(6, Integer.parseInt(stmtIdResponsibleUser));
	    	            pst.setInt(7, Integer.parseInt(stmtIdInitiative));
	    	            pst.setString(8, stmtIsPriority);
	    	            pst.setInt(9, Integer.parseInt(stmtDeliverableValue));
	    	            pst.setString(10, stmtCurrentUserName);
	    	            
	    	            return pst;
				}
    	    },
    	    keyHolder);
    	
    	Long autoincrementedId = (Long)keyHolder.getKey();
		
		deliverableVo.setIddeliverable(autoincrementedId.toString());
		
		return deliverableVo;		
	}
	
	/**
	 * finishDeliverable
	 * 
	 * @param deliverableId
	 * @return
	 */
	public void finishDeliverable(int deliverableId){ //TODO: need re-factoring to throw exception
		
		//Delete deliverable
		String deleteDeliverableSql = "UPDATE DELIVERABLE SET  status ='FINISHED' WHERE iddeliverable = ?";
		this.jdbcTemplate.update(deleteDeliverableSql, new Object[] { deliverableId });
		
		return;
	}
	
	/**
	 * deleteDeliverable
	 * 
	 * @param deliverableId
	 * @return
	 */
	public void deleteDeliverable(int deliverableId){ //TODO: need re-factoring to throw exception
		
		//Delete deliverable
		String deleteDeliverableSql = "DELETE FROM DELIVERABLE WHERE iddeliverable = ?";
		this.jdbcTemplate.update(deleteDeliverableSql, new Object[] { deliverableId });
		
		return;
	}
	
	/**
	 * setPriority
	 * 
	 * @param deliverableVo
	 */
	public void setPriority(DeliverableVo deliverableVo){
		
		//TODO: check data model type in filed prioritiyComment
		String sql = "UPDATE DELIVERABLE SET isPriority='YES', priorityComment='" + deliverableVo.getPriorityComment() + "', prioritizedBy='" + deliverableVo.getPrioritizedBy() + "' WHERE iddeliverable=" + deliverableVo.getIddeliverable();
		this.jdbcTemplate.update(sql);
				
		return;
			
	}
	
	/**
	 * resetPriority
	 * 
	 * @param deliverableVo
	 */
	public void resetPriority(DeliverableVo deliverableVo){
		
		//TODO: check data model type in filed prioritiyComment
		String sql = "UPDATE DELIVERABLE SET isPriority='NO', priorityComment='', prioritizedBy=NULL WHERE iddeliverable=" + deliverableVo.getIddeliverable();
		this.jdbcTemplate.update(sql);
				
		return;
	}
	
	/**
	 * updateDeliverable
	 * 
	 * @param deliverableVo
	 */
	public void updateDeliverable(DeliverableVo deliverableVo){
		
		String sql = "UPDATE DELIVERABLE SET title='" + deliverableVo.getTitle() + "', description='" + deliverableVo.getDescription() + "', duedate='" + deliverableVo.getDuedate() + "' WHERE iddeliverable=" + deliverableVo.getIddeliverable();
		this.jdbcTemplate.update(sql);
				
		return;
			
	}
	
}
