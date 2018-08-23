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
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.beatus.factureIT.app.services.model.Distributor;
import com.beatus.factureIT.app.services.model.Retailer;
import com.beatus.factureIT.app.services.model.User;
import com.beatus.factureIT.app.services.model.mapper.DistributorMapper;
import com.beatus.factureIT.app.services.model.mapper.RetailerMapper;
import com.beatus.factureIT.app.services.utils.Utils;

@Repository("retailerRepository")
public class RetailerRepository {

private static final Logger LOGGER = LoggerFactory.getLogger(RetailerRepository.class);
	
	JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier(value = "driverManagerDataSource")
	private DataSource dataSource;

	@Autowired
	public RetailerRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}


	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public boolean addRetailer(User retailer) throws ClassNotFoundException, SQLException {

		try {
			LOGGER.info("In addRetailer");
			String sql = "INSERT INTO retailer (retailer_id, retailer_company_id, uid, retailer_company_name, dictributor_company_type, retailer_first_name, retailer_last_name, retailer_phone, retailer_email, retailer_address, retailer_city, retailer_state, retailer_zipcode ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			int rowsInserted = jdbcTemplate.update(sql, Utils.generateRandomKey(50), retailer.getCompanyId(), retailer.getUid(),
					retailer.getCompanyName(), retailer.getCompanyType(), retailer.getFirstname(), retailer.getLastname(), retailer.getPhone(), retailer.getEmail(), 
					retailer.getAddress(), retailer.getCity(), retailer.getState(), retailer.getZipcode());
		
			if (rowsInserted > 0) {
				LOGGER.info("A new retailer was inserted successfully!");
				return true;
			} else {
				return false;
			}
		} finally {
		}
	}

	public boolean editRetailer(User retailer) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("In editRetailer");
			String sql = "UPDATE retailer SET retailer_company_name = ?, dictributor_company_type = ?, retailer_first_name= ?, retailer_last_name= ?, retailer_phone= ?, retailer_email= ?, retailer_address= ?, retailer_city= ?, retailer_state = ?,  retailer_zipcode= ? WHERE retailer_id = ?";
			
			int rowsInserted = jdbcTemplate.update(sql, retailer.getCompanyName(), retailer.getCompanyType(), retailer.getFirstname(),
					 retailer.getLastname(), retailer.getPhone(), retailer.getEmail(), 
					retailer.getAddress(), retailer.getCity(), retailer.getState(), retailer.getZipcode(), retailer.getId());
		
			if (rowsInserted > 0) {
				LOGGER.info("A retailer was updated successfully!");
				return true;
			} else {
				return false;
			}
		} finally {
		}
	}

	public Retailer getRetailerByRetailerId(String id) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("In getRetailerByRetailerId");
			String sql = "SELECT dist.retailer_id AS retailerId, dist.retailer_company_name AS retailerCompanyName, "
					+ " dist.retailer_company_type AS retailerCompanyType, dist.retailer_company_id AS retailerCompanyId, dist.uid AS uid, "
					+ " dist.retailer_first_name AS retailerFirstName, dist.retailer_last_name AS retailerLastName, "
					+ " dist.retailer_phone AS retailerPhone, dist.retailer_email AS retailerEmail, dist.retailer_address AS retailerAddress, "
					+ " dist.retailer_city AS retailerCity, dist.retailer_state AS retailerState, dist.retailer_zipcode AS retailerZipcode "
					+ " FROM retailer dist" + " WHERE retailer_id = ? ";
			Retailer retailer = jdbcTemplate.queryForObject(sql, new Object[] {  id }, new RetailerMapper());
			return retailer;
		} finally {
		}
	}

	public Retailer getRetailerByUID(String uid) throws ClassNotFoundException, SQLException {
		
		try {
			LOGGER.info("In getRetailerByRetailerId");
			String sql = "SELECT dist.retailer_id AS retailerId, dist.retailer_company_name AS retailerCompanyName, "
					+ " dist.retailer_company_type AS retailerCompanyType, dist.retailer_company_id AS retailerCompanyId, dist.uid AS uid, "
					+ " dist.retailer_first_name AS retailerFirstName, dist.retailer_last_name AS retailerLastName, "
					+ " dist.retailer_phone AS retailerPhone, dist.retailer_email AS retailerEmail, dist.retailer_address AS retailerAddress, "
					+ " dist.retailer_city AS retailerCity, dist.retailer_state AS retailerState, dist.retailer_zipcode AS retailerZipcode "
					+ " FROM retailer dist, users user" + " WHERE user.uid = ? AND dist.uid = user.uid ";
			Retailer retailer = jdbcTemplate.queryForObject(sql, new Object[] {  uid }, new RetailerMapper());
			return retailer;
		} finally {
		}
	}
	
	public List<Retailer> getAllRetailers() throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("In getAllRetailers");
			String sql = "SELECT dist.retailer_id AS retailerId, dist.retailer_company_name AS retailerCompanyName, "
					+ " dist.retailer_company_type AS retailerCompanyType, dist.retailer_company_id AS retailerCompanyId, dist.uid AS uid, "
					+ " dist.retailer_first_name AS retailerFirstName, dist.retailer_last_name AS retailerLastName, "
					+ " dist.retailer_phone AS retailerPhone, dist.retailer_email AS retailerEmail, dist.retailer_address AS retailerAddress, "
					+ " dist.retailer_city AS retailerCity, dist.retailer_state AS retailerState, dist.retailer_zipcode AS retailerZipcode "
					+ " FROM retailer dist";
			List<Retailer> retailers = jdbcTemplate.query(sql, new RetailerMapper());
			return retailers;
		} finally {
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public boolean addRetailerRelatedDistributors(List<String> distributorIds, String retailerId) {
		try {
			LOGGER.info("In addRetailerRelatedDistributors");
			String sql = "INSERT INTO distributor_retailers (distributor_retailer_id, distributor_id, retailer_id) VALUES (?, ?, ?)";
			int[] rowsInserted = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setString(1, Utils.generateRandomKey(50));
					ps.setString(2, distributorIds.get(i));
					ps.setString(3, retailerId);
				}

				@Override
				public int getBatchSize() {
					return distributorIds.size();
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
	public List<Distributor> getRetailerRelatedDistributors(String retailerId) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("In getRetailerRelatedDistributors");
			String sql = "SELECT dist.distributor_id AS distributorId, dist.distributor_company_name AS distributorCompanyName, "
					+ " dist.distributor_company_type AS distributorCompanyType, dist.distributor_company_id AS distributorCompanyId, dist.uid AS uid, "
					+ " dist.distributor_first_name AS distributorFirstName, dist.distributor_last_name AS distributorLastName, "
					+ " dist.distributor_phone AS distributorPhone, dist.distributor_email AS distributorEmail, dist.distributor_address AS distributorAddress, "
					+ " dist.distributor_city AS distributorCity, dist.distributor_state AS distributorState, dist.distributor_zipcode AS distributorZipcode "
					+ " FROM distributor dist, distributor_retailers dr WHERE dr.retailer_id = ? AND dr.distributor_id = dist.distributor_id";
			List<Distributor> distributors = jdbcTemplate.query(sql, new Object[] {retailerId}, new DistributorMapper());
			return distributors;
		} finally {
		}
	}

	public void deleteRetailer(int retailerId, String companyId) throws SQLException {
		/*String sql = "DELETE FROM retailer where retailer_id = ?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, retailerId);
		statement.setString(2, companyId);

		boolean result = statement.execute();
		return result;*/
	}
}
