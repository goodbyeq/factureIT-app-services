package com.beatus.factureIT.app.services.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("distributorService")
public class DistributorService {
/*
	private static final Logger LOGGER = LoggerFactory.getLogger(DistributorService.class);

	@Resource(name = "locationService")
	private LocationService locationService;
	
	@Resource(name = "distributorRepository")
	private DistributorRepository distributorRepository;

	public String addDistributor(Distributor distributor, String companyId, String uid) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addDistributor");
		distributor.setCompanyId(companyId);
		distributor.setUid(uid);
		distributorRepository.addDistributor(distributor);
		return Constants.REDIRECT + "/distributor/getDistributors";
	}
	
	public String editDistributor(Distributor distributor, String companyId, String uid) throws ClassNotFoundException, SQLException {
		LOGGER.info("In editDistributor");
		distributor.setCompanyId(companyId);
		distributor.setUid(uid);
		distributorRepository.editDistributor(distributor);
		return Constants.REDIRECT + "/distributor/getDistributors";
	}
	
	public Distributor getDistributorById(int id, String companyId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getDistributorById");
		Distributor distributor = distributorRepository.getDistributorById(id, companyId);
		return distributor;
	}

	public List<Distributor> getDistributors(String companyId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getDistributors");
		List<Distributor> distributors = distributorRepository.getDistributors(companyId);
		return distributors;
	}

	public List<Location> getLocations(String companyId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getLocations");
		return locationService.getLocations(companyId);
	}

	public String deleteDistributor(int distributorId, String companyId, String uid) throws SQLException {
		LOGGER.info("In deleteDistributor");

		boolean isDeleted = distributorRepository.deleteDistributor(distributorId, companyId);
		if(isDeleted){
			return Constants.REDIRECT + "/distributor/getDistributors";
		}else {
			//TO-DO add a message that the row is not deleted
			return Constants.REDIRECT + "/distributor/getDistributors";
		}
			
	}*/

}