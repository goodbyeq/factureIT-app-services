package com.beatus.factureIT.app.services.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.beatus.factureIT.app.services.model.User;
import com.beatus.factureIT.app.services.utils.Utils;

@Repository("retailerRepository")
public class RetailerRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(RetailerRepository.class);

	@Autowired
	@Qualifier(value = "driverManagerDataSource")
	private DataSource dataSource;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public boolean addRetailer(User retailer) throws ClassNotFoundException, SQLException {

		PreparedStatement statement = null;
		Connection conn = null;
		try {
			LOGGER.info("In addRetailer");
			String sql = "INSERT INTO retailer (retailer_id, retailer_company_id, uid, retailer_company_name, dictributor_company_type, retailer_first_name, retailer_last_name, retailer_phone, retailer_email, address, city, zipcode ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			conn = dataSource.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, Utils.generateRandomKey(50));
			statement.setString(2, retailer.getCompanyId());
			statement.setString(3, retailer.getUid());
			statement.setString(4, retailer.getCompanyName());
			statement.setString(5, retailer.getCompanyType());
			statement.setString(6, retailer.getFirstname());
			statement.setString(7, retailer.getLastname());
			statement.setString(8, retailer.getPhone());
			statement.setString(9, retailer.getEmail());
			statement.setString(10, retailer.getAddress());
			statement.setString(11, retailer.getCity());
			statement.setString(12, retailer.getZipcode());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				LOGGER.info("A new retailer was inserted successfully!");
				return true;
			}else {
				return false;
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
}
