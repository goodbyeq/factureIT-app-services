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
		List<String> userType = new ArrayList<String>();
		userType.add(Constants.DISTRIBUTOR_TYPE);
		distributor.setUserType(userType);
		return distributor;
	}

}
