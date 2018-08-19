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
		manufacturer.setAddress(result.getString("manufacturerPhone"));
		manufacturer.setCity(result.getString("manufacturerAddress"));
		manufacturer.setState(result.getString("manufacturerCity"));
		manufacturer.setZipcode(result.getString("manufacturerState"));
		List<String> userType = new ArrayList<String>();
		userType.set(0, Constants.DISTRIBUTOR_TYPE);
		manufacturer.setUserType(userType);
		return manufacturer;
	}

}
