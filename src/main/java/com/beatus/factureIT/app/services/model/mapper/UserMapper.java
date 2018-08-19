package com.beatus.factureIT.app.services.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.beatus.factureIT.app.services.model.User;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet result, int rowNum) throws SQLException {
		User user = new User();
		user.setUsername(result.getString("username"));
		user.setPassword(result.getString("password"));
		user.setIsVerified(result.getString("isVerified"));
		user.setUid(result.getString("uid"));
		return user;
	}

}
