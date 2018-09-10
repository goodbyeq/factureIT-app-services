package com.beatus.factureIT.app.services.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.beatus.factureIT.app.services.model.Distributor;
import com.beatus.factureIT.app.services.utils.Constants;

public class DistributorMapper implements RowMapper<Distributor> {

	@Override
	public Distributor mapRow(ResultSet result, int rowNum) throws SQLException {
		Distributor distributor = new Distributor();
		distributor.setId(result.getString("distributorId"));
		distributor.setCompanyName(result.getString("distributorCompanyName"));
		distributor.setCompanyType(result.getString("distributorCompanyType"));
		distributor.setCompanyId(result.getString("distributorCompanyId"));
		distributor.setUid(result.getString("uid"));
		distributor.setFirstname(result.getString("distributorFirstName"));
		distributor.setLastname(result.getString("distributorLastName"));
		distributor.setPhone(result.getString("distributorPhone"));
		distributor.setEmail(result.getString("distributorEmail"));
		distributor.setAddress(result.getString("distributorAddress"));
		distributor.setCity(result.getString("distributorCity"));
		distributor.setState(result.getString("distributorState"));
		distributor.setZipcode(result.getString("distributorZipcode"));
		distributor.setUsername(result.getString("username"));
		String userType = result.getString("user_type");
		List<String> userTypes = new ArrayList<String>();
		if(userType.contains(Constants.DISTRIBUTOR_TYPE)){
			userTypes.add(Constants.DISTRIBUTOR_TYPE);
		}
		if(userType.contains(Constants.RETAILER_TYPE)){
			userTypes.add(Constants.RETAILER_TYPE);
		}
		if(userType.contains(Constants.MANUFACTURER_TYPE)){
			userTypes.add(Constants.MANUFACTURER_TYPE);
		}
		if(userType.contains(Constants.CUSTOMER_TYPE)){
			userTypes.add(Constants.CUSTOMER_TYPE);
		}
		if(userType.contains(Constants.COLLECTION_AGENT_TYPE)){
			userTypes.add(Constants.COLLECTION_AGENT_TYPE);
		}
		distributor.setUserType(userTypes);
		distributor.setIsVerified(result.getString("verified"));
		return distributor;
	}

}
