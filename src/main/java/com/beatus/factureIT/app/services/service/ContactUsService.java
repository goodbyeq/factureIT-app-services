package com.beatus.factureIT.app.services.service;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.beatus.factureIT.app.services.model.ContactUs;
import com.beatus.factureIT.app.services.repository.ContactUsRepository;

@Service
@Component("contactUsService")
public class ContactUsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContactUsService.class);

	@Resource(name = "contactUsRepository")
	private ContactUsRepository contactUsRepository;

	public String addContactUsInfo(ContactUs contact) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addDistributor");
		String isCreated = contactUsRepository.addContactUsInfo(contact);
		return isCreated;
	}

}