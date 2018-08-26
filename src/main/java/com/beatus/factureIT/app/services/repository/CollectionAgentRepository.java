package com.beatus.factureIT.app.services.repository;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.beatus.factureIT.app.services.model.CollectionAgent;
import com.beatus.factureIT.app.services.utils.Constants;
import com.beatus.factureIT.app.services.utils.Utils;

@Repository("collectionAgentRepository")
public class CollectionAgentRepository {
	
	JdbcTemplate jdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(CollectionAgentRepository.class);

	@Autowired
	@Qualifier(value = "driverManagerDataSource")
	private DataSource dataSource;

	@Autowired
	public CollectionAgentRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public String addCollectionAgent(CollectionAgent agent, String uid){
		try {
			LOGGER.info("In addCollectionAgent");
			String sql = "INSERT INTO collection_agent (collection_agent_id, collection_agent_uid, user_type_uid) VALUES (?, ?, ?)";
			int rowsInserted = jdbcTemplate.update(sql, Utils.generateRandomKey(50), agent.getUid(), uid);

			if (rowsInserted > 0) {
				LOGGER.info("A new collection_agent was inserted successfully!");
				return Constants.USER_CREATED;
			} else {
				return Constants.ERROR_USER_CREATION;
			}
		} finally {
			
		}
	}
	
	//TO-DO add list of destIds
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public String addRouteForCollectionAgent(String collectionAgentId, String sourceUId, String destUId, String amountNeedTobeCollected, String amountCollected, String isValidatedBySourceUser, String isValidatedByDestUser){
		try {
			LOGGER.info("In addRouteForCollectionAgent");
			String sql = "INSERT INTO collection_agent_route (collection_agent_route_id, collection_agent_id, source_user_type_id,"
					+ " dest_user_type_id, amount_need_to_be_collected, amount_collected, is_verified_by_source_user, is_verified_by_dest_user)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			int rowsInserted = jdbcTemplate.update(sql, Utils.generateRandomKey(50), collectionAgentId, sourceUId, destUId,
					amountNeedTobeCollected, amountCollected, isValidatedBySourceUser, isValidatedByDestUser);

			if (rowsInserted > 0) {
				LOGGER.info("A new collection_agent was inserted successfully!");
				return Constants.USER_CREATED;
			} else {
				return Constants.ERROR_USER_CREATION;
			}
		} finally {
			
		}
	}
	
}
