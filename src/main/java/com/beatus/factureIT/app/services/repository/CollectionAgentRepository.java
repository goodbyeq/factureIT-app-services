package com.beatus.factureIT.app.services.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.beatus.factureIT.app.services.model.CollectionAgent;
import com.beatus.factureIT.app.services.model.CollectionAgentRoute;
import com.beatus.factureIT.app.services.model.mapper.CollectionAgentRouteMapper;
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
	public String addCollectionAgent(CollectionAgent agent, String uid)  throws ClassNotFoundException, SQLException{
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
	public String addRouteForCollectionAgent(String collectionAgentId, String sourceUId, List<String> destUids, List<String> amountsNeedTobeCollected)  throws ClassNotFoundException, SQLException{
		try {
			LOGGER.info("In addRouteForCollectionAgent");
			String sql = "INSERT INTO collection_agent_route (collection_agent_route_id, collection_agent_id, source_uid,"
					+ " dest_uid, amount_need_to_be_collected, amount_collected, is_updated_by_collection_agent, is_verified_by_source_user, is_verified_by_dest_user)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			int[] rowsInserted = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setString(1, Utils.generateRandomKey(50));
					ps.setString(2, collectionAgentId);
					ps.setString(3, sourceUId);
					ps.setString(4, destUids.get(i));
					ps.setString(5, amountsNeedTobeCollected.get(i));
					ps.setString(6, Constants.ZERO_DOUBLE_AMOUNT);
					ps.setString(7, Constants.NO);
					ps.setString(8, Constants.NO);
					ps.setString(9, Constants.NO);
				}

				@Override
				public int getBatchSize() {
					return destUids.size();
				}
			});
			
			if (rowsInserted.length > 0) {
				LOGGER.info("Collection_agent rutes were inserted successfully!");
				return Constants.COLLECTION_AGENT_ROUTES_CREATED_OR_UPDATED;
			} else {
				return Constants.ERROR_CREATING_OR_UPDATING_COLLECTION_AGENT_ROUTES;
			}
		} finally {
			
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Throwable.class)
	public List<CollectionAgentRoute> getCollectionAgentRoutesNotUpdated(String collectionAgentId)  throws ClassNotFoundException, SQLException {
		LOGGER.info("In getCollectionAgentRoutesNotUpdated " + collectionAgentId);
		String sql = "SELECT * FROM collection_agent_route WHERE collectionAgentId = ? AND is_updated_by_collection_agent = ?";
		List<CollectionAgentRoute> collectionAgentRoutes = jdbcTemplate.query(sql, new Object[] { collectionAgentId, Constants.NO }, new CollectionAgentRouteMapper());
		LOGGER.info("The user returned " + collectionAgentRoutes != null ? collectionAgentRoutes.toString() : null);
		return collectionAgentRoutes != null ? collectionAgentRoutes.size() > 0 ? collectionAgentRoutes : null : null;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public String updateCollectionAgentRoute(CollectionAgentRoute route)  throws ClassNotFoundException, SQLException {
		if (route != null && route.getSourceUid() != null) {
			try {
				LOGGER.info("In updateCollectionAgentRoute");
				String sql = "UPDATE collection_agent_route SET amount_collected = ?, is_updated_by_collection_agent = ?, is_verified_by_source_user = ?, is_verified_by_dest_user = ? WHERE collection_agent_route_id = ?";
				int rowsUpdated = jdbcTemplate.update(sql, route.getAmountCollected(), route.getIsCollectionAgentUpdatedTheAmount(),
						route.getIsAmountVerifiedBySourceUser(), route.getIsAmountVerifiedByDestUser(), route.getCollectionAgentRouteId());

				if (rowsUpdated > 0) {
					LOGGER.info("A Collection agent route info was updated successfully!");
					return Constants.COLLECTION_AGENT_ROUTES_CREATED_OR_UPDATED;
				} else {
					return Constants.ERROR_CREATING_OR_UPDATING_COLLECTION_AGENT_ROUTES;
				}
			} finally {

			}
		}
		return Constants.ERROR_USER_DEVICE_INFO_CREATION;
	}
	
}
