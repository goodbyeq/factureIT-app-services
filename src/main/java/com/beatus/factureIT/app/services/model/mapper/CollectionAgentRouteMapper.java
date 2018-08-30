package com.beatus.factureIT.app.services.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.beatus.factureIT.app.services.model.CollectionAgentRoute;

public class CollectionAgentRouteMapper implements RowMapper<CollectionAgentRoute> {

	@Override
	public CollectionAgentRoute mapRow(ResultSet rs, int rowNum) throws SQLException {
		CollectionAgentRoute route = new CollectionAgentRoute();
		route.setCollectionAgentRouteId(rs.getString("collection_agent_route_id"));
		route.setCollectionAgentId(rs.getString("collection_agent_id"));
		route.setSourceUid(rs.getString("source_uid"));
		route.setDestUid(rs.getString("dest_uid"));
		route.setAmountCollected(rs.getString("amount_collected"));
		route.setAmountToBeCollected(rs.getString("amount_need_to_be_collected"));
		route.setIsCollectionAgentUpdatedTheAmount(rs.getString("is_updated_by_collection_agent"));
		route.setIsAmountVerifiedBySourceUser(rs.getString("is_verified_by_source_user"));
		route.setIsAmountVerifiedByDestUser(rs.getString("is_verified_by_dest_user"));
		return null;
	}

	

}
