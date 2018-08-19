package com.beatus.factureIT.app.services.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.beatus.factureIT.app.services.model.Manufacturer;
import com.beatus.factureIT.app.services.model.User;
import com.beatus.factureIT.app.services.model.mapper.ManufacturerMapper;
import com.beatus.factureIT.app.services.utils.Utils;

@Repository("manufacturerRepository")
public class ManufacturerRepository {

private static final Logger LOGGER = LoggerFactory.getLogger(ManufacturerRepository.class);
	
	JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier(value = "driverManagerDataSource")
	private DataSource dataSource;

	@Autowired
	public ManufacturerRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}


	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public boolean addManufacturer(User manufacturer) throws ClassNotFoundException, SQLException {

		try {
			LOGGER.info("In addManufacturer");
			String sql = "INSERT INTO manufacturer (manufacturer_id, manufacturer_company_id, uid, manufacturer_company_name, dictributor_company_type, manufacturer_first_name, manufacturer_last_name, manufacturer_phone, manufacturer_email, manufacturer_address, manufacturer_city, manufacturer_state, manufacturer_zipcode ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			int rowsInserted = jdbcTemplate.update(sql, Utils.generateRandomKey(50), manufacturer.getCompanyId(), manufacturer.getUid(),
					manufacturer.getCompanyName(), manufacturer.getCompanyType(), manufacturer.getFirstname(), manufacturer.getLastname(), manufacturer.getPhone(), manufacturer.getEmail(), 
					manufacturer.getAddress(), manufacturer.getCity(), manufacturer.getState(), manufacturer.getZipcode());
		
			if (rowsInserted > 0) {
				LOGGER.info("A new manufacturer was inserted successfully!");
				return true;
			} else {
				return false;
			}
		} finally {
		}
	}

	public boolean editManufacturer(User manufacturer) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("In editManufacturer");
			String sql = "UPDATE manufacturer SET manufacturer_company_name = ?, dictributor_company_type = ?, manufacturer_first_name= ?, manufacturer_last_name= ?, manufacturer_phone= ?, manufacturer_email= ?, manufacturer_address= ?, manufacturer_city= ?, manufacturer_state = ?,  manufacturer_zipcode= ? WHERE manufacturer_id = ?";
			
			int rowsInserted = jdbcTemplate.update(sql, manufacturer.getCompanyName(), manufacturer.getCompanyType(), manufacturer.getFirstname(),
					 manufacturer.getLastname(), manufacturer.getPhone(), manufacturer.getEmail(), 
					manufacturer.getAddress(), manufacturer.getCity(), manufacturer.getState(), manufacturer.getZipcode(), manufacturer.getId());
		
			if (rowsInserted > 0) {
				LOGGER.info("A manufacturer was updated successfully!");
				return true;
			} else {
				return false;
			}
		} finally {
		}
	}

	public Manufacturer getManufacturerByManufacturerId(String id) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("In getManufacturerByManufacturerId");
			String sql = "SELECT dist.manufacturer_id AS manufacturerId, dist.manufacturer_company_name AS manufacturerCompanyName, "
					+ " dist.manufacturer_company_type AS manufacturerCompanyType, dist.manufacturer_company_id AS manufacturerCompanyId, dist.uid AS uid, "
					+ " dist.manufacturer_first_name AS manufacturerFirstName, dist.manufacturer_last_name AS manufacturerLastName, "
					+ " dist.manufacturer_phone AS manufacturerPhone, dist.manufacturer_email AS manufacturerEmail, dist.manufacturer_address AS manufacturerAddress, "
					+ " dist.manufacturer_city AS manufacturerCity, dist.manufacturer_state AS manufacturerState, dist.manufacturer_zipcode AS manufacturerZipcode "
					+ " FROM manufacturer dist" + " WHERE manufacturer_id = ? ";
			Manufacturer manufacturer = jdbcTemplate.queryForObject(sql, new Object[] {  id }, new ManufacturerMapper());
			return manufacturer;
		} finally {
		}
	}

	public Manufacturer getManufacturerByUID(String uid) throws ClassNotFoundException, SQLException {
		
		try {
			LOGGER.info("In getManufacturerByManufacturerId");
			String sql = "SELECT dist.manufacturer_id AS manufacturerId, dist.manufacturer_company_name AS manufacturerCompanyName, "
					+ " dist.manufacturer_company_type AS manufacturerCompanyType, dist.manufacturer_company_id AS manufacturerCompanyId, dist.uid AS uid, "
					+ " dist.manufacturer_first_name AS manufacturerFirstName, dist.manufacturer_last_name AS manufacturerLastName, "
					+ " dist.manufacturer_phone AS manufacturerPhone, dist.manufacturer_email AS manufacturerEmail, dist.manufacturer_address AS manufacturerAddress, "
					+ " dist.manufacturer_city AS manufacturerCity, dist.manufacturer_state AS manufacturerState, dist.manufacturer_zipcode AS manufacturerZipcode "
					+ " FROM manufacturer dist, users user" + " WHERE user.uid = ? AND dist.uid = user.uid ";
			Manufacturer manufacturer = jdbcTemplate.queryForObject(sql, new Object[] {  uid }, new ManufacturerMapper());
			return manufacturer;
		} finally {
		}
	}
	
	public List<Manufacturer> getAllManufacturers() throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("In getAllManufacturers");
			String sql = "SELECT dist.manufacturer_id AS manufacturerId, dist.manufacturer_company_name AS manufacturerCompanyName, "
					+ " dist.manufacturer_company_type AS manufacturerCompanyType, dist.manufacturer_company_id AS manufacturerCompanyId, dist.uid AS uid, "
					+ " dist.manufacturer_first_name AS manufacturerFirstName, dist.manufacturer_last_name AS manufacturerLastName, "
					+ " dist.manufacturer_phone AS manufacturerPhone, dist.manufacturer_email AS manufacturerEmail, dist.manufacturer_address AS manufacturerAddress, "
					+ " dist.manufacturer_city AS manufacturerCity, dist.manufacturer_state AS manufacturerState, dist.manufacturer_zipcode AS manufacturerZipcode "
					+ " FROM manufacturer dist";
			List<Manufacturer> manufacturers = jdbcTemplate.query(sql, new ManufacturerMapper());
			return manufacturers;
		} finally {
		}
	}

	public void deleteManufacturer(int manufacturerId, String companyId) throws SQLException {
		/*String sql = "DELETE FROM manufacturer where manufacturer_id = ?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, manufacturerId);
		statement.setString(2, companyId);

		boolean result = statement.execute();
		return result;*/
	}

}
