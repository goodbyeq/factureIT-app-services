package com.beatus.factureIT.app.services.repository;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.beatus.factureIT.app.services.model.ContactUs;
import com.beatus.factureIT.app.services.utils.Constants;
import com.beatus.factureIT.app.services.utils.Utils;

@Component("contactUsRepository")
public class ContactUsRepository {

	JdbcTemplate jdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(ContactUsRepository.class);

	@Autowired
	@Qualifier(value = "driverManagerDataSource")
	private DataSource dataSource;

	@Autowired
	public ContactUsRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public String addContactUsInfo(ContactUs contact) throws ClassNotFoundException, SQLException {
		if (contact.getFirstname() != null) {
			try {
				LOGGER.info("In addContactUsInfo");
				String sql = "INSERT INTO contact_us (contact_us_id, first_name, last_name, middle_name, phone, email, query) VALUES (?, ?, ?, ?, ?, ?, ?)";
				int rowsInserted = jdbcTemplate.update(sql, Utils.generateRandomKey(50), contact.getFirstname(), contact.getLastname(),
						contact.getMiddlename(), contact.getPhone(), contact.getEmail(), contact.getQuery());

				if (rowsInserted > 0) {
					LOGGER.info("A new contact us was inserted successfully!");
					return Constants.CREATED;
				} else {
					return Constants.ERROR_CREATION;
				}
			} finally {

			}
		}
		return Constants.ERROR_USER_CREATION;
	}
}
