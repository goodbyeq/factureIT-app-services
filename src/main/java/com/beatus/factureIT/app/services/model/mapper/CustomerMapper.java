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
		List<String> userType = new ArrayList<String>();
		userType.add(Constants.CUSTOMER_TYPE);
		customer.setUserType(userType);
		return customer;
	}

}
