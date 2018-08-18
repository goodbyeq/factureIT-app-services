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

@Repository("manufacturerRepository")
public class ManufacturerRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(ManufacturerRepository.class);

	@Autowired
	@Qualifier(value = "driverManagerDataSource")
	private DataSource dataSource;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public boolean addManufacturer(User manufacturer) throws ClassNotFoundException, SQLException {

		PreparedStatement statement = null;
		Connection conn = null;
		try {
			LOGGER.info("In addManufacturer");
			String sql = "INSERT INTO manufacturer (manufacturer_id, manufacturer_company_id, uid, manufacturer_company_name, dictributor_company_type, manufacturer_first_name, manufacturer_last_name, manufacturer_phone, manufacturer_email, address, city, zipcode ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			conn = dataSource.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, Utils.generateRandomKey(50));
			statement.setString(2, manufacturer.getCompanyId());
			statement.setString(3, manufacturer.getUid());
			statement.setString(4, manufacturer.getCompanyName());
			statement.setString(5, manufacturer.getCompanyType());
			statement.setString(6, manufacturer.getFirstname());
			statement.setString(7, manufacturer.getLastname());
			statement.setString(8, manufacturer.getPhone());
			statement.setString(9, manufacturer.getEmail());
			statement.setString(10, manufacturer.getAddress());
			statement.setString(11, manufacturer.getCity());
			statement.setString(12, manufacturer.getZipcode());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				LOGGER.info("A new manufacturer was inserted successfully!");
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
