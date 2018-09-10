package com.beatus.factureIT.app.services.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.beatus.factureIT.app.services.model.Manufacturer;
import com.beatus.factureIT.app.services.utils.Constants;

public class ManufacturerMapper implements RowMapper<Manufacturer> {

	@Override
	public Manufacturer mapRow(ResultSet result, int rowNum) throws SQLException {
		
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setId(result.getString("manufacturerId"));
		manufacturer.setCompanyName(result.getString("manufacturerCompanyName"));
		manufacturer.setCompanyType(result.getString("manufacturerCompanyType"));
		manufacturer.setCompanyId(result.getString("manufacturerCompanyId"));
		manufacturer.setUid(result.getString("uid"));
		manufacturer.setFirstname(result.getString("manufacturerFirstName"));
		manufacturer.setLastname(result.getString("manufacturerLastName"));
		manufacturer.setPhone(result.getString("manufacturerPhone"));
		manufacturer.setEmail(result.getString("manufacturerEmail"));
		manufacturer.setAddress(result.getString("manufacturerAddress"));
		manufacturer.setCity(result.getString("manufacturerCity"));
		manufacturer.setState(result.getString("manufacturerState"));
		manufacturer.setZipcode(result.getString("manufacturerZipcode"));
		manufacturer.setUsername(result.getString("username"));
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
		manufacturer.setUserType(userTypes);
		manufacturer.setIsVerified(result.getString("verified"));
		return manufacturer;
	}

}
