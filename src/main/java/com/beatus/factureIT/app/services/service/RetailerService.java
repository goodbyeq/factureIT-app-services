package com.beatus.factureIT.app.services.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.beatus.factureIT.app.services.model.Distributor;
import com.beatus.factureIT.app.services.model.Retailer;
import com.beatus.factureIT.app.services.model.User;
import com.beatus.factureIT.app.services.repository.RetailerRepository;

@Service
@Component("retailerService")
public class RetailerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RetailerService.class);

	@Resource(name = "retailerRepository")
	private RetailerRepository retailerRepository;

	public Retailer getRetailerByRetailerId(String id) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getRetailerByRetailerId");
		Retailer retailer = retailerRepository.getRetailerByRetailerId(id);
		return retailer;
	}
	
	public Retailer getRetailerByUID(String uid) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getRetailerByUID");
		Retailer retailer = retailerRepository.getRetailerByUID(uid);
		return retailer;
	}

	public List<Retailer> getAllRetailers() throws ClassNotFoundException, SQLException {
		LOGGER.info("In getAllRetailers");
		List<Retailer> retailers = retailerRepository.getAllRetailers();
		return retailers;
	}
	
	public boolean addRetailerRelatedDistributors(List<String> distributorIds, String retailerId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addRetailerRelatedRetailers service");
		boolean isAdded = retailerRepository.addRetailerRelatedDistributors(distributorIds, retailerId);
		return isAdded;
	}
	
	public List<Distributor> getRetailerRelatedDistributors(String retailerId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getRetailerRelatedRetailers");
		List<Distributor> distributors = retailerRepository.getRetailerRelatedDistributors(retailerId);
		return distributors;
	}
	
	public String addRetailer(User retailer) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addRetailer");
		String id = retailerRepository.addRetailer(retailer);
		return id;
	}
	
	public boolean editRetailer(User retailer) throws ClassNotFoundException, SQLException {
		LOGGER.info("In editRetailer");
		boolean isRetailerEdited = retailerRepository.editRetailer(retailer);
		return isRetailerEdited;
	}
	
	public List<Retailer> getAllRetailersInASpecificArea(String latitude, String longitude, String radius) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getAllRetailers");
		List<Retailer> retailers = retailerRepository.getAllRetailersInASpecificArea(latitude, longitude, radius);
		return retailers;
	}

}