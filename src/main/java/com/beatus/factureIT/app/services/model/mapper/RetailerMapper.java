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
		retailer.setAddress(result.getString("retailerPhone"));
		retailer.setCity(result.getString("retailerAddress"));
		retailer.setState(result.getString("retailerCity"));
		retailer.setZipcode(result.getString("retailerState"));
		List<String> userType = new ArrayList<String>();
		userType.add(Constants.RETAILER_TYPE);
		retailer.setUserType(userType);
		return retailer;
	}

}
