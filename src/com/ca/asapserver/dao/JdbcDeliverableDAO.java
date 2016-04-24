package com.ca.asapserver.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ca.asapserver.vo.DeliverableVo;

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
	 * getPrioritizedDeliverables
	 * 
	 * Reads end return all prioritized deliverables in the repository (database)
	 * 
	 */
	public List<DeliverableVo> getPrioritizedDeliverables(){
			
		String sql = "SELECT * FROM DELIVERABLE WHERE isPriority = 'YES'";
		
		List<DeliverableVo> deliverables = this.jdbcTemplate.query(sql, new DeliverableRowMapper()); 
			
		return deliverables;
	}
	
	
}
