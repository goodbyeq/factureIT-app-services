package com.beatus.factureIT.app.services.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.beatus.factureIT.app.services.model.Distributor;
import com.beatus.factureIT.app.services.repository.DistributorRepository;

@Service
@Component("distributorService")
public class DistributorService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DistributorService.class);

	@Resource(name = "distributorRepository")
	private DistributorRepository distributorRepository;

	public Distributor getDistributorByDistributorId(String id) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getDistributorByDistributorId");
		Distributor distributor = distributorRepository.getDistributorByDistributorId(id);
		return distributor;
	}
	
	public Distributor getDistributorByUID(String uid) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getDistributorByUID");
		Distributor distributor = distributorRepository.getDistributorByUID(uid);
		return distributor;
	}

	public List<Distributor> getAllDistributors() throws ClassNotFoundException, SQLException {
		LOGGER.info("In getAllDistributors");
		List<Distributor> distributors = distributorRepository.getAllDistributors();
		return distributors;
	}

}