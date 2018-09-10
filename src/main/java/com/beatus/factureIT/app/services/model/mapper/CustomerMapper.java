package com.beatus.factureIT.app.services.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.beatus.factureIT.app.services.model.Customer;
import com.beatus.factureIT.app.services.utils.Constants;

public class CustomerMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet result, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setId(result.getString("customerId"));
		customer.setCompanyName(result.getString("customerCompanyName"));
		customer.setCompanyType(result.getString("customerCompanyType"));
		customer.setCompanyId(result.getString("customerCompanyId"));
		customer.setUid(result.getString("uid"));
		customer.setFirstname(result.getString("customerFirstName"));
		customer.setLastname(result.getString("customerLastName"));
		customer.setPhone(result.getString("customerPhone"));
		customer.setEmail(result.getString("customerEmail"));
		customer.setAddress(result.getString("customerAddress"));
		customer.setCity(result.getString("customerCity"));
		customer.setState(result.getString("customerState"));
		customer.setZipcode(result.getString("customerZipcode"));
		customer.setUsername(result.getString("username"));
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
		customer.setUserType(userTypes);
		customer.setIsVerified(result.getString("verified"));
		return customer;
	}

}
