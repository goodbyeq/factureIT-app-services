package com.beatus.factureIT.app.services.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.beatus.factureIT.app.services.model.User;
import com.beatus.factureIT.app.services.utils.Constants;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet result, int rowNum) throws SQLException {
		User user = new User();
		user.setUsername(result.getString("username"));
		user.setPassword(result.getString("password"));
		user.setIsVerified(result.getString("isVerified"));
		user.setUid(result.getString("uid"));
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
		user.setUserType(userTypes);
		return user;
	}

}
