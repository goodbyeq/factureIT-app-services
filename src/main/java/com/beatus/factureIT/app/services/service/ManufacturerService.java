package com.beatus.factureIT.app.services.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.beatus.factureIT.app.services.model.Manufacturer;
import com.beatus.factureIT.app.services.repository.ManufacturerRepository;

@Service
@Component("manufacturerService")
public class ManufacturerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ManufacturerService.class);

	@Resource(name = "manufacturerRepository")
	private ManufacturerRepository manufacturerRepository;

	public Manufacturer getManufacturerByManufacturerId(String id) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getManufacturerByManufacturerId");
		Manufacturer manufacturer = manufacturerRepository.getManufacturerByManufacturerId(id);
		return manufacturer;
	}
	
	public Manufacturer getManufacturerByUID(String uid) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getManufacturerByUID");
		Manufacturer manufacturer = manufacturerRepository.getManufacturerByUID(uid);
		return manufacturer;
	}

	public List<Manufacturer> getAllManufacturers() throws ClassNotFoundException, SQLException {
		LOGGER.info("In getAllManufacturers");
		List<Manufacturer> manufacturers = manufacturerRepository.getAllManufacturers();
		return manufacturers;
	}

}