package com.beatus.factureIT.app.services.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.beatus.factureIT.app.services.model.Retailer;
import com.beatus.factureIT.app.services.utils.Constants;

public class RetailerMapper implements RowMapper<Retailer> {

	@Override
	public Retailer mapRow(ResultSet result, int rowNum) throws SQLException {
		Retailer retailer = new Retailer();
		retailer.setId(result.getString("retailerId"));
		retailer.setCompanyName(result.getString("retailerCompanyName"));
		retailer.setCompanyType(result.getString("retailerCompanyType"));
		retailer.setCompanyId(result.getString("retailerCompanyId"));
		retailer.setUid(result.getString("uid"));
		retailer.setFirstname(result.getString("retailerFirstName"));
		retailer.setLastname(result.getString("retailerLastName"));
		retailer.setPhone(result.getString("retailerPhone"));
		retailer.setEmail(result.getString("retailerEmail"));
		retailer.setAddress(result.getString("retailerAddress"));
		retailer.setCity(result.getString("retailerCity"));
		retailer.setState(result.getString("retailerState"));
		retailer.setZipcode(result.getString("retailerZipcode"));
		retailer.setUsername(result.getString("username"));
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
		retailer.setUserType(userTypes);
		retailer.setIsVerified(result.getString("verified"));
		return retailer;
	}

}
