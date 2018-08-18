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

@Repository("customerRepository")
public class CustomerRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRepository.class);

	@Autowired
	@Qualifier(value = "driverManagerDataSource")
	private DataSource dataSource;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public boolean addCustomer(User customer) throws ClassNotFoundException, SQLException {

		PreparedStatement statement = null;
		Connection conn = null;
		try {
			LOGGER.info("In addCustomer");
			String sql = "INSERT INTO customer (customer_id, customer_company_id, uid, customer_company_name, dictributor_company_type, customer_first_name, customer_last_name, customer_phone, customer_email, address, city, zipcode ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			conn = dataSource.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, Utils.generateRandomKey(50));
			statement.setString(2, customer.getCompanyId());
			statement.setString(3, customer.getUid());
			statement.setString(4, customer.getCompanyName());
			statement.setString(5, customer.getCompanyType());
			statement.setString(6, customer.getFirstname());
			statement.setString(7, customer.getLastname());
			statement.setString(8, customer.getPhone());
			statement.setString(9, customer.getEmail());
			statement.setString(10, customer.getAddress());
			statement.setString(11, customer.getCity());
			statement.setString(12, customer.getZipcode());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				LOGGER.info("A new customer was inserted successfully!");
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
