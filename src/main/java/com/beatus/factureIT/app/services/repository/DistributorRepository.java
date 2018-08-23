package com.beatus.factureIT.app.services.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.beatus.factureIT.app.services.model.Distributor;
import com.beatus.factureIT.app.services.model.Manufacturer;
import com.beatus.factureIT.app.services.model.Retailer;
import com.beatus.factureIT.app.services.model.User;
import com.beatus.factureIT.app.services.model.mapper.DistributorMapper;
import com.beatus.factureIT.app.services.model.mapper.ManufacturerMapper;
import com.beatus.factureIT.app.services.model.mapper.RetailerMapper;
import com.beatus.factureIT.app.services.utils.Utils;

@Repository("distributorRepository")
public class DistributorRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(DistributorRepository.class);
	
	JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier(value = "driverManagerDataSource")
	private DataSource dataSource;

	@Autowired
	public DistributorRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}


	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public boolean addDistributor(User distributor) throws ClassNotFoundException, SQLException {

		try {
			LOGGER.info("In addDistributor");
			String sql = "INSERT INTO distributor (distributor_id, distributor_company_id, uid, distributor_company_name, dictributor_company_type, distributor_first_name, distributor_last_name, distributor_phone, distributor_email, distributor_address, distributor_city, distributor_state, distributor_zipcode ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			int rowsInserted = jdbcTemplate.update(sql, Utils.generateRandomKey(50), distributor.getCompanyId(), distributor.getUid(),
					distributor.getCompanyName(), distributor.getCompanyType(), distributor.getFirstname(), distributor.getLastname(), distributor.getPhone(), distributor.getEmail(), 
					distributor.getAddress(), distributor.getCity(), distributor.getState(), distributor.getZipcode());
		
			if (rowsInserted > 0) {
				LOGGER.info("A new distributor was inserted successfully!");
				return true;
			} else {
				return false;
			}
		} finally {
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public boolean editDistributor(User distributor) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("In editDistributor");
			String sql = "UPDATE distributor SET distributor_company_name = ?, dictributor_company_type = ?, distributor_first_name= ?, distributor_last_name= ?, distributor_phone= ?, distributor_email= ?, distributor_address= ?, distributor_city= ?, distributor_state = ?,  distributor_zipcode= ? WHERE distributor_id = ?";
			
			int rowsInserted = jdbcTemplate.update(sql, distributor.getCompanyName(), distributor.getCompanyType(), distributor.getFirstname(),
					 distributor.getLastname(), distributor.getPhone(), distributor.getEmail(), 
					distributor.getAddress(), distributor.getCity(), distributor.getState(), distributor.getZipcode(), distributor.getId());
		
			if (rowsInserted > 0) {
				LOGGER.info("A distributor was updated successfully!");
				return true;
			} else {
				return false;
			}
		} finally {
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Throwable.class)
	public Distributor getDistributorByDistributorId(String id) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("In getDistributorByDistributorId");
			String sql = "SELECT dist.distributor_id AS distributorId, dist.distributor_company_name AS distributorCompanyName, "
					+ " dist.distributor_company_type AS distributorCompanyType, dist.distributor_company_id AS distributorCompanyId, dist.uid AS uid, "
					+ " dist.distributor_first_name AS distributorFirstName, dist.distributor_last_name AS distributorLastName, "
					+ " dist.distributor_phone AS distributorPhone, dist.distributor_email AS distributorEmail, dist.distributor_address AS distributorAddress, "
					+ " dist.distributor_city AS distributorCity, dist.distributor_state AS distributorState, dist.distributor_zipcode AS distributorZipcode "
					+ " FROM distributor dist" + " WHERE distributor_id = ? ";
			Distributor distributor = jdbcTemplate.queryForObject(sql, new Object[] {  id }, new DistributorMapper());
			return distributor;
		} finally {
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Throwable.class)
	public Distributor getDistributorByUID(String uid) throws ClassNotFoundException, SQLException {
		
		try {
			LOGGER.info("In getDistributorByDistributorId");
			String sql = "SELECT dist.distributor_id AS distributorId, dist.distributor_company_name AS distributorCompanyName, "
					+ " dist.distributor_company_type AS distributorCompanyType, dist.distributor_company_id AS distributorCompanyId, dist.uid AS uid, "
					+ " dist.distributor_first_name AS distributorFirstName, dist.distributor_last_name AS distributorLastName, "
					+ " dist.distributor_phone AS distributorPhone, dist.distributor_email AS distributorEmail, dist.distributor_address AS distributorAddress, "
					+ " dist.distributor_city AS distributorCity, dist.distributor_state AS distributorState, dist.distributor_zipcode AS distributorZipcode "
					+ " FROM distributor dist, users user" + " WHERE user.uid = ? AND dist.uid = user.uid ";
			Distributor distributor = jdbcTemplate.queryForObject(sql, new Object[] {  uid }, new DistributorMapper());
			return distributor;
		} finally {
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Throwable.class)
	public List<Distributor> getAllDistributors() throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("In getAllDistributors");
			String sql = "SELECT dist.distributor_id AS distributorId, dist.distributor_company_name AS distributorCompanyName, "
					+ " dist.distributor_company_type AS distributorCompanyType, dist.distributor_company_id AS distributorCompanyId, dist.uid AS uid, "
					+ " dist.distributor_first_name AS distributorFirstName, dist.distributor_last_name AS distributorLastName, "
					+ " dist.distributor_phone AS distributorPhone, dist.distributor_email AS distributorEmail, dist.distributor_address AS distributorAddress, "
					+ " dist.distributor_city AS distributorCity, dist.distributor_state AS distributorState, dist.distributor_zipcode AS distributorZipcode "
					+ " FROM distributor dist";
			List<Distributor> distributors = jdbcTemplate.query(sql, new DistributorMapper());
			return distributors;
		} finally {
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public boolean addDistributorRelatedManufacturers(List<String> manufacturerIds, String distributorId) {
		try {
			LOGGER.info("In addDistributorRelatedManufacturers");
			String sql = "INSERT INTO manufacturer_distributors (manufacturer_distributor_id, manufacturer_id, distributor_id) VALUES (?, ?, ?)";
			int[] rowsInserted = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setString(1, Utils.generateRandomKey(50));
					ps.setString(2, manufacturerIds.get(i));
					ps.setString(3, distributorId);
				}

				@Override
				public int getBatchSize() {
					return manufacturerIds.size();
				}
			});
			if (rowsInserted.length > 0) {
				LOGGER.info("A new manufacturer_distributor_id was inserted successfully!");
				return true;
			} else {
				return false;
			}
		} finally {
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Throwable.class)
	public List<Manufacturer> getDistributorRelatedManufacturers(String distributorId) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("In getDistributorRelatedManufacturers");
			String sql = "SELECT dist.manufacturer_id AS manufacturerId, dist.manufacturer_company_name AS manufacturerCompanyName, "
					+ " dist.manufacturer_company_type AS manufacturerCompanyType, dist.manufacturer_company_id AS manufacturerCompanyId, dist.uid AS uid, "
					+ " dist.manufacturer_first_name AS manufacturerFirstName, dist.manufacturer_last_name AS manufacturerLastName, "
					+ " dist.manufacturer_phone AS manufacturerPhone, dist.manufacturer_email AS manufacturerEmail, dist.manufacturer_address AS manufacturerAddress, "
					+ " dist.manufacturer_city AS manufacturerCity, dist.manufacturer_state AS manufacturerState, dist.manufacturer_zipcode AS manufacturerZipcode "
					+ " FROM manufacturer dist, manufacturer_distributors md WHERE md.distributor_id = ? AND md.manufacturer_id = dist.manufacturer_id ";
			List<Manufacturer> manufacturers = jdbcTemplate.query(sql, new Object[] {distributorId}, new ManufacturerMapper());
			return manufacturers;
		} finally {
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public boolean addDistributorRelatedRetailers(List<String> retailerIds, String distributorId) {
		try {
			LOGGER.info("In addDistributorRelatedRetailers");
			String sql = "INSERT INTO distributor_retailers (distributor_retailer_id, distributor_id, retailer_id) VALUES (?, ?, ?)";
			int[] rowsInserted = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setString(1, Utils.generateRandomKey(50));
					ps.setString(2, distributorId);
					ps.setString(3, retailerIds.get(i));
				}

				@Override
				public int getBatchSize() {
					return retailerIds.size();
				}
			});
			if (rowsInserted.length > 0) {
				LOGGER.info("A new distributor_retailer_id was inserted successfully!");
				return true;
			} else {
				return false;
			}
		} finally {
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Throwable.class)
	public List<Manufacturer> getDistributorRelatedRetailers(String distributorId) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("In getDistributorRelatedRetailers");
			String sql = "SELECT dist.retailer_id AS retailerId, dist.retailer_company_name AS retailerCompanyName, "
					+ " dist.retailer_company_type AS retailerCompanyType, dist.retailer_company_id AS retailerCompanyId, dist.uid AS uid, "
					+ " dist.retailer_first_name AS retailerFirstName, dist.retailer_last_name AS retailerLastName, "
					+ " dist.retailer_phone AS retailerPhone, dist.retailer_email AS retailerEmail, dist.retailer_address AS retailerAddress, "
					+ " dist.retailer_city AS retailerCity, dist.retailer_state AS retailerState, dist.retailer_zipcode AS retailerZipcode "
					+ " FROM retailer dist, distributor_retailers dr WHERE dr.distributor_id = ? AND dr.retailer_id = dist.retailer_id ";
			List<Retailer> retailers = jdbcTemplate.query(sql, new Object[] {distributorId}, new RetailerMapper());
			return manufacturers;
		} finally {
		}
	}
	
	public void deleteDistributor(int distributorId, String companyId) throws SQLException {
		/*String sql = "DELETE FROM distributor where distributor_id = ?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, distributorId);
		statement.setString(2, companyId);

		boolean result = statement.execute();
		return result;*/
	}
}
